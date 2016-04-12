package ControleAcces;

/**
 * Interface definition : biometrique
 * 
 * @author OpenORB Compiler
 */
public class biometriquePOATie extends biometriquePOA
{

    //
    // Private reference to implementation object
    //
    private biometriqueOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public biometriquePOATie(biometriqueOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public biometriquePOATie(biometriqueOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public biometriqueOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(biometriqueOperations delegate_)
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
    public void verificationEmpreinte(String idEmpreinteCrypte, String empreinte, String apiPublic)
        throws ControleAcces.empreinteInconnue, ControleAcces.apiIncorrect
    {
        _tie.verificationEmpreinte( idEmpreinteCrypte,  empreinte,  apiPublic);
    }

    /**
     * Operation ajoutEmpreinte
     */
    public String ajoutEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.empreinteDejaExistante, ControleAcces.apiIncorrect
    {
        return _tie.ajoutEmpreinte( empreinte,  apiPublic);
    }

    /**
     * Operation modificationEmpreinte
     */
    public void modificationEmpreinte(String idEmpreinteCrypte, String nouvelleEmpreinte, String apiPublic)
        throws ControleAcces.empreinteInconnue, ControleAcces.apiIncorrect
    {
        _tie.modificationEmpreinte( idEmpreinteCrypte,  nouvelleEmpreinte,  apiPublic);
    }

    /**
     * Operation suppressionEmpreinte
     */
    public void suppressionEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.empreinteInconnue, ControleAcces.apiIncorrect
    {
        _tie.suppressionEmpreinte( empreinte,  apiPublic);
    }

    /**
     * Operation identificationBiometrique
     */
    public String identificationBiometrique(String empreinte, String apiPublic)
        throws ControleAcces.empreinteInconnue, ControleAcces.apiIncorrect
    {
        return _tie.identificationBiometrique( empreinte,  apiPublic);
    }

}
