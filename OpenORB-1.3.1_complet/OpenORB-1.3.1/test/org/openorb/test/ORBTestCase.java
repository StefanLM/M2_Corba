/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test;

import java.util.Properties;

import junit.framework.TestCase;

import org.openorb.io.HexPrintStream;

/**
 * Skeleton orb test case to use when testing an orb component. The pre_init and
 * post init functions deal with creating and destroying orb instances for the
 * client and server ends.
 *
 * @author Chris Wood
 */
public abstract class ORBTestCase
    extends TestCase
{
    public ORBTestCase( String name )
    {
        super( name );
    }

    /**
     * This method is called prior to calling run and basically starts 
     * up a server and client orb, and spawns a thread for the server orb
     * to run with.
     */
    protected void setUp()
    {
        setUp( null );
    }

    /**
     * Override setUp and call this method with alternative properties to
     * startup an orb with alternative parameters.
     */
    protected void setUp( Properties props )
    {
        if ( props == null )
            m_props = new Properties();
        else
            m_props = props;
        m_props.setProperty( "openorb.useStaticThreadGroup", "true" );
        String [] args = new String[ 0 ];
        m_serverORB = org.omg.CORBA.ORB.init( args, m_props );
        final java.util.Vector v = new java.util.Vector();
        m_serverThread = new Thread( new Runnable()
            {
                public void run()
                {
                    try
                    {
                        v.add( new Object() );
                        // The variable m_serverORB seems to be null from time to time,
                        // but that doesn't seem to influence the test case !!!!!!!!!!!
                        m_serverORB.run();
                    }
                    catch ( java.lang.Exception ex )
                    {
                        System.out.println( "THIS IS REALLY STRANGE!!!" );
                        ex.printStackTrace();
                    }
                }
            } );

        m_serverThread.start();
        try
        {
            while ( v.size() == 0 )
            {
                Thread.sleep( 100 );
            }
        }
        catch ( java.lang.InterruptedException ex )
        {
             // shouldn't happen
        }
    }


    /**
     * This method is called after calling run. It shuts down the server and 
     * client orbs.
     */
    protected void tearDown()
    {
        m_serverORB.shutdown( true );
        m_serverORB = null;

        try
        {
            m_serverThread.join( 20000 );
        }
        catch ( InterruptedException ex )
        {
            // catch to make javac happy
        }

        assertTrue( "ORBTestCase.postExecute: Unable to stop server orb",
              !m_serverThread.isAlive() );

        m_props = null;
    }

    /**
     * Restarts the server side orb.
     */
    protected org.omg.CORBA.ORB restartORB()
    {
        m_serverORB.shutdown( true );

        try
        {
            m_serverThread.join( 20000 );
        }
        catch ( InterruptedException ex )
        {
            // catch to make javac happy
        }

        assertTrue( "ORBTestCase.restartORB: Unable to stop server orb",
              !m_serverThread.isAlive() );

        String [] args = new String[ 0 ];

        m_serverORB = org.omg.CORBA.ORB.init( args, m_props );

        m_serverThread = new Thread( new Runnable()
                                    {
                                        public void run()
                                        {
                                            m_serverORB.run();
                                        }
                                    }
                                  );
        m_serverThread.start();

        return m_serverORB;
    }

    /**
     * Get the server side orb.
     */
    public org.omg.CORBA.ORB getORB()
    {
        return m_serverORB;
    }

    /**
     * Sets local invoke policy on target.
     */
    public org.omg.CORBA.Object forceMarshal( org.omg.CORBA.Object obj )
        throws org.omg.CORBA.PolicyError
    {
        if ( m_forcePolicy == null )
            m_forcePolicy = m_serverORB.create_policy( 
                  org.openorb.policy.FORCE_MARSHAL_POLICY_ID.value, m_serverORB.create_any() );

        return obj._set_policy_override( new org.omg.CORBA.Policy[] { m_forcePolicy },
              org.omg.CORBA.SetOverrideType.ADD_OVERRIDE );
    }

    /**
     * Write buffer as hex to given stream.
     */
    public static void writeVerboseHex( java.io.OutputStream stream, byte [] buf )
        throws java.io.IOException
    {
        HexPrintStream hps = new HexPrintStream( stream, HexPrintStream.FORMAT_MIXED );

        hps.write( buf );
        hps.flush();
    }

    private org.omg.CORBA.Policy m_forcePolicy;
    private org.omg.CORBA.ORB m_serverORB;
    private Thread m_serverThread;
    private Properties m_props;
}
