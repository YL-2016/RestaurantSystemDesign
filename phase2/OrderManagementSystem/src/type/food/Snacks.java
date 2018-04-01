package type.food;

import java.util.Map;

import type.EnumInfo.FoodTypeEnum;

public class Snacks extends FixedFood {

	public Snacks(String name, double unitPrice,
			Map<String, Integer> ingredientMap) {
		super(name, unitPrice, FoodTypeEnum.Snacks, ingredientMap);
	}

}
