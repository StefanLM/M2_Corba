/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package poa.ServantLocator;

public class SrvLocator
    extends org.omg.CORBA.LocalObject
    implements org.omg.PortableServer.ServantLocator
{
    private org.omg.PortableServer.Servant m_servant;

    public SrvLocator()
    {
        m_servant = new Calculator();
    }

    public org.omg.PortableServer.Servant preinvoke( byte[] oid, org.omg.PortableServer.POA adapter,
          java.lang.String operation,
          org.omg.PortableServer.ServantLocatorPackage.CookieHolder the_cookie )
        throws org.omg.PortableServer.ForwardRequest
    {
        String oidStr = new String( oid );

        System.out.println( "[ Servant Locator ] preinvoke => method : " + operation
              + " / oid : " + oidStr );

        the_cookie.value = oidStr;

        return m_servant;
    }

    public void postinvoke( byte[] oid, org.omg.PortableServer.POA adapter,
          java.lang.String operation,
          java.lang.Object the_cookie, org.omg.PortableServer.Servant the_servant )
    {
        String oidStr = new String( oid );

        System.out.println( "[ Servant Locator ] postinvoke => method : " + operation
              + " / oid : " + oidStr );

        if ( !oidStr.equals( the_cookie ) )
            throw new org.omg.CORBA.OBJECT_NOT_EXIST();
    }
}

