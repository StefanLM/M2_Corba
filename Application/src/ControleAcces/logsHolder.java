package ControleAcces;

/**
 * Holder class for : Logs
 * 
 * @author OpenORB Compiler
 */
final public class LogsHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Logs value
     */
    public ControleAcces.Logs value;

    /**
     * Default constructor
     */
    public LogsHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public LogsHolder(ControleAcces.Logs initial)
    {
        value = initial;
    }

    /**
     * Read Logs from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = LogsHelper.read(istream);
    }

    /**
     * Write Logs into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        LogsHelper.write(ostream,value);
    }

    /**
     * Return the Logs TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return LogsHelper.type();
    }

}
