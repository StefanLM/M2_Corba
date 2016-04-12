/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package interceptors;

public class Client
{
    public static void main( String[] args )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );

        org.omg.CORBA.Object obj = null;

        try
        {
            java.io.FileInputStream file = new java.io.FileInputStream( "ObjectId" );
            java.io.BufferedReader myInput = new java.io.BufferedReader(
                  new java.io.InputStreamReader( file ) );
            String stringTarget = myInput.readLine();

            obj = orb.string_to_object( stringTarget );

        }
        catch ( java.io.IOException ex )
        {
            System.out.println( "File error" );
            System.exit( 0 );
        }

        ServerTest srv = ServerTestHelper.narrow( obj );

        try
        {
            System.out.println( "Invokes the server..." );

            String msg = srv.print( "Hello from the client side..." );

            System.out.println( "Received " + msg );
        }
        catch ( java.lang.Exception ex )
        {
            System.out.println( "An exception has been intercepted..." );
        }
    }
}
