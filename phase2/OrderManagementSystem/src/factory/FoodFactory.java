package factory;

import java.util.Map;

import type.EnumInfo.SizeTypeEnum;
import type.food.Burgers;
import type.food.Desserts;
import type.food.Drinks;
import type.food.Salads;
import type.food.Snacks;
import utils.ConstUtils;

public class FoodFactory {
	private static FoodFactory factory = null;

	public static FoodFactory getInstance() {
		if (factory == null) {
			factory = new FoodFactory();
		}

		return factory;
	}

	public Drinks makeDrinks(SizeTypeEnum sizeTypeEnum, boolean withIce,
			int type) {
		switch (type) {
		case 0: {
			return makeDietCoke(sizeTypeEnum, withIce);
		}
		case 1: {
			return makeCoke(sizeTypeEnum, withIce);
		}
		case 2: {
			return makeOrangeJuice(sizeTypeEnum, withIce);
		}
		}
		return null;
	}

	private Drinks makeDietCoke(SizeTypeEnum sizeTypeEnum, boolean withIce) {
		return new Drinks(ConstUtils.DIET_COKE, 1, sizeTypeEnum,
				ConstUtils.INGREDIENT_DIET_COKE, withIce);
	}

	private Drinks makeCoke(SizeTypeEnum sizeTypeEnum, boolean withIce) {
		return new Drinks(ConstUtils.COKE, 1, sizeTypeEnum,
				ConstUtils.INGREDIENT_COKE, withIce);
	}

	private Drinks makeOrangeJuice(SizeTypeEnum sizeTypeEnum, boolean withIce) {
		return new Drinks(ConstUtils.ORANGE_JUICE, 1, sizeTypeEnum,
				ConstUtils.INGREDIENT_ORANGE_JUICE, withIce);
	}

	private Drinks makeSprite(SizeTypeEnum sizeTypeEnum, boolean withIce) {
		return new Drinks(ConstUtils.SPRITE, 1, sizeTypeEnum,
				ConstUtils.INGREDIENT_SPRITE, withIce);
	}

	private Drinks makeIcedTea(SizeTypeEnum sizeTypeEnum, boolean withIce) {
		return new Drinks(ConstUtils.ICEDTEA, 1, sizeTypeEnum,
				ConstUtils.INGREDIENT_ICEDTEA, withIce);
	}

	private Drinks makeWater(SizeTypeEnum sizeTypeEnum, boolean withIce) {
		return new Drinks(ConstUtils.WATER, 1, sizeTypeEnum,
				ConstUtils.INGREDIENT_WATER, withIce);
	}

	public Snacks makeSnacks(int type) {
		switch (type) {
		case 0:
			return makeChickenNugget();
		case 1:
			return makeBananaChocolateChunkMuffin();
		}
		return null;
	}

	private Snacks makeChickenNugget() {
		return new Snacks(ConstUtils.CHICKEN_NUGGET, 1,
				ConstUtils.INGREDIENT_MAP_OF_CHICKEN_NUGGET);
	}

	private Snacks makeBananaChocolateChunkMuffin() {
		return new Snacks(ConstUtils.BANANA_CHOCOLATE_CHUNK_MUFFIN, 2.5,
				ConstUtils.INGREDIENT_MAP_OF_BANANA_CHOCOLATE_CHUNK_MUFFIN);
	}

	public Desserts makeDesserts(int type) {
		switch (type) {
		case 0:
			return makeSundae();
		case 1:
			return makeVanillaCone();
		case 2:
			return makeApplePie();
		default:
			break;
		}
		return null;
	}

	private Desserts makeSundae() {
		return new Desserts(ConstUtils.SUNDAE, 2,
				ConstUtils.INGREDIENT_MAP_OF_SUNDAE);
	}

	private Desserts makeVanillaCone() {
		return new Desserts(ConstUtils.VANILLA_CONE, 1.5,
				ConstUtils.INGREDIENT_MAP_OF_VANILLA_CONE);
	}

	private Desserts makeApplePie() {
		return new Desserts(ConstUtils.APPLE_PIE, 2.5,
				ConstUtils.INGREDIENT_MAP_OF_APPLE_PIE);
	}

	public Salads makeSalads(SizeTypeEnum sizeTypeEnum, int type) {
		switch (type) {
		case 0:
			return makeCaesarSalad(sizeTypeEnum);
		case 1:
			return makeGardenFreshSalad(sizeTypeEnum);
		}
		return null;
	}

	private Salads makeCaesarSalad(SizeTypeEnum sizeTypeEnum) {
		return new Salads(ConstUtils.CAESAR_SALAD, 5, sizeTypeEnum,
				ConstUtils.INGREDIENT_SET_OF_CAESAR_SALAD);
	}

	private Salads makeGardenFreshSalad(SizeTypeEnum sizeTypeEnum) {
		return new Salads(ConstUtils.GARDEN_FRESH_SALAD, 6, sizeTypeEnum,
				ConstUtils.INGREDIENT_SET_OF_GARDEN_FRESH_SALAD);
	}

	public Burgers makeBurgers(Map<String, Integer> additionalMap, int type) {
		switch (type) {
		case 0:
			return makeDoubleCheeseBurger(additionalMap);
		case 1:
			return makeGrilledChickenBurger(additionalMap);
		default:
			break;
		}
		return null;
	}

	private Burgers makeDoubleCheeseBurger(Map<String, Integer> additionalMap) {
		return new Burgers(ConstUtils.DOUBLE_CHEESEBURGER, 6,
				ConstUtils.INGREDIENT_MAP_OF_DOUBLE_CHEESEBURGER, additionalMap);
	}

	private Burgers makeBaconBeefBurger(Map<String, Integer> additionalMap) {
		return new Burgers(ConstUtils.BACON_BEEF_BURGER, 7,
				ConstUtils.INGREDIENT_MAP_OF_BACON_BEEF_BURGER, additionalMap);
	}

	private Burgers makeGrilledChickenBurger(Map<String, Integer> additionalMap) {
		return new Burgers(ConstUtils.GRILLED_CHICKEN_BURGER, 8,
				ConstUtils.INGREDIENT_MAP_OF_GRILLED_CHICKEN_BURGER,
				additionalMap);
	}
}
