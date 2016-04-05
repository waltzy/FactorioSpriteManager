package com.graymatter.spritemanager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.graymatter.spritemanager.exceptions.SpriteBuilderException;

public class CombinedSprite extends Sprite {

	private List<Sprite> sprites = new ArrayList<Sprite>();
	
	public CombinedSprite() throws SpriteBuilderException {

	}

	public void addSprite(Sprite sprite){
		sprites.add(sprite);
		if (sprites.size()==1){
			this.setImage(sprite.getImage());
			this.setDimentions(sprite.getDimentions());
		}
	}
	
	public String getStripe(){
		return null;
		
	}
	
	public void update(){
		for (int i =0; i < sprites.size()-2; i++){
			if (i==0) continue;
			this.setImage(joinBufferedImageVerticial(this.getImage(), sprites.get(i).getImage()));
			updateDimentions();
		}
	}

	public void SaveBufferedImage(String fileName) throws IOException{
		File outputfile = new File(sprites.get(0).getFile().getParentFile().getAbsolutePath()+"/"+fileName);
	    ImageIO.write(getImage(), "png", outputfile);
	}


	
	   public static BufferedImage joinBufferedImageHorizontal(BufferedImage img1,BufferedImage img2) {

	        int offset  = 5;
	        int wid = img1.getWidth()+img2.getWidth()+offset;
	        int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
	        //create a new buffer and draw two image into the new image
	        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2 = newImage.createGraphics();
	        Color oldColor = g2.getColor();
	        //fill background
	        //g2.setPaint(Color.WHITE);
	        //g2.fillRect(0, 0, wid, height);
	        //draw image
	        g2.setColor(oldColor);
	        g2.drawImage(img1, null, 0, 0);
	        g2.drawImage(img2, null, img1.getWidth()+offset, 0);
	        g2.dispose();
	        return newImage;
	    }
	   
	   
	   public static BufferedImage joinBufferedImageVerticial(BufferedImage img1,BufferedImage img2) {

	        int height = img1.getHeight()+img2.getHeight();
	        int width = Math.max(img1.getWidth(),img2.getWidth());
	        
	        //create a new buffer and draw two image into the new image
	        BufferedImage newImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2 = newImage.createGraphics();
	        Color oldColor = g2.getColor();
	        //fill background
	        //g2.setPaint(Color.WHITE);
	        //g2.fillRect(0, 0, width, height);
	        //draw image
	        g2.setColor(oldColor);
	        g2.drawImage(img1, null, 0, 0);
	        g2.drawImage(img2, null, 0, img1.getHeight());
	        g2.dispose();
	        return newImage;
	    }
}
