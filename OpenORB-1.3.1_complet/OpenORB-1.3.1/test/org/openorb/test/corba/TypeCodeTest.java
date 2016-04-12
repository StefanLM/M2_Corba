/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.CORBA;

import junit.framework.TestSuite;

public class TypeCodeTest
    extends CORBATestCase
{
    public TypeCodeTest( String name )
    {
        super( name );
    }

    public void setUp()
    {
        super.setUp();

        m_orb = getORB();
        m_any = m_orb.create_any();
    }

    private org.omg.CORBA.ORB m_orb;
    private org.omg.CORBA.Any m_any;

    /**
     * Test the primitive TypeCodes operations. The test creates a short
     * primitive TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCPrimative() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_char );

        doTests( tc );
    }


    /**
     * Test the string TypeCode operations. The test creates a string
     * TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCString() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_string_tc( 1 );

        doTests( tc );
    }

    /**
     * Test the alias TypeCode operations. The test creates a alias (aliased to a string)
     * TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCAlias() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_alias_tc( "IDL:Dummy:1.0",
              "ReallyDummy", m_orb.create_string_tc( 1 ) );

        doTests( tc );
    }

    /**
     * Test the array TypeCode operations. The test creates a array (string elements)
     * TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCArray() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_array_tc( 3, m_orb.create_wstring_tc( 1 ) );

        doTests( tc );
    }

    /**
     * Test the enum TypeCode operations. The test creates an enum
     * TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCEnum() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_enum_tc( "IDL:Dummy:1.0",
              "ReallyDummy", new String[ 0 ] );

        doTests( tc );
    }

    /**
     * Test the fixed TypeCode operations. The test creates a fixed
     * TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCFixed() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_fixed_tc( ( short ) 5, ( short ) 2 );

        doTests( tc );
    }

    /**
     * Test the object reference TypeCode operations. The test creates a object
     * reference TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCObject() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_objref );

        doTests( tc );
    }

    /**
     * Test the struct TypeCode operations. The test creates a struct
     * TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCStruct() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_struct_tc( "IDL:Dummy:1.0",
              "ReallyDummy", new org.omg.CORBA.StructMember[ 0 ] );

        doTests( tc );
    }

    /**
     * Test the union TypeCode operations. The test creates an union
     * TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCUnion() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_union_tc( "IDL:Dummy:1.0",
              "ReallyDummy", m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_short ),
              new org.omg.CORBA.UnionMember[ 0 ] );

        doTests( tc );
    }

    /**
     * Test the value TypeCode operations. The test creates a value
     * TypeCode and then invokes all its operations.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testTCValue() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_value_tc( "IDL:Dummy:1.0",
              "ReallyDummy", ( short ) 0, m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_null ),
              new org.omg.CORBA.ValueMember[ 0 ] );

        doTests( tc );
    }

    /**
     * Test the recursive TypeCode. This typecode must be embeded into another
     * typecode before being used.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails 
     */
    public void testTCRecursive() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.TypeCode tc = m_orb.create_recursive_tc( "IDL:Dummy:1.0" );

        org.omg.CORBA.TypeCode seqTC = m_orb.create_sequence_tc( 1, tc );

        org.omg.CORBA.StructMember [] sm = new org.omg.CORBA.StructMember[] {
              new org.omg.CORBA.StructMember( "backref", seqTC, null ) };

        org.omg.CORBA.TypeCode strTC = m_orb.create_struct_tc( "IDL:Dummy:1.0", "ReallyDummy", sm );

        assertTrue( "Recursive typecode not properly formed",
                    seqTC.content_type() == strTC );

        // do tests on the recursive typecode. These fail differently to normal (?)
        tc.hashCode();
        tc.equals( tc );

        try
        {
            tc.equivalent( m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_char ) );
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE ex )
        {
            // attempt to access incomplete typecode
        }

        try
        {
            tc.equal( m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_char ) );
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE ex )
        {
            // attempt to access incomplete typecode
        }

        try
        {
            tc.get_compact_typecode();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE ex )
        {
            // attempt to access incomplete typecode
        }


        // non-primitive types
        try
        {
            tc.id();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // attempt to access incomplete typecode
        }

        try
        {
            tc.name();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }


        // strings, wstrings, arrays, sequences
        try
        {
            tc.length();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }


        // structs, exceptions, unions, enum, value
        try
        {
            tc.member_count();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }


        // structs, exceptions, unions, value
        try
        {
            tc.member_name( 0 );
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }
        catch ( org.omg.CORBA.TypeCodePackage.Bounds ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        try
        {
            tc.member_type( 0 );
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }
        catch ( org.omg.CORBA.TypeCodePackage.Bounds ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        // unions


        try
        {
            tc.member_label( 0 );
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }
        catch ( org.omg.CORBA.TypeCodePackage.Bounds ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        try
        {
            tc.default_index();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        try
        {
            tc.discriminator_type();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }


        // sequences, arrays, aliases, boxed values
        try
        {
            tc.content_type();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }


        // fixed
        try
        {
            tc.fixed_digits();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        try
        {
            tc.fixed_scale();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }


        // value
        try
        {
            tc.type_modifier();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        try
        {
            tc.member_visibility( 0 );
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        try
        {
            tc.concrete_base_type();
            fail( "expected exception" );
        }
        catch ( org.omg.CORBA.BAD_TYPECODE expected )
        {
            // attempt to access incomplete typecode
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }
    }

    /**
     * Test all TypeCode operations.
     * The methods fail on some TCs and work on others, having such a general
     * test makes no sens. It would be better to check that the right methods
     * fail with each TC independently.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    private void doTests( org.omg.CORBA.TypeCode tc ) throws org.omg.CORBA.UserException
    {
        tc.equivalent( m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_char ) );
        tc.equal( m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_char ) );

        tc.get_compact_typecode();

        tc.hashCode();

        // non-primitive types
        try
        {
            tc.id();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        try
        {
            tc.name();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        // strings, wstrings, arrays, sequences
        try
        {
            tc.length();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        // structs, exceptions, unions, enum, value
        try
        {
            tc.member_count();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        // structs, exceptions, unions, value
        try
        {
            tc.member_name( 0 );
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }
        catch ( org.omg.CORBA.TypeCodePackage.Bounds expected )
        {
            // occurs with some TCs
        }

        try
        {
            tc.member_type( 0 );
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }
        catch ( org.omg.CORBA.TypeCodePackage.Bounds expected )
        {
            // occurs with some TCs
        }

        // unions
        try
        {
            tc.member_label( 0 );
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }
        catch ( org.omg.CORBA.TypeCodePackage.Bounds expected )
        {
            // occurs with some TCs
        }

        try
        {
            tc.default_index();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        try
        {
            tc.discriminator_type();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        // sequences, arrays, aliases, boxed values
        try
        {
            tc.content_type();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        // fixed
        try
        {
            tc.fixed_digits();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        try
        {
            tc.fixed_scale();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        // value
        try
        {
            tc.type_modifier();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }

        try
        {
            tc.member_visibility( 0 );
            // needed in jdk 1.2
            if ( false )
                throw new org.omg.CORBA.TypeCodePackage.Bounds();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }
        catch ( org.omg.CORBA.TypeCodePackage.Bounds expected )
        {
            // occurs with some TCs
        }

        try
        {
            tc.concrete_base_type();
        }
        catch ( org.omg.CORBA.TypeCodePackage.BadKind expected )
        {
            // occurs with some TCs
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( TypeCodeTest.class ) );
    }
}
