/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.adapter.fwd;

//import org.omg.PortableServer.*;
import junit.framework.TestSuite;

import org.openorb.test.ORBTestCase;

import org.openorb.corbaloc.CorbalocService;
import org.openorb.corbaloc.CorbalocServiceHelper;

import org.openorb.test.adapter.poa.Hello;
import org.openorb.test.adapter.poa.HelloPOA;
import org.openorb.test.adapter.poa.HelloHelper;

/**
 * Tests range of POA policies and features.
 * <p>
 *
 * @author Chris Wood
 */
public class FWDTest
    extends ORBTestCase
{
    public FWDTest( String name )
    {
        super( name );
    }

    private static final String PORT = "23412";

    /**
     * Overloaded to allow the persistant test case to work.
     */
    protected void setUp()
    {
        java.util.Properties props = new java.util.Properties();
        // set known iiop port since persistant references are used.
        props.setProperty( "ImportModule.Corbaloc",
              "${openorb.home}config/default.xml#CorbalocService" );
        props.setProperty( "iiop.port", PORT );
        setUp( props );
    }

    /**
     * Test that init reference service can be resolved.
     */
    public void testResolve() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.ORB orb = getORB();

        CorbalocService service = CorbalocServiceHelper.narrow(
              orb.resolve_initial_references( "CorbalocService" ) );

        Hello hello = new HelloImpl()._this( getORB() );

        service.put( "Test", hello );

        assertTrue( "Get object is not equivalent", hello._is_equivalent( service.get( "Test" ) ) );

        org.omg.CORBA.Object obj = orb.string_to_object( "corbaloc::localhost:" + PORT + "/Test" );

        Hello ini = HelloHelper.narrow( obj );

        ini.hello( "Mouse" );
    }

    /**
     * Test that init reference service can be resolved.
     */
    public void testLocalCorbaloc() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.ORB orb = getORB();

        CorbalocService service = CorbalocServiceHelper.narrow(
              orb.string_to_object( "corbaloc::localhost:" + PORT + "/CorbalocService" ) );

        Hello hello = new HelloImpl()._this( getORB() );

        service.put( "Test", hello );

        assertTrue( "Get object is not equivalent", hello._is_equivalent( service.get( "Test" ) ) );

        org.omg.CORBA.Object obj = orb.string_to_object( "corbaloc::localhost:" + PORT + "/Test" );

        Hello ini = HelloHelper.narrow( obj );

        ini.hello( "Mouse" );
    }

    /**
     * Test that init reference service can be resolved.
     */
    public void testRemoteCorbaloc() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( ( String[] ) null, null );
        Thread.currentThread().interrupt();
        orb.run();
        Thread.interrupted();

        CorbalocService service = CorbalocServiceHelper.narrow(
              orb.string_to_object( "corbaloc::localhost:" + PORT + "/CorbalocService" ) );

        HelloImpl helloImpl = new HelloImpl();
        Hello hello = helloImpl._this( getORB() );
        helloImpl._default_POA().the_POAManager().activate();

        service.put( "Test", hello );

        assertTrue( "Get object is not equivalent", hello._is_equivalent( service.get( "Test" ) ) );

        org.omg.CORBA.Object obj = orb.string_to_object( "corbaloc::localhost:" + PORT + "/Test" );

        Hello ini = HelloHelper.narrow( obj );

        ini.hello( "Mouse" );


        orb.shutdown( true );
    }

    /**
     *  Utility classes.
     */
    static class HelloImpl
        extends HelloPOA
    {
        public void hello( String msg )
        {
            System.out.println( msg );
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( FWDTest.class ) );
    }
}

