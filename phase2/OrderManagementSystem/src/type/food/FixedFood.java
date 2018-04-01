package type.food;

import java.util.Map;

import type.EnumInfo.FoodTypeEnum;

public class FixedFood extends Food {
	private Map<String, Integer> ingredientMap;

	public FixedFood(String name, double unitPrice, FoodTypeEnum foodEnum,
			Map<String, Integer> ingredientMap) {
		super(name, unitPrice, foodEnum);
		this.ingredientMap = ingredientMap;
	}

	@Override
	public double getPrice() {
		return unitPrice;
	}

	@Override
	public Map<String, Integer> getIngredientCost() {
		return ingredientMap;
	}

}
