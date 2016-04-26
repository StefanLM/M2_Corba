package ControleAcces;

/** 
 * Helper class for : Logs
 *  
 * @author OpenORB Compiler
 */ 
public class LogsHelper
{
    /**
     * Insert Logs into an any
     * @param a an any
     * @param t Logs value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.Logs t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract Logs from an any
     * @param a an any
     * @return the extracted Logs value
     */
    public static ControleAcces.Logs extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.LogsHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the Logs TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"Logs");
        }
        return _tc;
    }

    /**
     * Return the Logs IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/Logs:1.0";

    /**
     * Read Logs from a marshalled stream
     * @param istream the input stream
     * @return the readed Logs value
     */
    public static ControleAcces.Logs read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.Logs)istream.read_Object(ControleAcces._LogsStub.class);
    }

    /**
     * Write Logs into a marshalled stream
     * @param ostream the output stream
     * @param value Logs value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.Logs value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to Logs
     * @param obj the CORBA Object
     * @return Logs Object
     */
    public static Logs narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Logs)
            return (Logs)obj;

        if (obj._is_a(id()))
        {
            _LogsStub stub = new _LogsStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to Logs
     * @param obj the CORBA Object
     * @return Logs Object
     */
    public static Logs unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Logs)
            return (Logs)obj;

        _LogsStub stub = new _LogsStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
