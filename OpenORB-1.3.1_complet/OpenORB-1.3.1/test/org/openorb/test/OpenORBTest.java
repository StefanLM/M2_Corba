/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;


/**
 * This is the mainline for the OpenORB test harness. This test harness can 
 * execute one or more tests depending on the input parameter. It also follows
 * the signelton design pattern.
 *
 * @author Chris Wood
 */
public class OpenORBTest
    extends TestCase
{
    public OpenORBTest( String name )
    {
        super( name );
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTest( org.openorb.test.CORBA.CORBATestSuite.suite() );
        suite.addTest( org.openorb.test.iiop.IIOPTest.suite() );
        suite.addTest( org.openorb.test.dynamic.DynamicTestSuite.suite() );
        suite.addTestSuite( org.openorb.test.adapter.poa.POATest.class );
        suite.addTestSuite( org.openorb.test.adapter.boa.BOATest.class );
        suite.addTestSuite( org.openorb.test.adapter.fwd.FWDTest.class );
        suite.addTestSuite( org.openorb.test.pi.PITest.class );
        suite.addTestSuite( org.openorb.test.naming.NamingTest.class );
        return suite;
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( suite() );
    }
}
