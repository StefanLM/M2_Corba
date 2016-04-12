package ControleAcces;

/**
 * Mettre le minimum de paramètre, uniquement ce qu'on va utiliser
 */
public final class Personne implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member idPersonne
     */
    public int idPersonne;

    /**
     * Struct member nom
     */
    public String nom;

    /**
     * Struct member prenom
     */
    public String prenom;

    /**
     * Struct member login
     */
    public String login;

    /**
     * Struct member photo
     */
    public String photo;

    /**
     * Struct member jourAutorisationDebut
     */
    public int jourAutorisationDebut;

    /**
     * Struct member jourAutorisationFin
     */
    public int jourAutorisationFin;

    /**
     * Struct member heureDebut
     */
    public int heureDebut;

    /**
     * Struct member heureFin
     */
    public int heureFin;

    /**
     * Struct member estPermanent
     */
    public boolean estPermanent;

    /**
     * Struct member idEmpreinteCrypte
     */
    public String idEmpreinteCrypte;

    /**
     * Default constructor
     */
    public Personne()
    { }

    /**
     * Constructor with fields initialization
     * @param idPersonne idPersonne struct member
     * @param nom nom struct member
     * @param prenom prenom struct member
     * @param login login struct member
     * @param photo photo struct member
     * @param jourAutorisationDebut jourAutorisationDebut struct member
     * @param jourAutorisationFin jourAutorisationFin struct member
     * @param heureDebut heureDebut struct member
     * @param heureFin heureFin struct member
     * @param estPermanent estPermanent struct member
     * @param idEmpreinteCrypte idEmpreinteCrypte struct member
     */
    public Personne(int idPersonne, String nom, String prenom, String login, String photo, int jourAutorisationDebut, int jourAutorisationFin, int heureDebut, int heureFin, boolean estPermanent, String idEmpreinteCrypte)
    {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.photo = photo;
        this.jourAutorisationDebut = jourAutorisationDebut;
        this.jourAutorisationFin = jourAutorisationFin;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.estPermanent = estPermanent;
        this.idEmpreinteCrypte = idEmpreinteCrypte;
    }

}
