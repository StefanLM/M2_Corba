package ControleAcces;

/**
 * Interface definition : Biometrique
 * 
 * @author OpenORB Compiler
 */
public class BiometriquePOATie extends BiometriquePOA
{

    //
    // Private reference to implementation object
    //
    private BiometriqueOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public BiometriquePOATie(BiometriqueOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public BiometriquePOATie(BiometriqueOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public BiometriqueOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(BiometriqueOperations delegate_)
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
     * Operation verificationEmpreinte
     */
    public boolean verificationEmpreinte(String idEmpreinteCrypte, String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte
    {
        return _tie.verificationEmpreinte( idEmpreinteCrypte,  empreinte,  apiPublic);
    }

    /**
     * Operation ajoutEmpreinte
     */
    public String ajoutEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteDejaExistante, ControleAcces.APIIncorrecte
    {
        return _tie.ajoutEmpreinte( empreinte,  apiPublic);
    }

    /**
     * Operation modificationEmpreinte
     */
    public void modificationEmpreinte(String idEmpreinteCrypte, String nouvelleEmpreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte
    {
        _tie.modificationEmpreinte( idEmpreinteCrypte,  nouvelleEmpreinte,  apiPublic);
    }

    /**
     * Operation suppressionEmpreinte
     */
    public void suppressionEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte
    {
        _tie.suppressionEmpreinte( empreinte,  apiPublic);
    }

    /**
     * Operation identificationBiometrique
     */
    public String identificationBiometrique(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte
    {
        return _tie.identificationBiometrique( empreinte,  apiPublic);
    }

}
