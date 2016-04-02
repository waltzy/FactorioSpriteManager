package com.graymatter.spritemanager;


import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.graymatter.spritemanager.exceptions.SpriteBuilderException;

public class SpriteBuilder {

	private List<Sprite> sprites = new ArrayList<>();
	private List<String> spritesPaths = new ArrayList<>();
	private File sprite;
	private String rootSpritePath;
	private String formatRegex;
	private String directoryPath;
	private String formatPattern;
	
	public SpriteBuilder(String rootSpritePath, String formatPattern) throws SpriteBuilderException{
		setRootSpritePath(rootSpritePath);
		setFormatPattern(formatPattern);
		buildFormatRegex();
		getSprites();
		createCombinedSprites();
	}

	private void buildFormatRegex(){
		String regex = "";
		String[] parts = getFormatPattern().split("%[0-9]*d");
		regex = parts[0] + "([0-9]+)" + parts[1];
		setFormatRegex(regex);
	}
	
	public List<CombinedSprite> createCombinedSprites() throws SpriteBuilderException{
		CombinedSpriteFactory.generateCombinedSprites(sprites);
		return null;
	} 
	
	private void getSprites() throws SpriteBuilderException {
		sprite = new File(rootSpritePath);
		if (sprite.isDirectory()) throw new SpriteBuilderException("Root Sprite cannot be a directory");
		setDirectoryPath(sprite.getParentFile().getAbsolutePath());
		Pattern pattern = Pattern.compile(getFormatRegex());
		Matcher matcher = pattern.matcher(sprite.getName());
		Integer startIndex = null;
		while(matcher.find()){
			if (startIndex!=null) throw new SpriteBuilderException("More than one charchter match, their can only be one %d");
			startIndex = Integer.parseInt(matcher.group(1));
		}
		if (startIndex==null) throw new SpriteBuilderException("Selected File does not match pattern");
		
		System.out.println("Starting at index: "+startIndex);

		for (int index = startIndex; index < Integer.MAX_VALUE; index++){
			
			String scanFor = getDirectoryPath()+"\\"+getFormattedString(index);
			System.out.println("Looking for file : \""+scanFor+"\"");
			
			File foundFile = new File(scanFor);
			if (!foundFile.exists()) break;
			sprites.add(new Sprite(foundFile));
			spritesPaths.add(foundFile.getAbsolutePath());
		}
		if (sprites.size()==0) throw new SpriteBuilderException("No Sprites found that match pattern");
		System.out.println("Found "+sprites.size()+" sprites");
		checkDimentions();
	}
	
	private void checkDimentions() throws SpriteBuilderException{
		Vector2i dimentions = sprites.get(0).getDimentions();
		for (Sprite sprite : sprites) {
			if (!sprite.getDimentions().equals(dimentions)) throw new SpriteBuilderException("Sprites not all same size, "
					+ "sprite \""+sprite.getFile().getName()+"\""
					+ " dimentions are "+sprite.getDimentions()
					+ " expected dimentions are "+dimentions);
		}
	}
	
	private String getFormattedString(int num){
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		try {
			return formatter.format(getFormatPattern(), num).toString();
		}  finally {
			formatter.close();
		}
	}

	public String getRootSpritePath() {
		return rootSpritePath;
	}

	public void setRootSpritePath(String rootSpritePath) {
		this.rootSpritePath = rootSpritePath;
	}

	public String getFormatPattern() {
		return formatPattern;
	}

	public void setFormatPattern(String formatPattern) {
		this.formatPattern = formatPattern;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getFormatRegex() {
		return formatRegex;
	}

	public void setFormatRegex(String formatRegex) {
		this.formatRegex = formatRegex;
	}
	
	
	
}
