package com.graymatter.spritemanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.graymatter.spritemanager.exceptions.SpriteBuilderException;

public class Sprite {
	private File file;
	private BufferedImage image;
	private Vector2i dimentions;
	
	public Sprite() throws SpriteBuilderException{

	}
	
	@Override 
	public String toString(){
		return file.getName();
	} 
	
	public Sprite(File file) throws SpriteBuilderException {
		this.file = file;
		try {
			image = ImageIO.read(file);
			updateDimentions();
		} catch (IOException e) {
			throw new SpriteBuilderException("Could not load "+file.getName()+"The file bay be in use by another process or not an image, details: "+e.getMessage());
		}
	}

	protected void updateDimentions(){
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
	
}
