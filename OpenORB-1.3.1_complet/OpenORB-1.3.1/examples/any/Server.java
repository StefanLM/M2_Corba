/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package any;

public class Server
{
    public static void main( String [] args )
    {
        java.util.Properties props = new java.util.Properties();
        props.setProperty( "ImportModule.BOA", "${openorb.home}config/default.xml#BOA" );

        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, props );

        org.omg.CORBA.BOA boa = org.omg.CORBA.BOA.init( orb, args ) ;

        Polymorph poly = new Polymorph( ) ;

        boa.connect( poly ) ;

        boa.obj_is_ready( poly ) ;

        try
        {
            String ref = orb.object_to_string( poly ) ;
            java.io.FileOutputStream file = new java.io.FileOutputStream( "ObjectId" );
            java.io.PrintStream pfile = new java.io.PrintStream( file );
            pfile.println( ref );
            pfile.close() ;
        }
        catch ( java.io.IOException ex )
        {
            ex.printStackTrace( ) ;
            System.exit( 0 );
        }

        try
        {
            System.out.println( "The server is ready..." ) ;
            boa.impl_is_ready( ) ;
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            ex.printStackTrace() ;
        }
    }
} ;
