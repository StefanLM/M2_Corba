package ControleAcces;

/**
 * Holder class for : empreinteDejaExistante
 * 
 * @author OpenORB Compiler
 */
final public class empreinteDejaExistanteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal empreinteDejaExistante value
     */
    public ControleAcces.empreinteDejaExistante value;

    /**
     * Default constructor
     */
    public empreinteDejaExistanteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public empreinteDejaExistanteHolder(ControleAcces.empreinteDejaExistante initial)
    {
        value = initial;
    }

    /**
     * Read empreinteDejaExistante from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = empreinteDejaExistanteHelper.read(istream);
    }

    /**
     * Write empreinteDejaExistante into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        empreinteDejaExistanteHelper.write(ostream,value);
    }

    /**
     * Return the empreinteDejaExistante TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return empreinteDejaExistanteHelper.type();
    }

}
