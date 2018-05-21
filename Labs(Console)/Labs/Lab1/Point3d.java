
import static java.lang.Math.*;
//класс "Трехмерная точка"
public class Point3d {
	//координата Х точки
	private double xCoord;

	//координата Y точки
	private double yCoord;

	//координата Z точки
	private double zCoord;

	//конструктор инициализации
	public Point3d(double x, double y, double z) {
		xCoord = x;
		yCoord = y;
		zCoord = z;
	}

	//конструктор по умолчанию
	public Point3d() {
		this(0, 0, 0);
	}

	//метод, позволяющий получить координату X точки
	public double getX() {
		return xCoord;
	}

	//метод, позволяющий получить координату Y точки
	public double getY() {
		return yCoord;
	}

	//метод, позволяющий получить координату Z точки
	public double getZ() {
		return zCoord;
	}

	//метод, позволяющий изменить координату X точки
	public void setX(double val) {
		xCoord = val;
	}

	//метод, позволяющий изменить координату Y точки
	public void setY(double val) {
		yCoord = val;
	}

	//метод, позволяющий изменить координату Z точки
	public void setZ(double val) {
		zCoord = val;
	}

	//метод, сравнивающий два объекта
	public boolean isEqual(Point3d p1) {
		if(this.xCoord == p1.xCoord && this.yCoord == p1.yCoord && this.zCoord == p1.zCoord) 
			return true;
		else 
			return false;
	}

	//метод, возвращающий расстояние между двумя точками в пространстве
	public double distanceTo(Point3d p1) {
		double Distance = sqrt(pow((xCoord-p1.getX()), 2) + pow((yCoord-p1.getY()), 2) + pow((zCoord-p1.getZ()), 2));
		return Distance;
	}
}
