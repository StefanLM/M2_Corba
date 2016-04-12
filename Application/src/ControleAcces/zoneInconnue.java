package ControleAcces;

/**
 * Exception definition : zoneInconnue
 * 
 * @author OpenORB Compiler
 */
public final class zoneInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public zoneInconnue()
    {
        super(zoneInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public zoneInconnue(String raison)
    {
        super(zoneInconnueHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public zoneInconnue(String orb_reason, String raison)
    {
        super(zoneInconnueHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
