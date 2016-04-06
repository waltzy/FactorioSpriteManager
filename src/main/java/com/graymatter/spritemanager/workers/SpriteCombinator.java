package com.graymatter.spritemanager.workers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import com.graymatter.spritemanager.CombinedSprite;
import com.graymatter.spritemanager.Sprite;
import com.graymatter.spritemanager.entities.SpriteType;
import com.graymatter.spritemanager.exceptions.SpriteBuilderException;
import com.graymatter.spritemanager.ui.ProjectEditorWindow;
import com.graymatter.spritemanager.ui.UIUtils;

public class SpriteCombinator extends SwingWorker<List<CombinedSprite>, Integer> {
	
	public static int MAX_WIDTH = 2048;
	public static int MAX_HEIGHT = 2048;
	
	private SpriteType type;
	private List<Sprite> tiles;
	private ProjectEditorWindow invokingWindow;
	
	public SpriteCombinator(SpriteType type, List<Sprite> tiles, ProjectEditorWindow invokingWindow) {
		super();
		this.type = type;
		this.tiles = tiles;
		this.invokingWindow = invokingWindow;
	}

	
	
	@Override
	protected List<CombinedSprite> doInBackground() throws Exception {

		switch (type) {
		case VEHICLE:
			return generateVehicleCombinedSprite(tiles);
		default:
			throw new SpriteBuilderException("No Stripe Processor for Sprite type " + type);
		}
	}

	private List<CombinedSprite> generateVehicleCombinedSprite(List<Sprite> sprites) throws SpriteBuilderException {

		if (sprites.size() == 0)
			return new ArrayList<CombinedSprite>();

		Sprite rootSprite = sprites.get(0);

		int stripeFrameLength = MAX_HEIGHT / rootSprite.getDimentions().y;

		System.out.println("MAX SrtipeLength is " + stripeFrameLength);

		List<CombinedSprite> combinedSprites = new ArrayList<CombinedSprite>();

		int totalSprites = sprites.size();

		CombinedSprite currentCombinedSprite = null;
		int counter = 0;
		int totalCounter = 0;
		double procressPercent = 50/totalSprites;
		while (totalCounter < totalSprites) {
			
			if (currentCombinedSprite == null || counter == stripeFrameLength) {
				counter = 0;
				currentCombinedSprite = new CombinedSprite();
				combinedSprites.add(currentCombinedSprite);
			}
			currentCombinedSprite.addSprite(sprites.get(totalCounter));
			setProgress((int)(procressPercent*totalCounter));
			counter++;
			totalCounter++;
			
		}

		procressPercent = 50/combinedSprites.size();
		int i = 0;
		for (CombinedSprite combinedSprite : combinedSprites) {
			
			combinedSprite.update();
			try {
				combinedSprite.SaveBufferedImage("combined-" + i + ".png");
			} catch (IOException e) {
				throw new SpriteBuilderException("could not save image");
			}
			i++;
			setProgress((int)(50+(procressPercent*i)));
		}

		System.out.println("Created " + combinedSprites.size() + " combned Sprites");

		return combinedSprites;
	}

	
	@Override
    protected void done() { // called in the EDT. You can update the GUI here, show error dialogs, etc.
        try { 
            List<CombinedSprite> outSprites = get();
            System.out.println("finished Generating Stripes, generated "+outSprites.size()+" stripes");
            getInvokingWindow().setStripes(outSprites);
        } catch (Exception e) {
        	UIUtils.showError(e, getInvokingWindow());
        }
       
    }
	
	
	
	public SpriteType getType() {
		return type;
	}

	public void setType(SpriteType type) {
		this.type = type;
	}

	public List<Sprite> getTiles() {
		return tiles;
	}

	public void setTiles(List<Sprite> tiles) {
		this.tiles = tiles;
	}



	public ProjectEditorWindow getInvokingWindow() {
		return invokingWindow;
	}



	public void setInvokingWindow(ProjectEditorWindow invokingWindow) {
		this.invokingWindow = invokingWindow;
	}

}
