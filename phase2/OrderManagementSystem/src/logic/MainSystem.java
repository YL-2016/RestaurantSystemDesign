package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import type.EnumInfo.EmployeeTypeEnum;
import type.Inventory;
import type.Order;
import type.Permission;
import type.employee.Chef;
import type.employee.Employee;
import type.employee.Manager;
import type.employee.Server;
import utils.IOUtils;
import utils.LogUtils;

public class MainSystem {
	private Inventory inventory;
	private Map<Integer, Manager> managerMap;
	private Map<Integer, Chef> chefMap;
	private Map<Integer, Server> serverMap;
	private Map<Integer, Order> waitingOrderMap;
	private Map<Integer, Order> preparedOrderMap;
	private Map<Integer, Order> finishedOrderMap;

	private Employee currentEmployee;
	private int alarmNum;

	private static MainSystem system;

	public static MainSystem getInstance() {
		if (system == null) {
			system = new MainSystem();
		}
		return system;
	}

	public MainSystem() {
	}

	public List<Order> getCurrentFinishedOrderList() {
		Server server = getCurrentServer();
		if (server != null) {
			return server.getFinishedList();
		} else {
			List<Order> list = new ArrayList<Order>();
			for (Map.Entry<Integer, Order> entry : finishedOrderMap.entrySet()) {
				list.add(entry.getValue());
			}
			return list;
		}
	}

	public void prepareOrder(Order order) {
		Chef chef = order.getChef();
		Server server = order.getServer();
		chef.prepareOrder();
		server.toPreparedList(order);
		toPreparedMap(order);
		inventory.reduce(order.getNeedsMap());
	}

	public void finishOrder(Order order) {
		LogUtils.getInstance()
				.log("Order " + order.getOrderID()
						+ " is delivered to the customer");
		Server server = order.getServer();
		server.toFinishedList(order);
		toFinishedMap(order);
		LogUtils.getInstance().log(
				"Bill for Order " + order.getOrderID() + " is created.");
	}

	public Chef getCurrentChef() {
		return chefMap.get(currentEmployee.getId());
	}

	public Server getCurrentServer() {
		return serverMap.get(currentEmployee.getId());
	}

	public void addNewOrder(Order order) {
		currentEmployee.addNewOrder(order);
		addToSuitableChef(order);
		addToWaitingOrderMap(order);
	}

	public void toPreparedList(Order order) {
		currentEmployee.toPreparedList(order);
	}

	public void toFinishedList(Order order) {
		currentEmployee.toFinishedList(order);
	}

	public Order prepareOrder() {
		return currentEmployee.prepareOrder();
	}

	public boolean isEnough(Map<String, Integer> needsMap) {
		return inventory.isEnough(needsMap);
	}

	public void update(Map<String, Integer> needsMap) {
		inventory.update(needsMap);
	}

	public Map<String, Integer> getIngredientMap() {
		return inventory.getIngredientMap();
	}

	public int getAlarmNum() {
		return alarmNum;
	}

	public void setAlarmNum(int value) {
		LogUtils.getInstance().log("Alarm value is set to " + value);
		alarmNum = value;
	}

	public void addNewChef(Chef chef) {
		LogUtils.getInstance().log("New chef " + chef.getName() + " is hired.");
		chefMap.put(chef.getId(), chef);
	}

	public void addNewServer(Server server) {
		LogUtils.getInstance().log(
				"New server " + server.getName() + " is hired.");
		serverMap.put(server.getId(), server);
	}

	public void fireEmployee(Employee employee) {
		LogUtils.getInstance().log(
				"Employee " + employee.getName() + " is fired.");
		int id = employee.getId();
		if (chefMap.containsKey(id)) {
			chefMap.remove(id);
			return;
		} else if (serverMap.containsKey(id)) {
			serverMap.remove(id);
			return;
		}
	}

	public Map<Integer, Chef> getChefMap() {
		return chefMap;
	}

	public Map<Integer, Server> getServerMap() {
		return serverMap;
	}

	public String getCurrentName() {
		return currentEmployee.getName();
	}

	public int getCurrentId() {
		return currentEmployee.getId();
	}

	public String getEmployeeType() {
		return currentEmployee.getEmployeeType().getValue();
	}

	public Permission getCurrentPermission() {
		return currentEmployee.getPermission();
	}

	public boolean login(EmployeeTypeEnum type, int id) {
		switch (type) {
		case MANAGER: {
			currentEmployee = managerMap.get(id);
			break;
		}
		case Chef: {
			currentEmployee = chefMap.get(id);
			break;
		}
		case Server: {
			currentEmployee = serverMap.get(id);
			break;
		}

		default:
			return false;
		}

		if (currentEmployee != null) {
			LogUtils.getInstance().log(
					currentEmployee.getEmployeeType().getValue() + " "
							+ currentEmployee.getName() + "" + " logged in");
			return true;
		} else {
			return false;
		}
	}

	public void initialze() {
		inventory = new Inventory(IOUtils.getInstance().loadInventory());
		managerMap = IOUtils.getInstance().loadManager();
		chefMap = IOUtils.getInstance().loadChef();
		serverMap = IOUtils.getInstance().loadServer();
		alarmNum = 10;
		waitingOrderMap = new HashMap<Integer, Order>();
		preparedOrderMap = new HashMap<Integer, Order>();
		finishedOrderMap = new HashMap<Integer, Order>();
	}

	public void addToWaitingOrderMap(Order order) {
		waitingOrderMap.put(order.getOrderID(), order);
	}

	public void toPreparedMap(Order order) {
		waitingOrderMap.remove(order.getOrderID());
		preparedOrderMap.put(order.getOrderID(), order);
	}

	public void toFinishedMap(Order order) {
		preparedOrderMap.remove(order.getOrderID());
		finishedOrderMap.put(order.getOrderID(), order);
	}

	public void reset() {
		IOUtils.getInstance().initDatabase();
	}

	private void addToSuitableChef(Order order) {
		int id = 0;
		int tasks = Integer.MAX_VALUE;
		for (Map.Entry<Integer, Chef> entry : chefMap.entrySet()) {
			Chef chef = entry.getValue();
			if (chef.getOrderListSize() < tasks) {
				tasks = chef.getOrderListSize();
				id = entry.getKey();
			}
		}
		Chef targetChef = chefMap.get(id);
		targetChef.addNewOrder(order);
	}
}
