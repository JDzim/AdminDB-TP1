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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class IHM extends JFrame
{
        private int status = -1;
        private int id = -1;
        private String[] headers = new String[] {"empty"};
        private Object[][] data = new String[][]{{"empty"}};
    
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
            JTable jt = new JTable();
            final JLabel jl = new JLabel();
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
                                
                                // Si la requete contient "select", on execute le filtre correspondant
                                if (request.toLowerCase().contains("select"))
                                {
                                    // On obtient une instance unique a la BdD
                                    Singleton singleton = Singleton.getInstance();
                                    
                                    // On initie un statement
                                    Statement statement = singleton.getConnection().createStatement();
                                    
                                    // On recupere l'id de la personne
                                    id = Integer.parseInt(jtf2.getText());
                                    
                                    // On applique le filtre
                                    request = Filtre.filtrer(request, status, id);
                                    
                                    // On execute la requete
                                    statement.execute(request);
                                    
                                    // On recupere le resultat
                                    ResultSet rs = statement.getResultSet();

                                    StringBuilder sb = new StringBuilder();
                                    String s = "";

                                    // Si le statut de la personne est 0 (client), alors on affiche les entetes Total et IDClient
                                    if (status == 0)
                                    {
                                        s += "IDCmd     " + " | " + "Total      " + " | " + "IDClient       " + "\n";
                                        headers = new String[]{"IDCmd" , "Total" , "IDClient"};
                                    }
                                    // Sinon on affiche les entetes Total et IDClient et IDVendeur
                                    else
                                    {
                                        s += "IDCmd     " + " | " + "Total      " + " | " + "IDClient       " + " | " + "IDVendeur      " + "\n";
                                        headers = new String[]{"IDCmd" , "Total" , "IDClient" , "IDVendeur"};
                                    }
                                        
                                    int i = 0;
                                    
                                    // On parcourt le ResultSet pour recuperer les donnees
                                    while (rs.next())
                                    {
                                        //System.out.println(rs.getInt("IDCmd") + " | " + rs.getInt("Total") + " | " + rs.getInt("IDClient") + " | " + rs.getInt("IDVendeur"));
                                        
                                        if (status == 0)
                                        {
                                            s += rs.getInt("IDCmd") + "      | " + rs.getInt("Total") + "        | " + rs.getInt("IDClient") + "\n";
                                            data = new Object[rs.getMetaData().getColumnCount()][3];
                                            data[i] = new Object[]{rs.getInt("IDCmd") , rs.getInt("Total") , rs.getInt("IDClient")};
                                        }
                                        else
                                        {
                                            s += rs.getInt("IDCmd") + "      | " + rs.getInt("Total") + "        | " + rs.getInt("IDClient") + "        | " + rs.getInt("IDVendeur") + "\n";
                                            data = new Object[rs.getMetaData().getColumnCount()][4];
                                            data[i] = new Object[]{rs.getInt("IDCmd") , rs.getInt("Total") , rs.getInt("IDClient") , rs.getInt("IDVendeur")};
                                        }

                                        //sb.append(rs.getInt(1)).append('\n');
                                        
                                        i++;
                                    }

    //                                if (sb.toString().compareTo("") != 0)
    //                                {
    //                                    sb.append(rs.getInt(1)).append('\n');
    //                                    jl.setText(sb.toString());
    //                                }
                                    
                                    // Si les donnees sont disponibles, on affiche les donnees
                                    if (s.compareTo("") != 0)
                                    {
                                        //jl.setText(s);
                                        System.out.println(s);
                                        jta.setText(s);
                                    }
                                    else
                                    {
                                        //jl.setText("nothing");
                                        System.out.println("nothing");
                                        jta.setText("nothing");
                                    }
                                }
                                
                                // Si la requete contient "update", on execute le filtre correspondant
                                if (request.toLowerCase().contains("update"))
                                {
                                    Singleton singleton = Singleton.getInstance();
                                    Statement statement = singleton.getConnection().createStatement();
                                    jta.setText(""+statement.executeUpdate(request));
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
                            e.printStackTrace();
                        }
                    }
                }
            );
            
            System.out.println(headers);
            System.out.println(data);
            
            jt = new JTable(data, headers);
            
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
            /*bigJp.add(jt);*/
            bigJp.add(jta);
            this.add(bigJp);
        }
	
}
