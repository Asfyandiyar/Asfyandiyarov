// ласс, в котором наход€тс€ данные о местоположении

public class Location
{
    public int xCoord;

    public int yCoord;

    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    public Location()
    {
        this(0, 0);
    }
    
    
    public boolean equals (Object object) {
    	
    	if (object instanceof Location) {
    		Location other = (Location) object;
    		
    		if (xCoord == other.xCoord && yCoord == other.yCoord) {
    		 return true;	
    		}
    	}
    	return false;
    }
    
    
    public int hashCode() {
   	 int result = 17; 
   	
   	 result = 37 * result + (xCoord * 13);
   	 result = 37 * result + (yCoord * 15);
   
   	 return result;
   	} 
}