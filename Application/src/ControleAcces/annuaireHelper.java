package ControleAcces;

/** 
 * Helper class for : annuaire
 *  
 * @author OpenORB Compiler
 */ 
public class annuaireHelper
{
    /**
     * Insert annuaire into an any
     * @param a an any
     * @param t annuaire value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.annuaire t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract annuaire from an any
     * @param a an any
     * @return the extracted annuaire value
     */
    public static ControleAcces.annuaire extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.annuaireHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the annuaire TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"annuaire");
        }
        return _tc;
    }

    /**
     * Return the annuaire IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/annuaire:1.0";

    /**
     * Read annuaire from a marshalled stream
     * @param istream the input stream
     * @return the readed annuaire value
     */
    public static ControleAcces.annuaire read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.annuaire)istream.read_Object(ControleAcces._annuaireStub.class);
    }

    /**
     * Write annuaire into a marshalled stream
     * @param ostream the output stream
     * @param value annuaire value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.annuaire value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to annuaire
     * @param obj the CORBA Object
     * @return annuaire Object
     */
    public static annuaire narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof annuaire)
            return (annuaire)obj;

        if (obj._is_a(id()))
        {
            _annuaireStub stub = new _annuaireStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to annuaire
     * @param obj the CORBA Object
     * @return annuaire Object
     */
    public static annuaire unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof annuaire)
            return (annuaire)obj;

        _annuaireStub stub = new _annuaireStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
