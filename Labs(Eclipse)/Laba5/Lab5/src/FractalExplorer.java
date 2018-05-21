import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;

public class FractalExplorer {
    
    //������ ����������� 
    private int displaySize;
    
    private int rowsRemaining;
    
   //��������� � ���������� ������ JImageDisplay, �� ������ ������������ ��������� ������ ��� ���������� ��������
    private JImageDisplay image;
    
    //��������� ���������� ���������
    private JComboBox<String> chooseFractal;
    
    private JButton saveButton;
    
    private JButton resetButton;
   
    private JFrame frame;
    
   //��������� � ���������� ������ FractalGenerator, �� ������ ������������ ��������
    private FractalGenerator generate;
    
    //�������� ����������� ��������� �����������
    Rectangle2D.Double range;

    //�����, � ������� �������������� ������� �� ������ ������ �����������
    private class ResetHandler implements ActionListener 
    { 
        public void actionPerformed(ActionEvent e) 
        { 
            range = new Rectangle2D.Double();
            generate.getInitialRange(range);
            
            drawFractal();
        } 
    }
    
    //�����, � ������� ������������� ����� ������������� �������� � 
    //����������� ���������� ���������� ����������� �������� �� ����
    private class FractalHandler implements ActionListener 
    { 
        public void actionPerformed(ActionEvent e) 
        { 
            String cmd = e.getActionCommand(); 

            if (e.getSource() == chooseFractal) 
            { 
                String selectedItem = chooseFractal.getSelectedItem().toString();

                if(selectedItem.equals(Mandelbrot.nameString()))
                {
                    generate = new Mandelbrot();
                }
                else if(selectedItem.equals(Tricorn.nameString()))
                {
                    generate = new Tricorn();
                }
                else if(selectedItem.equals(BurningShip.nameString()))
                {
                    generate = new BurningShip();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "������������ ������ �� ������");
                    return;
                }
                
                range = new Rectangle2D.Double();
                generate.getInitialRange(range);
                
                drawFractal();
            } 
            else if (cmd.equals("reset")) 
            { 
                range = new Rectangle2D.Double();
                generate.getInitialRange(range);
                
                drawFractal();
            } 
            else if (cmd.equals("save")) 
            { 
                JFileChooser chooser = new JFileChooser();
                
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                
                if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    try 
                    {
                        File fd = chooser.getSelectedFile();
                        String filePath = fd.getPath();
                        if(!filePath.toLowerCase().endsWith(".png"))
                        {
                            fd = new File(filePath + ".png");
                        }
                        
                        ImageIO.write(image.getImage(), "png", fd);
                    } 
                    catch (IOException exc) 
                    {
                        JOptionPane.showMessageDialog(null, "���������� ��������� ���� ( " + exc.getMessage() + " )");
                        
                        exc.printStackTrace();
                    }
                }
            } 
            else
            {
                JOptionPane.showMessageDialog(null, "������ ����������");
            }
        } 
    }
    
    //�����, � ������� ������������� ���������� ������� ������ ���� � ����������� �������
    //� ������� �������
    private class MouseHandler extends MouseAdapter 
    { 
        public void mouseClicked(MouseEvent e)
        { 
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, e.getX());
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, e.getY());
            
            generate.recenterAndZoomRange(range,xCoord, yCoord, 0.5);
            
            drawFractal();
        } 
    }
    
    //�����, � ������� ������������ ���� ��� ������ ��������
private class FractalWorker extends SwingWorker<Object, Object> {
        
       
        private int Y;
        
        
        private int[] rgbValues;
        
        public FractalWorker(int y)
        {
            Y = y;
        }
        
       //���� ����� ������������ ���������� �������� ������
        protected Object doInBackground()
        {
            rgbValues = new int[displaySize];
            
            int numIters;
            int rgbColor;
            
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, Y);
            double xCoord;
            float hue;
            
