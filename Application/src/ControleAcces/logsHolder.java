package ControleAcces;

/**
 * Holder class for : logs
 * 
 * @author OpenORB Compiler
 */
final public class logsHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal logs value
     */
    public ControleAcces.logs value;

    /**
     * Default constructor
     */
    public logsHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public logsHolder(ControleAcces.logs initial)
    {
        value = initial;
    }

    /**
     * Read logs from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = logsHelper.read(istream);
    }

    /**
     * Write logs into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        logsHelper.write(ostream,value);
    }

    /**
     * Return the logs TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return logsHelper.type();
    }

}
