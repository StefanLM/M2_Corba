package ControleAcces;

/**
 * Exception definition : personneDejaExistante
 * 
 * @author OpenORB Compiler
 */
public final class personneDejaExistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member personne
     */
    public ControleAcces.Personne personne;

    /**
     * Default constructor
     */
    public personneDejaExistante()
    {
        super(personneDejaExistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param personne personne exception member
     */
    public personneDejaExistante(ControleAcces.Personne personne)
    {
        super(personneDejaExistanteHelper.id());
        this.personne = personne;
    }

    /**
     * Full constructor with fields initialization
     * @param personne personne exception member
     */
    public personneDejaExistante(String orb_reason, ControleAcces.Personne personne)
    {
        super(personneDejaExistanteHelper.id() +" " +  orb_reason);
        this.personne = personne;
    }

}
