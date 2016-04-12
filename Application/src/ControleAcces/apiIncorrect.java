package ControleAcces;

/**
 * Exception definition : apiIncorrect
 * 
 * @author OpenORB Compiler
 */
public final class apiIncorrect extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public apiIncorrect()
    {
        super(apiIncorrectHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public apiIncorrect(String raison)
    {
        super(apiIncorrectHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public apiIncorrect(String orb_reason, String raison)
    {
        super(apiIncorrectHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
