/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package interceptors;

public class ClientInterceptor
    extends org.omg.CORBA.LocalObject
    implements org.omg.PortableInterceptor.ClientRequestInterceptor
{

    public void send_request( org.omg.PortableInterceptor.ClientRequestInfo ri )
        throws org.omg.PortableInterceptor.ForwardRequest
    {
        System.out.println( "Invokes operation : " + ri.operation() );
    }

    public void send_poll( org.omg.PortableInterceptor.ClientRequestInfo ri )
    {
    }

    public void receive_reply( org.omg.PortableInterceptor.ClientRequestInfo ri )
    {
        System.out.println( "Receives a reply for the operation : " + ri.operation() );
    }

    public void receive_exception( org.omg.PortableInterceptor.ClientRequestInfo ri )
        throws org.omg.PortableInterceptor.ForwardRequest
    {
    }

    public void receive_other( org.omg.PortableInterceptor.ClientRequestInfo ri )
        throws org.omg.PortableInterceptor.ForwardRequest
    {
    }

    public String name()
    {
        return "ClientInterceptor";
    }

    public void destroy()
    {
    }
}

