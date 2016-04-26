package ControleAcces;

/**
 * Holder class for : PersonneInconnue
 * 
 * @author OpenORB Compiler
 */
final public class PersonneInconnueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal PersonneInconnue value
     */
    public ControleAcces.PersonneInconnue value;

    /**
     * Default constructor
     */
    public PersonneInconnueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public PersonneInconnueHolder(ControleAcces.PersonneInconnue initial)
    {
        value = initial;
    }

    /**
     * Read PersonneInconnue from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = PersonneInconnueHelper.read(istream);
    }

    /**
     * Write PersonneInconnue into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        PersonneInconnueHelper.write(ostream,value);
    }

    /**
     * Return the PersonneInconnue TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return PersonneInconnueHelper.type();
    }

}
