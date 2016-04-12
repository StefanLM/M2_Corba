package ControleAcces;

/**
 * Exception definition : ecritureImpossible
 * 
 * @author OpenORB Compiler
 */
public final class ecritureImpossible extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public ecritureImpossible()
    {
        super(ecritureImpossibleHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public ecritureImpossible(String raison)
    {
        super(ecritureImpossibleHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public ecritureImpossible(String orb_reason, String raison)
    {
        super(ecritureImpossibleHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
