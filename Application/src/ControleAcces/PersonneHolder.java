package ControleAcces;

/**
 * Holder class for : Personne
 * 
 * @author OpenORB Compiler
 */
final public class PersonneHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Personne value
     */
    public ControleAcces.Personne value;

    /**
     * Default constructor
     */
    public PersonneHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public PersonneHolder(ControleAcces.Personne initial)
    {
        value = initial;
    }

    /**
     * Read Personne from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = PersonneHelper.read(istream);
    }

    /**
     * Write Personne into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        PersonneHelper.write(ostream,value);
    }

    /**
     * Return the Personne TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return PersonneHelper.type();
    }

}
