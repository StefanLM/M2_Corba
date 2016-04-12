package ControleAcces;

/** 
 * Helper class for : Personne
 *  
 * @author OpenORB Compiler
 */ 
public class PersonneHelper
{
    private static final boolean HAS_OPENORB;
    static {
        boolean hasOpenORB = false;
        try {
            Thread.currentThread().getContextClassLoader().loadClass("org.openorb.CORBA.Any");
            hasOpenORB = true;
        }
        catch(ClassNotFoundException ex) {
        }
        HAS_OPENORB = hasOpenORB;
    }
    /**
     * Insert Personne into an any
     * @param a an any
     * @param t Personne value
     */
    public static void insert(org.omg.CORBA.Any a, ControleAcces.Personne t)
    {
        a.insert_Streamable(new ControleAcces.PersonneHolder(t));
    }

    /**
     * Extract Personne from an any
     * @param a an any
     * @return the extracted Personne value
     */
    public static ControleAcces.Personne extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof ControleAcces.PersonneHolder)
                    return ((ControleAcces.PersonneHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            ControleAcces.PersonneHolder h = new ControleAcces.PersonneHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;
    private static boolean _working = false;

    /**
     * Return the Personne TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            synchronized(org.omg.CORBA.TypeCode.class) {
                if (_tc != null)
                    return _tc;
                if (_working)
                    return org.omg.CORBA.ORB.init().create_recursive_tc(id());
                _working = true;
                org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[11];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "idPersonne";
                _members[0].type = ControleAcces.IdPersonneHelper.type();
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "nom";
                _members[1].type = ControleAcces.NomHelper.type();
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "prenom";
                _members[2].type = ControleAcces.PrenomHelper.type();
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "login";
                _members[3].type = ControleAcces.LoginHelper.type();
                _members[4] = new org.omg.CORBA.StructMember();
                _members[4].name = "photo";
                _members[4].type = ControleAcces.PhotoHelper.type();
                _members[5] = new org.omg.CORBA.StructMember();
                _members[5].name = "jourAutorisationDebut";
                _members[5].type = ControleAcces.DateHelper.type();
                _members[6] = new org.omg.CORBA.StructMember();
                _members[6].name = "jourAutorisationFin";
                _members[6].type = ControleAcces.DateHelper.type();
                _members[7] = new org.omg.CORBA.StructMember();
                _members[7].name = "heureDebut";
                _members[7].type = ControleAcces.DateHelper.type();
                _members[8] = new org.omg.CORBA.StructMember();
                _members[8].name = "heureFin";
                _members[8].type = ControleAcces.DateHelper.type();
                _members[9] = new org.omg.CORBA.StructMember();
                _members[9].name = "estPermanent";
                _members[9].type = ControleAcces.PermanentHelper.type();
                _members[10] = new org.omg.CORBA.StructMember();
                _members[10].name = "idEmpreinteCrypte";
                _members[10].type = ControleAcces.IdEmpreinteHelper.type();
                _tc = orb.create_struct_tc(id(),"Personne",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the Personne IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:ControleAcces/Personne:1.0";

    /**
     * Read Personne from a marshalled stream
     * @param istream the input stream
     * @return the readed Personne value
     */
    public static ControleAcces.Personne read(org.omg.CORBA.portable.InputStream istream)
    {
        ControleAcces.Personne new_one = new ControleAcces.Personne();

        new_one.idPersonne = ControleAcces.IdPersonneHelper.read(istream);
        new_one.nom = ControleAcces.NomHelper.read(istream);
        new_one.prenom = ControleAcces.PrenomHelper.read(istream);
        new_one.login = ControleAcces.LoginHelper.read(istream);
        new_one.photo = ControleAcces.PhotoHelper.read(istream);
        new_one.jourAutorisationDebut = ControleAcces.DateHelper.read(istream);
        new_one.jourAutorisationFin = ControleAcces.DateHelper.read(istream);
        new_one.heureDebut = ControleAcces.DateHelper.read(istream);
        new_one.heureFin = ControleAcces.DateHelper.read(istream);
        new_one.estPermanent = ControleAcces.PermanentHelper.read(istream);
        new_one.idEmpreinteCrypte = ControleAcces.IdEmpreinteHelper.read(istream);

        return new_one;
    }

    /**
     * Write Personne into a marshalled stream
     * @param ostream the output stream
     * @param value Personne value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ControleAcces.Personne value)
    {
        ControleAcces.IdPersonneHelper.write(ostream,value.idPersonne);
        ControleAcces.NomHelper.write(ostream,value.nom);
        ControleAcces.PrenomHelper.write(ostream,value.prenom);
        ControleAcces.LoginHelper.write(ostream,value.login);
        ControleAcces.PhotoHelper.write(ostream,value.photo);
        ControleAcces.DateHelper.write(ostream,value.jourAutorisationDebut);
        ControleAcces.DateHelper.write(ostream,value.jourAutorisationFin);
        ControleAcces.DateHelper.write(ostream,value.heureDebut);
        ControleAcces.DateHelper.write(ostream,value.heureFin);
        ControleAcces.PermanentHelper.write(ostream,value.estPermanent);
        ControleAcces.IdEmpreinteHelper.write(ostream,value.idEmpreinteCrypte);
    }

}
