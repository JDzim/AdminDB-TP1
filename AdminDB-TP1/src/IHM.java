import java.awt.Dimension;
import javax.swing.JFrame;

public class IHM extends JFrame
{
	public IHM()
	{
		this.setTitle("Filtre SQL");
                this.setSize(new Dimension(1600, 900));
                this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setAlwaysOnTop(true);
                this.setVisible(true);
	}
	
}
