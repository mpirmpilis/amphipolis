package model.Tile;

/**
* 
* @author Georgios Mpirmpilis<br>
* 
* Defines a Statue tile
*/

public class TileStatue extends Tile{
	private static final long serialVersionUID = 1L;
	
	private StatueType type;
	
	/**
	 * <b>Constructor</b>: Creates a Statue tile with no color, region and the type (Karuatida or Sphinx)
	 * @param color The color of the Statue (NO_COLOR)
	 * @param region The region of the tile (for statues is 2)
	 * @param type The type of the statue. Can be either Karuatida or Sphinx
	 */
	public TileStatue(TileColor color, int region, StatueType type) {
		super(color, region);
		this.type = type;
	}
	
	
	/**
	 * <b>Observer</b>: Retrieves the type of the selected statue tile <br>
	 * <b>Post-Condition</b>: The the type of the selected statue tile has been returned
	 * @return The type of the selected statue. Can be <b>Karuatida</b> or <b>Sphinx</b>
	 */
	public StatueType getStatueType() {
		return this.type;
	}
}
