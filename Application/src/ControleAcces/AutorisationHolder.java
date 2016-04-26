package ControleAcces;

/**
 * Holder class for : Autorisation
 * 
 * @author OpenORB Compiler
 */
final public class AutorisationHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Autorisation value
     */
    public ControleAcces.Autorisation value;

    /**
     * Default constructor
     */
    public AutorisationHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public AutorisationHolder(ControleAcces.Autorisation initial)
    {
        value = initial;
    }

    /**
     * Read Autorisation from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = AutorisationHelper.read(istream);
    }

    /**
     * Write Autorisation into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        AutorisationHelper.write(ostream,value);
    }

    /**
     * Return the Autorisation TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return AutorisationHelper.type();
    }

}
