package ControleAcces;

/** 
 * Helper class for : IdPorte
 *  
 * @author OpenORB Compiler
 */ 
public class IdPorteHelper
{
    /**
     * Insert IdPorte into an any
     * @param a an any
     * @param t IdPorte value
     */
    public static void insert(org.omg.CORBA.Any a, int t)
    {
        a.type(type());
        write(a.create_output_stream(),t);
    }

    /**
     * Extract IdPorte from an any
     * @param a an any
     * @return the extracted IdPorte value
     */
    public static int extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the IdPorte TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_alias_tc(id(),"IdPorte",orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_long));
        }
        return _tc;
    }

    /**
     * Return the IdPorte IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/IdPorte:1.0";

    /**
     * Read IdPorte from a marshalled stream
     * @param istream the input stream
     * @return the readed IdPorte value
     */
    public static int read(org.omg.CORBA.portable.InputStream istream)
    {
        int new_one;
        new_one = istream.read_long();

        return new_one;
    }

    /**
     * Write IdPorte into a marshalled stream
     * @param ostream the output stream
     * @param value IdPorte value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, int value)
    {
        ostream.write_long(value);
    }

}
