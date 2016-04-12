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
public class ValueEImpl
    extends ValueE
{
    /**
     * Creates new ValueEImpl 
     */
    public ValueEImpl( String notransport )
    {
        m_no_transport = notransport;
    }

    private String m_no_transport;

    public void print()
    {
        System.out.println( prefix + "\" untransported state: \"" + m_no_transport + "\"" );
    }
}
