/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.adapter.poa;

import java.util.Arrays;
import java.util.Properties;

import junit.framework.TestSuite;

import org.omg.CORBA.Policy;

import org.omg.PortableServer.AdapterActivator;
import org.omg.PortableServer.IdAssignmentPolicyValue;
import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.RequestProcessingPolicyValue;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.ServantActivator;
import org.omg.PortableServer.ServantLocator;
import org.omg.PortableServer.ServantRetentionPolicyValue;

import org.openorb.test.ORBTestCase;

/**
 * Tests range of POA policies and features.
 *
 * @author Chris Wood
 */
public class POATest
    extends ORBTestCase
{
    public POATest( String name )
    {
        super( name );
    }

    /**
     * Overloaded to allow the persistant test case to work.
     */
    protected void setUp()
    {
        java.util.Properties props = new java.util.Properties();
        // set known iiop port since persistant references are used.
        props.setProperty( "iiop.port", "17847" );
        props.setProperty( "ssliop.port", "17848" );
        setUp( props );
    }

    /**
     * Test implicit activation with the root POA and basic object operations
     * @exception org.omg.CORBA.UserException if any of the test case fails .
     */
    public void testImplicitActivationRootPOA() throws org.omg.CORBA.UserException
    {
        // find the root poa
        org.omg.CORBA.ORB orb = getORB();

        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );

        Hello svr_ref = ( new HelloImpl( rootPOA ) )._this( orb );

        rootPOA.the_POAManager().activate();

        Hello clt_ref = HelloHelper.narrow( forceMarshal( svr_ref ) );

        clt_ref.hello( "Test msg" );
    }

    /**
     * Test explicit activation and deactivation with the root POA.
     * @exception org.omg.CORBA.UserException if any of the test case fails 
     */
    public void testDefaultPolicies() throws org.omg.CORBA.UserException
    {
        // find the root poa
        org.omg.CORBA.ORB orb = getORB();

        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );
        rootPOA.the_POAManager().activate();

        Policy [] pols = new Policy[ 0 ];

        POA poa = rootPOA.create_POA( "default", rootPOA.the_POAManager(), pols );

        HelloImpl impl = new HelloImpl( poa );

        Hello svr_ref, clt_ref;

        try
        {
            svr_ref = impl._this( orb );
            fail( "able to implicitly activate reference" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // implicit activation is not allowed with this POA
        }

        // activate object


        byte [] oid = poa.activate_object( impl );

        svr_ref = impl._this();

        clt_ref = HelloHelper.narrow( forceMarshal( svr_ref ) );

        // make a call
        clt_ref.hello( "Test msg" );

        try
        {
            byte [] ignore = poa.activate_object( impl );
            fail( "Able to reactivate servant" );
        }
        catch ( org.omg.PortableServer.POAPackage.ServantAlreadyActive ex )
        {
            // activating an object twice is not allowed
        }

        if ( !Arrays.equals( poa.servant_to_id( impl ), oid ) )
            fail( "servant_to_id returned wrong id" );

        if ( poa.id_to_servant( oid ) != impl )
            fail( "id_to_servant returned wrong servant" );

        if ( !Arrays.equals( poa.reference_to_id( svr_ref ), oid ) )
            fail( "reference_to_id returned wrong id for server reference" );

        if ( !Arrays.equals( poa.reference_to_id( clt_ref ), oid ) )
            fail( "reference_to_id returned wrong id for client reference" );

        org.omg.CORBA.Object from_id = poa.id_to_reference( oid );

        if ( !from_id._is_equivalent( svr_ref ) )
            fail( "id_to_reference not equivalent to server reference" );

        if ( !from_id._is_equivalent( clt_ref ) )
            fail( "id_to_reference not equivalent to client reference" );

        org.omg.PortableServer.Servant srv_from_ref = poa.reference_to_servant( svr_ref );

        assertEquals( "reference to servant produced wrong servant", impl, srv_from_ref );

        org.omg.CORBA.Object from_srv = poa.servant_to_reference( srv_from_ref );

        if ( !from_srv._is_equivalent( svr_ref ) )
            fail( "id_to_reference not equivalent to server reference" );

        if ( !from_srv._is_equivalent( clt_ref ) )
            fail( "id_to_reference not equivalent to client reference" );

        poa.deactivate_object( oid );

        try
        {
            clt_ref.hello( "Deactivated" );
            fail( "Able to call deactivated servant" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // It is not allowed to call methods an a deactivated servant
        }

        // try reactivation with the same id.
        poa.activate_object_with_id( oid, impl );
        clt_ref.hello( "Reactivated" );

        // destroy the poa
        poa.destroy( true, true );
        try
        {
            clt_ref.hello( "Deactivated POA" );
            fail( "Able to call deactivated poa" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // It is not allowed to call objects via an deactivated POA
        }

        // 'recreate' the poa
        poa = rootPOA.create_POA( "default", rootPOA.the_POAManager(), pols );
        // try reactivation with the same id. this will not work
        try
        {
            poa.activate_object_with_id( oid, impl );
            fail( "Able to reactivate reference from recreated POA" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // It is not allowed to reactivate a reference once a POA has been deactivated
        }
    }

    /**
     * Test user ids.
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testUserIDPolicy() throws org.omg.CORBA.UserException
    {
        // find the root poa
        org.omg.CORBA.ORB orb = getORB();

        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );
        rootPOA.the_POAManager().activate();

        // user id
        Policy [] pols = new Policy[ 1 ];
        pols[ 0 ] = rootPOA.create_id_assignment_policy( IdAssignmentPolicyValue.USER_ID );

        POA poa = rootPOA.create_POA( "UID", rootPOA.the_POAManager(), pols );

        HelloImpl impl = new HelloImpl( poa );

        byte [] oid = "Carwash\u00FEMy\u00FDCar".getBytes();

        poa.activate_object_with_id( oid, impl );

        Hello svr_ref = impl._this();

        org.omg.CORBA.Object from_id = poa.id_to_reference( oid );

        if ( !from_id._is_equivalent( svr_ref ) )
            fail( "id_to_reference not equivalent to server reference" );

        Hello clt_ref = HelloHelper.narrow( forceMarshal( svr_ref ) );

        clt_ref.hello( "Test msg" );
    }

    /**
     * Test implicit activation with the root POA.
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testNonRetainDefaultServant() throws org.omg.CORBA.UserException
    {
        // find the root poa
        org.omg.CORBA.ORB orb = getORB();

        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );

        // non-retain, default servant
        Policy [] pols = new Policy[ 2 ];
        pols[ 0 ] = rootPOA.create_servant_retention_policy(
              ServantRetentionPolicyValue.NON_RETAIN );
        pols[ 1 ] = rootPOA.create_request_processing_policy( 
              RequestProcessingPolicyValue.USE_DEFAULT_SERVANT );

        POA poa = rootPOA.create_POA( "NR+DS", rootPOA.the_POAManager(), pols );

        HelloImpl impl = new HelloImpl( poa );

        poa.set_servant( impl );

        Hello svr_ref1 = HelloHelper.narrow( poa.create_reference( HelloHelper.id() ) );
        Hello svr_ref2 = HelloHelper.narrow( poa.create_reference( HelloHelper.id() ) );

        rootPOA.the_POAManager().activate();

        Hello clt_ref1 = HelloHelper.narrow( forceMarshal( svr_ref1 ) );
        Hello clt_ref2 = HelloHelper.narrow( forceMarshal( svr_ref2 ) );

        clt_ref1.hello( "Test msg" );
        clt_ref2.hello( "Test msg" );
    }

    /**
      * Test persistant/system ids, adapter activator, servant activator.
      *
      * @exception org.omg.CORBA.UserException if any of the test case fails
      */
    public void testPersistanceAndActivators() throws org.omg.CORBA.UserException
    {
        // find the root poa
        org.omg.CORBA.ORB orb = getORB();

        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );

        // register adapter activator to create child servants
        AdapterActivator activ = new MyAdapterActivator();
        rootPOA.the_activator( activ );

        rootPOA.the_POAManager().activate();

        // setup references...

        POA poaAPU = rootPOA.find_POA( "APU", true );
        POA poaAPS = rootPOA.find_POA( "APS", true );
        POA poaATU = rootPOA.find_POA( "ATU", true );
        POA poaLTS = rootPOA.find_POA( "LTS", true );

        Hello svr_APU = HelloHelper.narrow( poaAPU.create_reference_with_id( "APU".getBytes(),
              HelloHelper.id() ) );
        Hello svr_APS = HelloHelper.narrow( poaAPS.create_reference( HelloHelper.id() ) );
        Hello svr_ATU = HelloHelper.narrow( poaATU.create_reference_with_id( "ATU".getBytes(),
              HelloHelper.id() ) );
        Hello svr_LTS = HelloHelper.narrow( poaLTS.create_reference( HelloHelper.id() ) );

        byte [] id_APU = poaAPU.reference_to_id( svr_APU );
        byte [] id_APS = poaAPS.reference_to_id( svr_APS );
        byte [] id_ATU = poaATU.reference_to_id( svr_ATU );
        byte [] id_LTS = poaLTS.reference_to_id( svr_LTS );

        // unlike most of the tests we must use a different orb for the client
        // so shutdown won't destroy the client side.
        Properties props = new Properties();
        props.setProperty( "openorb.useStaticThreadGroup", "true" );
        props.setProperty( "openorb.server.enable", "false" );
        org.omg.CORBA.ORB clientORB = org.omg.CORBA.ORB.init( new String[ 0 ], props );

        Hello clt_APU = HelloHelper.narrow( clientORB.string_to_object(
              orb.object_to_string( svr_APU ) ) );
        Hello clt_APS = HelloHelper.narrow( clientORB.string_to_object(
              orb.object_to_string( svr_APS ) ) );
        Hello clt_ATU = HelloHelper.narrow( clientORB.string_to_object(
              orb.object_to_string( svr_ATU ) ) );
        Hello clt_LTS = HelloHelper.narrow( clientORB.string_to_object(
              orb.object_to_string( svr_LTS ) ) );

        // call the references

        clt_APU.hello( "Test APU" );
        clt_APS.hello( "Test APS" );
        clt_ATU.hello( "Test ATU" );
        clt_ATU.hello( "Test ATU Again (incarnate not called)" );
        clt_LTS.hello( "Test LTS" );
        clt_LTS.hello( "Test LTS Again (preinvoke called)" );

        // destroy all the POAs
        poaAPU.destroy( true, true );
        poaAPS.destroy( true, true );
        poaATU.destroy( true, true );
        poaLTS.destroy( true, true );

        // persistant references should still work fine
        clt_APU.hello( "Test APU POA Restarted by request" );
        clt_APS.hello( "Test APS POA Restarted by request" );
        clt_ATU.hello( "Test ATS POA Restarted by request" );
        clt_LTS.hello( "Test LTS POA Restarted by request" );

        // restart the orb
        orb = restartORB();

        rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );

        // register adapter activator to create child servants
        rootPOA.the_activator( activ );

        rootPOA.the_POAManager().activate();

        // persistant references should still work fine
        clt_APU.hello( "Test APU ORB Restarted" );
        clt_APS.hello( "Test APS ORB Restarted" );

        // transient references still should not work
        try
        {
            clt_ATU.hello( "Should not work" );
            fail( "Able to call reactivated poa" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // It is not allowed to call objs with transient refs once the POA has been destroyed
        }

        try
        {
            clt_LTS.hello( "Should not work" );
            fail( "Able to call reactivated poa" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // It is not allowed to call objs with transient refs once the POA has been destroyed
        }
    }

    /**
     *  Utility classes.
     */

    static class HelloImpl
                extends HelloPOA
    {
        HelloImpl( POA poa )
        {
            m_poa = poa;
        }

        private POA m_poa;

        public void hello( String msg )
        {
            try
            {
                System.out.println( "Hello message: \"" + msg + "\" with id:" );
                ORBTestCase.writeVerboseHex( System.out, _object_id() );
            }
            catch ( Exception ex )
            {
                System.out.println( "[ERR : HelloImpl] " + ex.toString() );
            }
        }

        public POA _default_POA()
        {
            return m_poa;
        }
    }


    static class MyAdapterActivator
        extends org.omg.CORBA.LocalObject
        implements AdapterActivator
    {
        public boolean unknown_adapter( org.omg.PortableServer.POA parent, String name )
        {
            try
            {
                Policy [] pols = new Policy[ 4 ];
                pols[ 0 ] = parent.create_request_processing_policy(
                      RequestProcessingPolicyValue.USE_SERVANT_MANAGER );
                boolean locator;

                switch ( name.charAt( 0 ) )
                {

                case 'A':
                    pols[ 1 ] = parent.create_servant_retention_policy(
                          ServantRetentionPolicyValue.RETAIN );
                    locator = false;
                    break;

                case 'L':
                    pols[ 1 ] = parent.create_servant_retention_policy(
                          ServantRetentionPolicyValue.NON_RETAIN );
                    locator = true;
                    break;

                default:
                    return false;
                }

                switch ( name.charAt( 1 ) )
                {

                case 'P':
                    pols[ 2 ] = parent.create_lifespan_policy( LifespanPolicyValue.PERSISTENT );
                    break;

                case 'T':
                    pols[ 2 ] = parent.create_lifespan_policy( LifespanPolicyValue.TRANSIENT );
                    break;

                default:
                    return false;
                }

                switch ( name.charAt( 2 ) )
                {

                case 'U':
                    pols[ 3 ] = parent.create_id_assignment_policy(
                          IdAssignmentPolicyValue.USER_ID );
                    break;

                case 'S':
                    pols[ 3 ] = parent.create_id_assignment_policy(
                          IdAssignmentPolicyValue.SYSTEM_ID );
                    break;

                default:
                    return false;
                }

                POA poa = parent.create_POA( name, parent.the_POAManager(), pols );

                if ( locator )
                    poa.set_servant_manager( new MyServantLocator() );
                else
                    poa.set_servant_manager( new MyServantActivator() );

                System.out.println( "Created adapter \"" + name + "\"" );

                return true;
            }
            catch ( Exception ex )
            {
                System.out.println( "[ERR : MyAdapterActivator] " + ex.toString() );
                return false;
            }
        }
    }

    static class MyServantActivator
        extends org.omg.CORBA.LocalObject
        implements ServantActivator
    {
        public Servant incarnate( byte[] oid, org.omg.PortableServer.POA adapter )
        throws org.omg.PortableServer.ForwardRequest
        {
            try
            {
                System.out.println( "Incarnate on target with id:" );
                ORBTestCase.writeVerboseHex( System.out, oid );
            }
            catch ( Exception ex )
            {
                System.out.println( "[ERR : MyServantActivator] " + ex.toString() );
            }

            return new HelloImpl( adapter );
        }

        public void etherealize( byte[] oid, org.omg.PortableServer.POA adapter,
              org.omg.PortableServer.Servant serv, boolean cleanup_in_progress,
              boolean remaining_activations )
        {
            try
            {
                System.out.println( "Etherialize on target with id:" );
                ORBTestCase.writeVerboseHex( System.out, oid );
            }
            catch ( Exception ex )
            {
                System.out.println( "[ERR : MyServantActivator] " + ex.toString() );
            }
        }
    }

    public static class MyServantLocator
        extends org.omg.CORBA.LocalObject
        implements ServantLocator
    {
        public Servant preinvoke( byte[] oid, POA adapter, String operation,
              org.omg.PortableServer.ServantLocatorPackage.CookieHolder the_cookie )
            throws org.omg.PortableServer.ForwardRequest
        {
            try
            {
                System.out.println( "Preinvoke on target with id:" );
                ORBTestCase.writeVerboseHex( System.out, oid );
            }
            catch ( Exception ex )
            {
                System.out.println( "[ERR : MyServantActivator] " + ex.toString() );
            }

            return new HelloImpl( adapter );
        }

        public void postinvoke( byte[] oid, POA adapter, String operation,
              Object the_cookie, Servant the_servant )
        {
            try
            {
                System.out.println( "Postinvoke on target with id:" );
                ORBTestCase.writeVerboseHex( System.out, oid );
            }
            catch ( Exception ex )
            {
                System.out.println( "[ERR : MyServantActivator] " + ex.toString() );
            }
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( POATest.class ) );
    }
}
