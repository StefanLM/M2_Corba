package ControleAcces;

/** 
 * Helper class for : relaiDeZone
 *  
 * @author OpenORB Compiler
 */ 
public class relaiDeZoneHelper
{
    /**
     * Insert relaiDeZone into an any
     * @param a an any
     * @param t relaiDeZone value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.relaiDeZone t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract relaiDeZone from an any
     * @param a an any
     * @return the extracted relaiDeZone value
     */
    public static ControleAcces.relaiDeZone extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.relaiDeZoneHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the relaiDeZone TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"relaiDeZone");
        }
        return _tc;
    }

    /**
     * Return the relaiDeZone IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/relaiDeZone:1.0";

    /**
     * Read relaiDeZone from a marshalled stream
     * @param istream the input stream
     * @return the readed relaiDeZone value
     */
    public static ControleAcces.relaiDeZone read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.relaiDeZone)istream.read_Object(ControleAcces._relaiDeZoneStub.class);
    }

    /**
     * Write relaiDeZone into a marshalled stream
     * @param ostream the output stream
     * @param value relaiDeZone value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.relaiDeZone value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to relaiDeZone
     * @param obj the CORBA Object
     * @return relaiDeZone Object
     */
    public static relaiDeZone narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof relaiDeZone)
            return (relaiDeZone)obj;

        if (obj._is_a(id()))
        {
            _relaiDeZoneStub stub = new _relaiDeZoneStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to relaiDeZone
     * @param obj the CORBA Object
     * @return relaiDeZone Object
     */
    public static relaiDeZone unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof relaiDeZone)
            return (relaiDeZone)obj;

        _relaiDeZoneStub stub = new _relaiDeZoneStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
