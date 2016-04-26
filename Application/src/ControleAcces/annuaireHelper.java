package ControleAcces;

/** 
 * Helper class for : Annuaire
 *  
 * @author OpenORB Compiler
 */ 
public class AnnuaireHelper
{
    /**
     * Insert Annuaire into an any
     * @param a an any
     * @param t Annuaire value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.Annuaire t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract Annuaire from an any
     * @param a an any
     * @return the extracted Annuaire value
     */
    public static ControleAcces.Annuaire extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ControleAcces.AnnuaireHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the Annuaire TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"Annuaire");
        }
        return _tc;
    }

    /**
     * Return the Annuaire IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/Annuaire:1.0";

    /**
     * Read Annuaire from a marshalled stream
     * @param istream the input stream
     * @return the readed Annuaire value
     */
    public static ControleAcces.Annuaire read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ControleAcces.Annuaire)istream.read_Object(ControleAcces._AnnuaireStub.class);
    }

    /**
     * Write Annuaire into a marshalled stream
     * @param ostream the output stream
     * @param value Annuaire value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.Annuaire value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to Annuaire
     * @param obj the CORBA Object
     * @return Annuaire Object
     */
    public static Annuaire narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Annuaire)
            return (Annuaire)obj;

        if (obj._is_a(id()))
        {
            _AnnuaireStub stub = new _AnnuaireStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to Annuaire
     * @param obj the CORBA Object
     * @return Annuaire Object
     */
    public static Annuaire unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Annuaire)
            return (Annuaire)obj;

        _AnnuaireStub stub = new _AnnuaireStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
