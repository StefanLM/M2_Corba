/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.CORBA;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;


/**
 * Tests marshaling and unmarshaling of various iiop types.
 * <p>
 *
 * @author Chris Wood
 */
public class CORBATestSuite
    extends TestCase
{
    public CORBATestSuite( String name )
    {
        super( name );
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite( SingletonTest.class );
        suite.addTestSuite( ORBTest.class );
        suite.addTestSuite( DataStreamTest.class );
        suite.addTestSuite( AnyTest.class );
        suite.addTestSuite( TypeCodeTest.class );
        return suite;
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( suite() );
    }
}
