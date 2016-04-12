/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.bench;

import java.util.Arrays;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;

import org.omg.PortableServer.POA;

import org.openorb.test.iiop.value.AbstractA2Factory;
import org.openorb.test.iiop.value.AbstractA2Helper;
import org.openorb.test.iiop.value.AbstractA3Factory;
import org.openorb.test.iiop.value.AbstractA3Helper;
import org.openorb.test.iiop.value.ValueADefaultFactory;
import org.openorb.test.iiop.value.ValueAHelper;
import org.openorb.test.iiop.value.ValueBDefaultFactory;
import org.openorb.test.iiop.value.ValueBHelper;
import org.openorb.test.iiop.value.ValueCFactory;
import org.openorb.test.iiop.value.ValueCHelper;
import org.openorb.test.iiop.value.ValueDFactory;
import org.openorb.test.iiop.value.ValueDHelper;
import org.openorb.test.iiop.value.ValueEFactory;
import org.openorb.test.iiop.value.ValueFDefaultFactory;
import org.openorb.test.iiop.value.ValueFHelper;
import org.openorb.test.iiop.value.ValueEHelper;
import org.openorb.test.iiop.value.ValueGDefaultFactory;
import org.openorb.test.iiop.value.ValueGHelper;

/**
 * @author Chris Wood
 */
