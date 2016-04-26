package ControleAcces;

/**
 * Interface definition : RelaiDeZone
 * 
 * @author OpenORB Compiler
 */
public interface RelaiDeZoneOperations
{
    /**
     * Operation demandeAcces
     */
    public String demandeAcces(String photo, String empreinte)
        throws ControleAcces.PorteInconnue, ControleAcces.AccesRefuse;

}
