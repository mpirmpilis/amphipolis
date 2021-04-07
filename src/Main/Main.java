package Main;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.Controller;


/**
 * @author Georgios Mpirmpilis
 * 
 * <p> This is the main function where the game starts </p>
 */
public class Main {
	public static void main(String[] args) throws IOException {

		String[] choices = {"4", "1"};
		int answer = JOptionPane.showOptionDialog(null,"\tSelect mode\n\n4 : 4 Players\n1 : Player versus thief","Select mode", 0, JOptionPane.INFORMATION_MESSAGE,null,choices,null);
		if (answer == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Mode selected: 4 Players\n\nGood luck!");
			new Controller();      // just create the controller without assigning it somewhere
		} else {
			JOptionPane.showMessageDialog(null, "Mode selected: 1 Player versus Thief\n\nGood luck!");
			new Controller(1);
		}
	}
}
