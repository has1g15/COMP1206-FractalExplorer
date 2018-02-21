import java.awt.BorderLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class FractalExplorerFrame extends JFrame{
	
	public FractalExplorerFrame()
	{
		//Creating separate models for mandelbrot and julia sets 
		FractalModel mandelbrot = new FractalModel(new Complex(0,0), new Rectangle2D.Double(-2, -1.6, 4, 3.2), 100);
		FractalModel julia = new FractalModel(new Complex(1,-1), new Rectangle2D.Double(-2, -1.6, 4, 3.2), 100);
		
		//Setting size of panels and passing in models to panels 
		FieldsPanel fieldsPanel = new FieldsPanel(0, 0, 200, 500, mandelbrot, julia);
		MandelbrotImage mandelbrotImage = new MandelbrotImage(200, 0, 600, 500, mandelbrot, julia);
		JuliaImage juliaImage = new JuliaImage(800, 0, 600, 500, julia);
		
		//Adding listeners for images to models 
		mandelbrot.addListener(mandelbrotImage);
		julia.addListener(juliaImage);
		
		//Setting frame properties 
		this.setTitle("Interactive Fractal Explorer");
		this.setSize(1300,525);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Separate panels are added to frame
		this.add(fieldsPanel);
		this.add(mandelbrotImage);
		this.add(juliaImage);
		mandelbrotImage.update();
		juliaImage.update();
		this.setVisible(true);
	}
}
