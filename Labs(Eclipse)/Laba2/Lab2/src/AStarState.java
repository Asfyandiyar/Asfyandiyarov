import java.util.HashMap;

//���� ����� ������ ��������� �������� � �������� ������� ����� � 
//������������� �������� ��������, ����������� ��� ���������������� ��������� A *
public class AStarState
{

    private Map2D map;


    //  �������� ������e ����� 
    private HashMap<Location, Waypoint> openWaypoints;
    // �������� ������� ����� 
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

    //��� ������� ��������� ��� ����� �������� � ��������� �������� ������� ����� �
    //���������� ������ �� ������� ����� � ���������� ����� ����������
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

    //��������� ��������� ������� �����, ���� ������������ ������� ����� ���� �����
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

    //���������� ���������� ������� ����� � ��������� �������� ������� �����
    public int numOpenWaypoints()
    {
    	return openWaypoints.size();
    }


  //��� ������� ����� ������� ����� � ���������� �� �� ��������� �������� ������� ����� 
    //� ��������� �������� ������� �����
    public void closeWaypoint(Location loc)
    {
    	Waypoint point = openWaypoints.remove(loc);
        
        if(point != null)
        {
            closedWaypoints.put(loc, point);
        }
    }

    //����������, ��������� ��������� �������������� � ��������� �������� ����� ��� ���
    public boolean isLocationClosed(Location loc)
    {
    	return closedWaypoints.containsKey(loc);
    }
}
