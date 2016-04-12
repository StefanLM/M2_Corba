/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.support_interface;

public class valueExampleImpl extends valueExample
{
    public void print()
    {
        System.out.println( ". " );
        System.out.println( ". Do I local or remote ?" );
        System.out.println( ". " );
    }

    public void printName()
    {
        System.out.println( ". Operation 'printName'" );
        System.out.println( ". " );
        System.out.println( ". Member value = " + name_state );
        System.out.println( ". " );
    }
}
