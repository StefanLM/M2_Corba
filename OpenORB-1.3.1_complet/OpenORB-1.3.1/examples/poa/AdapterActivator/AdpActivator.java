/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package poa.AdapterActivator;

public class AdpActivator
    extends org.omg.CORBA.LocalObject
    implements org.omg.PortableServer.AdapterActivator
{
    public boolean unknown_adapter ( org.omg.PortableServer.POA parent, String name )
    {
        System.out.println( "I am the AdapterActivator called by POA " + parent.the_name()
              + " to create " + name );

        org.omg.CORBA.Policy[] policies = new org.omg.CORBA.Policy[ 2 ];

        policies[ 0 ] = parent.create_lifespan_policy(
              org.omg.PortableServer.LifespanPolicyValue.PERSISTENT );

        policies[ 1 ] = parent.create_id_assignment_policy(
              org.omg.PortableServer.IdAssignmentPolicyValue.USER_ID );

        org.omg.PortableServer.POA childPOA = null;

        try
        {
            childPOA = parent.create_POA( name, parent.the_POAManager(), policies );
        }
        catch ( java.lang.Exception e )
        {
            return false;
        }

        childPOA.the_activator( parent.the_activator() );

        if ( name.equals( "secondPOA" ) )
        {
            System.out.println( "Creates the Servant" );

            Calculator calc = new Calculator();

            byte [] servantId = "myObject".getBytes();

            try
            {
                childPOA.activate_object_with_id( servantId, calc );
            }
            catch ( java.lang.Exception ex )
            {
                return false;
            }
        }
        return true;
    }
}
