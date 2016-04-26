package ControleAcces;

/**
 * Exception definition : PhotoInexistante
 * 
 * @author OpenORB Compiler
 */
public final class PhotoInexistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member idPhoto
     */
    public String idPhoto;

    /**
     * Default constructor
     */
    public PhotoInexistante()
    {
        super(PhotoInexistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param idPhoto idPhoto exception member
     */
    public PhotoInexistante(String idPhoto)
    {
        super(PhotoInexistanteHelper.id());
        this.idPhoto = idPhoto;
    }

    /**
     * Full constructor with fields initialization
     * @param idPhoto idPhoto exception member
     */
    public PhotoInexistante(String orb_reason, String idPhoto)
    {
        super(PhotoInexistanteHelper.id() +" " +  orb_reason);
        this.idPhoto = idPhoto;
    }

}
