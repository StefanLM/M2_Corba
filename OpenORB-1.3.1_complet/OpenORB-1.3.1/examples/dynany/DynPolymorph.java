/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package dynany;

public class DynPolymorph extends IPolymorphPOA
{
    public void pass( org.omg.CORBA.Any value )
        throws UnknownType
    {
        org.omg.CORBA.TypeCode tc = value.type( ) ;

        switch ( tc.kind().value() )
        {

        case org.omg.CORBA.TCKind._tk_long :
            int l = value.extract_long( ) ;
            System.out.println( " A long value : " + l ) ;
            break ;

        case org.omg.CORBA.TCKind._tk_short :
            int sh = value.extract_short( ) ;
            System.out.println( " A short value : " + sh ) ;
            break ;

        case org.omg.CORBA.TCKind._tk_string :
            String s = value.extract_string( ) ;
            System.out.println( " A string value : " + s ) ;
            break ;

        case org.omg.CORBA.TCKind._tk_boolean :
            boolean b = value.extract_boolean( ) ;
            System.out.println( " A boolean value : " + b ) ;
            break ;

        case org.omg.CORBA.TCKind._tk_octet :
            byte octet = value.extract_octet( ) ;
            System.out.println( " An octet value : " + octet ) ;
            break ;

        default :
            scan_any( value );
            break;
        }
    }

    private void scan_any( org.omg.CORBA.Any value )
        throws UnknownType
    {
        org.omg.CORBA.TypeCode tc = value.type( ) ;

        switch ( getEquivalentKind( tc ) )
        {

        case org.omg.CORBA.TCKind._tk_struct :
            scan_struct( value );
            break;

        case org.omg.CORBA.TCKind._tk_sequence :
            scan_sequence( value );
            break;

        case org.omg.CORBA.TCKind._tk_array :
            scan_array( value );
            break;

        default :
            throw new UnknownType();
        }

    }

    private int getEquivalentKind( org.omg.CORBA.TypeCode tc )
    {
        switch ( tc.kind().value() )
        {

        case org.omg.CORBA.TCKind._tk_alias:

            try
            {
                return tc.content_type().kind().value();
            }
            catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
            {
                ex.printStackTrace();
            }

            return org.omg.CORBA.TCKind._tk_null;

        default:
            return tc.kind().value();
        }
    }

    private void scan_struct( org.omg.CORBA.Any value )
        throws UnknownType
    {
        org.omg.DynamicAny.DynAnyFactory factory = getDynAnyFactory();

        org.omg.DynamicAny.DynAny dany = null;

        try
        {
            dany = factory.create_dyn_any( value );
        }
        catch ( org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode e )
        {
            System.out.println( "Inconsistent typecode: " + e );
            System.exit( 1 );
        }

        org.omg.DynamicAny.DynStruct dstruct = org.omg.DynamicAny.DynStructHelper.narrow( dany );

        org.omg.DynamicAny.NameValuePair [] members = dstruct.get_members();

        System.out.println( "-------------------------------------------" );
        System.out.println( "Got a struct, scan each member :" );

        for ( int i = 0; i < members.length; i++ )
        {
            System.out.println( "Member name : " + members[ i ].id );

            pass( members[ i ].value );
        }

        System.out.println( "-------------------------------------------" );
        System.out.println( "" );
    }

    private void scan_array( org.omg.CORBA.Any value )
        throws UnknownType
    {
        org.omg.DynamicAny.DynAnyFactory factory = getDynAnyFactory();

        org.omg.DynamicAny.DynAny dany = null;

        try
        {
            dany = factory.create_dyn_any( value );
        }
        catch ( org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode e )
        {
            System.out.println( "Inconsistent typecode: " + e );
            System.exit( 1 );
        }

        org.omg.DynamicAny.DynArray darray = org.omg.DynamicAny.DynArrayHelper.narrow( dany );

        org.omg.CORBA.Any [] any_array = darray.get_elements();

        System.out.println( "-------------------------------------------" );
        System.out.println( "Got an array, scan each element : " );

        for ( int i = 0; i < any_array.length; i++ )
        {
            pass( any_array[ i ] );
        }

        System.out.println( "-------------------------------------------" );
        System.out.println( "" );
    }

    private void scan_sequence( org.omg.CORBA.Any value )
        throws UnknownType
    {
        org.omg.DynamicAny.DynAnyFactory factory = getDynAnyFactory();

        org.omg.DynamicAny.DynAny dany = null;

        try
        {
            dany = factory.create_dyn_any( value );
        }
        catch ( org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode e )
        {
            System.out.println( "Inconsistent typecode: " + e );
            System.exit( 1 );
        }

        org.omg.DynamicAny.DynSequence dseq = org.omg.DynamicAny.DynSequenceHelper.narrow( dany );

        org.omg.CORBA.Any [] any_seq = dseq.get_elements();

        System.out.println( "-------------------------------------------" );
        System.out.println( "Got a sequence, scan each member : " );

        for ( int i = 0; i < any_seq.length; i++ )
        {
            pass( any_seq[ i ] );
        }

        System.out.println( "-------------------------------------------" );
        System.out.println( "" );
    }

    private org.omg.DynamicAny.DynAnyFactory getDynAnyFactory()
    {
        org.omg.DynamicAny.DynAnyFactory factory = null;

        try
        {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();

            org.omg.CORBA.Object obj = orb.resolve_initial_references( "DynAnyFactory" ) ;

            factory = org.omg.DynamicAny.DynAnyFactoryHelper.narrow( obj ) ;
        }
        catch ( org.omg.CORBA.ORBPackage.InvalidName ex )
        {
            System.out.println( "Couldn't get DynAnyFactory!" );
            System.exit( 1 );
        }

        return factory;
    }
}