            for(int x = 0 ; x < displaySize ; ++x)
            {
            	xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
                
                numIters = generate.numIterations(xCoord, yCoord);
                if(numIters < 0)
                {
                    rgbColor = 0;
                }
                else
                {
                    hue = 0.7f + numIters / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }
                
                rgbValues[x] = rgbColor;
            }
            
            return null;
        }
        
        //���� ����� �������� � ��������, ����� ������� ������ �����������
        protected void done()
        {
            for(int x = 0 ; x < displaySize ; ++x)
            {
                image.drawPixel(x, Y, rgbValues[x]);
            }
            
            image.repaint(0, 0, Y, displaySize, 1);
            
            if((--rowsRemaining) < 1)
            {
                enableUI(true);
            }
        }
        
    }
    
    //�����������, ����������� ������ ����������� � �������� ��������� � ����������� � ������������
    //����, � ���������������� ������� ��������� � ���������� ���������
    public FractalExplorer(int dispSize)
    {
        displaySize = dispSize;
        
        generate = new Mandelbrot();
        
        range = new Rectangle2D.Double();
        generate.getInitialRange(range);
    }
    
    //�����, ���������������� JFrame, ������� �������� ������ JImageDisplay, ������������ 
    //�������, ������ ��� ������ �����������, ������ ������ ���������, � ������ ����������
    //�����������
    public void createAndShowGUI()
    {
        frame = new JFrame("Fractal Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout());
        
        FractalHandler handler = new FractalHandler();
        
        JPanel fractalPanel = new JPanel();
        
        JLabel panelLabel = new JLabel("Fractal: ");
        fractalPanel.add(panelLabel);
        
        chooseFractal = new JComboBox<String>();
        chooseFractal.addItem(Mandelbrot.nameString());
        chooseFractal.addItem(Tricorn.nameString());
        chooseFractal.addItem(BurningShip.nameString());
        chooseFractal.addActionListener(handler);
        
        fractalPanel.add(chooseFractal);
        
        contentPane.add(fractalPanel, BorderLayout.NORTH);

        image = new JImageDisplay(displaySize, displaySize);
        contentPane.add(image, BorderLayout.CENTER);
        
        JPanel buttonsPanel = new JPanel();
        
        saveButton = new JButton("Save Image");
        saveButton.setActionCommand("save"); 
        saveButton.addActionListener(handler);
        buttonsPanel.add(saveButton);
        
        resetButton = new JButton("Reset Display");
        resetButton.setActionCommand("reset"); 
        resetButton.addActionListener(handler);
        buttonsPanel.add(resetButton);
        
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);
        
        contentPane.addMouseListener(new MouseHandler());
        
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    //��������������� ����� ��� ����������� ��������
    public void drawFractal()
    {
        double xCoord = 0;
        double yCoord = 0;
        
        float numIters = 0;
        float hue = 0;
        
        int rgbColor = 0;
        
        for(int x = 0 ; x < displaySize ; ++x)
        {
        	// x - ���������� �������; xCoord - ���������� � ������������ �������� 
            xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
            
            for(int y = 0 ; y < displaySize ; ++y)
            {
            	// y - ���������� �������; yCoord - ���������� � ������������ �������� 
                yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
                
                numIters = generate.numIterations(xCoord, yCoord);
                if(numIters < 0)
                {
                    rgbColor = 0;
                }
                else
                {
                    hue = 0.7f + numIters / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }
                
                image.drawPixel(x, y, rgbColor);
            }
        }
        
        image.repaint();
    }
    
    
    //�������, � ������� ����������� ���������\���������� ��������� ����������
    private void enableUI(boolean val)
    {
        chooseFractal.setEnabled(val);
        
        saveButton.setEnabled(val);
        resetButton.setEnabled(val);
    }
    
    public static void main(String[] args) 
    {
        FractalExplorer explorer = new FractalExplorer (400);
        explorer.createAndShowGUI();
        explorer.drawFractal();
    } 
}
