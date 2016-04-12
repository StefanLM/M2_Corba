package ControleAcces;

/**
 * Holder class for : ecritureImpossible
 * 
 * @author OpenORB Compiler
 */
final public class ecritureImpossibleHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal ecritureImpossible value
     */
    public ControleAcces.ecritureImpossible value;

    /**
     * Default constructor
     */
    public ecritureImpossibleHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public ecritureImpossibleHolder(ControleAcces.ecritureImpossible initial)
    {
        value = initial;
    }

    /**
     * Read ecritureImpossible from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = ecritureImpossibleHelper.read(istream);
    }

    /**
     * Write ecritureImpossible into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        ecritureImpossibleHelper.write(ostream,value);
    }

    /**
     * Return the ecritureImpossible TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return ecritureImpossibleHelper.type();
    }

}
