package type;

import java.util.HashMap;
import java.util.Map;

import factory.FoodFactory;
import type.EnumInfo.OrderStateEnum;
import type.EnumInfo.SizeTypeEnum;
import type.employee.Chef;
import type.employee.Server;
import type.food.Burgers;
import type.food.Desserts;
import type.food.Drinks;
import type.food.Salads;
import type.food.Snacks;
import utils.ConstUtils;
import utils.FuncUtils;
import utils.LogUtils;

public class Order {
	private static int ORDER_ID = 1000000;

	private int orderID;
	private Map<Drinks, Integer> drinkMap;
	private Map<Snacks, Integer> snackMap;
	private Map<Desserts, Integer> dessertsMap;
	private Map<Salads, Integer> saladsMap;
	private Map<Burgers, Integer> burgersMap;

	private Map<String, Integer> needsMap;

	private OrderStateEnum state;

	private Server server;
	private Chef chef;

	private int persons;
	private String tableNums;

	private double discount;

	public static void main(String[] args) {
		Map<Drinks, Integer> drinkMap = new HashMap<Drinks, Integer>();
		Map<Snacks, Integer> snackMap = new HashMap<>();
		Map<Desserts, Integer> dessertsMap = new HashMap<>();
		Map<Salads, Integer> saladsMap = new HashMap<>();
		Map<Burgers, Integer> burgersMap = new HashMap<>();

		drinkMap.put(
				FoodFactory.getInstance().makeDrinks(SizeTypeEnum.Large, true,
						0), 2);

		Map<String, Integer> needsMap = FuncUtils.getIngredientNeedsMap(
				drinkMap, snackMap, dessertsMap, saladsMap, burgersMap);

		int persons = 10;
		String tableNums = "85";
		double discount = 0.8;
		Order order = new Order(drinkMap, snackMap, dessertsMap, saladsMap,
				burgersMap, needsMap, persons, discount, tableNums);
		order.setServer(new Server("123", null));

		System.out.println(order.generateBillInfo(true));
	}

	public double getTotal() {
		double feeOfDrinks = .0;
		double feeOfSnacks = .0;
		double feeOfDesserts = .0;
		double feeOfSalads = .0;
		double feeOfBurgers = .0;

		for (Map.Entry<Drinks, Integer> entry : drinkMap.entrySet()) {
			Drinks drink = entry.getKey();
			double price = drink.getPrice() * entry.getValue();
			feeOfDrinks += price;
		}

		for (Map.Entry<Snacks, Integer> entry : snackMap.entrySet()) {
			Snacks snack = entry.getKey();
			double price = snack.getPrice() * entry.getValue();
			feeOfSnacks += price;
		}

		for (Map.Entry<Desserts, Integer> entry : dessertsMap.entrySet()) {
			Desserts dessert = entry.getKey();
			double price = dessert.getPrice() * entry.getValue();
			feeOfDesserts += price;
		}

		for (Map.Entry<Salads, Integer> entry : saladsMap.entrySet()) {
			Salads salad = entry.getKey();
			double price = salad.getPrice() * entry.getValue();
			feeOfSalads += price;
		}

		for (Map.Entry<Burgers, Integer> entry : burgersMap.entrySet()) {
			Burgers burger = entry.getKey();
			double price = burger.getPrice() * entry.getValue();
			feeOfBurgers += price;
		}

		double subTotal = feeOfDrinks + feeOfSnacks + feeOfDesserts
				+ feeOfSalads + feeOfBurgers;

		double gratuity = .0;
		if (persons >= 8) {
			gratuity = subTotal * 0.15;
		}
		double tax = subTotal * 0.13;

		double total = gratuity + tax + subTotal;

		return total;
	}

	public String generateBillInfo(boolean flag) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("RECEIPT").append(ConstUtils.LINE_SEPARATOR);
		sBuffer.append("=========================================").append(
				ConstUtils.LINE_SEPARATOR);
		sBuffer.append("# OrderID: "
				+ String.format(FuncUtils.getFormatString("# OrderID: "),
						orderID) + ConstUtils.LINE_SEPARATOR);
		sBuffer.append("# TableNumber: "
				+ String.format(FuncUtils.getFormatString("# TableNumber: "),
						tableNums) + ConstUtils.LINE_SEPARATOR);
		sBuffer.append("Server#: "
				+ String.format(FuncUtils.getFormatString("Server#: "),
						server.getId()) + ConstUtils.LINE_SEPARATOR);

		sBuffer.append("=========================================").append(
				ConstUtils.LINE_SEPARATOR);
		sBuffer.append("Persons: ")
				.append(String.format(FuncUtils.getFormatString("Persons: "),
						persons + "")).append(ConstUtils.LINE_SEPARATOR);
		sBuffer.append("=========================================").append(
				ConstUtils.LINE_SEPARATOR);

