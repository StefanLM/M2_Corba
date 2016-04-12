package ControleAcces;

/**
 * Holder class for : annuaire
 * 
 * @author OpenORB Compiler
 */
final public class annuaireHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal annuaire value
     */
    public ControleAcces.annuaire value;

    /**
     * Default constructor
     */
    public annuaireHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public annuaireHolder(ControleAcces.annuaire initial)
    {
        value = initial;
    }

    /**
     * Read annuaire from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = annuaireHelper.read(istream);
    }

    /**
     * Write annuaire into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        annuaireHelper.write(ostream,value);
    }

    /**
     * Return the annuaire TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return annuaireHelper.type();
    }

}
