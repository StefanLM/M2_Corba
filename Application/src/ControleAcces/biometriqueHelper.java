package ControleAcces;

/** 
 * Helper class for : Biometrique
 *  
 * @author OpenORB Compiler
 */ 
public class BiometriqueHelper
{
    /**
     * Insert Biometrique into an any
     * @param a an any
     * @param t Biometrique value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.Biometrique t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract Biometrique from an any
     * @param a an any
     * @return the extracted Biometrique value
     */
    public static ControleAcces.Biometrique extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.BiometriqueHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the Biometrique TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"Biometrique");
        }
        return _tc;
    }

    /**
     * Return the Biometrique IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/Biometrique:1.0";

    /**
     * Read Biometrique from a marshalled stream
     * @param istream the input stream
     * @return the readed Biometrique value
     */
    public static ControleAcces.Biometrique read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.Biometrique)istream.read_Object(ControleAcces._BiometriqueStub.class);
    }

    /**
     * Write Biometrique into a marshalled stream
     * @param ostream the output stream
     * @param value Biometrique value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.Biometrique value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to Biometrique
     * @param obj the CORBA Object
     * @return Biometrique Object
     */
    public static Biometrique narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Biometrique)
            return (Biometrique)obj;

        if (obj._is_a(id()))
        {
            _BiometriqueStub stub = new _BiometriqueStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to Biometrique
     * @param obj the CORBA Object
     * @return Biometrique Object
     */
    public static Biometrique unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Biometrique)
            return (Biometrique)obj;

        _BiometriqueStub stub = new _BiometriqueStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
