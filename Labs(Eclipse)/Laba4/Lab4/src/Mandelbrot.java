import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;
    
    //метод, задающий место возникновения изображения
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        
        range.width = 3;
        range.height = 3;
    }
    
   //метод, вычисляющий количество итераций для соответствующей координаты
    public int numIterations(double x, double y)
    {
        int count = 0;
        
        double re = x;
        double im = y;
        
        double nextRe = re*re;
        double nextIm = im*im;
        
        double znpow2 = 0;
        
        while(count < MAX_ITERATIONS && znpow2 < 4)
        {
            im = (2 * re * im) + y;
            re = (nextRe - nextIm) + x;
            
            nextRe  = re*re;
            nextIm  = im*im;
            
            znpow2 = nextRe + nextIm;
            ++count;
        }
        
        return count < MAX_ITERATIONS ? count : -1;
    }
    
    // метод, возвращающий имя изображения
    public static String nameString()
    {
        return "Mandelbrot";
    }
}