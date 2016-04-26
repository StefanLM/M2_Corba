import ControleAcces.AccesRefuse;
import ControleAcces.PorteInconnue;

/**
 * Created by RoBiN on 21/04/2016.
 */
public class RelaiZoneImpl extends ControleAcces.RelaiDeZonePOA {
    @Override
    public String demandeAcces(String photo, String empreinte) throws PorteInconnue, AccesRefuse {
        if (photo != null & empreinte != null) {
            System.out.println("Ca fonctionne");
        }
        return "Ca fonctionne";
    }
}
