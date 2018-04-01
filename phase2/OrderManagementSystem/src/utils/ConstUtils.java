package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConstUtils {
	public final static String LINE_SEPARATOR = System.getProperty(
			"line.separator", "/n");

	// Database
	public final static String INGREDIENT_FILE_PATH = "database/ingredient";
	// Log
	public final static String LOG_FILE = "log.txt";

	public final static double SIZE_LARGE_COEF = 1.5;
	public final static double SIZE_SMALL_COEF = 0.8;

	// Food
	/* Drinks */
	public final static String DIET_COKE = "DietCoke";
	public final static String COKE = "Coke";
	public final static String ORANGE_JUICE = "OrangeJuice";
	public final static String SPRITE = "Sprite";
	public final static String ICEDTEA = "IcedTea";
	public final static String WATER = "Water";

	/* Snacks */
	public final static String CHICKEN_NUGGET = "ChickenNugget";
	public final static String BANANA_CHOCOLATE_CHUNK_MUFFIN = "BananaChocolateChunkMuffin";

	/* Desserts */
	public final static String SUNDAE = "Sundae";
	public final static String VANILLA_CONE = "VanillaCone";
	public final static String APPLE_PIE = "ApplePie";

	/* Salads */
	public final static String CAESAR_SALAD = "CaesarSalad";
	public final static String GARDEN_FRESH_SALAD = "GardenFreshSalad";

	/* Burgers */
	public final static String DOUBLE_CHEESEBURGER = "DoubleCheeseBurger";
	public final static String BACON_BEEF_BURGER = "BaconBeefBurger";
	public final static String GRILLED_CHICKEN_BURGER = "GrilledChickenBurger";

	// Ingredient
	public final static String INGREDIENT_DIET_COKE = "DietCoke";
	public final static String INGREDIENT_COKE = "Coke";
	public final static String INGREDIENT_ORANGE_JUICE = "OrangeJuice";
	public final static String INGREDIENT_SPRITE = "Sprite";
	public final static String INGREDIENT_ICEDTEA = "IcedTea";
	public final static String INGREDIENT_WATER = "Water";
	public final static String INGREDIENT_LETTUCE = "Lettuce";
	public final static String INGREDIENT_CHEESE = "Cheese";
	public final static String INGREDIENT_TOMATO = "Tomato";
	public final static String INGREDIENT_CUCUMBER = "Cucumber";
	public final static String INGREDIENT_BREAD = "Bread";
	public final static String INGREDIENT_BEEF = "Beef";
	public final static String INGREDIENT_BACON = "Bacon";
	public final static String INGREDIENT_CHICKEN = "Chicken";
	public final static String INGREDIENT_POTATO = "Potato";
	public final static String INGREDIENT_CHICKEN_MEAT = "ChickenMeat";
	public final static String INGREDIENT_FLOUR = "Flour";
	public final static String INGREDIENT_BANANA = "Banana";
	public final static String INGREDIENT_CHOCOLATE = "Chocolate";
	public final static String INGREDIENT_SUGAR = "Sugar";
	public final static String INGREDIENT_MILK = "Milk";
	public final static String INGREDIENT_CREAM = "Cream";
	public final static String INGREDIENT_VANILLA_SEED = "Cream";
	public final static String INGREDIENT_APPLE = "Apple";

	public static List<String> INGREDIENT_SETS;
	static {
		INGREDIENT_SETS = new ArrayList<String>();
		INGREDIENT_SETS.add(INGREDIENT_DIET_COKE);
		INGREDIENT_SETS.add(INGREDIENT_COKE);
		INGREDIENT_SETS.add(INGREDIENT_ORANGE_JUICE);
		INGREDIENT_SETS.add(INGREDIENT_SPRITE);
		INGREDIENT_SETS.add(INGREDIENT_ICEDTEA);
		INGREDIENT_SETS.add(INGREDIENT_WATER);
		INGREDIENT_SETS.add(INGREDIENT_LETTUCE);
		INGREDIENT_SETS.add(INGREDIENT_CHEESE);
		INGREDIENT_SETS.add(INGREDIENT_TOMATO);
		INGREDIENT_SETS.add(INGREDIENT_CUCUMBER);
		INGREDIENT_SETS.add(INGREDIENT_BREAD);
		INGREDIENT_SETS.add(INGREDIENT_BEEF);
		INGREDIENT_SETS.add(INGREDIENT_BACON);
		INGREDIENT_SETS.add(INGREDIENT_CHICKEN);
		INGREDIENT_SETS.add(INGREDIENT_POTATO);
		INGREDIENT_SETS.add(INGREDIENT_CHICKEN_MEAT);
		INGREDIENT_SETS.add(INGREDIENT_FLOUR);
		INGREDIENT_SETS.add(INGREDIENT_BANANA);
		INGREDIENT_SETS.add(INGREDIENT_CHOCOLATE);
		INGREDIENT_SETS.add(INGREDIENT_SUGAR);
		INGREDIENT_SETS.add(INGREDIENT_MILK);
		INGREDIENT_SETS.add(INGREDIENT_CREAM);
		INGREDIENT_SETS.add(INGREDIENT_VANILLA_SEED);
		INGREDIENT_SETS.add(INGREDIENT_APPLE);
	}

	public static Map<String, Integer> INGREDIENT_MAP_OF_CHICKEN_NUGGET;
	static {
		INGREDIENT_MAP_OF_CHICKEN_NUGGET = new HashMap<String, Integer>();
		INGREDIENT_MAP_OF_CHICKEN_NUGGET.put(INGREDIENT_CHICKEN_MEAT, 1);
		INGREDIENT_MAP_OF_CHICKEN_NUGGET.put(INGREDIENT_FLOUR, 1);
	}

	public static Map<String, Integer> INGREDIENT_MAP_OF_BANANA_CHOCOLATE_CHUNK_MUFFIN;
	static {
		INGREDIENT_MAP_OF_BANANA_CHOCOLATE_CHUNK_MUFFIN = new HashMap<String, Integer>();
		INGREDIENT_MAP_OF_BANANA_CHOCOLATE_CHUNK_MUFFIN.put(INGREDIENT_BANANA,
				1);
		INGREDIENT_MAP_OF_BANANA_CHOCOLATE_CHUNK_MUFFIN.put(
				INGREDIENT_CHOCOLATE, 1);
		INGREDIENT_MAP_OF_BANANA_CHOCOLATE_CHUNK_MUFFIN
				.put(INGREDIENT_FLOUR, 1);
	}

	public static Map<String, Integer> INGREDIENT_MAP_OF_SUNDAE;
	static {
		INGREDIENT_MAP_OF_SUNDAE = new HashMap<String, Integer>();
		INGREDIENT_MAP_OF_SUNDAE.put(INGREDIENT_SUGAR, 2);
		INGREDIENT_MAP_OF_SUNDAE.put(INGREDIENT_MILK, 1);
		INGREDIENT_MAP_OF_SUNDAE.put(INGREDIENT_CREAM, 1);
	}

	public static Map<String, Integer> INGREDIENT_MAP_OF_VANILLA_CONE;
	static {
		INGREDIENT_MAP_OF_VANILLA_CONE = new HashMap<String, Integer>();
		INGREDIENT_MAP_OF_VANILLA_CONE.put(INGREDIENT_SUGAR, 2);
		INGREDIENT_MAP_OF_VANILLA_CONE.put(INGREDIENT_MILK, 2);
		INGREDIENT_MAP_OF_VANILLA_CONE.put(INGREDIENT_VANILLA_SEED, 1);
	}

	public static Map<String, Integer> INGREDIENT_MAP_OF_APPLE_PIE;
	static {
		INGREDIENT_MAP_OF_APPLE_PIE = new HashMap<String, Integer>();
		INGREDIENT_MAP_OF_APPLE_PIE.put(INGREDIENT_APPLE, 2);
		INGREDIENT_MAP_OF_APPLE_PIE.put(INGREDIENT_FLOUR, 2);
		INGREDIENT_MAP_OF_APPLE_PIE.put(INGREDIENT_SUGAR, 2);
	}

	public static Set<String> INGREDIENT_SET_OF_CAESAR_SALAD;
	static {
		INGREDIENT_SET_OF_CAESAR_SALAD = new HashSet<String>();
		INGREDIENT_SET_OF_CAESAR_SALAD.add(INGREDIENT_LETTUCE);
		INGREDIENT_SET_OF_CAESAR_SALAD.add(INGREDIENT_CHEESE);
	}

	public static Set<String> INGREDIENT_SET_OF_GARDEN_FRESH_SALAD;
	static {
		INGREDIENT_SET_OF_GARDEN_FRESH_SALAD = new HashSet<String>();
		INGREDIENT_SET_OF_GARDEN_FRESH_SALAD.add(INGREDIENT_LETTUCE);
		INGREDIENT_SET_OF_GARDEN_FRESH_SALAD.add(INGREDIENT_TOMATO);
		INGREDIENT_SET_OF_GARDEN_FRESH_SALAD.add(INGREDIENT_CUCUMBER);
	}

	public static Map<String, Integer> INGREDIENT_MAP_OF_DOUBLE_CHEESEBURGER;
	static {
		INGREDIENT_MAP_OF_DOUBLE_CHEESEBURGER = new HashMap<String, Integer>();
		INGREDIENT_MAP_OF_DOUBLE_CHEESEBURGER.put(INGREDIENT_CHEESE, 2);
		INGREDIENT_MAP_OF_DOUBLE_CHEESEBURGER.put(INGREDIENT_BREAD, 2);
		INGREDIENT_MAP_OF_DOUBLE_CHEESEBURGER.put(INGREDIENT_BEEF, 2);
	}

	public static Map<String, Integer> INGREDIENT_MAP_OF_BACON_BEEF_BURGER;
	static {
		INGREDIENT_MAP_OF_BACON_BEEF_BURGER = new HashMap<String, Integer>();
		INGREDIENT_MAP_OF_BACON_BEEF_BURGER.put(INGREDIENT_BACON, 2);
		INGREDIENT_MAP_OF_BACON_BEEF_BURGER.put(INGREDIENT_BEEF, 2);
		INGREDIENT_MAP_OF_BACON_BEEF_BURGER.put(INGREDIENT_BREAD, 2);
		INGREDIENT_MAP_OF_BACON_BEEF_BURGER.put(INGREDIENT_LETTUCE, 2);
	}

	public static Map<String, Integer> INGREDIENT_MAP_OF_GRILLED_CHICKEN_BURGER;
	static {
		INGREDIENT_MAP_OF_GRILLED_CHICKEN_BURGER = new HashMap<String, Integer>();
		INGREDIENT_MAP_OF_GRILLED_CHICKEN_BURGER.put(INGREDIENT_CHICKEN, 2);
		INGREDIENT_MAP_OF_GRILLED_CHICKEN_BURGER.put(INGREDIENT_BREAD, 2);
		INGREDIENT_MAP_OF_GRILLED_CHICKEN_BURGER.put(INGREDIENT_LETTUCE, 2);
	}
}
