package ControleAcces;

/**
 * Exception definition : EcritureImpossible
 * 
 * @author OpenORB Compiler
 */
public final class EcritureImpossible extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public EcritureImpossible()
    {
        super(EcritureImpossibleHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public EcritureImpossible(String raison)
    {
        super(EcritureImpossibleHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public EcritureImpossible(String orb_reason, String raison)
    {
        super(EcritureImpossibleHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
