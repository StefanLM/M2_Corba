/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.orb.examples.messaging;

public class Client
{
    public static void main( String args[] )
    {
        java.util.Properties props = System.getProperties();
        props.put( "org.openorb.PI.FeatureInitializerClass."
              + "org.openorb.messaging.MessagingInitializer", "" );
        props.put( "org.omg.CORBA.ORBClass", "org.openorb.CORBA.ORB" );
        props.put( "org.omg.CORBA.ORBSingletonClass", "org.openorb.CORBA.ORBSingleton" );
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, props );

        org.omg.CORBA.Object obj = null;

        try
        {
            java.io.FileInputStream file = new java.io.FileInputStream( "ObjectId" );
            java.io.BufferedReader myInput = new java.io.BufferedReader(
                  new java.io.InputStreamReader ( file ) );
            String stringTarget = myInput.readLine();

            obj = orb.string_to_object( stringTarget );

        }
        catch ( java.io.IOException ex )
        {
            System.out.println( "File error" );
            System.exit( 1 );
        }

        int timeout_msec    = 6 * 1000;  // set timeout to 6s
        int wait_delta_msec = 2 * 1000;  // wait +-2s
        try
        {
            org.omg.CORBA.PolicyManager opm =
                ( org.omg.CORBA.PolicyManager )
                    orb.resolve_initial_references( "ORBPolicyManager" );

            org.omg.CORBA.Any time_any = orb.create_any();
            // convert from 1ms to 100ns
            org.omg.TimeBase.TimeTHelper.insert( time_any, timeout_msec * 1000 * 10 );
            org.omg.CORBA.Policy [] policies = new org.omg.CORBA.Policy[ 1 ];
            policies[ 0 ] = orb.create_policy(
                org.omg.Messaging.RELATIVE_RT_TIMEOUT_POLICY_TYPE.value,
                    time_any );
            opm.set_policy_overrides( policies,
                org.omg.CORBA.SetOverrideType.ADD_OVERRIDE );
        }
        catch ( org.omg.CORBA.ORBPackage.InvalidName invname_ex )
        {
            System.err.println( "ORBPolicyManager wasn't found! (" + invname_ex + ")" );
            System.exit( 1 );
        }
        catch ( org.omg.CORBA.PolicyError pol_ex )
        {
            System.err.println( "A policy error occured: " + pol_ex );
            System.exit( 1 );
        }
        catch ( org.omg.CORBA.InvalidPolicies invpol_ex )
        {
            System.err.println( "An invalid policy has been specified: " + invpol_ex );
            System.exit( 1 );
        }

        ITimeout time = ITimeoutHelper.narrow( obj );

        try
        {
            System.out.println("1) Calling server...");
            time.waitForTimeout( timeout_msec - wait_delta_msec );
            System.out.println("1) Returned from server!");
        }
        catch ( org.omg.CORBA.TIMEOUT ex )
        {
            System.out.println( "The TIMEOUT exception should not occur here!" );
        }

        try
        {
            System.out.println("2) Calling server...");
            time.waitForTimeout( timeout_msec + wait_delta_msec );
            System.out.println("2) Returned from server!");
        }
        catch ( org.omg.CORBA.TIMEOUT ex )
        {
            System.out.println( "The TIMEOUT exception occured as expected!" );
        }
    }
}