		double feeOfDrinks = .0;
		double feeOfSnacks = .0;
		double feeOfDesserts = .0;
		double feeOfSalads = .0;
		double feeOfBurgers = .0;

		if(!drinkMap.isEmpty()) {
			sBuffer.append("Drinks: " + ConstUtils.LINE_SEPARATOR);
			for (Map.Entry<Drinks, Integer> entry : drinkMap.entrySet()) {
				Drinks drink = entry.getKey();
				double price = drink.getPrice() * entry.getValue();
				feeOfDrinks += price;
				String leftPart = entry.getValue() + " " + drink.getName();
				sBuffer.append(leftPart
						+ String.format(FuncUtils.getFormatString(leftPart),
							String.format("$ %.2f", price))
					+ ConstUtils.LINE_SEPARATOR);
			}
			sBuffer.append(ConstUtils.LINE_SEPARATOR);
		}

		if(!snackMap.isEmpty()) {
			sBuffer.append("Snacks: " + ConstUtils.LINE_SEPARATOR);
			for (Map.Entry<Snacks, Integer> entry : snackMap.entrySet()) {
				Snacks snack = entry.getKey();
				double price = snack.getPrice() * entry.getValue();
				feeOfSnacks += price;
				String leftPart = entry.getValue() + " " + snack.getName();
				sBuffer.append(leftPart
						+ String.format(FuncUtils.getFormatString(leftPart),
								String.format("$ %.2f", price))
						+ ConstUtils.LINE_SEPARATOR);
			}
	
			sBuffer.append(ConstUtils.LINE_SEPARATOR);
		}
		if(!dessertsMap.isEmpty()) {
			sBuffer.append("Desserts: " + ConstUtils.LINE_SEPARATOR);
			for (Map.Entry<Desserts, Integer> entry : dessertsMap.entrySet()) {
				Desserts dessert = entry.getKey();
				double price = dessert.getPrice() * entry.getValue();
				feeOfDesserts += price;
				String leftPart = entry.getValue() + " " + dessert.getName();
				sBuffer.append(leftPart
						+ String.format(FuncUtils.getFormatString(leftPart),
								String.format("$ %.2f", price))
						+ ConstUtils.LINE_SEPARATOR);
			}
	
			sBuffer.append(ConstUtils.LINE_SEPARATOR);
		}
		if(!saladsMap.isEmpty()) {
			sBuffer.append("Salads: " + ConstUtils.LINE_SEPARATOR);
			for (Map.Entry<Salads, Integer> entry : saladsMap.entrySet()) {
				Salads salad = entry.getKey();
				double price = salad.getPrice() * entry.getValue();
				feeOfSalads += price;
				String leftPart = entry.getValue() + " " + salad.getName();
				sBuffer.append(leftPart
						+ String.format(FuncUtils.getFormatString(leftPart),
								String.format("$ %.2f", price))
						+ ConstUtils.LINE_SEPARATOR);
			}
	
			sBuffer.append(ConstUtils.LINE_SEPARATOR);
		}
		if(!burgersMap.isEmpty())
		{
			sBuffer.append("Burgers: " + ConstUtils.LINE_SEPARATOR);
			for (Map.Entry<Burgers, Integer> entry : burgersMap.entrySet()) {
				Burgers burger = entry.getKey();
				double price = burger.getPrice() * entry.getValue();
				feeOfBurgers += price;
				String leftPart = entry.getValue() + " " + burger.getName();
				sBuffer.append(leftPart
						+ String.format(FuncUtils.getFormatString(leftPart),
								String.format("$ %.2f", price))
						+ ConstUtils.LINE_SEPARATOR);
			}
		}

		double subTotal = feeOfDrinks + feeOfSnacks + feeOfDesserts
				+ feeOfSalads + feeOfBurgers;

		double gratuity = .0;
		if (persons >= 8) {
			gratuity = subTotal * 0.15;
		}
		double tax = subTotal * 0.13;

		double tempTotal = gratuity + tax + subTotal;
		double total = tempTotal*discount;
		

		sBuffer.append("=========================================").append(
				ConstUtils.LINE_SEPARATOR);
		sBuffer.append(String.format(FuncUtils.getFormatString(""),
				"SubTotal: " + String.format("$ %.2f", subTotal))
				+ ConstUtils.LINE_SEPARATOR);
		sBuffer.append(String.format(FuncUtils.getFormatString(""), "TAX: "
				+ String.format("$ %.2f", tax))
				+ ConstUtils.LINE_SEPARATOR);
		sBuffer.append(String.format(FuncUtils.getFormatString(""),
				"GRATUITY: " + String.format("$ %.2f", gratuity))
				+ ConstUtils.LINE_SEPARATOR);
		sBuffer.append("=========================================").append(
				ConstUtils.LINE_SEPARATOR);		
		sBuffer.append(String.format(FuncUtils.getFormatString(""), "TOTAL: "
				+ String.format("$ %.2f", tempTotal))
				+ ConstUtils.LINE_SEPARATOR);
		sBuffer.append(String.format(FuncUtils.getFormatString(""), "DISCOUNT: "
				+ String.format("%.2f", discount))
				+ ConstUtils.LINE_SEPARATOR);	
		sBuffer.append(String.format(FuncUtils.getFormatString(""), "AFTER DISCOUNT: "
				+ String.format("$ %.2f", total))
				+ ConstUtils.LINE_SEPARATOR);		
		if (flag) {
			double average = total / persons;
			sBuffer.append(String.format(FuncUtils.getFormatString(""),
					"AVERAGE: " + String.format("$ %.2f", average))
					+ ConstUtils.LINE_SEPARATOR);
		}

