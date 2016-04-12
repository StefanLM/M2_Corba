/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.orb.examples.messaging;

public class Timeout extends ITimeoutPOA
{
    public void waitForTimeout( int msec )
    {
        try
        {
            System.out.println("Sleeping " + msec + " msec now...");
            Thread.sleep( msec );
            System.out.println("Finished sleeping!!");
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }
}

