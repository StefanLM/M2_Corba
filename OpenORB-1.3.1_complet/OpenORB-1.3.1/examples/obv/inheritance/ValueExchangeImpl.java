/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.inheritance;

public class ValueExchangeImpl extends ValueExchangePOA
{
    public void sendValueBase( aValueBase value )
    {
        System.out.println( "I received a value type" );
        System.out.println( "" );
        System.out.println( "Public member = " + value.public_state );
        System.out.println( "" );
    }

    public void sendSubValue( aSubValue value )
    {
        System.out.println( "I received a sub value type" );
        System.out.println( "" );

        value.print();
    }
}
