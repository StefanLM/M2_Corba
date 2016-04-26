package ControleAcces;

/**
 * Exception definition : APIIncorrecte
 * 
 * @author OpenORB Compiler
 */
public final class APIIncorrecte extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public APIIncorrecte()
    {
        super(APIIncorrecteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public APIIncorrecte(String raison)
    {
        super(APIIncorrecteHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public APIIncorrecte(String orb_reason, String raison)
    {
        super(APIIncorrecteHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}
