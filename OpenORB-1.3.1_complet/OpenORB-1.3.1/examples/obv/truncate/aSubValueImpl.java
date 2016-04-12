/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.truncate;

public class aSubValueImpl extends aSubValue
{

    public void print()
    {
        System.out.println( "Prints all public and private members" );
        System.out.println( "" );
        System.out.println( "Inherited from aValueBase : " );
        System.out.println( "Private member = " + private_state );
        System.out.println( "Public member = " + public_state );
        System.out.println( "" );
        System.out.println( "My own members :" );
        System.out.println( "Public member = " + a_public_state );
        System.out.println( "" );
    }

}
