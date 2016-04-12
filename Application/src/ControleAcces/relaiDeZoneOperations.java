package ControleAcces;

/**
 * Interface definition : relaiDeZone
 * 
 * @author OpenORB Compiler
 */
public interface relaiDeZoneOperations
{
    /**
     * Operation demandeAcces
     */
    public void demandeAcces(int idPorte, String photo, String empreinte)
        throws ControleAcces.PorteInconnue, ControleAcces.accesRefuse;

}
