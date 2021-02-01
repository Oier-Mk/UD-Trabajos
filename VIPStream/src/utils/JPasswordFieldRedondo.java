package utils;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public final class JPasswordFieldRedondo extends JPasswordField {
	Font fontG = new Font("Calibri", Font.PLAIN, 12); //Editar fuente de Usuario y contraseña
	Color color = Color.GRAY;
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
		revalidate();
	}

	public JPasswordFieldRedondo(String str) {
		this.setText(str);
		this.setOpaque(false);
		this.setFont(fontG); //INCLUIR FUENTE
		this.setMargin(new Insets(0, 5, 0, 0));
		this.setForeground(Color.gray);
		this.setBackground( new Color(242, 242, 242) );
		setEditable(false);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setEditable(true);
				setText("");
			}
		});
		addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if (getText().isEmpty()) setText(str);
			}
		});
		
	}

	@Override protected void paintComponent(Graphics g) {
		if (!isOpaque()) {
			int w = getWidth() - 1;
			int h = getHeight() - 1;
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(UIManager.getColor("TextField.background"));
			g2.fillRoundRect(0, 0, w, h, h, h);
			g2.setPaint(color);
			g2.drawRoundRect(0, 0, w, h, h, h);
			g2.dispose();
		}
		super.paintComponent(g);
	}

	@Override public void updateUI() {
		super.updateUI();
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
	}
}
