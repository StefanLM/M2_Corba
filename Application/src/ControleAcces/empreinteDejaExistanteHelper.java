package ControleAcces;

/** 
 * Helper class for : empreinteDejaExistante
 *  
 * @author OpenORB Compiler
 */ 
public class empreinteDejaExistanteHelper
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
     * Insert empreinteDejaExistante into an any
     * @param a an any
     * @param t empreinteDejaExistante value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.empreinteDejaExistante t)
    {
        a.insert_Streamable(new ControleAcces.empreinteDejaExistanteHolder(t));
    }

    /**
     * Extract empreinteDejaExistante from an any
     * @param a an any
     * @return the extracted empreinteDejaExistante value
     */
    public static ControleAcces.empreinteDejaExistante extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof ControleAcces.empreinteDejaExistanteHolder)
                    return ((ControleAcces.empreinteDejaExistanteHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            ControleAcces.empreinteDejaExistanteHolder h = new ControleAcces.empreinteDejaExistanteHolder(read(a.create_input_stream()));
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
     * Return the empreinteDejaExistante TypeCode
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
                _members[0].name = "empreinte";
                _members[0].type = ControleAcces.EmpreinteHelper.type();
                _tc = orb.create_exception_tc(id(),"empreinteDejaExistante",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the empreinteDejaExistante IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/empreinteDejaExistante:1.0";

    /**
     * Read empreinteDejaExistante from a marshalled stream
     * @param istream the input stream
     * @return the readed empreinteDejaExistante value
     */
    public static ControleAcces.empreinteDejaExistante read(org.omg.CORBA.portable.InputStream istream)
    {
        ControleAcces.empreinteDejaExistante new_one = new ControleAcces.empreinteDejaExistante();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.empreinte = ControleAcces.EmpreinteHelper.read(istream);

        return new_one;
    }

    /**
     * Write empreinteDejaExistante into a marshalled stream
     * @param ostream the output stream
     * @param value empreinteDejaExistante value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.empreinteDejaExistante value)
    {
        ostream.write_string(id());
        ControleAcces.EmpreinteHelper.write(ostream,value.empreinte);
    }

}
