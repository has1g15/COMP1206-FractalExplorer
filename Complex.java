
public class Complex {

	private double real;
	private double imaginary;
	
	public Complex(double real, double imaginary)
	{
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public double getReal()
	{
		return real;
	}
	
	public double getImaginary()
	{
		return imaginary;
	}
	
	//Squares the complex number 
	public void square()
	{
		double sqReal = (real*real) - (imaginary*imaginary);
		double sqImaginary = 2*real*imaginary;
		this.setReal(sqReal);
		this.setImaginary(sqImaginary);
	}
	
	//Returns the square of the modulus of the complex number 
	public double modulusSquared()
	{
		return real*real + imaginary*imaginary;
	}
	
	//Adds the complex number d to the complex number 
	public void add(Complex d)
	{
		real+=d.getReal();
		imaginary+=d.getImaginary();
	}

	public void setReal(double real) {
		this.real = real;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}
	
	//Formats the number of decimal places of the complex point 
	public String toString()
	{
		return String.format("%.3f", this.real) +
				", " + String.format("%.3f", this.imaginary) + "i";
	}
	
}
