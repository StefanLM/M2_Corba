package ControleAcces;

/**
 * Holder class for : sequenceIdEmpreinte
 * 
 * @author OpenORB Compiler
 */
final public class sequenceIdEmpreinteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal sequenceIdEmpreinte value
     */
    public String[] value;

    /**
     * Default constructor
     */
    public sequenceIdEmpreinteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public sequenceIdEmpreinteHolder(String[] initial)
    {
        value = initial;
    }

    /**
     * Read sequenceIdEmpreinte from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = sequenceIdEmpreinteHelper.read(istream);
    }

    /**
     * Write sequenceIdEmpreinte into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        sequenceIdEmpreinteHelper.write(ostream,value);
    }

    /**
     * Return the sequenceIdEmpreinte TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return sequenceIdEmpreinteHelper.type();
    }

}
