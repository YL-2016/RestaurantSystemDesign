package type.food;

import java.util.Map;

import type.EnumInfo.FoodTypeEnum;

public abstract class Food {
	protected String name;
	protected double unitPrice;
	protected FoodTypeEnum foodEnum;

	public Food(String name, double unitPrice, FoodTypeEnum foodEnum) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
		this.foodEnum = foodEnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public FoodTypeEnum getFoodEnum() {
		return foodEnum;
	}

	public void setFoodEnum(FoodTypeEnum foodEnum) {
		this.foodEnum = foodEnum;
	}

	public abstract double getPrice();

	public abstract Map<String, Integer> getIngredientCost();
}
