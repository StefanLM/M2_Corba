package ControleAcces;

/**
 * Interface definition : RelaiDeZone
 * 
 * @author OpenORB Compiler
 */
public class RelaiDeZonePOATie extends RelaiDeZonePOA
{

    //
    // Private reference to implementation object
    //
    private RelaiDeZoneOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public RelaiDeZonePOATie(RelaiDeZoneOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public RelaiDeZonePOATie(RelaiDeZoneOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public RelaiDeZoneOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(RelaiDeZoneOperations delegate_)
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
    public String demandeAcces(String photo, String empreinte)
        throws ControleAcces.PorteInconnue, ControleAcces.AccesRefuse
    {
        return _tie.demandeAcces( photo,  empreinte);
    }

}
