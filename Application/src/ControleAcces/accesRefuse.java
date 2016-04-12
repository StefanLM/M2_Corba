package ControleAcces;

/**
 * Exception definition : accesRefuse
 * 
 * @author OpenORB Compiler
 */
public final class accesRefuse extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public accesRefuse()
    {
        super(accesRefuseHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public accesRefuse(String raison)
    {
        super(accesRefuseHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public accesRefuse(String orb_reason, String raison)
    {
        super(accesRefuseHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
