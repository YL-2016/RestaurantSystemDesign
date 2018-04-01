package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import logic.MainSystem;
import type.Order;
import type.employee.Chef;
import type.employee.Employee;
import type.employee.Manager;
import type.employee.Server;
import factory.EmployeeFactory;

public class IOUtils {
	private static IOUtils fileUtil = null;

	public static IOUtils getInstance() {
		if (fileUtil == null) {
			fileUtil = new IOUtils();
		}

		return fileUtil;
	}

	public void printAllReceiptInfo() {
		LogUtils.getInstance().log("printAllReceiptInfo start");
		List<Order> orderList = MainSystem.getInstance()
				.getCurrentFinishedOrderList();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String filePath = ConstUtils.ORDER_FILE_PATH + "/"
				+ dateFormat.format(date) + ".txt";
		try {
			FileWriter fw = new FileWriter(new File(filePath), false);
			fw.write("Finished Order List: " + ConstUtils.LINE_SEPARATOR);
			double total = .0;
			for (int i = 0; i < orderList.size(); i++) {
				fw.write(orderList.get(i).generateBillInfo(false));
				fw.write(ConstUtils.LINE_SEPARATOR);
				total += orderList.get(i).getTotal();
			}
			fw.write("Summary: " + ConstUtils.LINE_SEPARATOR);
			fw.write("Total Income: "
					+ String.format(
							FuncUtils.getFormatString("Total Income: "),
							String.format("$ %.2f", total))
					+ ConstUtils.LINE_SEPARATOR);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LogUtils.getInstance().log("printAllReceiptInfo finished");
	}

	public void printReceipt(int orderID, String receiptContent) {
		LogUtils.getInstance().log("printReceipt start");
		String filePath = ConstUtils.ORDER_FILE_PATH + "/" + orderID + ".txt";
		try {
			FileWriter fw = new FileWriter(new File(filePath), false);
			fw.write(receiptContent);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		LogUtils.getInstance().log("printReceipt finished");
	}

	public Map<Integer, Order> loadWaitingOrderMap() {
		return new HashMap<Integer, Order>();
	}

	public Map<Integer, Order> loadPreparedOrderMap() {
		return new HashMap<Integer, Order>();
	}

	public Map<Integer, Order> loadFinishedOrderMap() {
		return new HashMap<Integer, Order>();
	}

	public Map<String, Integer> loadInventory() {
		LogUtils.getInstance().log("loadInventory start");
		Map<String, Integer> map = new HashMap<String, Integer>();
		Iterator<String> it = ConstUtils.INGREDIENT_SETS.iterator();
		while (it.hasNext()) {
			String str = it.next();
			String filePath = ConstUtils.INGREDIENT_FILE_PATH + "/" + str;
			File file = new File(filePath);
			try {
				BufferedReader bf = new BufferedReader(new FileReader(file));
				int count = Integer.parseInt(bf.readLine().trim());
				map.put(str, count);
				bf.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LogUtils.getInstance().log("loadInventory finished");

		return map;
	}

	public Map<Integer, Manager> loadManager() {
		Map<Integer, Manager> map = new HashMap<>();
		map.put(Employee.EMPLOYEE_ID, EmployeeFactory.getInstance()
				.createManager("James"));

		return map;
	}

	public Map<Integer, Chef> loadChef() {
		Map<Integer, Chef> map = new HashMap<>();
		map.put(Employee.EMPLOYEE_ID,
				EmployeeFactory.getInstance().createChef("Howard"));

		return map;
	}

	public Map<Integer, Server> loadServer() {
		Map<Integer, Server> map = new HashMap<>();
		map.put(Employee.EMPLOYEE_ID, EmployeeFactory.getInstance()
				.createServer("Dorothy"));

		return map;
	}

	public int loadAlarmNum() {
		return 10;
	}

	public boolean updateInventory(Map<String, Integer> map) {
		LogUtils.getInstance().log("updateInventory start");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String str = entry.getKey();
			String filePath = ConstUtils.INGREDIENT_FILE_PATH + "/" + str;
			try {
				FileWriter fw = new FileWriter(new File(filePath), false);
				fw.write(entry.getValue() + "");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		LogUtils.getInstance().log("updateInventory finished");
		return true;
	}

	public void initDatabase() {
		createSystemLog();
		createIngredientsTable();
	}

	private void createSystemLog() {
		File file = new File(ConstUtils.LOG_FILE);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void createIngredientsTable() {
		LogUtils.getInstance().log("createIngredientsTable start");
		Iterator<String> it = ConstUtils.INGREDIENT_SETS.iterator();
		while (it.hasNext()) {
			String str = it.next();
			String filePath = ConstUtils.INGREDIENT_FILE_PATH + "/" + str;
			File file = new File(filePath);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				FileWriter fw = new FileWriter(file, false);
				fw.write("100");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LogUtils.getInstance().log("createIngredientsTable finished");
	}

}
