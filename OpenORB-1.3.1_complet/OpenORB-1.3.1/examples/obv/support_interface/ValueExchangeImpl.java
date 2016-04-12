/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package obv.support_interface;

public class ValueExchangeImpl extends ValueExchangePOA
{
    private valueExample m_value;

    private MessagePOATie m_tie;

    public void prepare( org.omg.PortableServer.POA poa )
    {
        m_value = new valueExampleImpl();
        m_value.name_state = "Hello";

        m_tie = new MessagePOATie( m_value );

        try
        {
            poa.activate_object( m_tie ) ;
        }
        catch ( java.lang.Exception ex )
        {
            ex.printStackTrace();
        }
    }

    public Message getValueSupportedInterface()
    {
        System.out.println( ". " );
        System.out.println( ". Ask for a remote object" );
        System.out.println( ". " );

        return m_tie._this();
    }

    /**
     * Operation IDL 'getValueExample'
     */
    public valueExample getValueExample()
    {
        System.out.println( ". " );
        System.out.println( ". Ask for a local object" );
        System.out.println( ". " );

        return m_value;
    }
}
