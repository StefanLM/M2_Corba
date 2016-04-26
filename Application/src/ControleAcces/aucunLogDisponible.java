package ControleAcces;

/**
 * Exception definition : AucunLogDisponible
 * 
 * @author OpenORB Compiler
 */
public final class AucunLogDisponible extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public AucunLogDisponible()
    {
        super(AucunLogDisponibleHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public AucunLogDisponible(String raison)
    {
        super(AucunLogDisponibleHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public AucunLogDisponible(String orb_reason, String raison)
    {
        super(AucunLogDisponibleHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
