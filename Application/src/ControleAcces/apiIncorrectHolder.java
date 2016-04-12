package ControleAcces;

/**
 * Holder class for : apiIncorrect
 * 
 * @author OpenORB Compiler
 */
final public class apiIncorrectHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal apiIncorrect value
     */
    public ControleAcces.apiIncorrect value;

    /**
     * Default constructor
     */
    public apiIncorrectHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public apiIncorrectHolder(ControleAcces.apiIncorrect initial)
    {
        value = initial;
    }

    /**
     * Read apiIncorrect from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = apiIncorrectHelper.read(istream);
    }

    /**
     * Write apiIncorrect into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        apiIncorrectHelper.write(ostream,value);
    }

    /**
     * Return the apiIncorrect TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return apiIncorrectHelper.type();
    }

}
