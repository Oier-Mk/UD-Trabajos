package utils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;

public class JButtonRedondo extends JButton {
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		JButtonRedondo b = new JButtonRedondo("Hola mundo!");
		f.setLocationRelativeTo(null);
		f.setSize(50, 50);
		f.add(p);
		p.add(b);
		f.setVisible(true);
	}
	
	public JButtonRedondo(String string) {

		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setText(string);
		setContentAreaFilled(false);
	}

	public void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(getBackground());
		}
		g.fillOval(0, 0, getSize().width-1, getSize().height-1);
		super.paintComponent(g);
	}

	public void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, 0, getSize().width-1, getSize().height-1);
	}

	Shape shape;
	public boolean contains(int x, int y) {
		if (shape == null || 
				!shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
}