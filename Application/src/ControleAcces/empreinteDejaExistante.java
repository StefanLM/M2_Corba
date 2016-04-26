package ControleAcces;

/**
 * Exception definition : EmpreinteDejaExistante
 * 
 * @author OpenORB Compiler
 */
public final class EmpreinteDejaExistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member empreinte
     */
    public String empreinte;

    /**
     * Default constructor
     */
    public EmpreinteDejaExistante()
    {
        super(EmpreinteDejaExistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param empreinte empreinte exception member
     */
    public EmpreinteDejaExistante(String empreinte)
    {
        super(EmpreinteDejaExistanteHelper.id());
        this.empreinte = empreinte;
    }

    /**
     * Full constructor with fields initialization
     * @param empreinte empreinte exception member
     */
    public EmpreinteDejaExistante(String orb_reason, String empreinte)
    {
        super(EmpreinteDejaExistanteHelper.id() +" " +  orb_reason);
        this.empreinte = empreinte;
    }

}
