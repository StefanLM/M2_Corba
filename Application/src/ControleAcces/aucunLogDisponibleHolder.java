package ControleAcces;

/**
 * Holder class for : AucunLogDisponible
 * 
 * @author OpenORB Compiler
 */
final public class AucunLogDisponibleHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal AucunLogDisponible value
     */
    public ControleAcces.AucunLogDisponible value;

    /**
     * Default constructor
     */
    public AucunLogDisponibleHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public AucunLogDisponibleHolder(ControleAcces.AucunLogDisponible initial)
    {
        value = initial;
    }

    /**
     * Read AucunLogDisponible from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = AucunLogDisponibleHelper.read(istream);
    }

    /**
     * Write AucunLogDisponible into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        AucunLogDisponibleHelper.write(ostream,value);
    }

    /**
     * Return the AucunLogDisponible TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return AucunLogDisponibleHelper.type();
    }

}
