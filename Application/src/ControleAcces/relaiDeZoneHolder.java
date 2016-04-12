package ControleAcces;

/**
 * Holder class for : relaiDeZone
 * 
 * @author OpenORB Compiler
 */
final public class relaiDeZoneHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal relaiDeZone value
     */
    public ControleAcces.relaiDeZone value;

    /**
     * Default constructor
     */
    public relaiDeZoneHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public relaiDeZoneHolder(ControleAcces.relaiDeZone initial)
    {
        value = initial;
    }

    /**
     * Read relaiDeZone from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = relaiDeZoneHelper.read(istream);
    }

    /**
     * Write relaiDeZone into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        relaiDeZoneHelper.write(ostream,value);
    }

    /**
     * Return the relaiDeZone TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return relaiDeZoneHelper.type();
    }

}
