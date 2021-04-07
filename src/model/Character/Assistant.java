package model.Character;

/**
 * 
 * @author Georgios Mpirmpilis<br>
 * 
 * Defines the Assistant special card
 *
 */
public class Assistant  extends Character {
	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructor</b>: Creates the Assistant card by calling the super constructor setting the
	 * played to false
	 * <b>Post-Condition</b>: The Assistant card is set up
	 */
	public Assistant() {
		super(false);
	}
}
