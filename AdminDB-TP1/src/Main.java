
import java.sql.SQLException;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class Main 
{
    public static void main(String[] args)
    {
        
        try
        {
            // Tout est dans le IHM
            IHM ihm = new IHM();
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
//        catch (SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
        
    }
   
}
