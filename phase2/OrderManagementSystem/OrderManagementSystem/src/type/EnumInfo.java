package type;

public class EnumInfo {
	public enum FoodTypeEnum {
		Drinks, Snacks, Desserts, Salads, Burgers
	}

	public enum SizeTypeEnum {
		Small, Medium, Large
	}

	public enum EmployeeTypeEnum {
		MANAGER("Manager"), Chef("Chef"), Server("Server");

		private String value;

		EmployeeTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public enum OrderStateEnum{
		NEW, WAITING, SENT, FINISHED
	}
}
