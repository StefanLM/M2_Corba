package ControleAcces;

/**
 * Holder class for : RelaiDeZone
 * 
 * @author OpenORB Compiler
 */
final public class RelaiDeZoneHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal RelaiDeZone value
     */
    public ControleAcces.RelaiDeZone value;

    /**
     * Default constructor
     */
    public RelaiDeZoneHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public RelaiDeZoneHolder(ControleAcces.RelaiDeZone initial)
    {
        value = initial;
    }

    /**
     * Read RelaiDeZone from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = RelaiDeZoneHelper.read(istream);
    }

    /**
     * Write RelaiDeZone into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        RelaiDeZoneHelper.write(ostream,value);
    }

    /**
     * Return the RelaiDeZone TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return RelaiDeZoneHelper.type();
    }

}
