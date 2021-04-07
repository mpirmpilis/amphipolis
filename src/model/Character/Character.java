package model.Character;

import java.io.Serializable;

/**
 * 
 * @author Georgios Mpirmpilis<br>
 * 
 * Defines a special card (used for Archaeologist, Assistant, Digger and Professor)
 *
 */
public class Character implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean isAlreadyPlayed;   // checks if the card has been played to avoid playing it again
	
	/**
	 * <b>Constructor</b>: Creates the a special card that can be Archaeologist, Assistant, Digger or Professor <br>
	 * <b>Post-Condition</b>: The special card is set up and played status is set to false
	 */
	public Character(boolean status) {
		this.isAlreadyPlayed = status;
	}
	
	
	/**
	 * <b>Transformer</b>: Sets the played flag of the current card to played value <br>
	 * <b>Post-Condition</b>: The played flag has been set to played status
	 * @param played Sets the status of the special card
	 */
	public void setStatus(boolean played) {
		this.isAlreadyPlayed = played;
	}
	
	/**
	 * <b>Observer</b>: Retrieves the status of the played card <br>
	 * <b>Post-Condition</b>: Status is <b>true</b> if the card is already played else <b>false</b>
	 */
	public boolean GetStatus() {
		return this.isAlreadyPlayed;
	}
}
