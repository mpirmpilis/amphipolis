package view;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;


/**
 * 
 * @author Georgios Mpirmpilis
 *
 * <br><br>Creates the <b>Graphical User Interface (GUI)</b> in order the player can play the game
 */

public class GraphicsUI {
	private Controller game;
	private JFrame  frame = new JFrame("Amphipolis Board Game - CS252");
	
	private JButton drawButton = new JButton("Draw Tiles");
	public JButton endRoundButton = new JButton("End turn");  
	public JButton assistantButton = new JButton(new ImageIcon("images_2020\\assistant.png"));
	public JButton archaeologistButton = new JButton(new ImageIcon("images_2020\\archaeologist.png"));
	public JButton diggerButton = new JButton(new ImageIcon("images_2020\\digger.png"));
	public JButton professorButton = new JButton(new ImageIcon("images_2020\\professor.png"));
	private JButton playerCollectionButton = new JButton("Show player's collection");
	public JButton save_button = new JButton("Save game");
	public JButton load_button = new JButton("Load game");
	
	public JButton[] toublakia = new JButton[135];
	public JPanel[] panelakia = new JPanel[135];
	
	private JLabel choose_char_label = new JLabel("Choose Character");
	private JLabel drawn_from_bag = new JLabel("Drawn tiles from bag");
	
	public JLabel info_player;
	public JLabel drawn_tiles = new JLabel();
	private JLabel StonesOnBoard = new JLabel("Stones at gate:");
	public JLabel numStones = new JLabel("0");
	
	/**
	 * <b>Constructor</b>: Sets the controller so it can communicates and creates the board and the buttons-panels
	 * <br><b>Post-condition</b>: The GUI is ready and the player can start playing the game
	 * @param controller The controller which controls the actions of the game
	 * @throws IOException
	 */
	public GraphicsUI(Controller controller) throws IOException {
		this.game = controller;
		info_player = new JLabel(game.currentPlayer.toString());
		Board();
		createButtonsAndPanels();
	}
	

