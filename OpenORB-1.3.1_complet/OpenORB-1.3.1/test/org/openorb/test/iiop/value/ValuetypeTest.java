/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.iiop.value;

import org.omg.PortableServer.POA;

import org.openorb.test.ORBTestCase;

import junit.framework.TestSuite;

/**
 * Tests marshaling and unmarshaling of various valuetypes.
 *
 * @author Chris Wood
 */
public class ValuetypeTest
    extends ORBTestCase
{
    public ValuetypeTest( String name )
    {
        super( name );
    }

    protected void setUp()
    {
        super.setUp();

        try
        {
            // find the root poa
            org.omg.CORBA.ORB orb = getORB();
            m_orb = ( org.omg.CORBA_2_3.ORB ) orb;

            POA rootPOA = ( POA ) orb.resolve_initial_references( "RootPOA" );

            rootPOA.the_POAManager().activate();

            TestTC01 svr_ref = ( new TestTC01Impl( rootPOA ) )._this( orb );

            m_cltRef = TestTC01Helper.narrow( forceMarshal( svr_ref ) );

            m_any = m_orb.create_any();
        }
        catch ( org.omg.CORBA.UserException ex )
        {
            fail( "exception during setup:" + ex.toString() );
        }
    }

    private org.omg.CORBA_2_3.ORB m_orb;
    private TestTC01 m_cltRef;
    private org.omg.CORBA.Any m_any;


    /**
     * Test the Basic valuetype.
     */
    public void testBasicValuetype()
    {
        Object ret;

        ret = m_cltRef.get_value_a( 0 );
        assertTrue( "Value returned from get_value_a(0) is not a ValueA",
                    ret instanceof ValueA );

        ret = m_cltRef.get_value( 0 );
        assertTrue( "Value returned from get_value(0) is not a ValueA",
                    ret instanceof ValueA );

        ret = m_cltRef.get_abstract( 0 );
        assertTrue( "Value returned from get_abstract(0) is not a ValueA",
                    ret instanceof ValueA );

        ValueAHelper.insert( m_any, ( ValueA ) ret );
        m_cltRef.echo_any( m_any );
    }

    /**
     * Test Valuetype with private members.
     */
    public void testPMValuetype()
    {
        Object ret;

        ret = m_cltRef.get_value_b();
        assertTrue( "Value returned from get_value_b() is not a ValueB",
                    ret instanceof ValueB );

        ret = m_cltRef.get_value( 1 );
        assertTrue( "Value returned from get_value(1) is not a ValueB",
                    ret instanceof ValueB );

        ret = m_cltRef.get_abstract( 1 );
        assertTrue( "Value returned from get_abstract(1) is not a ValueB",
                    ret instanceof ValueB );
    }

    /**
     * Test Nontruncatable, requiring factory.
     */
    public void testNontruncatableValuetype()
    {
        try
        {
            m_cltRef.get_value_c();
            fail( "Read a ValueC without factory (should fail)" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // this should fail, no factory registered.
        }

        try
        {
            m_cltRef.get_value_a( 2 );
            fail( "Read a ValueC as ValueA without factory (should fail)" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // this should fail, no factory registered.
        }

        try
        {
            m_cltRef.get_value( 2 );
            fail( "Read a ValueC as ValueBase without factory (should fail)" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // this should fail, no factory registered.
        }

        m_orb.register_value_factory( ValueCHelper.id(), new ValueCFactory() );

        Object ret;

        ret = m_cltRef.get_value_c();
        assertTrue( "Value returned from get_value_c() is not a ValueC",
                    ret instanceof ValueC );

        ret = m_cltRef.get_value_a( 2 );
        assertTrue( "Value returned from get_value_a(2) is not a ValueC",
                    ret instanceof ValueC );

        ret = m_cltRef.get_value( 2 );
        assertTrue( "Value returned from get_value(2) is not a ValueC",
                    ret instanceof ValueC );

        ret = m_cltRef.get_abstract( 2 );
        assertTrue( "Value returned from get_abstract(2) is not a ValueC",
                    ret instanceof ValueC );
    }

    /**
     * Test Truncatable, requiring factory.
     */
    public void testTruncatableValuetype()
    {
        try
        {
            m_cltRef.get_value_d();
            fail( "Read a ValueD without factory (should fail)" );
        }
        catch ( org.omg.CORBA.SystemException ex )
        {
            // this should fail, no factory registered.
        }

        Object ret;

        ret = m_cltRef.get_value_a( 3 );
        assertTrue( "Truncated value returned from get_value_a(3) is not a ValueA",
                    ret instanceof ValueA );
        assertTrue( "Truncated value returned from get_value_a(3) is a ValueD",
                    !( ret instanceof ValueD ) );

        ret = m_cltRef.get_value( 3 );
        assertTrue( "Truncated value returned from get_value(3) is not a ValueA",
                    ret instanceof ValueA );
        assertTrue( "Truncated value returned from get_value(3) is a ValueD",
                    !( ret instanceof ValueD ) );

        m_orb.register_value_factory( ValueDHelper.id(), new ValueDFactory() );

        ret = m_cltRef.get_value_d();
        assertTrue( "Value returned from get_value_d() is not a ValueD",
                    ret instanceof ValueD );

        ret = m_cltRef.get_value_a( 3 );
        assertTrue( "Value returned from get_value_a(3) is not a ValueD",
                    ret instanceof ValueD );

        ret = m_cltRef.get_value( 3 );
        assertTrue( "Value returned from get_value(3) is not a ValueD",
                    ret instanceof ValueD );

        ret = m_cltRef.get_abstract( 3 );
        assertTrue( "Value returned from get_abstract(3) is not a ValueC",
                    ret instanceof ValueD );
    }

    /**
     * Test Supports valuetype.
     */
    public void testSupportsValuetype()
    {
        Object ret;

        ret = m_cltRef.get_value_e();
        assertTrue( "Value returned from get_value_e() is not a ValueERemoteOperations",
                    ret instanceof ValueERemoteOperations );
        assertTrue( "Value returned from get_value_e() is not a ValueE",
                    ret instanceof ValueE );
        assertTrue( "Value returned from get_value_e() is an object reference",
                    !( ret instanceof org.omg.CORBA.Object ) );

        ret = m_cltRef.get_value_e_remote();
        assertTrue( "Value returned from get_value_e_remote() is not a ValueERemote",
                    ret instanceof ValueERemote );
        assertTrue( "Value returned from get_value_e_remote() is not an object reference",
                    ret instanceof org.omg.CORBA.Object );

        ret = m_cltRef.get_value( 4 );
        assertTrue( "Value returned from get_value(4) is not a ValueE",
                    ret instanceof ValueE );

        ret = m_cltRef.get_abstract( 4 );
        assertTrue( "Value returned from get_abstract(4) is not a ValueE",
                    ret instanceof ValueE );
    }

    /**
     * Test custom marshal.
     */
    public void testCustomMarshalValuetype()
    {
        Object ret;

        ret = m_cltRef.get_value_f();
        assertTrue( "Value returned from get_value_f() is not a ValueF",
                    ret instanceof ValueF );

        ret = m_cltRef.get_value( 5 );
        assertTrue( "Value returned from get_value(5) is not a ValueF",
                    ret instanceof ValueF );

        ret = m_cltRef.get_abstract( 5 );
        assertTrue( "Value returned from get_abstract(5) is not a ValueF",
                    ret instanceof ValueF );
    }

    /**
     * Test custom marshal.
     */
    public void testNestedValuetype()
    {
        Object ret;

        ret = m_cltRef.get_value_g();
        assertTrue( "Value returned from get_value_g() is not a ValueG",
                    ret instanceof ValueG );

        ret = m_cltRef.get_value( 6 );
        assertTrue( "Value returned from get_value(6) is not a ValueG",
                    ret instanceof ValueG );

        ret = m_cltRef.get_abstract( 6 );
        assertTrue( "Value returned from get_abstract(6) is not a ValueG",
                    ret instanceof ValueG );

        ValueG val = ( ValueG ) ret;

        assertTrue( "Valuetype cycle has not transmitted properly",
                    val == val.left.parent && val == val.right.parent );
    }

    /**
     * Test abstract valuetypes.
     */
    public void testAbstract()
    {
        Object ret;

        ret = m_cltRef.get_abstract_a( 0 );
        assertTrue( "Value returned from get_abstract_a(0) is not an AbstractA",
                    ret instanceof AbstractA );
        assertTrue( "Value returned from get_abstract_a(0) is not an Object",
                    ret instanceof org.omg.CORBA.Object );

        ret = m_cltRef.get_abstract_a( 1 );
        assertTrue( "Value returned from get_abstract_a(1) is not an AbstractA",
                    ret instanceof AbstractA );
        assertTrue( "Value returned from get_abstract_a(1) is an Object",
                    !( ret instanceof org.omg.CORBA.Object ) );

        ret = m_cltRef.get_abstract_a( 2 );
        assertTrue( "Value returned from get_abstract_a(2) is not an AbstractA",
                    ret instanceof AbstractA );
        assertTrue( "Value returned from get_abstract_a(2) is an Object",
                    !( ret instanceof org.omg.CORBA.Object ) );

        ret = m_cltRef.get_abstract_a( 3 );
        assertTrue( "Value returned from get_abstract_a(3) is not an AbstractA",
                    ret instanceof AbstractA );
        assertTrue( "Value returned from get_abstract_a(3) is not an Object",
                    ret instanceof org.omg.CORBA.Object );

        AbstractAHelper.insert( m_any, ( AbstractA ) ret );
        m_cltRef.echo_any( m_any );
    }

    private static final int F_L = 49875;

    static class TestTC01Impl
        extends TestTC01POA
    {
        TestTC01Impl( POA poa )
        {
            m_poa = poa;
        }

        private POA m_poa;

        public POA _default_POA()
        {
            return m_poa;
        }

        public org.omg.CORBA.Any echo_any( org.omg.CORBA.Any val )
        {
            return val;
        }

        public org.omg.CORBA.TypeCode echo_TypeCode( org.omg.CORBA.TypeCode arg )
        {
            return arg;
        }

        public LongBox echo_longBox( LongBox val )
        {
            return val;
        }

        public int[] echo_longBoxSeq( int[] val )
        {
            return val;
        }

        public java.io.Serializable get_value( int type )
        {
            switch ( type )
            {

            case 0:
                return get_value_a( 0 );

            case 1:
                return get_value_b();

            case 2:
                return get_value_c();

            case 3:
                return get_value_d();

            case 4:
                return get_value_e();

            case 5:
                return get_value_f();

            case 6:
                return get_value_g();

            default:
                return null;
            }
        }

        public ValueA get_value_a( int type )
        {
            switch ( type )
            {

            case 0:
                ValueA ret = new ValueAImpl();
                ret.l = 1;
                ret.str = "ValueA";
                return ret;

            case 2:
                return get_value_c();

            case 3:
                return get_value_d();

            default:
                return null;
            }
        }

        public ValueB get_value_b()
        {
            org.omg.CORBA_2_3.ORB orb = ( org.omg.CORBA_2_3.ORB ) _orb();
            ValueBValueFactory factory = ( ValueBValueFactory )
                  orb.lookup_value_factory( ValueBHelper.id() );

            ValueB ret = factory.init( 1 );
            ret.str = "ValueB";
            return ret;
        }

        public ValueC get_value_c()
        {
            ValueC ret = new ValueCImpl();
            ret.l = 1;
            ret.n = 0;
            ret.str = "ValueC";
            return ret;
        }

        public ValueD get_value_d()
        {
            ValueD ret = new ValueDImpl();
            ret.l = 1;
            ret.n = 0;
            ret.str = "ValueD";
            return ret;
        }

        public ValueERemote get_value_e_remote()
        {
            return ( new ValueERemotePOATie( get_value_e(), _poa() ) )._this( _orb() );
        }

        public ValueE get_value_e()
        {
            ValueE ret = new ValueEImpl( "remote" );
            ret.prefix = "ValueE";
            return ret;
        }

        public ValueF get_value_f()
        {
            ValueF ret = new ValueFImpl();
            ret.l = F_L;
            return ret;
        }

        public ValueG get_value_g()
        {
            ValueG root = new ValueGImpl();
            root.idx = 0;
            root.name = "root";
            root.left = new ValueGImpl();
            root.left.idx = 1;
            root.left.name = "left";
            root.left.parent = root;
            root.right = new ValueGImpl();
            root.right.idx = 1;
            root.right.name = "right";
            root.right.parent = root;
            return root;
        }

        public Object get_abstract( int type )
        {
            if ( type < 7 )
                return get_value( type );

            return get_abstract_a( type - 7 );
        }

        public AbstractA get_abstract_a( int type )
        {
            AbstractA ret;

            switch ( type )
            {

            case 0:
                return ( new AbstractA1Impl( _poa() ) )._this( _orb() );

            case 1:
                return new AbstractA2Impl();

            case 2:
                return new AbstractA3Impl( "remote" );

            case 3:
                return ( new AbstractA1POATie(
                      new AbstractA3Impl( "remote" ), _poa() ) )._this( _orb() );

            default:
                return null;
            }
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( ValuetypeTest.class ) );
    }
}

