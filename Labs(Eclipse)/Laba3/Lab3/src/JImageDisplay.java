import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent{

    private BufferedImage image;
    
    //конструктор, в котором инициализируется член BufferedImage и вызывает метод, который
    //позволит полностью отображать изображение
    public JImageDisplay(int width, int height)
    {   
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        Dimension dimension = new Dimension(width, height);
        super.setPreferredSize(dimension);
    }
    
    //метод, который рисует изображение
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }
    
    //метод, рисующий пиксели черного цвета
    public void clearImage()
    {
        for(int j = 0 ; j < image.getHeight() ; ++j)
        {
            for(int i = 0 ; i < image.getWidth() ; ++i)
            {
                this.drawPixel(i, j, 0);
            }
        }
    }
    //метод, рисующий пиксели определенного цвета
    public void drawPixel(int x, int y, int rgbColor)
    {
        image.setRGB(x, y, rgbColor);
    }
    
}
