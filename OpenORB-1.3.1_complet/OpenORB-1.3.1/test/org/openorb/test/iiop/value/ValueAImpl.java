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
public class ValueAImpl extends ValueA
{
    public ValueAImpl()
    {
        str = "";
    }

    public String toString()
    {
        return "ValueA (\"" + str + "\", " + l + ")";
    }
}
