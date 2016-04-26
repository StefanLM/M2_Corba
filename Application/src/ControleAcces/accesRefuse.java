package ControleAcces;

/**
 * Exception definition : AccesRefuse
 * 
 * @author OpenORB Compiler
 */
public final class AccesRefuse extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public AccesRefuse()
    {
        super(AccesRefuseHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public AccesRefuse(String raison)
    {
        super(AccesRefuseHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public AccesRefuse(String orb_reason, String raison)
    {
        super(AccesRefuseHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
