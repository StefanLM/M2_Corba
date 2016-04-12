/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.bench;

import org.openorb.test.iiop.value.AbstractA;
import org.openorb.test.iiop.value.AbstractA1Impl;
import org.openorb.test.iiop.value.AbstractA1POATie;
import org.openorb.test.iiop.value.AbstractA2Impl;
import org.openorb.test.iiop.value.AbstractA3Impl;
import org.openorb.test.iiop.value.LongBox;
import org.openorb.test.iiop.value.ValueA;
import org.openorb.test.iiop.value.ValueAImpl;
import org.openorb.test.iiop.value.ValueB;
import org.openorb.test.iiop.value.ValueBDefaultFactory;
import org.openorb.test.iiop.value.ValueBHelper;
import org.openorb.test.iiop.value.ValueBValueFactory;
import org.openorb.test.iiop.value.ValueC;
import org.openorb.test.iiop.value.ValueCImpl;
import org.openorb.test.iiop.value.ValueD;
import org.openorb.test.iiop.value.ValueDImpl;
import org.openorb.test.iiop.value.ValueE;
import org.openorb.test.iiop.value.ValueEImpl;
import org.openorb.test.iiop.value.ValueERemote;
import org.openorb.test.iiop.value.ValueERemotePOATie;
import org.openorb.test.iiop.value.ValueF;
import org.openorb.test.iiop.value.ValueFImpl;
import org.openorb.test.iiop.value.ValueG;
import org.openorb.test.iiop.value.ValueGImpl;

/**
 * @author Chris Wood
 */
public class BenchmarkImpl
    implements org.openorb.bench.BenchmarkOperations
{
    public BenchmarkImpl( org.omg.CORBA.ORB orb, org.omg.PortableServer.POA poa )
    {
        m_orb = orb;
        m_poa = poa;
    }

    private org.omg.CORBA.ORB m_orb;
    private org.omg.PortableServer.POA m_poa;

    private static final int F_L = 9234987;

    public void ping()
    {}

    public void raise_exception()
    {
        throw new org.omg.CORBA.UNKNOWN();
    }

    public org.omg.CORBA.Object echo_Object( org.omg.CORBA.Object arg )
    {
        return arg;
    }

    public org.omg.CORBA.TypeCode echo_TypeCode( org.omg.CORBA.TypeCode arg )
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

    public boolean echo_boolean( boolean arg )
    {
        return arg;
    }

    public boolean[] echo_boolean_s( boolean[] arg )
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

    public double echo_double( double arg )
    {
        return arg;
    }

    public double[] echo_double_s( double[] arg )
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

    public int echo_long( int arg )
    {
        return arg;
    }

    public int[] echo_long_s( int[] arg )
    {
        return arg;
    }

    public int[][] echo_long_s_s( int[][] arg )
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

    public byte echo_octet( byte arg )
    {
        return arg;
    }

    public byte[] echo_octet_s( byte[] arg )
    {
        return arg;
    }

    public byte[][] echo_octet_s_s( byte[][] arg )
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

    public java.lang.String echo_string( java.lang.String arg )
    {
        return arg;
    }

    public java.lang.String[] echo_string_s( java.lang.String[] arg )
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

    public long echo_ulonglong( long arg )
    {
        return arg;
    }

    public long[] echo_ulonglong_s( long[] arg )
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

    public char echo_wchar( char arg )
    {
        return arg;
    }

    public char[] echo_wchar_s( char[] arg )
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

    public LongBox echo_longBox( LongBox val )
    {
        return val;
    }

    public int[] echo_longBoxSeq( int[] val )
    {
        return val;
    }

    public java.io.Serializable get_value( int type )
    {
        switch ( type )
        {

        case 0:
            return get_value_a( 0 );

        case 1:
            return get_value_b();

        case 2:
            return get_value_c();

        case 3:
            return get_value_d();

        case 4:
            return get_value_e();

        case 5:
            return get_value_f();

        case 6:
            return get_value_g();

        default:
            return null;
        }
    }

    public ValueA get_value_a( int type )
    {
        switch ( type )
        {

        case 0:
            ValueA ret = new ValueAImpl();
            ret.l = 1;
            ret.str = "ValueA";
            return ret;

        case 2:
            return get_value_c();

        case 3:
            return get_value_d();

        default:
            return null;
        }
    }

    public ValueB get_value_b()
    {
        org.omg.CORBA_2_3.ORB orb = ( org.omg.CORBA_2_3.ORB ) m_orb;
        orb.register_value_factory( ValueBHelper.id(), new ValueBDefaultFactory() );
        ValueBValueFactory factory = ( ValueBValueFactory )
              orb.lookup_value_factory( ValueBHelper.id() );

        ValueB ret = factory.init( 1 );
        ret.str = "ValueB";
        return ret;
    }

    public ValueC get_value_c()
    {
        ValueC ret = new ValueCImpl();
        ret.l = 1;
        ret.n = 0;
        ret.str = "ValueC";
        return ret;
    }

    public ValueD get_value_d()
    {
        ValueD ret = new ValueDImpl();
        ret.l = 1;
        ret.n = 0;
        ret.str = "ValueD";
        return ret;
    }

    public ValueERemote get_value_e_remote()
    {
        return ( new ValueERemotePOATie( get_value_e(), m_poa ) )._this( m_orb );
    }

    public ValueE get_value_e()
    {
        ValueE ret = new ValueEImpl( "remote" );
        ret.prefix = "ValueE";
        return ret;
    }

    public ValueF get_value_f()
    {
        ValueF ret = new ValueFImpl();
        ret.l = F_L;
        return ret;
    }

    public ValueG get_value_g()
    {
        ValueG root = new ValueGImpl();
        root.idx = 0;
        root.name = "root";
        root.left = new ValueGImpl();
        root.left.idx = 1;
        root.left.name = "left";
        root.left.parent = root;
        root.right = new ValueGImpl();
        root.right.idx = 1;
        root.right.name = "right";
        root.right.parent = root;
        return root;
    }

    public Object get_abstract( int type )
    {
        if ( type < 7 )
            return get_value( type );

        return get_abstract_a( type - 7 );
    }

    public AbstractA get_abstract_a( int type )
    {
        AbstractA ret;

        switch ( type )
        {

        case 0:
            return ( new AbstractA1Impl( m_poa ) )._this( m_orb );

        case 1:
            return new AbstractA2Impl();

        case 2:
            return new AbstractA3Impl( "remote" );

        case 3:
            return ( new AbstractA1POATie( new AbstractA3Impl( "remote" ), m_poa ) )._this( m_orb );

        default:
            return null;
        }
    }
}
