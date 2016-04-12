package ControleAcces;

/**
 * Interface definition : relaiDeZone
 * 
 * @author OpenORB Compiler
 */
public class relaiDeZonePOATie extends relaiDeZonePOA
{

    //
    // Private reference to implementation object
    //
    private relaiDeZoneOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public relaiDeZonePOATie(relaiDeZoneOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public relaiDeZonePOATie(relaiDeZoneOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public relaiDeZoneOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(relaiDeZoneOperations delegate_)
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
     * Operation demandeAcces
     */
    public void demandeAcces(int idPorte, String photo, String empreinte)
        throws ControleAcces.PorteInconnue, ControleAcces.accesRefuse
    {
        _tie.demandeAcces( idPorte,  photo,  empreinte);
    }

}
