package ControleAcces;

/**
 * Exception definition : PorteInconnue
 * 
 * @author OpenORB Compiler
 */
public final class PorteInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public PorteInconnue()
    {
        super(PorteInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public PorteInconnue(String raison)
    {
        super(PorteInconnueHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public PorteInconnue(String orb_reason, String raison)
    {
        super(PorteInconnueHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
