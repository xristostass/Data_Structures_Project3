//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
 import java.lang.*;
public class Rectangle {
	protected int xmin;
	protected int ymin;
	protected int xmax;
	protected int ymax;
	//constructor 
	public Rectangle(int xmin, int ymin, int xmax, int ymax)throws MinMaxException{
		if(xmin>xmax){
			throw new MinMaxException("Xmin is larger than Xmax.");
		}
		if(ymin>ymax){
			throw new MinMaxException("Ymin is larger than Ymax.");
		}
		this.xmin=xmin;
		this.ymin=ymin;
		this.xmax=xmax;
		this.ymax=ymax;
		
	}
		// construct the rectangle [xmin, ymin] x [xmax, ymax]
	public int xmin(){
		return xmin;
	} // minimum x-coordinate of rectangle

	public int ymin(){
		return ymin;
	} // minimum y-coordinate of rectangle

	public int xmax(){
		return xmax;
	} // maximum x-coordinate of rectangle

	public int ymax(){
		return ymax;
	} // maximum y-coordinate of rectangle

	public boolean contains(Point p){
		boolean returnvalue=false;
		if (p.x>=xmin && p.x<=xmax && p.y>=ymin && p.y<=ymax){
			returnvalue=true;
		}
		return returnvalue;
	} //does p belong to the rectangle?

	public boolean intersects(Rectangle that){
		boolean intersection=false;
		Point x1 =new Point(xmin,ymin);
		Point x2 =new Point(xmax,ymax);
		Point x3 =new Point(xmin,ymax);
		Point x4 =new Point(xmax,ymin);
		//various cases comparing the rectangles' positions
		intersection=that.contains(x1);
		if(intersection){
			return true;
		}
		intersection=that.contains(x2);
		if(intersection){
			return true;
		}
		intersection=that.contains(x3);
		if(intersection){
			return true;
		}
		intersection=that.contains(x4);
		if(intersection){
			return true;
		}
		x1 =new Point(that.xmin,that.ymin);
		x2 =new Point(that.xmax,that.ymax);
		x3 =new Point(that.xmin,that.ymax);
		x4 =new Point(that.xmax,that.ymin);
		intersection=contains(x1);
		//checking and comparing both rectangles
		if(intersection){
			return true;
		}
		intersection=contains(x2);        
		if(intersection){
			return true;
		}
		intersection=contains(x3);
		if(intersection){
			return true;
		}
		intersection=contains(x4);
		if(intersection){
			return true;
		}
		if(xmin<=that.xmin && xmax>=that.xmax && ymin>= that.ymin && ymax<=that.ymax){
			return true;
		}
		if(that.xmin<=xmin && that.xmax>=xmax && that.ymin>=ymin && that.ymax<=ymax){
			return true;
		}
		return false;
	} // do the two rectangles
		// intersect?

	public double distanceTo(Point p){
		Point x;
		if(contains(p)){
			return 0.0;
		}
		if(p.x<=xmin && p.y<=ymin){
			x=new Point (xmin,ymin);
			return x.distanceTo(p);
		}else if (p.x<=xmin&& p.y<=ymax&&p.y>=ymin){
			return xmin-p.x;
		}else if(p.x<=xmin&&p.y>=ymax){
			x=new Point (xmin,ymax);
			return x.distanceTo(p);
		}else if(p.x>=xmin&&p.x<=xmax&&p.y>=ymax){
			return p.y-ymax;
		}else if(p.x>=xmax&&p.y>=ymax){
			x=new Point (xmax,ymax);
			return x.distanceTo(p);
		}else if(p.x>=xmax&& p.y<=ymax&&p.y>=ymin){
			return p.x-xmax;
		}else if (p.x>=xmax&&p.y<=xmin){
			x=new Point (xmax,ymin);
			return x.distanceTo(p);
		}else {
			return ymin-p.y;
		}
	} // Euclidean distance from p
		//to closest point in rectangle

	public int squareDistanceTo(Point p){
		Point x;
		if(contains(p)){
			return 0;
		}
		if(p.x<=xmin && p.y<=ymin){
			x=new Point (xmin,ymin);
			return x.squareDistanceTo(p);
		}else if (p.x<=xmin&& p.y<=ymax&&p.y>=ymin){
			return (xmin-p.x)*(xmin-p.x);
		}else if(p.x<=xmin&&p.y>=ymax){
			x=new Point (xmin,ymax);
			return x.squareDistanceTo(p);
		}else if(p.x>=xmin&&p.x<=xmax&&p.y>=ymax){
			return (p.y-ymax)*(p.y-ymax);
		}else if(p.x>=xmax&&p.y>=ymax){
			x=new Point (xmax,ymax);
			return x.squareDistanceTo(p);
		}else if(p.x>=xmax&& p.y<=ymax&&p.y>=ymin){
			return (p.x-xmax)*(p.x-xmax);
		}else if (p.x>=xmax&&p.y<=xmin){
			x=new Point (xmax,ymin);
			return x.squareDistanceTo(p);
		}else {
			return (ymin-p.y)*(ymin-p.y);
		}
		
	} // square of Euclidean
		// distance from p to closest point in rectangle

	public String toString(){
		return "["+xmin+","+xmax+"] x ["+ymin+","+ymax+"]";
	} // string representation:
		// [xmin, xmax] x [ymin, ymax]
	
}
