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

public class DynamicScenarioTest
    extends ORBTestCase
{
    public DynamicScenarioTest( String name )
    {
        super( name );
    }

    public void setUp()
    {
        super.setUp();

        try
        {
            m_orb = getORB();

            org.omg.CORBA.Object obj = m_orb.resolve_initial_references( "DynAnyFactory" );

            m_dyn_factory = org.omg.DynamicAny.DynAnyFactoryHelper.narrow( obj );

            POA rootPOA = ( POA ) m_orb.resolve_initial_references( "RootPOA" );

            DIITarget svr_ref = ( new DIITargetImpl( rootPOA ) )._this( m_orb );

            rootPOA.the_POAManager().activate();

            m_cltRef = forceMarshal( svr_ref );
        }
        catch ( org.omg.CORBA.UserException ex )
        {
            fail( "exception during setup:" + ex.toString() );
        }
    }

    private org.omg.CORBA.ORB m_orb;
    private org.omg.CORBA.Object m_cltRef;

    private org.omg.DynamicAny.DynAnyFactory m_dyn_factory;

    /**
     * This test creates thanks to DynAny a sequence and then sends it to a CORBA Server by
     * using DII.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testDIISequence() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.Request req = m_cltRef._request( "sequenceTest" );

        org.omg.CORBA.Any any = req.add_in_arg();

        org.omg.DynamicAny.DynSequence dyn_seq, dyn_res;

        org.omg.CORBA.TypeCode tc_seq = m_orb.create_alias_tc(
              "IDL:openorb.org/test/dynamic/StringSeq:1.0", "StringSeq",
              m_orb.create_sequence_tc( 0,
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string ) ) );

        dyn_seq = ( org.omg.DynamicAny.DynSequence )
              m_dyn_factory.create_dyn_any_from_type_code( tc_seq );

        dyn_seq.set_length( 2 );

        org.omg.CORBA.Any [] values = new org.omg.CORBA.Any[ 2 ];
        values[ 0 ] = m_orb.create_any();
        values[ 1 ] = m_orb.create_any();
        values[ 0 ].insert_string( "value 0" );
        values[ 1 ].insert_string( "value 1" );

        try
        {
            dyn_seq.set_elements( values );
        }
        catch ( java.lang.Exception ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        any.type( dyn_seq.type() );

        any.read_value( dyn_seq.to_any().create_input_stream(), dyn_seq.type() );

        req.set_return_type( tc_seq );

        req.invoke();

        org.omg.CORBA.Any rvalue = req.return_value();

        dyn_res = ( org.omg.DynamicAny.DynSequence )
              m_dyn_factory.create_dyn_any_from_type_code( tc_seq );

        dyn_res.from_any( rvalue );

        if ( dyn_res.get_length() != 2 )
            fail( "Invalid sequence received" );
    }

    /**
     * This test creates thanks to DynAny a struct and then sends it to a CORBA Server by
     * using DII.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testDIIStruct() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.Request req = m_cltRef._request( "structTest" );

        org.omg.CORBA.Any any = req.add_in_arg();

        org.omg.DynamicAny.DynStruct dyn_param, dyn_res;

        org.omg.CORBA.StructMember [] members = new org.omg.CORBA.StructMember[ 3 ];

        members[ 0 ] = new org.omg.CORBA.StructMember( "firstname",
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string ), null );
        members[ 1 ] = new org.omg.CORBA.StructMember( "surname",
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string ), null );
        members[ 2 ] = new org.omg.CORBA.StructMember( "age",
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_long ), null );

        org.omg.CORBA.TypeCode tc_struct =
              m_orb.create_struct_tc( "IDL:openorb.org/test/dynamic/Person:1.0",
              "Person", members );

        dyn_param = ( org.omg.DynamicAny.DynStruct )
              m_dyn_factory.create_dyn_any_from_type_code( tc_struct );

        org.omg.DynamicAny.NameValuePair[] values = new org.omg.DynamicAny.NameValuePair[ 3 ];
        org.omg.CORBA.Any val1 = m_orb.create_any();
        val1.insert_string( "joe" );
        values[ 0 ] = new org.omg.DynamicAny.NameValuePair( "firstname", val1 );
        org.omg.CORBA.Any val2 = m_orb.create_any();
        val2.insert_string( "bob" );
        values[ 1 ] = new org.omg.DynamicAny.NameValuePair( "surname", val2 );
        org.omg.CORBA.Any val3 = m_orb.create_any();
        val3.insert_long( 50 );
        values[ 2 ] = new org.omg.DynamicAny.NameValuePair( "age", val3 );

        try
        {
            dyn_param.set_members( values );
        }
        catch ( java.lang.Exception ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        any.type( dyn_param.type() );

        any.read_value( dyn_param.to_any().create_input_stream(), dyn_param.type() );

        req.set_return_type( tc_struct );

        req.invoke();

        org.omg.CORBA.Any rvalue = req.return_value();

        dyn_res = ( org.omg.DynamicAny.DynStruct )
              m_dyn_factory.create_dyn_any_from_type_code( tc_struct );

        dyn_res.from_any( rvalue );

        if ( dyn_res.get_members().length != 3 )
            fail( "Invalid struct received" );
    }

    /**
     * Servant implementation used for tests.
     */

    public static class DIITargetImpl extends DIITargetPOA
    {
        private String m_name = "TargetRange";

        public DIITargetImpl( org.omg.PortableServer.POA poa )
        {
        }

        public void hello()
        {
        }

        public void hello_oneway()
        {
        }

        public String message()
        {
            return "Hello from the server";
        }

        public String name()
        {
            return m_name;
        }

        public void name( String n )
        {
            m_name = n;
        }

        public float add( float nb1, float nb2 )
        {
            return nb1 + nb2;
        }

        public float divide( float nb1, float nb2 )
            throws org.openorb.test.dynamic.DIITargetPackage.DivideByZero
        {
            if ( nb2 == 0 )
                throw new org.openorb.test.dynamic.DIITargetPackage.DivideByZero();

            return nb1 / nb2;
        }

        public void clauseContext( org.omg.CORBA.Context ctx )
        {
            org.omg.CORBA.NVList nv = null;

            try
            {
                nv = ctx.get_values( "", 0, "Testing" );
            }
            catch ( org.omg.CORBA.BAD_CONTEXT ex )
            {
                return ;
            }

            try
            {
                org.omg.CORBA.NamedValue n = nv.item( 0 );
                org.omg.CORBA.Any any = n.value();

                any.extract_string();
            }
            catch ( org.omg.CORBA.Bounds ex )
            {
                fail( "No value found: " + ex );
            }

        }

        public String [] sequenceTest( String [] seq )
        {
            return seq;
        }

        public org.openorb.test.dynamic.Person structTest( org.openorb.test.dynamic.Person p )
        {
            return p;
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( DynamicScenarioTest.class ) );
    }
}

