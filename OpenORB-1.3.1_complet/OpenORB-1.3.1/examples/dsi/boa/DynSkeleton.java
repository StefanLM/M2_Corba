/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package dsi.boa;

public class DynSkeleton extends org.omg.CORBA.DynamicImplementation
{
    static final String[] ID_LIST =
        {
            "IDL:dsi/boa/Sample:1.0"
        };

    public String[ ] _ids()
    {
        return ID_LIST;
    }

    private org.omg.CORBA.ORB m_orb;

    public DynSkeleton( org.omg.CORBA.ORB orb )
    {
        m_orb = orb;
    }

    public void invoke ( org.omg.CORBA.ServerRequest request )
    {
        // find which operation is being invoked.
        String operation = request.operation();

        if ( operation.equals( "div" ) )
        {
            // create the argument list.
            org.omg.CORBA.NVList argList = m_orb.create_list( 0 );

            // first float argument
            org.omg.CORBA.Any arg0 = m_orb.create_any();
            arg0.type( m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float ) );
            argList.add_value( "", arg0, org.omg.CORBA.ARG_IN.value );

            // second float argument
            org.omg.CORBA.Any arg1 = m_orb.create_any();
            arg1.type( m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float ) );
            argList.add_value( "", arg1, org.omg.CORBA.ARG_IN.value );

            // set the arguments for the request. This will result in the arguments
            // being unmarshalled.
            request.arguments( argList );

            // extract the arguements
            float nb1 = arg0.extract_float();
            float nb2 = arg1.extract_float();

            try
            {
                // can't divide by zero.
                if ( nb2 == 0 )
                    throw new DivByZero();

                float resultat = nb1 / nb2 ;

                // create the result argument.
                org.omg.CORBA.Any any_result = m_orb.create_any();

                any_result.insert_float( resultat );

                // send the result
                request.set_result( any_result );

            }
            catch ( DivByZero ex )
            {
                // we throw an exception.
                org.omg.DynamicAny.DynAnyFactory factory = null;

                try
                {
                    org.omg.CORBA.Object obj = null;
                    obj = m_orb.resolve_initial_references( "DynAnyFactory" );

                    factory =
                        org.omg.DynamicAny.DynAnyFactoryHelper.narrow( obj );
                }
                catch ( org.omg.CORBA.ORBPackage.InvalidName e )
                {
                    System.out.println( "DynAnyFactory not available!" );
                    System.exit( 1 );
                }

                // The DivByZero exception has no arguments


                org.omg.CORBA.StructMember [] members = null;

                members = new org.omg.CORBA.StructMember[ 0 ];

                org.omg.CORBA.TypeCode tc = m_orb.create_exception_tc(
                      "IDL:dsi/boa/DivByZero:1.0", "DivByZero", members );

                org.omg.DynamicAny.DynAny dany = null;

                try
                {
                    dany = factory.create_dyn_any_from_type_code( tc );
                }
                catch ( org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode e )
                {
                    System.out.println( "Couldn't create dynany from typecode!" );
                    System.exit( 1 );
                }

                org.omg.CORBA.Any any_ex = dany.to_any();

                request.set_exception( any_ex );
            }
        }
        else
            throw new org.omg.CORBA.BAD_OPERATION();
    }
}

