/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.valuetype;

public class AccountDefaultFactory implements AccountValueFactory
{
    public Account init( String name, String address, float balance )
    {
        AccountImpl cpt = new AccountImpl( ) ;

        cpt.name = name ;
        cpt.address = address ;
        cpt.balance = balance ;

        return cpt ;
    }

    public java.io.Serializable read_value( org.omg.CORBA_2_3.portable.InputStream is )
    {
        AccountImpl cpt = new AccountImpl( ) ;

        cpt = ( AccountImpl ) is.read_value( cpt ) ;

        return cpt ;
    }

}
