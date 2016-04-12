package ControleAcces;

/** 
 * Helper class for : Log
 *  
 * @author OpenORB Compiler
 */ 
public class LogHelper
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
     * Insert Log into an any
     * @param a an any
     * @param t Log value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.Log t)
    {
        a.insert_Streamable(new ControleAcces.LogHolder(t));
    }

    /**
     * Extract Log from an any
     * @param a an any
     * @return the extracted Log value
     */
    public static ControleAcces.Log extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof ControleAcces.LogHolder)
                    return ((ControleAcces.LogHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            ControleAcces.LogHolder h = new ControleAcces.LogHolder(read(a.create_input_stream()));
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
     * Return the Log TypeCode
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
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[4];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "idLog";
                _members[0].type = ControleAcces.IdLogHelper.type();
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "typeLog";
                _members[1].type = ControleAcces.TypeLogHelper.type();
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "dateLog";
                _members[2].type = ControleAcces.DateHelper.type();
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "descriptionLog";
                _members[3].type = ControleAcces.DescriptionLogHelper.type();
                _tc = orb.create_struct_tc(id(),"Log",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the Log IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/Log:1.0";

    /**
     * Read Log from a marshalled stream
     * @param istream the input stream
     * @return the readed Log value
     */
    public static ControleAcces.Log read(org.omg.CORBA.portable.InputStream istream)
    {
        ControleAcces.Log new_one = new ControleAcces.Log();

        new_one.idLog = ControleAcces.IdLogHelper.read(istream);
        new_one.typeLog = ControleAcces.TypeLogHelper.read(istream);
        new_one.dateLog = ControleAcces.DateHelper.read(istream);
        new_one.descriptionLog = ControleAcces.DescriptionLogHelper.read(istream);

        return new_one;
    }

    /**
     * Write Log into a marshalled stream
     * @param ostream the output stream
     * @param value Log value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.Log value)
    {
        ControleAcces.IdLogHelper.write(ostream,value.idLog);
        ControleAcces.TypeLogHelper.write(ostream,value.typeLog);
        ControleAcces.DateHelper.write(ostream,value.dateLog);
        ControleAcces.DescriptionLogHelper.write(ostream,value.descriptionLog);
    }

}
