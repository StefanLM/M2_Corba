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
public class ValueBDefaultFactory implements ValueBValueFactory
{
    public ValueB init( int l )
    {
        return new ValueBImpl( l );
    }

    public java.io.Serializable read_value( org.omg.CORBA_2_3.portable.InputStream is )
    {
        ValueB val = new ValueBImpl();
        is.read_value( val );
        return val;
    }
}
