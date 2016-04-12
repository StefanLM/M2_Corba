package ControleAcces;

/**
 * Interface definition : annuaire
 * 
 * @author OpenORB Compiler
 */
public class annuairePOATie extends annuairePOA
{

    //
    // Private reference to implementation object
    //
    private annuaireOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public annuairePOATie(annuaireOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public annuairePOATie(annuaireOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public annuaireOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(annuaireOperations delegate_)
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
     * Operation verificationPhoto
     */
    public String verificationPhoto(String photo, String apiPublic)
        throws ControleAcces.photoInexistante, ControleAcces.apiIncorrect
    {
        return _tie.verificationPhoto( photo,  apiPublic);
    }

    /**
     * Operation verificationPersonne
     */
    public ControleAcces.Personne verificationPersonne(String nom, String prenom, String photo, String apiPublic)
        throws ControleAcces.photoInexistante, ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        return _tie.verificationPersonne( nom,  prenom,  photo,  apiPublic);
    }

    /**
     * Operation authentification
     */
    public String authentification(String login, String password, String apiPublic)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        return _tie.authentification( login,  password,  apiPublic);
    }

    /**
     * Operation ajoutAutorisation
     */
    public void ajoutAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        _tie.ajoutAutorisation( personne,  zone,  apiPrive);
    }

    /**
     * Operation modificationAutorisation
     */
    public void modificationAutorisation(ControleAcces.Personne personne, String nouvelleZone, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        _tie.modificationAutorisation( personne,  nouvelleZone,  apiPrive);
    }

    /**
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        _tie.supprimerAutorisation( personne,  zone,  apiPrive);
    }

    /**
     * Operation ajoutPersonne
     */
    public void ajoutPersonne(String nom, String prenom, String login, String password, String photo, String apiPrive)
        throws ControleAcces.personneDejaExistante, ControleAcces.apiIncorrect
    {
        _tie.ajoutPersonne( nom,  prenom,  login,  password,  photo,  apiPrive);
    }

    /**
     * Operation ajoutPhotoPersonne
     */
    public void ajoutPhotoPersonne(ControleAcces.Personne personne, String photo, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        _tie.ajoutPhotoPersonne( personne,  photo,  apiPrive);
    }

    /**
     * Operation modificationEmpreintePersonne
     */
    public void modificationEmpreintePersonne(ControleAcces.Personne personne, String idEmpreinteCrypte, String apiPublic)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        _tie.modificationEmpreintePersonne( personne,  idEmpreinteCrypte,  apiPublic);
    }

    /**
     * Operation modificationPersonne
     */
    public void modificationPersonne(ControleAcces.Personne personne, String nomNew, String prenomNew, String photoNew, String loginNew, String passwordNew, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        _tie.modificationPersonne( personne,  nomNew,  prenomNew,  photoNew,  loginNew,  passwordNew,  apiPrive);
    }

    /**
     * Operation supprimerPersonne
     */
    public void supprimerPersonne(ControleAcces.Personne personne, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        _tie.supprimerPersonne( personne,  apiPrive);
    }

    /**
     * Operation consultationPersonne
     */
    public ControleAcces.Personne consultationPersonne(String nom, String prenom, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        return _tie.consultationPersonne( nom,  prenom,  apiPrive);
    }

    /**
     * Operation consultationPersonneBiometrique
     */
    public ControleAcces.Personne consultationPersonneBiometrique(String idEmpreinteCrypte, String apiPublic)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect
    {
        return _tie.consultationPersonneBiometrique( idEmpreinteCrypte,  apiPublic);
    }

    /**
     * Operation demandeSuppressionEmpreinte
     */
    public String[] demandeSuppressionEmpreinte(String apiPublic)
    {
        return _tie.demandeSuppressionEmpreinte( apiPublic);
    }

}
