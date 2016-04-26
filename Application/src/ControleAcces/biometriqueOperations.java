package ControleAcces;

/**
 * Interface definition : Biometrique
 * 
 * @author OpenORB Compiler
 */
public interface BiometriqueOperations
{
    /**
     * Operation verificationEmpreinte
     */
    public boolean verificationEmpreinte(String idEmpreinteCrypte, String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation ajoutEmpreinte
     */
    public String ajoutEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteDejaExistante, ControleAcces.APIIncorrecte;

    /**
     * Operation modificationEmpreinte
     */
    public void modificationEmpreinte(String idEmpreinteCrypte, String nouvelleEmpreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation suppressionEmpreinte
     */
    public void suppressionEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte;

    /**
     * Operation identificationBiometrique
     */
    public String identificationBiometrique(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte;

}
