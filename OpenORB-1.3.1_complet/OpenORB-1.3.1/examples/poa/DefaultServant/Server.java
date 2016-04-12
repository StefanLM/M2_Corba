/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package poa.DefaultServant;

public class Server
{
    public static void main( String args[] )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );

        org.omg.PortableServer.POA rootPOA = null;

        try
        {
            org.omg.CORBA.Object objPoa = orb.resolve_initial_references( "RootPOA" );

            rootPOA = org.omg.PortableServer.POAHelper.narrow( objPoa );
        }
        catch ( org.omg.CORBA.ORBPackage.InvalidName ex )
        {
            System.out.println( "Couldn't find RootPOA!" );
            System.exit( 1 );
        }

        try
        {
            org.omg.CORBA.Policy [] policies = new org.omg.CORBA.Policy[ 3 ];

            policies[ 0 ] =
                rootPOA.create_id_uniqueness_policy(
                    org.omg.PortableServer.IdUniquenessPolicyValue.MULTIPLE_ID );

            policies[ 1 ] =
                rootPOA.create_request_processing_policy(
                    org.omg.PortableServer.RequestProcessingPolicyValue.USE_DEFAULT_SERVANT );

            policies[ 2 ] =
                rootPOA.create_id_assignment_policy(
                    org.omg.PortableServer.IdAssignmentPolicyValue.USER_ID );

            org.omg.PortableServer.POA childPOA = rootPOA.create_POA(
                 "MyPOA", rootPOA.the_POAManager(), policies );

            FileSystem fileSys = new FileSystem( childPOA );

            byte [] servantId = ( new String( "FileSystem" ) ).getBytes();

            childPOA.activate_object_with_id( servantId, fileSys );

            org.omg.CORBA.Object obj = childPOA.id_to_reference( servantId );

            String reference = orb.object_to_string( obj );

            try
            {
                java.io.FileOutputStream file = new java.io.FileOutputStream( "ObjectId" );
                java.io.PrintStream pfile = new java.io.PrintStream( file );
                pfile.println( reference );
            }
            catch ( java.io.IOException ex )
            {
                System.out.println( "File error !" );
            }

            rootPOA.the_POAManager().activate();

            System.out.println( "The server is ready..." );

            orb.run();
        }
        catch ( java.lang.Exception ex )
        {
            System.out.println( "An exception has been intercepted" );
        }

    }
}
