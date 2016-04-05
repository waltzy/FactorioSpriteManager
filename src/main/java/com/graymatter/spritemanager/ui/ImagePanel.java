package com.graymatter.spritemanager.ui;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image!=null) {

			setBounds(getBounds().x, getBounds().y, image.getWidth(), image.getWidth());
			g.drawImage(image, 0, 0, null);
		}
	}

}
