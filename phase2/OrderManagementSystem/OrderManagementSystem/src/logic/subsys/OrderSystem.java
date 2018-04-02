package logic.subsys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import type.Order;
import type.employee.Chef;
import type.employee.Server;
import utils.IOUtils;

public class OrderSystem {
	private Map<Integer, Order> waitingOrderMap;
	private Map<Integer, Order> preparedOrderMap;
	private Map<Integer, Order> finishedOrderMap;

	public OrderSystem() {
		initialze();
	}

	private void initialze() {
		waitingOrderMap = IOUtils.getInstance().loadWaitingOrderMap();
		preparedOrderMap = IOUtils.getInstance().loadPreparedOrderMap();
		finishedOrderMap = IOUtils.getInstance().loadFinishedOrderMap();
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

	public void addToSuitableChef(Map<Integer, Chef> chefMap, Order order) {
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

	public List<Order> getCurrentFinishedOrderList(Server server) {
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

	public List<Order> getCurrentWaitingOrderList(Chef chef) {
		if (chef != null) {
			return chef.getWaitingList();
		} else {
			List<Order> list = new ArrayList<>();
			for (Map.Entry<Integer, Order> entry : waitingOrderMap.entrySet()) {
				list.add(entry.getValue());
			}
			return list;
		}
	}

	public List<Order> getCurrentPreparedOrderList(Server server){
		if (server != null){
			return server.getPreparedList();
		} else{
			List<Order> list = new ArrayList<>();
			for (Map.Entry<Integer, Order> entry : preparedOrderMap.entrySet()){
				list.add(entry.getValue());
			}
			return list;
		}
	}


}