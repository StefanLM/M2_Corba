/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package poa.DefaultServant;

import java.io.DataInputStream;

public class FileDescriptor
    extends IFileDescriptorPOA
{
    private org.omg.PortableServer.POA m_poa;

    public FileDescriptor( org.omg.PortableServer.POA poa )
    {
        m_poa = poa;
    }

    public DataInputStream getDescriptor()
    {
        byte [] oid = null;

        try
        {
            org.omg.CORBA.Object current_obj = _orb().resolve_initial_references( "POACurrent" );

            org.omg.PortableServer.Current current =
                  org.omg.PortableServer.CurrentHelper.narrow( current_obj );

            oid = current.get_object_id();
        }
        catch ( org.omg.CORBA.ORBPackage.InvalidName ex )
        {
            System.out.println( "Unable to get context" );
            System.exit( 1 );
        }
        catch ( org.omg.PortableServer.CurrentPackage.NoContext ex )
        {
            System.out.println( "Unable to get context" );
            System.exit( 1 );
        }

        String id = new String( oid );

        int num = new Integer( id ).intValue();

        return ( DataInputStream ) FileSystem.getList().elementAt( num - 1 );
    }

    public int write( byte[] buffer )
    {
        return 0;
    }

    public byte[] read( int num_bytes )
    {
        DataInputStream input = getDescriptor();

        byte [] buffer = new byte[ num_bytes ];

        try
        {
            input.read( buffer, 0, num_bytes );
        }
        catch ( java.io.IOException ex )
        {
            System.out.println( "Unable to read some data" );
            return null;
        }

        return buffer;
    }

    public void destroy()
    {
        try
        {
            m_poa.deactivate_object( _object_id() );
        }
        catch ( Exception ex )
        {
            System.err.println( "Exception wile destroying object: " + ex );
            System.exit( 1 );
        }
    }
}