		return sBuffer.toString();
	}

	public void removeDrinks(String str) {
		for (Map.Entry<Drinks, Integer> entry : drinkMap.entrySet()) {
			Drinks drink = entry.getKey();
			if (drink.getName().equals(str)) {
				LogUtils.getInstance().log(
						"Drinks " + drink.getName() + " is returned!");
				drinkMap.remove(drink);
				return;
			}
		}
	}

	public void removeSnacks(String str) {
		for (Map.Entry<Snacks, Integer> entry : snackMap.entrySet()) {
			Snacks snack = entry.getKey();
			if (snack.getName().equals(str)) {
				LogUtils.getInstance().log(
						"Snacks " + snack.getName() + " is returned!");
				snackMap.remove(snack);
				return;
			}
		}
	}

	public void removeDesserts(String str) {
		for (Map.Entry<Desserts, Integer> entry : dessertsMap.entrySet()) {
			Desserts dessert = entry.getKey();
			if (dessert.getName().equals(str)) {
				LogUtils.getInstance().log(
						"Desserts " + dessert.getName() + " is returned!");
				dessertsMap.remove(dessert);
				return;
			}
		}
	}

	public void removeSalads(String str) {
		for (Map.Entry<Salads, Integer> entry : saladsMap.entrySet()) {
			Salads salad = entry.getKey();
			if (salad.getName().equals(str)) {
				LogUtils.getInstance().log(
						"Salads " + salad.getName() + " is returned!");
				saladsMap.remove(salad);
				return;
			}
		}
	}

	public void removeBurgers(String str) {
		for (Map.Entry<Burgers, Integer> entry : burgersMap.entrySet()) {
			Burgers burger = entry.getKey();
			if (burger.getName().equals(str)) {
				LogUtils.getInstance().log(
						"Burgers " + burger.getName() + " is returned!");
				burgersMap.remove(burger);
				return;
			}
		}
	}

	public Order(Map<Drinks, Integer> drinkMap, Map<Snacks, Integer> snackMap,
			Map<Desserts, Integer> dessertsMap, Map<Salads, Integer> saladsMap,
			Map<Burgers, Integer> burgersMap, Map<String, Integer> needsMap,
			int persons, double discount, String tableNums) {
		super();
		this.orderID = ORDER_ID++;
		this.drinkMap = drinkMap;
		this.snackMap = snackMap;
		this.dessertsMap = dessertsMap;
		this.saladsMap = saladsMap;
		this.burgersMap = burgersMap;
		this.needsMap = needsMap;
		this.persons = persons;
		this.tableNums = tableNums;
		this.discount=discount;
		state = OrderStateEnum.WAITING;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public String getTableNums() {
		return tableNums;
	}

	public void setTableNums(String tableNums) {
		this.tableNums = tableNums;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Map<Drinks, Integer> getDrinkMap() {
		return drinkMap;
	}

	public void setDrinkMap(Map<Drinks, Integer> drinkMap) {
		this.drinkMap = drinkMap;
	}

	public Map<Snacks, Integer> getSnackMap() {
		return snackMap;
	}

	public void setSnackMap(Map<Snacks, Integer> snackMap) {
		this.snackMap = snackMap;
	}

	public Map<Desserts, Integer> getDessertsMap() {
		return dessertsMap;
	}

	public void setDessertsMap(Map<Desserts, Integer> dessertsMap) {
		this.dessertsMap = dessertsMap;
	}

	public Map<Salads, Integer> getSaladsMap() {
		return saladsMap;
	}

	public void setSaladsMap(Map<Salads, Integer> saladsMap) {
		this.saladsMap = saladsMap;
	}

	public Map<Burgers, Integer> getBurgersMap() {
		return burgersMap;
	}

	public void setBurgersMap(Map<Burgers, Integer> burgersMap) {
		this.burgersMap = burgersMap;
	}

	public OrderStateEnum getState() {
		return state;
	}

	public void setState(OrderStateEnum state) {
		this.state = state;
	}

	public Map<String, Integer> getNeedsMap() {
		return needsMap;
	}

	public void setNeedsMap(Map<String, Integer> needsMap) {
		this.needsMap = needsMap;
	}

	@Override
	public String toString() {
		return orderID + "";
	}
}
