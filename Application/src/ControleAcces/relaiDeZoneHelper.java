package ControleAcces;

/** 
 * Helper class for : RelaiDeZone
 *  
 * @author OpenORB Compiler
 */ 
public class RelaiDeZoneHelper
{
    /**
     * Insert RelaiDeZone into an any
     * @param a an any
     * @param t RelaiDeZone value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.RelaiDeZone t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract RelaiDeZone from an any
     * @param a an any
     * @return the extracted RelaiDeZone value
     */
    public static ControleAcces.RelaiDeZone extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.RelaiDeZoneHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the RelaiDeZone TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"RelaiDeZone");
        }
        return _tc;
    }

    /**
     * Return the RelaiDeZone IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/RelaiDeZone:1.0";

    /**
     * Read RelaiDeZone from a marshalled stream
     * @param istream the input stream
     * @return the readed RelaiDeZone value
     */
    public static ControleAcces.RelaiDeZone read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.RelaiDeZone)istream.read_Object(ControleAcces._RelaiDeZoneStub.class);
    }

    /**
     * Write RelaiDeZone into a marshalled stream
     * @param ostream the output stream
     * @param value RelaiDeZone value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.RelaiDeZone value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to RelaiDeZone
     * @param obj the CORBA Object
     * @return RelaiDeZone Object
     */
    public static RelaiDeZone narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof RelaiDeZone)
            return (RelaiDeZone)obj;

        if (obj._is_a(id()))
        {
            _RelaiDeZoneStub stub = new _RelaiDeZoneStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to RelaiDeZone
     * @param obj the CORBA Object
     * @return RelaiDeZone Object
     */
    public static RelaiDeZone unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof RelaiDeZone)
            return (RelaiDeZone)obj;

        _RelaiDeZoneStub stub = new _RelaiDeZoneStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
