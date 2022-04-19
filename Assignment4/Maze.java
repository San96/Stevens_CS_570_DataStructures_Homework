package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Sangram Thorat - CWID - 20007345
 * 
 * 
 **/
public class Maze implements GridColors {

	/** The maze */
	private TwoDimGrid maze;

	public Maze(TwoDimGrid m) {
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath() {
		findAllMazePaths(0, 0);
		findMazePathMin(0, 0);
		return findMazePath(0, 0); // (0, 0) is the start point.
	}

	/**
	 * Attempts to find a path through point (x, y).
	 * 
	 * @pre Possible path cells are in BACKGROUND color; barrier cells are in
	 *      ABNORMAL color.
	 * @post If a path is found, all cells on it are set to the PATH color; all
	 *       cells that were visited but are not on the path are in the TEMPORARY
	 *       color.
	 * @param x The x-coordinate of current point
	 * @param y The y-coordinate of current point
	 * @return If a path through (x, y) is found, true; otherwise, false
	 */
	public boolean findMazePath(int x, int y) {
		// COMPLETE HERE FOR PROBLEM 1
		// variable to get number of rows
		int rows = maze.getNRows() - 1;
		// variable to get number of columns
		int cols = maze.getNCols() - 1;

		// condition for pointer if its out of bound

		if (x < 0 || y < 0 || x > cols || y > rows) {
			return false;
		}

		// return false - condition when current pointer is not the part of path
		if (maze.getColor(x, y) != NON_BACKGROUND) {
			return false;
		}

		// Return True if current pointer reached the exit
		if (x == cols && y == rows) {
			maze.recolor(x, y, PATH);
			return true;
		}
		// Setting current pointer to path if it is not reached or cannot find the exit
		maze.recolor(x, y, PATH);

		if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
			return true;
		}
		// Color again the current pointer to TEMPORARY which is set to black

		else {
			// Color again the current pointer to TEMPORARY which is set to black

			maze.recolor(x, y, TEMPORARY);
			return false;
		}

	}

	// ADD METHOD FOR PROBLEM 2 HERE
	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) throws IllegalArgumentException {

		int nRows = maze.getNRows() - 1;
		int nCols = maze.getNCols() - 1;
		if (x < 0 || y < 0 || x > nCols || y > nRows) {
			throw new IllegalArgumentException();
		}

		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>();
		findMazePathStackBased(0, 0, result, trace);
		if (result.size() != 0) {
			System.out.println("All the paths to the exit are: ");
			System.out.println(result.get(0));
			for (int i = 1; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		}
		return result;
	}

	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		// Push the point to trace
		// trace.push(new PairInt(x, y));
		int nRows = maze.getNRows() - 1;
		int nCols = maze.getNCols() - 1;

		if (x < 0 || y < 0 || x > nCols || y > nRows) {
			// After visiting this point, remove it from the trace
			// trace.pop();
			return;
		}

		if (maze.getColor(x, y) != NON_BACKGROUND) {
			// trace.pop();
			return;
		}

		if (x == nCols && y == nRows) {
			PairInt pair = new PairInt(x, y);
			ArrayList<PairInt> temp = new ArrayList<PairInt>();
			trace.push(pair);
			temp.addAll(trace);
			result.add(temp);
			// After visiting this point, remove it from the trace
			trace.pop();
			return;
		} else {
			PairInt pair = new PairInt(x, y);
			// Color again the current pointer to PATH which is set to green
			maze.recolor(x, y, PATH);
			trace.push(pair);
			findMazePathStackBased(x - 1, y, result, trace);
			findMazePathStackBased(x + 1, y, result, trace);
			findMazePathStackBased(x, y - 1, result, trace);
			findMazePathStackBased(x, y + 1, result, trace);

			trace.pop();
			// Color again the current pointer to NON_BACKGROUND which is set to red

			maze.recolor(x, y, NON_BACKGROUND);
		}

	}

	// ADD METHOD FOR PROBLEM 3 HERE

	// Helper method for findMazePathMin
	public boolean findMinpath(int x, int y, Stack<PairInt> path, ArrayList<PairInt> result) {

		if (x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || x < 0 || y < 0)
			return false;
		if (maze.getColor(x, y) != NON_BACKGROUND)
			return false;
		if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {

			path.push(new PairInt(x, y));
			if (path.size() < result.size() || result.size() == 0) {
				result.clear();
				result.addAll(path);
			}
			path.pop();
			return true;
		} else {
			maze.recolor(x, y, PATH);
			path.push(new PairInt(x, y));
			boolean p1, p2, p3, p4;
			p1 = findMinpath(x - 1, y, path, result);
			p2 = findMinpath(x + 1, y, path, result);
			p3 = findMinpath(x, y - 1, path, result);
			p4 = findMinpath(x, y + 1, path, result);
			maze.recolor(x, y, NON_BACKGROUND);
			path.pop();
			if (p1 | p2 | p3 | p4) {

				return true;
			} else
				return false;
		}
	}

	//Method to find minimum path of maze
	public ArrayList<PairInt> findMazePathMin(int x, int y) {

		ArrayList<PairInt> result = new ArrayList<>();
		Stack<PairInt> path = new Stack<>();
		findMinpath(0, 0, path, result);
		if (result.size() != 0) {
			System.out.println("\n The shortest path to the path is");
			System.out.println(result);
		}
		return result;
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp() {
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore() {
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}
	/* </exercise> */
}
/* </listing> */
