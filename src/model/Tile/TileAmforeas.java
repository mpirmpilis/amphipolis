package model.Tile;

/**
 * 
 * @author Georgios Mpirmpilis<br>
 * 
 * Defines an Amphorea tile
 */
public class TileAmforeas extends Tile {
	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructor</b>: Creates an Amphorea tile with a color and the region
	 * @param color The color of the Amphorea (Blue, Brow, Red, Green, Yellow or Purple)
	 * @param region The region of the tile (for amphorae is 3)
	 */
	public TileAmforeas(TileColor color, int region) {
		super(color, region);
	}
}
