import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MandelbrotImage extends JPanel {

	BufferedImage fractal;
	FractalModel mandelbrotModel;
	FractalModel juliaModel;
	
	public MandelbrotImage(int x, int y, int width, int height, FractalModel mandelbrotModel, FractalModel juliaModel)
	{
		this.setBounds(x,y,width,height);
		fractal = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		this.mandelbrotModel = mandelbrotModel;
		this.juliaModel = juliaModel;
		//When point on mandelbrot image is clicked, julia set is created using the complex point c 
		this.addMouseListener(new MouseAdapter() {
			@Override
            public void mousePressed(MouseEvent e) 
			{
                juliaModel.setC(new Complex(mandelbrotModel.getAxis().getX() + ((double)e.getX()/width)*mandelbrotModel.getAxis().getWidth(),
                		mandelbrotModel.getAxis().getY() + ((double)e.getY()/height)*mandelbrotModel.getAxis().getHeight()));
                FieldsPanel.uspField.setText(juliaModel.getC().toString());
			}

            @Override
            public void mouseReleased(MouseEvent e) 
            {
            	
            }
        });	
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) 
			{
				
			}

			@Override
			public void mouseMoved(MouseEvent e) 
			{
					
			}
		});
	}
	
	public void paintComponent(Graphics g)
	{
		//Mandelbrot image is drawn on panel 
		update();
		g.drawImage(fractal, 0, 0, null);
	}
	
	//Updates pixel colours of image 
	public void update()
	{
		double real, imaginary;
		Rectangle2D rect = mandelbrotModel.getAxis();
		for (int x=0; x<this.getWidth(); x++)
		{
			//Pixel coordinates of display translated to points on the complex plane
			real = (rect.getX() + (((double)x)/this.getWidth())*rect.getWidth());
			for (int y=0; y<this.getHeight(); y++)
			{
				imaginary = (rect.getY() + ((double)y/this.getHeight())*rect.getHeight());
				mandelbrotModel.setC(new Complex(real, imaginary));
				fractal.setRGB(x, y, Color.HSBtoRGB(
						1-((float)calculateIterations(new Complex(0,0))/mandelbrotModel.getIterations())
						, 1, 1));
			}
		}
	}
	
	//Iterations are calculated from complex number passed in and c value from fractal model
	public int calculateIterations(Complex z)
	{
		int iterations = 0;
        while (z.modulusSquared() <= 4 && iterations < mandelbrotModel.getIterations()) {
            z.square();
            z.add(mandelbrotModel.getC());
            iterations++; 
        }
        return iterations;
	}
}
