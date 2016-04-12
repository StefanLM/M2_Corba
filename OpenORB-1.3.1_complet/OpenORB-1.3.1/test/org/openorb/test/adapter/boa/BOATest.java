/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.adapter.boa;

import junit.framework.TestSuite;

import org.openorb.test.ORBTestCase;

public class BOATest
    extends ORBTestCase
{
    public BOATest( String name )
    {
        super( name );
    }

    public void setUp()
    {
        java.util.Properties props = new java.util.Properties();
        // set known iiop port since persistant references are used.
        props.setProperty( "iiop.port", "17847" );
        props.setProperty( "ssliop.port", "17848" );
        props.setProperty( "ImportModule.BOA", "${openorb.home}config/default.xml#BOA" );
        props.setProperty( "ImportModule.CorbalocService",
              "${openorb.home}config/default.xml#CorbalocService" );

        setUp( props );

    }

    /**
     * Test the BOA basic operations (connect, imp_is_ready, disconnect) as well
     * as most of the BOA package operations (object_key finders, is_a).
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails 
     */
    public void testBOAInvocation() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.ORB orb = getORB();

        TargetImplBOA srv = new TargetImplBOA( orb );

        final org.omg.CORBA.BOA boa = org.omg.CORBA.BOA.init( orb, null ) ;

        boa.connect( srv ) ;
        boa.obj_is_ready( srv ) ;

        Thread _serverThread = new Thread( new Runnable()
                                           {
                                               public void run()
                                               {
                                                   try
                                                   {
                                                       boa.impl_is_ready();
                                                   }
                                                   catch ( java.lang.Exception ex )
                                                   {
                                                       fail( "Unexpected exception caught: " + ex );
                                                   }
                                               }
                                           }
                                         );
        _serverThread.start();

        TargetImplBOA srv2 = new TargetImplBOA( orb );

        boa.connect( srv2, "Backup" ) ;
        boa.obj_is_ready( srv2 ) ;

        boa.forward( srv, srv2 );

        // Test scoring, need to be updated
        ( ( org.openorb.adapter.boa.BOA ) boa ).cache_priority();

        ( ( org.openorb.adapter.boa.BOA ) boa ).single_threaded();

        ( ( org.openorb.adapter.boa.BOA ) boa ).getAdapterManager();

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).find_adapter( "Backup".getBytes() );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).adapter_id( "Backup".getBytes() );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).object_id( "Backup".getBytes() );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).get_server_policies( new int[ 0 ] );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).forced_marshal( "Backup".getBytes() );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).servant_postinvoke( "Backup".getBytes(),
                  ( ( org.openorb.adapter.boa.BOA ) boa ).servant_preinvoke( "Backup".getBytes(),
                  "message", String.class ) );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).locate( "Backup".getBytes() );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).is_a( "Backup".getBytes(),
                  "IDL:openorb.org/test/CORBA/dsi/Target:1.0" );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).get_domain_managers( "Backup".getBytes() );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).get_component( "Backup".getBytes() );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        try
        {
            ( ( org.openorb.adapter.boa.BOA ) boa ).disconnect( srv );
        }
        catch ( java.lang.Exception ex )
        {
            // To be checked later
        }

        boa.deactivate_obj( srv2 ) ;

        boa.deactivate_impl();

        ( ( org.openorb.adapter.boa.BOA ) boa ).etherealize( true );

        try
        {
            _serverThread.join( 1000 );
        }
        catch ( InterruptedException ex )
        {
            // do nothing
        }
    }

    /**
     * Utility class
     */

    public static class TargetImplBOA extends org.omg.CORBA.DynamicImplementation
    {
        private org.omg.CORBA.ORB m_orb;

        public TargetImplBOA( org.omg.CORBA.ORB orb )
        {
            m_orb = orb;
        }

        private static final String[] ID_LIST = {
                                              "IDL:openorb.org/test/CORBA/dsi/Target:1.0" };

        public String[ ] _ids()
        {
            return ID_LIST;
        }

        public void invoke ( org.omg.CORBA.ServerRequest request )
        {
            org.omg.CORBA.ORB orb = m_orb;
            String operation = request.operation();

            if ( operation.equals( "divide" ) )
            {
                org.omg.CORBA.NVList argList = orb.create_list( 0 );

                org.omg.CORBA.Any arg0 = orb.create_any();
                arg0.type( orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float ) );
                argList.add_value( "", arg0, org.omg.CORBA.ARG_IN.value );

                org.omg.CORBA.Any arg1 = orb.create_any();
                arg1.type( orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float ) );
                argList.add_value( "", arg1, org.omg.CORBA.ARG_IN.value );

                request.arguments( argList );

                float nb1 = arg0.extract_float();
                float nb2 = arg1.extract_float();

                try
                {
                    if ( nb2 == 0 )
                        throw new org.openorb.test.dynamic.DIITargetPackage.DivideByZero();

                    float resultat = nb1 / nb2 ;

                    org.omg.CORBA.Any any_result = orb.create_any();

                    any_result.insert_float( resultat );

                    request.set_result( any_result );

                }
                catch ( org.openorb.test.dynamic.DIITargetPackage.DivideByZero ex )
                {
                    org.omg.DynamicAny.DynAnyFactory factory = null;

                    try
                    {
                        org.omg.CORBA.Object obj = null;
                        obj = orb.resolve_initial_references( "DynAnyFactory" );

                        factory =
                            org.omg.DynamicAny.DynAnyFactoryHelper.narrow( obj );
                    }
                    catch ( org.omg.CORBA.ORBPackage.InvalidName e )
                    {
                        fail( "Unexpected exception caught: " + ex );
                    }

                    org.omg.CORBA.StructMember [] members = null;

                    members = new org.omg.CORBA.StructMember[ 0 ];

                    org.omg.CORBA.TypeCode tc = orb.create_exception_tc(
                          "IDL:openorb.org/test/CORBA/dsi/Target/DivideByZero:1.0",
                          "DivideByZero", members );

                    org.omg.DynamicAny.DynAny dany = null;

                    try
                    {
                        dany = factory.create_dyn_any_from_type_code( tc );
                    }
                    catch ( org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode e )
                    {
                        fail( "Unexpected exception caught: " + ex );
                    }

                    org.omg.CORBA.Any any_ex = dany.to_any();

                    request.set_exception( any_ex );
                }
            }
            else
                throw new org.omg.CORBA.BAD_OPERATION();
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( BOATest.class ) );
    }
}

