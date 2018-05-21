import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


//это настраиваемый компонент Swing, который используетс€ дл€ отображени€ состо€ни€ €чеек на карте
public class JMapCell extends JComponent
{
    private static final Dimension CELL_SIZE = new Dimension(12, 12);
    

    boolean endpoint = false;
    
    boolean passable = true;
    
    boolean path = false;
    
    //строит новую €чейку карты с учетом проходимости
    public JMapCell(boolean pass)
    {

        setPreferredSize(CELL_SIZE);
        
        setPassable(pass);
    }
    
    public JMapCell()
    {
        this(true);
    }
    
    //отмечает €чеку, как конечную или начальную
    public void setEndpoint(boolean end)
    {
        endpoint = end;
        updateAppearance();
    }
    
    //отмечает, €вл€етс€ ли €чейка проходимой или нет
    public void setPassable(boolean pass)
    {
        passable = pass;
        updateAppearance();
    }
    
    //определ€ет проходимость €чейки
    public boolean isPassable()
    {
        return passable;
    }
    
    //переключает проходимое состо€ние €чейки в новое
    public void togglePassable()
    {
        setPassable(!isPassable());
    }
    
    //отмечает €чейку частью отмеченного пути
    public void setPath(boolean path)
    {
        this.path = path;
        updateAppearance();
    }
    
    //обновл€ет цвет фона €чейки с учетом проходимости
    private void updateAppearance()
    {
        if (passable)
        {
            setBackground(Color.WHITE);

            if (endpoint)
                setBackground(Color.CYAN);
            else if (path)
                setBackground(Color.GREEN);
        }
        else
        {
            setBackground(Color.RED);
        }
    }

    
    protected void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}