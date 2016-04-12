/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.iiop.complex;

import junit.framework.TestSuite;

import org.omg.PortableServer.POA;

import org.openorb.test.ORBTestCase;

/**
 * Tests marshaling and unmarshaling of various iiop types.
 *
 * @author Chris Wood
 */
public class ComplexTest
    extends ORBTestCase
{
    public ComplexTest( String name )
    {
        super( name );
    }

    protected void setUp()
    {
        super.setUp();

        try
        {
            m_orb = getORB();

            POA rootPOA = ( POA ) m_orb.resolve_initial_references( "RootPOA" );

            EchoComplex svr_ref = ( new EchoComplexImpl( rootPOA ) )._this( m_orb );

            rootPOA.the_POAManager().activate();

            m_cltRef = EchoComplexHelper.narrow( forceMarshal( svr_ref ) );

            m_any = m_orb.create_any();
        }
        catch ( org.omg.CORBA.UserException ex )
        {
            fail( "exception during setup:" + ex.toString() );
        }
    }

    private EchoComplex m_cltRef;
    private org.omg.CORBA.ORB m_orb;
    private org.omg.CORBA.Any m_any;
    private org.omg.CORBA.Any m_rpl;

    /**
     * Simple struct echo.
     */
    public void testSimpleStruct()
    {
        SimpleStruct ss = new SimpleStruct( 1 );

        SimpleStruct ret = m_cltRef.echo_simple_s( ss );

        assertEquals( "Body of returned struct not equal", ss.n, ret.n );

        SimpleStructHelper.insert( m_any, ss );

        m_rpl = m_cltRef.echo_any( m_any );

        assertTrue( "Returned any is not identical to original", m_rpl.equal( m_any ) );
    }

    /**
     * Complex struct echo.
     */
    public void testComplexStruct()
    {
        ComplexStruct s = new ComplexStruct( new SimpleStruct( 1 ), "a string" );

        ComplexStruct ret = m_cltRef.echo_complex_s( s );

        assertEquals( "Body of returned struct not equal", s.s, ret.s );

        ComplexStructHelper.insert( m_any, s );

        m_rpl = m_cltRef.echo_any( m_any );

        assertTrue( "Returned any is not identical to original", m_rpl.equal( m_any ) );
    }

    /**
     * Recursive struct echo.
     */
    public void testRecursiveStruct()
    {
        RecursiveStruct [] e = new RecursiveStruct[ 0 ];

        RecursiveStruct [] left = new RecursiveStruct[] { new RecursiveStruct( "left", e, e ) };
        RecursiveStruct [] right = new RecursiveStruct[] { new RecursiveStruct( "right", e, e ) };

        RecursiveStruct s = new RecursiveStruct( "top", left, right );

        RecursiveStruct ret = m_cltRef.echo_recursive_s( s );

        RecursiveStructHelper.insert( m_any, s );

        m_rpl = m_cltRef.echo_any( m_any );

        assertTrue( "Returned any is not identical to original", m_rpl.equal( m_any ) );
    }
    /**
     * Fixed type echo.
     */
    public void testFixed()
    {
        java.math.BigDecimal f = new java.math.BigDecimal( "132.22" );

        java.math.BigDecimal ret = m_cltRef.echo_fixed( f );

        assertEquals( "Body of returned fixed not equal", f, ret );

        Fixed52Helper.insert( m_any, f );

        m_rpl = m_cltRef.echo_any( m_any );

        assertTrue( "Returned any is not identical to original", m_rpl.equal( m_any ) );
    }


    static class EchoComplexImpl extends EchoComplexPOA
    {
        EchoComplexImpl( POA poa )
        {
            m_poa = poa;
        }

        private POA m_poa;

        public POA _default_POA()
        {
            return m_poa;
        }

        public org.omg.CORBA.Any echo_any( org.omg.CORBA.Any a )
        {
            return a;
        }

        public org.openorb.test.iiop.complex.SimpleStruct echo_simple_s(
              org.openorb.test.iiop.complex.SimpleStruct ss )
        {
            return ss;
        }

        public org.openorb.test.iiop.complex.ComplexStruct echo_complex_s(
              org.openorb.test.iiop.complex.ComplexStruct ss )
        {
            return ss;
        }

        public org.openorb.test.iiop.complex.RecursiveStruct echo_recursive_s(
              org.openorb.test.iiop.complex.RecursiveStruct rs )
        {
            return rs;
        }

        public org.openorb.test.iiop.complex.SimpleEnum echo_simple_e(
              org.openorb.test.iiop.complex.SimpleEnum se )
        {
            return se;
        }

        public org.openorb.test.iiop.complex.EnumUnion echo_enum_u(
              org.openorb.test.iiop.complex.EnumUnion eu )
        {
            return eu;
        }

        public org.openorb.test.iiop.complex.LongUnion echo_long_u(
              org.openorb.test.iiop.complex.LongUnion lu )
        {
            return lu;
        }


        public java.math.BigDecimal echo_fixed( java.math.BigDecimal fi )
        {
            return fi;
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( ComplexTest.class ) );
    }
}
