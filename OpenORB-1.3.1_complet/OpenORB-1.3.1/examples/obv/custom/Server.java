/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.custom;

public class Server
{
    public static void main( String args[] )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );

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

        valueExampleDefaultFactory factory = new valueExampleDefaultFactory();

        ( ( org.omg.CORBA_2_3.ORB ) orb ).register_value_factory(
              valueExampleHelper.id(), factory );

        ValueExchangeImpl value = new ValueExchangeImpl();

        String reference = orb.object_to_string( value._this( orb ) );

        try
        {
            java.io.FileOutputStream file = new java.io.FileOutputStream( "ObjectId" );
            java.io.PrintStream pfile = new java.io.PrintStream( file );
            pfile.println( reference );
        }
        catch ( java.io.IOException ex )
        {
            System.out.println( "Unable to export reference" );
        }

        try
        {
            rootPOA.the_POAManager().activate();

            System.out.println( "The server is ready..." );

            orb.run();
        }
        catch ( java.lang.Exception ex )
        {
            System.out.println( "An exception has been intercepted." );
        }
    }
}
