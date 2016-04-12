/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.dynamic;

import junit.framework.TestSuite;

import org.omg.CORBA.Any;

import org.omg.DynamicAny.DynStruct;
import org.omg.DynamicAny.NameDynAnyPair;
import org.omg.DynamicAny.NameValuePair;

import org.openorb.test.CORBA.CORBATestCase;

public class DynAnyTest
    extends CORBATestCase
{
    public DynAnyTest( String name )
    {
        super( name );
    }

    public void setUp()
    {
        super.setUp();

        try
        {
            m_orb = getORB();

            m_any = m_orb.create_any();

            org.omg.CORBA.Object obj = m_orb.resolve_initial_references( "DynAnyFactory" );

            m_dyn_factory = org.omg.DynamicAny.DynAnyFactoryHelper.narrow( obj );
        }
        catch ( org.omg.CORBA.UserException ex )
        {
            fail( "exception during setup:" + ex.toString() );
        }
    }

    private org.omg.CORBA.ORB m_orb;
    private org.omg.CORBA.Any m_any;

    private org.omg.DynamicAny.DynAnyFactory m_dyn_factory;

    /**
     * Test the various basic inserting within the DynAnys. The DynAny is created,
     * a corresponding value is inserted and then the DynAny is destroyed to avoid
     * memory leak (explicit destruction is necessary). The tested types are char,
     * wchar, boolean, octet, short, ushort, long, ulong, longlong, ulonglong,
     * float, double, any, string, wstring, typecode, object.
     * Basic DynAny operations (rewind, component_count) are also tested.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testDynBasic() throws org.omg.CORBA.UserException
    {
        org.omg.DynamicAny.DynAny dynAny;

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_char ) );
        dynAny.insert_char( 'c' );
        dynAny.get_char();

        dynAny.type();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_wchar ) );
        dynAny.insert_wchar( 'c' );
        dynAny.get_wchar();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_boolean ) );
        dynAny.insert_boolean( true );
        dynAny.get_boolean();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_octet ) );
        dynAny.insert_octet( ( byte ) 'o' );
        dynAny.get_octet();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_short ) );
        dynAny.insert_short( ( short ) 1 );
        dynAny.get_short();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_ushort ) );
        dynAny.insert_ushort( ( short ) 1 );
        dynAny.get_ushort();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_long ) );
        dynAny.insert_long( 2 );
        dynAny.get_long();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_ulong ) );
        dynAny.insert_ulong( 2 );
        dynAny.get_ulong();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_longlong ) );
        dynAny.insert_longlong( 2L );
        dynAny.get_longlong();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_ulonglong ) );
        dynAny.insert_ulonglong( 2L );
        dynAny.get_ulonglong();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float ) );
        dynAny.insert_float( ( float ) 3.0 );
        dynAny.get_float();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_double ) );
        dynAny.insert_double( 3.0 );
        dynAny.get_double();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_any ) );
        dynAny.insert_any( m_any );
        dynAny.get_any();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string ) );
        dynAny.insert_string( "str" );
        dynAny.get_string();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_wstring ) );
        dynAny.insert_wstring( "str" );
        dynAny.get_wstring();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_TypeCode ) );
        dynAny.insert_typecode( m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_TypeCode ) );
        dynAny.get_typecode();
        dynAny.destroy();

        dynAny = m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_objref ) );
        dynAny.insert_reference( null );
        dynAny.get_reference();

        // For test scoring only
        dynAny.type();

        dynAny.rewind();

        dynAny.seek( 0 );

        dynAny.component_count();

        dynAny.next();

        dynAny.current_component();

        dynAny.from_any( dynAny.to_any() );

        dynAny.assign( dynAny.copy() );

        // For test scoring only
        ( ( org.omg.DynamicAny.DynAny ) dynAny ).rewind();

        ( ( org.omg.DynamicAny.DynAny ) dynAny ).seek( 0 );

        ( ( org.omg.DynamicAny.DynAny ) dynAny ).component_count();

        ( ( org.omg.DynamicAny.DynAny ) dynAny ).next();

        ( ( org.omg.DynamicAny.DynAny ) dynAny ).current_component();

        ( ( org.omg.DynamicAny.DynAny ) dynAny ).from_any(
              ( ( org.omg.DynamicAny.DynAny ) dynAny ).to_any() );

        ( ( org.omg.DynamicAny.DynAny ) dynAny ).assign(
              ( ( org.omg.DynamicAny.DynAny ) dynAny ).copy() );

        ( ( org.omg.DynamicAny.DynAny ) dynAny ).equal(
              ( ( org.omg.DynamicAny.DynAny ) dynAny ).copy() );

        dynAny.destroy();
    }

    /**
     * Test the various operations inserting within the DynEnums. The DynEnum is
     * created with a TypeCode. DynEnum operations (set_as_string, set_as_ulong ) and
     * DynAny operations (rewind, component_count) are also tested.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testDynEnum() throws org.omg.CORBA.UserException
    {
        org.omg.DynamicAny.DynEnum dyn_enum;

        dyn_enum = ( org.omg.DynamicAny.DynEnum )
              m_dyn_factory.create_dyn_any_from_type_code( m_orb.create_enum_tc(
              "IDL:Dummy:1.0", "Dummy", new String[] {"RED", "GREEN"} ) );

        dyn_enum.set_as_string( "RED" );

        dyn_enum.get_as_string();

        dyn_enum.set_as_ulong( 0 );

        dyn_enum.get_as_ulong();

        // For test scoring only
        dyn_enum.rewind();

        dyn_enum.seek( 0 );

        dyn_enum.component_count();

        dyn_enum.next();

        dyn_enum.current_component();

        dyn_enum.from_any( dyn_enum.to_any() );

        dyn_enum.assign( dyn_enum.copy() );

        dyn_enum.destroy();
    }

    /*
     * --------------------------------------------------------
     * DynStruct
     * --------------------------------------------------------
     */

    /**
     * Test the various operations for the DynStruct.
     * The DynStruct is created with an org.omg.GIOP.MessageHeader_1_1
     * struct and with org.omg.GIOP.MessageHeader_1_2, an alias for
     * the former.  The following operations have few or no tests:
     * assign, from_any, to_any, destroy, copy, set_members,
     * set_members_as_dyn_any.
     *
     * @throws org.omg.CORBA.UserException if any test fails
     */
    public void testDynStruct() throws org.omg.CORBA.UserException
    {
        TestingDynStructFactory factory;
        DynStruct dyn_struct;

        /*
         * There are two major subgroups of DynAny struct testing:
         * with and without aliases.  In either case an IDL struct is
         * required.  Since the org.omg.GIOP.MessageHeader_1_1 struct
         * also has a typedef, org.omg.GIOP.MessageHeader_1_2, that's
         * what we use to test.
         *
         * First the non-aliased tests...
         */

        factory = new NonAliasedDynStructFactory ();
        dyn_struct = factory.createDynStruct ();
        dynStruct_basicTests (dyn_struct);
        dynStruct_iteratorTests (dyn_struct);
        dynStruct_getMembersTests (dyn_struct);
        dynStruct_getMembersAsDynAnyTests (dyn_struct);
        dyn_struct.destroy();
        dynStruct_setMembersTests (factory);
        dynStruct_setMembersAsDynAnyTests (factory);


        /*
         * aliased tests...
         */

        factory = new AliasedDynStructFactory ();
        dyn_struct = factory.createDynStruct ();
        dynStruct_basicTests (dyn_struct);
        dynStruct_iteratorTests (dyn_struct);
        dynStruct_getMembersTests (dyn_struct);
        dynStruct_getMembersAsDynAnyTests (dyn_struct);
        dyn_struct.destroy();
        dynStruct_setMembersTests (factory);
        dynStruct_setMembersAsDynAnyTests (factory);
    }

    private interface TestingDynStructFactory
    {
        DynStruct createDynStruct ()
            throws org.omg.CORBA.UserException;
    }

    private class NonAliasedDynStructFactory
        implements TestingDynStructFactory
    {
        public DynStruct createDynStruct ()
            throws org.omg.CORBA.UserException
        {
            Any any = m_orb.create_any();
            org.omg.GIOP.MessageHeader_1_1 theStruct
                = new org.omg.GIOP.MessageHeader_1_1 (
                    new char [] { 'a', 'b', 'c', 'd' },
                    new org.omg.GIOP.Version ( (byte) 0xaa, (byte) 0xbb ), 
                    (byte) 0x01,
                    (byte) 0x02,
                    10 );
            org.omg.GIOP.MessageHeader_1_1Helper.insert (any, theStruct);

            return ( org.omg.DynamicAny.DynStruct )
                m_dyn_factory.create_dyn_any ( any );
        }
    }

    private class AliasedDynStructFactory
        implements TestingDynStructFactory
    {
        public DynStruct createDynStruct ()
            throws org.omg.CORBA.UserException
        {
            Any any = m_orb.create_any();
            org.omg.GIOP.MessageHeader_1_1 theStruct
                = new org.omg.GIOP.MessageHeader_1_1 (
                    new char [] { 'a', 'b', 'c', 'd' },
                    new org.omg.GIOP.Version ( (byte) 0xaa, (byte) 0xbb ), 
                    (byte) 0x01,
                    (byte) 0x02,
                    10 );
            org.omg.GIOP.MessageHeader_1_2Helper.insert (any, theStruct);

            return ( org.omg.DynamicAny.DynStruct )
                m_dyn_factory.create_dyn_any ( any );
        }
    }

    private void dynStruct_basicTests (DynStruct dyn_struct)
        throws org.omg.CORBA.UserException
    {
        assertEquals (
            "MessageHeader_1_1 has five components",
            5,
            dyn_struct.component_count() );
    }

    private void dynStruct_iteratorTests (DynStruct dyn_struct)
        throws org.omg.CORBA.UserException
    {
        org.omg.DynamicAny.DynStruct member_struct;
        org.omg.DynamicAny.DynArray member_array;

        /* ------------------------------------------------------
         * Testing the constructor configuration of the current
         * location.
         */

        assertEquals (
            "member name 0 is 'magic'",
            "magic",
            dyn_struct.current_member_name() );

        assertTrue (
            "the 'magic' member kind is alias",
            org.omg.CORBA.TCKind.tk_alias.value()
                == dyn_struct.current_member_kind().value() );

        member_array = ( org.omg.DynamicAny.DynArray )
            dyn_struct.current_component();

        assertTrue (
            "the 'magic' member is an alias",
            org.omg.CORBA.TCKind.tk_alias.value()
                == member_array.type().kind().value() );

        assertEquals (
            "the 'magic' member data is an array of 4 char",
            4,
            member_array.component_count() );

        /* ------------------------------------------------------
         * Testing next().
         * Currently on index 0; next should put us on index 1, an
         * org.omg.GIOP.Version struct member named 'GIOP_version'.
         */

        dyn_struct.next();

        assertEquals (
            "member name 1 is 'GIOP_version'",
            "GIOP_version",
            dyn_struct.current_member_name() );

        assertTrue (
            "the 'GIOP_version' member is an org.omg.GIOP.Version struct",
            org.omg.CORBA.TCKind.tk_struct.value()
                == dyn_struct.current_member_kind().value() );

        member_struct = ( org.omg.DynamicAny.DynStruct )
            dyn_struct.current_component();

        assertTrue (
            "the 'GIOP_version' member is a struct",
            org.omg.CORBA.TCKind.tk_struct.value()
                == member_struct.type().kind().value() );

        assertEquals (
            "the org.omg.GIOP.Version struct has 2 members",
            2,
            member_struct.component_count() );

        /*
         * ------------------------------------------------------
         * Testing seek().  Index 3 is 'octet message_type;'.
         */

        dyn_struct.seek( 3 );

        assertEquals (
            "member name 3 is 'message_type'",
            "message_type",
            dyn_struct.current_member_name() );

        assertTrue (
            "the 'message_type' member is an octet",
            org.omg.CORBA.TCKind.tk_octet.value()
                == dyn_struct.current_member_kind().value() );

        /* ------------------------------------------------------
         * Testing rewind().  Index 0 is 'octet message_type;'.
         */

        dyn_struct.rewind();

        assertEquals (
            "member name 0 is 'magic'",
            "magic",
            dyn_struct.current_member_name() );

        assertTrue (
            "the 'magic' member is an alias",
            org.omg.CORBA.TCKind.tk_alias.value()
                == dyn_struct.current_member_kind().value() );
    }

    /**
     * Test get_members().  Check all types, and the values of the
     * non-constructed members.
     */
    private void dynStruct_getMembersTests (DynStruct dyn_struct)
        throws org.omg.CORBA.UserException
    {

        NameValuePair[] members = dyn_struct.get_members();

        assertEquals (
            "MessageHeader_1_1 has five members",
            5,
            members.length );

        // 0

        assertEquals (
            "member 0 has id 'magic'",
            "magic",
            members[0].id );

        assertEquals (
            "member 0 is an octet",
            org.omg.CORBA.TCKind.tk_alias.value(),
            members[0].value.type().kind().value() );

        // 1

        assertEquals (
            "member 1 has id 'GIOP_version'",
            "GIOP_version",
            members[1].id );

        assertEquals (
            "member 1 is an org.omg.GIOP.Version struct",
            org.omg.CORBA.TCKind.tk_struct.value(),
            members[1].value.type().kind().value() );

        // 2

        assertEquals (
            "member 2 has id 'flags'",
            "flags",
            members[2].id );

        assertEquals (
            "member 2 is an octet",
            org.omg.CORBA.TCKind.tk_octet.value(),
            members[2].value.type().kind().value() );

        assertEquals (
            "member 2 has value 0x01",
            0x01,
            members[2].value.extract_octet() );

        // 3

        assertEquals (
            "member 3 has id 'message_type'",
            "message_type",
            members[3].id );

        assertEquals (
            "member 3 is an octet",
            org.omg.CORBA.TCKind.tk_octet.value(),
            members[3].value.type().kind().value() );

        assertEquals (
            "member 3 has value 0x02",
            0x02,
            members[3].value.extract_octet() );

        // 4

        assertEquals (
            "member 4 has id 'message_size'",
            "message_size",
            members[4].id );

        assertEquals (
            "member 4 is an unsigned long",
            org.omg.CORBA.TCKind.tk_ulong.value(),
            members[4].value.type().kind().value() );

        assertEquals (
            "member 4 has value 10",
            10,
            members[4].value.extract_ulong() );
    }

    /**
     * Test get_members_as_dyn_any().  This code is (when initially
     * written) line-for-line identical with
     * dynStruct_getMembersTests(), but I can't see a good way to
     * combine them, since even though the code looks the same it is
     * operating on different objects.
     */
    private void dynStruct_getMembersAsDynAnyTests (DynStruct dyn_struct)
        throws org.omg.CORBA.UserException
    {
        /*
         * Testing get_members_as_dyn_any().  Check all types, and the
         * values of the non-constructed members.
         */

        NameDynAnyPair[] members = dyn_struct.get_members_as_dyn_any();

        assertEquals (
            "MessageHeader_1_1 has five members",
            5,
            members.length );

        // 0

        assertEquals (
            "member 0 has id 'magic'",
            "magic",
            members[0].id );

        assertEquals (
            "member 0 is an octet",
            org.omg.CORBA.TCKind.tk_alias.value(),
            members[0].value.type().kind().value() );

        // 1

        assertEquals (
            "member 1 has id 'GIOP_version'",
            "GIOP_version",
            members[1].id );

        assertEquals (
            "member 1 is an org.omg.GIOP.Version struct",
            org.omg.CORBA.TCKind.tk_struct.value(),
            members[1].value.type().kind().value() );

        // 2

        assertEquals (
            "member 2 has id 'flags'",
            "flags",
            members[2].id );

        assertEquals (
            "member 2 is an octet",
            org.omg.CORBA.TCKind.tk_octet.value(),
            members[2].value.type().kind().value() );

        assertEquals (
            "member 2 has value 0x01",
            0x01,
            members[2].value.get_octet() );

        // 3

        assertEquals (
            "member 3 has id 'message_type'",
            "message_type",
            members[3].id );

        assertEquals (
            "member 3 is an octet",
            org.omg.CORBA.TCKind.tk_octet.value(),
            members[3].value.type().kind().value() );

        assertEquals (
            "member 3 has value 0x02",
            0x02,
            members[3].value.get_octet() );

        // 4

        assertEquals (
            "member 4 has id 'message_size'",
            "message_size",
            members[4].id );

        assertEquals (
            "member 4 is an unsigned long",
            org.omg.CORBA.TCKind.tk_ulong.value(),
            members[4].value.type().kind().value() );

        assertEquals (
            "member 4 has value 10",
            10,
            members[4].value.get_ulong() );
    }

    private void dynStruct_setMembersTests (TestingDynStructFactory factory)
        throws org.omg.CORBA.UserException
    {
        DynStruct dyn_struct = factory.createDynStruct ();
        boolean threwIt;

        threwIt = false;
        try
        {
            dyn_struct.set_members( new org.omg.DynamicAny.NameValuePair[ 0 ] );
        }
        catch (org.omg.DynamicAny.DynAnyPackage.InvalidValue e)
        {
            threwIt = true;
        }
        assertTrue ( "should have thrown InvalidValue exception", threwIt );

        dyn_struct.destroy ();
    }

    private void dynStruct_setMembersAsDynAnyTests (TestingDynStructFactory factory)
        throws org.omg.CORBA.UserException
    {
        DynStruct dyn_struct = factory.createDynStruct ();
        boolean threwIt;

        threwIt = false;
        try
        {
            dyn_struct.set_members_as_dyn_any (
                new org.omg.DynamicAny.NameDynAnyPair[ 0 ] );
        }
        catch (org.omg.DynamicAny.DynAnyPackage.InvalidValue e)
        {
            threwIt = true;
        }
        assertTrue ( "should have thrown InvalidValue exception", threwIt );

        dyn_struct.destroy ();
    }


    /**
     * Test the various operations inserting within the DynFixeds. The DynFixed is created
     * with a TypeCode. DynFixed operations (get/set value) and DynAny operations (rewind,
     * component_count) are also tested.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testDynFixed() throws org.omg.CORBA.UserException
    {
        org.omg.DynamicAny.DynFixed dyn_fixed;

        dyn_fixed = ( org.omg.DynamicAny.DynFixed )
              m_dyn_factory.create_dyn_any_from_type_code( m_orb.create_fixed_tc(
              ( short ) 5, ( short ) 2 ) );

        dyn_fixed.set_value( "0123.450d" );

        dyn_fixed.get_value();

        // For test scoring only
        dyn_fixed.rewind();

        dyn_fixed.seek( 0 );

        dyn_fixed.component_count();

        dyn_fixed.next();

        dyn_fixed.current_component();

        dyn_fixed.from_any( dyn_fixed.to_any() );

        dyn_fixed.assign( dyn_fixed.copy() );

        dyn_fixed.destroy();
    }

    /**
     * Test the various operations inserting within the DynUnions. The DynUnion is created
     * with a TypeCode and a short TypeCode for the discriminator.
     * DynUnion operations (get/set discriminator, get/set members) and DynAny operations
     * (rewind, component_count) are also tested.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testDynUnion() throws org.omg.CORBA.UserException
    {
        org.omg.DynamicAny.DynUnion dyn_union;

        dyn_union = ( org.omg.DynamicAny.DynUnion )
              m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.create_union_tc( "IDL:Dummy:1.0", "ReallyDummy",
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_short ),
              new org.omg.CORBA.UnionMember[ 0 ] ) );

        dyn_union.set_discriminator( m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_short ) ) );

        try
        {
            dyn_union.get_discriminator();
        }
        catch ( java.lang.Exception ex )
        {
            fail( "Unexpected exception caught: " + ex );
        }

        dyn_union.has_no_active_member();
        try
        {
            dyn_union.set_to_no_active_member();
        }
        catch ( java.lang.Exception ex )
        {
            // ???
        }

        dyn_union.discriminator_kind();
        try
        {
            dyn_union.set_to_default_member();
        }
        catch ( java.lang.Exception ex )
        {
            // ???
        }

        dyn_union.member();

        dyn_union.member_name();

        dyn_union.member_kind();

        // For test scoring only
        dyn_union.rewind();

        dyn_union.seek( 0 );

        dyn_union.component_count();

        dyn_union.next();

        dyn_union.current_component();

        try
        {
            dyn_union.from_any( dyn_union.to_any() );
        }
        catch ( java.lang.Exception ex )
        {
            // Checked later
        }

        try
        {
            dyn_union.assign( dyn_union.copy() );
        }
        catch ( java.lang.Exception ex )
        {
            // Checked later
        }
        dyn_union.destroy();
    }


    /**
     * Test the various operations within the DynSequence The DynSequence is created
     * with a TypeCode and a length. DynSequence operations (get/set elements) and
     * DynAny operations (rewind, component_count) are also tested.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testDynSequence() throws org.omg.CORBA.UserException
    {
        org.omg.DynamicAny.DynSequence dyn_seq;

        dyn_seq = ( org.omg.DynamicAny.DynSequence )
              m_dyn_factory.create_dyn_any_from_type_code(
              m_orb.create_sequence_tc( 2,
              m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string ) ) );

        dyn_seq.set_length( 2 );

        dyn_seq.get_length();

        try
        {
            dyn_seq.set_elements( new org.omg.CORBA.Any[ 0 ] );
        }
        catch ( java.lang.Exception ex )
        {
            // sequence must be two elements long
        }

        dyn_seq.get_elements();

        try
        {
            dyn_seq.set_elements_as_dyn_any( new org.omg.DynamicAny.DynAny[ 0 ] );
        }
        catch ( java.lang.Exception ex )
        {
            // sequence must be two elements long
        }

        dyn_seq.get_elements_as_dyn_any();

        // For test scoring only
        dyn_seq.rewind();

        dyn_seq.seek( 0 );

        dyn_seq.component_count();

        dyn_seq.next();

        dyn_seq.current_component();

        dyn_seq.from_any( dyn_seq.to_any() );

        dyn_seq.assign( dyn_seq.copy() );

        dyn_seq.destroy();
    }


    /**
     * Test the various operations within the DynArray The DynArray is created with
     * a TypeCode and a short TypeCode for the discriminator. DynArray operations
     * (get/set discriminator, get/set members) and DynAny operations (rewind,
     * component_count) are also tested.
     *
     * @exception org.omg.CORBA.UserException if any of the test case fails
     */
    public void testDynArray() throws org.omg.CORBA.UserException
    {
        org.omg.DynamicAny.DynArray dyn_arr;

        dyn_arr = ( org.omg.DynamicAny.DynArray )
              m_dyn_factory.create_dyn_any_from_type_code( m_orb.create_array_tc(
              2, m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string ) ) );

        try
        {
            dyn_arr.set_elements( new org.omg.CORBA.Any[ 0 ] );
        }
        catch ( java.lang.Exception ex )
        {
            // Array must be two elements long
        }

        dyn_arr.get_elements();

        try
        {
            dyn_arr.set_elements_as_dyn_any( new org.omg.DynamicAny.DynAny[ 0 ] );
        }
        catch ( java.lang.Exception ex )
        {
            // Array must be two elements long
        }

        dyn_arr.get_elements_as_dyn_any();

        // For test scoring only
        dyn_arr.rewind();

        dyn_arr.seek( 0 );

        dyn_arr.component_count();

        dyn_arr.next();

        dyn_arr.current_component();

        dyn_arr.from_any( dyn_arr.to_any() );

        dyn_arr.assign( dyn_arr.copy() );

        dyn_arr.destroy();
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( DynAnyTest.class ) );
    }
}

