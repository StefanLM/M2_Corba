package ControleAcces;

/**
 * Holder class for : ApiIncorrect
 * 
 * @author OpenORB Compiler
 */
final public class ApiIncorrectHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal ApiIncorrect value
     */
    public ControleAcces.ApiIncorrect value;

    /**
     * Default constructor
     */
    public ApiIncorrectHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public ApiIncorrectHolder(ControleAcces.ApiIncorrect initial)
    {
        value = initial;
    }

    /**
     * Read ApiIncorrect from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = ApiIncorrectHelper.read(istream);
    }

    /**
     * Write ApiIncorrect into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        ApiIncorrectHelper.write(ostream,value);
    }

    /**
     * Return the ApiIncorrect TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return ApiIncorrectHelper.type();
    }

}
