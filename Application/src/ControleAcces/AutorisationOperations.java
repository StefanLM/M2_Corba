package ControleAcces;

/**
 * Interface definition : Autorisation
 * 
 * @author OpenORB Compiler
 */
public interface AutorisationOperations
{
    /**
     * Operation ajoutAutorisation
     */
    public void ajoutAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation modificationAutorisation
     */
    public void modificationAutorisation(ControleAcces.Personne personne, String nouvelleZone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte;

}
