import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton 
{
    
    private static Singleton instance = null;
    private static Connection connection = null;
    //private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String driver = "com.mysql.jdbc.Driver";
    //private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String url = "jdbc:mysql://localhost:3306/Magasin";
    //private final String login = "system";
    private final String login = "root";
    //private final String password = "ftyui";
    private final String password = "";
    
    private Singleton() throws ClassNotFoundException, SQLException
    {
        Class.forName(driver);
        connection = DriverManager.getConnection(url,login,password);
    }
    
    public static Singleton getInstance() throws ClassNotFoundException, SQLException
    {
        if (instance == null)
            instance = new Singleton();
        
        return instance;
    }
    
    public Connection getConnection()
    {
        return connection;
    }
    
}
