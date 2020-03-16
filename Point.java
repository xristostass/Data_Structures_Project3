//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
public class Point {
	protected int x;
	protected int y;
	//constructor
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	//getters and setters
	public int x(){
		return x;
	} // return the x-coordinate
	public int y(){
		return y;
	} // return the y-coordinate
	//distance betweein two Points
	public double distanceTo(Point z){
		return Math.sqrt((x-z.x)*(x-z.x)+(y-z.y)*(y-z.y));
	}
	//square of the previous distance
	public int squareDistanceTo(Point z){
		return ((x-z.x)*(x-z.x)+(y-z.y)*(y-z.y));
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}
