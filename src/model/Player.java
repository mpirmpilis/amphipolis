package model;

import java.io.Serializable;

import model.Character.*;

/**
 * 
 * @author Georgios Mpirmpilis<br>
 * 
 * Defines a player of the game
 */

public class Player implements Serializable{
	private static final long serialVersionUID = 1L;
	private PlayerColor color;
	private int score;
	
	// The 4 special characters
	private Professor professor;
	private Assistant assistant;
	private Digger digger;
	private Archaeologist archaeologist;
	
	
	private int mosaics_green = 0;
	private int mosaics_red = 0;
	private int mosaics_yellow = 0;
	
	private int statues_karuatides = 0;
	private int statues_sfigges = 0;
	
	private int amforeas_blue = 0;
	private int amforeas_brown = 0;
	private int amforeas_red = 0;
	private int amforeas_green = 0;
	private int amforeas_yellow = 0;
	private int amforeas_purple = 0;
	
	private int skeletos_big_top = 0;
	private int skeletos_big_bot = 0;
	private int skeletos_kid_top = 0;
	private int skeletos_kid_bot = 0;
	
	
	/**
	 * <b>Constructor</b>: Creates a new player initializing all of it's attributes and sets the color to the specified
	 * color
	 * @param color The color which is assigned to the player
	 */
	public Player(PlayerColor color) {
		this.color = color;
		this.score = 0;
		this.professor = new Professor();
		this.assistant = new Assistant();
		this.digger = new Digger();
		this.archaeologist = new Archaeologist();
	}
	
	/**
	 * <b>Transformer</b>: Sets player's score to the specified score <br>
	 * <b>Post-Condition</b>: The player's score has been set
	 * @param score The score which is assigned for the current player
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	
	/**
	 * <b>Observer</b>: Returns the player's current score <br>
	 * <b>Post-Condition</b>: The player's score has been returned
	 * @return Player's current score
	 */
	public int getScore() {
		return this.score;
	}
	
	
	/**
	 * <b>Observer</b>: Returns the player's color <br>
	 * <b>Post-Condition</b>: The player's color has been returned
	 * @return Player's color
	 */
	public PlayerColor getColor() {
		return this.color;
	}
	
	
	/**
	 * <b>Observer</b>: Returns who player is currently playing <br>
	 * <b>Post-Condition</b>: The current player's infos have been updated
	 * @return Player's information about color
	 */
	public String toString() {
		String s = "Player playing: " + this.color;
		return s;
	}
	
	
	/**
	 * <b>Observer</b>: Returns player's professor card <br>
	 * <b>Post-Condition</b>: The current player's professor card has been returned
	 * @return Player's professor object
	 */
	public Professor getProfessor() { return this.professor; }
	
	/**
	 * <b>Observer</b>: Returns player's assistant card <br>
	 * <b>Post-Condition</b>: The current player's assistant card has been returned
	 * @return Player's assistant object
	 */
	public Assistant getAssistant() { return this.assistant; }
	
	/**
	 * <b>Observer</b>: Returns player's digger card <br>
	 * <b>Post-Condition</b>: The current player's digger card has been returned
	 * @return Player's digger object
	 */
	public Digger getDigger() { return this.digger; }
	
	/**
	 * <b>Observer</b>: Returns player's archaeologist card <br>
	 * <b>Post-Condition</b>: The current player's archaeologist card has been returned
	 * @return Player's archaeologist object
	 */
	public Archaeologist getArchaeologist() { return this.archaeologist; }
	
	
	
	
	
	
	
	
	
