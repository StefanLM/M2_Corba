package ControleAcces;

/** 
 * Helper class for : PhotoInexistante
 *  
 * @author OpenORB Compiler
 */ 
public class PhotoInexistanteHelper
{
    private static final boolean HAS_OPENORB;
    static {
        boolean hasOpenORB = false;
        try {
            Thread.currentThread().getContextClassLoader().loadClass("org.openorb.CORBA.Any");
            hasOpenORB = true;
        }
        catch(ClassNotFoundException ex) {
        }
        HAS_OPENORB = hasOpenORB;
    }
    /**
     * Insert PhotoInexistante into an any
     * @param a an any
     * @param t PhotoInexistante value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.PhotoInexistante t)
    {
        a.insert_Streamable(new ControleAcces.PhotoInexistanteHolder(t));
    }

    /**
     * Extract PhotoInexistante from an any
     * @param a an any
     * @return the extracted PhotoInexistante value
     */
    public static ControleAcces.PhotoInexistante extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof ControleAcces.PhotoInexistanteHolder)
                    return ((ControleAcces.PhotoInexistanteHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            ControleAcces.PhotoInexistanteHolder h = new ControleAcces.PhotoInexistanteHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;
    private static boolean _working = false;

    /**
     * Return the PhotoInexistante TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            synchronized(org.omg.CORBA.TypeCode.class) {
                if (_tc != null)
                    return _tc;
                if (_working)
                    return org.omg.CORBA.ORB.init().create_recursive_tc(id());
                _working = true;
                org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[1];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "idPhoto";
                _members[0].type = ControleAcces.PhotoHelper.type();
                _tc = orb.create_exception_tc(id(),"PhotoInexistante",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the PhotoInexistante IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/PhotoInexistante:1.0";

    /**
     * Read PhotoInexistante from a marshalled stream
     * @param istream the input stream
     * @return the readed PhotoInexistante value
     */
    public static ControleAcces.PhotoInexistante read(org.omg.CORBA.portable.InputStream istream)
    {
        ControleAcces.PhotoInexistante new_one = new ControleAcces.PhotoInexistante();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.idPhoto = ControleAcces.PhotoHelper.read(istream);

        return new_one;
    }

    /**
     * Write PhotoInexistante into a marshalled stream
     * @param ostream the output stream
     * @param value PhotoInexistante value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.PhotoInexistante value)
    {
        ostream.write_string(id());
        ControleAcces.PhotoHelper.write(ostream,value.idPhoto);
    }

}
