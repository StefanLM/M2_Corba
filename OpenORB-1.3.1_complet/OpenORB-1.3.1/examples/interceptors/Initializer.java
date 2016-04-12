/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package interceptors;

public class Initializer
    extends org.omg.CORBA.LocalObject
    implements org.omg.PortableInterceptor.ORBInitializer
{
    public void pre_init( org.omg.PortableInterceptor.ORBInitInfo info )
    {
        ClientInterceptor clientInterceptor = new ClientInterceptor();
        ServerInterceptor serverInterceptor = new ServerInterceptor();

        try
        {
            info.add_client_request_interceptor( clientInterceptor );
            info.add_server_request_interceptor( serverInterceptor );
        }
        catch ( org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName dn )
        {
            System.out.println( "DuplicateName" );
        }
    }

    public void post_init( org.omg.PortableInterceptor.ORBInitInfo info )
    { }

}
