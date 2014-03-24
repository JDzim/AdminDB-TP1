import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IHM extends JFrame
{
	public IHM() throws ClassNotFoundException
	{
		this.setTitle("Filtre SQL");
                this.setSize(new Dimension(400, 150));
                this.setResizable(true);
                this.initialize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setAlwaysOnTop(false);
                this.setVisible(true);
	}
        
        private void initialize() throws ClassNotFoundException
        {
            JPanel jp = new JPanel();
            final JLabel jl = new JLabel();
            final JTextField jtf = new JTextField();
            final JButton jb = new JButton();
            GridLayout gl = new GridLayout(3, 1);
            
            jb.addActionListener
            (
                new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        try
                        {
                            if (ae.getSource().equals(jb))
                            {
                                String request = jtf.getText();
                                Singleton singleton = Singleton.getInstance();
                                Statement statement = singleton.getConnection().createStatement();
                                request = Filtre.filtrer(request, 1, 20);
                                statement.execute(request);
                                ResultSet rs = statement.getResultSet();
                               
                                rs.next();
                                StringBuilder sb = new StringBuilder();
                                
                                while (rs.next())
                                {
                                    sb.append(rs.getString(1)).append('\n');
                                    rs.next();
                                }
                                
                                if (rs != null)
                                {
                                    sb.append(rs.getString(1)).append('\n');
                                    jl.setText(sb.toString());
                                }
                                else
                                {
                                    jl.setText("nothing");
                                }
                            }
                        }
                        catch (ClassNotFoundException cnfe)
                        {
                            cnfe.printStackTrace();
                        }
                        catch (SQLException sqle)
                        {
                            sqle.printStackTrace();
                        }
                    }
                }
            );
            
            jp.setLayout(gl);
            jp.add(jtf);
            jp.add(jb);
            jp.add(jl);
            this.add(jp);
        }
        
        private class Action implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        }
	
}
