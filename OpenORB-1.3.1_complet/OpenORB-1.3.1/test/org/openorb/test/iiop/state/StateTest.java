/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.iiop.state;

import java.util.Properties;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.omg.CORBA.Policy;
import org.omg.CORBA.PolicyManager;
import org.omg.CORBA.SetOverrideType;

import org.omg.PortableServer.ImplicitActivationPolicyValue;
import org.omg.PortableServer.POA;

/**
 * Tests bidirectional IIOP.
 *
 * @author Chris Wood
 */
public class StateTest
    extends TestCase
{
    public StateTest( String name )
    {
        super( name );
    }

    private static final int REPEATS = 5;

    private Policy m_biDirPolicy = null;
    private org.omg.CORBA.ORB m_orb;
    private org.omg.CORBA.ORB m_orb2;

    private StateTarget m_ref;
    private StateTarget m_ref2;

    /**
     * This is the specialized setup, called directly from the test functions
     */
    private void setUp( Properties props, boolean withBiDir ) throws org.omg.CORBA.UserException
    {
        if ( props == null )
            props = new Properties();

        props.setProperty( "openorb.useStaticThreadGroup", "true" );

        if ( withBiDir )
        {
            props.setProperty( "iiop.allowBidir", "true" );
            m_orb2 = org.omg.CORBA.ORB.init( ( String [] ) null, props );

            org.omg.CORBA.Any any = m_orb2.create_any();
            any.insert_ushort( org.omg.BiDirPolicy.BOTH.value );
            m_biDirPolicy = m_orb2.create_policy(
                  org.omg.BiDirPolicy.BIDIRECTIONAL_POLICY_TYPE.value, any );

            Thread.currentThread().interrupt();
            m_orb2.run();
            Thread.currentThread().interrupted();
        }

        m_orb = org.omg.CORBA.ORB.init( ( String [] ) null, props );

        Thread.currentThread().interrupt();
        m_orb.run();
        Thread.currentThread().interrupted();

        m_ref = initRef( m_orb );

        if ( withBiDir )
            m_ref2 = StateTargetHelper.narrow(
                  m_orb.string_to_object( m_orb2.object_to_string( initRef( m_orb2 ) ) ) );
    }

    public void tearDown()
    {
        if ( m_orb != null )
            m_orb.shutdown( true );

        if ( m_orb2 != null )
            m_orb2.shutdown( true );
    }

    private StateTarget initRef( org.omg.CORBA.ORB orb ) throws org.omg.CORBA.UserException
    {
        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );

        Policy [] policies = new Policy[ ( m_biDirPolicy == null ) ? 2 : 3 ];
        policies[ 0 ] = orb.create_policy( org.openorb.policy.FORCE_MARSHAL_POLICY_ID.value,
              orb.create_any() );
        policies[ 1 ] = rootPOA.create_implicit_activation_policy(
              ImplicitActivationPolicyValue.IMPLICIT_ACTIVATION );

        if ( m_biDirPolicy != null )
            policies[ 2 ] = m_biDirPolicy;

        // set client policies, use bidir and force marshalling
        PolicyManager opm = ( PolicyManager ) orb.resolve_initial_references( "ORBPolicyManager" );

        opm.set_policy_overrides( policies, SetOverrideType.ADD_OVERRIDE );

        // setup poa
        POA poa = rootPOA.create_POA( "BidirAdapter", rootPOA.the_POAManager(), policies );

        // activate root poa
        rootPOA.the_POAManager().activate();

        return ( new StateTargetImpl( poa ) )._this( orb );
    }

    /**
     * Tests pause from the client side. This test will actualy work only 
     * statisticaly, hence the repeating.
     * 
     * @exception org.omg.CORBA.UserException if any of the test case fails
     * @exception InterruptedException if the thread is interrupted for any reason
     */
    public void testClientPause() throws org.omg.CORBA.UserException, InterruptedException
    {
        Properties props = new Properties();
        props.setProperty( "openorb.client.reapPauseDelay", "200" );

        setUp( props, false );

        for ( int i = 0; i < REPEATS; ++i )
        {
            m_ref.ping();

            Thread.sleep( 500 );
        }
    }

    /**
     * Tests pause from the server side. This test will actualy work only 
     * statisticaly, hence the repeating.
     * 
     * @exception org.omg.CORBA.UserException if any of the test case fails
     * @exception InterruptedException if the thread is interrupted for any reason
     */
    public void testServerPause() throws org.omg.CORBA.UserException, InterruptedException
    {
        Properties props = new Properties();
        props.setProperty( "openorb.server.reapCloseDelay", "500" );

        setUp( props, false );

        for ( int i = 0; i < REPEATS; ++i )
        {
            m_ref.ping();

            Thread.sleep( 700 );
        }
    }

    /**
     * Tests pause from the initiating side of a bidir connection. This test 
     * will actualy work only statisticaly, hence the repeating.
     * 
     * @exception org.omg.CORBA.UserException if any of the test case fails
     * @exception InterruptedException if the thread is interrupted for any reason
     */
    public void testBiDirClientPause() throws org.omg.CORBA.UserException, InterruptedException
    {
        Properties props = new Properties();
        props.setProperty( "openorb.client.reapPauseDelay", "200" );

        setUp( props, true );

        for ( int i = 0; i < REPEATS; ++i )
        {
            m_ref2.call_ping( m_ref );

            Thread.sleep( 500 );
        }

        // try to reuse previous ref in new call
        m_ref2.call_ping( null );
    }

    /**
     * Tests pause from the listening side of a bidir connection. This test will
     * actualy work only statisticaly, hence the repeating.
     * 
     * @exception org.omg.CORBA.UserException if any of the test case fails
     * @exception InterruptedException if the thread is interrupted for any reason
     */
    public void testBiDirServerPause() throws org.omg.CORBA.UserException, InterruptedException
    {
        Properties props = new Properties();
        props.setProperty( "openorb.server.reapCloseDelay", "700" );

        setUp( props, true );

        for ( int i = 0; i < REPEATS; ++i )
        {
            m_ref2.call_ping( m_ref );

            Thread.sleep( 1000 );
        }

        // try to reuse previous ref in new call
        m_ref2.call_ping( null );
    }

    private static class StateTargetImpl
        extends StateTargetPOA
    {
        StateTargetImpl( POA poa )
        {
            m_poa = poa;
        }

        private POA m_poa;

        public POA _default_POA()
        {
            return m_poa;
        }

        public void ping()
        {
            int i = 1;
        }

        public boolean sleep( long time )
        {
            try
            {
                Thread.sleep( time );
                return false;
            }
            catch ( InterruptedException ex )
            {
                return true;
            }
        }

        public boolean call_sleep( StateTarget test, long time )
        {
            if ( test != null )
                m_lastTest = test;

            m_lastTest.ping();

            return test.sleep( time );
        }

        public void call_ping( StateTarget test )
        {
            if ( test != null )
                m_lastTest = test;

            m_lastTest.ping();
        }

        private StateTarget m_lastTest;
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( StateTest.class ) );
    }
}
