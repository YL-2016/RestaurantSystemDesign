package type.food;

import type.EnumInfo.FoodTypeEnum;
import type.EnumInfo.SizeTypeEnum;
import utils.ConstUtils;

public abstract class SizeDependFood extends Food {

	protected SizeTypeEnum sizeTypeEnum;

	public SizeDependFood(String name, double unitPrice, FoodTypeEnum foodEnum,
			SizeTypeEnum sizeTypeEnum) {
		super(name, unitPrice, foodEnum);
		this.sizeTypeEnum = sizeTypeEnum;
	}

	public SizeTypeEnum getSizeTypeEnum() {
		return sizeTypeEnum;
	}

	public void setSizeTypeEnum(SizeTypeEnum sizeTypeEnum) {
		this.sizeTypeEnum = sizeTypeEnum;
	}

	@Override
	public double getPrice() {
		double coef = 1.0;
		switch (sizeTypeEnum) {
		case Small: {
			coef = ConstUtils.SIZE_SMALL_COEF;
			break;
		}
		case Medium: {
			break;
		}
		case Large: {
			coef = ConstUtils.SIZE_LARGE_COEF;
			break;
		}

		default:
			break;
		}

		return unitPrice * coef;
	}

}
