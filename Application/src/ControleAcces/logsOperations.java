package ControleAcces;

/**
 * Interface definition : Logs
 * 
 * @author OpenORB Compiler
 */
public interface LogsOperations
{
    /**
     * Operation demandeConsultationLog
     */
    public String demandeConsultationLog(int date, String apiPrive)
        throws ControleAcces.AucunLogDisponible;

    /**
     * Operation ecrireLog
     */
    public void ecrireLog(String typeLog, String descriptionLog)
        throws ControleAcces.EcritureImpossible;

}
