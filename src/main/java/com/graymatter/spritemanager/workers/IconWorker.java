package com.graymatter.spritemanager.workers;

import java.util.List;

import javax.swing.JList;
import javax.swing.SwingWorker;

import com.graymatter.spritemanager.Sprite;
import com.graymatter.spritemanager.ui.ProjectEditorWindow;

public class IconWorker<T extends Sprite> extends SwingWorker<List<T>, T>{

	private List<T> sprites;
	private List<T> outSprites;
	private ProjectEditorWindow pwe;
	public IconWorker(List<T> sprites, List<T> windowSprites, ProjectEditorWindow pwe) {
		this.sprites = sprites;
		this.outSprites = windowSprites;
		this.pwe = pwe;
	}
	
	@Override
	protected List<T> doInBackground() throws Exception {
		
		float progress = 100/sprites.size(); 
		for (int i = 0; i < sprites.size(); i++) {
			
			sprites.get(i).createIcon();
			publish(sprites.get(i));
			setProgress((int)(progress*i));
			
		}
		
		setProgress(100);
		
		return sprites;
	}

	 @Override
     protected void process(List<T> chunks) {
		 outSprites.addAll(chunks);
		 pwe.updateTilesList();
		 pwe.updateStripesList();
     }
	
}
