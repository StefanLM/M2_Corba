package ControleAcces;

/** 
 * Helper class for : logs
 *  
 * @author OpenORB Compiler
 */ 
public class logsHelper
{
    /**
     * Insert logs into an any
     * @param a an any
     * @param t logs value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.logs t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract logs from an any
     * @param a an any
     * @return the extracted logs value
     */
    public static ControleAcces.logs extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.logsHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the logs TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"logs");
        }
        return _tc;
    }

    /**
     * Return the logs IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/logs:1.0";

    /**
     * Read logs from a marshalled stream
     * @param istream the input stream
     * @return the readed logs value
     */
    public static ControleAcces.logs read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.logs)istream.read_Object(ControleAcces._logsStub.class);
    }

    /**
     * Write logs into a marshalled stream
     * @param ostream the output stream
     * @param value logs value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.logs value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to logs
     * @param obj the CORBA Object
     * @return logs Object
     */
    public static logs narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof logs)
            return (logs)obj;

        if (obj._is_a(id()))
        {
            _logsStub stub = new _logsStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to logs
     * @param obj the CORBA Object
     * @return logs Object
     */
    public static logs unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof logs)
            return (logs)obj;

        _logsStub stub = new _logsStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
