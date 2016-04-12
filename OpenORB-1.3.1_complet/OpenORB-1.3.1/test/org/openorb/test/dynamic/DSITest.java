/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.dynamic;

import junit.framework.TestSuite;

import org.omg.PortableServer.POA;

import org.openorb.test.ORBTestCase;

public class DSITest
    extends ORBTestCase
{
    public DSITest( String name )
    {
        super( name );
    }

    public void setUp()
    {
        java.util.Properties props = new java.util.Properties();

        // set known iiop port since persistent references are used.
        props.setProperty( "iiop.port", "17847" );
        props.setProperty( "ssliop.port", "17848" );
        props.setProperty( "ImportModule.BOA", "${openorb.home}config/default.xml#BOA" );

        setUp( props );
    }

    /**
     * Test the Dynamic Skeleton Interface by invoking an operation including
     * parameters, result and exception on the DSI servant.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testPOAInvocation() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.ORB orb = getORB();
        POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );
        rootPOA.the_POAManager().activate();

        TargetImplPOA svr_ref = new TargetImplPOA( orb );
        rootPOA.activate_object( svr_ref );

        doTest( svr_ref._this_object() );
    }

    /**
     * This method invokes the request.
     */
    private void doTest( org.omg.CORBA.Object srvRef ) throws org.omg.CORBA.UserException
    {
        DSITarget obj = DSITargetHelper.narrow( forceMarshal( srvRef ) );

        try
        {
            float res = obj.divide( ( float ) 3.0, ( float ) 2.0 );
            obj.divide( ( float ) 3.0, ( float ) 0 );
        }
        catch ( org.openorb.test.dynamic.DSITargetPackage.DivideByZero ex )
        {
            // expected.
        }

    }

    /**
     * Servant implementation used for testing. This Servant extends the 
     * org.omg.PortableServer.DynamicImplementation class.
     *
     * @see org.omg.PortableServer.DynamicImplementation
     */

    public static class TargetImplPOA extends org.omg.PortableServer.DynamicImplementation
    {

        private org.omg.CORBA.ORB m_orb;

        public TargetImplPOA( org.omg.CORBA.ORB orb )
        {
            m_orb = orb;
        }

        private String[] m_id_list = { "IDL:openorb.org/test/dynamic/DSITarget:1.0" };

        /**
         * This operation returns the IDL interfaces that are implemented by the Servant. These 
         * are interfaces the Servant can be narrowed to and operations can be invoked from. 
         */
        public String[ ] _all_interfaces( org.omg.PortableServer.POA poa, byte [] objectId )
        {
            return m_id_list;
        }

        /**
         * This methos gets the invoked operation name (via request.operation() ).
         * Depending on the operation name, the params are extracted from the request and
         * are processed. Then depending on the params, either a new execption is created
         * and thrown or the result is sent back.
         */
        public void invoke ( org.omg.CORBA.ServerRequest request )
        {
            org.omg.CORBA.ORB orb = m_orb;
            String operation = request.operation();

            assertTrue( "Problem with operation name", request.operation().equals( operation ) );

            if ( operation.equals( "divide" ) )
            {
                org.omg.CORBA.NVList argList = orb.create_list( 0 );

                org.omg.CORBA.Any arg0 = orb.create_any();
                arg0.type( orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float ) );
                argList.add_value( "", arg0, org.omg.CORBA.ARG_IN.value );

                org.omg.CORBA.Any arg1 = orb.create_any();
                arg1.type( orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float ) );
                argList.add_value( "", arg1, org.omg.CORBA.ARG_IN.value );

                request.arguments( argList );

                request.ctx();

                float nb1 = arg0.extract_float();
                float nb2 = arg1.extract_float();

                try
                {
                    if ( nb2 == 0 )
                        throw new org.openorb.test.dynamic.DIITargetPackage.DivideByZero();

                    float resultat = nb1 / nb2 ;

                    org.omg.CORBA.Any any_result = orb.create_any();

                    any_result.insert_float( resultat );

                    request.set_result( any_result );

                }
                catch ( org.openorb.test.dynamic.DIITargetPackage.DivideByZero ex )
                {
                    org.omg.DynamicAny.DynAnyFactory factory = null;

                    try
                    {
                        org.omg.CORBA.Object obj = null;
                        obj = orb.resolve_initial_references( "DynAnyFactory" );

                        factory = org.omg.DynamicAny.DynAnyFactoryHelper.narrow( obj );
                    }
                    catch ( org.omg.CORBA.ORBPackage.InvalidName e )
                    {
                        fail( "Unexpected exception caught: " + e );
                    }

                    org.omg.CORBA.StructMember [] members = null;

                    members = new org.omg.CORBA.StructMember[ 0 ];

                    org.omg.CORBA.TypeCode tc = orb.create_exception_tc(
                          "IDL:openorb.org/test/dynamic/DSITarget/DivideByZero:1.0",
                          "DivideByZero", members );
                    org.omg.DynamicAny.DynAny dany = null;

                    try
                    {
                        dany = factory.create_dyn_any_from_type_code( tc );
                    }
                    catch ( org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode e )
                    {
                        fail( "Unexpected exception caught: " + e );
                    }

                    org.omg.CORBA.Any any_ex = dany.to_any();

                    request.set_exception( any_ex );

                }
            }
            else
                throw new org.omg.CORBA.BAD_OPERATION();
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( DSITest.class ) );
    }
}
