package ControleAcces;

/**
 * Holder class for : PhotoInexistante
 * 
 * @author OpenORB Compiler
 */
final public class PhotoInexistanteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal PhotoInexistante value
     */
    public ControleAcces.PhotoInexistante value;

    /**
     * Default constructor
     */
    public PhotoInexistanteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public PhotoInexistanteHolder(ControleAcces.PhotoInexistante initial)
    {
        value = initial;
    }

    /**
     * Read PhotoInexistante from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = PhotoInexistanteHelper.read(istream);
    }

    /**
     * Write PhotoInexistante into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        PhotoInexistanteHelper.write(ostream,value);
    }

    /**
     * Return the PhotoInexistante TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return PhotoInexistanteHelper.type();
    }

}
