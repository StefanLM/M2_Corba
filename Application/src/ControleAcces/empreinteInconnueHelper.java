package ControleAcces;

/** 
 * Helper class for : empreinteInconnue
 *  
 * @author OpenORB Compiler
 */ 
public class empreinteInconnueHelper
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
     * Insert empreinteInconnue into an any
     * @param a an any
     * @param t empreinteInconnue value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.empreinteInconnue t)
    {
        a.insert_Streamable(new ControleAcces.empreinteInconnueHolder(t));
    }

    /**
     * Extract empreinteInconnue from an any
     * @param a an any
     * @return the extracted empreinteInconnue value
     */
    public static ControleAcces.empreinteInconnue extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof ControleAcces.empreinteInconnueHolder)
                    return ((ControleAcces.empreinteInconnueHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            ControleAcces.empreinteInconnueHolder h = new ControleAcces.empreinteInconnueHolder(read(a.create_input_stream()));
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
     * Return the empreinteInconnue TypeCode
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
                _tc = orb.create_exception_tc(id(),"empreinteInconnue",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the empreinteInconnue IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/empreinteInconnue:1.0";

    /**
     * Read empreinteInconnue from a marshalled stream
     * @param istream the input stream
     * @return the readed empreinteInconnue value
     */
    public static ControleAcces.empreinteInconnue read(org.omg.CORBA.portable.InputStream istream)
    {
        ControleAcces.empreinteInconnue new_one = new ControleAcces.empreinteInconnue();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.empreinte = ControleAcces.EmpreinteHelper.read(istream);

        return new_one;
    }

    /**
     * Write empreinteInconnue into a marshalled stream
     * @param ostream the output stream
     * @param value empreinteInconnue value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.empreinteInconnue value)
    {
        ostream.write_string(id());
        ControleAcces.EmpreinteHelper.write(ostream,value.empreinte);
    }

}
