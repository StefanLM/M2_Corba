package ControleAcces;

/**
 * Holder class for : zoneInconnue
 * 
 * @author OpenORB Compiler
 */
final public class zoneInconnueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal zoneInconnue value
     */
    public ControleAcces.zoneInconnue value;

    /**
     * Default constructor
     */
    public zoneInconnueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public zoneInconnueHolder(ControleAcces.zoneInconnue initial)
    {
        value = initial;
    }

    /**
     * Read zoneInconnue from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = zoneInconnueHelper.read(istream);
    }

    /**
     * Write zoneInconnue into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        zoneInconnueHelper.write(ostream,value);
    }

    /**
     * Return the zoneInconnue TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return zoneInconnueHelper.type();
    }

}
