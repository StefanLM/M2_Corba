/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.CORBA;

import junit.framework.TestCase;

import java.util.Properties;

/**
 * Skeleton orb test case to use when testing an orb component. The pre_init and
 * post init functions deal with creating and destroying orb instances for the
 * client and server ends.
 *
 * @author Chris Wood
 */
public abstract class CORBATestCase
    extends TestCase
{
    public CORBATestCase( String name )
    {
        super( name );
    }

    private org.omg.CORBA.ORB m_orb;

    /**
     * This method is called prior to calling run and basically starts 
     * up a server and client orb, and spawns a thread for the server orb
     * to run with.
     */
    protected void setUp()
    {
        Properties props = new Properties();
        props.setProperty( "openorb.useStaticThreadGroup", "true" );
        props.setProperty( "openorb.server.enable", "false" );
        m_orb = org.omg.CORBA.ORB.init( ( String[] ) null, props );
    }


    /**
     * This method is called after calling run. It shuts down the server and 
     * client orbs.
     */
    protected void tearDown()
    {
        m_orb.shutdown( true );
    }

    /**
     * Get the server side orb.
     */
    public org.omg.CORBA.ORB getORB()
    {
        return m_orb;
    }
}
