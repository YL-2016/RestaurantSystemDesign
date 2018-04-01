package type.food;

import java.util.Map;

import type.EnumInfo.FoodTypeEnum;

public class Burgers extends Food {
	private Map<String, Integer> ingredientMap;
	private Map<String, Integer> additionalMap;

	public Burgers(String name, double unitPrice,
			Map<String, Integer> ingredientMap,
			Map<String, Integer> additionalMap) {
		super(name, unitPrice, FoodTypeEnum.Burgers);
		this.ingredientMap = ingredientMap;
		this.additionalMap = additionalMap;
	}

	public int getAdditionalNumber(String str) {
		Integer integer = additionalMap.get(str);
		if (integer != null) {
			return integer;
		} else {
			return 0;
		}
	}

	@Override
	public double getPrice() {
		int count = 0;
		for (Map.Entry<String, Integer> entry : additionalMap.entrySet()) {
			count += entry.getValue();
		}

		return unitPrice + count;
	}

	@Override
	public Map<String, Integer> getIngredientCost() {
		for (Map.Entry<String, Integer> entry : additionalMap.entrySet()) {
			String key = entry.getKey();
			ingredientMap.put(key, ingredientMap.get(key) + entry.getValue());
		}

		return ingredientMap;
	}

}
