import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FractalModel {

	private Complex c;
	private Rectangle2D axis;
	private int iterations;
	private ArrayList<JPanel> listeningPanels;
	
	//Fractal model using getters and setters for complex point, axes and iterations 
	public FractalModel(Complex c, Rectangle2D axis, int iterations)
	{
		this.c = c;
		this.axis = axis;
		this.iterations = iterations;
		listeningPanels = new ArrayList<>();
	}

	//Method adding panel argument to array list 
	public void addListener(JPanel listener)
	{
		listeningPanels.add(listener);
	}
	
	private void alertListeners()
	{
		//Repaint method called on all the panels in the array list 
		for(JPanel panel:listeningPanels)
		{
			panel.repaint();	
		}
	}
	
	public Complex getC() 
	{
		return c;
	}

	public void setC(Complex c) 
	{
		this.c = c;
		alertListeners();
	}

	public Rectangle2D getAxis() 
	{
		return axis;
	}

	public void setAxis(Rectangle2D axis) 
	{
		this.axis = axis;
		alertListeners();
	}

	public int getIterations() 
	{
		return iterations;
	}

	public void setIterations(int iterations) 
	{
		this.iterations = iterations;
		alertListeners();
	}
}
