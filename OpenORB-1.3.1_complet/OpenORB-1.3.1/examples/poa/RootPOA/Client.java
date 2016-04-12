/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package poa.RootPOA;

public class Client
{
    public static void main( String args[] )
    {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );

        ICalculator calc = null;

        org.omg.CORBA.Object obj = null;

        try
        {
            java.io.FileInputStream file = new java.io.FileInputStream( "ObjectId" );
            java.io.BufferedReader myInput = new java.io.BufferedReader(
                  new java.io.InputStreamReader( file ) );
            String stringTarget = myInput.readLine();

            obj = orb.string_to_object( stringTarget );

        }
        catch ( java.io.IOException ex )
        {
            System.out.println( "File error" );
            System.exit( 0 );
        }

        calc = ICalculatorHelper.narrow( obj );

        try
        {
            System.out.println( "5 + 3 = " + calc.add( 5, 3 ) );

            System.out.println( "5 / 0 = " + calc.div( 5, 0 ) );
        }
        catch ( DivByZero ex )
        {
            System.out.println( "A division by zero has been intercepted" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            System.out.println( "A CORBA System exception has been intercepted" );
            ex.printStackTrace();
        }

    }
}
