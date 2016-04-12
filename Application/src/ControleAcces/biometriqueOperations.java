package ControleAcces;

/**
 * Interface definition : biometrique
 * 
 * @author OpenORB Compiler
 */
public interface biometriqueOperations
{
    /**
     * Operation verificationEmpreinte
     */
    public void verificationEmpreinte(String idEmpreinteCrypte, String empreinte, String apiPublic)
        throws ControleAcces.empreinteInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation ajoutEmpreinte
     */
    public String ajoutEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.empreinteDejaExistante, ControleAcces.apiIncorrect;

    /**
     * Operation modificationEmpreinte
     */
    public void modificationEmpreinte(String idEmpreinteCrypte, String nouvelleEmpreinte, String apiPublic)
        throws ControleAcces.empreinteInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation suppressionEmpreinte
     */
    public void suppressionEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.empreinteInconnue, ControleAcces.apiIncorrect;

    /**
     * Operation identificationBiometrique
     */
    public String identificationBiometrique(String empreinte, String apiPublic)
        throws ControleAcces.empreinteInconnue, ControleAcces.apiIncorrect;

}
