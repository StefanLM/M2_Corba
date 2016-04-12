package ControleAcces;

/**
 * Struct definition : Log
 * 
 * @author OpenORB Compiler
*/
public final class Log implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member idLog
     */
    public String idLog;

    /**
     * Struct member typeLog
     */
    public String typeLog;

    /**
     * Struct member dateLog
     */
    public int dateLog;

    /**
     * Struct member descriptionLog
     */
    public String descriptionLog;

    /**
     * Default constructor
     */
    public Log()
    { }

    /**
     * Constructor with fields initialization
     * @param idLog idLog struct member
     * @param typeLog typeLog struct member
     * @param dateLog dateLog struct member
     * @param descriptionLog descriptionLog struct member
     */
    public Log(String idLog, String typeLog, int dateLog, String descriptionLog)
    {
        this.idLog = idLog;
        this.typeLog = typeLog;
        this.dateLog = dateLog;
        this.descriptionLog = descriptionLog;
    }

}
