//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
public class TreeNode {

	TreeNode leftNode;
	TreeNode rightNode;
	Point data;

	// constructor
	TreeNode(Point data) {
		this(data, null, null);
	}

	// constructor
	TreeNode(Point data, TreeNode leftNode, TreeNode rightNode) {
		this.data = data;
		this.leftNode = leftNode;
		this.rightNode = rightNode;

	}

	// return method to return the node's data
	Point getObject() {
		return data;
	}

	// getters and setters
	TreeNode getLeftNode() {
		return leftNode;
	}

	TreeNode getRightNode() {
		return rightNode;
	}

	void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}

	void setData(Point data) {
		this.data = data;
	}
}
