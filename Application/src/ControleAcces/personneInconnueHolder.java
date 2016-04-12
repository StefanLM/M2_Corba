package ControleAcces;

/**
 * Holder class for : personneInconnue
 * 
 * @author OpenORB Compiler
 */
final public class personneInconnueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal personneInconnue value
     */
    public ControleAcces.personneInconnue value;

    /**
     * Default constructor
     */
    public personneInconnueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public personneInconnueHolder(ControleAcces.personneInconnue initial)
    {
        value = initial;
    }

    /**
     * Read personneInconnue from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = personneInconnueHelper.read(istream);
    }

    /**
     * Write personneInconnue into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        personneInconnueHelper.write(ostream,value);
    }

    /**
     * Return the personneInconnue TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return personneInconnueHelper.type();
    }

}
