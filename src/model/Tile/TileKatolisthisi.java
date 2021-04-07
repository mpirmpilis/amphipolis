package model.Tile;

/**
* 
* @author Georgios Mpirmpilis<br>
* 
* Defines a Katolisthisi tile
*/
public class TileKatolisthisi extends Tile{
	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructor</b>: Creates a Katolisthisi tile with a color (no_color value) and the region
	 * @param color The color of the Katolisthisi (NO_COLOR)
	 * @param region The region of the tile (for katolisthisi is 5)
	 */
	public TileKatolisthisi(TileColor color, int region) {
		super(color, region);
	}
}
