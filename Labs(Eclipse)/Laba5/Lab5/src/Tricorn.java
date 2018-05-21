import java.awt.geom.Rectangle2D;

//import lab4.FractalGenerator;

public class Tricorn extends FractalGenerator{

    public static final int MAX_ITERATIONS = 2000;
    
  //метод, задающий место возникновения изображения
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -2;
        
        range.width = 4;
        range.height = 4;
    }
    
  //метод, вычисляющий количество итераций для соответствующей координаты
    public int numIterations(double x, double y)
    {
        int count = 0;
        
        double re = x;
        double im = y;
        
        double re2 = re*re;
        double im2 = im*im;
        
        double z_n2 = 0;
        
        while(count < MAX_ITERATIONS && z_n2 < 4)
        {
            im = ((-2) * re * im) + y;
            re = (re2 - im2) + x;
            
            re2  = re*re;
            im2  = im*im;
            
            z_n2 = re2 + im2;
            ++count;
        }
        
        return count < MAX_ITERATIONS ? count : -1;
    }
    
    
    // метод, возвращающий имя изображения
    public static String nameString()
    {
        return "Tricorn";
    }
    
}