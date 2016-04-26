package ControleAcces;

/**
 * Holder class for : Biometrique
 * 
 * @author OpenORB Compiler
 */
final public class BiometriqueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Biometrique value
     */
    public ControleAcces.Biometrique value;

    /**
     * Default constructor
     */
    public BiometriqueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public BiometriqueHolder(ControleAcces.Biometrique initial)
    {
        value = initial;
    }

    /**
     * Read Biometrique from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = BiometriqueHelper.read(istream);
    }

    /**
     * Write Biometrique into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        BiometriqueHelper.write(ostream,value);
    }

    /**
     * Return the Biometrique TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return BiometriqueHelper.type();
    }

}
