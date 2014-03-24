import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton 
{
    
    private Connection instance = null;
    private final String url = "";
    private final String login = "";
    private final String password = "";
    
    private Singleton()
    {
        try
        {
            instance = DriverManager.getConnection(url,login,password);
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
}
