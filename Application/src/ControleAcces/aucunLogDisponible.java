package ControleAcces;

/**
 * Exception definition : aucunLogDisponible
 * 
 * @author OpenORB Compiler
 */
public final class aucunLogDisponible extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public aucunLogDisponible()
    {
        super(aucunLogDisponibleHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public aucunLogDisponible(String raison)
    {
        super(aucunLogDisponibleHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public aucunLogDisponible(String orb_reason, String raison)
    {
        super(aucunLogDisponibleHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
