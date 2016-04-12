/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package poa.DefaultServant;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class FileSystem extends IFileSystemPOA
{

    private static java.util.Vector s_list;

    public static java.util.Vector getList()
    {
        return s_list;
    }

    private org.omg.PortableServer.POA m_poa;

    public FileSystem( org.omg.PortableServer.POA poa )
    {
        m_poa = poa;
        s_list = new java.util.Vector();

        try
        {
            poa.set_servant ( new FileDescriptor( m_poa ) );
        }
        catch ( org.omg.PortableServer.POAPackage.WrongPolicy ex )
        {
            System.out.println( "Unable to set default servant because" );
            System.out.println( "this POA does not have the good policies" );
            System.exit( 0 );
        }
    }

    public IFileDescriptor open( java.lang.String file_name, int flags )
    {
        DataInputStream file;
        org.omg.CORBA.Object obj;

        try
        {
            file = new DataInputStream(
                       new BufferedInputStream(
                           new FileInputStream( file_name ) ) );
        }
        catch ( java.io.IOException ex )
        {
            System.out.println( "Unable to open file : " + file_name );
            return null;
        }

        s_list.addElement ( file );

        byte [] oid = ( new String( new Integer( s_list.size() ).toString() ) ).getBytes();

        obj = m_poa.create_reference_with_id( oid, "IDL:IFileDescriptor:1.0" );

        return IFileDescriptorHelper.narrow( obj );
    }
}
