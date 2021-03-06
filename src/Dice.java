import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Dice extends JFrame {
	
	public static void main(String args[]) {
		JFrame frame = new Dice();
		frame.setSize(300, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(" Groter Of Kleiner ");
		frame.setContentPane(new Paneel());
		frame.setVisible(true);
	}
}

@SuppressWarnings("serial")
class Paneel extends JPanel{

	
	private Dobbelsteen dobbelsteen;
	private JButton rollButton, groter, kleiner,send;
	private int waarde1, waarde2, totaal, waarde, lastVal, newVal, rounds;
	private boolean higher, rollThisShit;
	private JTextField naam;
	private DB db;
	

	public Paneel() {
		dobbelsteen = new Dobbelsteen();
		db = new DB();
		
		naam = new JTextField(12);
		send = new JButton("Verstuur");
		rollButton = new JButton("Roll");
		groter = new JButton("Groter");
		kleiner = new JButton("Kleiner");

		add(groter);
		add(rollButton);
		add(kleiner);
		add(naam);
		add(send);
		rollButton.addActionListener(new RollListener());
		groter.addActionListener(new GroterListener());
		kleiner.addActionListener(new KleinerListener());
		send.addActionListener(new SendListener());
		
		send.setVisible(false);
		naam.setVisible(false);
	}

	public void paintComponent(Graphics g) {
		
		if(rollThisShit){
			g.setColor(Color.white);
			g.fillRect(0, 0, 300, 300);
			g.setColor(Color.black);
			waarde1 = paintDie(g, 20);
			waarde2 = paintDie(g, 150);
	
			dobbelsteen.setWorp1(waarde1);
			dobbelsteen.setWorp2(waarde2);
	
			totaal = waarde1 + waarde2;
			newVal = totaal;
			int checked = checkHiger();
			if(checked == 2){
				// Goed geraden
				rounds++;
				g.drawString("de score is : " + rounds, 20, 50);
				System.out.print("Vorige waarde: " + lastVal + " Waarde nu: " + totaal + "\r\n");
				lastVal = newVal;
			}else if(checked == 1){
				// Nog niet begonnen
				System.out.print("We zijn nog niet begonnen\r\n");
				lastVal = totaal;
			}else if(checked == 0){
				// Fout geraden
				System.out.print("GAME OVER!!!" + "\r\n");
				lastVal = 0;
				rollThisShit = false;
				
				groter.setVisible(false);
				kleiner.setVisible(false);
				rollButton.setVisible(false);
				
				g.setColor(Color.WHITE);
				//g.fillRect(0, 0, 300, 300);
				g.setColor(Color.BLACK);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
				g.drawString("Game Over.",50,210);
				g.drawString("Uw Score is: " + rounds,50,230);
				
				send.setVisible(true);
				naam.setVisible(true);
			}
		}
	}

	private int paintDie(Graphics g, int xOffset) {
		g.drawRoundRect(xOffset, 60, 120, 120, 15, 15);

		waarde = dobbelsteen.gooi();
		switch (waarde) {
		case 1:
			g.fillOval(xOffset + 50, 110, 20, 20);
			break;
		case 2:
			g.fillOval(xOffset + 30, 85, 20, 20);
			g.fillOval(xOffset + 70, 135, 20, 20);
			break;
		case 3:
			g.fillOval(xOffset + 50, 110, 20, 20);
			g.fillOval(xOffset + 30, 85, 20, 20);
			g.fillOval(xOffset + 70, 135, 20, 20);
			break;
		case 4:
			g.fillOval(xOffset + 30, 85, 20, 20);
			g.fillOval(xOffset + 70, 85, 20, 20);
			g.fillOval(xOffset + 30, 135, 20, 20);
			g.fillOval(xOffset + 70, 135, 20, 20);
			break;
		case 5:
			g.fillOval(xOffset + 50, 110, 20, 20);
			g.fillOval(xOffset + 30, 85, 20, 20);
			g.fillOval(xOffset + 70, 85, 20, 20);
			g.fillOval(xOffset + 30, 135, 20, 20);
			g.fillOval(xOffset + 70, 135, 20, 20);
			break;
		case 6:
			g.fillOval(xOffset + 25, 80, 20, 20);
			g.fillOval(xOffset + 25, 110, 20, 20);
			g.fillOval(xOffset + 25, 140, 20, 20);
			g.fillOval(xOffset + 75, 80, 20, 20);
			g.fillOval(xOffset + 75, 110, 20, 20);
			g.fillOval(xOffset + 75, 140, 20, 20);
			break;
		}
		return waarde;

	}
	
	private int checkHiger(){
		if(lastVal <= 0){
			return 1;
		}else{
			if(higher){
				if(lastVal <= newVal){
					return 2;
				}else{
					return 0;
				}
			}else{
				if(lastVal >= newVal){
					return 2;
				}else{
					return 0;
				}
			}
		}
	}

	private class RollListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event) {
				// since only one widget this should be true
				rollThisShit = true;
				if (event.getSource() == rollButton) {
					repaint();
					rollButton.setVisible(false);
				}
			}
		}
	
	private class GroterListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// since only one widget this should be true
			rollThisShit = true;
			higher = true;
			if (event.getSource() == groter) {
				repaint();
			}
		}
	}
	
	private class KleinerListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// since only one widget this should be true
			rollThisShit = true;
			higher = false;
			if (event.getSource() == kleiner) {
				repaint();
			}
		}
	}
	
	private class SendListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// since only one widget this should be true
			rollThisShit = false;
			if (event.getSource() == send) {
				db.addScore(naam.getText(), rounds);
				System.out.print("Score toegevoegd met naam: " + naam.getText() + " met score: " + rounds + "\r\n");
				send.setVisible(false);
				naam.setVisible(false);
				groter.setVisible(true);
				kleiner.setVisible(true);
				rollThisShit = true;
				rounds = 0;
				repaint();
			}
		}
	}
	
}
