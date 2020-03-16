//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class TwoDTree {
	public TreeNode head;

	public TwoDTree() {
		// construct an empty tree
		head = null;
	}

	//constructor with initialization
	public TwoDTree(Point data) {
		head = new TreeNode(data, null, null);
	}

	public boolean isEmpty() {
		return (head == null);// is the tree empty?
	}

	static int N;// N represents the size of the tree.

	public int size() {
		N = 0;
		sizeR(head);
		return N;
		// number of points in the tree
	}
	//recursive method for calculating the tree's size.
	public void sizeR(TreeNode h) {

		if (h != null) {
			N++;
			sizeR(h.leftNode);
			sizeR(h.rightNode);
		}
	}
	// inserts the point p to the tree
	public void insert(Point p) {
		
		head = insertR(head, p, true);
	}
	//recursive method for inserting the point p to the tree.
	private TreeNode insertR(TreeNode h, Point p,
			boolean coordcomp) {/*
								 * the boolean coordcomp(coordinate
								 * compare)variable stands for the path that is
								 * to be followed.
								 */
		/*
		 * if it is true we compare the x coordinates.Otherwise we compare the y
		 * coordinates.
		 */
		if (h == null) {
			return new TreeNode(p);
		} else {
			//inserting and editing according to the x coordinate.
			if (coordcomp) {
				
				if (h.getObject().x > p.x)
					h.leftNode = insertR(h.leftNode, p, false);
				else
					h.rightNode = insertR(h.rightNode, p, false);
				return h;
				//inserting and editing according to the y coordinate.
			} else {
				if (h.getObject().y > p.y)
					h.leftNode = insertR(h.leftNode, p, true);
				else
					h.rightNode = insertR(h.rightNode, p, true);
				return h;
			}
		}
		// return h;
	}

	public boolean search(Point p) {
		// does the tree contain p?
		TreeNode result = searchR(head, p, true);// storing either the Node if
													// the search is successful
													// of either null if it's
													// not.
		if (result == null)
			return false;
		else
			return true;
	}
	//recursive method for searching the tree.
	private TreeNode searchR(TreeNode h, Point p, boolean coordcomp) {// coordcomp
																		// is
																		// the
																		// same
																		// as in
																		// the
																		// method
																		// insertR.
		if (h == null)
			return null;
		if (coordcomp) {
			//searching the tree according to the x coordinate.
			if (p.x == h.getObject().x)
				return h;
			if (h.getObject().x > p.x)
				return searchR(h.leftNode, p, false);
			else
				return searchR(h.rightNode, p, false);
		} else {
			//searching the tree according to the y coordinate.
			if (p.y == h.getObject().y)
				return h;
			if (h.getObject().y > p.y)
				return searchR(h.leftNode, p, true);
			else
				return searchR(h.rightNode, p, true);
		}
	}

	public Point nearestNeighbor(Point p) {// point in the tree that is closest to p(null if tree is empty)
		try {
			if (head == null) {
				return null;
			} else {
				Rectangle recmax = new Rectangle(0, 0, 100, 100);
				return nearestNeighborR(head, p, true,200000, recmax);
			}
		} catch (MinMaxException e) {
			System.err.println("There was a MinMaxException.");
		}
		return null;
	}
	//recursive method for pointing to the point in the tree closest to p.
	private Point nearestNeighborR(TreeNode h, Point p, boolean coordcomp, int distance, Rectangle rec) {

		try {
			Rectangle x1 = null, x2 = null;
			int distance1 = distance;//distance for the left Node.
			int distance2 = distance;//distance for the right node.
			if (h.leftNode == null && h.rightNode == null) {
				return h.getObject();
			}
			if (coordcomp) {
				//searching according to the x coordinate to the left child.
				 if (h.leftNode != null) {
					x1 = new Rectangle(rec.xmin, rec.ymin, h.leftNode.getObject().x, rec.ymax);
					distance1 =(x1.squareDistanceTo(p));
				}
				 //searching according to the x coordinate to the right child.
				if (h.rightNode != null) {
					x2 = new Rectangle(h.rightNode.getObject().x, rec.ymin, rec.xmax, rec.ymax);
					distance2 =(x2.squareDistanceTo(p));
				}
				if (distance1 < distance || distance2 < distance) {
					//recursively calling the method for the left and right child respectively.
					if (distance1 < distance2) {
						if (distance1 < distance) {
							return nearestNeighborR(h.leftNode, p, false, distance1, x1);
						} 
					} else {
						if (distance2 < distance) {
							return nearestNeighborR(h.rightNode, p, false, distance2, x2);
						}
					}
						
				}
				//searching according to the x coordinate to the right child.
			} else {
				 if (h.leftNode != null) {
					 
					x1 = new Rectangle(rec.xmin, rec.ymin, rec.xmax, h.leftNode.getObject().y);
					distance1 = (x1.squareDistanceTo(p));
					System.out.println("left"+distance +" " +distance1);
				} 
				if (h.rightNode != null) {
					
					x2 = new Rectangle(rec.xmin, h.rightNode.getObject().y, rec.xmax, rec.ymax);
					distance2 = (x2.squareDistanceTo(p));
					System.out.println("right"+ distance +" "+distance2);
				}
				if (distance1 < distance || distance2 < distance) {
					if (distance1 < distance2) {
						//recursively calling the method for the left and right child respectively.
						if (distance1 < distance) {
							return nearestNeighborR(h.leftNode, p, true, distance1, x1);
						} 
					} else {
						if (distance2 < distance) {
							return nearestNeighborR(h.rightNode, p, true, distance2, x2);
						} 
					}
					
				}
			}
			return h.getObject();
		} catch (MinMaxException e) {
			System.err.println("There was a MinMaxException.");
		}
		return null;
	}
	//Returns a list with the Points that are contained in the rectangle
	public StringStackImpl<Point> rangeSearch(Rectangle rect) {
		try {//storing the points to a stack.
			StringStackImpl<Point> points = new StringStackImpl<>();
			if (head != null) {
				Rectangle recmax = new Rectangle(0, 0, 100, 100);
				if (rect.contains(head.getObject())) {
					points.push(head.getObject());
				}
				rangeSearchR(rect, true, points, recmax, head);
			}

			return points;
		} catch (MinMaxException e) {
			System.err.println("There was a MinMaxException.");
		}
		return null;
	} 
	//recursive method for searching the points that are contained in a rectangle.
	private void rangeSearchR(Rectangle rect, boolean coordcomp, StringStackImpl<Point> points, Rectangle rec,
			TreeNode h) {
		try{
		Rectangle x1, x2;
		if (h.leftNode == null && h.rightNode == null) {
			return;
		}
		if (coordcomp) {
			//searching the left subtree according to the x coordinate.
			 if (h.leftNode != null) {
				x1 = new Rectangle(rec.xmin, rec.ymin, h.leftNode.getObject().x, rec.ymax);
				if (x1.intersects(rect)) {
					if (rect.contains(h.leftNode.getObject())) {
						points.push(h.leftNode.getObject());
					}
					//recursively calling the method for the left child.
					rangeSearchR(rect, false, points, rec, h.leftNode);
				}
			}
			if (h.rightNode != null) {
				//searching the right subtree according to the x.
				x2 = new Rectangle(h.rightNode.getObject().x, rec.ymin, rec.xmax, rec.ymax);
				if (x2.intersects(rect)) {
					if (rect.contains(h.rightNode.getObject())) {
						points.push(h.rightNode.getObject());
					}
					//recursively calling the method for the right child.
					rangeSearchR(rect, false, points, rec, h.rightNode);
				}

			}
		} else {
			//searching the left subtree according to the y coordinate.
			 if (h.leftNode != null) {
				x1 = new Rectangle(rec.xmin, rec.ymin, rec.xmax, h.leftNode.getObject().y);
				if (x1.intersects(rect)) {
					if (rect.contains(h.leftNode.getObject())) {
						points.push(h.leftNode.getObject());
					}
					rangeSearchR(rect, true, points, rec, h.leftNode);
				}
			}
			//searching the right subtree according to the y coordinate.
			if (h.rightNode != null) {
				x2 = new Rectangle(rec.xmin, h.rightNode.getObject().y, rec.xmax, rec.ymax);
				if (x2.intersects(rect)) {
					if (rect.contains(h.rightNode.getObject())) {
						points.push(h.rightNode.getObject());
					}
					rangeSearchR(rect, true, points, rec, h.rightNode);
				}

			}
		
		}}catch(MinMaxException e){
			System.err.println("There was a MinMaxException.");
		}
		
		

	}

	public static void main(String[] args) {
		int counter = 0;
		BufferedReader reader = null;
		String line = " ";
		StringTokenizer tok;
		int numofpoints = 0;
		int pointobjects = 0;
		Point[] points = null;//storing the points that we read from the file.
		boolean correctformat = true;
		boolean coordsoutofbounds = false;
		boolean repeatedpoint = false;
		Scanner in = new Scanner(System.in);
		// try and catch block for successfully opening the file
		try {
			reader = new BufferedReader(new FileReader(args[0]));

		} catch (FileNotFoundException e) {
			System.err.println("Error opening file!");
		}

		try {
			line = reader.readLine();
			//System.out.println(line);
			counter++;
			//ensuring the file's correct format.
			Point current;
			int px, py;
			while (line != null) {
				tok = new StringTokenizer(line);
				if (counter == 1) {
					numofpoints = Integer.parseInt(tok.nextToken());
					if (tok.hasMoreTokens()) {
						correctformat = false;
						break;
					}
					points = new Point[numofpoints];

				} else {
					px = Integer.parseInt(tok.nextToken());
					if (tok.hasMoreTokens()) {
						py = Integer.parseInt(tok.nextToken());
					} else {
						correctformat = false;
						break;
					}
					if (tok.hasMoreTokens()) {
						correctformat = false;
						break;
					}
					if (px > 100 && px < 0 && py > 100 && py < 0) {
						coordsoutofbounds = true;
						break;
					}//storing the Points.
					if (counter - 1 <= numofpoints) {
						current = new Point(px, py);
						for (int i = 0; i < pointobjects; i++) {
							if (current.x == points[i].x && current.y == points[i].y) {
								repeatedpoint = true;
								break;
							}
						}
						points[pointobjects] = current;
						pointobjects++;
					}else{
						correctformat=false;
						break;
					}

				}
				if (!correctformat) {
					break;
				}

				line = reader.readLine();
				counter++;
			}
		} catch (IOException e) {
			System.err.println("Error reading line " + counter + ".");
		}//creating the binary tree.
		if (correctformat && !coordsoutofbounds && !repeatedpoint) {
			TwoDTree input = new TwoDTree();
			for (int i = 0; i < pointobjects; i++) {
				input.insert(points[i]);
				//System.out.print(points[i] + "\n");
			}

			int answer, xmin, xmax, ymin, ymax;
			xmin = 0;
			ymin = 0;
			xmax = 0;
			ymax = 0;
			//menu
			while (true) {
				System.out.println(
						"Press 1 for query rectangle. \nPress 2 for query point. \nPress 3 for exit.\nInsert: ");
				answer = in.nextInt();
				in.nextLine();
				//query search calls the range search method.
				if (answer == 1) {
					System.out.println("Insert Xmin Xmax");
					line = in.nextLine();
					tok = new StringTokenizer(line);

					xmin = Integer.parseInt(tok.nextToken());
					if (tok.hasMoreTokens()) {
						xmax = Integer.parseInt(tok.nextToken());
					}
					System.out.println("Insert Ymin Ymax");
					line = in.nextLine();
					tok = new StringTokenizer(line);
					ymin = Integer.parseInt(tok.nextToken());
					if (tok.hasMoreTokens()) {
						ymax = Integer.parseInt(tok.nextToken());
					}
					Rectangle inputrec;
					try{
						inputrec = new Rectangle(xmin, ymin, xmax, ymax);
					
					}catch(MinMaxException e){
						System.err.println("There was a MinMaxException.");
						break;
					}
					StringStackImpl<Point> points1 = input.rangeSearch(inputrec);
					points1.printStack(null);
					//query point calls the nearest neighbor method.
				} else if (answer == 2) {
					System.out.println("Insert X Y");
					line = in.nextLine();
					tok = new StringTokenizer(line);
					xmin = Integer.parseInt(tok.nextToken());
					if (tok.hasMoreTokens()) {
						ymin = Integer.parseInt(tok.nextToken());
					}
					Point givenpoint = new Point(xmin, ymin);
					Point result = input.nearestNeighbor(givenpoint);
					if (result == null) {
						System.out.println("There's no point.");
					} else {
						System.out.println("Nearest Point is: " + result);
						System.out.println("The distance is: " + givenpoint.distanceTo(result));
					}
					//exiting the program.
				} else if (answer == 3) {
					System.out.println("Thank you very much.");
					break;
				}
			}
		} else if (coordsoutofbounds) {
			System.err.println("The coordinates are out of bounds.");
		} else if (repeatedpoint) {
			System.err.println("A point was given twice.");
		} else {
			System.err.println("The file's structure is not right!");
		}
	}
}