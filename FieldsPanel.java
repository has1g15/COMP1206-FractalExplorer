import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FieldsPanel extends JPanel {

	static JTextField uspField;
	private JSpinner realScale;
	private JSpinner imaginaryScale;
	private JSpinner iterations;
	private JSpinner changeX;
	private JSpinner changeY;
	private JButton save;
	private JButton load;
	private ArrayList<Complex> saved;
	
	public FieldsPanel(int x, int y, int width, int height, FractalModel mandelbrot, FractalModel julia)
	{
		this.setBounds(x, y, width, height);
		this.setLayout(new GridLayout(14,1));

		ChangeListener listener = new ChangeListener() 
		{
			  public void stateChanged(ChangeEvent e) 
			  {
				  //When spinners changed, image is rescaled 
				  Rectangle2D bounds = new Rectangle2D.Double();
				  bounds.setFrame((double)changeX.getValue(), (double)changeY.getValue(),
						  (double)realScale.getValue(), (double)imaginaryScale.getValue());
				  mandelbrot.setAxis(bounds);
				  mandelbrot.setIterations(
						  (int)iterations.getValue());
			  }
		};
			
		final String format = "0.0000";
		
		//Adding spinners to change the axis scales, x & y offsets and iterations 
		JLabel real = new JLabel("Scale Real Axis");
		realScale = new JSpinner(new SpinnerNumberModel(mandelbrot.getAxis().getWidth(), 0, 100.0, 0.1));
		realScale.addChangeListener(listener);
		realScale.setEditor(new JSpinner.NumberEditor(realScale, format));
	
		JLabel imaginary = new JLabel("Scale Imaginary Axis");
		imaginaryScale = new JSpinner(new SpinnerNumberModel(mandelbrot.getAxis().getHeight(), 0, 10.0, 0.1));
		imaginaryScale.addChangeListener(listener);
		imaginaryScale.setEditor(new JSpinner.NumberEditor(imaginaryScale, format));
		
		JLabel iter = new JLabel("Iterations");
		iterations = new JSpinner(new SpinnerNumberModel(mandelbrot.getIterations(), 0, 150, 1));
		iterations.addChangeListener(listener);
		
		JLabel xView = new JLabel("Change X Value");
		changeX = new JSpinner(new SpinnerNumberModel(mandelbrot.getAxis().getX(), -10.0, 10.0, 0.1));
		changeX.addChangeListener(listener);
		changeX.setEditor(new JSpinner.NumberEditor(changeX, format));
		
		JLabel yView = new JLabel("Change Y Value");
		changeY = new JSpinner(new SpinnerNumberModel(mandelbrot.getAxis().getY(), -10.0, 10.0, 0.1));
		changeY.addChangeListener(listener);
		changeY.setEditor(new JSpinner.NumberEditor(changeY, format));
		
		JLabel usp = new JLabel("User Selected Point");
		uspField = new JTextField(); 
		
		//When save button is clicked, the user selected point is passed into the add method of the Julia favourites class 
		LoadJuliaFavourites loadJuliaFavourites= new LoadJuliaFavourites(250, 250, julia);
		save = new JButton("Save");
		save.addActionListener(e ->
		{
			Complex c = julia.getC();
			loadJuliaFavourites.add(new Complex(c.getReal(), c.getImaginary()));
		});
		
		//When load button is clicked, frame opens to select complex number points 
		load = new JButton("Load");
		load.addActionListener(e ->
		{
			loadJuliaFavourites.popup();
		});
		
		//Adding components to panel of fields 
		this.add(real);
		this.add(realScale);
		this.add(imaginary);
		this.add(imaginaryScale);
		this.add(iter);
		this.add(iterations);
		this.add(usp);
		this.add(uspField);
		this.add(xView);
		this.add(changeX);
		this.add(yView);
		this.add(changeY);
		this.add(save);
		this.add(load);
	}
}
