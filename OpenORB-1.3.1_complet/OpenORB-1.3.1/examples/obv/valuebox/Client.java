/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.valuebox;

public class Client
{
    public static void main( String [] args )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );

        IValueExchange value = null;
        org.omg.CORBA.Object obj = null;

        try
        {
            java.io.FileInputStream file = new java.io.FileInputStream( "ObjectId" );
            java.io.InputStreamReader input = new java.io.InputStreamReader( file );
            java.io.BufferedReader reader = new java.io.BufferedReader( input );
            String ref = reader.readLine();
            obj = orb.string_to_object( ref ) ;
        }
        catch ( java.io.IOException ex )
        {
            System.out.println( "File error" );
            System.exit( 0 );
        }

        value = IValueExchangeHelper.narrow( obj );

        try
        {
            longBox aLongBox = new longBox( 100 );

            value.sendLongBox( aLongBox );

            int [] aLongSeqBox = new int[ 10 ];

            for ( int i = 0; i < 10; i++ )
                aLongSeqBox[ i ] = i;

            value.sendLongSeqBox( aLongSeqBox );

            value.sendLongBox( null );

            value.sendLongSeqBox( null );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            System.out.println( "A CORBA Exception has been intercepted" );
        }
    }
}