public abstract class Main
    extends java.lang.Object
{
    /**
     * @param args the command line arguments
     */
    public static void main ( String args[] )
    {
        boolean server = false;
        boolean client = false;
        boolean benchmark = false;
        boolean interop = false;
        boolean stress = false;

        String target_str = null;

        for ( int i = 0; i < args.length; ++i )
        {
            if ( args[ i ].equals( "-server" ) )
                server = true;
            else if ( args[ i ].equals( "-client" ) )
            {
                client = true;
                target_str = args[ ++i ];
            }
            else if ( args[ i ].equals( "-interop" ) )
                interop = true;
            else if ( args[ i ].equals( "-benchmark" ) )
                benchmark = true;
            else if ( args[ i ].equals( "-stress" ) )
                stress = true;
        }

        if ( !server && !client )
            server = client = true;

        if ( !interop && !benchmark && !stress )
            interop = benchmark = true;

        final ORB svr_orb;

        if ( server )
        {
            svr_orb = ORB.init( args, null );
            POA rootPOA;

            try
            {
                rootPOA = org.omg.PortableServer.POAHelper.narrow(
                      svr_orb.resolve_initial_references( "RootPOA" ) );
                rootPOA.the_POAManager().activate();
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
                return ;
            }

            Benchmark svr = ( new BenchmarkPOATie(
                  new BenchmarkImpl( svr_orb, rootPOA ), rootPOA ) )._this( svr_orb );
            target_str = svr_orb.object_to_string( svr );

            if ( client )
            {
                ( new Thread( new Runnable()
                              {
                                  public void run()
                                  {
                                      svr_orb.run();
                                  }
                              }

                            ) ).start();
            }
            else
            {
                System.out.println( target_str );
                svr_orb.run();
            }
        }
        else
        {
            svr_orb = null;
        }

        if ( client )
        {
            ORB clt_orb = ORB.init( args, null );

            Benchmark target = BenchmarkHelper.narrow( clt_orb.string_to_object( target_str ) );

            if ( interop )
                interop( target, clt_orb );

            if ( benchmark )
                benchmark( target, clt_orb );

            if ( stress )
                stress( target, clt_orb );

            if ( server )
                svr_orb.shutdown( true );
            else
                System.exit( 0 );
        }
    }

    private static void interop( Benchmark target, org.omg.CORBA.ORB orb )
    {
        org.omg.CORBA.Any any = orb.create_any();
        org.omg.CORBA.Any rpl;

        //if(target._non_existent())
        // System.out.println("Object does not exist");

        if ( !target._is_a( "IDL:omg.org/CORBA/Object:1.0" ) )
            System.out.println( "Object is not a \"IDL:omg.org/CORBA/Object:1.0\"" );

        if ( !target._is_a( BenchmarkHelper.id() ) )
            System.out.println( "Object is not a \"" + BenchmarkHelper.id() + "\"" );

        if ( target._is_a( "IDL:com.beezwax/Bumblebee:1.0" ) )
            System.out.println( "Object is a \"IDL:com.beezwax/Bumblebee:1.0\"" );

        try
        {
            org.omg.CORBA.DomainManager [] managers = target._get_domain_managers();
            System.out.println( "Retrieved " + managers.length + " domain managers" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            System.out.println( "Unable to retrieve domain managers" );
        }

        try
        {
            target._get_interface_def();
            System.out.println( "Retrieved interface definition successfully" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            System.out.println( "Unable to locate interface repository" );
        }

        // object ref

        if ( !target.echo_Object( target )._is_equivalent( target ) )
            System.out.println( "failed object reference" );

        any.insert_Object( target );

        rpl = target.echo_any( any );

        if ( !any.equal( rpl ) )
            System.out.println( "Equality test failed for object reference" );

        if ( !rpl.extract_Object()._is_equivalent( target ) )
            System.out.println( "failed object reference any" );

        // void
        target.ping();

        any.type( orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_void ) );

        if ( target.echo_any( any ).type().kind() != org.omg.CORBA.TCKind.tk_void )
            System.out.println( "failed any void" );

        if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
            System.out.println( "failed typecode void" );

        // throw exception
        try
        {
            target.raise_exception();
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            // expected.
        }

        // boolean


        {
            final boolean [] vals = { true, false };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_boolean( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed boolean " + ( vals[ i ] ? "true" : "false" ) );

                any.insert_boolean( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for boolean any " +
                          ( vals[ i ] ? "true" : "false" ) );

                if ( rpl.extract_boolean() != vals[ i ] )
                    System.out.println( "failed boolean any " + ( vals[ i ] ? "true" : "false" ) );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed boolean typecode" );


            if ( !Arrays.equals( vals, target.echo_boolean_s( vals ) ) )
                System.out.println( "Sequence test failed for boolean" );

            org.omg.CORBA.BooleanSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed boolean seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for boolean any sequence" );
        }

        // octet

        {
            final byte [] vals = { ( byte )    0, ( byte )    1, ( byte )    7,
                                   ( byte ) 0x1F, ( byte ) 0x80, ( byte ) 0xFF };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_octet( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed octet " + vals[ i ] );

                any.insert_octet( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for octet any " + vals[ i ] );

                if ( rpl.extract_octet() != vals[ i ] )
                    System.out.println( "failed octet any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed octet typecode" );

            if ( !Arrays.equals( vals, target.echo_octet_s( vals ) ) )
                System.out.println( "Sequence test failed for octet" );

            org.omg.CORBA.OctetSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed octet seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for octet any sequence" );

            //if(!Arrays.equals(vals, org.omg.CORBA.OctetSeqHelper.extract(rpl)))
            // System.out.println("Equality test failed for octet any sequence contents");
        }

        // short



        {
            final short [] vals = { ( short ) 0, ( short ) 1, ( short ) 0xFF,
                                    ( short ) 0x2345, ( short ) 0x6767,
                                    ( short ) 0x8077, ( short ) 0xFFFF };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_short( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed short " + vals[ i ] );

                any.insert_short( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for short any " + vals[ i ] );

                if ( rpl.extract_short() != vals[ i ] )
                    System.out.println( "failed short any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed short typecode" );

            if ( !Arrays.equals( vals, target.echo_short_s( vals ) ) )
                System.out.println( "Sequence test failed for short" );

            org.omg.CORBA.ShortSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed short seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for short any sequence" );
        }

        // unsigned short
        {
            final short [] vals = { ( short ) 0, ( short ) 1, ( short ) 0xFF,
                                    ( short ) 0x2345, ( short ) 0x6767,
                                    ( short ) 0x8077, ( short ) 0xFFFF };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_ushort( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed ushort " + vals[ i ] );

                any.insert_ushort( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for ushort any " + vals[ i ] );

                if ( rpl.extract_ushort() != vals[ i ] )
                    System.out.println( "failed ushort any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed ushort typecode" );

            if ( !Arrays.equals( vals, target.echo_ushort_s( vals ) ) )
                System.out.println( "Sequence test failed for ushort" );

            org.omg.CORBA.UShortSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed ushort seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for ushort any sequence" );
        }

        // long
        {
            int [] vals = { 0, 1, 0xFF, 0xFFFF, 0xFFF679, 0x80079874, 0xFFFFFFFF, 0x7FFFFFFF };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_long( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed long " + vals[ i ] );

                any.insert_long( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for long any " + vals[ i ] );

                if ( rpl.extract_long() != vals[ i ] )
                    System.out.println( "failed long any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed long typecode" );

            if ( !Arrays.equals( vals, target.echo_long_s( vals ) ) )
                System.out.println( "Sequence test failed for long" );

            org.omg.CORBA.LongSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed long seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for long any sequence" );
        }

        // unsigned long
        {
            int [] vals = { 0, 1, 0xFF, 0xFFFF, 0xFFF679, 0x80079874, 0xFFFFFFFF, 0x7FFFFFFF };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_ulong( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed ulong " + vals[ i ] );

                any.insert_ulong( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for ulong any " + vals[ i ] );

                if ( rpl.extract_ulong() != vals[ i ] )
                    System.out.println( "failed ulong any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed ulong typecode" );

            if ( !Arrays.equals( vals, target.echo_ulong_s( vals ) ) )
                System.out.println( "Sequence test failed for ulong" );

            org.omg.CORBA.ULongSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed ulong seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for ulong any sequence" );
        }

        // long long
        {
            long [] vals = { 0l, 1l, 0xFFl, 0xFFFFl, 0xFFF679l, 0x80079874l,
                             0xFFFFFFFFl, 0x7FFFFFFFl, 0xFF320984723l, 0x709808576FFFACFBl,
                             0xFFFFFFFFFFFFFFFFl, 0x7FFFFFFFFFFFFFFFl };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_longlong( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed long long " + vals[ i ] );

                any.insert_longlong( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for long long any " + vals[ i ] );

                if ( rpl.extract_longlong() != vals[ i ] )
                    System.out.println( "failed long long any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed long long typecode" );

            if ( !Arrays.equals( vals, target.echo_longlong_s( vals ) ) )
                System.out.println( "Sequence test failed for long long" );

            org.omg.CORBA.LongLongSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed long long seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for long long any sequence" );
        }

        // unsigned long long
        {
            long [] vals = { 0l, 1l, 0xFFl, 0xFFFFl, 0xFFF679l, 0x80079874l,
                             0xFFFFFFFFl, 0x7FFFFFFFl, 0xFF320984723l, 0x709808576FFFACFBl,
                             0xFFFFFFFFFFFFFFFFl, 0x7FFFFFFFFFFFFFFFl };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_ulonglong( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed ulonglong " + vals[ i ] );

                any.insert_ulonglong( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for ulonglong any " + vals[ i ] );

                if ( rpl.extract_ulonglong() != vals[ i ] )
                    System.out.println( "failed ulonglong any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed ulonglong typecode" );

            if ( !Arrays.equals( vals, target.echo_ulonglong_s( vals ) ) )
                System.out.println( "Sequence test failed for ulonglong" );

            org.omg.CORBA.ULongLongSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed ulonglong seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for ulonglong any sequence" );
        }

        // float
        {
            final float [] vals = { ( float ) 0.0, ( float ) -0.0, Float.MAX_VALUE,
                  Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_float( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed float " + vals[ i ] );

                any.insert_float( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for float any " + vals[ i ] );

                if ( rpl.extract_float() != vals[ i ] )
                    System.out.println( "failed float any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed float typecode" );

            if ( !Arrays.equals( vals, target.echo_float_s( vals ) ) )
                System.out.println( "Sequence test failed for float" );

            org.omg.CORBA.FloatSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed float seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for float any sequence" );

            // extra tests for NaNs
            if ( !Float.isNaN( target.echo_float( Float.NaN ) ) )
                System.out.println( "failed float " + Float.NaN );

            any.insert_float( Float.NaN );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for float any " + Float.NaN );

            if ( !Float.isNaN( rpl.extract_float() ) )
                System.out.println( "failed float any " + Float.NaN );
        }

        // double
        {
            final double [] vals = { 0.0, -0.0, Double.MAX_VALUE, Double.MIN_VALUE,
                  Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY };

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_double( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed double " + vals[ i ] );

                any.insert_double( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for double any " + vals[ i ] );

                if ( rpl.extract_double() != vals[ i ] )
                    System.out.println( "failed double any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed double typecode" );

            if ( !Arrays.equals( vals, target.echo_double_s( vals ) ) )
                System.out.println( "Sequence test failed for double" );

            org.omg.CORBA.DoubleSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed double seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for double any sequence" );

            // extra tests for NaNs
            if ( !Double.isNaN( target.echo_double( Double.NaN ) ) )
                System.out.println( "failed double " + Double.NaN );

            any.insert_double( Double.NaN );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for double any " + Double.NaN );

            if ( !Double.isNaN( rpl.extract_double() ) )
                System.out.println( "failed double any " + Double.NaN );
        }

        // char
        {
            final char [] vals = { '\u0001', '\u0020' /*, '\u008D', '\u00FF' */};

            for ( int i = 0; i < vals.length; ++i )
            {
                try
                {
                    if ( target.echo_char( vals[ i ] ) != vals[ i ] )
                        System.out.println( "failed char " + vals[ i ] );
                }
                catch ( org.omg.CORBA.SystemException ex )
                {
                    System.out.println( "exception for char " + vals[ i ] );
                    continue;
                }

                any.insert_char( vals[ i ] );
                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for char any " + vals[ i ] );

                if ( rpl.extract_char() != vals[ i ] )
                    System.out.println( "failed char any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed char typecode" );

            if ( !Arrays.equals( vals, target.echo_char_s( vals ) ) )
                System.out.println( "Sequence test failed for char" );

            org.omg.CORBA.CharSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed char seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for char any sequence" );
        }

        // wchar
        {
            final char [] vals = { 'a', 'b', '\u0001', '\u0020', '\u008D', '\u00FF',
                  '\u0626', '\u7554', '\uF7F5', '\uFFFF'};

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( target.echo_wchar( vals[ i ] ) != vals[ i ] )
                    System.out.println( "failed wchar " + vals[ i ] );

                any.insert_wchar( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for wchar any " + vals[ i ] );

                if ( rpl.extract_wchar() != vals[ i ] )
                    System.out.println( "failed wchar any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed wchar typecode" );

            if ( !Arrays.equals( vals, target.echo_wchar_s( vals ) ) )
                System.out.println( "Sequence test failed for wchar" );

            org.omg.CORBA.WCharSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed wchar seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for wchar any sequence" );
        }

        // string
        {
            final String [] vals = { "", "Mr Jock, T.V. quiz PhD, bags few lynx.",
                 "\u0001\u0010\u007F\u008D\u00FF"};

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( !target.echo_string( vals[ i ] ).equals( vals[ i ] ) )
                    System.out.println( "failed string " + vals[ i ] );

                any.insert_string( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for string any " + vals[ i ] );

                if ( !rpl.extract_string().equals( vals[ i ] ) )
                    System.out.println( "failed string any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed string typecode" );

            if ( !Arrays.equals( vals, target.echo_string_s( vals ) ) )
                System.out.println( "Sequence test failed for string" );

            org.omg.CORBA.StringSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed string seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for string any sequence" );

            // echo limited length string typecode
            org.omg.CORBA.TypeCode tc = orb.create_string_tc( 10 );

            if ( !target.echo_TypeCode( tc ).equal( tc ) )
                System.out.println( "failed string<10> typecode" );
        }

        // wstring
        {
            final String [] vals = { "", "Mr Jock, T.V. quiz PhD, bags few lynx."
                  /*, "\u0001\u0010\u007F\u008D\u00FF\u05DD\u7FDE\u8D40\uFFFF"*/};

            for ( int i = 0; i < vals.length; ++i )
            {
                if ( !target.echo_wstring( vals[ i ] ).equals( vals[ i ] ) )
                    System.out.println( "failed wstring " + vals[ i ] );

                any.insert_wstring( vals[ i ] );

                rpl = target.echo_any( any );

                if ( !any.equal( rpl ) )
                    System.out.println( "Equality test failed for wstring any " + vals[ i ] );

                if ( !rpl.extract_wstring().equals( vals[ i ] ) )
                    System.out.println( "failed wstring any " + vals[ i ] );
            }

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed wstring typecode" );

            if ( !Arrays.equals( vals, target.echo_wstring_s( vals ) ) )
                System.out.println( "Sequence test failed for wstring" );

            org.omg.CORBA.WStringSeqHelper.insert( any, vals );

            if ( !target.echo_TypeCode( any.type() ).equal( any.type() ) )
                System.out.println( "failed wstring seq typecode" );

            rpl = target.echo_any( any );

            if ( !any.equal( rpl ) )
                System.out.println( "Equality test failed for wstring any sequence" );

            // echo limited length wstring typecode
            org.omg.CORBA.TypeCode tc = orb.create_wstring_tc( 10 );

            if ( !target.echo_TypeCode( tc ).equal( tc ) )
                System.out.println( "failed wstring<10> typecode" );
        }

        if ( !( orb instanceof org.omg.CORBA_2_3.ORB ) )
            System.out.println( "Orb is not a valuetype enabled orb" );

        org.omg.CORBA_2_3.ORB clientORB = ( org.omg.CORBA_2_3.ORB ) orb;

        // ValueA a very plain value.
        clientORB.register_value_factory( ValueAHelper.id(), new ValueADefaultFactory() );

        System.out.println( "Read a ValueA: " + target.get_value_a( 0 ).toString() );

        System.out.println( "Read a ValueA as ValueBase: " + target.get_value( 0 ).toString() );

        //System.out.println("Read a ValueA an AbstractBase: " + target.get_abstract(0).toString());

        // ValueB has private members.
        clientORB.register_value_factory( ValueBHelper.id(), new ValueBDefaultFactory() );

        System.out.println( "Read a ValueB: " + target.get_value_b().toString() );

        System.out.println( "Read a ValueB as ValueBase: " + target.get_value( 1 ).toString() );

        // ValueC, subclasses without truncating ValueA
        try
        {
            // this should fail, no factory registered.
            System.out.println( "Read a ValueC without factory (should fail) " +
                  target.get_value_c() );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {}

        try
        {
            // this should fail, no factory registered.
            System.out.println( "Read a ValueC as ValueA without factory (should fail) " +
                  target.get_value_a( 2 ) );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {}

        try
        {
            // this should fail, no factory registered.
            System.out.println( "Read a ValueC as ValueBase without factory (should fail) " +
                  target.get_value( 2 ) );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {}

        clientORB.register_value_factory( ValueCHelper.id(), new ValueCFactory() );

        System.out.println( "Read a ValueC: " + target.get_value_c().toString() );

        System.out.println( "Read a ValueC as a ValueA: " + target.get_value_a( 2 ).toString() );

        System.out.println( "Read a ValueC as a ValueBase: " + target.get_value( 2 ).toString() );

        // ValueD subclasses and truncates to ValueA

        try
        {
            // this should fail, can't get a ValueD as factory not registered.
            System.out.println( "Read a ValueD without factory (should fail) " +
                  target.get_value_d() );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {}

        System.out.println( "Read a ValueD as ValueA and truncate: " +
              target.get_value_a( 3 ).toString() );

        System.out.println( "Read a ValueD as ValueBase and truncate: " +
              target.get_value( 3 ).toString() );

        clientORB.register_value_factory( ValueDHelper.id(), new ValueDFactory() );

        System.out.println( "Read a ValueD: " + target.get_value_d().toString() );

        System.out.println( "Read a ValueD as a ValueA: " + target.get_value_a( 3 ).toString() );

        System.out.println( "Read a ValueD as a ValueBase: " + target.get_value( 3 ).toString() );

        // ValueE (supports)

        clientORB.register_value_factory( ValueEHelper.id(), new ValueEFactory() );

        System.out.println( "Call print on ValueE as a value:" );
        target.get_value_e().print();

        System.out.println( "Call print on ValueE as an Object:" );
        target.get_value_e_remote().print();

        // ValueF, custom marshal.
        clientORB.register_value_factory( ValueFHelper.id(), new ValueFDefaultFactory() );
        System.out.println( "Read a ValueF: " + target.get_value_f().toString() );

        // ValueG nested values
        clientORB.register_value_factory( ValueGHelper.id(), new ValueGDefaultFactory() );
        System.out.println( "Read a ValueG: " + target.get_value_g().toString() );

        System.out.println( "Call print on AbstractA1" );
        target.get_abstract_a( 0 ).print();

        clientORB.register_value_factory( AbstractA2Helper.id(), new AbstractA2Factory() );
        clientORB.register_value_factory( AbstractA3Helper.id(), new AbstractA3Factory() );

        System.out.println( "Call print on AbstractA2" );
        target.get_abstract_a( 1 ).print();

        System.out.println( "Call print on AbstractA3 local" );
        target.get_abstract_a( 2 ).print();

        System.out.println( "Call print on AbstractA3 remote" );
        target.get_abstract_a( 3 ).print();
    }

    private static void benchmark( Benchmark target, org.omg.CORBA.ORB orb )
    {
        final long repeat = 10000;

        long start, stop, sum;
        org.omg.CORBA.Any any = orb.create_any();
        java.util.Random rand = new java.util.Random();

        // bind
        target._non_existent();

        System.out.println( "Test                 time" );

        boolean delayed = false;
        boolean big = true;

        // locate request.
        /*
        if(false) {
         org.openorb.util.Profiler.reset(16);
         sum = 0;
         
         any.insert_Object(target);

         for(int i = 0; i < repeat; ++i) {
          start = System.currentTimeMillis();
          target.echo_any(any);
          stop = System.currentTimeMillis();
          sum += stop-start;
          org.openorb.util.Profiler.point();
          try { Thread.sleep(1); } catch(Exception ex) {}
          org.openorb.util.Profiler.next();
         }
         System.out.println("Ping                 "+((sum)/repeat)+"."+((sum))%repeat);  

         java.text.DecimalFormat fmt = new java.text.DecimalFormat("0.0000");
         double [] mean = org.openorb.util.Profiler.means();
         System.out.print("means = { ");
         for(int i = 0; i < mean.length-1; ++i)
          System.out.print(fmt.format(mean[i]) + ", ");
         System.out.println(fmt.format(mean[mean.length-1]) + " }");

         double [] stdev = org.openorb.util.Profiler.stddev();
         System.out.print("stdev = { ");
         for(int i = 0; i < mean.length-1; ++i)
          System.out.print(fmt.format(stdev[i]) + ", ");
         System.out.println(fmt.format(stdev[stdev.length-1]) + " }");
         
         return;
    }
        */
        target._non_existent();

        start = System.currentTimeMillis();

        for ( int i = 0; i < repeat; ++i )
            target._non_existent();

        stop = System.currentTimeMillis();

        System.out.println( "Locate               " + ( ( stop - start ) / repeat ) + "." +
              ( ( stop - start ) ) % repeat );

        if ( delayed )
        {
            sum = 0;

            for ( int i = 0; i < repeat; ++i )
            {
                start = System.currentTimeMillis();
                target._non_existent();
                sum += System.currentTimeMillis() - start;

                try
                {
                    Thread.sleep( 0, 1 );
                }
                catch ( InterruptedException ex )
                {}

            }

            System.out.println( "Locate (l)           " + ( ( sum ) / repeat ) + "." +
                  ( ( sum ) ) % repeat );
        }

        // ping
        sum = 0;

        target.ping();

        start = System.currentTimeMillis();

        for ( int i = 0; i < repeat; ++i )
            target.ping();

        stop = System.currentTimeMillis();

        System.out.println( "Ping                 " + ( ( stop - start ) / repeat ) + "." +
              ( ( stop - start ) ) % repeat );

        if ( delayed )
        {
            sum = 0;

            for ( int i = 0; i < repeat; ++i )
            {
                start = System.currentTimeMillis();
                target.ping();
                sum += System.currentTimeMillis() - start;

                try
                {
                    Thread.sleep( 0, 1 );
                }
                catch ( InterruptedException ex )
                {}

            }

            System.out.println( "Ping (l)             " + ( ( sum ) / repeat ) + "." +
                  ( ( sum ) ) % repeat );
        }

        // echo empty any

        any.type( orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_void ) );

        target.echo_any( any );

        start = System.currentTimeMillis();

        for ( int i = 0; i < repeat; ++i )
            target.echo_any( any );

        stop = System.currentTimeMillis();

        System.out.println( "any(void)            " + ( ( stop - start ) / repeat ) + "." +
              ( ( stop - start ) ) % repeat );

        if ( delayed )
        {
            sum = 0;

            for ( int i = 0; i < repeat; ++i )
            {
                start = System.currentTimeMillis();
                target.echo_any( any );
                sum += System.currentTimeMillis() - start;

                try
                {
                    Thread.sleep( 0, 1 );
                }
                catch ( InterruptedException ex )
                {}

            }

            System.out.println( "any(void) (l)        " + ( ( sum ) / repeat ) + "." +
                  ( ( sum ) ) % repeat );
        }

        // thow exception

        try
        {
            target.raise_exception();
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {}

        start = System.currentTimeMillis();

        for ( int i = 0; i < repeat; ++i )
        {
            try
            {
                target.raise_exception();
            }
            catch ( org.omg.CORBA.UNKNOWN ex )
            {}

        }

        stop = System.currentTimeMillis();

        System.out.println( "Throw Exception      " + ( ( stop - start ) / repeat ) + "." +
              ( ( stop - start ) ) % repeat );

        if ( delayed )
        {
            sum = 0;

            for ( int i = 0; i < repeat; ++i )
            {
                start = System.currentTimeMillis();

                try
                {
                    target.raise_exception();
                }
                catch ( org.omg.CORBA.UNKNOWN ex )
                {}

                ;

                sum += System.currentTimeMillis() - start;

                try
                {
                    Thread.sleep( 0, 1 );
                }
                catch ( InterruptedException ex )
                {}

            }

            System.out.println( "Throw Exception (l)  " + ( ( sum ) / repeat ) + "." +
                  ( ( sum ) ) % repeat );
        }

        // echo object
        target.echo_Object( target );

        start = System.currentTimeMillis();

        for ( int i = 0; i < repeat; ++i )
            target.echo_Object( target );

        stop = System.currentTimeMillis();

        System.out.println( "Object               " + ( ( stop - start ) / repeat ) + "." +
              ( ( stop - start ) ) % repeat );

        if ( delayed )
        {
            sum = 0;

            for ( int i = 0; i < repeat; ++i )
            {
                start = System.currentTimeMillis();
                target.echo_Object( target );
                sum += System.currentTimeMillis() - start;

                try
                {
                    Thread.sleep( 0, 1 );
                }
                catch ( InterruptedException ex )
                {}

            }

            System.out.println( "Object (l)           " + ( ( sum ) / repeat ) + "." +
                  ( ( sum ) ) % repeat );
        }

        // any containing object

        any.insert_Object( target );

        target.echo_any( any );

        start = System.currentTimeMillis();

        for ( int i = 0; i < repeat; ++i )
            target.echo_any( any );

        stop = System.currentTimeMillis();

        System.out.println( "any(Object)          " + ( ( stop - start ) / repeat ) + "." +
              ( ( stop - start ) ) % repeat );

        if ( delayed )
        {
            sum = 0;

            for ( int i = 0; i < repeat; ++i )
            {
                start = System.currentTimeMillis();
                target.echo_any( any );
                sum += System.currentTimeMillis() - start;

                try
                {
                    Thread.sleep( 1 );
                }
                catch ( InterruptedException ex )
                {}

            }

            System.out.println( "any(Object) (l)      " + ( ( sum ) / repeat ) + "." +
                  ( ( sum ) ) % repeat );
        }

        // octet
        target.echo_octet( ( byte ) 0 );

        start = System.currentTimeMillis();

        for ( int i = 0; i < repeat; ++i )
            target.echo_octet( ( byte ) 0 );

        stop = System.currentTimeMillis();

        System.out.println( "octet                " + ( ( stop - start ) / repeat ) + "." +
              ( ( stop - start ) ) % repeat );

        if ( delayed )
        {
            sum = 0;

            for ( int i = 0; i < repeat; ++i )
            {
                start = System.currentTimeMillis();
                target.echo_octet( ( byte ) 0 );
                sum += System.currentTimeMillis() - start;

                try
                {
                    Thread.sleep( 0, 1 );
                }
                catch ( InterruptedException ex )
                {}

            }

            System.out.println( "octet (l)            " + ( ( sum ) / repeat ) + "." +
                  ( ( sum ) ) % repeat );
        }

        any.insert_octet( ( byte ) 0 );
        target.echo_any( any );
        start = System.currentTimeMillis();

        for ( int i = 0; i < repeat; ++i )
            target.echo_any( any );

        stop = System.currentTimeMillis();

        System.out.println( "any(octet)           " + ( ( stop - start ) / repeat ) + "." +
              ( ( stop - start ) ) % repeat );

        if ( delayed )
        {
            sum = 0;

            for ( int i = 0; i < repeat; ++i )
            {
                start = System.currentTimeMillis();
                target.echo_any( any );
                sum += System.currentTimeMillis() - start;

                try
                {
                    Thread.sleep( 0, 1 );
                }
                catch ( InterruptedException ex )
                {}

            }

            System.out.println( "any(octet) (l)       " + ( ( sum ) / repeat ) + "." +
                  ( ( sum ) ) % repeat );
        }

        // octet arrays.
        for ( int len = 4, n = 2; n <= 14; n += 2, len *= 4 )
        {
            byte [] data = new byte[ len ];
            rand.nextBytes( data );

            target.echo_octet_s( data );
            start = System.currentTimeMillis();

            for ( int i = 0; i < repeat; ++i )
                target.echo_octet_s( data );

            stop = System.currentTimeMillis();

            System.out.println( "octet[2^" + n + ( ( n < 10 ) ? "]           " : "]          " ) +
                  ( ( stop - start ) / repeat ) + "." + ( ( stop - start ) ) % repeat );

            if ( delayed )
            {
                sum = 0;

                for ( int i = 0; i < repeat; ++i )
                {
                    start = System.currentTimeMillis();
                    target.echo_octet_s( data );
                    sum += System.currentTimeMillis() - start;

                    try
                    {
                        Thread.sleep( 0, 1 );
                    }
                    catch ( InterruptedException ex )
                    {}

                }

                System.out.println( "octet[2^" + n + ( ( n < 10 ) ? "] (l)       " : "] (l)      " )
                      + ( ( sum ) / repeat ) + "." + ( ( sum ) ) % repeat );
            }
        }

        if ( big )
            for ( int len = 32 * 1024, n = 15; n <= 20; ++n, len *= 2 )
            {
                byte [] data = new byte[ len ];
                rand.nextBytes( data );

                int div = n < 17 ? 10 : 100;

                target.echo_octet_s( data );
                start = System.currentTimeMillis();

                for ( int i = 0; i < repeat / div; ++i )
                    target.echo_octet_s( data );

                stop = System.currentTimeMillis();

                System.out.println( "octet[2^" + n + ( ( n < 10 ) ? "]           " : "]          " )
                      + ( ( stop - start ) / ( repeat / div ) ) + "." +
                      ( ( stop - start ) ) % ( repeat / div ) );

                if ( delayed )
                {
                    sum = 0;

                    for ( int i = 0; i < repeat / div; ++i )
                    {
                        start = System.currentTimeMillis();
                        target.echo_octet_s( data );
                        sum += System.currentTimeMillis() - start;

                        try
                        {
                            Thread.sleep( 0, 1 );
                        }
                        catch ( InterruptedException ex )
                        {}

                    }

                    System.out.println( "octet[2^" + n + ( ( n < 10 ) ? "] (l)       " :
                          "] (l)      " ) + ( ( sum ) / ( repeat / div ) ) + "." +
                          ( ( sum ) ) % ( repeat / div ) );
                }
            }

        if ( big )
            for ( int len = 16, n = 4; n <= 9; ++n, len *= 2 )
            {
                byte [][] data = new byte[ len ][];

                for ( int i = 0; i < data.length; ++i )
                {
                    data[ i ] = new byte[ 2048 ];
                    rand.nextBytes( data[ i ] );
                }

                int div = n < 7 ? 10 : 100;

                target.echo_octet_s_s( data );
                start = System.currentTimeMillis();

                for ( int i = 0; i < repeat / div; ++i )
                    target.echo_octet_s_s( data );

                stop = System.currentTimeMillis();

                System.out.println( "octet[2^" + n +
                      ( ( n < 10 ) ? "][2048]     " : "][2048]    " ) +
                      ( ( stop - start ) / ( repeat / div ) ) + "." +
                      ( ( stop - start ) ) % ( repeat / div ) );

                if ( delayed )
                {
                    sum = 0;

                    for ( int i = 0; i < repeat / 10; ++i )
                    {
                        start = System.currentTimeMillis();
                        target.echo_octet_s_s( data );
                        sum += System.currentTimeMillis() - start;

                        try
                        {
                            Thread.sleep( 0, 1 );
                        }
                        catch ( InterruptedException ex )
                        {}

                    }

                    System.out.println( "octet[2^" + n +
                          ( ( n < 10 ) ? "][2048] (l) " : "][2048] (l)" ) +
                          ( ( sum ) / ( repeat / div ) ) + "." + ( ( sum ) ) % ( repeat / div ) );
                }
            }

        // any(octet)
        for ( int len = 4, n = 2; n <= 14; n += 2, len *= 4 )
        {
            byte [] data = new byte[ len ];
            rand.nextBytes( data );

            org.omg.CORBA.OctetSeqHelper.insert( any, data );
            target.echo_any( any );

            start = System.currentTimeMillis();

            for ( int i = 0; i < repeat; ++i )
                target.echo_any( any );

            stop = System.currentTimeMillis();

            System.out.println( "any(octet[2^" + n +
                  ( ( n < 10 ) ? "])      " : "])     " ) +
                  ( ( stop - start ) / repeat ) + "." + ( ( stop - start ) ) % repeat );

            if ( delayed )
            {
                sum = 0;

                for ( int i = 0; i < repeat; ++i )
                {
                    start = System.currentTimeMillis();
                    target.echo_any( any );
                    sum += System.currentTimeMillis() - start;

                    try
                    {
                        Thread.sleep( 0, 1 );
                    }
                    catch ( InterruptedException ex )
                    {}

                }

                System.out.println( "any(octet[2^" + n +
                      ( ( n < 10 ) ? "]) (l)  " : "]) (l) " ) +
                      ( ( sum ) / repeat ) + "." + ( ( sum ) ) % repeat );
            }
        }

        if ( big )
            for ( int len = 32 * 1024, n = 15; n <= 20; ++n, len *= 2 )
            {
                byte [] data = new byte[ len ];
                rand.nextBytes( data );

                org.omg.CORBA.OctetSeqHelper.insert( any, data );
                target.echo_any( any );

                int div = ( n < 16 ) ? 10 : 100;

                start = System.currentTimeMillis();

                for ( int i = 0; i < repeat / div; ++i )
                    target.echo_any( any );

                stop = System.currentTimeMillis();

                System.out.println( "any(octet[2^" + n +
                      ( ( n < 10 ) ? "])      " : "])     " ) +
                      ( ( stop - start ) / ( repeat / div ) ) + "." +
                      ( ( stop - start ) ) % ( repeat / div ) );

                if ( delayed )
                {
                    sum = 0;

                    for ( int i = 0; i < repeat / div; ++i )
                    {
                        start = System.currentTimeMillis();
                        target.echo_any( any );
                        sum += System.currentTimeMillis() - start;

                        try
                        {
                            Thread.sleep( 0, 1 );
                        }
                        catch ( InterruptedException ex )
                        {}

                    }

                    System.out.println( "any(octet[2^" + n +
                          ( ( n < 10 ) ? "]) (l)  " : "]) (l) " ) +
                          ( ( sum ) / ( repeat / div ) ) + "." + ( ( sum ) ) % ( repeat / div ) );
                }
            }

        // long (needs buffer copies)
        for ( int len = 4, n = 2; n <= 12; n += 2, len *= 4 )
        {
            int [] data = new int[ len ];

            for ( int i = 0; i < len; ++i )
                data[ i ] = rand.nextInt();

            target.echo_long_s( data );

            start = System.currentTimeMillis();

            for ( int i = 0; i < repeat; ++i )
                target.echo_long_s( data );

            stop = System.currentTimeMillis();

            System.out.println( "long[2^" + n +
                  ( ( n < 10 ) ? "]            " : "]           " ) +
                  ( ( stop - start ) / repeat ) + "." + ( ( stop - start ) ) % repeat );

            if ( delayed )
            {
                sum = 0;

                for ( int i = 0; i < repeat; ++i )
                {
                    start = System.currentTimeMillis();
                    target.echo_long_s( data );
                    sum += System.currentTimeMillis() - start;

                    try
                    {
                        Thread.sleep( 0, 1 );
                    }
                    catch ( InterruptedException ex )
                    {}

                }

                System.out.println( "long[2^" + n +
                      ( ( n < 10 ) ? "] (l)        " : "] (l)       " ) +
                      ( ( sum ) / repeat ) + "." + ( ( sum ) ) % repeat );
            }
        }

        if ( big )
            for ( int len = 8 * 1024, n = 13; n <= 18; ++n, len *= 2 )
            {
                int [] data = new int[ len ];

                for ( int i = 0; i < len; ++i )
                    data[ i ] = rand.nextInt();

                int div = ( n < 15 ) ? 10 : 100;

                target.echo_long_s( data );

                start = System.currentTimeMillis();

                for ( int i = 0; i < repeat / div; ++i )
                    target.echo_long_s( data );

                stop = System.currentTimeMillis();

                System.out.println( "long[2^" + n +
                      ( ( n < 10 ) ? "]          " : "]           " ) +
                      ( ( stop - start ) / ( repeat / div ) ) + "." +
                      ( ( stop - start ) ) % ( repeat / div ) );

                if ( delayed )
                {
                    sum = 0;

                    for ( int i = 0; i < repeat / div; ++i )
                    {
                        start = System.currentTimeMillis();
                        target.echo_long_s( data );
                        sum += System.currentTimeMillis() - start;

                        try
                        {
                            Thread.sleep( 0, 1 );
                        }
                        catch ( InterruptedException ex )
                        {}

                    }

                    System.out.println( "long[2^" + n +
                          ( ( n < 10 ) ? "] (l)        " : "] (l)       " ) +
                          ( ( sum ) / ( repeat / div ) ) + "." + ( ( sum ) ) % ( repeat / div ) );
                }
            }

        if ( big )
            for ( int len = 4, n = 2; n <= 7; ++n, len *= 2 )
            {
                int [][] data = new int[ len ][];

                for ( int i = 0; i < data.length; ++i )
                {
                    data[ i ] = new int[ 2048 ];

                    for ( int j = 0; j < data[ i ].length; ++j )
                        data[ i ][ j ] = rand.nextInt();
                }

                int div = ( n < 4 ) ? 10 : 100;

                target.echo_long_s_s( data );
                start = System.currentTimeMillis();

                for ( int i = 0; i < repeat / div; ++i )
                    target.echo_long_s_s( data );

                stop = System.currentTimeMillis();

                System.out.println( "long[2^" + n +
                      ( ( n < 10 ) ? "][2048]      " : "][2048]     " ) +
                      ( ( stop - start ) / ( repeat / div ) ) + "." +
                      ( ( stop - start ) ) % ( repeat / div ) );

                if ( delayed )
                {
                    sum = 0;

                    for ( int i = 0; i < repeat / div; ++i )
                    {
                        start = System.currentTimeMillis();
                        target.echo_long_s_s( data );
                        sum += System.currentTimeMillis() - start;

                        try
                        {
                            Thread.sleep( 0, 1 );
                        }
                        catch ( InterruptedException ex )
                        {}

                    }

                    System.out.println( "long[2^" + n +
                          ( ( n < 10 ) ? "][2048] (l)  " : "][2048] (l) " ) +
                          ( ( sum ) / ( repeat / div ) ) + "." + ( ( sum ) ) % ( repeat / div ) );
                }
            }
    }

    private static volatile boolean s_done = false;

    private static void stress( final Benchmark target, org.omg.CORBA.ORB orb )
    {
        final IntHolder totalCalls = new IntHolder( 0 );

        final Runnable testRunner = new Runnable()
                                    {
                                        public void run()
                                        {
                                            try
                                            {
                                                Thread.sleep( 60000 );
                                            }
                                            catch ( InterruptedException ex )
                                            {}

                                            int i = 0;

                                            while ( !s_done )
                                            {
                                                try
                                                {
                                                    target.ping();
                                                }
                                                catch ( org.omg.CORBA.TRANSIENT ex )
                                                {
                                                    continue;
                                                }

                                                ++i;
                                            }

                                            synchronized ( totalCalls )
                                            {
                                                totalCalls.value += i;
                                            }
                                        }
                                    };

        int [] tc = { 1, 5, 10, 15, 20, 30, 50, 100, 200, 300, 400, 500 };

        try
        {
            for ( int i = 0; i < tc.length; ++i )
            {
                Thread [] w = new Thread[ tc[ i ] ];
                ThreadGroup tg = new ThreadGroup( "Workers" );

                for ( int j = 0; j < tc[ i ]; ++j )
                {
                    w[ j ] = new Thread( tg, testRunner, "w" + i + "/" + tc[ i ] );
                    w[ j ].start();
                }

                long start = System.currentTimeMillis();
                s_done = false;
                totalCalls.value = 0;
                tg.interrupt();
                Thread.sleep( 5000 );
                s_done = true;
                long stop = System.currentTimeMillis();

                for ( int j = 0; j < tc[ i ]; ++j )
                    w[ j ].join();

                for ( int j = 0; j < tc[ i ]; ++j )
                {
                    w[ j ] = new Thread( tg, testRunner, "w" + i + "/" + tc[ i ] );
                    w[ j ].start();
                }

                start = System.currentTimeMillis();
                s_done = false;
                totalCalls.value = 0;
                tg.interrupt();
                Thread.sleep( 40000 );
                s_done = true;
                stop = System.currentTimeMillis();

                for ( int j = 0; j < tc[ i ]; ++j )
                    w[ j ].join();

                System.out.println( tc[ i ] + "\t" +
                      ( ( double ) ( totalCalls.value * 1000 ) ) /
                      ( ( double ) ( stop - start ) ) );
            }
        }
        catch ( InterruptedException ex )
        {
            System.out.println( "yarr!!!!! kersplut" );
        }
    }
}
