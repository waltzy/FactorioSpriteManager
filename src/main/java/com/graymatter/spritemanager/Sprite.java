package com.graymatter.spritemanager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.graymatter.spritemanager.entities.ManagedSprite;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;
import com.graymatter.spritemanager.exceptions.SpriteBuilderException;
import com.graymatter.spritemanager.ui.SpriteListCellRenderer;
import com.graymatter.spritemanager.util.SMFileUtils;

public class Sprite {
	private File file;
	private BufferedImage image;
	private Vector2i dimentions;
	protected ManagedSprite parent;

	public Sprite() throws SpriteBuilderException {

	}

	@Override
	public String toString() {
		return file.getName();
	}

	public Sprite(File file, ManagedSprite sprite) throws SpriteBuilderException {
		this.setParent(sprite);
		this.file = file;
		try {
			image = ImageIO.read(file);
			updateDimentions();
		} catch (IOException e) {
			throw new SpriteBuilderException("Could not load " + file.getName()
					+ "The file bay be in use by another process or not an image, details: " + e.getMessage());
		}
	}

	protected void updateDimentions() {
		dimentions = new Vector2i(image.getWidth(), image.getHeight());
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Vector2i getDimentions() {
		return dimentions;
	}

	public void setDimentions(Vector2i dimentions) {
		this.dimentions = dimentions;
	}

	private BufferedImage icon;
	private File iconFile;

	public void createIcon() {
		BufferedImage image = getImage();
		int x = image.getWidth();
		float xpc = (float) SpriteListCellRenderer.ICON_MAX_WIDTH / (float) x;
		int y = (int) (image.getHeight() * xpc);
		Image iconImage = image.getScaledInstance(40, y, Image.SCALE_FAST);

		icon = new BufferedImage(iconImage.getWidth(null), iconImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = icon.createGraphics();
		bGr.drawImage(iconImage, 0, 0, null);
		bGr.dispose();

		try {
			File saveto = SMFileUtils.createOrLoadDirectory(file.getParent() + "/icons");
			iconFile = new File(saveto.getAbsolutePath() + "/" + file.getName());
			ImageIO.write(icon, "png", iconFile);
		} catch (ProjectSetupException | IOException e) {
			// meh
			e.printStackTrace();
		}

	}

	public BufferedImage getIcon() {

		if (icon == null) {
			createIcon();
		}

		return icon;
	}

	public File getIconFIle() {
		if (icon == null) {
			createIcon();
		}
		return iconFile;
	}

	public void setIconFIle(File iconFIle) {
		this.iconFile = iconFIle;
	}

	public ManagedSprite getParent() {
		return parent;
	}

	public void setParent(ManagedSprite parent) {
		this.parent = parent;
	}

}
