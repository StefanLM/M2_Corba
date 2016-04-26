package ControleAcces;

/**
 * Interface definition : Logs
 * 
 * @author OpenORB Compiler
 */
public class LogsPOATie extends LogsPOA
{

    //
    // Private reference to implementation object
    //
    private LogsOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public LogsPOATie(LogsOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public LogsPOATie(LogsOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public LogsOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(LogsOperations delegate_)
    {
        _tie = delegate_;
    }

    /**
     * _default_POA method
     */
    public org.omg.PortableServer.POA _default_POA()
    {
        if (_poa != null)
            return _poa;
        else
            return super._default_POA();
    }

    /**
     * Operation demandeConsultationLog
     */
    public String demandeConsultationLog(int date, String apiPrive)
        throws ControleAcces.AucunLogDisponible
    {
        return _tie.demandeConsultationLog( date,  apiPrive);
    }

    /**
     * Operation ecrireLog
     */
    public void ecrireLog(String typeLog, String descriptionLog)
        throws ControleAcces.EcritureImpossible
    {
        _tie.ecrireLog( typeLog,  descriptionLog);
    }

}
