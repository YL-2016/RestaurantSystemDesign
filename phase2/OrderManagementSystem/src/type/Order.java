package type;

import java.util.Map;

import type.EnumInfo.OrderStateEnum;
import type.employee.Chef;
import type.employee.Server;
import type.food.Burgers;
import type.food.Desserts;
import type.food.Drinks;
import type.food.Salads;
import type.food.Snacks;
import utils.ConstUtils;
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

	public String generateBillInfo() {
		double feeOfDrinks = .0;
		double feeOfSnacks = .0;
		double feeOfDesserts = .0;
		double feeOfSalads = .0;
		double feeOfBurgers = .0;
		for (Map.Entry<Drinks, Integer> entry : drinkMap.entrySet()) {
			Drinks drink = entry.getKey();
			feeOfDrinks += drink.getPrice() * entry.getValue();
		}

		for (Map.Entry<Snacks, Integer> entry : snackMap.entrySet()) {
			Snacks snack = entry.getKey();
			feeOfSnacks += snack.getPrice() * entry.getValue();
		}

		for (Map.Entry<Desserts, Integer> entry : dessertsMap.entrySet()) {
			Desserts dessert = entry.getKey();
			feeOfDesserts += dessert.getPrice() * entry.getValue();
		}

		for (Map.Entry<Salads, Integer> entry : saladsMap.entrySet()) {
			Salads salad = entry.getKey();
			feeOfSalads += salad.getPrice() * entry.getValue();
		}

		for (Map.Entry<Burgers, Integer> entry : burgersMap.entrySet()) {
			Burgers burger = entry.getKey();
			feeOfBurgers += burger.getPrice() * entry.getValue();
		}

		double totalFee = feeOfDrinks + feeOfSnacks + feeOfDesserts
				+ feeOfSalads + feeOfBurgers;

		double gratuity = .0;
		if (persons >= 8) {
			gratuity = totalFee * 0.15;
		}
		double tax = totalFee * 0.13;

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Recept").append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("=========================================")
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Persons: ").append(persons)
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("=========================================")
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Drinks: ")
				.append(String.format("%.2f", feeOfDrinks))
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Snacks: ")
				.append(String.format("%.2f", feeOfSnacks))
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Desserts: ")
				.append(String.format("%.2f", feeOfSnacks))
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Salads: ")
				.append(String.format("%.2f", feeOfSalads))
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Burgers: ")
				.append(String.format("%.2f", feeOfBurgers))
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("=========================================")
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Tax: ").append(String.format("%.2f", tax))
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Gratuity: ")
				.append(String.format("%.2f", gratuity))
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("=========================================")
				.append(ConstUtils.LINE_SEPARATOR);
		stringBuffer.append("Total: ")
				.append(String.format("%.2f", gratuity + tax + totalFee))
				.append(ConstUtils.LINE_SEPARATOR);

		return stringBuffer.toString();
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
			int persons) {
		super();
		this.orderID = ORDER_ID++;
		this.drinkMap = drinkMap;
		this.snackMap = snackMap;
		this.dessertsMap = dessertsMap;
		this.saladsMap = saladsMap;
		this.burgersMap = burgersMap;
		this.needsMap = needsMap;
		this.persons = persons;
		state = OrderStateEnum.WAITING;
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