	/**
	 * <b>Transformer</b>: Creates the board with every button and image required for the game
	 * <br><b>Post-Condition</b>: The board is now created
	 * @throws IOException
	 */
	public void Board() throws IOException {
    /* load background here -- START HERE */
    	BufferedImage img = ImageIO.read(new File("images_2020\\background.png"));
    	Image scaled = img.getScaledInstance(650, 650, Image.SCALE_SMOOTH);
    	ImageIcon icon = new ImageIcon(scaled);
    	frame.setPreferredSize(new Dimension(900,800));
    	JLabel ico = new JLabel(icon);
    	
    	info_player.setBounds(700, -5, 555, 100);
    	choose_char_label.setBounds(720, 30, 555, 100);
    	drawn_from_bag.setBounds(480, 646, 200, 50);
    	drawn_tiles.setBounds(360, 668, 700, 50);
    	StonesOnBoard.setBounds(655, 430, 100, 170);
    	numStones.setBounds(745, 430, 100, 170);
    	
    	ico.setBounds(0,0,650,650);   // the 0,0 starts from the top-left corner of the frame
    /* load background here -- STOP HERE */
    	
    	frame.setVisible(true);
    	frame.add(ico);
    	frame.add(drawn_from_bag);
    	
    	frame.add(numStones);
    	frame.add(StonesOnBoard);
    	frame.add(drawn_tiles);
    	frame.add(info_player);
    	frame.add(choose_char_label);
    	frame.setLayout(null);
    	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
    	archaeologistButton.setBounds(655, 100, 100, 170);     // panw-aristera
    	assistantButton.setBounds(780, 100, 100, 170);         // panw-deksia
    	diggerButton.setBounds(655, 300, 100, 170);            // katw-aristera
    	professorButton.setBounds(780, 300, 100, 170);         // katw-deksia
    	playerCollectionButton.setBounds(10, 675, 200, 50);
    	save_button.setBounds(250, 675, 100, 30);
    	load_button.setBounds(250, 715, 100, 30);

    	endRoundButton.setBounds(655, 600, 200, 50);
    	drawButton.setBounds(655,550,200,50);
    	
    	
    	frame.add(assistantButton);
    	frame.add(archaeologistButton);
    	frame.add(diggerButton);
    	frame.add(professorButton);
    	frame.add(drawButton);
    	frame.add(drawButton);
    	frame.add(endRoundButton);
    	frame.add(playerCollectionButton);
    	frame.add(save_button);
    	frame.add(load_button);
    	
    	frame.setResizable(false);
    	
    	frame.pack();
    	frame.requestFocus();
	}
	
	
	/**
	 * <b>Transformer</b>: Creates and specifically positions buttons and panels on the board
	 * <br><b>Post-Condition</b>: All 135 buttons and panels are correctly set up 
	 */
	private void createButtonsAndPanels() {
		int i, x, y, k=0, m, j;
		
		// set up the buttons
		for (i=0; i<135; i++) {
			if (i>=0 && i<9)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\mosaic_green.png"));
			else if (i>=9 && i<18)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\mosaic_red.png"));
			else if (i>=18 && i<27)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\mosaic_yellow.png"));
			else if (i>=27 && i<39)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\caryatid.png"));
			else if (i>=39 && i<51)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\sphinx.png"));
			else if (i>=51 && i<61)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\skeleton_big_top.png"));
			else if (i>=61 && i<71)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\skeleton_big_bottom.png"));
			else if (i>=71 && i<76)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\skeleton_small_top.png"));
			else if (i>=76 && i<81)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\skeleton_small_bottom.png"));	
			else if (i>=81 && i<86)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\amphora_blue.png"));
			else if (i>=86 && i<91)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\amphora_brown.png"));
			else if (i>=91 && i<96)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\amphora_red.png"));
			else if (i>=96 && i<101)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\amphora_green.png"));
			else if (i>=101 && i<106)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\amphora_yellow.png"));
			else if (i>=106 && i<111)
				toublakia[i] = new JButton(new ImageIcon("images_2020\\amphora_purple.png"));
			else
				toublakia[i] = new JButton(new ImageIcon("images_2020\\landslide.png"));
			
			toublakia[i].setBounds(19,19,36,66);
			toublakia[i].setBorder(BorderFactory.createEmptyBorder());
		}
		
		
		/* 1. prosthiki mwsaikwn */
		x = 17;
		y = 13;
		for (i=0; i<27; i++) {
			panelakia[i] = new JPanel();
			panelakia[i].setLayout(new FlowLayout());
			panelakia[i].setBounds(x, y, 29, 32);
			panelakia[i].add(toublakia[i]);
			
			
			// this is used for positioning the tiles (panels actually) on the frame
			if (k < 4) {
				x = x + 40;   // position on x-axis
				k++;
			} else {
				k = 0;
				x = 17;
				y = y + 30;   // position on y-axis
			}
		}	
			
		m = i;	
			/* 2. prosthiki agalmatwn */
			x = 441;
			y = 12;
			k = 0;
			for (j=m; j<m+24; j++) {
				panelakia[j] = new JPanel();
				panelakia[j].setLayout(new FlowLayout());
				panelakia[j].setBounds(x, y, 29, 32);
				panelakia[j].add(toublakia[j]);
				
				// this is used for positioning the tiles (panels actually) on the frame
				if (k < 4) {
					x = x + 40;   // position on x-axis
					k++;
				} else {
					k = 0;
					x = 441;
					y = y + 33;   // position on y-axis
				}
			}
			m = j;
			/* 3. prosthiki skeletos */
			
			
			x = 439;
			y = 435;
			k = 0;
			for (j=m; j<m+30; j++) {
				panelakia[j] = new JPanel();
				panelakia[j].setLayout(new FlowLayout());
				panelakia[j].setBounds(x, y, 29, 32);
				panelakia[j].add(toublakia[j]);
				
				
				// this is used for positioning the tiles (panels actually) on the frame
				if (k < 4) {
					x = x + 40;   // position on x-axis
					k++;
				} else {
					k = 0;
					x = 439;
					y = y + 33;   // position on y-axis
				}
			}
			
			
			
			/* 4. prosthiki amforewn */
			x = 17;
			y = 435;
			k = 0;
			m = j;
			for (j=m; j<m+30; j++) {
				panelakia[j] = new JPanel();
				panelakia[j].setLayout(new FlowLayout());
				panelakia[j].setBounds(x, y, 29, 32);
				panelakia[j].add(toublakia[j]);
				
				
				// this is used for positioning the next tiles after the first one (panels actually) on the frame
				if (k < 4) {
					x = x + 40;   // position on x-axis
					k++;
				} else {
					k = 0;
					x = 17;
					y = y + 33;   // position on y-axis
				}
			}
			
			
			
			
			/* 5. prosthiki katoslithisewn */
			x = 230;
			y = 278;
			k = 0;
			m = j;
			for (j=m; j<m+24; j++) {
				panelakia[j] = new JPanel();
				panelakia[j].setLayout(new FlowLayout());
				panelakia[j].setBounds(x, y, 29, 32);
				panelakia[j].add(toublakia[j]);
				
				// this is used for positioning the next tiles after the first one (panels actually) on the frame
				if (k < 4) {
					x = x + 40;   // position on x-axis
					k++;
				} else {
					k = 0;
					x = 230;      // reset position on x-axis to go down
					y = y + 33;   // position on y-axis
				}
			}
	}
	

	
	/**
	 * <b>Transformer</b>: Adds the AssistantListener from controller to Assistant button
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Assistant button is now connected to the corresponding listener
	 * @param AssistantListener the listener to be assigned to the Assistant button
	 */
	public void addAssistantListener(ActionListener AssistantListener) {
		assistantButton.addActionListener(AssistantListener);
	}
	
	
	/**
	 * <b>Transformer</b>: Adds the ArchaeologistListener from controller to Archaeologist button
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Archaeologist button is now connected to the corresponding listener
	 * @param ArchaeologistListener the listener to be assigned to the Archaeologist button
	 */
	public void addArchaeologistListener(ActionListener ArchaeologistListener) {
		archaeologistButton.addActionListener(ArchaeologistListener);
	}
	
	
	/**
	 * <b>Transformer</b>: Adds the DiggerListener from controller to Digger button
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Digger button is now connected to the corresponding listener
	 * @param DiggerListener the listener to be assigned to the Digger button
	 */
	public void addDiggerListener(ActionListener DiggerListener) {
		diggerButton.addActionListener(DiggerListener);
	}
	
	
	/**
	 * <b>Transformer</b>: Adds the ProfessorListener from controller to Professor button
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Professor button is now connected to the corresponding listener
	 * @param ProfessorListener the listener to be assigned to the Professor button
	 */
	public void addProfessorListener(ActionListener ProfessorListener) {
		professorButton.addActionListener(ProfessorListener);
	}
	
