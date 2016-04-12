/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package interceptors;

public class ServerImpl extends ServerTestPOA
{
    public java.lang.String print( java.lang.String message )
    {
        return "Hello from the client side ( in response to " + message + " )";
    }

}
