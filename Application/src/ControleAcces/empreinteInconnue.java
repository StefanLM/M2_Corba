package ControleAcces;

/**
 * Exception definition : empreinteInconnue
 * 
 * @author OpenORB Compiler
 */
public final class empreinteInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member empreinte
     */
    public String empreinte;

    /**
     * Default constructor
     */
    public empreinteInconnue()
    {
        super(empreinteInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param empreinte empreinte exception member
     */
    public empreinteInconnue(String empreinte)
    {
        super(empreinteInconnueHelper.id());
        this.empreinte = empreinte;
    }

    /**
     * Full constructor with fields initialization
     * @param empreinte empreinte exception member
     */
    public empreinteInconnue(String orb_reason, String empreinte)
    {
        super(empreinteInconnueHelper.id() +" " +  orb_reason);
        this.empreinte = empreinte;
    }

}
