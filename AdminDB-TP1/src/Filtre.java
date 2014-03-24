/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class Filtre {
    
    public static String filtrer(String request, int status, int id) {
        StringBuilder res = new StringBuilder(request);
        if (status == 0) {
            res.append(" where IDClient = ").append(id);
        } else if (status == 1) {
            res.append(" where IDVendeur = ").append(id);
        }
        return res.toString();
    }
}
