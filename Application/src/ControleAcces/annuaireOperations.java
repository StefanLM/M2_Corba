package ControleAcces;

/**
 * Interface definition : annuaire
 * 
 * @author OpenORB Compiler
 */
public interface annuaireOperations
{
    /**
     * Operation verificationPhoto
     */
    public String verificationPhoto(String photo, String apiPublic)
        throws ControleAcces.photoInexistante, ControleAcces.apiIncorrect;

    /**
     * Operation verificationPersonne
     */
    public ControleAcces.Personne verificationPersonne(String nom, String prenom, String photo, String apiPublic)
        throws ControleAcces.photoInexistante, ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation authentification
     */
    public String authentification(String login, String password, String apiPublic)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation ajoutAutorisation
     */
    public void ajoutAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation modificationAutorisation
     */
    public void modificationAutorisation(ControleAcces.Personne personne, String nouvelleZone, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation ajoutPersonne
     */
    public void ajoutPersonne(String nom, String prenom, String login, String password, String photo, String apiPrive)
        throws ControleAcces.personneDejaExistante, ControleAcces.apiIncorrect;

    /**
     * Operation ajoutPhotoPersonne
     */
    public void ajoutPhotoPersonne(ControleAcces.Personne personne, String photo, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation modificationEmpreintePersonne
     */
    public void modificationEmpreintePersonne(ControleAcces.Personne personne, String idEmpreinteCrypte, String apiPublic)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation modificationPersonne
     */
    public void modificationPersonne(ControleAcces.Personne personne, String nomNew, String prenomNew, String photoNew, String loginNew, String passwordNew, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation supprimerPersonne
     */
    public void supprimerPersonne(ControleAcces.Personne personne, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation consultationPersonne
     */
    public ControleAcces.Personne consultationPersonne(String nom, String prenom, String apiPrive)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation consultationPersonneBiometrique
     */
    public ControleAcces.Personne consultationPersonneBiometrique(String idEmpreinteCrypte, String apiPublic)
        throws ControleAcces.personneInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation demandeSuppressionEmpreinte
     */
    public String[] demandeSuppressionEmpreinte(String apiPublic);

}
