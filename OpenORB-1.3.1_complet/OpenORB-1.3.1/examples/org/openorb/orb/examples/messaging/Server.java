/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.orb.examples.messaging;

public class Server
{
    public static void main( String args[] )
    {
        java.util.Properties props = System.getProperties();
        props.put( "org.omg.CORBA.ORBClass", "org.openorb.CORBA.ORB" );
        props.put( "org.omg.CORBA.ORBSingletonClass", "org.openorb.CORBA.ORBSingleton" );
        //props.put( "verbose", "5" );
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, props );

        org.omg.CORBA.Object objPoa = null;
        org.omg.PortableServer.POA rootPOA = null;

        try
        {
            objPoa = orb.resolve_initial_references( "RootPOA" );
        }
        catch ( org.omg.CORBA.ORBPackage.InvalidName ex )
        {
            System.out.println( "Couldn't find RootPOA!" );
            System.exit( 1 );
        }

        rootPOA = org.omg.PortableServer.POAHelper.narrow( objPoa );

        Timeout time = new Timeout();

        try
        {
            org.omg.CORBA.Object obj = time._this( orb );

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

            System.out.println( "The server is ready..." );

            orb.run();
        }
        catch ( Exception ex )
        {
            System.out.println( "An exception has been intercepted" );
            ex.printStackTrace();
        }

    }
}
