import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
//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
public class Frame extends JPanel implements KeyListener, ActionListener{
	
	Ball b = new Ball(350, 250, 50);
	Paddle A = new Paddle(0, 175);
	Paddle B = new Paddle(760, 175);
	
	int p1score = 0;
	int p2score = 0;
	
	/* paint is getting called roughly 60x per second */
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		b.paint(g);
		A.paint(g);
		B.paint(g);
		
		Font f = new Font("Monospaced", Font.BOLD, 70);
		g.setFont(f);
		g.drawString(p1score + "", 200, 50);
		g.drawString(p2score + "", 550, 50);
		
		if(b.getX() == 0 || b.getX() == 750) {
			b.setVelocityX(-b.getVx());
			if(b.getX() == 0) {
				p2score++;
			}else {
				p1score++;
			}
		}
		if(b.getY() == 0 || b.getY() == 500) {
			b.setVelocityY(-b.getVy());
		}
		if(collisionA(b, A) || collisionB(b,B)) {
			b.setVelocityX(-b.getVx());
		}
	}
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		//87 = w, 83 = s, 38 = up, 40 = down
		if(b.getY() >= 0 && b.getY() <= 500) {
			switch(arg0.getKeyCode()) {
			case 87: 
				A.setVy(-15);
				break;
			case 83:
				A.setVy(15);
				break;
			case 38:
				B.setVy(-15);
				break;
			case 40:
				B.setVy(15);
				break;
			}	
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()) {
		case 87: 
			A.setVy(0);
			break;
		case 83:
			A.setVy(0);
			break;
		case 38:
			B.setVy(0);
			break;
		case 40:
			B.setVy(0);
			break;
		}	
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//Collision For A: If the ball's coordinates are less than or equal to the coordiantes of the paddle plus their respective lenghts, its a collision
	public boolean collisionA(Ball b, Paddle p) {
		return (p.getX() + p.getWidth() >= b.getX() && p.getY() + p.getHeight() >= b.getY());
	}
	
	public boolean collisionB(Ball b, Paddle p) {
		return (p.getX() == b.getX() + b.getWidth() && p.getY() + p.getHeight() >= b.getY());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	Timer t;
	
	public Frame() {
		JFrame f = new JFrame("Pong");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		f.add(this);
		f.addKeyListener(this);
		
		b.setVelocityX(10);
		b.setVelocityY(10);
		t = new Timer(16, this);
		t.start();
		f.setVisible(true);
		
	}
}
