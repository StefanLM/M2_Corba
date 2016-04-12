/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.valuetype;

public class AccountImpl extends Account
{
    public void credit( float amount )
    {
        balance += amount ;
        System.out.println( "Balance after debit = " + balance ) ;
    }

    public void debit( float amount )
    {
        balance -= amount ;
        System.out.println( "Balance after debit = " + balance ) ;
    }
}
