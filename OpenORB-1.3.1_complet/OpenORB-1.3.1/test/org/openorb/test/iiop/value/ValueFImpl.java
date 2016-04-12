/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.iiop.value;

/**
 * @author Chris Wood
 */
public class ValueFImpl extends ValueF
{
    public void unmarshal( org.omg.CORBA.DataInputStream is )
    {
        l = Integer.parseInt( is.read_string() );
    }

    public void marshal( org.omg.CORBA.DataOutputStream os )
    {
        os.write_string( Integer.toString( l ) );
    }

    public String toString()
    {
        return "ValueF ( " + l + ")";
    }

}
