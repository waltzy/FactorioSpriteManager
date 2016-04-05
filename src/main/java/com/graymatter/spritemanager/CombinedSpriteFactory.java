package com.graymatter.spritemanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.graymatter.spritemanager.entities.SpriteType;
import com.graymatter.spritemanager.exceptions.SpriteBuilderException;

public class CombinedSpriteFactory {

	public static int MAX_WIDTH = 2048;
	public static int MAX_HEIGHT = 2048;

	public static List<CombinedSprite> generateCombinedSprites(List<Sprite> sprites, SpriteType type)
			throws SpriteBuilderException {

		switch (type) {
		case VEHICLE:
			return generateVehicleCombinedSprite(sprites);
		default:
			throw new SpriteBuilderException("No Stripe Processor for Sprite type " + type);
		}
	}

	private static List<CombinedSprite> generateVehicleCombinedSprite(List<Sprite> sprites) throws SpriteBuilderException {

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
		while (totalCounter < totalSprites) {

			if (currentCombinedSprite == null || counter == stripeFrameLength) {
				counter = 0;
				currentCombinedSprite = new CombinedSprite();
				combinedSprites.add(currentCombinedSprite);
			}
			currentCombinedSprite.addSprite(sprites.get(totalCounter));
			counter++;
			totalCounter++;
		}

		int i = 0;
		for (CombinedSprite combinedSprite : combinedSprites) {

			combinedSprite.update();
			try {
				combinedSprite.SaveBufferedImage("combined-" + i + ".png");
			} catch (IOException e) {
				throw new SpriteBuilderException("could not save image");
			}
			i++;
		}

		System.out.println("Created " + combinedSprites.size() + " combned Sprites");

		return combinedSprites;
	}

}
