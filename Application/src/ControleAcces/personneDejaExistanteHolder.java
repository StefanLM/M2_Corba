package ControleAcces;

/**
 * Holder class for : personneDejaExistante
 * 
 * @author OpenORB Compiler
 */
final public class personneDejaExistanteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal personneDejaExistante value
     */
    public ControleAcces.personneDejaExistante value;

    /**
     * Default constructor
     */
    public personneDejaExistanteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public personneDejaExistanteHolder(ControleAcces.personneDejaExistante initial)
    {
        value = initial;
    }

    /**
     * Read personneDejaExistante from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = personneDejaExistanteHelper.read(istream);
    }

    /**
     * Write personneDejaExistante into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        personneDejaExistanteHelper.write(ostream,value);
    }

    /**
     * Return the personneDejaExistante TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return personneDejaExistanteHelper.type();
    }

}
