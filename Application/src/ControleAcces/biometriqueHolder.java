package ControleAcces;

/**
 * Holder class for : biometrique
 * 
 * @author OpenORB Compiler
 */
final public class biometriqueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal biometrique value
     */
    public ControleAcces.biometrique value;

    /**
     * Default constructor
     */
    public biometriqueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public biometriqueHolder(ControleAcces.biometrique initial)
    {
        value = initial;
    }

    /**
     * Read biometrique from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = biometriqueHelper.read(istream);
    }

    /**
     * Write biometrique into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        biometriqueHelper.write(ostream,value);
    }

    /**
     * Return the biometrique TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return biometriqueHelper.type();
    }

}
