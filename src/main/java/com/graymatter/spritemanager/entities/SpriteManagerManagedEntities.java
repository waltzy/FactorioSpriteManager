package com.graymatter.spritemanager.entities;

import org.apache.commons.lang3.ArrayUtils;

import com.graymatter.spritemanager.exceptions.ProjectSetupException;

public class SpriteManagerManagedEntities {

	private ManagedSprite[] sprites = new ManagedSprite[0];

	public ManagedSprite[] getSprites() {
		return sprites;
	}

	public void setSprites(ManagedSprite[] sprites) {
		this.sprites = sprites;
	}

	public void addEntity(ManagedSprite ms) throws ProjectSetupException {
		for (int i = 0; i < sprites.length; i++) {
			if (sprites[i].getManagedSpriteName().equals(ms.getManagedSpriteName())) throw new ProjectSetupException("Managed Sprite already exists "+ms.getManagedSpriteName());
		}
		sprites = (ManagedSprite[]) ArrayUtils.add(sprites, ms);
	}

	public void removeEntity(ManagedSprite ms){
		int index = -1;
		
		for (int i = 0; i < sprites.length; i++) {
			if (sprites[i].getManagedSpriteName().equals(ms.getManagedSpriteName())) index = i; 
		}
		sprites = ArrayUtils.remove(sprites, index);
	}
}

