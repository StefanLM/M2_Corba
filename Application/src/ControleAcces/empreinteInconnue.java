package ControleAcces;

/**
 * Exception definition : EmpreinteInconnue
 * 
 * @author OpenORB Compiler
 */
public final class EmpreinteInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member empreinte
     */
    public String empreinte;

    /**
     * Default constructor
     */
    public EmpreinteInconnue()
    {
        super(EmpreinteInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param empreinte empreinte exception member
     */
    public EmpreinteInconnue(String empreinte)
    {
        super(EmpreinteInconnueHelper.id());
        this.empreinte = empreinte;
    }

    /**
     * Full constructor with fields initialization
     * @param empreinte empreinte exception member
     */
    public EmpreinteInconnue(String orb_reason, String empreinte)
    {
        super(EmpreinteInconnueHelper.id() +" " +  orb_reason);
        this.empreinte = empreinte;
    }

}
