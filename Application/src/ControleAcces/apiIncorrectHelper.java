package ControleAcces;

/** 
 * Helper class for : apiIncorrect
 *  
 * @author OpenORB Compiler
 */ 
public class apiIncorrectHelper
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
     * Insert apiIncorrect into an any
     * @param a an any
     * @param t apiIncorrect value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.apiIncorrect t)
    {
        a.insert_Streamable(new ControleAcces.apiIncorrectHolder(t));
    }

    /**
     * Extract apiIncorrect from an any
     * @param a an any
     * @return the extracted apiIncorrect value
     */
    public static ControleAcces.apiIncorrect extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof ControleAcces.apiIncorrectHolder)
                    return ((ControleAcces.apiIncorrectHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            ControleAcces.apiIncorrectHolder h = new ControleAcces.apiIncorrectHolder(read(a.create_input_stream()));
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
     * Return the apiIncorrect TypeCode
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
                _members[0].name = "raison";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _tc = orb.create_exception_tc(id(),"apiIncorrect",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the apiIncorrect IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/apiIncorrect:1.0";

    /**
     * Read apiIncorrect from a marshalled stream
     * @param istream the input stream
     * @return the readed apiIncorrect value
     */
    public static ControleAcces.apiIncorrect read(org.omg.CORBA.portable.InputStream istream)
    {
        ControleAcces.apiIncorrect new_one = new ControleAcces.apiIncorrect();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.raison = istream.read_string();

        return new_one;
    }

    /**
     * Write apiIncorrect into a marshalled stream
     * @param ostream the output stream
     * @param value apiIncorrect value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.apiIncorrect value)
    {
        ostream.write_string(id());
        ostream.write_string(value.raison);
    }

}
