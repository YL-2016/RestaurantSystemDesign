package logic;

import java.util.List;
import java.util.Map;

import logic.subsys.AccountSystem;
import logic.subsys.AlarmSystem;
import logic.subsys.InventorySystem;
import logic.subsys.OrderSystem;
import type.EnumInfo.EmployeeTypeEnum;
import type.Order;
import type.Permission;
import type.employee.Chef;
import type.employee.Employee;
import type.employee.Server;
import utils.IOUtils;
import utils.LogUtils;

public class MainSystem {
	private InventorySystem inventorySystem;
	private AccountSystem accountSystem;
	private AlarmSystem alarmSystem;
	private OrderSystem orderSystem;

	private static MainSystem system;

	public static MainSystem getInstance() {
		if (system == null) {
			system = new MainSystem();
		}

		return system;
	}

	public boolean canPlaceAnotherOrder() {
		Employee currentEmployee = accountSystem.getCurrentEmployee();
		return currentEmployee.canPlaceAnotherOrder();
	}

	public List<Order> getCurrentFinishedOrderList() {
		Server server = getCurrentServer();
		return orderSystem.getCurrentFinishedOrderList(server);
	}

	public List<Order> getCurrentWaitingOrderList(){
		Chef chef = getCurrentChef();
		return orderSystem.getCurrentWaitingOrderList(chef);
	}

	public List<Order> getCurrentPreparedOrderList(){
		Server server = getCurrentServer();
		return orderSystem.getCurrentPreparedOrderList(server);
	}


	public void prepareOrder(Order order) {
		Chef chef = order.getChef();
		Server server = order.getServer();
		chef.prepareOrder();
		server.toPreparedList(order);
		toPreparedMap(order);
		inventorySystem.reduce(order.getNeedsMap());

		LogUtils.getInstance().log(
				"Order " + order.getOrderID() + " is prepared by Chef "
						+ chef.getName());
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
		return accountSystem.getCurrentChef();
	}

	public Server getCurrentServer() {
		return accountSystem.getCurrentServer();
	}

	public void addNewOrder(Order order) {
		accountSystem.getCurrentEmployee().addNewOrder(order);
		Map<Integer, Chef> chefMap = accountSystem.getChefMap();
		orderSystem.addToSuitableChef(chefMap, order);
		addToWaitingOrderMap(order);
	}

	public void toPreparedList(Order order) {
		accountSystem.getCurrentEmployee().toPreparedList(order);
	}

	public void toFinishedList(Order order) {
		accountSystem.getCurrentEmployee().toFinishedList(order);
	}

	public Order prepareOrder() {
		return accountSystem.getCurrentEmployee().prepareOrder();
	}

	public boolean isEnough(Map<String, Integer> needsMap) {
		return inventorySystem.isEnough(needsMap);
	}

	public void update(Map<String, Integer> needsMap) {
		inventorySystem.update(needsMap);
	}

	public Map<String, Integer> getIngredientMap() {
		return inventorySystem.getIngredientMap();
	}

	public int getAlarmNum() {
		return alarmSystem.getAlarmNum();
	}

	public void setAlarmNum(int value) {
		alarmSystem.setAlarmNum(value);
	}

	public void addNewChef(Chef chef) {
		accountSystem.addNewChef(chef);
	}

	public void addNewServer(Server server) {
		accountSystem.addNewServer(server);
	}

	public void fireEmployee(Employee employee) {
		accountSystem.fireEmployee(employee);
	}

	public Map<Integer, Chef> getChefMap() {
		return accountSystem.getChefMap();
	}

	public Map<Integer, Server> getServerMap() {
		return accountSystem.getServerMap();
	}

	public String getCurrentName() {
		return accountSystem.getCurrentName();
	}

	public int getCurrentId() {
		return accountSystem.getCurrentId();
	}

	public String getEmployeeType() {
		return accountSystem.getEmployeeType();
	}

	public Permission getCurrentPermission() {
		return accountSystem.getCurrentPermission();
	}

	public void initialize() {
		accountSystem = new AccountSystem();
		inventorySystem = new InventorySystem();
		alarmSystem = new AlarmSystem();
		orderSystem = new OrderSystem();
	}

	public void addToWaitingOrderMap(Order order) {
		orderSystem.addToWaitingOrderMap(order);
	}

	public void toPreparedMap(Order order) {
		orderSystem.toPreparedMap(order);
	}

	public void toFinishedMap(Order order) {
		orderSystem.toFinishedMap(order);
	}

	public void reset() {
		IOUtils.getInstance().initDatabase();
	}

	public boolean login(EmployeeTypeEnum employeeTypeEnum, int id) {
		return accountSystem.login(employeeTypeEnum, id);
	}

}
