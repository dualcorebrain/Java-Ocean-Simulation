package seaSimulations;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Represent a rectangular grid of field positions.
 * Each position is able to store a single creature.
 * 
 * @author David J. Barnes and Michael Kolling
 * @author Maria Chli
 * @version 18-Oct-2006
 */
public class Field
{
	// The depth and width of the field.
	private int depth, width;
	// Storage for the creatures.
	private Creature[][] field;
	
	/**
	 * Represent a field of the given dimensions.
	 * The topology of the grid is torus shaped. 
	 * @param depth The depth of the field.
	 * @param width The width of the field.
	 */
	public Field(int depth, int width)
	{
		this.depth = depth;
		this.width = width;
		field = new Creature[depth][width];
	}
	
	/**
	 * Empty the field.
	 */
	public void clear()
	{
		for(int row = 0; row < depth; row++) {
			for(int col = 0; col < width; col++) {
				field[row][col] = null;
			}
		}
	}
	
	/**
	 * Place a creature at the given location.
	 * If there is already a creature at the location it will
	 * be lost.
	 * @param creature The creature to be placed.
	 * @param row Row coordinate of the location.
	 * @param col Column coordinate of the location.
	 */
	public void place(Creature creature, int row, int col)
	{
		place(creature, new Location(row, col));
	}
	
	/**
	 * Place a creature at the given location.
	 * If there is already a creature at the location it will
	 * be lost.
	 * @param creature The creature to be placed.
	 * @param location Where to place the creature.
	 */
	public void place(Creature creature, Location location)
	{
		field[location.getRow()][location.getCol()] = creature;
	}
	
	/**
	 * Return the creature at the given location, if any.
	 * @param location Where in the field.
	 * @return The creature at the given location, or null if there is none.
	 */
	public Creature getObjectAt(Location location)
	{
		return getObjectAt(location.getRow(), location.getCol());
	}
	
	/**
	 * Return the creature at the given location, if any.
	 * @param row The desired row.
	 * @param col The desired column.
	 * @return The creature at the given location, or null if there is none.
	 */
	public Creature getObjectAt(int row, int col)
	{
		return field[row][col];
	}
	

	/**
	 * Try to find a free location that is adjacent to the
	 * given location. If there is none, then return the current
	 * location if it is free. If not, return null.
	 * The returned location will be within the valid bounds
	 * of the field.
	 * @param location The location from which to generate an adjacency.
	 * @return A valid location within the grid area. This may be the
	 *         same object as the location parameter, or null if all
	 *         locations around are full.
	 */
	public Location freeAdjacentLocation(Location location)
	{
		Iterator adjacent = adjacentLocations(location);
		while(adjacent.hasNext()) {
			Location next = (Location) adjacent.next();
			if(field[next.getRow()][next.getCol()] == null) {
				return next;
			}
		}
		// check whether current location is free
		if(field[location.getRow()][location.getCol()] == null) {
			return location;
		} 
		else {
			return null;
		}
	}
	
	/**
	 * Generate an iterator over a shuffled list of locations adjacent
	 * to the given one. The list will not include the location itself.
	 * All locations will lie within the grid.
	 * The topology of the grid is torus shaped. 
	 * This means that it is like a chessboard but when a piece goes beyond the 
	 * bottom row it reappears from the first row and vice versa. 
	 * Similarly when it goes beyond the rightmost column it reappears from the 
	 * leftmost column and vice versa.
	 * @param location The location from which to generate adjacencies.
	 * @return An iterator over locations adjacent to that given.
	 */
	public Iterator adjacentLocations(Location location)
	{
		int row = location.getRow();
		int col = location.getCol();
		LinkedList<Location> locations = new LinkedList<Location>();
		for(int roffset = -1; roffset <= 1; roffset++) 
		{
			int nextRow = row + roffset;
			//if(nextRow >= 0 && nextRow < depth) {
			for(int coffset = -1; coffset <= 1; coffset++) 
			{
				int nextCol = col + coffset;
				// Exclude invalid locations and the original location.
/*
					if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
						locations.add(new Location(nextRow, nextCol));
					}
*/
				if(nextCol<0)
					nextCol = nextCol + width;
				if(nextCol>=width)
					nextCol = nextCol - width;
				if(nextRow<0)
					nextRow = nextRow + depth;
				if(nextRow>=depth)
					nextRow = nextRow - depth;
				locations.add(new Location(nextRow, nextCol));
				}
			//}
		}
		Random rand = RandomGenerator.getRandom();
		Collections.shuffle(locations,rand);
		return locations.iterator();
	}
	
	/**
	 * @return The depth of the field.
	 */
	public int getDepth()
	{
		return depth;
	}
	
	/**
	 * @return The width of the field.
	 */
	public int getWidth()
	{
		return width;
	}
}
