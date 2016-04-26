package ControleAcces;

/** 
 * Helper class for : Autorisation
 *  
 * @author OpenORB Compiler
 */ 
public class AutorisationHelper
{
    /**
     * Insert Autorisation into an any
     * @param a an any
     * @param t Autorisation value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.Autorisation t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract Autorisation from an any
     * @param a an any
     * @return the extracted Autorisation value
     */
    public static ControleAcces.Autorisation extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.AutorisationHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the Autorisation TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"Autorisation");
        }
        return _tc;
    }

    /**
     * Return the Autorisation IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/Autorisation:1.0";

    /**
     * Read Autorisation from a marshalled stream
     * @param istream the input stream
     * @return the readed Autorisation value
     */
    public static ControleAcces.Autorisation read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.Autorisation)istream.read_Object(ControleAcces._AutorisationStub.class);
    }

    /**
     * Write Autorisation into a marshalled stream
     * @param ostream the output stream
     * @param value Autorisation value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.Autorisation value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to Autorisation
     * @param obj the CORBA Object
     * @return Autorisation Object
     */
    public static Autorisation narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Autorisation)
            return (Autorisation)obj;

        if (obj._is_a(id()))
        {
            _AutorisationStub stub = new _AutorisationStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to Autorisation
     * @param obj the CORBA Object
     * @return Autorisation Object
     */
    public static Autorisation unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Autorisation)
            return (Autorisation)obj;

        _AutorisationStub stub = new _AutorisationStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
