/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.inheritance;

public class Client
{
    public static void main( String [] args )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );

        org.omg.CORBA.Object obj = null;

        try
        {
            java.io.FileInputStream file = new java.io.FileInputStream( "ObjectId" );
            java.io.InputStreamReader input = new java.io.InputStreamReader( file );
            java.io.BufferedReader reader = new java.io.BufferedReader( input );
            String ref = reader.readLine();

            obj = orb.string_to_object( ref ) ;
        }
        catch ( java.io.IOException ex )
        {
            System.out.println( "File error..." );
            System.exit( 0 );
        }

        ValueExchange value = ValueExchangeHelper.narrow( obj );

        try
        {
            aValueBaseImpl valueType = new aValueBaseImpl();

            valueType.public_state = "Example : Hello from client";

            valueType.private_state = 100;

            value.sendValueBase( valueType );

            aSubValueImpl subValue = new aSubValueImpl();

            subValue.public_state = "Example : I'm a client message";

            subValue.private_state = 200;

            subValue.a_public_state = 300;

            value.sendSubValue( subValue );

            value.sendValueBase( subValue );

        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            System.out.println( "A CORBA System exception has been intercepted" ) ;
        }
    }
}
