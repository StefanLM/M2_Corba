/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.naming;

import junit.framework.TestSuite;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.BindingHolder;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import org.omg.PortableServer.POA;

import org.openorb.test.ORBTestCase;

import org.openorb.util.NamingUtils;
import org.openorb.util.MapNamingContext;

/**
 * Tests naming service.
 *
 * @author Chris Wood
 */
public class NamingTest
    extends ORBTestCase
{
    public NamingTest( String name )
    {
        super( name );
    }

    private static boolean compareNames( NameComponent [] nc1, NameComponent [] nc2 )
    {
        if ( nc1.length != nc2.length )
            return false;

        for ( int i = 0; i < nc1.length; ++i )
            if ( !nc1[ i ].id.equals( nc2[ i ].id )
                  || !nc1[ i ].kind.equals( nc2[ i ].kind ) )
                return false;

        return true;
    }

    /**
     * This test apply several Name creations and transformations
     *
     * @exception org.omg.CosNaming.NamingContextPackage.InvalidName if any of the test cases fails
     */
    public void testNameManipulation() throws InvalidName
    {
        NameComponent [] n;
        String s, debug;

        debug = "Empty name";
        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( "", "" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s, "." );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        debug = ( "id only" );
        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( "hello", "" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s, "hello" );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        debug = ( "kind only" );
        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( "", "world" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s, ".world" );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        debug = ( "both" );
        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( "hello", "world" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s, "hello.world" );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        debug = ( "Multiple elements..." );
        n = new NameComponent[ 4 ];
        n[ 0 ] = new NameComponent( "", "" );
        n[ 1 ] = new NameComponent( "hello", "" );
        n[ 2 ] = new NameComponent( "", "world" );
        n[ 3 ] = new NameComponent( "hello", "world" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s, "./hello/.world/hello.world" );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        debug = ( "names needing escaping" );
        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( ".", "" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s, "\\." );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( "/", "" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s, "\\/" );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( "\\", "" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s, "\\\\" );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        n = new NameComponent[ 2 ];
        n[ 0 ] = new NameComponent( "\\\\\\.../\\.", "/.//.\\" );
        n[ 1 ] = new NameComponent( "m.o.r.e.", "//slashy\\\\" );
        s = NamingUtils.to_string( n );
        assertEquals( debug, s,
              "\\\\\\\\\\\\\\.\\.\\.\\/\\\\\\..\\/\\.\\/\\/\\.\\\\/m"
              + "\\.o\\.r\\.e\\..\\/\\/slashy\\\\\\\\" );
        assertTrue( debug, compareNames( n, NamingUtils.to_name( s ) ) );

        s = "";
        try
        {
            NamingUtils.to_name( s );
            fail( "Exception should be thrown" );
        }
        catch ( InvalidName ex )
        {
            // Exception should be thrown
        }

        s = "..";
        try
        {
            NamingUtils.to_name( s );
            fail( "Exception should be thrown" );
        }
        catch ( InvalidName ex )
        {
            // Exception should be thrown
        }

        s = "hello.world/";
        try
        {
            NamingUtils.to_name( s );
            fail( "Exception should be thrown" );
        }
        catch ( InvalidName ex )
        {
            // Exception should be thrown
        }

        s = "I.want.blood";
        try
        {
            NamingUtils.to_name( s );
            fail( "Exception should be thrown" );
        }
        catch ( InvalidName ex )
        {
            // Exception should be thrown
        }
    }

    /**
      * Test name service.
      *
      * @exception org.omg.CORBA.UserException if any of the test case fails
      */
    public void testNameService() throws org.omg.CORBA.UserException
    {
        // find the root poa
        org.omg.CORBA.ORB orb = getORB();

        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );
        rootPOA.the_POAManager().activate();

        MapNamingContext map = new MapNamingContext( orb, null );

        NamingContextExt ctxt = map.getRootCtxt();

        map.put( "empty", null );
        map.put( "submap.context/recurse", ctxt );
        map.put( "recurse", ctxt );

        NameComponent [] n;
        org.omg.CORBA.Object res = null;
        org.omg.CORBA.Object sub = null;
        BindingListHolder bl;
        BindingIteratorHolder bi;
        BindingHolder bh;

        bl = new BindingListHolder();
        bi = new BindingIteratorHolder();
        ctxt.list( 1, bl, bi );

        assertTrue( bl.value.length == 1 );

        if ( bi.value != null )
        {
            bh = new BindingHolder();

            while ( bi.value.next_one( bh ) )

                ; // do nothing
            bi.value.destroy();
        }

        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( "empty", "" );
        res = ctxt.resolve( n );
        assertNull( res );

        n = new NameComponent[ 1 ];
        n[ 0 ] = new NameComponent( "recurse", "" );
        res = ctxt.resolve( n );
        assertTrue( res._is_equivalent( ctxt ) );

        sub = null;
        n[ 0 ] = new NameComponent( "submap", "context" );
        sub = ctxt.resolve( n );
        assertTrue( !sub._is_equivalent( ctxt ) );

        res = null;
        n[ 0 ] = new NameComponent( "recurse", "" );
        res = NamingContextHelper.narrow( sub ).resolve( n );
        assertTrue( res._is_equivalent( ctxt ) );

        n = new NameComponent[ 2 ];
        n[ 0 ] = new NameComponent( "submap", "context" );
        n[ 1 ] = new NameComponent( "recurse", "" );
        res = ctxt.resolve( n );
        assertTrue( res._is_equivalent( ctxt ) );

        sub = null;
        n[ 0 ] = new NameComponent( "recurse", "" );
        n[ 1 ] = new NameComponent( "submap", "context" );
        sub = ctxt.resolve( n );
        assertTrue( !sub._is_equivalent( ctxt ) );

        sub = null;
        n[ 0 ] = new NameComponent( "recurse", "" );
        n[ 1 ] = new NameComponent( "nonexist", "" );

        try
        {
            sub = ctxt.resolve( n );
            fail( "Able to resolve nonexistent context" );
        }
        catch ( NotFound ex )
        {
            // expected
        }

    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( NamingTest.class ) );
    }
}
