package ControleAcces;

/**
 * Interface definition : logs
 * 
 * @author OpenORB Compiler
 */
public class logsPOATie extends logsPOA
{

    //
    // Private reference to implementation object
    //
    private logsOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public logsPOATie(logsOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public logsPOATie(logsOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public logsOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(logsOperations delegate_)
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
        throws ControleAcces.aucunLogDisponible
    {
        return _tie.demandeConsultationLog( date,  apiPrive);
    }

    /**
     * Operation ecrireLog
     */
    public void ecrireLog(int idPersonne, String nom, String prenom, String acces, String zone, String descriptionLog)
        throws ControleAcces.ecritureImpossible, ControleAcces.zoneInconnue, ControleAcces.personneInconnue
    {
        _tie.ecrireLog( idPersonne,  nom,  prenom,  acces,  zone,  descriptionLog);
    }

}
