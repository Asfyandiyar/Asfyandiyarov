import java.io.*;
import static java.lang.Math.*;
//Класс "Lab1"
public class Lab1 {
	//основной метод main
	public static void main(String[] args) {
		Point3d p1 = new Point3d();
		Point3d p2 = new Point3d(3, 7, -11);
		Point3d p3 = new Point3d(4, 6, -14);
		if(p1.isEqual(p3)||p1.isEqual(p2)||p2.isEqual(p3))
			System.out.println("Invalid data");
		else {
			String print = String.format("The area is %.2f", computeArea(p1, p2, p3));
    		System.out.println(print);
		} 
				
	}


	//метод, который возвращает площадь фигуры, ограниченной тремя точками в пространстве
	public static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
		double S;
		double a = p1.distanceTo(p3);
		double b = p3.distanceTo(p2);
		double c = p2.distanceTo(p1);
		double p = (a + b + c)/2;
		
		S=sqrt(p*(p-a)*(p-b)*(p-c));

		return S;
	}
}

