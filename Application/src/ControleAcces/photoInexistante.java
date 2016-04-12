package ControleAcces;

/**
 * Exception definition : photoInexistante
 * 
 * @author OpenORB Compiler
 */
public final class photoInexistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member idPhoto
     */
    public String idPhoto;

    /**
     * Default constructor
     */
    public photoInexistante()
    {
        super(photoInexistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param idPhoto idPhoto exception member
     */
    public photoInexistante(String idPhoto)
    {
        super(photoInexistanteHelper.id());
        this.idPhoto = idPhoto;
    }

    /**
     * Full constructor with fields initialization
     * @param idPhoto idPhoto exception member
     */
    public photoInexistante(String orb_reason, String idPhoto)
    {
        super(photoInexistanteHelper.id() +" " +  orb_reason);
        this.idPhoto = idPhoto;
    }

}
