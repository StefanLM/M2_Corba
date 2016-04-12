package ControleAcces;

/**
 * Holder class for : accesRefuse
 * 
 * @author OpenORB Compiler
 */
final public class accesRefuseHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal accesRefuse value
     */
    public ControleAcces.accesRefuse value;

    /**
     * Default constructor
     */
    public accesRefuseHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public accesRefuseHolder(ControleAcces.accesRefuse initial)
    {
        value = initial;
    }

    /**
     * Read accesRefuse from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = accesRefuseHelper.read(istream);
    }

    /**
     * Write accesRefuse into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        accesRefuseHelper.write(ostream,value);
    }

    /**
     * Return the accesRefuse TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return accesRefuseHelper.type();
    }

}
