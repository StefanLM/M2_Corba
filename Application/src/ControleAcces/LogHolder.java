package ControleAcces;

/**
 * Holder class for : Log
 * 
 * @author OpenORB Compiler
 */
final public class LogHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Log value
     */
    public ControleAcces.Log value;

    /**
     * Default constructor
     */
    public LogHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public LogHolder(ControleAcces.Log initial)
    {
        value = initial;
    }

    /**
     * Read Log from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = LogHelper.read(istream);
    }

    /**
     * Write Log into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        LogHelper.write(ostream,value);
    }

    /**
     * Return the Log TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return LogHelper.type();
    }

}
