package utils;

import java.util.HashMap;
import java.util.Map;

import type.food.Burgers;
import type.food.Desserts;
import type.food.Drinks;
import type.food.Salads;
import type.food.Snacks;

public class FuncUtils {
	public static int getNumberFromStr(String str) {
		try {
			int number = Integer.parseInt(str);
			return number;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static Map<String, Integer> getIngredientNeedsMap(
			Map<Drinks, Integer> drinkMap, Map<Snacks, Integer> snackMap,
			Map<Desserts, Integer> dessertsMap, Map<Salads, Integer> saladsMap,
			Map<Burgers, Integer> burgersMap) {
		Map<String, Integer> map = new HashMap<>();

		for (Map.Entry<Drinks, Integer> entry : drinkMap.entrySet()) {
			int number = entry.getValue();
			Drinks drinks = entry.getKey();
			Map<String, Integer> tmpMap = drinks.getIngredientCost();
			collect(map, tmpMap, number);
		}

		for (Map.Entry<Snacks, Integer> entry : snackMap.entrySet()) {
			int number = entry.getValue();
			Snacks snacks = entry.getKey();
			Map<String, Integer> tmpMap = snacks.getIngredientCost();
			collect(map, tmpMap, number);
		}

		for (Map.Entry<Desserts, Integer> entry : dessertsMap.entrySet()) {
			int number = entry.getValue();
			Desserts dessert = entry.getKey();
			Map<String, Integer> tmpMap = dessert.getIngredientCost();
			collect(map, tmpMap, number);
		}

		for (Map.Entry<Salads, Integer> entry : saladsMap.entrySet()) {
			int number = entry.getValue();
			Salads salad = entry.getKey();
			Map<String, Integer> tmpMap = salad.getIngredientCost();
			collect(map, tmpMap, number);
		}

		for (Map.Entry<Burgers, Integer> entry : burgersMap.entrySet()) {
			int number = entry.getValue();
			Burgers burger = entry.getKey();
			Map<String, Integer> tmpMap = burger.getIngredientCost();
			collect(map, tmpMap, number);
		}

		return map;
	}

	private static void collect(Map<String, Integer> targetMap,
			Map<String, Integer> sourceMap, int number) {
		for (Map.Entry<String, Integer> entry : sourceMap.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue() * number;
			Integer originalValue = targetMap.get(key);
			if (originalValue == null) {
				targetMap.put(key, value);
			} else {
				targetMap.put(key, value + originalValue);
			}
		}
	}

}
