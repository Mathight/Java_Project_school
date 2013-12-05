import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Dice extends JFrame {
public static void main( String args[] ) {
 JFrame frame = new Dice();
 frame.setSize( 350, 350);
 frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
 frame.setTitle( " Groter Of Kleiner " );
 frame.setContentPane( new Paneel() );
 frame.setVisible( true );
}
}
    
@SuppressWarnings("serial")
class Paneel extends JPanel {
 
	private Die die;
    private Button rollButton,groter,kleiner;
    
    
    
    public Paneel() {
    
        die = new Die();
        rollButton = new Button("Roll");
        groter = new Button("Groter");
        kleiner = new Button("Kleiner");
        add(groter);
        add(rollButton);
        add(kleiner);
        rollButton.addActionListener(new RollListener());
    }

    public void paintComponent(Graphics g)
    {
        // simple text displayed on applet
        g.setColor(Color.white);
        g.fillRect(0, 0, 300, 300);
        g.setColor(Color.black);
        paintDie(g, 20);
        paintDie(g, 150);
        
    }

    private void paintDie(Graphics g, int xOffset)
    {
    	
    	
        g.drawRoundRect(xOffset, 60, 120, 120,15,15);
        switch(die.getValue()) {
        case 1:
            g.fillOval(xOffset + 50, 110, 20, 20);
            break;
        case 2:
            g.fillOval(xOffset + 30, 110, 20, 20);
            g.fillOval(xOffset + 70, 110, 20, 20);
            break;
        case 3:
            g.fillOval(xOffset + 20, 110, 20, 20);
            g.fillOval(xOffset + 50, 110, 20, 20);
            g.fillOval(xOffset + 80, 110, 20, 20);
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
            g.fillOval(xOffset + 20, 85, 20, 20);
            g.fillOval(xOffset + 50, 85, 20, 20);
            g.fillOval(xOffset + 80, 85, 20, 20);
            g.fillOval(xOffset + 20, 135, 20, 20);
            g.fillOval(xOffset + 50, 135, 20, 20);
            g.fillOval(xOffset + 80, 135, 20, 20);
            break;
        }
        die.rollDie(); // update value after displaying
    }

 
    
    private class RollListener implements ActionListener
    {
    	 
        public void actionPerformed(ActionEvent event)
        {
            // since only one widget this should be true
            if(event.getSource() == rollButton) {
                repaint();
            }
        }
    }
   
}
