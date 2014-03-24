import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class IHM extends JFrame
{
        private int status = -1;
        private int id = -1;
    
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
            JPanel littleJp = new JPanel();
            JPanel bigJp = new JPanel();
            //final JLabel jl = new JLabel();
            final JTextArea jta = new JTextArea();
            final JTextField jtf1 = new JTextField();
            final JTextField jtf2 = new JTextField();
            final JButton jb = new JButton();
            GridLayout littleGl = new GridLayout(1, 3);
            GridLayout bigGl = new GridLayout(5, 1);
            
            JRadioButtonMenuItem jrbmi = new JRadioButtonMenuItem();
            JRadioButton jrb0 = new JRadioButton("Client");
            JRadioButton jrb1 = new JRadioButton("Vendeur");
            JRadioButton jrb2 = new JRadioButton("Responsable");
            ButtonGroup bg = new ButtonGroup();
            bg.add(jrb0);
            bg.add(jrb1);
            bg.add(jrb2);
            
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
                                String request = jtf1.getText();
                                Singleton singleton = Singleton.getInstance();
                                Statement statement = singleton.getConnection().createStatement();
                                request = Filtre.filtrer(request, status, id);
                                statement.execute(request);
                                ResultSet rs = statement.getResultSet();
                                
                                StringBuilder sb = new StringBuilder();
                                String s = "";
                                
                                while (rs.next())
                                {
                                    //System.out.println(rs.getInt("IDCmd") + " | " + rs.getInt("Total") + " | " + rs.getInt("IDClient") + " | " + rs.getInt("IDVendeur"));
                                    
                                    if (status == 0)
                                        s += rs.getInt("IDCmd") + " | " + rs.getInt("Total") + " | " + rs.getInt("IDClient") + "\n";
                                    if (status == 1)
                                        s += rs.getInt("IDCmd") + " | " + rs.getInt("Total") + " | " + rs.getInt("IDClient") + " | " + rs.getInt("IDVendeur") + "\n";
                                    if (status == 2)
                                        s += rs.getInt("IDCmd") + " | " + rs.getInt("Total") + " | " + rs.getInt("IDClient") + " | " + rs.getInt("IDVendeur") + "\n";
                                    
                                    //sb.append(rs.getInt(1)).append('\n');
                                }
                                
//                                if (sb.toString().compareTo("") != 0)
//                                {
//                                    sb.append(rs.getInt(1)).append('\n');
//                                    jl.setText(sb.toString());
//                                }
                                if (s.compareTo("") != 0)
                                {
                                    //jl.setText(s);
                                    jta.setText(s);
                                }
                                else
                                {
                                    //jl.setText("nothing");
                                    jta.setText("nothing");
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
                        catch (Exception e)
                        {
                            System.out.println("Exception");
                        }
                    }
                }
            );
            
            jtf2.addActionListener
            (
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        id = Integer.parseInt(jtf2.getText());
                    }  
                }
            );
            
            jrb0.addActionListener
            (
                new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        status = 0;
                    }
                }
            );
            
            jrb1.addActionListener
            (
                new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        status = 1;
                    }
                }
            );
            
            jrb2.addActionListener
            (
                new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        status = 2;
                    }
                }
            );
            
            littleJp.setLayout(littleGl);
            littleJp.add(jrb0);
            littleJp.add(jrb1);
            littleJp.add(jrb2);
            bigJp.add(littleJp);
            bigJp.setLayout(bigGl);
            bigJp.add(jtf2);
            bigJp.add(jtf1);
            bigJp.add(jb);
            //bigJp.add(jl);
            bigJp.add(jta);
            this.add(bigJp);
        }
	
}
