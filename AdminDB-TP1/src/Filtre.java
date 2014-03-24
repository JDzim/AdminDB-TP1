/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class Filtre {
    
    public static String filtrer(String request, int status, int id) {
        StringBuilder res = new StringBuilder(request);
        if (status == 0) {
            int index = res.indexOf("*");
            if (index != -1) {
                res.replace(index, index+1, "IDCmd, Total, IDClient");
            }
            res.append(" where IDClient = ").append(id);
        } else if (status == 1) {
            res.append(" where IDVendeur = ").append(id);
        }
        System.out.println(res.toString());
        return res.toString();
    }
}
