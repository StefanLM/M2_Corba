/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.iiop.bidir;

import junit.framework.TestSuite;

import org.omg.CORBA.Policy;
import org.omg.CORBA.PolicyManager;
import org.omg.CORBA.SetOverrideType;

import org.omg.PortableServer.ImplicitActivationPolicyValue;
import org.omg.PortableServer.POA;

import org.openorb.test.ORBTestCase;

/**
 * Tests bidirectional IIOP.
 *
 * @author Chris Wood
 */
public class BiDirTest extends ORBTestCase
{
    public BiDirTest( String name )
    {
        super( name );
    }

    /**
      * Overloaded to allow the persistant test case to work.
      */
    protected void setUp()
    {
        java.util.Properties props = new java.util.Properties();
        props.setProperty( "openorb.useStaticThreadGroup", "true" );
        props.setProperty( "iiop.biDirOnlyServer", "true" );
        setUp( props );

        props.setProperty( "iiop.biDirOnlyServer", "false" );
        m_orb2 = org.omg.CORBA.ORB.init( new String[ 0 ], props );

        Thread curr = Thread.currentThread();
        curr.interrupt();
        m_orb2.run();
    }

    protected void tearDown()
    {
        super.tearDown();

        m_orb2.shutdown( true );
    }

    private org.omg.CORBA.ORB m_orb2;
    private static boolean [] s_callAt;

    /**
     * Test bidirectional iiop.
     * 
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testBiDir() throws org.omg.CORBA.UserException
    {
        // find the root poa
        org.omg.CORBA.ORB orb = getORB();

        org.omg.CORBA.Any any = orb.create_any();
        any.insert_ushort( org.omg.BiDirPolicy.BOTH.value );

        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );
        POA rootPOA2 = ( POA ) m_orb2.resolve_initial_references( "RootPOA" );

        Policy [] policies = new Policy[ 3 ];
        policies[ 0 ] = orb.create_policy( org.omg.BiDirPolicy.BIDIRECTIONAL_POLICY_TYPE.value,
              any );
        policies[ 1 ] = orb.create_policy( org.openorb.policy.FORCE_MARSHAL_POLICY_ID.value,
              orb.create_any() );
        policies[ 2 ] = rootPOA.create_implicit_activation_policy(
              ImplicitActivationPolicyValue.IMPLICIT_ACTIVATION );

        // set client policies, use bidir and force marshalling
        PolicyManager opm = ( PolicyManager ) orb.resolve_initial_references( "ORBPolicyManager" );
        opm.set_policy_overrides( policies, SetOverrideType.ADD_OVERRIDE );
        PolicyManager opm2 = ( PolicyManager ) m_orb2.resolve_initial_references(
              "ORBPolicyManager" );
        opm2.set_policy_overrides( policies, SetOverrideType.ADD_OVERRIDE );

        // setup poa
        POA poa = rootPOA.create_POA( "BidirAdapter", rootPOA.the_POAManager(), policies );
        POA poa2 = rootPOA2.create_POA( "BidirAdapter", rootPOA2.the_POAManager(), policies );

        // activate root poa
        rootPOA.the_POAManager().activate();
        rootPOA2.the_POAManager().activate();

        Callback ref1 = ( new CallbackImpl( poa ) )._this( orb );
        Callback ref2 = ( new CallbackImpl( poa2 ) )._this( m_orb2 );
        Callback ref2at1 = CallbackHelper.narrow( orb.string_to_object(
              m_orb2.object_to_string( ref2 ) ) );

        // do the callback
        s_callAt = new boolean[ 2 ];
        ref2at1.call( ref1, 1 );

        for ( int i = s_callAt.length - 1; i >= 0; --i )
            assertTrue( "Call level did not reach level " + i, s_callAt[ i ] );

    }

    /**
     * Test bidirectional iiop with fragmentation.
     * 
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testBiDirFragmented()
    throws org.omg.CORBA.UserException
    {
        // find the root poa
        org.omg.CORBA.ORB orb = getORB();

        org.omg.CORBA.Any any = orb.create_any();
        any.insert_ushort( org.omg.BiDirPolicy.BOTH.value );

        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );
        POA rootPOA2 = ( POA ) m_orb2.resolve_initial_references( "RootPOA" );

        Policy [] policies = new Policy[ 3 ];
        policies[ 0 ] = orb.create_policy( org.omg.BiDirPolicy.BIDIRECTIONAL_POLICY_TYPE.value,
              any );
        policies[ 1 ] = orb.create_policy( org.openorb.policy.FORCE_MARSHAL_POLICY_ID.value,
              orb.create_any() );
        policies[ 2 ] = rootPOA.create_implicit_activation_policy(
              ImplicitActivationPolicyValue.IMPLICIT_ACTIVATION );

        // set client policies, use bidir and force marshalling
        PolicyManager opm = ( PolicyManager ) orb.resolve_initial_references( "ORBPolicyManager" );
        opm.set_policy_overrides( policies, SetOverrideType.ADD_OVERRIDE );
        PolicyManager opm2 = ( PolicyManager ) m_orb2.resolve_initial_references(
              "ORBPolicyManager" );
        opm2.set_policy_overrides( policies, SetOverrideType.ADD_OVERRIDE );

        // setup poa
        POA poa = rootPOA.create_POA( "BidirAdapter", rootPOA.the_POAManager(), policies );
        POA poa2 = rootPOA2.create_POA( "BidirAdapter", rootPOA2.the_POAManager(), policies );

        // activate root poa
        rootPOA.the_POAManager().activate();
        rootPOA2.the_POAManager().activate();

        Callback ref1 = ( new CallbackImpl( poa ) )._this( orb );
        Callback ref2 = ( new CallbackImpl( poa2 ) )._this( m_orb2 );
        Callback ref2at1 = CallbackHelper.narrow( orb.string_to_object(
              m_orb2.object_to_string( ref2 ) ) );

        // do the callback
        s_callAt = new boolean[ 4 ];
        ref2at1.call_large( ref1, 3, new byte[ 1024 * 1024 ] );

        for ( int i = s_callAt.length - 1; i >= 0; --i )
            assertTrue( "Call level did not reach level " + i, s_callAt[ i ] );

    }

    private static class CallbackImpl extends CallbackPOA
    {
        CallbackImpl( POA poa )
        {
            m_poa = poa;
        }

        private POA m_poa;

        public POA _default_POA()
        {
            return m_poa;
        }

        public void call( org.openorb.test.iiop.bidir.Callback call, int remain )
        {
            s_callAt[ remain ] = true;

            if ( remain > 0 )
                call.call( _this(), remain - 1 );
        }

        public void call_large( org.openorb.test.iiop.bidir.Callback call,
                                int remain, byte[] buffer )
        {
            s_callAt[ remain ] = true;

            if ( remain > 0 )
                call.call_large( _this(), remain - 1, buffer );
        }

    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( new TestSuite( BiDirTest.class ) );
    }
}
