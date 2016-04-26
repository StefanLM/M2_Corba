package ControleAcces;

/**
 * Holder class for : PersonneDejaExistante
 * 
 * @author OpenORB Compiler
 */
final public class PersonneDejaExistanteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal PersonneDejaExistante value
     */
    public ControleAcces.PersonneDejaExistante value;

    /**
     * Default constructor
     */
    public PersonneDejaExistanteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public PersonneDejaExistanteHolder(ControleAcces.PersonneDejaExistante initial)
    {
        value = initial;
    }

    /**
     * Read PersonneDejaExistante from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = PersonneDejaExistanteHelper.read(istream);
    }

    /**
     * Write PersonneDejaExistante into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        PersonneDejaExistanteHelper.write(ostream,value);
    }

    /**
     * Return the PersonneDejaExistante TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return PersonneDejaExistanteHelper.type();
    }

}
