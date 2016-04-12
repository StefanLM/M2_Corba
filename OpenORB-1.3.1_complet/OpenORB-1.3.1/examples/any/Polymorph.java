/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package any;

public class Polymorph extends _IPolymorphImplBase
{
    public void pass( org.omg.CORBA.Any value )
    throws UnknownType
    {
        org.omg.CORBA.TypeCode tc = value.type( ) ;

        switch ( getEquivalentKind( tc ) )
        {

        case org.omg.CORBA.TCKind._tk_long :
            int l = value.extract_long( ) ;
            System.out.println( " A long value : " + l ) ;
            break ;

        case org.omg.CORBA.TCKind._tk_string :
            String s = value.extract_string( ) ;
            System.out.println( " A string value : " + s ) ;
            break ;

        case org.omg.CORBA.TCKind._tk_boolean :
            boolean b = value.extract_boolean( ) ;
            System.out.println( " A boolean value : " + b ) ;
            break ;

        case org.omg.CORBA.TCKind._tk_struct :

            if ( tc.equal( personHelper.type() ) )
            {
                person p = personHelper.extract( value ) ;

                System.out.println( " A person: " ) ;
                System.out.println( " Name = " + p.name ) ;
                System.out.println( " Surname = " + p.surname ) ;
            }
            else
                throw new UnknownType( ) ;

            break ;

        case org.omg.CORBA.TCKind._tk_sequence :
            if ( tc.equal( longSeqHelper.type() ) )
            {
                int [] seq = longSeqHelper.extract( value ) ;
                System.out.println( " A long sequence value : " ) ;

                for ( int i = 0 ; i < seq.length ; i++ )
                    System.out.println( " Element " + i + " = " + seq[ i ] ) ;
            }
            else
                throw new UnknownType( ) ;

            break ;

        default :
            // Unknown type
            throw new UnknownType( ) ;
        }
    }

    private int getEquivalentKind( org.omg.CORBA.TypeCode tc )
    {
        switch ( tc.kind().value() )
        {

        case org.omg.CORBA.TCKind._tk_alias :

            try
            {
                return tc.content_type().kind().value();
            }
            catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
            {
                ex.printStackTrace();
            }
            return org.omg.CORBA.TCKind._tk_null;

        default :
            return tc.kind().value();
        }
    }
}

