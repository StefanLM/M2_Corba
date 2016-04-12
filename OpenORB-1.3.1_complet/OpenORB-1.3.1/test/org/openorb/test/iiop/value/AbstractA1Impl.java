/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.iiop.value;

import org.omg.PortableServer.POA;

/**
 * @author Chris Wood
 */
public class AbstractA1Impl extends AbstractA1POA
{
    public AbstractA1Impl( POA poa )
    {
        m_poa = poa;
    }

    private POA m_poa;

    public POA _default_POA()
    {
        return m_poa;
    }

    public void print()
    {
        System.out.println( "Remote invocation on AbstractA1Impl" );
    }
}
