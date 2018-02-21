import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LoadJuliaFavourites extends JFrame{

	JList favourites;
	JScrollPane scrollPane;
	ArrayList<Complex> saved;
	
	//Creates JFrame with options to load a stored complex number and create Julia set 
	public LoadJuliaFavourites(int width, int height,FractalModel julia)
	{
		this.setTitle("Select Julia Favourite");
		this.setSize(width, height);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setSize(250,250);
		saved = new ArrayList<>();
		saved.add(new Complex(0,0));
		favourites = new JList(saved.toArray());
		JButton ok = new JButton("OK");
		//Complex number is set in julia fractal model when ok button is clicked 
		ok.addActionListener(e ->
		{
			julia.setC((Complex)favourites.getSelectedValue());
			System.out.println(((Complex)favourites.getSelectedValue()).toString());
			this.setVisible(false);
			
		});
		JButton cancel = new JButton("Cancel");
		this.add(panel);
		panel.add(favourites);
		panel.add(ok);
		panel.add(cancel);
	}
	
	//Adds number to saved array list, method is called when save button is clicked 
	public void add(Complex c)
	{
		saved.add(c);
		favourites.setListData(saved.toArray());
	}
	
	//When load button is clicked, Frame pops up 
	public void popup()
	{
		this.setVisible(true);
	}
}
