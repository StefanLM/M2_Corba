/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.valuebox;

public class ValueExchange extends IValueExchangePOA
{
    public void sendLongBox( longBox box )
    {
        if ( box == null )
        {
            System.out.println( ". " );
            System.out.println( ". I received a NULL value box : longBox" );
            System.out.println( ". " );
        }
        else
        {
            System.out.println( ". " );
            System.out.println( ". I received a value box : longBox" );
            System.out.println( ". Its value is : " + box.value );
            System.out.println( ". " );
        }
    }

    public void sendLongSeqBox( int [] box )
    {
        if ( box == null )
        {
            System.out.println( ". " );
            System.out.println( ". I received a NULL value for a complexe value box: longSeqBox" );
            System.out.println( ". " );
        }
        else
        {
            System.out.println( ". " );
            System.out.println( ". I received a complexe value box : longSeqBox" );
            System.out.println( ". Its value is : " );

            for ( int i = 0; i < box.length; i++ )
                System.out.println( ". box[" + i + "] = " + box[ i ] );

            System.out.println( ". " );
        }
    }

}
