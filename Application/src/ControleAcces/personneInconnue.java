package ControleAcces;

/**
 * Exception definition : PersonneInconnue
 * 
 * @author OpenORB Compiler
 */
public final class PersonneInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member personne
     */
    public ControleAcces.Personne personne;

    /**
     * Default constructor
     */
    public PersonneInconnue()
    {
        super(PersonneInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param personne personne exception member
     */
    public PersonneInconnue(ControleAcces.Personne personne)
    {
        super(PersonneInconnueHelper.id());
        this.personne = personne;
    }

    /**
     * Full constructor with fields initialization
     * @param personne personne exception member
     */
    public PersonneInconnue(String orb_reason, ControleAcces.Personne personne)
    {
        super(PersonneInconnueHelper.id() +" " +  orb_reason);
        this.personne = personne;
    }

}
