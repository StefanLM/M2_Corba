package ControleAcces;

/**
 * Holder class for : EcritureImpossible
 * 
 * @author OpenORB Compiler
 */
final public class EcritureImpossibleHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal EcritureImpossible value
     */
    public ControleAcces.EcritureImpossible value;

    /**
     * Default constructor
     */
    public EcritureImpossibleHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public EcritureImpossibleHolder(ControleAcces.EcritureImpossible initial)
    {
        value = initial;
    }

    /**
     * Read EcritureImpossible from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = EcritureImpossibleHelper.read(istream);
    }

    /**
     * Write EcritureImpossible into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        EcritureImpossibleHelper.write(ostream,value);
    }

    /**
     * Return the EcritureImpossible TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return EcritureImpossibleHelper.type();
    }

}
