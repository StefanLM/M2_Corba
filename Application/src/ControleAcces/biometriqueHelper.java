package ControleAcces;

/** 
 * Helper class for : biometrique
 *  
 * @author OpenORB Compiler
 */ 
public class biometriqueHelper
{
    /**
     * Insert biometrique into an any
     * @param a an any
     * @param t biometrique value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.biometrique t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract biometrique from an any
     * @param a an any
     * @return the extracted biometrique value
     */
    public static ControleAcces.biometrique extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.biometriqueHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the biometrique TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"biometrique");
        }
        return _tc;
    }

    /**
     * Return the biometrique IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/biometrique:1.0";

    /**
     * Read biometrique from a marshalled stream
     * @param istream the input stream
     * @return the readed biometrique value
     */
    public static ControleAcces.biometrique read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.biometrique)istream.read_Object(ControleAcces._biometriqueStub.class);
    }

    /**
     * Write biometrique into a marshalled stream
     * @param ostream the output stream
     * @param value biometrique value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.biometrique value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to biometrique
     * @param obj the CORBA Object
     * @return biometrique Object
     */
    public static biometrique narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof biometrique)
            return (biometrique)obj;

        if (obj._is_a(id()))
        {
            _biometriqueStub stub = new _biometriqueStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to biometrique
     * @param obj the CORBA Object
     * @return biometrique Object
     */
    public static biometrique unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof biometrique)
            return (biometrique)obj;

        _biometriqueStub stub = new _biometriqueStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
