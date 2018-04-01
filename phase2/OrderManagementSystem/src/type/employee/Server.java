package type.employee;

import java.util.ArrayList;
import java.util.List;

import type.EnumInfo.OrderStateEnum;
import type.Order;
import type.Permission;
import type.EnumInfo.EmployeeTypeEnum;
import utils.LogUtils;

public class Server extends Employee {
	private List<Order> waitingList;
	private List<Order> preparedList;
	private List<Order> finishedList;

	public Server(String name, Permission permission) {
		super(name, permission);
		waitingList = new ArrayList<Order>();
		preparedList = new ArrayList<Order>();
		finishedList = new ArrayList<Order>();
	}

	public List<Order> getWaitingList() {
		return waitingList;
	}

	public void setWaitingList(List<Order> waitingList) {
		this.waitingList = waitingList;
	}

	public List<Order> getPreparedList() {
		return preparedList;
	}

	public void setPreparedList(List<Order> preparedList) {
		this.preparedList = preparedList;
	}

	public List<Order> getFinishedList() {
		return finishedList;
	}

	public void setFinishedList(List<Order> finishedList) {
		this.finishedList = finishedList;
	}

	@Override
	public void addNewOrder(Order order) {
		LogUtils.getInstance().log(
				"New order " + order.getOrderID() + " is created by " + name);
		waitingList.add(order);
		order.setServer(this);
	}

	@Override
	public void toPreparedList(Order order) {
		waitingList.remove(order);
		preparedList.add(order);
	}

	@Override
	public void toFinishedList(Order order) {
		preparedList.remove(order);
		finishedList.add(order);
		order.setState(OrderStateEnum.FINISHED);
	}

	public EmployeeTypeEnum getEmployeeType() {
		return EmployeeTypeEnum.Server;
	}

}
