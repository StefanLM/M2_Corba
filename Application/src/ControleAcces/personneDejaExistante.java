package ControleAcces;

/**
 * Exception definition : PersonneDejaExistante
 * 
 * @author OpenORB Compiler
 */
public final class PersonneDejaExistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member personne
     */
    public ControleAcces.Personne personne;

    /**
     * Default constructor
     */
    public PersonneDejaExistante()
    {
        super(PersonneDejaExistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param personne personne exception member
     */
    public PersonneDejaExistante(ControleAcces.Personne personne)
    {
        super(PersonneDejaExistanteHelper.id());
        this.personne = personne;
    }

    /**
     * Full constructor with fields initialization
     * @param personne personne exception member
     */
    public PersonneDejaExistante(String orb_reason, ControleAcces.Personne personne)
    {
        super(PersonneDejaExistanteHelper.id() +" " +  orb_reason);
        this.personne = personne;
    }

}
