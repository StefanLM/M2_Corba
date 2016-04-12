/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package org.openorb.test.pi;

import junit.framework.TestSuite;

import org.omg.PortableServer.POA;

import org.openorb.test.ORBTestCase;

import org.openorb.test.adapter.poa.Hello;
import org.openorb.test.adapter.poa.HelloPOA;
import org.openorb.test.adapter.poa.HelloHelper;

/**
 * Tests marshaling and unmarshaling of various iiop types.
 *
 * @author Chris Wood
 */
public class PITest
    extends ORBTestCase
{
    /**
     * The constructor is responsible for constructing a test category and
     * adding the suite of test cases. It throws CWClassConstructorException
     * if it cannot construct the category
     *
     * @exception   Exception
     */
    public PITest( String name )
    {
        super( name );

        s_visitMask = 0;
        s_throwExcept = 0;
        s_retryCount = 0;
    }

    protected void setUp()
    {
        java.util.Properties props = new java.util.Properties();
        // set known iiop port since persistant references are used.
        props.setProperty( "org.omg.PortableInterceptor.ORBInitializerClass."
              + EmptyInitializer.class.getName(), "" );
        setUp( props );

        try
        {
            // find the root poa
            m_orb = getORB();

            POA rootPOA = ( POA ) m_orb.resolve_initial_references( "RootPOA" );

            Hello svrRef = ( new HelloImpl( rootPOA ) )._this( m_orb );

            rootPOA.the_POAManager().activate();

            m_cltRef = HelloHelper.narrow( forceMarshal( svrRef ) );

            s_any = m_orb.create_any();
            s_any.insert_boolean( true );
        }
        catch ( org.omg.CORBA.UserException ex )
        {
            fail( "exception during setup:" + ex.toString() );
        }
    }

    private Hello m_cltRef = null;

    private static final int SEND_REQ = 0x1;
    private static final int SEND_POLL = 0x2;
    private static final int SEND_SC = 0x8;
    private static final int RECV_REQSC = 0x10;
    private static final int RECV_REQ = 0x20;
    private static final int RECV_SC = 0x80;
    private static final int SEND_REPL = 0x100;
    private static final int SEND_EXPT = 0x200;
    private static final int SEND_OTHR = 0x400;
    private static final int SEND_REPL_SC = 0x800;
    private static final int RECV_REPL = 0x1000;
    private static final int RECV_EXPT = 0x2000;
    private static final int RECV_OTHR = 0x4000;
    private static final int RECV_REPL_SC = 0x8000;

    private static final int NOR_PATH = SEND_REQ | RECV_REQSC | RECV_REQ | SEND_REPL | RECV_REPL;
    private static final int NOR_SC = SEND_SC | RECV_SC | SEND_REPL_SC | RECV_REPL_SC;

    private static final int TEST_SCID = 0x444f7F01;

    private static int s_throwExcept;
    private static int s_visitMask;
    private static int s_retryCount;

    private static int s_slotID;
    private static org.omg.CORBA.Any s_any;

    private org.omg.CORBA.ORB m_orb;

    /**
     * Test complete request call.
     */
    public void testCompleteCall()
    {
        s_throwExcept = 0;

        try
        {
            m_cltRef.hello( "A Message from testCompleteCall()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            // The PI throws this exception by intention
        }

        assertEquals( "Complete call did not visit all interception points.", s_visitMask,
              NOR_PATH );
    }

    /**
     * Test complete request call with service contexts.
     *
     * @exception org.omg.CORBA.UserException if any of the test cases fails
     */
    public void testCompleteCallWithSCs() throws org.omg.CORBA.UserException
    {
        s_throwExcept = 0;
        org.omg.PortableInterceptor.Current curr =
            ( org.omg.PortableInterceptor.Current ) m_orb.resolve_initial_references( "PICurrent" );

        curr.set_slot( s_slotID, s_any );

        try
        {
            m_cltRef.hello( "A message from testCompleteCallWithSCs()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            // The PI throws this exception by intention
        }

        assertEquals( "Complete call did not visit all interception points.", s_visitMask,
              NOR_PATH | NOR_SC );
    }

    /**
     * Abort at send_request
     */
    public void testToSendRequest()
    {
        s_throwExcept = SEND_REQ;

        try
        {
            m_cltRef.hello( "A message from testToSendRequest()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            assertEquals( "Complete call did not visit all interception points.", s_visitMask,
                  SEND_REQ );
            assertEquals( "Exception thrown in wrong place", ex.minor, SEND_REQ );
            return ;
        }

        fail( "expected exception" );
    }

    /**
     * Abort at receive_request_service_contexts
     */
    public void testToRecvRequestSC()
    {
        s_throwExcept = RECV_REQSC;

        try
        {
            m_cltRef.hello( "A message from testToRecvRequestSC()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            assertEquals( "Complete call did not visit all interception points.",
                          s_visitMask, SEND_REQ | RECV_REQSC | RECV_EXPT );
            assertEquals( "Exception thrown in wrong place", ex.minor, RECV_REQSC );
            return ;
        }

        fail( "expected exception" );
    }

    /**
     * Abort at receive_request_service_contexts and recieve_exception
     */
    public void testToRecvRequestSCReceiveException()
    {
        s_throwExcept = RECV_REQSC | RECV_EXPT;

        try
        {
            m_cltRef.hello( "A message from testToRecvRequestSCReceiveException()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            assertEquals( "Complete call did not visit all interception points.",
                          s_visitMask, SEND_REQ | RECV_REQSC | RECV_EXPT );
            assertEquals( "Exception thrown in wrong place", ex.minor, RECV_REQSC | RECV_EXPT );
            return ;
        }

        fail( "expected exception" );
    }

    /**
     * Abort at receive_request
     */
    public void testToRecvRequest()
    {
        s_throwExcept = RECV_REQ;

        try
        {
            m_cltRef.hello( "A message from testToRecvRequest()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            assertEquals( "Complete call did not visit all interception points.",
                          s_visitMask, SEND_REQ | RECV_REQSC | RECV_REQ | RECV_EXPT );
            assertEquals( "Exception thrown in wrong place", ex.minor, RECV_REQ );
            return ;
        }

        fail( "expected exception" );
    }

    /**
     * Abort at receive_request and recieve_exception
     */
    public void testToRecvRequestRecvExcept()
    {
        s_throwExcept = RECV_REQ | RECV_EXPT;

        try
        {
            m_cltRef.hello( "A message from testToRecvRequestRecvExcept()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            assertEquals( "Complete call did not visit all interception points.",
                          s_visitMask, SEND_REQ | RECV_REQSC | RECV_REQ | RECV_EXPT );
            assertEquals( "Exception thrown in wrong place", ex.minor, RECV_REQ | RECV_EXPT );
            return ;
        }

        fail( "expected exception" );
    }

    /**
     * Abort at send_reply
     */
    public void testToSendReply()
    {
        s_throwExcept = SEND_REPL;

        try
        {
            m_cltRef.hello( "A message from testToSendReply()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            assertEquals( "Complete call did not visit all interception points.",
                          s_visitMask, SEND_REQ | RECV_REQSC | RECV_REQ | SEND_REPL | RECV_EXPT );
            assertEquals( "Exception thrown in wrong place", ex.minor, SEND_REPL );
            return ;
        }

        fail( "expected exception" );
    }

    /**
     * Abort at send_reply
     */
    public void testToRecvReply()
    {
        s_throwExcept = RECV_REPL;

        try
        {
            m_cltRef.hello( "A message from testToRecvReply()..." );
        }
        catch ( org.omg.CORBA.UNKNOWN ex )
        {
            assertEquals( "Complete call did not visit all interception points.",
                          s_visitMask, NOR_PATH );
            assertEquals( "Exception thrown in wrong place", ex.minor, RECV_REPL );
            return ;
        }

        fail( "expected exception" );
    }

    static class HelloImpl
        extends HelloPOA
    {
        HelloImpl( POA poa )
        {
            m_poa = poa;
        }

        private POA m_poa;

        public void hello( String msg )
        {
            System.out.println( msg );
        }

        public POA _default_POA()
        {
            return m_poa;
        }
    }

    public static class EmptyInitializer
        extends org.omg.CORBA.LocalObject
        implements org.omg.PortableInterceptor.ORBInitializer
    {
        public void pre_init( org.omg.PortableInterceptor.ORBInitInfo info )
        {
            try
            {
                s_slotID = info.allocate_slot_id();
                info.add_server_request_interceptor( new EmptyServerInterceptor() );
                info.add_client_request_interceptor( new EmptyClientInterceptor() );
                info.add_ior_interceptor( new EmptyIORInterceptor() );
            }
            catch ( org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName ex )
            {
                fail( "unexpected exception received: " + ex );
            }
        }

        public void post_init( org.omg.PortableInterceptor.ORBInitInfo info )
        {
        }
    }

    static class EmptyIORInterceptor
        extends org.omg.CORBA.LocalObject
        implements org.omg.PortableInterceptor.IORInterceptor
    {
        public String name()
        {
            return "";
        }

        public void destroy()
        {
        }

        public void establish_components( org.omg.PortableInterceptor.IORInfo info )
        {
            info.add_ior_component( new org.omg.IOP.TaggedComponent( TEST_SCID, new byte[ 0 ] ) );
        }
    }

    static class EmptyClientInterceptor
        extends org.omg.CORBA.LocalObject
        implements org.omg.PortableInterceptor.ClientRequestInterceptor
    {
        public String name()
        {
            return "";
        }

        public void destroy()
        {
        }

        public void send_request( org.omg.PortableInterceptor.ClientRequestInfo ri )
            throws org.omg.PortableInterceptor.ForwardRequest
        {
            if ( ( s_visitMask & SEND_REQ ) != 0 )
                s_retryCount++;

            s_visitMask = SEND_REQ;

            if ( s_throwExcept == SEND_REQ )
                throw new org.omg.CORBA.UNKNOWN( SEND_REQ,
                      org.omg.CORBA.CompletionStatus.COMPLETED_NO );

            // add service context.
            try
            {
                if ( ri.get_slot( s_slotID ).type().kind() != org.omg.CORBA.TCKind.tk_null )
                {
                    ri.add_request_service_context( new org.omg.IOP.ServiceContext( TEST_SCID,
                          new byte[ 0 ] ), true );
                    s_visitMask = s_visitMask | SEND_SC;
                }
            }
            catch ( org.omg.PortableInterceptor.InvalidSlot ex )
            {
                fail( ex.toString() );
            }

            // request information.
            ri.request_id();

            assertEquals( "Operation name not correct", ri.operation(), "hello" );

            assertTrue( "No response expected for request with response", ri.response_expected() );

            assertEquals( "Incorrect sync scope", ri.sync_scope(), 3 );

            // target information
            ri.target();

            ri.effective_target();

            ri.effective_profile();

            ri.get_effective_component( org.omg.IOP.TAG_CODE_SETS.value );

            ri.get_effective_components( org.omg.IOP.TAG_CODE_SETS.value );

            try
            {
                ri.arguments();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request parameters. These will always fail
            }

            try
            {
                ri.exceptions();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request parameters. These will always fail
            }

            try
            {
                ri.contexts();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request parameters. These will always fail
            }

            try
            {
                ri.operation_context();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request parameters. These will always fail
            }


            /*
              forward_reference no no no no yes 2
              get_slot yes yes yes yes yes
              get_request_service_context yes no yes yes yes
              get_reply_service_context no no yes yes yes
              get_request_policy yes no yes yes yes
              add_request_service_context yes no no no no
             */
        }

        public void send_poll( org.omg.PortableInterceptor.ClientRequestInfo ri )
        {
            s_visitMask = s_visitMask | SEND_POLL;

            if ( ( s_throwExcept & SEND_POLL ) != 0 )
                throw new org.omg.CORBA.UNKNOWN( SEND_POLL,
                      org.omg.CORBA.CompletionStatus.COMPLETED_YES );
        }

        public void receive_reply( org.omg.PortableInterceptor.ClientRequestInfo ri )
        {
            s_visitMask = s_visitMask | RECV_REPL;

            if ( 0 != ( s_throwExcept & RECV_REPL ) )
                throw new org.omg.CORBA.UNKNOWN( RECV_REPL,
                      org.omg.CORBA.CompletionStatus.COMPLETED_YES );

            try
            {
                ri.get_reply_service_context( TEST_SCID );

                s_visitMask = s_visitMask | RECV_REPL_SC;
            }
            catch ( org.omg.CORBA.BAD_PARAM ex )
            {
                // normal !?
            }

            try
            {
                ri.result();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request results. These will always fail
            }

        }

        public void receive_other( org.omg.PortableInterceptor.ClientRequestInfo ri )
            throws org.omg.PortableInterceptor.ForwardRequest
        {
            s_visitMask = s_visitMask | RECV_OTHR;

            if ( 0 != ( s_throwExcept & RECV_OTHR ) )
                throw new org.omg.CORBA.UNKNOWN( RECV_OTHR,
                      org.omg.CORBA.CompletionStatus.COMPLETED_YES );
        }

        public void receive_exception( org.omg.PortableInterceptor.ClientRequestInfo ri )
            throws org.omg.PortableInterceptor.ForwardRequest
        {
            s_visitMask = s_visitMask | RECV_EXPT;

            if ( ri.received_exception_id().equals( org.omg.CORBA.UNKNOWNHelper.id() )
                    && 0 != ( s_throwExcept & RECV_EXPT ) )
            {
                org.omg.CORBA.UNKNOWN uex = org.omg.CORBA.UNKNOWNHelper.extract(
                      ri.received_exception() );
                uex.minor = uex.minor | RECV_EXPT;
                throw uex;
            }
        }

    }

    static class EmptyServerInterceptor
        extends org.omg.CORBA.LocalObject
        implements org.omg.PortableInterceptor.ServerRequestInterceptor
    {
        public java.lang.String name()
        {
            return "";
        }

        public void destroy()
        {
        }

        public void receive_request_service_contexts(
              org.omg.PortableInterceptor.ServerRequestInfo ri )
            throws org.omg.PortableInterceptor.ForwardRequest
        {
            s_visitMask = s_visitMask | RECV_REQSC;

            if ( 0 != ( s_throwExcept & RECV_REQSC ) )
                throw new org.omg.CORBA.UNKNOWN( RECV_REQSC,
                      org.omg.CORBA.CompletionStatus.COMPLETED_NO );

            // request information.
            ri.request_id();

            assertEquals( "Operation name not correct", ri.operation(), "hello" );

            assertTrue( "No response expected for request with response", ri.response_expected() );

            assertEquals( "Incorrect sync scope", ri.sync_scope(), 3 );

            try
            {
                ri.get_request_service_context( TEST_SCID );

                s_visitMask = s_visitMask | RECV_SC;

                ri.set_slot( s_slotID, s_any );
            }
            catch ( org.omg.CORBA.BAD_PARAM ex )
            {
                // normal !?
            }
            catch ( org.omg.PortableInterceptor.InvalidSlot ex )
            {
                fail( "unexpected exception received: " + ex );
            }

            /*
            reply_status no no yes yes yes
            forward_reference no no no no yes 2
            get_slot yes yes yes yes yes
            get_request_service_context yes yes yes yes yes
            get_reply_service_context no no yes yes yes
            get_server_policy yes yes yes yes yes
            set_slot yes yes yes yes yes
            add_reply_service_context yes yes yes yes yes      
             */
        }

        public void receive_request( org.omg.PortableInterceptor.ServerRequestInfo ri )
            throws org.omg.PortableInterceptor.ForwardRequest
        {
            s_visitMask = s_visitMask | RECV_REQ;

            if ( 0 != ( s_throwExcept & RECV_REQ ) )
                throw new org.omg.CORBA.UNKNOWN( RECV_REQ,
                      org.omg.CORBA.CompletionStatus.COMPLETED_NO );

            // request information
            ri.object_id();

            ri.adapter_id();

            ri.target_most_derived_interface();

            assertTrue( "target does not implement object",
                  ri.target_is_a( "IDL:omg.org/CORBA/Object:1.0" ) );

            try
            {
                ri.arguments();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request parameters. These will always fail
            }

            try
            {
                ri.exceptions();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request parameters. These will always fail
            }

            try
            {
                ri.contexts();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request parameters. These will always fail
            }

            try
            {
                ri.operation_context();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request parameters. These will always fail
            }
        }

        public void send_reply( org.omg.PortableInterceptor.ServerRequestInfo ri )
        {
            s_visitMask = s_visitMask | SEND_REPL;

            if ( 0 != ( s_throwExcept & SEND_REPL ) )
                throw new org.omg.CORBA.UNKNOWN( SEND_REPL,
                      org.omg.CORBA.CompletionStatus.COMPLETED_YES );

            try
            {
                if ( ri.get_slot( s_slotID ).type().kind() != org.omg.CORBA.TCKind.tk_null )
                {
                    ri.add_reply_service_context( new org.omg.IOP.ServiceContext( TEST_SCID,
                          new byte[ 0 ] ), true );

                    s_visitMask = s_visitMask | SEND_REPL_SC;
                }
            }
            catch ( org.omg.PortableInterceptor.InvalidSlot ex )
            {
                fail( ex.toString() );
            }
        }

        public void send_exception( org.omg.PortableInterceptor.ServerRequestInfo ri )
            throws org.omg.PortableInterceptor.ForwardRequest
        {
            s_visitMask = s_visitMask | SEND_EXPT;
            org.omg.CORBA.Any any = ri.sending_exception();

            if ( any.type().equals( org.omg.CORBA.UNKNOWNHelper.type() )
                    && 0 != ( s_throwExcept & SEND_EXPT ) )
            {
                org.omg.CORBA.UNKNOWN uex = org.omg.CORBA.UNKNOWNHelper.extract(
                      ri.sending_exception() );
                uex.minor = uex.minor | SEND_EXPT;
                uex.completed = org.omg.CORBA.CompletionStatus.COMPLETED_YES;
                throw uex;
            }

            try
            {
                ri.result();
            }
            catch ( org.omg.CORBA.NO_RESOURCES ex )
            {
                // test retrieving request results. These will always fail
            }
        }

        public void send_other( org.omg.PortableInterceptor.ServerRequestInfo ri )
            throws org.omg.PortableInterceptor.ForwardRequest
        {
            s_visitMask = s_visitMask | SEND_OTHR;

            if ( 0 != ( s_throwExcept & SEND_OTHR ) )
                throw new org.omg.CORBA.UNKNOWN( SEND_OTHR,
                      org.omg.CORBA.CompletionStatus.COMPLETED_YES );
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( PITest.class ) );
    }
}

