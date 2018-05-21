
//  в классе реализуетс€ карта, состо€ща€ из двумернх €чеек
 
public class Map2D
{
    //ƒлина карты
    private int width;

    //¬ысота карты
    private int height;

    // «дессь будут хранитьс€ €чейки карты
     
    private int[][] cells;

    //стартова€ позици€
    private Location start;

    //конечна€ позици€
    private Location finish;


    //формирование карты заданной длиной и высотой
    public Map2D(int width, int height)
    {
        if (width <= 0 || height <= 0)
        {
            throw new IllegalArgumentException(
                    "width and height must be positive values; got " + width +
                    "x" + height);
        }
        
        this.width = width;
        this.height = height;
        
        cells = new int[width][height];
        
        start = new Location(0, height / 2);
        finish = new Location(width - 1, height / 2);
    }


    //провер€етс€ нахождение координат в пределах границ карты
    private void checkCoords(int x, int y)
    {
        if (x < 0 || x > width)
        {
            throw new IllegalArgumentException("x must be in range [0, " + 
                    width + "), got " + x);
        }
        
        if (y < 0 || y > height)
        {
            throw new IllegalArgumentException("y must be in range [0, " + 
                    height + "), got " + y);
        }
    }    
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    //провер€ет существование указанной координаты на карте
    public boolean contains(int x, int y)
    {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }
    
    
    //провер€ет существование указанного местоположени€ на карте
    public boolean contains(Location loc)
    {
        return contains(loc.xCoord, loc.yCoord);
    }
    
    public int getCellValue(int x, int y)
    {
        checkCoords(x, y);
        return cells[x][y];
    }
    
    //¬озвращает местоположение €чейки
    public int getCellValue(Location loc)
    {
        return getCellValue(loc.xCoord, loc.yCoord);
    }
    
    //”станавливает местоположение €чейки
    public void setCellValue(int x, int y, int value)
    {
        checkCoords(x, y);
        cells[x][y] = value;
    }
    
    public Location getStart()
    {
        return start;
    }
    
    public void setStart(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc cannot be null");
        
        start = loc;
    }

    public Location getFinish()
    {
        return finish;
    }
    
    public void setFinish(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc cannot be null");
        
        finish = loc;
    }
}