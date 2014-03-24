import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IHM extends JFrame
{
	public IHM()
	{
		this.setTitle("Filtre SQL");
                this.setSize(new Dimension(800, 450));
                this.setResizable(false);
                this.initialize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setAlwaysOnTop(true);
                this.setVisible(true);
	}
        
        private void initialize()
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
                        if (ae.getSource().equals(jtf))
                        {
                            
                        }
                        if (ae.getSource().equals(jb))
                        {
                            String query = jtf.getText();
                            //Connection c = Singleton.getConnection();
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
	
}
