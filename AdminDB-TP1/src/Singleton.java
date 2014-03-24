import java.sql.Connection;
import java.sql.DriverManager;

public class Singleton 
{
    
    private final Connection instance;
    private final String url = "";
    private final String login = "";
    private final String password = "";
    
    private Singleton()
    {
        //instance = DriverManager.getConnection(url,login,password);
    }
    
}
