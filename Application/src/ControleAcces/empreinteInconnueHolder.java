package ControleAcces;

/**
 * Holder class for : EmpreinteInconnue
 * 
 * @author OpenORB Compiler
 */
final public class EmpreinteInconnueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal EmpreinteInconnue value
     */
    public ControleAcces.EmpreinteInconnue value;

    /**
     * Default constructor
     */
    public EmpreinteInconnueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public EmpreinteInconnueHolder(ControleAcces.EmpreinteInconnue initial)
    {
        value = initial;
    }

    /**
     * Read EmpreinteInconnue from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = EmpreinteInconnueHelper.read(istream);
    }

    /**
     * Write EmpreinteInconnue into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        EmpreinteInconnueHelper.write(ostream,value);
    }

    /**
     * Return the EmpreinteInconnue TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return EmpreinteInconnueHelper.type();
    }

}
