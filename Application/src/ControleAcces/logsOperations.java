package ControleAcces;

/**
 * Interface definition : logs
 * 
 * @author OpenORB Compiler
 */
public interface logsOperations
{
    /**
     * Operation demandeConsultationLog
     */
    public String demandeConsultationLog(int date, String apiPrive)
        throws ControleAcces.aucunLogDisponible;

    /**
     * Operation ecrireLog
     */
    public void ecrireLog(int idPersonne, String nom, String prenom, String acces, String zone, String descriptionLog)
        throws ControleAcces.ecritureImpossible, ControleAcces.zoneInconnue, ControleAcces.personneInconnue;

}
