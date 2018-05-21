import java.util.HashMap;

//этот класс хранит коллекции открытых и закрытых путевых точек и 
//предоставляет основные операции, необходимые для функционирования алгоритма A *
public class AStarState
{

    private Map2D map;


    //  открытые путевыe точки 
    private HashMap<Location, Waypoint> openWaypoints;
    // закрытые путевые точки 
    private HashMap<Location, Waypoint> closedWaypoints; 
    
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
        
        openWaypoints = new HashMap<Location, Waypoint>();
        closedWaypoints = new HashMap<Location, Waypoint>();
    }

    public Map2D getMap()
    {
        return map;
    }

    //Эта функция сканирует все точки маршрута в коллекции открытых путевых точек и
    //возвращает ссылку на путевую точку с наименьшей общей стоимостью
    public Waypoint getMinOpenWaypoint()
    {
    	Waypoint minWayPoint = null;
        
        float min = Float.POSITIVE_INFINITY;
        float totalCost = 0;
        
        for(Waypoint p : openWaypoints.values())
        {
            totalCost = p.getTotalCost();
            
            if(min > totalCost)
            {
                min = totalCost;
                minWayPoint = p;
            }
        }
        
        return minWayPoint;
    }

    //добавляет указанную путевую точку, если существующая путевая точка хуже новой
    public boolean addOpenWaypoint(Waypoint newWP)
    {
    	Waypoint wayPointOpen = openWaypoints.get(newWP.getLocation());
        
        if(wayPointOpen == null || newWP.getPreviousCost() < wayPointOpen.getPreviousCost())
        {
            openWaypoints.put(newWP.getLocation(), newWP);
            return true;
        }

        return false;
    }

    //возвращает количество путевых точек в коллекции открытых путевых точек
    public int numOpenWaypoints()
    {
    	return openWaypoints.size();
    }


  //Эта функция берет путевую точку и перемещает ее из коллекции открытых путевых точек 
    //в коллекцию закрытых путевых точек
    public void closeWaypoint(Location loc)
    {
    	Waypoint point = openWaypoints.remove(loc);
        
        if(point != null)
        {
            closedWaypoints.put(loc, point);
        }
    }

    //определяет, находится указанное местоположение в коллекции закрытых точек или нет
    public boolean isLocationClosed(Location loc)
    {
    	return closedWaypoints.containsKey(loc);
    }
}
