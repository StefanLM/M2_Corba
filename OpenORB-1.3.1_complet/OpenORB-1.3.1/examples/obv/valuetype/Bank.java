/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.valuetype;

public class Bank extends IBankPOA
{
    public Account create_account( String name, String address, float balance )
    {
        AccountDefaultFactory factory = new AccountDefaultFactory() ;

        return factory.init( name, address, balance ) ;
    }
}
