/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.iiop;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

import org.openorb.test.iiop.primitive.PrimitiveTest;
import org.openorb.test.iiop.value.ValuetypeTest;
import org.openorb.test.iiop.bidir.BiDirTest;
import org.openorb.test.iiop.state.StateTest;
import org.openorb.test.iiop.complex.ComplexTest;

/**
 * Tests marshaling and unmarshaling of various iiop types.
 * <p>
 *
 * @author Chris Wood
 */
public class IIOPTest extends TestCase
{
    public IIOPTest( String name )
    {
        super( name );
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite( PrimitiveTest.class );
        suite.addTestSuite( ValuetypeTest.class );
        suite.addTestSuite( BiDirTest.class );
        suite.addTestSuite( StateTest.class );
        suite.addTestSuite( ComplexTest.class );
        return suite;
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( suite() );
    }
}
