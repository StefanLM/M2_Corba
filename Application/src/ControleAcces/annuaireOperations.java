package ControleAcces;

/**
 * Interface definition : Annuaire
 * 
 * @author OpenORB Compiler
 */
public interface AnnuaireOperations
{
    /**
     * Operation verificationPhoto
     */
    public String verificationPhoto(String photo, String apiPublic)
        throws ControleAcces.PhotoInexistante, ControleAcces.APIIncorrecte;

    /**
     * Operation verificationPersonne
     */
    public ControleAcces.Personne verificationPersonne(String nom, String prenom, String photo, String apiPublic)
        throws ControleAcces.PhotoInexistante, ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation authentification
     */
    public String authentification(String login, String password, String apiPublic)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation ajoutPersonne
     */
    public void ajoutPersonne(String nom, String prenom, String login, String password, String photo, String apiPrive)
        throws ControleAcces.PersonneDejaExistante, ControleAcces.APIIncorrecte;

    /**
     * Operation ajoutPhotoPersonne
     */
    public void ajoutPhotoPersonne(ControleAcces.Personne personne, String photo, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation modificationEmpreintePersonne
     */
    public void modificationEmpreintePersonne(ControleAcces.Personne personne, String idEmpreinteCrypte, String apiPublic)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation modificationPersonne
     */
    public void modificationPersonne(ControleAcces.Personne personne, String nomNew, String prenomNew, String photoNew, String loginNew, String passwordNew, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation supprimerPersonne
     */
    public void supprimerPersonne(ControleAcces.Personne personne, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation consultationPersonne
     */
    public ControleAcces.Personne consultationPersonne(String nom, String prenom, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation consultationPersonneBiometrique
     */
    public ControleAcces.Personne consultationPersonneBiometrique(String idEmpreinteCrypte, String apiPublic)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation demandeSuppressionEmpreinte
     */
    public String[] demandeSuppressionEmpreinte(String apiPublic);

}
