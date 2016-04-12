/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.support_abstract;

public class ValueExchangeImpl extends ValueExchangePOA
{
    private valueExample m_value;

    private ExampleImpl m_object;

    public void prepare( org.omg.PortableServer.POA poa )
    {
        m_value = new valueExampleImpl();
        m_value.name_state = "Hello";

        m_object = new ExampleImpl();

        try
        {
            poa.activate_object( m_object ) ;
        }
        catch ( Exception ex )
        {
            System.out.println( "Couldn't activate object: " + ex );
            System.exit( 1 );
        }
    }

    public AnAbstractInterface getInterface( boolean byValue )
    {
        if ( byValue )
            return m_value;
        else
            return m_object._this();
    }
}
