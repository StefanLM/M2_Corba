package ControleAcces;

/** 
 * Helper class for : SequenceIdEmpreinte
 *  
 * @author OpenORB Compiler
 */ 
public class SequenceIdEmpreinteHelper
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
     * Insert SequenceIdEmpreinte into an any
     * @param a an any
     * @param t SequenceIdEmpreinte value
     */
    public static void insert(org.omg.CORBA.Any a, String[] t)
    {
        a.insert_Streamable(new ControleAcces.SequenceIdEmpreinteHolder(t));
    }

    /**
     * Extract SequenceIdEmpreinte from an any
     * @param a an any
     * @return the extracted SequenceIdEmpreinte value
     */
    public static String[] extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if(HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof ControleAcces.SequenceIdEmpreinteHolder)
                    return ((ControleAcces.SequenceIdEmpreinteHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            ControleAcces.SequenceIdEmpreinteHolder h = new ControleAcces.SequenceIdEmpreinteHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the SequenceIdEmpreinte TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_alias_tc(id(),"SequenceIdEmpreinte",orb.create_sequence_tc(0,ControleAcces.IdEmpreinteHelper.type()));
        }
        return _tc;
    }

    /**
     * Return the SequenceIdEmpreinte IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/SequenceIdEmpreinte:1.0";

    /**
     * Read SequenceIdEmpreinte from a marshalled stream
     * @param istream the input stream
     * @return the readed SequenceIdEmpreinte value
     */
    public static String[] read(org.omg.CORBA.portable.InputStream istream)
    {
        String[] new_one;
        {
        int size7 = istream.read_ulong();
        new_one = new String[size7];
        for (int i7=0; i7<new_one.length; i7++)
         {
            new_one[i7] = ControleAcces.IdEmpreinteHelper.read(istream);

         }
        }

        return new_one;
    }

    /**
     * Write SequenceIdEmpreinte into a marshalled stream
     * @param ostream the output stream
     * @param value SequenceIdEmpreinte value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, String[] value)
    {
        ostream.write_ulong(value.length);
        for (int i7=0; i7<value.length; i7++)
        {
            ControleAcces.IdEmpreinteHelper.write(ostream,value[i7]);

        }
    }

}
