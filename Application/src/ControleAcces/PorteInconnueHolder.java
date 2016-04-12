package ControleAcces;

/**
 * Holder class for : PorteInconnue
 * 
 * @author OpenORB Compiler
 */
final public class PorteInconnueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal PorteInconnue value
     */
    public ControleAcces.PorteInconnue value;

    /**
     * Default constructor
     */
    public PorteInconnueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public PorteInconnueHolder(ControleAcces.PorteInconnue initial)
    {
        value = initial;
    }

    /**
     * Read PorteInconnue from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = PorteInconnueHelper.read(istream);
    }

    /**
     * Write PorteInconnue into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        PorteInconnueHelper.write(ostream,value);
    }

    /**
     * Return the PorteInconnue TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return PorteInconnueHelper.type();
    }

}
