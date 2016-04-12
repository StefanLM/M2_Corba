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
public class ValueGImpl extends ValueG
{
    public String toString()
    {
        return "(\"" 
               + name + "\","
               + idx
               + ","
               + ( ( left == null ) ? "()" : left.toString()
               + ( ( left.parent == this ) ? "*" : "" ) )
               + ","
               + ( ( middle == null ) ? "()" : middle.toString()
               + ( ( left.parent == this ) ? "*" : "" ) )
               + ","
               + ( ( right == null ) ? "()" : right.toString()
               + ( ( left.parent == this ) ? "*" : "" ) )
               + ")";
    }
}
