/**
 * 
 */
package Maze;

/**
 * UML Diagram ----------------------------------------- PairInt
 * ------------------------------------------- private int x private int y
 * -------------------------------------------- public PairInt(int x, int y)
 * public int getX() public int getY() public void setX(int x) public void
 * setY(int y) public boolean equals(Object p) public String toString() public
 * PairInt copy() ---------------------------------------------
 * 
 * @author Sangram Thorat CWID : 20007345
 *
 */
public class PairInt {

	// data fields
	private int x;
	private int y;

	// constructor
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// getter setter properties implementation

	// get for x
	public int getX() {
		return x;
	}

	// get for y
	public int getY() {
		return y;
	}

	// set for x
	public void setX(int _x) {
		x = _x;
	}

	// set for y
	public void setY(int _y) {
		y = _y;
	}

	public boolean equals(PairInt p) {

		if (p == null) {
			return false;
		}

		if (p.getX() == this.x && p.getY() == this.y)
			return true;
		else
			return false;

	}

	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	public PairInt copy() {
		return new PairInt(this.x, this.y);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
