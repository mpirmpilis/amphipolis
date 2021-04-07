package model.Character;

/**
 * 
 * @author Georgios Mpirmpilis<br>
 * 
 * Defines the Digger special card
 *
 */
public class Digger  extends Character {
	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructor</b>: Creates the Digger card by calling the super constructor setting the
	 * played to false
	 * <b>Post-Condition</b>: The Digger card is set up
	 */
	public Digger() {
		super(false);
	}
}