import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JuliaImage extends JPanel{

	BufferedImage fractal;
	FractalModel fractalModel;
	
	public JuliaImage(int x, int y, int width, int height, FractalModel fractalModel)
	{
		this.setBounds(x, y, width, height);
		fractal = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		this.fractalModel = fractalModel;
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		//Julia image is drawn on panel 
		update();
		g.drawImage(fractal, 0, 0, null);
	}
	
	//Updates pixel colours of image 
	public void update()
	{ 
		double real, imaginary;
		Rectangle2D rect = fractalModel.getAxis();
		for (int x=0; x<this.getWidth(); x++)
		{
			//Pixel coordinates of display translated to points on the complex plane 
			real = (rect.getX() + (((double)x)/this.getWidth())*rect.getWidth());
			for (int y=0; y<this.getHeight(); y++)
			{
				imaginary = (rect.getY() + ((double)y/this.getHeight())*rect.getHeight());
				fractal.setRGB(x, y, 
						Color.HSBtoRGB(
								1-((float)calculateIterations(new Complex(real, imaginary))/fractalModel.getIterations())
								, 1, 1));
			}
		}
	}
	
	//Iterations are calculated from complex number passed in and fixed c value from fractal model 
	public int calculateIterations(Complex z)
	{
		int iterations = 0;
        while (z.modulusSquared() <= 4 && iterations < fractalModel.getIterations()) {
            z.square();
            z.add(fractalModel.getC());
            iterations++; 
        }
        return iterations;
	}
}