	/**
	 * <b>Transformer</b>: Adds the PlayerCollectionListener from controller to Player's collection button
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Player's collection button is now connected to the corresponding listener
	 * @param PlayerCollectionListener the listener to be assigned to the Player's collection button
	 */
	public void addShowPlayerCollectionListener(ActionListener PlayerCollectionListener) {
		playerCollectionButton.addActionListener(PlayerCollectionListener);
	}
	
	
	/**
	 * <b>Transformer</b>: Adds the DrawListener from controller to Draw button
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Draw button is now connected to the corresponding listener
	 * @param DrawListener the listener to be assigned to the Draw button
	 */
	public void addDrawListener(ActionListener DrawListener) {
		drawButton.addActionListener(DrawListener);
	}

	
	/**
	 * <b>Transformer</b>: Adds the EndRoundListener from controller to End turn button
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: End turn button is now connected to the corresponding listener
	 * @param EndRoundListener the listener to be assigned to the End turn button
	 */
	public void addEndRoundListener(ActionListener EndRoundListener) {
		endRoundButton.addActionListener(EndRoundListener);
	}
	
	
	/**
	 * <b>Transformer</b>: Adds the ToublakiaListener to all buttons simulating the tiles
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Tile buttons are now connected to the corresponding listener
	 * @param ToublakiaListener the listener to be assigned to the tile buttons
	 */
	public void addToublakiaListener(ActionListener ToublakiaListener) {
		// don't assign listener to rocks as they shouldn't be picked up
		for (int i=0; i<111; i++) {
			toublakia[i].addActionListener(ToublakiaListener);
		}
	}
	

	
	/**
	 * <b>Transformer</b>: Adds the SaveGameListener to save button for saving the game
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Save button is now connected to the corresponding listener
	 * @param SaveGameListener the listener to be assigned to the save game button
	 */
	public void addSaveGameListener(ActionListener SaveGameListener) {
		save_button.addActionListener(SaveGameListener);
	}

	
	/**
	 * <b>Transformer</b>: Adds the LoadGameListener to load game button for loading the game from a
	 * previous state
	 * <br><b>Pre-Condition</b>: The corresponding listener must be created before assignment
	 * <br><b>Post-Condition</b>: Load button is now connected to the corresponding listener
	 * @param LoadGameListener the listener to be assigned to the load game button
	 */
	public void addLoadGameListener(ActionListener LoadGameListener) {
		load_button.addActionListener(LoadGameListener);
	}

	/**
	 * <b>Observer</b>: Returns the current frame
	 * <br><b>Post-Condition</b>: The current view's frame is returned
	 * @return the view's frame
	 */
	public JFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * <b>Transformer</b>: Adds the comp (JPanel actually) to the frame, indicating a tile added to the board <br>
	 * <br><b>Post-Condition</b>: The component is successfully added in the frame
	 * @param comp The component to be added to the frame
	 */
	public void addToFrame(JPanel comp) {
		this.frame.add(comp);
		frame.revalidate();
		frame.pack();
	}
	
	
	/**
	 * Refreshes the GUI after a game has been successfully loaded from a <b>savegame.txt</b> file <br>
	 * <b>Post-Condition</b>: The GUI is now set to the loaded game from a previous state
	 * @throws IOException
	 */
	public void refresh_the_gui() {
		int[] temp_bag = game.getBagHistory();
		
		
		// check which panels were active during the last session
		
		for (int i=0; i<135; i++) {
			if (temp_bag[i] == 1) {
				frame.requestFocus();
				frame.add(panelakia[i]);
				frame.revalidate();
				frame.pack();
			} 
		}
		archaeologistButton.setEnabled(!game.currentPlayer.getArchaeologist().GetStatus());
		assistantButton.setEnabled(!game.currentPlayer.getAssistant().GetStatus());
		diggerButton.setEnabled(!game.currentPlayer.getDigger().GetStatus());
		professorButton.setEnabled(!game.currentPlayer.getProfessor().GetStatus());
		
		info_player.setText(game.currentPlayer.toString());
		drawn_tiles.setText(game.getPickedTiles());
		numStones.setText(String.valueOf(game.getNumberOfStones()));
	}
}
