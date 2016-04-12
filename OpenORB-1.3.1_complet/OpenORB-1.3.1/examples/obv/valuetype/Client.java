/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.valuetype;

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

        IBank bank = IBankHelper.narrow( obj );

        AccountDefaultFactory factory = new AccountDefaultFactory() ;

        ( ( org.omg.CORBA_2_3.ORB ) orb ).register_value_factory( AccountHelper.id(), factory );

        try
        {
            Account cpt = bank.create_account( "John", "1 mission street", 5000 ) ;

            cpt.credit( 2000 ) ;

            cpt.debit( 100 ) ;
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            System.out.println( "A CORBA System exception has been intercepted" ) ;
        }
    }
}
