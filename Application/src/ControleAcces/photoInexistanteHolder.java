package ControleAcces;

/**
 * Holder class for : photoInexistante
 * 
 * @author OpenORB Compiler
 */
final public class photoInexistanteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal photoInexistante value
     */
    public ControleAcces.photoInexistante value;

    /**
     * Default constructor
     */
    public photoInexistanteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public photoInexistanteHolder(ControleAcces.photoInexistante initial)
    {
        value = initial;
    }

    /**
     * Read photoInexistante from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = photoInexistanteHelper.read(istream);
    }

    /**
     * Write photoInexistante into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        photoInexistanteHelper.write(ostream,value);
    }

    /**
     * Return the photoInexistante TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return photoInexistanteHelper.type();
    }

}
