package type.food;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import type.EnumInfo.FoodTypeEnum;
import type.EnumInfo.SizeTypeEnum;

public class Salads extends SizeDependFood {
	private Set<String> ingredients;

	public Salads(String name, double unitPrice, SizeTypeEnum sizeTypeEnum,
			Set<String> ingredients) {
		super(name, unitPrice, FoodTypeEnum.Salads, sizeTypeEnum);
		this.ingredients = ingredients;
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

		Iterator<String> it = ingredients.iterator();
		while (it.hasNext()) {
			String str = it.next();
			map.put(str, count);
		}

		return map;
	}

}
