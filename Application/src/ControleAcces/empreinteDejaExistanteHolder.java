package ControleAcces;

/**
 * Holder class for : EmpreinteDejaExistante
 * 
 * @author OpenORB Compiler
 */
final public class EmpreinteDejaExistanteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal EmpreinteDejaExistante value
     */
    public ControleAcces.EmpreinteDejaExistante value;

    /**
     * Default constructor
     */
    public EmpreinteDejaExistanteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public EmpreinteDejaExistanteHolder(ControleAcces.EmpreinteDejaExistante initial)
    {
        value = initial;
    }

    /**
     * Read EmpreinteDejaExistante from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = EmpreinteDejaExistanteHelper.read(istream);
    }

    /**
     * Write EmpreinteDejaExistante into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        EmpreinteDejaExistanteHelper.write(ostream,value);
    }

    /**
     * Return the EmpreinteDejaExistante TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return EmpreinteDejaExistanteHelper.type();
    }

}
