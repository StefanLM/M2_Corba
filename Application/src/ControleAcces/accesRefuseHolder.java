package ControleAcces;

/**
 * Holder class for : AccesRefuse
 * 
 * @author OpenORB Compiler
 */
final public class AccesRefuseHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal AccesRefuse value
     */
    public ControleAcces.AccesRefuse value;

    /**
     * Default constructor
     */
    public AccesRefuseHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public AccesRefuseHolder(ControleAcces.AccesRefuse initial)
    {
        value = initial;
    }

    /**
     * Read AccesRefuse from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = AccesRefuseHelper.read(istream);
    }

    /**
     * Write AccesRefuse into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        AccesRefuseHelper.write(ostream,value);
    }

    /**
     * Return the AccesRefuse TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return AccesRefuseHelper.type();
    }

}
