package type.food;

import java.util.HashMap;
import java.util.Map;

import type.EnumInfo.FoodTypeEnum;
import type.EnumInfo.SizeTypeEnum;

public class Drinks extends SizeDependFood {
	private String ingredient;
	private boolean withIce;

	public Drinks(String name, double unitPrice, SizeTypeEnum sizeTypeEnum,
			String ingredient, boolean withIce) {
		super(name, unitPrice, FoodTypeEnum.Drinks, sizeTypeEnum);
		this.ingredient = ingredient;
		this.withIce = withIce;
	}

	public boolean isWithIce() {
		return withIce;
	}

	public void setWithIce(boolean withIce) {
		this.withIce = withIce;
	}

	@Override
	public Map<String, Integer> getIngredientCost() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int count = 0;
		switch (sizeTypeEnum) {
		case Small: {
			count = 1;
			break;
		}
		case Medium: {
			count = 2;
			break;
		}
		case Large: {
			count = 3;
			break;
		}

		default:
			break;
		}

		map.put(ingredient, count);

		return map;
	}
}
