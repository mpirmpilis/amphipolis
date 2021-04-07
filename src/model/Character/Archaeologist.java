package model.Character;

/**
 * 
 * @author Georgios Mpirmpilis<br>
 * 
 * Defines the Arcaheologist special card
 *
 */
public class Archaeologist  extends Character {
	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructor</b>: Creates the Archaeologist card by calling the super constructor setting the
	 * played to false
	 * <b>Post-Condition</b>: The Archaeologist card is set up
	 */
	public Archaeologist() {
		super(false);
	}
}