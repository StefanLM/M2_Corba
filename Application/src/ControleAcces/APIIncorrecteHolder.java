package ControleAcces;

/**
 * Holder class for : APIIncorrecte
 * 
 * @author OpenORB Compiler
 */
final public class APIIncorrecteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal APIIncorrecte value
     */
    public ControleAcces.APIIncorrecte value;

    /**
     * Default constructor
     */
    public APIIncorrecteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public APIIncorrecteHolder(ControleAcces.APIIncorrecte initial)
    {
        value = initial;
    }

    /**
     * Read APIIncorrecte from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = APIIncorrecteHelper.read(istream);
    }

    /**
     * Write APIIncorrecte into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        APIIncorrecteHelper.write(ostream,value);
    }

    /**
     * Return the APIIncorrecte TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return APIIncorrecteHelper.type();
    }

}
