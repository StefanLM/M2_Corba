/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.iiop.primitive;

import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.Properties;

import org.omg.PortableServer.POA;

import org.openorb.test.ORBTestCase;

/**
 * Tests marshaling and unmarshaling of various iiop types.
 *
 * @author Chris Wood
 */
public class PrimitiveTest extends ORBTestCase
{
    public PrimitiveTest( String name )
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

            m_svrRef = ( new EchoImpl( rootPOA ) )._this( m_orb );

            rootPOA.the_POAManager().activate();

            m_cltRef = EchoHelper.narrow( forceMarshal( m_svrRef ) );
            
            m_any = m_orb.create_any();
        }
        catch ( org.omg.CORBA.UserException ex )
        {
            fail( "exception during setup:" + ex.toString() );
        }
    }

    private Echo m_svrRef;
    private Echo m_cltRef;
    private org.omg.CORBA.ORB m_orb;
    private org.omg.CORBA.Any m_any;
    private org.omg.CORBA.Any m_rpl;

    /**
     * Test basic object operations.
     */
    public void testBasicOperations()
    {
        assertTrue( "Object is local", !( ( org.omg.CORBA.portable.ObjectImpl )
              m_cltRef )._is_local() );

        assertTrue( "Object does not exist", !m_cltRef._non_existent() );

        assertTrue( "Object is not a \"IDL:omg.org/CORBA/Object:1.0\"",
                    m_cltRef._is_a( "IDL:omg.org/CORBA/Object:1.0" ) );

        String nomin = EchoHelper.id();
        nomin = nomin.substring( 0, nomin.lastIndexOf( '.' ) );
        String overs = nomin + ".0";
        String nvers = nomin + ".2";

        // client side check
        assertTrue( "Object is not a \"" + EchoHelper.id() + "\"",
                    m_cltRef._is_a( EchoHelper.id() ) );

        assertTrue( "Object is not a \"" + overs + "\"",
                    m_cltRef._is_a( overs ) );

        assertTrue( "Object is a \"" + nvers + "\"",
                    !m_cltRef._is_a( nvers ) );

        assertTrue( "Object is a \"IDL:com.beezwax/Bumblebee:1.0\"",
                    !m_cltRef._is_a( "IDL:com.beezwax/Bumblebee:1.0" ) );

        org.omg.CORBA.DomainManager [] managers = m_cltRef._get_domain_managers();

        try
        {
            m_cltRef._get_interface_def();
        }
        catch ( org.omg.CORBA.INTF_REPOS ex )
        {
           // no Interface Repository is running in this case
        }

        m_cltRef.toString();
    }

    /**
     * Test local invocation.
     */
    public void testLocalInvocation()
    {
        assertTrue( "Object is not local", ( ( org.omg.CORBA.portable.ObjectImpl )
              m_svrRef )._is_local() );

        assertTrue( "Object does not exist", !m_svrRef._non_existent() );

        assertTrue( "Object is not a \"IDL:omg.org/CORBA/Object:1.0\"",
                    m_svrRef._is_a( "IDL:omg.org/CORBA/Object:1.0" ) );

        assertTrue( "Object is not a \"" + EchoHelper.id() + "\"",
                    m_svrRef._is_a( EchoHelper.id() ) );

        assertTrue( "Object is a \"IDL:com.beezwax/Bumblebee:1.0\"",
                    !m_svrRef._is_a( "IDL:com.beezwax/Bumblebee:1.0" ) );

        org.omg.CORBA.DomainManager [] managers = m_svrRef._get_domain_managers();

        try
        {
            m_svrRef._get_interface_def();
        }
        catch ( org.omg.CORBA.INTF_REPOS ex )
        {
           // no Interface Repository is running in this case
        }

        m_svrRef.ping();
    }

    /**
     * Test interorb invocation.
     */
    public void testInterORBInvocation()
    throws Exception
    {
        Properties props = new Properties();
        props.setProperty( "openorb.useStaticThreadGroup", "true" );
        org.omg.CORBA.ORB nlORB = org.omg.CORBA.ORB.init( new String[ 0 ], props );

        org.omg.CORBA.Object nlObj = nlORB.string_to_object( m_orb.object_to_string( m_svrRef ) );

        assertTrue( "Object is local",
              !( ( org.omg.CORBA.portable.ObjectImpl ) nlObj )._is_local() );

        assertTrue( "Object does not exist", !nlObj._non_existent() );

        EchoHelper.narrow( m_svrRef ).ping();

        nlORB.shutdown( true );
    }

    /**
     * Test object ref transmission.
     */
    public void testObject()
    {
        assertTrue( "Object is not equivalent to self",
                    m_cltRef.echo_Object( m_cltRef )._is_equivalent( m_cltRef ) );

        m_any.insert_Object( m_cltRef );
        m_rpl = m_cltRef.echo_any( m_any );

        assertTrue( "failed object reference any contained",
                    m_any.equal( m_rpl ) );

        assertTrue( "failed object reference any",
                    m_rpl.extract_Object()._is_equivalent( m_cltRef ) );
    }
    
    /**
     * Test valuetype transmission.
     * TODO: #501332/#819
     */
    public void testValuetypes()
    {
        java.lang.String str = "String as Serializable";

        m_any.insert_Value((java.io.Serializable) str, org.omg.CORBA.StringValueHelper.type());

        m_rpl = m_cltRef.echo_any( m_any );

        assertTrue( "failed valuetype any contained",
                    m_any.equal( m_rpl ) );

        assertTrue( "failed valuetype any",
                    ((String) m_rpl.extract_Value()).equals( str ) );
    }

    /**
     * Test ping/void
     */
    public void testVoid()
    {
        m_cltRef.ping();

        m_any.type( m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_void ) );

        if ( m_cltRef.echo_any( m_any ).type().kind() != org.omg.CORBA.TCKind.tk_void )
            fail( "failed any void" );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed typecode void" );
    }

    /**
     * Throw exception
     */
    public void testThrowException()
    {
        // throw exception
        try
        {
            m_cltRef.raise_exception();
            fail( "exception not raised" );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            // expected.
        }

    }

    /**
     * Test boolean echo.
     */
    public void testBoolean()
    {
        final boolean [] vals = { true, false };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_boolean( vals[ i ] ) != vals[ i ] )
                fail( "failed boolean " + ( vals[ i ] ? "true" : "false" ) );

            m_any.insert_boolean( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for boolean any " + ( vals[ i ] ? "true" : "false" ) );

            if ( m_rpl.extract_boolean() != vals[ i ] )
                fail( "failed boolean any " + ( vals[ i ] ? "true" : "false" ) );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed boolean typecode" );


        if ( !Arrays.equals( vals, m_cltRef.echo_boolean_s( vals ) ) )
            fail( "Sequence test failed for boolean" );

        org.omg.CORBA.BooleanSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed boolean seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for boolean any sequence" );
    }

    /**
     * Test octet echo.
     */
    public void testOctet()
    {
        final byte [] vals = { ( byte )    0, ( byte )    1, ( byte )    7,
                               ( byte ) 0x1F, ( byte ) 0x80, ( byte ) 0xFF };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_octet( vals[ i ] ) != vals[ i ] )
                fail( "failed octet " + vals[ i ] );

            m_any.insert_octet( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for octet any " + vals[ i ] );

            if ( m_rpl.extract_octet() != vals[ i ] )
                fail( "failed octet any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed octet typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_octet_s( vals ) ) )
            fail( "Sequence test failed for octet" );

        org.omg.CORBA.OctetSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed octet seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for boolean any sequence" );
    }

    /**
     * Test short echo.
     */
    public void testShort()
    {
        final short [] vals = { ( short ) 0, ( short ) 1,
                                ( short ) 0xFF, ( short ) 0x2345,
                                ( short ) 0x6767, ( short ) 0x8077, ( short ) 0xFFFF };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_short( vals[ i ] ) != vals[ i ] )
                fail( "failed short " + vals[ i ] );

            m_any.insert_short( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for short any " + vals[ i ] );

            if ( m_rpl.extract_short() != vals[ i ] )
                fail( "failed short any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed short typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_short_s( vals ) ) )
            fail( "Sequence test failed for short" );

        org.omg.CORBA.ShortSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed short seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for short any sequence" );
    }

    /**
     * Test unsigned short echo.
     */
    public void testUShort()
    {
        final short [] vals = { ( short ) 0, ( short ) 1,
              ( short ) 0xFF, ( short ) 0x2345, ( short ) 0x6767,
              ( short ) 0x8077, ( short ) 0xFFFF };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_ushort( vals[ i ] ) != vals[ i ] )
                fail( "failed ushort " + vals[ i ] );

            m_any.insert_ushort( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for ushort any " + vals[ i ] );

            if ( m_rpl.extract_ushort() != vals[ i ] )
                fail( "failed ushort any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed ushort typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_ushort_s( vals ) ) )
            fail( "Sequence test failed for ushort" );

        org.omg.CORBA.UShortSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed ushort seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for ushort any sequence" );
    }

    /**
     * Test long echo.
     */
    public void testLong()
    {
        int [] vals = { 0, 1, 0xFF, 0xFFFF, 0xFFF679, 0x80079874, 0xFFFFFFFF, 0x7FFFFFFF };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_long( vals[ i ] ) != vals[ i ] )
                fail( "failed long " + vals[ i ] );

            m_any.insert_long( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for long any " + vals[ i ] );

            if ( m_rpl.extract_long() != vals[ i ] )
                fail( "failed long any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed long typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_long_s( vals ) ) )
            fail( "Sequence test failed for long" );

        org.omg.CORBA.LongSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed long seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for long any sequence" );
    }

    /**
     * Test unsigned long echo.
     */
    public void testULong()
    {
        int [] vals = { 0, 1, 0xFF, 0xFFFF, 0xFFF679, 0x80079874, 0xFFFFFFFF, 0x7FFFFFFF };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_ulong( vals[ i ] ) != vals[ i ] )
                fail( "failed ulong " + vals[ i ] );

            m_any.insert_ulong( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for ulong any " + vals[ i ] );

            if ( m_rpl.extract_ulong() != vals[ i ] )
                fail( "failed ulong any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed ulong typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_ulong_s( vals ) ) )
            fail( "Sequence test failed for ulong" );

        org.omg.CORBA.ULongSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed ulong seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for ulong any sequence" );
    }

    /**
     * Test long long echo.
     */
    public void testLongLong()
    {
        long [] vals = { 0L, 1L, 0xFFL, 0xFFFFL, 0xFFF679L, 0x80079874L,
                         0xFFFFFFFFL, 0x7FFFFFFFL, 0xFF320984723L, 0x709808576FFFACFBL,
                         0xFFFFFFFFFFFFFFFFL, 0x7FFFFFFFFFFFFFFFL };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_longlong( vals[ i ] ) != vals[ i ] )
                fail( "failed long long " + vals[ i ] );

            m_any.insert_longlong( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for long long any " + vals[ i ] );

            if ( m_rpl.extract_longlong() != vals[ i ] )
                fail( "failed long long any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed long long typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_longlong_s( vals ) ) )
            fail( "Sequence test failed for long long" );

        org.omg.CORBA.LongLongSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed long long seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for long long any sequence" );
    }

    /**
     * Test unsigned long long echo.
     */
    public void testULongLong()
    {
        long [] vals = { 0L, 1L, 0xFFL, 0xFFFFL, 0xFFF679L, 0x80079874L,
                         0xFFFFFFFFL, 0x7FFFFFFFL, 0xFF320984723L, 0x709808576FFFACFBL,
                         0xFFFFFFFFFFFFFFFFL, 0x7FFFFFFFFFFFFFFFL };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_ulonglong( vals[ i ] ) != vals[ i ] )
                fail( "failed ulonglong " + vals[ i ] );

            m_any.insert_ulonglong( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for ulonglong any " + vals[ i ] );

            if ( m_rpl.extract_ulonglong() != vals[ i ] )
                fail( "failed ulonglong any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed ulonglong typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_ulonglong_s( vals ) ) )
            fail( "Sequence test failed for ulonglong" );

        org.omg.CORBA.ULongLongSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed ulonglong seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for ulonglong any sequence" );
    }

    /**
     * Test float echo.
     */
    public void testFloat()
    {
        final float [] vals = { ( float ) 0.0, ( float ) -0.0, Float.MAX_VALUE,
              Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_float( vals[ i ] ) != vals[ i ] )
                fail( "failed float " + vals[ i ] );

            m_any.insert_float( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for float any " + vals[ i ] );

            if ( m_rpl.extract_float() != vals[ i ] )
                fail( "failed float any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed float typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_float_s( vals ) ) )
            fail( "Sequence test failed for float" );

        org.omg.CORBA.FloatSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed float seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for float any sequence" );

        // extra tests for NaNs
        if ( !Float.isNaN( m_cltRef.echo_float( Float.NaN ) ) )
            fail( "failed float " + Float.NaN );

        m_any.insert_float( Float.NaN );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for float any " + Float.NaN );

        if ( !Float.isNaN( m_rpl.extract_float() ) )
            fail( "failed float any " + Float.NaN );
    }

    /**
     * Test double echo.
     */
    public void testDoubleEcho()
    {
        final double [] vals = { 0.0, -0.0, Double.MAX_VALUE, Double.MIN_VALUE,
              Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_double( vals[ i ] ) != vals[ i ] )
                fail( "failed double " + vals[ i ] );

            m_any.insert_double( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for double any " + vals[ i ] );

            if ( m_rpl.extract_double() != vals[ i ] )
                fail( "failed double any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed double typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_double_s( vals ) ) )
            fail( "Sequence test failed for double" );

        org.omg.CORBA.DoubleSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed double seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for double any sequence" );

        // extra tests for NaNs
        if ( !Double.isNaN( m_cltRef.echo_double( Double.NaN ) ) )
            fail( "failed double " + Double.NaN );

        m_any.insert_double( Double.NaN );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for double any " + Double.NaN );

        if ( !Double.isNaN( m_rpl.extract_double() ) )
            fail( "failed double any " + Double.NaN );
    }

    /**
     * Test char echo.
     */
    public void testChar()
    {
        final char [] testvals = 
        { 
            '\u0001', '\u0020', '\u00FF',
            '\u00E4', '\u00F6', '\u00FC', '\u00C4', '\u00D6', '\u00DC', '\u00DF'
                /* Small  : a umlaut, o umlaut, u umlaut, german sz ligature, 
                   Capital: a umlaut, o umlaut, u umlaut */ 
        };
        char [] vals = testvals;

        for ( int i = 0; i < vals.length; ++i )
        {
            try
            {
                char _crpl = m_cltRef.echo_char( vals[ i ] );
                if ( _crpl != vals[ i ] )
                    fail( "Failed char \'" + vals[ i ] + "\' at pos " + i + " send="
                          + (int) vals[ i ] + " rcvd=" + (int) _crpl );

                m_any.insert_char( vals[ i ] );

                m_rpl = m_cltRef.echo_any( m_any );

                if ( !m_any.equal( m_rpl ) )
                    fail( "Equality test failed for char any \'" + vals[ i ] + "\'" );

                if ( m_rpl.extract_char() != vals[ i ] )
                    fail( "Failed char any \'" + vals[ i ] + "\'" );
            }
            catch ( org.omg.CORBA.DATA_CONVERSION ex )
            {
                // this can occur if the char is a representation of a multibyte char
                char [] old = vals;
                vals = new char[ vals.length - 1 ];
                System.arraycopy( old, 0, vals, 0, i );

                if ( i + 1 < vals.length )
                    System.arraycopy( old, i + 1, vals, i, vals.length - 1 - i );

                --i;
            }
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "Failed char typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_char_s( vals ) ) )
            fail( "Sequence test failed for char" );

        org.omg.CORBA.CharSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "Failed char seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for char any sequence" );
    }

    /**
     * Test wchar echo.
     */
    public void testWChar()
    {
        final char [] vals = 
        { 
            '\u0001', '\u0020', '\u008D', '\u00FF', '\u0626', '\u7554', '\uF7F5', '\uFFFF',
            '\u00E4', '\u00F6', '\u00FC', '\u00C4', '\u00D6', '\u00DC', '\u00DF'
                /* Small  : a umlaut, o umlaut, u umlaut, german sz ligature, 
                   Capital: a umlaut, o umlaut, u umlaut */ 
        };
 
        for ( int i = 0; i < vals.length; ++i )
        {
            if ( m_cltRef.echo_wchar( vals[ i ] ) != vals[ i ] )
                fail( "failed wchar " + vals[ i ] );

            m_any.insert_wchar( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for wchar any " + vals[ i ] );

            if ( m_rpl.extract_wchar() != vals[ i ] )
                fail( "failed wchar any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed wchar typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_wchar_s( vals ) ) )
            fail( "Sequence test failed for wchar" );

        org.omg.CORBA.WCharSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed wchar seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for wchar any sequence" );
    }

    /**
     * Test string echo.
     */
    public void testStringEcho()
    {
        final String [] vals = { 
            "",
            "Mr Jock, T.V. quiz PhD, bags few lynx.", 
            "\u0001\u0010\u007F\u006D\u00FF", 
            "\u00E4\u00F6\u00FC\u00C4\u00D6\u00DC\u00DF"
                  /* Small  : a umlaut, o umlaut, u umlaut, german sz ligature, 
                     Capital: a umlaut, o umlaut, u umlaut */ 
        }; 

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( !m_cltRef.echo_string( vals[ i ] ).equals( vals[ i ] ) )
                fail( "Failed string at pos " + i + " \'" + vals[ i ] + "\'" );

            m_any.insert_string( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for string any " + vals[ i ] );

            if ( !m_rpl.extract_string().equals( vals[ i ] ) )
                fail( "Failed string any \'" + vals[ i ] + "\'" );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed string typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_string_s( vals ) ) )
            fail( "Sequence test failed for string" );

        org.omg.CORBA.StringSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "Failed string seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for string any sequence" );

        // echo limited length string typecode
        org.omg.CORBA.TypeCode tc = m_orb.create_string_tc( 10 );

        if ( !m_cltRef.echo_TypeCode( tc ).equal( tc ) )
            fail( "Failed string<10> typecode" );
    }

    /**
     * Test wstring echo.
     */
    public void testWStringEcho()
    {
        final String [] vals = 
        { 
            "", 
            "Mr Jock, T.V. quiz PhD, bags few lynx.", 
            "\u0001\u0010\u007F\u008D\u00FF\u05DD\u7FDE\u8D40\uFFFF",
            "\u00E4\u00F6\u00FC\u00C4\u00D6\u00DC\u00DF"
                  /* Small  : a umlaut, o umlaut, u umlaut, german sz ligature, 
                     Capital: a umlaut, o umlaut, u umlaut */ 
        };

        for ( int i = 0; i < vals.length; ++i )
        {
            if ( !m_cltRef.echo_wstring( vals[ i ] ).equals( vals[ i ] ) )
                fail( "failed wstring " + vals[ i ] );

            m_any.insert_wstring( vals[ i ] );

            m_rpl = m_cltRef.echo_any( m_any );

            if ( !m_any.equal( m_rpl ) )
                fail( "Equality test failed for wstring any " + vals[ i ] );

            if ( !m_rpl.extract_wstring().equals( vals[ i ] ) )
                fail( "failed wstring any " + vals[ i ] );
        }

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed wstring typecode" );

        if ( !Arrays.equals( vals, m_cltRef.echo_wstring_s( vals ) ) )
            fail( "Sequence test failed for wstring" );

        org.omg.CORBA.WStringSeqHelper.insert( m_any, vals );

        if ( !m_cltRef.echo_TypeCode( m_any.type() ).equal( m_any.type() ) )
            fail( "failed wstring seq typecode" );

        m_rpl = m_cltRef.echo_any( m_any );

        if ( !m_any.equal( m_rpl ) )
            fail( "Equality test failed for wstring any sequence" );

        // echo limited length wstring typecode
        org.omg.CORBA.TypeCode tc = m_orb.create_wstring_tc( 10 );

        if ( !m_cltRef.echo_TypeCode( tc ).equal( tc ) )
            fail( "failed wstring<10> typecode" );
    }

    /**
     * Test large buffer echo.
     */
    public void testLargeBufferEcho()
    {
        byte [] largebuf = new byte[ 1024 * 1024 ];

        ( new java.util.Random() ).nextBytes( largebuf );

        byte [] resp = m_cltRef.echo_octet_s( largebuf );

        if ( !Arrays.equals( largebuf, resp ) )
            fail( "failed echo large buffer" );
    }

    /**
     * Test large array of strings echo.
     */
    public void testLargeStringSeqEcho()
    {
        String[] strings = new String[ 20000 ];

        for ( int i = 0; i < strings.length; i++ )
            strings[ i ] = new String( "" + i );

        String [] ret = m_cltRef.echo_string_s( strings );

        if ( !Arrays.equals( strings, ret ) )
            fail( "failed echo large buffer" );
    }
    
    static class EchoImpl extends EchoPOA
    {
        EchoImpl( POA poa )
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
        }

        public void raise_exception()
        {
            throw new org.omg.CORBA.UNKNOWN();
        }

        public boolean echo_boolean( boolean arg )
        {
            return arg;
        }

        public boolean[] echo_boolean_s( boolean[] arg )
        {
            return arg;
        }

        public byte echo_octet( byte arg )
        {
            return arg;
        }

        public byte[] echo_octet_s( byte[] arg )
        {
            return arg;
        }

        public short echo_short( short arg )
        {
            return arg;
        }

        public short[] echo_short_s( short[] arg )
        {
            return arg;
        }

        public short echo_ushort( short arg )
        {
            return arg;
        }

        public short[] echo_ushort_s( short[] arg )
        {
            return arg;
        }

        public int echo_long( int arg )
        {
            return arg;
        }

        public int[] echo_long_s( int[] arg )
        {
            return arg;
        }

        public int echo_ulong( int arg )
        {
            return arg;
        }

        public int[] echo_ulong_s( int[] arg )
        {
            return arg;
        }

        public long echo_longlong( long arg )
        {
            return arg;
        }

        public long[] echo_longlong_s( long[] arg )
        {
            return arg;
        }

        public long echo_ulonglong( long arg )
        {
            return arg;
        }

        public long[] echo_ulonglong_s( long[] arg )
        {
            return arg;
        }

        public float echo_float( float arg )
        {
            return arg;
        }

        public float[] echo_float_s( float[] arg )
        {
            return arg;
        }

        public double echo_double( double arg )
        {
            return arg;
        }

        public double[] echo_double_s( double[] arg )
        {
            return arg;
        }

        public char echo_char( char arg )
        {
            return arg;
        }

        public char[] echo_char_s( char[] arg )
        {
            return arg;
        }

        public char echo_wchar( char arg )
        {
            return arg;
        }

        public char[] echo_wchar_s( char[] arg )
        {
            return arg;
        }

        public java.lang.String echo_string( java.lang.String arg )
        {
            return arg;
        }

        public java.lang.String[] echo_string_s( java.lang.String[] arg )
        {
            return arg;
        }

        public java.lang.String echo_wstring( java.lang.String arg )
        {
            return arg;
        }

        public java.lang.String[] echo_wstring_s( java.lang.String[] arg )
        {
            return arg;
        }

        public org.omg.CORBA.Object echo_Object( org.omg.CORBA.Object arg )
        {
            return arg;
        }

        public org.omg.CORBA.Any echo_any( org.omg.CORBA.Any arg )
        {
            return arg;
        }

        public org.omg.CORBA.Any[] echo_any_s( org.omg.CORBA.Any[] arg )
        {
            return arg;
        }

        public org.omg.CORBA.TypeCode echo_TypeCode( org.omg.CORBA.TypeCode arg )
        {
            return arg;
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( PrimitiveTest.class ) );
    }
}
