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

public class DIITest
    extends ORBTestCase
{
    public DIITest( String name )
    {
        super( name );
    }

    public void setUp()
    {
        super.setUp();

        try
        {
            m_orb = getORB();

            POA rootPOA = ( POA ) m_orb.resolve_initial_references( "RootPOA" );

            DIITarget svr_ref = ( new DIITargetImpl( rootPOA ) )._this( m_orb );

            rootPOA.the_POAManager().activate();

            m_cltRef = forceMarshal( svr_ref );

            m_any = m_orb.create_any();
        }
        catch ( org.omg.CORBA.UserException ex )
        {
            fail( "exception during setup:" + ex.toString() );
        }
    }

    private org.omg.CORBA.ORB m_orb;
    private org.omg.CORBA.Object m_cltRef;
    private org.omg.CORBA.Any m_any;

    /**
     * Test a simple invocation using the DII. The request is created with no parameter
     * and no result or exception are expected.
     *
     */
    public void testSimpleInvocation()
    {
        org.omg.CORBA.Request req = m_cltRef._request( "hello" );

        req.invoke();

        java.lang.Exception exception = req.env().exception();
        req.env().clear();

        if ( exception != null )
            fail( exception.getMessage() );
    }

    /**
     * Test a simple invocation using the DII with a return parameter. The request is created
     * with no param and a string result is expected. Trying to invoke the operation using the
     * oneway mechanism throws an exception as expected.
     */
    public void testReturnInvocation()
    {
        org.omg.CORBA.Request req = m_cltRef._request( "message" );

        org.omg.CORBA.TypeCode tc_return = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string );

        req.set_return_type( tc_return );

        req.invoke();

        java.lang.Exception exception = req.env().exception();

        if ( exception != null )
            fail( exception.getMessage() );

        org.omg.CORBA.Any result = req.return_value();

        String msg = result.extract_string( );

        req.operation();

        req.arguments();

        req.result();

        req.target();

        req.ctx();
    }

    /**
     * Test a simple invocation using the DII with a return parameter. The request is
     * created with 2 float parameters. A float result is expected. Typing of the
     * result is checked.
     */
    public void testParamInvocation()
    {
        org.omg.CORBA.Request req = m_cltRef._request( "add" );

        org.omg.CORBA.Any param1 = req.add_in_arg( );
        org.omg.CORBA.Any param2 = req.add_in_arg();

        param1.insert_float( 5 );
        param2.insert_float( ( float ) 3.14 );

        org.omg.CORBA.TypeCode tc_return = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float );

        req.set_return_type( tc_return );

        req.invoke();

        java.lang.Exception exception = req.env().exception();

        if ( exception != null )
            fail( exception.getMessage() );

        org.omg.CORBA.Any result = req.return_value();

        float res = result.extract_float( );
    }

    /**
     * Test a simple invocation using the DII with an exception. The request is created with 2 float
     * parameters. A float result is expected. One invocation is made resulting in throwing an
     * an exception and another one without the exception. This method tests both cases.
     *
     * @exception org.omg.CORBA.TypeCodePackage.BadKind if any of the test cases fails
     */
    public void testExceptionInvocation() throws org.omg.CORBA.TypeCodePackage.BadKind
    {
        org.omg.CORBA.Request req = m_cltRef._request( "divide" );

        org.omg.CORBA.Any param1 = req.add_in_arg( );
        org.omg.CORBA.Any param2 = req.add_in_arg();

        param1.insert_float( 5 );
        param2.insert_float( 0 );

        org.omg.CORBA.TypeCode tc_return = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_float );

        req.set_return_type( tc_return );

        org.omg.CORBA.StructMember [ ] members = new org.omg.CORBA.StructMember[ 0 ];

        org.omg.CORBA.TypeCode tc_exception =
              m_orb.create_exception_tc( "IDL:openorb.org/test/dynamic/DIITarget/DivideByZero:1.0",
              "DivideByZero", members );

        req.exceptions().add( tc_exception );

        req.invoke();

        org.omg.CORBA.Any result = req.return_value();

        java.lang.Exception exception = req.env().exception();
        assertNotNull( "Expected exception return", exception );

        if ( exception instanceof org.omg.CORBA.UnknownUserException )
        {
            org.omg.CORBA.UnknownUserException unk_except =
                  ( org.omg.CORBA.UnknownUserException ) exception;

            assertTrue( "Unknown exception instead of DivideByZero exception",
                  unk_except.except.type().id().equals(
                  "IDL:openorb.org/test/dynamic/DIITarget/DivideByZero:1.0" ) );
        }
        else
        {
            throw ( org.omg.CORBA.SystemException ) exception;
        }
    }

    /**
     * Test a invocation using the DII with a context and context operations. The request
     * is created with a test context that was described in the IDL. Several operations are
     * performed on the context, such as setting its value, creating
     * a child context and finally invoking the contructed request.
     */
    public void testContextInvocation()
    {
        org.omg.CORBA.Request req = m_cltRef._request( "clauseContext" );

        req.contexts().add( "Testing" );

        org.omg.CORBA.Context aContext = m_orb.get_default_context( );

        org.omg.CORBA.Any valueCtx = m_orb.create_any( );

        valueCtx.insert_string( "Context value for context 'Testing'. " );

        aContext.set_one_value( "Testing", valueCtx );

        aContext.set_values( m_orb.create_list( 2 ) );

        aContext.context_name();
        org.omg.CORBA.Context parentCtx = aContext.parent();
        org.omg.CORBA.Context childCtx = aContext.create_child( "ChildContext" );

        aContext.delete_values( "None" );

        req.ctx( aContext );

        req.invoke();

        java.lang.Exception exception = req.env().exception();

        if ( exception != null )
            fail( exception.getMessage() );
    }

    /**
     * Test an attribute invocation using the DII. As an attribute was declared in the IDL,
     * the request is created and invoked for the _get_XXX and _set_XXX generated operations.
     * Tests are performed to check good behaviour of these operations.
     */
    public void testAttributeInvocation()
    {
        org.omg.CORBA.Request req_read = m_cltRef._request( "_get_name" );

        org.omg.CORBA.Request req_write = m_cltRef._request( "_set_name" );

        org.omg.CORBA.TypeCode tc_string = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string );

        req_read.set_return_type( tc_string );

        req_read.invoke();

        org.omg.CORBA.Any result = req_read.return_value();

        result.extract_string();

        org.omg.CORBA.Any param = req_write.add_in_arg();

        param.insert_string( "NewName" );

        req_write.invoke( );
    }


    /**
     * Test a oneway invocation.
     */
    public void testOnewayInvocation()
    {
        org.omg.CORBA.Request req = m_cltRef._request( "hello_oneway" );

        req.send_oneway();

        java.lang.Exception exception = req.env().exception();
        req.env().clear();

        if ( exception != null )
            fail( exception.getMessage() );
    }

    /**
     * Test sending multiple oneway requests.
     */
    public void testMultipleOnewayInvocation()
    {
        org.omg.CORBA.Request [] req = new org.omg.CORBA.Request[ 5 ];

        for ( int i = 0; i < req.length; ++i )
            req[ i ] = m_cltRef._request( "hello_oneway" );

        m_orb.send_multiple_requests_oneway( req );

        for ( int i = 0; i < req.length; ++i )
        {
            java.lang.Exception exception = req[ i ].env().exception();
            req[ i ].env().clear();

            if ( exception != null )
                fail( exception.getMessage() );
        }
    }

    /**
     * Test a deferred invocation using the DII. A deferred request may be
     * delayed by the ORB and the result is available later. This test invokes
     * a request as delayed, and then gets the response using the get_response
     * function.
     *
     * @exception org.omg.CORBA.UserException if any of the test cases fails
     */
    public void testDeferredInvocation() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.Request req = m_cltRef._request( "message" );

        org.omg.CORBA.TypeCode tc_return = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string );

        req.set_return_type( tc_return );

        req.send_deferred();

        req.get_response();

        org.omg.CORBA.Any result = req.return_value();

        String msg = result.extract_string( );
    }

    /**
     * Test a deferred invocation using the DII. A deferred request may be
     * delayed by the ORB and the result is available later. This test invokes
     * a request as delayed, polls until a response arrives and then gets the
     * response using the get_response function.
     *
     * @exception org.omg.CORBA.UserException if any of the test cases fails
     */
    public void testPollingInvocation() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.Request req = m_cltRef._request( "message" );

        org.omg.CORBA.TypeCode tc_return = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string );

        req.set_return_type( tc_return );

        req.send_deferred();

        try
        {
            while ( !req.poll_response() )
                Thread.sleep( 500 );
        }
        catch ( InterruptedException ex )
        {
            // catch to make javac happy
        }

        req.get_response();

        org.omg.CORBA.Any result = req.return_value();

        String msg = result.extract_string( );
    }

    private org.omg.CORBA.Request m_crossThreadReq;
    private RuntimeException m_crossThreadException;

    /**
     * Test a deferred invocation using the DII. A deferred request may be
     * delayed by the ORB and the result is available later. This test invokes
     * a request as delayed, and then gets the response using the get_response
     * function in a different thread.
     *
     * @exception org.omg.CORBA.UserException if any of the test cases fails
     */
    public void testDeferredCrossThreadInvocation() throws org.omg.CORBA.UserException
    {
        m_crossThreadReq = m_cltRef._request( "message" );

        org.omg.CORBA.TypeCode tc_return = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string );

        m_crossThreadReq.set_return_type( tc_return );

        m_crossThreadReq.send_deferred();

        Thread poll = new Thread( new Runnable()
                                  {
                                      public void run()
                                      {
                                          try
                                          {
                                              while ( !m_crossThreadReq.poll_response() )
                                                  Thread.sleep( 500 );
                                          }
                                          catch ( RuntimeException ex )
                                          {
                                              m_crossThreadException = ex;
                                          }
                                          catch ( InterruptedException ex )
                                          {
                                              // catch to make javac happy
                                          }
                                      }

                                  }

                                );

        poll.start();

        try
        {
            poll.join();
        }
        catch ( InterruptedException ex )
        {
            // catch to make javac happy
        }


        if ( m_crossThreadException != null )
            throw m_crossThreadException;

        Thread getResponse = new Thread( new Runnable()
                                         {
                                             public void run()
                                             {
                                                 try
                                                 {
                                                     m_crossThreadReq.get_response();
                                                 }
                                                 catch ( RuntimeException ex )
                                                 {
                                                     m_crossThreadException = ex;
                                                 }
                                                 catch ( Exception ex )
                                                 {
                                                     fail( "Unexpected exception caught: " + ex );
                                                 }
                                             }
                                         }
                                       );
        getResponse.start();

        try
        {
            poll.join();
        }
        catch ( InterruptedException ex )
        {
            // catch to make javac happy
        }

        if ( m_crossThreadException != null )
            throw m_crossThreadException;

        org.omg.CORBA.Any result = m_crossThreadReq.return_value();

        String msg = result.extract_string( );
    }

    /**
     * Test sending multiple defered requests, waiting for responses to arrive,
     * and recieving each response.
     *
     * @exception org.omg.CORBA.UserException if any of the test cases fails
     */
    public void testMultipleDeferredInvocations() throws org.omg.CORBA.UserException
    {
        org.omg.CORBA.Request [] req = new org.omg.CORBA.Request[ 5 ];

        org.omg.CORBA.TypeCode tc_return = m_orb.get_primitive_tc( org.omg.CORBA.TCKind.tk_string );

        for ( int i = 0; i < req.length; ++i )
        {
            req[ i ] = m_cltRef._request( "message" );
            req[ i ].set_return_type( tc_return );
        }

        m_orb.send_multiple_requests_deferred( req );

        for ( int i = 0; i < req.length; ++i )
        {
            try
            {
                while ( !m_orb.poll_next_response() )
                    Thread.sleep( 500 );
            }
            catch ( InterruptedException ex )
            {
                // catch to make javac happy
            }

            org.omg.CORBA.Request next = m_orb.get_next_response();

            org.omg.CORBA.Any result = next.return_value();

            String msg = result.extract_string( );
        }
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
                //System.out.println("No value found");
            }

        }

        public String [] sequenceTest( String [] sequence )
        {
            return sequence;
        }

        public org.openorb.test.dynamic.Person structTest( org.openorb.test.dynamic.Person p )
        {
            return p;
        }
    }

    public static void main( String args[] )
    {
        junit.textui.TestRunner.run( new TestSuite( DIITest.class ) );
    }
}

