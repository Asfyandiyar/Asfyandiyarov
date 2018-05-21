//этот класс работает с отдельными путевыми точками, которые 
//содержат информацию о местоположении, о предыдущей путевой точке и
//о наилучшем пути 
public class Waypoint
{
    Location loc;

    Waypoint prevWaypoint;

    //информация о пути из предыдущей точки в исходную   
    private float prevCost;

    //здесь хранится информация о том, сколько осталось до
    //пункта назначения
    private float remainingCost;


    //реализация новой путевой точки
    public Waypoint(Location loc, Waypoint prevWaypoint)
    {
        this.loc = loc;
        this.prevWaypoint = prevWaypoint;
    }

    public Location getLocation()
    {
        return loc;
    }
    
    public Waypoint getPrevious()
    {
        return prevWaypoint;
    }
    
    
     //здесь определяется предыдущий и оставшийся путь
    public void setCosts(float prevCost, float remainingCost)
    {
        this.prevCost = prevCost;
        this.remainingCost = remainingCost;
    }

    public float getPreviousCost()
    {
        return prevCost;
    }
    
    
    //возвращает информацию об оставшейся пути
    public float getRemainingCost()
    {
        return remainingCost;
    }

   //общая информация о путевой точке
    public float getTotalCost()
    {
        return prevCost + remainingCost;
    }
}
