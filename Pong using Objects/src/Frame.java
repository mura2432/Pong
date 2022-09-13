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
	/* paint is getting called roughly 60x per second */
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		b.paint(g);
		A.paint(g);
		B.paint(g);
		if(b.getX() == 0) {
			b.setVelocityX(10);
		}else if(b.getX() == 750) {
			b.setVelocityX(-10);
		}
		
		if(b.getY() == 0) {
			b.setVelocityY(10);
		}else if(b.getY() == 550) {
			b.setVelocityY(-10);
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
		if(arg0.getKeyCode() == 87) {
			A.setY(A.getY()-15);
		}
		if(arg0.getKeyCode() == 83) {
			A.setY(A.getY()+15);
		}
		if(arg0.getKeyCode() == 38) {
			B.setY(B.getY() - 15);
		}
		if(arg0.getKeyCode() == 40) {
			B.setY(B.getY() + 15);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean collisionA(Ball b, Paddle p) {
		return ((b.getX() <= p.getX() && b.getY() <= p.getY()) &&(b.getX() + b.getWidth() > p.getX()) && (b.getY() + b.getWidth() > p.getY())) || (p.getX() < b.getX() && p.getY() < b.getY()) &&(p.getX() + p.getWidth() > b.getX() && p.getY() + p.getHeight() > b.getY());
	}
	
	public boolean collisionB(Ball b, Paddle p) {
		return ((b.getX() >= p.getX() && b.getY() >= p.getY()) &&(b.getX() + b.getWidth() < p.getX()) && (b.getY() + b.getWidth() < p.getY())) || (p.getX() > b.getX() && p.getY() > b.getY()) &&(p.getX() + p.getWidth() < b.getX() && p.getY() + p.getHeight() < b.getY());
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
