package model.Tile;

import java.io.Serializable;

/**
 * 
 * @author Georgios Mpirmpilis
 * 
 * Defines a tile
 */
public class Tile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TileColor tileColor;
	private int region;
	private boolean drawnFromBag;
	
	/**
	 * <b>Constructor</b>: Creates a new tile based on the given parameters <br>
	 * <b>Post-Condition</b>: A new tile has been created
	 * @param color The color of the tile
	 * @param region The region it belongs to. 1=Mosaic 2=Statue, 3=Amphorea, 4=Skeleton, 5=Stone
	 */
	public Tile(TileColor color, int region) {
		this.tileColor = color;
		this.region = region;
		this.drawnFromBag = false;
	}
	
	
	/**
	 * <b>Observer</b>: Retrieves the region of the selected tile <br>
	 * <b>Post-Condition</b>: The region of the selected tile has been returned
	 * @return The region of the selected tile
	 */
	public int getRegion() {
		return this.region;
	}
	
	/**
	 * <b>Observer</b>: Retrieves the color of the selected tile <br>
	 * <b>Post-Condition</b>: The color of the selected tile has been returned
	 * @return The color of the selected tile
	 */
	public TileColor getColor() {
		return this.tileColor;
	}
	
	
	/**
	 * <b>Transformer</b>: Sets the flag that indicates if the given tile has been drawn from bag to <b>set</b> status <br>
	 * <b>Post-Condition</b>: The draw from bag flag has been set to <b>set</b> status
	 * @param set Sets the flag to this value
	 */
	public void setDrawnFromBag(boolean set) {
		this.drawnFromBag = set;
	}
	
	/**
	 * <b>Observer</b>: Retrieves the flag which is responsible to check if a tile is drawn from the bag <br>
	 * <b>Post-Condition</b>: The flag has been returned
	 * @return The drawn from the bag flag. <b>True</b> if it's already drawn else <b>false</b>
	 */
	public boolean getDrawnFromBag() {
		return this.drawnFromBag;
	}
}
