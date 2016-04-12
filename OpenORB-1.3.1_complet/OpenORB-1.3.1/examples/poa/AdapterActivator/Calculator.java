/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package poa.AdapterActivator;

public class Calculator
    extends ICalculatorPOA
{
    public float add( float nb1, float nb2 )
    {
        return nb1 + nb2;
    }

    public float div( float nb1, float nb2 )
        throws DivByZero
    {
        if ( nb2 == 0 )
            throw new DivByZero();

        return nb1 / nb2;
    }
}
