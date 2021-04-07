package model.Tile;

/**
* 
* @author Georgios Mpirmpilis<br>
* 
* Defines a Mosaic tile
*/
public class TileMosaic extends Tile {
	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructor</b>: Creates a Mosaic tile with a color (Green, Red or Yellow) and the region
	 * @param color The color of the Mosaic (Green, Red or Yellow)
	 * @param region The region of the tile (for mosaic is 1)
	 */
	public TileMosaic(TileColor color, int region) {
		super(color, region);
	}
}
