package model.Tile;

/**
* 
* @author Georgios Mpirmpilis<br>
* 
* Defines a Skeleton tile
*/
public class TileSkeletos extends Tile{
	private static final long serialVersionUID = 1L;
	
	private SkeletosType age, body;
	
	/**
	 * <b>Constructor</b>: Creates a Skeleton tile with a no color,age (adult-kid) and body(top-bottom)
	 * @param color The color of the Skeleton (NO_COLOR)
	 * @param region The region of the tile (for skeletons is 4)
	 * @param age The age of the skeleton. Can be adult or kid
	 * @param body The body of the skeleton. Can be top or bottom
	 */
	public TileSkeletos(TileColor color, int region, SkeletosType age, SkeletosType body) {
		super(color, region);
		this.age = age;
		this.body = body;
	}
	
	/**
	 * <b>Observer</b>: Retrieves the age of the selected skeleton tile <br>
	 * <b>Post-Condition</b>: The age of the selected skeleton tile has been returned
	 * @return The age of the selected skeleton tile
	 */
	public SkeletosType getAge() {
		return this.age;
	}
	
	
	/**
	 * <b>Observer</b>: Retrieves the body of the selected skeleton tile <br>
	 * <b>Post-Condition</b>: The body of the selected skeleton tile has been returned
	 * @return The body of the selected skeleton tile
	 */
	public SkeletosType getBody() {
		return this.body;
	}
}
