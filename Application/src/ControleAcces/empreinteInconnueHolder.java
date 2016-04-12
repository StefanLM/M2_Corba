package ControleAcces;

/**
 * Holder class for : empreinteInconnue
 * 
 * @author OpenORB Compiler
 */
final public class empreinteInconnueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal empreinteInconnue value
     */
    public ControleAcces.empreinteInconnue value;

    /**
     * Default constructor
     */
    public empreinteInconnueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public empreinteInconnueHolder(ControleAcces.empreinteInconnue initial)
    {
        value = initial;
    }

    /**
     * Read empreinteInconnue from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = empreinteInconnueHelper.read(istream);
    }

    /**
     * Write empreinteInconnue into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        empreinteInconnueHelper.write(ostream,value);
    }

    /**
     * Return the empreinteInconnue TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return empreinteInconnueHelper.type();
    }

}
