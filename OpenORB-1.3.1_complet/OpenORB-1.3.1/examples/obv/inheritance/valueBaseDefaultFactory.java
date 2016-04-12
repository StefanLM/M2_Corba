/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.inheritance;

public class valueBaseDefaultFactory implements org.omg.CORBA.portable.ValueFactory
{
    public java.io.Serializable read_value( org.omg.CORBA_2_3.portable.InputStream is )
    {
        aValueBaseImpl value = new aValueBaseImpl();

        value = ( aValueBaseImpl ) is.read_value( value );

        return value;
    }
}
