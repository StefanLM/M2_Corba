package ControleAcces;

/**
 * Holder class for : SequenceIdEmpreinte
 * 
 * @author OpenORB Compiler
 */
final public class SequenceIdEmpreinteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal SequenceIdEmpreinte value
     */
    public String[] value;

    /**
     * Default constructor
     */
    public SequenceIdEmpreinteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public SequenceIdEmpreinteHolder(String[] initial)
    {
        value = initial;
    }

    /**
     * Read SequenceIdEmpreinte from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = SequenceIdEmpreinteHelper.read(istream);
    }

    /**
     * Write SequenceIdEmpreinte into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        SequenceIdEmpreinteHelper.write(ostream,value);
    }

    /**
     * Return the SequenceIdEmpreinte TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return SequenceIdEmpreinteHelper.type();
    }

}
