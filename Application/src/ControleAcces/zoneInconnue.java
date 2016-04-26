package ControleAcces;

/**
 * Exception definition : ZoneInconnue
 * 
 * @author OpenORB Compiler
 */
public final class ZoneInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public ZoneInconnue()
    {
        super(ZoneInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public ZoneInconnue(String raison)
    {
        super(ZoneInconnueHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public ZoneInconnue(String orb_reason, String raison)
    {
        super(ZoneInconnueHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
