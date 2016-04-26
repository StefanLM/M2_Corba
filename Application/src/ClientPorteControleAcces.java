import ControleAcces.RelaiDeZone;
import ControleAcces.RelaiDeZoneHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Rob1 on 13/04/2016.
 */
public class ClientPorteControleAcces {

    /*******************************
     * Client CORBA Porte de l'objet RelaiDeZone
     *******************************/

    public static RelaiDeZone relai;

    public static void main(String args[]) {

        try {
            // Intialisation de l'orb
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "2001");
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);

            // Saisie du nom de l'objet (si utilisation du service de nommage)
            System.out.println("Quel objet Corba voulez-vous contacter ?");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String idObj = in.readLine();

            // Recuperation du naming service
            org.omg.CosNaming.NamingContext nameRoot = org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

            // Instruction similaire à la précédente --> Si utilisation de l'URL
            // org.omg.CosNaming.NamingContext nameRoot = org.omg.CosNaming.NamingContextHelper.narrow(orb.string_to_object("corbaloc:iiop:1.2@192.168.43.237:2001/NameService"));

            // Construction du nom a rechercher
            org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
            nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj, "");

            // Recherche aupres du naming service
            org.omg.CORBA.Object distantRelai = nameRoot.resolve(nameToFind);
            System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
            System.out.println(orb.object_to_string(distantRelai));


            // Utilisation directe de l'IOR (SAUF utilisation du service de nommage)
//            org.omg.CORBA.Object distantRelai = orb.string_to_object("IOR:000000000000002249444c3a436f6e74726f6c6541636365732f52656c616944655a6f6e653a312e30000000000000010000000000000086000102000000000d3139322e3136382e302e31320000f46c00000031afabcb000000002039d960c200000001000000000000000100000008526f6f74504f410000000008000000010000000014000000000000020000000100000020000000000001000100000002050100010001002000010109000000010001010000000026000000020002");


            // Casting de l'objet CORBA au type dont on a besoin
            relai = RelaiDeZoneHelper.narrow(distantRelai);

            // Action à effectuer
            relai.demandeAcces("toto", "toto");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}