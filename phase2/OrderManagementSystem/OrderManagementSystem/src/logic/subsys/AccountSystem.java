package logic.subsys;

import java.util.Map;

import type.Order;
import type.Permission;
import type.EnumInfo.EmployeeTypeEnum;
import type.employee.Chef;
import type.employee.Employee;
import type.employee.Manager;
import type.employee.Server;
import utils.IOUtils;
import utils.LogUtils;

public class AccountSystem {

	private Map<Integer, Manager> managerMap;
	private Map<Integer, Chef> chefMap;
	private Map<Integer, Server> serverMap;
	private Employee currentEmployee;
	
	public AccountSystem(){
		initialze();
	}
	
	public Employee getCurrentEmployee(){
		return currentEmployee;
	}
	
	private void initialze() {
		managerMap = IOUtils.getInstance().loadManager();
		chefMap = IOUtils.getInstance().loadChef();
		serverMap = IOUtils.getInstance().loadServer();
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
	
	public Chef getCurrentChef() {
		return chefMap.get(currentEmployee.getId());
	}

	public Server getCurrentServer() {
		return serverMap.get(currentEmployee.getId());
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
	
	public void addNewChef(Chef chef) {
		LogUtils.getInstance().log("New chef " + chef.getName() + " is hired.");
		chefMap.put(chef.getId(), chef);
	}

	public void addNewServer(Server server) {
		LogUtils.getInstance().log(
				"New server " + server.getName() + " is hired.");
		serverMap.put(server.getId(), server);
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
}
