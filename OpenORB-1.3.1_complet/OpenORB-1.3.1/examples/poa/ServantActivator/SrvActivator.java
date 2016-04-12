/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package poa.ServantActivator;

public class SrvActivator
            extends org.omg.CORBA.LocalObject
            implements org.omg.PortableServer.ServantActivator
{
    public org.omg.PortableServer.Servant incarnate(
        byte[] oid,
        org.omg.PortableServer.POA adapter )
    throws org.omg.PortableServer.ForwardRequest
    {
        String oid_str = new String( oid );

        if ( oid_str.equals( "MyOwnId" ) )
        {
            return new Calculator();
        }
        else
            throw new org.omg.CORBA.OBJECT_NOT_EXIST();
    }

    public void etherealize(
        byte[] oid,
        org.omg.PortableServer.POA adapter,
        org.omg.PortableServer.Servant serv,
        boolean cleanup_in_progress,
        boolean remaining_activations )
    {
        if ( remaining_activations == false )
            serv = null;
    }
}

