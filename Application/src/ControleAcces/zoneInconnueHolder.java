package ControleAcces;

/**
 * Holder class for : ZoneInconnue
 * 
 * @author OpenORB Compiler
 */
final public class ZoneInconnueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal ZoneInconnue value
     */
    public ControleAcces.ZoneInconnue value;

    /**
     * Default constructor
     */
    public ZoneInconnueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public ZoneInconnueHolder(ControleAcces.ZoneInconnue initial)
    {
        value = initial;
    }

    /**
     * Read ZoneInconnue from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = ZoneInconnueHelper.read(istream);
    }

    /**
     * Write ZoneInconnue into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        ZoneInconnueHelper.write(ostream,value);
    }

    /**
     * Return the ZoneInconnue TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return ZoneInconnueHelper.type();
    }

}
