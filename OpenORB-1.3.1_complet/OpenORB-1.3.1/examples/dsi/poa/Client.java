/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package dsi.poa;

public class Client
{
    public static void main( String [] args )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null ) ;

        org.omg.CORBA.Object obj = null ;

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
            ex.printStackTrace( ) ;
            System.exit( 0 );
        }

        try
        {
            Sample ex = SampleHelper.narrow( obj ) ;

            System.out.println( " 6 / 2.25 = " + ex.div( 6, ( float ) 2.25 ) );

            System.out.println( " 6 / 0..." + ex.div( 6, 0 ) );
        }
        catch ( DivByZero ex )
        {
            System.out.println( "Division by zero..." );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            ex.printStackTrace( ) ;
        }

    }
}
