package ControleAcces;

/**
 * Exception definition : ApiIncorrect
 * 
 * @author OpenORB Compiler
 */
public final class ApiIncorrect extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public ApiIncorrect()
    {
        super(ApiIncorrectHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public ApiIncorrect(String raison)
    {
        super(ApiIncorrectHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public ApiIncorrect(String orb_reason, String raison)
    {
        super(ApiIncorrectHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
