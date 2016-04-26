package ControleAcces;

/**
 * Interface definition : Autorisation
 * 
 * @author OpenORB Compiler
 */
public class AutorisationPOATie extends AutorisationPOA
{

    //
    // Private reference to implementation object
    //
    private AutorisationOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public AutorisationPOATie(AutorisationOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public AutorisationPOATie(AutorisationOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public AutorisationOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(AutorisationOperations delegate_)
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
     * Operation ajoutAutorisation
     */
    public void ajoutAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        _tie.ajoutAutorisation( personne,  zone,  apiPrive);
    }

    /**
     * Operation modificationAutorisation
     */
    public void modificationAutorisation(ControleAcces.Personne personne, String nouvelleZone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        _tie.modificationAutorisation( personne,  nouvelleZone,  apiPrive);
    }

    /**
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        _tie.supprimerAutorisation( personne,  zone,  apiPrive);
    }

}
