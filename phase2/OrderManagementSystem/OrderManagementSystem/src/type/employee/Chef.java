package type.employee;

import java.util.ArrayList;
import java.util.List;

import type.Order;
import type.Permission;
import type.EnumInfo.EmployeeTypeEnum;
import type.EnumInfo.OrderStateEnum;
import utils.LogUtils;

public class Chef extends Employee {
	private List<Order> waitingList;

	public Chef(String name, Permission permission) {
		super(name, permission);
		waitingList = new ArrayList<Order>();
	}

	public List<Order> getWaitingList() {
		return waitingList;
	}

	public void setWaitingList(List<Order> waitingList) {
		this.waitingList = waitingList;
	}

	@Override
	public Order prepareOrder() {
		Order order = waitingList.remove(0);
		order.setState(OrderStateEnum.SENT);
		LogUtils.getInstance().log(
				"Order " + order.getOrderID() + " is prepared by Chef "
						+ getName());

		return order;
	}

	@Override
	public void addNewOrder(Order order) {
		LogUtils.getInstance().log(
				"Order " + order.getOrderID() + " is assigned to Chef "
						+ getName());
		waitingList.add(order);
		order.setChef(this);
	}

	public EmployeeTypeEnum getEmployeeType() {
		return EmployeeTypeEnum.Chef;
	}

	public int getOrderListSize() {
		return waitingList.size();
	}

}
