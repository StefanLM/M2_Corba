/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package interceptors;

public class ServerInterceptor
    extends org.omg.CORBA.LocalObject
    implements org.omg.PortableInterceptor.ServerRequestInterceptor
{
    public void receive_request_service_contexts(
          org.omg.PortableInterceptor.ServerRequestInfo ri )
        throws org.omg.PortableInterceptor.ForwardRequest
    {
    }

    public void receive_request( org.omg.PortableInterceptor.ServerRequestInfo ri )
        throws org.omg.PortableInterceptor.ForwardRequest
    {
        System.out.println( "Receives a request for an object : "
              + ri.target_most_derived_interface() + ", operation : " + ri.operation() );
    }

    public void send_reply( org.omg.PortableInterceptor.ServerRequestInfo ri )
    {
        System.out.println( "Sends a reply for operation : " + ri.operation() );
    }

    public void send_exception( org.omg.PortableInterceptor.ServerRequestInfo ri )
        throws org.omg.PortableInterceptor.ForwardRequest
    {
    }

    public void send_other( org.omg.PortableInterceptor.ServerRequestInfo ri )
        throws org.omg.PortableInterceptor.ForwardRequest
    {
    }

    public java.lang.String name()
    {
        return "ServerInterceptor";
    }

    public void destroy()
    {
    }
}

