package ControleAcces;

/**
 * Holder class for : aucunLogDisponible
 * 
 * @author OpenORB Compiler
 */
final public class aucunLogDisponibleHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal aucunLogDisponible value
     */
    public ControleAcces.aucunLogDisponible value;

    /**
     * Default constructor
     */
    public aucunLogDisponibleHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public aucunLogDisponibleHolder(ControleAcces.aucunLogDisponible initial)
    {
        value = initial;
    }

    /**
     * Read aucunLogDisponible from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = aucunLogDisponibleHelper.read(istream);
    }

    /**
     * Write aucunLogDisponible into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        aucunLogDisponibleHelper.write(ostream,value);
    }

    /**
     * Return the aucunLogDisponible TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return aucunLogDisponibleHelper.type();
    }

}
