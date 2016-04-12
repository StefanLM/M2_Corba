/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.CORBA;

import java.util.Properties;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ORBTest
    extends TestCase
{
    public ORBTest( String name )
    {
        super( name );
    }

    public void setUp()
    {
        Properties props = new Properties();
        props.setProperty( "openorb.useStaticThreadGroup", "true" );
        props.setProperty( "openorb.server.enable", "false" );
        m_orb = org.omg.CORBA.ORB.init( ( String[] ) null, props );
        m_any = m_orb.create_any();
    }

    protected void tearDown()
    {
        m_orb.shutdown( true );
    }

    private org.omg.CORBA.ORB m_orb;
    private org.omg.CORBA.Any m_any;

    /**
     * ORBClass test. Test all ORB operations like resolve_initial_references
     * and create operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testCorbaloc() throws org.omg.CORBA.UserException
    {
        // testing corbaloc: [#510234] ArrayIndexOutOfBoundsException
        String corbalocURL = 
              "corbaloc:iiop:1.2@www.sun.com:10000,iiop:1.2@www.microsoft.com:10000/NameService";
        org.omg.CORBA.Object obj = m_orb.string_to_object(corbalocURL);
        String ior = m_orb.object_to_string(obj);
    }

    /**
     * ORBClass test. Test all ORB operations like resolve_initial_references
     * and create operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testORB() throws org.omg.CORBA.UserException
    {
        String[] list = m_orb.list_initial_services();

        // resolve a local service
        m_orb.resolve_initial_references( "ORBPolicyManager" );

        // resolve a nonlocal reference
        m_orb.resolve_initial_references( "NameService" );

        // work ops.
        m_orb.work_pending();

        m_orb.perform_work();

        // factory ops
        m_orb.create_list( 3 );

        m_orb.create_named_value( "Test", null, 0 );

        m_orb.create_exception_list();

        m_orb.create_context_list();

        m_orb.create_environment();

        m_orb.get_service_information( ( short ) 0, null );

        /*
        m_orb.send_multiple_requests_oneway ( new org.omg.CORBA.Request [0] );

        m_orb.send_multiple_requests_deferred ( new org.omg.CORBA.Request [0] );

        m_orb.poll_next_response();

        m_orb.get_next_response();
        */
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( ORBTest.class ) );
    }
}
