/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package dsi.boa;

public class DynServer
{
    public static void main( String [] args )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );

        org.omg.CORBA.BOA boa = org.omg.CORBA.BOA.init( orb, args ) ;

        DynSkeleton skeleton = new DynSkeleton( orb );

        boa.connect( skeleton ) ;

        boa.obj_is_ready( skeleton ) ;

        try
        {
            String ref = orb.object_to_string( skeleton ) ;
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
            System.out.println( " The server is ready..." ) ;
            boa.impl_is_ready( ) ;
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            ex.printStackTrace() ;
        }
    }
}
