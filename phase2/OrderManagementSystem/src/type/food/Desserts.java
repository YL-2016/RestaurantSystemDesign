package type.food;

import java.util.Map;

import type.EnumInfo.FoodTypeEnum;

public class Desserts extends FixedFood {

	public Desserts(String name, double unitPrice,
			Map<String, Integer> ingredientMap) {
		super(name, unitPrice, FoodTypeEnum.Desserts, ingredientMap);
	}

}