	/**
	 * <b>Transformer</b>: Increases player's number of green mosaic tiles <br>
	 * <b>Post-Condition</b>: The current player's number of green mosaic tiles have been increased by 1
	 */
	public void increaseMosaicGreen() { this.mosaics_green++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of red mosaic tiles <br>
	 * <b>Post-Condition</b>: The current player's number of red mosaic tiles have been increased by 1
	 */
	public void increaseMosaicRed() { this.mosaics_red++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of yellow mosaic tiles <br>
	 * <b>Post-Condition</b>: The current player's number of yellow mosaic tiles have been increased by 1
	 */
	public void increaseMosaicYellow() { this.mosaics_yellow++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of karuatides statues <br>
	 * <b>Post-Condition</b>: The current player's number of karuatides statues have been increased by 1
	 */
	public void increaseStatueKaruatides() { this.statues_karuatides++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of sphinxes statues <br>
	 * <b>Post-Condition</b>: The current player's number of sphinxes statues have been increased by 1
	 */
	public void increaseStatueSfigges() { this.statues_sfigges++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of blue amphoare <br>
	 * <b>Post-Condition</b>: The current player's number of blue amphoare have been increased by 1
	 */
	public void increaseAmforeasBlue() { this.amforeas_blue++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of brown amphoare <br>
	 * <b>Post-Condition</b>: The current player's number of brown amphoare have been increased by 1
	 */
	public void increaseAmforeasBrown() { this.amforeas_brown++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of red amphoare <br>
	 * <b>Post-Condition</b>: The current player's number of red amphoare have been increased by 1
	 */
	public void increaseAmforeasRed() { this.amforeas_red++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of green amphoare <br>
	 * <b>Post-Condition</b>: The current player's number of green amphoare have been increased by 1
	 */
	public void increaseAmforeasGreen() { this.amforeas_green++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of yellow amphoare <br>
	 * <b>Post-Condition</b>: The current player's number of yellow amphoare have been increased by 1
	 */
	public void increaseAmforeasYellow() { this.amforeas_yellow++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of purple amphoare <br>
	 * <b>Post-Condition</b>: The current player's number of purple amphoare have been increased by 1
	 */
	public void increaseAmforeasPurple() { this.amforeas_purple++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of adult-top skeleton body <br>
	 * <b>Post-Condition</b>: The current player's number of adult-top skeleton body have increased by 1
	 */
	public void increaseSkeletosBigTop() { this.skeletos_big_top++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of adult-bot skeleton body <br>
	 * <b>Post-Condition</b>: The current player's number of adult-bot skeleton body have increased by 1
	 */
	public void increaseSkeletosBigBot() { this.skeletos_big_bot++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of kid-top skeleton body <br>
	 * <b>Post-Condition</b>: The current player's number of kid-top skeleton body have increased by 1
	 */
	public void increaseSkeletosKidTop() { this.skeletos_kid_top++; }
	
	/**
	 * <b>Transformer</b>: Increases player's number of kid-bot skeleton body <br>
	 * <b>Post-Condition</b>: The current player's number of kid-bot skeleton body have increased by 1
	 */
	public void increaseSkeletosKidBot() { this.skeletos_kid_bot++; }
	
	
	
	
	
	
	
	/**
	 * <b>Observer</b>: Retrieves player's current green mosaic tile number <br>
	 * <b>Post-Condition</b>: The number of green mosaic tiles have been retrieved
	 * @return The number of green mosaic tiles for the current player
	 */
	public int getMosaicGreen() { return this.mosaics_green; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current red mosaic tile number <br>
	 * <b>Post-Condition</b>: The number of red mosaic tiles have been retrieved
	 * @return The number of red mosaic tiles for the current player
	 */
	public int getMosaicRed() { return this.mosaics_red; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current yellow mosaic tile number <br>
	 * <b>Post-Condition</b>: The number of yellow mosaic tiles have been retrieved
	 * @return The number of yellow mosaic tiles for the current player
	 */
	public int getMosaicYellow() { return this.mosaics_yellow; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current karuatides statue tile number <br>
	 * <b>Post-Condition</b>: The number of karuatides statue tiles have been retrieved
	 * @return The number of karuatides statue tiles for the current player
	 */
	public int getStatueKaruatides() { return this.statues_karuatides; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current sphinxes statue tile number <br>
	 * <b>Post-Condition</b>: The number of sphinxes statue tiles have been retrieved
	 * @return The number of sphinxes statue tiles for the current player
	 */
	public int getStatueSfigges() { return this.statues_sfigges; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current blue amphorae tile number <br>
	 * <b>Post-Condition</b>: The number of blue amphorae tiles have been retrieved
	 * @return The number of blue amphorae tiles for the current player
	 */
	public int getAmforeasBlue() { return this.amforeas_blue; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current brown amphorae tile number <br>
	 * <b>Post-Condition</b>: The number of brown amphorae tiles have been retrieved
	 * @return The number of brown amphorae tiles for the current player
	 */
	public int getAmforeasBrown() { return this.amforeas_brown; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current red amphorae tile number <br>
	 * <b>Post-Condition</b>: The number of red amphorae tiles have been retrieved
	 * @return The number of red amphorae tiles for the current player
	 */
	public int getAmforeasRed() { return this.amforeas_red; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current green amphorae tile number <br>
	 * <b>Post-Condition</b>: The number of green amphorae tiles have been retrieved
	 * @return The number of green amphorae tiles for the current player
	 */
	public int getAmforeasGreen() { return this.amforeas_green; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current yellow amphorae tile number <br>
	 * <b>Post-Condition</b>: The number of yellow amphorae tiles have been retrieved
	 * @return The number of yellow amphorae tiles for the current player
	 */
	public int getAmforeasYellow() { return this.amforeas_yellow; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current purple amphorae tile number <br>
	 * <b>Post-Condition</b>: The number of purple amphorae tiles have been retrieved
	 * @return The number of purple amphorae tiles for the current player
	 */
	public int getAmforeasPurple() { return this.amforeas_purple; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current adult-top tile number <br>
	 * <b>Post-Condition</b>: The number of adult-top tiles have been retrieved
	 * @return The number of adult-top tiles for the current player
	 */
	public int getSkeletosBigTop() { return this.skeletos_big_top; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current adult-bot tile number <br>
	 * <b>Post-Condition</b>: The number of adult-bot tiles have been retrieved
	 * @return The number of adult-bot tiles for the current player
	 */
	public int getSkeletosBigBot() { return this.skeletos_big_bot; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current kid-top tile number <br>
	 * <b>Post-Condition</b>: The number of kid-top tiles have been retrieved
	 * @return The number of kid-top tiles for the current player
	 */
	public int getSkeletosKidTop() { return this.skeletos_kid_top; }
	
	/**
	 * <b>Observer</b>: Retrieves player's current kid-bot tile number <br>
	 * <b>Post-Condition</b>: The number of kid-bot tiles have been retrieved
	 * @return The number of kid-bot tiles for the current player
	 */
	public int getSkeletosKidBot() { return this.skeletos_kid_bot; }
	
	
	
	
	
	/**
	 * Retrieves current player's stats about their collected items <br>
	 * <b>Post-Condition</b>: The player's stats are set and ready to be displayed
	 * @return The player's stats
	 */
	public String getPlayerStatistics() {
		String s = "                    Player " + this.color + " statistics\n\n";
		s = s + "*** Mosaics ***\n";
		s = s + "Green:" + this.mosaics_green + "   Red:" + this.mosaics_red + "   Yellow:" + this.mosaics_yellow;
		s = s + "\n\n*** Statues ***\n";
		s = s + "Karuatides:" + this.statues_karuatides + "     Sfigges:" + this.statues_sfigges;
		s = s + "\n\n*** Amforeis ***\n";
		s = s + "Blue:"+this.amforeas_blue + "   Brown:"+this.amforeas_brown + "   Red:"+this.amforeas_red
				+ "   Green:"+this.amforeas_green + "   Yellow:"+this.amforeas_yellow + "   Purple:"+this.amforeas_purple;
		s = s + "\n\n*** Skeletoi ***\n";
		s = s + "Adult-Top:" + this.skeletos_big_top + "      Kid-Top:" + this.skeletos_kid_top;
		s = s + "\nAdult-Bot:" + this.skeletos_big_bot + "       Kid-Bot:" + this.skeletos_kid_bot;
		s = s + "\n";
		
		return s;
	}
}
