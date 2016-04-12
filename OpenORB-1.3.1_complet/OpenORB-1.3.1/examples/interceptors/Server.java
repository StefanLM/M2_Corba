/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package interceptors;

public class Server
{
    public static void main( String[] args )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );

        org.omg.PortableServer.POA rootPOA = null;

        try
        {
            org.omg.CORBA.Object objPoa = orb.resolve_initial_references( "RootPOA" );
            rootPOA = org.omg.PortableServer.POAHelper.narrow( objPoa );
        }
        catch ( java.lang.Exception ex )
        {
            ex.printStackTrace();
        }

        ServerImpl srv = new ServerImpl();

        try
        {
            org.omg.CORBA.Object obj = srv._this( orb );

            String reference = orb.object_to_string( obj );

            try
            {
                java.io.FileOutputStream file = new java.io.FileOutputStream( "ObjectId" );
                java.io.PrintStream pfile = new java.io.PrintStream( file );
                pfile.println( reference );
                file.close();
            }
            catch ( java.io.IOException ex )
            {
                System.out.println( "File error" );
            }

            rootPOA.the_POAManager().activate();

            System.out.println( "The server is ready ..." );

            orb.run();
        }
        catch ( java.lang.Exception ex )
        {
            System.out.println( "An exception has been intercepted..." );
        }
    }
}
