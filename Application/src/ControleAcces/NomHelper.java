package ControleAcces;

/** 
 * Helper class for : Nom
 *  
 * @author OpenORB Compiler
 */ 
public class NomHelper
{
    /**
     * Insert Nom into an any
     * @param a an any
     * @param t Nom value
     */
    public static void insert(org.omg.CORBA.Any a, String t)
    {
        a.type(type());
        write(a.create_output_stream(),t);
    }

    /**
     * Extract Nom from an any
     * @param a an any
     * @return the extracted Nom value
     */
    public static String extract(org.omg.CORBA.Any a)
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
     * Return the Nom TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_alias_tc(id(),"Nom",orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string));
        }
        return _tc;
    }

    /**
     * Return the Nom IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/Nom:1.0";

    /**
     * Read Nom from a marshalled stream
     * @param istream the input stream
     * @return the readed Nom value
     */
    public static String read(org.omg.CORBA.portable.InputStream istream)
    {
        String new_one;
        new_one = istream.read_string();

        return new_one;
    }

    /**
     * Write Nom into a marshalled stream
     * @param ostream the output stream
     * @param value Nom value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, String value)
    {
        ostream.write_string(value);
    }

}
