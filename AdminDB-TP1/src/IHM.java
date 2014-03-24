import java.awt.Dimension;
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
                this.setSize(new Dimension(1600, 900));
                this.setResizable(false);
                this.initialize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setAlwaysOnTop(true);
                this.setVisible(true);
	}
        
        private void initialize()
        {
            JPanel jp = new JPanel();
            JLabel jl = new JLabel();
            JTextField jtf = new JTextField();
            JButton jb = new JButton();
            
            jb.addActionListener
            (
                new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        
                    }
                }
            );
            
            jp.add(jl);
            jp.add(jtf);
            jp.add(jb);
            this.add(jp);
        }
	
}
