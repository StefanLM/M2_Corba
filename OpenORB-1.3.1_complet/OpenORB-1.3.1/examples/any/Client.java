/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package any;

public class Client
{
    public static void main( String [] args )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null ) ;

        org.omg.CORBA.Object obj = null ;

        try
        {

            java.io.FileInputStream file = new java.io.FileInputStream( "ObjectId" );
            java.io.InputStreamReader myInput = new java.io.InputStreamReader( file );
            java.io.BufferedReader reader = new java.io.BufferedReader( myInput );
            String ref = reader.readLine();
            obj = orb.string_to_object( ref ) ;
        }
        catch ( java.io.IOException ex )

        {
            ex.printStackTrace( ) ;
        }

        try
        {
            IPolymorph poly = IPolymorphHelper.narrow( obj ) ;

            org.omg.CORBA.Any any = orb.create_any() ;

            any.insert_boolean( true ) ;

            poly.pass( any ) ;

            any.insert_long( 123 ) ;

            poly.pass( any ) ;

            any.insert_string( " Hello world ! " ) ;

            poly.pass( any ) ;

            person p = new person( " John ", " Bob " ) ;

            personHelper.insert( any, p ) ;

            poly.pass( any ) ;

            int [] seq = new int[ 10 ] ;

            for ( int i = 0 ; i < seq.length ; i++ )
                seq[ i ] = i ;

            longSeqHelper.insert( any, seq ) ;

            poly.pass( any ) ;

            System.out.println( " We are going to catch an exception... " ) ;

            any.insert_float( ( float ) 3.14 ) ;

            poly.pass( any ) ;
        }
        catch ( UnknownType ex )
        {
            System.out.println( " A unknown type has been sent to the server. " ) ;
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            ex.printStackTrace( ) ;
        }

    }
}
