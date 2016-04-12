package ControleAcces;

/**
 * Exception definition : empreinteDejaExistante
 * 
 * @author OpenORB Compiler
 */
public final class empreinteDejaExistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member empreinte
     */
    public String empreinte;

    /**
     * Default constructor
     */
    public empreinteDejaExistante()
    {
        super(empreinteDejaExistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param empreinte empreinte exception member
     */
    public empreinteDejaExistante(String empreinte)
    {
        super(empreinteDejaExistanteHelper.id());
        this.empreinte = empreinte;
    }

    /**
     * Full constructor with fields initialization
     * @param empreinte empreinte exception member
     */
    public empreinteDejaExistante(String orb_reason, String empreinte)
    {
        super(empreinteDejaExistanteHelper.id() +" " +  orb_reason);
        this.empreinte = empreinte;
    }

}
