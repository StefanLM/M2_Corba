package ControleAcces;

/**
 * Exception definition : personneInconnue
 * 
 * @author OpenORB Compiler
 */
public final class personneInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member personne
     */
    public ControleAcces.Personne personne;

    /**
     * Default constructor
     */
    public personneInconnue()
    {
        super(personneInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param personne personne exception member
     */
    public personneInconnue(ControleAcces.Personne personne)
    {
        super(personneInconnueHelper.id());
        this.personne = personne;
    }

    /**
     * Full constructor with fields initialization
     * @param personne personne exception member
     */
    public personneInconnue(String orb_reason, ControleAcces.Personne personne)
    {
        super(personneInconnueHelper.id() +" " +  orb_reason);
        this.personne = personne;
    }

}
