/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.custom;

public class ValueExchangeImpl extends ValueExchangePOA
{
    public void sendValueExample( valueExample value )
    {
        System.out.println( "I received a value" );
        System.out.println( "" );
        System.out.println( "number = " + value.number() );
        System.out.println( "" );

        value.print();
    }
}
