/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.custom;

public class valueExampleImpl extends valueExample
{
    public int number()
    {
        return number_state;
    }

    public void print()
    {
        System.out.println( "Here is the 'print' operation" );
        System.out.println( "It's a local operation" );
        System.out.println( "" );
        System.out.println( "Private member value = " + number_state );
        System.out.println( "Public member value = " + name_state );
        System.out.println( "" );
    }

    // ----------------------------------------------------
    //
    // Additional operation for custom marshaling
    //
    // ----------------------------------------------------

    public void marshal( org.omg.CORBA.DataOutputStream os )
    {
        System.out.println( "Invoke the marshal operation..." );
        System.out.println( "" );

        os.write_string( "Here is an additional message in the marshalling" );
        os.write_long( number_state );
        os.write_string( name_state );
    }

    public void unmarshal( org.omg.CORBA.DataInputStream is )
    {
        System.out.println( "Invoke the unmarshal operation..." );
        System.out.println( is.read_string() );
        System.out.println( "" );

        number_state = is.read_long();
        name_state = is.read_string();
    }
}
