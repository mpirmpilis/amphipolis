package model.Character;

/**
 * 
 * @author Georgios Mpirmpilis<br>
 * 
 * Defines the Professor special card
 *
 */
public class Professor  extends Character {
	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructor</b>: Creates the Professor card by calling the super constructor setting the
	 * played to false
	 * <b>Post-Condition</b>: The Professor card is set up
	 */
	public Professor() {
		super(false);
	}
}