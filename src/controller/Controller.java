package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


import javax.swing.JButton;
import javax.swing.JOptionPane;


import model.*;
import model.Tile.SkeletosType;
import model.Tile.StatueType;
import model.Tile.Tile;
import model.Tile.TileAmforeas;
import model.Tile.TileColor;
import model.Tile.TileKatolisthisi;
import model.Tile.TileMosaic;
import model.Tile.TileSkeletos;
import model.Tile.TileStatue;
import view.GraphicsUI;

/**
 * @author Georgios Mpirmpilis
 * <p> This class illustrates the controller of the game </p>
 * <br><br><b>Controller</b> : Controls every game functionality and communicates 
 * with model and view in order to update the data and GUI.
 */

public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;
	private int game_mode;
	private GraphicsUI view;
	public Player currentPlayer;
	private int playerIndex = 0;
	
	public String picked_tiles = "";
	private Player[] players;
	private int[] tile_history = new int[135];  // used to track which tiles are on the board in case of save-load
	private Tile[] bag = new Tile[135];
	private int numOfStones = 0;
	private int lastTileRegion = 0;   // deixnei apo pou mazeutike to teleutaio tile wste na apotrepsei to mazema apo allh perioxh
	private int apothema = 2;
	private int[] proff_lastTileRegions = {-1,-1,-1};   // used to check the last regions for professor
	
	
	private boolean specialCardActivated = false;   // prevents a player from playing 2 special cards at the same round
	private int whichSpecialCard = 0;     // shows which special card was clicked. 1-aracheologist__2-assistant__3-digger__4-professor
	public int kinisi = 1; //1-travigma apo sakoula,  2-mazema apo katw, 3-pithani xrhsh xarakthra, 4-end turn
	
	
	
	
	
	/**
	 * <b>Constructor</b>: Creates a new Controller when 4 players mode is selected 
	 * and sets up the game to be ready to be played.
	 * <br><b>Post-condition</b>: The controller is ready, the bag, players and GUI are created and 
	 * the first player is set to start the game.
	 */
	public Controller() throws IOException {
		players = new Player[4];
		createPlayers();
		createBag();
		AddListenersAndThings();
		
		this.game_mode = 4;
		
		// these two below are used to check if the load game was working correctly. Uncomment to check on your own
		// System.out.println("Max players:" + players.length); 
		// System.out.println(players[0].getColor() + " - " + players[1].getColor() + " - " + players[2].getColor() + " - " + players[3].getColor());
	}

	/**
	 * <b>Constructor (parameterized)</b>: Creates a new Controller when thief mode is selected 
	 * and sets up the game to be ready to be played.
	 * <br><b>Post-condition</b>: The controller is ready, the bag, players and GUI are created and 
	 * the first player is set to start the game.
	 */
	public Controller(int x) throws IOException {
		players = new Player[2];    // player and thief
		players[0] = new Player(PlayerColor.BLUE);
		players[1] = new Player(PlayerColor.THIEF);
		createBag();
		AddListenersAndThings();
		this.game_mode = 1;
		AddEightStonesOnBoard();
	}

	/**
	 * <b>Transformer</b>: Adds the first 8 stones on the board <br>
	 * <b>Post-Condition</b>: The first 8 stones have been added
	 */
	private void AddEightStonesOnBoard() {
		for (int i=111; i<=118; i++) {
			view.getFrame().requestFocus();
			view.getFrame().add(view.panelakia[i]);
			view.getFrame().revalidate();
			view.getFrame().pack();
			tile_history[i] = 1;
			bag[i].setDrawnFromBag(true);
		}
		numOfStones = 8;
	}


	/**
	 * <b>Transformer</b>: Adds Listeners to all the buttons and sets the current player to first player <br>
	 * <b>Post-Condition</b>: All buttons have their corrected listeners and the current player is set to first one
	 * @throws IOException
	 */
	private void AddListenersAndThings() throws IOException {
		currentPlayer = players[0];
		
		this.view = new GraphicsUI(this);
		this.view.addDrawListener(new DrawListener());
		this.view.addEndRoundListener(new EndRoundListener());
		this.view.addToublakiaListener(new ToublakiaListener());
		this.view.addProfessorListener(new ProfessorListener());
		this.view.addDiggerListener(new DiggerListener());
		this.view.addAssistantListener(new AssistantListener());
		this.view.addArchaeologistListener(new ArchaeologistListener());
		this.view.addShowPlayerCollectionListener(new PlayerCollectionListener());
		this.view.addSaveGameListener(new SaveGameListener());
		this.view.addLoadGameListener(new LoadGameListener());
	}


	/**
	 * <b>Transformer</b>: Fills the tile bag with all 135 tiles.
	 * <br><b>Post-Condition</b>: The tile bag is filled with the necessary 135 tiles.
	 */
	 private void createBag() {
		for (int i=0; i<135; i++) {
			if (i>=0 && i<9)
				bag[i] = new TileMosaic(TileColor.GREEN, 1);
			else if (i>=9 && i<18)
				bag[i] = new TileMosaic(TileColor.RED, 1);
			else if (i>=18 && i<27)
				bag[i] = new TileMosaic(TileColor.YELLOW, 1);
			else if (i>=27 && i<39)
				bag[i] = new TileStatue(TileColor.NO_COLOR, 2, StatueType.KARUATIDA);
			else if (i>=39 && i<51)
				bag[i] = new TileStatue(TileColor.NO_COLOR, 2, StatueType.SFIGGA);
			else if (i>=51 && i<61)
				bag[i] = new TileSkeletos(TileColor.NO_COLOR, 4,SkeletosType.ADULT, SkeletosType.TOP);
			else if (i>=61 && i<71)
				bag[i] = new TileSkeletos(TileColor.NO_COLOR, 4, SkeletosType.ADULT, SkeletosType.BOTTOM);
			else if (i>=71 && i<76)
				bag[i] = new TileSkeletos(TileColor.NO_COLOR, 4, SkeletosType.KID, SkeletosType.TOP);
			else if (i>=76 && i<81)
				bag[i] = new TileSkeletos(TileColor.NO_COLOR, 4,SkeletosType.KID, SkeletosType.BOTTOM);
			else if (i>=81 && i<86)
				bag[i] = new TileAmforeas(TileColor.BLUE, 3);
			else if (i>=86 && i<91)
				bag[i] = new TileAmforeas(TileColor.BROWN, 3);
			else if (i>=91 && i<96)
				bag[i] = new TileAmforeas(TileColor.RED, 3);
			else if (i>=96 && i<101)
				bag[i] = new TileAmforeas(TileColor.GREEN, 3);
			else if (i>=101 && i<106)
				bag[i] = new TileAmforeas(TileColor.YELLOW, 3);
			else if (i>=106 && i<111)
				bag[i] = new TileAmforeas(TileColor.PURPLE, 3);
			else
				bag[i] = new TileKatolisthisi(TileColor.NO_COLOR, 5);
		}
	}
			
	
	
	/**
	 * <b>Transformer</b>: Sets and randomizes the players.
	 * <br><b>Post-Condition</b>: The players are set and randomized.
	 */
	private void createPlayers() {
		players[0] = new Player(PlayerColor.YELLOW);
		players[1] = new Player(PlayerColor.RED);
		players[2] = new Player(PlayerColor.BLUE);
		players[3] = new Player(PlayerColor.BLACK);
		Collections.shuffle(Arrays.asList(players));    // treat array as list and use shuffle to randomize them
	}
	
	
	
	/**
	 * @author Georgios Mpirmpilis (csd3296)
	 * 
	 * <br><p>Implements the Listener assigned to show player's tiles button.
	 * It shows a MessageDialog displaying player's tiles on every category.</p>
	 * <br><b>Post-Condition</b>: Displays a window showing current player's tiles at each category.
	 */
	class PlayerCollectionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,currentPlayer.getPlayerStatistics(),"Player " + currentPlayer.getColor() 
			+ " collected items",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	
	/**
	 * @author Georgios Mpirmpilis (csd3296)
	 * 
	 * <br><p>Implements the Listener assigned to draw button to draw four (4) random tiles from the bag.
	 * <br><br><b>Pre-Condition</b>: Player must be at their first move (kinisi=1).
	 * <br><b>Post-Condition</b>: Four (4) tiles randomly selected are displayed on the board and kinisi is set to 2 (kinisi=2).
	 */
	class DrawListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int draws = 0;
			int index;
			// can only draw when kinisi is 1
			if (kinisi == 1) {
				Random rand = new Random();
				do {
					index = rand.nextInt(135);
					if (bag[index].getDrawnFromBag() == true)
						continue;
					else {
						if (bag[index] instanceof TileKatolisthisi) {
							numOfStones++;
							view.numStones.setText(String.valueOf(numOfStones));
							if (game_mode == 1) {
								kinisi = 4;
								JOptionPane.showMessageDialog(null,"Drawn a stone. Thief steals it all", "Stone drawn!",JOptionPane.INFORMATION_MESSAGE);
								draws = ThiefStealsItAll();
							}
						}
						bag[index].setDrawnFromBag(true);
						picked_tiles = CheckPickedTile(picked_tiles, index);
						tile_history[index] = 1;
						view.addToFrame(view.panelakia[index]);
						
						if (numOfStones == 16) {
							JOptionPane.showMessageDialog(null,"Gate is filled with stones. Declaring the winner", "GATE COVERED WITH STONES",JOptionPane.INFORMATION_MESSAGE);
							declareWinnerAndEndGame();
							return;    //System.exit(0);
						}
						draws++;
					}
				} while (draws < 4);
				picked_tiles = picked_tiles.substring(0,picked_tiles.length()-5);
				view.drawn_tiles.setText(picked_tiles);
				kinisi = 2;
			}
		}

		
		/**
		 * <b>Transformer</b>: Sets a string to keep which tiles were drawn from bag <br>
		 * <b>Pre-Condition</b>: A tile must be drawn from the bag <br>
		 * <b>Post-Condition</b>: Adds the drawn tile to the string
		 * @param picked_tiles The drawn tile from which the string is correctly formatted
		 * @param i Which tile was drawn from the bag
		 * @return A string with the currently drawn tiles from the bag
		 */
		private String CheckPickedTile(String picked_tiles, int i) {
			if (bag[i] instanceof TileMosaic) {      // picked a mosaic tile
				if (bag[i].getColor() == TileColor.GREEN) {
					picked_tiles = picked_tiles + "Mosaic(Green) ";
				} else if (bag[i].getColor() == TileColor.RED) {
					picked_tiles = picked_tiles + "Mosaic(Red) ";
				} else {
					picked_tiles = picked_tiles + "Mosaic(Yellow) ";
				}
			} else if (bag[i] instanceof TileStatue) {
				if (((TileStatue)bag[i]).getStatueType() == StatueType.KARUATIDA) {
					picked_tiles = picked_tiles + "Statue(Karuatida) ";
				} else {
					picked_tiles = picked_tiles + "Statue(Sphinx) ";
				}
			} else if (bag[i] instanceof TileAmforeas) {
				if (bag[i].getColor() == TileColor.BLUE) {
					picked_tiles = picked_tiles + "Amphora(Blue) ";
				} else if (bag[i].getColor() == TileColor.BROWN) {
					picked_tiles = picked_tiles + "Amphora(Brown) ";
				} else if (bag[i].getColor() == TileColor.RED) {
					picked_tiles = picked_tiles + "Amphora(Red) ";
				} else if (bag[i].getColor() == TileColor.GREEN) {
					picked_tiles = picked_tiles + "Amphora(Green) ";
				} else if (bag[i].getColor() == TileColor.YELLOW) {
					picked_tiles = picked_tiles + "Amphora(Yellow) ";
				} else {
					picked_tiles = picked_tiles + "Amphora(Purple) ";
				}
			} else if (bag[i] instanceof TileSkeletos) {
				if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.ADULT && ((TileSkeletos)bag[i]).getBody() == SkeletosType.TOP) {
					picked_tiles = picked_tiles + "Skeleton(Adult-Top) ";
				} else if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.ADULT && ((TileSkeletos)bag[i]).getBody() == SkeletosType.BOTTOM) {
					picked_tiles = picked_tiles + "Skeleton(Adult-Bottom) ";
				} else if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.KID && ((TileSkeletos)bag[i]).getBody() == SkeletosType.TOP) {
					picked_tiles = picked_tiles + "Skeleton(Kid-Top) ";
				} else {
					picked_tiles = picked_tiles + "Skeleton(Kid-Bottom) ";
				}
			} else {
				picked_tiles = picked_tiles + "Stone ";
			}
			picked_tiles = picked_tiles + "---- ";
			return picked_tiles;
		}

		/**
		 * Thief steals every available tile except the rocks and the player starts the round again
		 * <b>Post-Condition</b>: Thief has stolen every available tile except the stones and the points for each tile have been
		 * added to thief's score.
		 * @return 10 as a red flag for drawn tiles. Cannot draw 10 tiles during a turn so this will ensures it will exit the loop
		 */
		private int ThiefStealsItAll() {
			for (int i=0; i<111; i++) {   // cannot steal the stones (stones are the last tiles in the bag)
				if (tile_history[i] == 1) {
					if (bag[i] instanceof TileMosaic) {      // picked a mosaic tile
						if (bag[i].getColor() == TileColor.GREEN) {
							players[1].increaseMosaicGreen();
						} else if (bag[i].getColor() == TileColor.RED) {
							players[1].increaseMosaicRed();
						} else {
							players[1].increaseMosaicYellow();
						}
					} else if (bag[i] instanceof TileStatue) {
						if (((TileStatue)bag[i]).getStatueType() == StatueType.KARUATIDA) {
							players[1].increaseStatueKaruatides();
						} else {
							players[1].increaseStatueSfigges();
						}
					} else if (bag[i] instanceof TileAmforeas) {
						if (bag[i].getColor() == TileColor.BLUE) {
							players[1].increaseAmforeasBlue();
						} else if (bag[i].getColor() == TileColor.BROWN) {
							players[1].increaseAmforeasBrown();
						} else if (bag[i].getColor() == TileColor.RED) {
							players[1].increaseAmforeasRed();
						} else if (bag[i].getColor() == TileColor.GREEN) {
							players[1].increaseAmforeasGreen();
						} else if (bag[i].getColor() == TileColor.YELLOW) {
							players[1].increaseAmforeasYellow();
						} else {
							players[1].increaseAmforeasPurple();
						}
					} else if (bag[i] instanceof TileSkeletos) {
						if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.ADULT && ((TileSkeletos)bag[i]).getBody() == SkeletosType.TOP) {
							players[1].increaseSkeletosBigTop();
						} else if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.ADULT && ((TileSkeletos)bag[i]).getBody() == SkeletosType.BOTTOM) {
							players[1].increaseSkeletosBigBot();
						} else if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.KID && ((TileSkeletos)bag[i]).getBody() == SkeletosType.TOP) {
							players[1].increaseSkeletosKidTop();
						} else {
							players[1].increaseSkeletosKidBot();
						}
					}
					view.getFrame().requestFocus();      // used for not "shading" other panels that need mouse hover to be revealed
					view.panelakia[i].setVisible(false);
					view.getFrame().revalidate();
					view.getFrame().pack();
					
					/* ***   VISUAL BUG   ***
					 * When thief steals everything, rocks are "shaded". To fix this just hover the mouse over
					 * the rocks (move the cursor around the region 5 to show the rocks)
					 */
					
					tile_history[i] = 0;
				}
			}
			view.getFrame().requestFocus();
			view.getFrame().revalidate();
			view.getFrame().pack();
			return 10;     // set that drawn tiles are 10 so it exits while loop after returning from here
		}
	}
	
	
	/**
	 * @author Georgios Mpirmpilis (csd3296)
	 * 
	 * <br><p>Implements the Listener assigned to EndRound button to end current player's turn.
	 * <br><br><b>Pre-Condition</b>: Kinisi cannot be 1. So valids are <b>kinisi=2</b> or <b>kinisi=3</b>.
	 * <br><b>Post-Condition</b>: Current player's turn ends, next player comes up, loads their data and everything is re-initialized.	 * 
	 */
	class EndRoundListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (kinisi == 2 || kinisi == 3 || kinisi == 4) {
				
				if (game_mode == 1) {
					playerIndex = 0;
				} else {
					if (playerIndex==3)
						playerIndex=0;
					else
						playerIndex++;
				}
				
			
				
				currentPlayer = players[playerIndex];
				view.info_player.setText(currentPlayer.toString());

				picked_tiles = "";  // initialize for the next turn
				
				if (currentPlayer.getArchaeologist().GetStatus() == false)
					view.archaeologistButton.setEnabled(true);
				else
					view.archaeologistButton.setEnabled(false);
				
				
				if (currentPlayer.getAssistant().GetStatus() == false)
					view.assistantButton.setEnabled(true);
				else
					view.assistantButton.setEnabled(false);

				
				if (currentPlayer.getDigger().GetStatus() == false)
					view.diggerButton.setEnabled(true);
				else
					view.diggerButton.setEnabled(false);
				
				
				if (currentPlayer.getProfessor().GetStatus() == false)
					view.professorButton.setEnabled(true);
				else
					view.professorButton.setEnabled(false);
				
				
				// initialize some basic stuff for the next player
				kinisi = 1;   // next player's first move to draw tiles
				apothema = 2;  // next player can draw 2 tiles from the board
				lastTileRegion = 0;
				specialCardActivated = false;
				whichSpecialCard = 0;
				proff_lastTileRegions[0] = proff_lastTileRegions[1] = proff_lastTileRegions[2] = -1;
			}
		}
	}
	

	
	
	
	

	/**
	 * @author Georgios Mpirmpilis (csd3296)
	 * 
	 * <br><p>Implements the ToublakiaListener assigned to little tile buttons on the board when clicked.
	 * <br><br><b>Pre-Condition</b>: Kinisi must be 3 or 4 (cannot pick a tile when at kinisi=1).
	 * <br><b>Post-Condition</b>: Picks up the tile if the region is not violated (even when a special card is activated or not)
	 * and assigns the appropriate points to the current player.
	 */
	class ToublakiaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (kinisi == 2 || kinisi == 3) {
				if (apothema > 0) {
					JButton clicked = (JButton)e.getSource();
					
					for (int i=0; i<135; i++) {    // this is where we search for the button
						if (clicked == view.toublakia[i]) {
							if (lastTileRegion == bag[i].getRegion() || lastTileRegion == 0 || whichSpecialCard == 1 || whichSpecialCard == 4) {
								
								
								if (whichSpecialCard == 1) {      // archaeologist activated
									if (proff_lastTileRegions[0] == bag[i].getRegion()) {
										return;
									}
								} else if (whichSpecialCard == 4) {
									for (int u=0; u<3; u++) {
										if (proff_lastTileRegions[u] == bag[i].getRegion())
											return;
										else if (proff_lastTileRegions[u] == -1) {
											proff_lastTileRegions[u] = bag[i].getRegion();
											break;
										}
									}
								}
								
								view.getFrame().requestFocus();      // used for not "shading" other panels that need mouse hover to be revealed
								view.panelakia[i].setVisible(false);
								view.getFrame().revalidate();
								view.getFrame().pack();
								tile_history[i] = 0;
																
								lastTileRegion = bag[i].getRegion();
								apothema--;
								

								if (bag[i] instanceof TileMosaic) {      // picked a mosaic tile
									if (bag[i].getColor() == TileColor.GREEN) {
										currentPlayer.increaseMosaicGreen();
									} else if (bag[i].getColor() == TileColor.RED) {
										currentPlayer.increaseMosaicRed();
									} else {
										currentPlayer.increaseMosaicYellow();
									}
								} else if (bag[i] instanceof TileStatue) {
									if (((TileStatue)bag[i]).getStatueType() == StatueType.KARUATIDA) {
										currentPlayer.increaseStatueKaruatides();
									} else {
										currentPlayer.increaseStatueSfigges();
									}
								} else if (bag[i] instanceof TileAmforeas) {
									if (bag[i].getColor() == TileColor.BLUE) {
										currentPlayer.increaseAmforeasBlue();
									} else if (bag[i].getColor() == TileColor.BROWN) {
										currentPlayer.increaseAmforeasBrown();
									} else if (bag[i].getColor() == TileColor.RED) {
										currentPlayer.increaseAmforeasRed();
									} else if (bag[i].getColor() == TileColor.GREEN) {
										currentPlayer.increaseAmforeasGreen();
									} else if (bag[i].getColor() == TileColor.YELLOW) {
										currentPlayer.increaseAmforeasYellow();
									} else {
										currentPlayer.increaseAmforeasPurple();
									}
								} else if (bag[i] instanceof TileSkeletos) {
									if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.ADULT && ((TileSkeletos)bag[i]).getBody() == SkeletosType.TOP) {
										currentPlayer.increaseSkeletosBigTop();
									} else if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.ADULT && ((TileSkeletos)bag[i]).getBody() == SkeletosType.BOTTOM) {
										currentPlayer.increaseSkeletosBigBot();
									} else if ( ((TileSkeletos)bag[i]).getAge() == SkeletosType.KID && ((TileSkeletos)bag[i]).getBody() == SkeletosType.TOP) {
										currentPlayer.increaseSkeletosKidTop();
									} else {
										currentPlayer.increaseSkeletosKidBot();
									}
								}
								kinisi = 3;				
								break;
							}
						}
					}
				}
			}
		}
	}
	
	
	
	
	
	/**
	 * @author Georgios Mpirmpilis (csd3296)
	 * 
	 * <br><p>Implements the Listener assigned to archaeologist button when clicked.
	 * <br><br><b>Pre-Condition</b>: Kinisi must be 3 and no other special card was played 
	 * during the current round (Assistant, Digger and Professor).
	 * <br><b>Post-Condition</b>: Declares the <b>Archaeologist</b> card as the round card and lets the player to
	 * pick at most 2 tiles from any other region excluding the last region.
	 */
	class ArchaeologistListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentPlayer.getArchaeologist().GetStatus() == false) {   // do the stuff if the button is still available
				if (kinisi == 3) {
					if (specialCardActivated == false) {
						specialCardActivated = true;
						whichSpecialCard = 1;
						
						currentPlayer.getArchaeologist().setStatus(true);
						view.archaeologistButton.setEnabled(false);
						
						proff_lastTileRegions[0] = lastTileRegion;
						apothema = 2;
					}
				}
			}
		}
	}
	
	
	/**
	 * @author Georgios Mpirmpilis (csd3296)
	 * 
	 * <br><p>Implements the Listener assigned to Assistant button when clicked.
	 * <br><br><b>Pre-Condition</b>: Kinisi must be 3 and no other special card was played 
	 * during the current round (Archaeologist, Digger and Professor).
	 * <br><b>Post-Condition</b>: Declares the <b>Assistant</b> card as the round card and lets player pick at most 1 tile
	 * from any region they desire.
	 */
	class AssistantListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentPlayer.getAssistant().GetStatus() == false) {   // do the stuff if the button is still available
				if (kinisi == 3) {
					if (specialCardActivated == false) {
						specialCardActivated = true;
						whichSpecialCard = 2;

						currentPlayer.getAssistant().setStatus(true);
						view.assistantButton.setEnabled(false);
						
						lastTileRegion = 0;
						apothema = 1;
					}
				}
			}
		}
	}
	
	
	
	
	/**
	 * @author Georgios Mpirmpilis (csd3296)
	 * 
	 * <br><p>Implements the Listener assigned to Digger button when clicked.
	 * <br><br><b>Pre-Condition</b>: Kinisi must be 3 and no other special card was played 
	 * during the current round (Archaeologist, Assistant and Professor).
	 * <br><b>Post-Condition</b>: Declares the <b>Digger</b> card as the round card and lets player pick at most 2 tile
	 * from the last region.
	 */
	class DiggerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentPlayer.getDigger().GetStatus() == false) {   // do the stuff if the button is still available
				if (kinisi == 3) {
					if (specialCardActivated == false) {
						specialCardActivated = true;
						whichSpecialCard = 3;
						
						
						currentPlayer.getDigger().setStatus(true);
						view.diggerButton.setEnabled(false);
						apothema = 2;
					}
				}
			}
		}
	}
	
	


	/**
	 * @author Georgios Mpirmpilis (csd3296)
	 * 
	 * <br><p>Implements the Listener assigned to Assistant button when clicked.
	 * <br><br><b>Pre-Condition</b>: Kinisi must be 3 and no other special card was played 
	 * during the current round (Archaeologist, Assistant and Digger).
	 * <br><b>Post-Condition</b>: Declares the <b>Professor</b> card as the round card and lets player pick at most 1 tile
	 * from the other 3 regions excluding the last region.
	 */
	class ProfessorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentPlayer.getProfessor().GetStatus() == false) {   // do the stuff if the button is still available
				if (kinisi == 3) {	
					if (specialCardActivated == false) {
						specialCardActivated = true;
						currentPlayer.getProfessor().setStatus(true);
						view.professorButton.setEnabled(false);
						
						whichSpecialCard = 4;
						apothema = 3;
						proff_lastTileRegions[0] = lastTileRegion;
					}
				}
			}
		}
	}
	
	
	
	/**
	 * 
	 * @author Georgios Mpirmpilis (csd3296) <br>
	 * 
	 * <p>Implements the listener for the savegame button.
	 * <br><br><b>Post-Condition</b>: If user clicks '<b>Yes</b>' the game is saved to a file called savegame.txt
	 * at the project's directory (where src folder is). If '<b>No</b>' is clicked, saving is not done and the game continues normally
	 */
	class SaveGameListener implements ActionListener, Serializable {
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			int choice = JOptionPane.showConfirmDialog (null, "Would you like to save your game?","Warning: Saving game", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				try {
					FileOutputStream fileStream = new FileOutputStream("savegame.txt");
					ObjectOutputStream out = new ObjectOutputStream(fileStream);
					
					out.writeObject(game_mode);
					out.writeObject(currentPlayer);
					out.writeObject(playerIndex);
					out.writeObject(players);
					out.writeObject(tile_history);
					out.writeObject(bag);
					out.writeObject(numOfStones);
					out.writeObject(lastTileRegion);
					out.writeObject(apothema);
					out.writeObject(proff_lastTileRegions);
					out.writeObject(specialCardActivated);
					out.writeObject(whichSpecialCard);
					out.writeObject(kinisi);
					out.writeObject(picked_tiles);
					
					
					out.close();
					fileStream.close();
					JOptionPane.showMessageDialog(null, "Game has been successfully saved.\nGame will now exit.");
					System.exit(0);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "There was an error while saving. Game is not saved", "Error saving game", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Game was not saved");
			}
		}
	}

	
	/**
	 * 
	 * @author Georgios Mpirmpilis (csd3296) <br>
	 * 
	 * <p>Implements the listener for the loadgame button.
	 * <br><br><b>Pre-Condition</b>: A previous serialized <b>savegame.txt</b> file must occur in project's folder
	 * <br><b>Post-Condition</b>:  If user clicks '<b>Yes</b>' the game is loaded from savegame.txt and game continues from
	 * that point. If '<b>No</b>' is clicked, loading is not done and the game continues normally
	 */
	class LoadGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int choice = JOptionPane.showConfirmDialog (null, "Would you like to load your last game?","Warning: Saving game", JOptionPane.YES_NO_OPTION);
			
			if (choice == JOptionPane.YES_OPTION) {
				try {
					FileInputStream fileStream = new FileInputStream("savegame.txt");
					ObjectInputStream out = new ObjectInputStream(fileStream);
					
					game_mode = (int) out.readObject();
					currentPlayer = (Player) out.readObject();
					playerIndex = (int) out.readObject();
					players = (Player[]) out.readObject();
					tile_history = (int[]) out.readObject();
					bag = (Tile[]) out.readObject();
					numOfStones = (int) out.readObject();
					lastTileRegion = (int) out.readObject();
					apothema = (int) out.readObject();
					proff_lastTileRegions = (int[]) out.readObject();
					specialCardActivated = (boolean) out.readObject();
					whichSpecialCard = (int) out.readObject();
					kinisi = (int) out.readObject();
					picked_tiles = (String) out.readObject();
					
					view.refresh_the_gui();

					out.close();
					fileStream.close();
					JOptionPane.showMessageDialog(null, "Game loaded successfully!");
				} catch (IOException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "!!! Previous savegame file not found !!!", "Error loading savegame", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Loading previous game aborted!");
			}
		}
	}
	
	
	
	/**
	 * <b>Transformer</b>: Declares the winner.
	 * <br><b>Pre-Condition</b>: The gate must be covered with 16 stones.
	 * <br><b>Post-Condition</b>: Displays every player's total score and declares the winner with the most points.
	 */
	public void declareWinnerAndEndGame() {
		int tetrades = 0, upoloipa = 0, i;
		int max = 0, min = 0, max_index = 0, min_index = 0;
		view.endRoundButton.setEnabled(false);
		
	
		// 1. Calculating mosaic tiles
		for (i=0; i<players.length; i++) {
			tetrades = ((players[i].getMosaicGreen()/4) + (players[i].getMosaicRed()/4) + (players[i].getMosaicYellow()/4)) * 4;
			upoloipa = ((players[i].getMosaicGreen()%4) + (players[i].getMosaicRed()%4) + (players[i].getMosaicYellow()%4)) / 4;
			upoloipa = upoloipa * 2;
			players[i].setScore(tetrades+upoloipa);
			upoloipa = 0;
			tetrades = 0;
		}
		
		
		// 2.1 Calculating Karuatides
		// if all players have the same karuatides (sphinx), they don't get any points
		if ((players[0].getStatueKaruatides() != players[1].getStatueKaruatides()) ||
				players[0].getStatueKaruatides() != players[2].getStatueKaruatides() ||
				players[0].getStatueKaruatides() != players[3].getStatueKaruatides() ||
				players[1].getStatueKaruatides() != players[2].getStatueKaruatides() ||
				players[1].getStatueKaruatides() != players[3].getStatueKaruatides() ||
				players[2].getStatueKaruatides() != players[3].getStatueKaruatides()) {
				
			max = min = players[0].getStatueKaruatides();
			max_index = min_index = 0;
			
			for (i=1; i<players.length; i++) {
				if (players[i].getStatueKaruatides() > max) {
					max = players[i].getStatueKaruatides();
					max_index = i;
				}
			
				if (players[i].getStatueKaruatides() < min) {
					min = players[i].getStatueKaruatides();
					min_index = i;
				}
			}
		
			for (i=0; i<players.length; i++) {
				if (i == max_index)
					players[max_index].setScore(players[max_index].getScore() + 6);
			
				if (i != max_index && i != min_index)
					players[i].setScore(players[i].getScore() + 3);
			}
		}
		
		
		
		// 2.2. Calculating sfigges
		if ((players[0].getStatueSfigges() != players[1].getStatueSfigges()) ||
				players[0].getStatueSfigges() != players[2].getStatueSfigges() ||
				players[0].getStatueSfigges() != players[3].getStatueSfigges() ||
				players[1].getStatueSfigges() != players[2].getStatueSfigges() ||
				players[1].getStatueSfigges() != players[3].getStatueSfigges() ||
				players[2].getStatueSfigges() != players[3].getStatueSfigges()) {
				
			max = min = players[0].getStatueSfigges();
			max_index = min_index = 0;
			
			for (i=1; i<players.length; i++) {
				if (players[i].getStatueSfigges() > max) {
					max = players[i].getStatueSfigges();
					max_index = i;
				}
			
				if (players[i].getStatueSfigges() < min) {
					min = players[i].getStatueSfigges();
					min_index = i;
				}
			}
		
			for (i=0; i<players.length; i++) {
				if (i == max_index)
					players[max_index].setScore(players[max_index].getScore() + 6);
			
				if (i != max_index && i != min_index)
					players[i].setScore(players[i].getScore() + 3);
			}
		}
		
	
		// 3. Calculating amforeis
		int[] max_array = new int[6];
		for (i=0; i<players.length; i++) {
			max_array[0] = players[i].getAmforeasBlue();
			max_array[1] = players[i].getAmforeasBrown();
			max_array[2] = players[i].getAmforeasRed();
			max_array[3] = players[i].getAmforeasGreen();
			max_array[4] = players[i].getAmforeasYellow();
			max_array[5] = players[i].getAmforeasPurple();
			
			
		/******** sorting in descending order -- START ********/
			for (int x=0; x<max_array.length; x++) { max_array[x] = max_array[x] * (-1); }
			Arrays.sort(max_array);
			for (int x=0; x<max_array.length; x++) { max_array[x] = max_array[x] * (-1); }   // reverse it back
		/******** sorting in descending order -- END ********/
			int temp_counter = 0;
			while (max_array[2] > 0) {     // calcs occur only if the 3rd max color is non-zero. If it is, calcs stop!
				for (int j = 0; j < 6; j++) {
					if (max_array[j] > 0) {
						temp_counter++;
						max_array[j]--;
					}
				}
				
				if (temp_counter == 6)
					players[i].setScore(players[i].getScore() + 6);
				else if (temp_counter == 5)
					players[i].setScore(players[i].getScore() + 4);
				else if (temp_counter == 4)
					players[i].setScore(players[i].getScore() + 2);
				else if (temp_counter == 3)
					players[i].setScore(players[i].getScore() + 1);

				temp_counter = 0;
			}
		}
		
		
		
		// 4. Calculating skeletons
		for (i=0; i<players.length; i++) {
			int full_adult = 0, full_kid = 0, zeugari_adult = 0, family = 0;
			if (players[i].getSkeletosBigTop() >= players[i].getSkeletosBigBot())
				full_adult = players[i].getSkeletosBigBot();
			else if (players[i].getSkeletosBigTop() <= players[i].getSkeletosBigBot())
				full_adult = players[i].getSkeletosBigTop();
			
			
			if (players[i].getSkeletosKidTop() >= players[i].getSkeletosKidBot())
				full_kid = players[i].getSkeletosKidBot();
			else if (players[i].getSkeletosKidTop() <= players[i].getSkeletosKidBot())
				full_kid = players[i].getSkeletosKidTop();
			
			
			
			zeugari_adult = full_adult / 2;
			for (int j=0; j <zeugari_adult; j++) {
				if (full_kid > 0) {
					family++;
					full_kid--;
				}
			}
			
			players[i].setScore(players[i].getScore() + (family*6));    // add the family points
			
			if (family == 0)
				players[i].setScore(players[i].getScore() + (full_kid + full_adult));
			else
				players[i].setScore(players[i].getScore() + (full_kid + (full_adult%2)));
			
			//System.out.println(players[i].getColor() + ":Full adults:" + full_adult + "   --- full kids:" + full_kid + "     -----  oikogeneia:" + family + "   ----TOTAL:" + players[i].getScore());
			
			
			full_adult = full_kid = zeugari_adult = family = 0;	
		}
		
		// get the winner
		max = players[0].getScore();
		max_index = 0;
		for (i = 1; i < players.length; i++) {
			if (players[i].getScore() > max) {
				max = players[i].getScore();
				max_index = i;
			}
		}
		
		String s = "";
		for (i=0; i<players.length; i++) {
			s = s + "Player " + players[i].getColor() + ": " + players[i].getScore() + " points\n";
		}
		s = s + "\nWinner is " +players[max_index].getColor() + " with " + max + " points!";
		/*String s = "Player " + players[0].getColor() + ": " + players[0].getScore() + " points\n" +
				   "Player " + players[1].getColor() + ": " + players[1].getScore() + " points\n" +
				   "Player " + players[2].getColor() + ": " + players[2].getScore() + " points\n" +
				   "Player " + players[3].getColor() + ": " + players[3].getScore() + " points\n\n" +
				   "Winner is "+players[max_index].getColor() + " with " + max + " points!"; */
		JOptionPane.showMessageDialog(null,s,"Players Stats",JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Returns an array which holds which tiles are currently on the board <br>
	 * <b>Post-Condition</b>: The tile history has been returned
	 * @return An array holding the tiles that are currently on the board
	 */
	public int[] getBagHistory() {
		return this.tile_history;
	}
	
	/**
	 * <b>Observer</b>: Returns the current number of stones presented at the game <br>
	 * <b>Post-Condition</b>: The number of stones at the gate has been returned
	 * @return The number of stones at the game
	 */
	public int getNumberOfStones() { return this.numOfStones; }
	
	/**
	 * <b>Observer</b>: Returns the current drawn tiles from the bag <br>
	 * <b>Post-Condition</b>: The drawn tiles from the bag have been returned
	 * @return The drawn tiles from the bag
	 */
	public String getPickedTiles() { return this.picked_tiles; }
}
