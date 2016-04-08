package com.graymatter.spritemanager.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpriteViewerPanel extends JPanel{

	
	public SpriteViewerPanel(){
		super();
		try {
			background = ImageIO.read(SpriteViewerPanel.class.getResourceAsStream("/background.png"));
		} catch (IOException e) {
			UIUtils.showError(e, this);
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private BufferedImage background;
	private boolean drawBackground = true;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		this.repaint();
		this.getParent().doLayout();
	}

	@Override
	public Dimension getPreferredSize(){
		if (image==null) return super.getPreferredSize();
		return new Dimension(image.getWidth(), image.getHeight());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image!=null) {
			if (isDrawBackground()) g.drawImage(background, 0 , 0 , null);
			g.drawImage(image, 0, 0, null);
		}
	}

	public boolean isDrawBackground() {
		return drawBackground;
	}

	public void setDrawBackground(boolean drawBackground) {
		this.drawBackground = drawBackground;
		this.repaint();
	}

}
