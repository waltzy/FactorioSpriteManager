package com.graymatter.spritemanager.workers;


import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;

import com.graymatter.spritemanager.Sprite;
import com.graymatter.spritemanager.Vector2i;
import com.graymatter.spritemanager.entities.ManagedSprite;
import com.graymatter.spritemanager.exceptions.SpriteBuilderException;
import com.graymatter.spritemanager.ui.ProjectEditorWindow;
import com.graymatter.spritemanager.ui.UIUtils;

public class SpriteBuilder extends SwingWorker<List<Sprite>, Double>{

	private List<Sprite> sprites = new ArrayList<>();
	private List<String> spritesPaths = new ArrayList<>();
	private File sprite;
	private String rootSpritePath;
	private String formatRegex;
	private String directoryPath;
	private String formatPattern;
	private ManagedSprite parent;
	private ProjectEditorWindow invokeingWindow;
	
	@Override
	protected List<Sprite> doInBackground() throws Exception {
		System.out.println("Sprite builder executing...");
		buildFormatRegex();
		getSprites();
		//createCombinedSprites();
		System.out.println("Sprites length is "+sprites.size());
		return sprites;
	}
	
	
	public SpriteBuilder(ManagedSprite parent, String rootSpritePath, String formatPattern, ProjectEditorWindow window) throws SpriteBuilderException{
		this.parent = parent;
		System.out.println("Starting Sprite Builder");
		setRootSpritePath(rootSpritePath);
		setFormatPattern(formatPattern);
		setInvokeingWindow(window);
	}

	private void buildFormatRegex(){
		System.out.println("buildFormatRegex");
		String regex = "";
		String[] parts = getFormatPattern().split("%[0-9]*d");
		System.out.println("splitting...");
		regex = parts[0] + "([0-9]+)" + parts[1];
		System.out.println("Assembling...");
		setFormatRegex(regex);
		System.out.println("Done");
	}
	
	
	private void getSprites() throws SpriteBuilderException {
		System.out.println("getting sprites..");
		sprite = new File(rootSpritePath);
		Integer startIndex = null;
		if (sprite.isDirectory()) {
			startIndex = 0;
			setDirectoryPath(sprite.getAbsolutePath());
		} else {
			setDirectoryPath(sprite.getParentFile().getAbsolutePath());
			Pattern pattern = Pattern.compile(getFormatRegex());
			Matcher matcher = pattern.matcher(sprite.getName());
			while(matcher.find()){
				if (startIndex!=null) throw new SpriteBuilderException("More than one charchter match, their can only be one %d");
				startIndex = Integer.parseInt(matcher.group(1));
			}
		}
		System.out.println("checking selected sprite");
		if (startIndex==null) throw new SpriteBuilderException("Selected File does not match pattern");
		
		System.out.println("Starting at index: "+startIndex);

		int children = new File(getDirectoryPath()).list().length;
		
		Double percetPerFile = 100d / (double)children; 
		
		for (int index = startIndex; index <= children; index++){
			setProgress((int)(percetPerFile*index));
			String scanFor = getDirectoryPath()+"\\"+getFormattedString(index);
			System.out.println("Looking for file : \""+scanFor+"\"");
			
			File foundFile = new File(scanFor);
			if (!foundFile.exists()) continue;
			sprites.add(new Sprite(foundFile, parent));
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

	
	
	@Override
    protected void done() { // called in the EDT. You can update the GUI here, show error dialogs, etc.
        try { 
            List<Sprite> outSprites = get();
            System.out.println("Processed Sprites: "+outSprites.size());
            getInvokeingWindow().setTiles(outSprites);
            
            getInvokeingWindow().processSprites();
            
            
        } catch (Exception e) {
        	UIUtils.showError(e, getInvokeingWindow());
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


	public ProjectEditorWindow getInvokeingWindow() {
		return invokeingWindow;
	}


	public void setInvokeingWindow(ProjectEditorWindow invokeingWindow) {
		this.invokeingWindow = invokeingWindow;
	}


	
	
}
