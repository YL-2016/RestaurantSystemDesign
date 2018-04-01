package gui;

import factory.EmployeeFactory;
import gui.panel.FoodPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import logic.MainSystem;
import type.Order;
import type.Permission;
import type.employee.Chef;
import type.employee.Employee;
import type.employee.Server;
import utils.LogUtils;

public class CoreJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2082519786286679302L;
	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_Type;
	private JTabbedPane tabbedPane;
	private DefaultListModel<Employee> employeeDlm;
	private JList<Employee> list_Employee;
	private JTable table_Ingredient;
	private DefaultTableModel ingredientDtm;
	private JTextField textField_Alarm;
	private FoodPanel panel_FoodOrder;
	private DefaultListModel<Order> waitingOrderDLM; // Order
	private DefaultListModel<Order> preparedOrderDLM; // Order
	private DefaultListModel<Order> kitchenOrderDLM; // Kitchen
	private DefaultListModel<Order> billOrderDLM; // Bill
	private JList list_Waiting;
	private JList list_Prepared;
	private FoodPanel panel_OrderInKitchen;
	private JList list_kitchen;
	private JTextArea textArea_Bill;
	private JList list_Bill;

	public void updateBillPanel() {
		List<Order> list = MainSystem.getInstance()
				.getCurrentFinishedOrderList();
		billOrderDLM.clear();
		for (Order order : list) {
			billOrderDLM.addElement(order);
		}
	}

	public void updateKitchenPanel() {
		Chef chef = MainSystem.getInstance().getCurrentChef();
		if (chef != null) {
			List<Order> waitingList = chef.getWaitingList();
			kitchenOrderDLM.clear();
			for (int i = 0; i < waitingList.size(); i++) {
				kitchenOrderDLM.addElement(waitingList.get(i));
			}
		}
	}

	public void updateOrderPanel() {
		Server server = MainSystem.getInstance().getCurrentServer();
		if (server != null) {
			List<Order> waitingList = server.getWaitingList();
			List<Order> preparedList = server.getPreparedList();

			waitingOrderDLM.clear();
			preparedOrderDLM.clear();
			for (int i = 0; i < waitingList.size(); i++) {
				waitingOrderDLM.addElement(waitingList.get(i));
			}
			for (int i = 0; i < preparedList.size(); i++) {
				preparedOrderDLM.addElement(preparedList.get(i));
			}
		}
	}

	private void init() {
		textField_ID.setText(MainSystem.getInstance().getCurrentId() + "");
		textField_Name.setText(MainSystem.getInstance().getCurrentName() + "");
		textField_Type.setText(MainSystem.getInstance().getEmployeeType() + "");
	}

	/**
	 * Create the frame.
	 */
	public CoreJFrame() {
		setTitle("System Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(400, 0, 474, 42);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 13, 18, 15);
		panel.add(lblNewLabel);

		textField_ID = new JTextField();
		textField_ID.setBounds(38, 10, 82, 21);
		panel.add(textField_ID);
		textField_ID.setEditable(false);
		textField_ID.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(210, 10, 88, 21);
		panel.add(textField_Name);
		textField_Name.setEditable(false);
		textField_Name.setColumns(10);

		JLabel label = new JLabel("Name:");
		label.setBounds(155, 13, 45, 15);
		panel.add(label);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(342, 13, 30, 15);
		panel.add(lblType);

		textField_Type = new JTextField();
		textField_Type.setBounds(382, 10, 82, 21);
		panel.add(textField_Type);
		textField_Type.setEditable(false);
		textField_Type.setColumns(10);

		JButton btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginJFrame.getInstance().setVisible(true);
				LogUtils.getInstance().log(
						"Employee ID: "
								+ MainSystem.getInstance().getCurrentId()
								+ " Name: "
								+ MainSystem.getInstance().getCurrentName()
								+ "" + " logged out");
				dispose();
			}
		});
		btnLogout.setBounds(10, 8, 93, 23);
		contentPane.add(btnLogout);

		Permission permission = MainSystem.getInstance().getCurrentPermission();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 52, 864, 500);
		contentPane.add(tabbedPane);

		JPanel panel_Inventory = new JPanel();
		if (permission.canUseInventoryPanel()) {
			tabbedPane.addTab("Inventory", null, panel_Inventory, null);
		}
		panel_Inventory.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_Inventory.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		ingredientDtm = getIngredientTableModel();
		table_Ingredient = new JTable(ingredientDtm) {
			private static final long serialVersionUID = -300348443048090716L;

			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		table_Ingredient.setBounds(10, 10, 511, 373);
		panel_1.add(table_Ingredient);

		JPanel panel_2 = new JPanel();
		panel_Inventory.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		int alarmNum = MainSystem.getInstance().getAlarmNum();
		textField_Alarm = new JTextField();
		textField_Alarm.setText(alarmNum + "");
		textField_Alarm.setEditable(false);
		panel_2.add(textField_Alarm);
		textField_Alarm.setColumns(10);

		JButton btn_Alarm = new JButton("AlarmValue");
		btn_Alarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Enter the new alarm num: ", "Tips",
						JOptionPane.INFORMATION_MESSAGE);
				try {
					int value = Integer.parseInt(input.trim());
					textField_Alarm.setText(value + "");
					MainSystem.getInstance().setAlarmNum(value);
					showAlarm();
				} catch (NumberFormatException ne) {

				}
			}
		});
		panel_2.add(btn_Alarm);

		JPanel panel_Order = new JPanel();
		if (permission.canUseOrderPanel()) {
			tabbedPane.addTab("Order", null, panel_Order, null);
		}
		panel_Order.setLayout(null);

		waitingOrderDLM = new DefaultListModel<>();
		list_Waiting = new JList(waitingOrderDLM);
		list_Waiting.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Order order = waitingOrderDLM.get(list_Waiting
						.getSelectedIndex());
				panel_FoodOrder.enterDisplayMode(order, false);
			}
		});
		list_Waiting.setBounds(10, 41, 71, 161);
		panel_Order.add(list_Waiting);

		JLabel lblNewLabel_1 = new JLabel("Waiting");
		lblNewLabel_1.setBounds(10, 16, 54, 15);
		panel_Order.add(lblNewLabel_1);

		JLabel lblPrepared = new JLabel("Prepared");
		lblPrepared.setBounds(10, 212, 54, 15);
		panel_Order.add(lblPrepared);

		preparedOrderDLM = new DefaultListModel<>();
		list_Prepared = new JList(preparedOrderDLM);
		list_Prepared.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = list_Prepared.getSelectedIndex();
				if (index != -1) {
					Order order = preparedOrderDLM.get(index);
					panel_FoodOrder.enterDisplayMode(order, true);
				}
			}
		});
		list_Prepared.setBounds(10, 237, 71, 155);
		panel_Order.add(list_Prepared);

		panel_FoodOrder = new FoodPanel();
		panel_FoodOrder.setBounds(91, 16, 758, 342);
		panel_Order.add(panel_FoodOrder);

		JButton btn_CreateOrder = new JButton("Create Order");
		btn_CreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryMakeNewOrder();
			}
		});
		btn_CreateOrder.setBounds(416, 380, 123, 23);
		panel_Order.add(btn_CreateOrder);

		JButton btn_MakeBill = new JButton("Make Bill");
		btn_MakeBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_Prepared.getSelectedIndex();
				if (index != -1) {
					Order order = preparedOrderDLM.get(index);
					panel_FoodOrder.checkOrder(order);
					MainSystem.getInstance().finishOrder(order);
					preparedOrderDLM.remove(index);
					updateBillPanel();
					JOptionPane.showMessageDialog(null, "Bill has printed",
							"Tips", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_MakeBill.setBounds(563, 380, 123, 23);
		panel_Order.add(btn_MakeBill);

		JButton btn_NewOrder = new JButton("New Order");
		btn_NewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_FoodOrder.enterNewOrderMode();
			}
		});
		btn_NewOrder.setBounds(270, 380, 123, 23);
		panel_Order.add(btn_NewOrder);

		JPanel panel_Bill = new JPanel();
		if (permission.canUseBillPanel()) {
			tabbedPane.addTab("Bill", null, panel_Bill, null);
		}
		panel_Bill.setLayout(null);

		billOrderDLM = new DefaultListModel<>();
		list_Bill = new JList(billOrderDLM);
		list_Bill.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = list_Bill.getSelectedIndex();
				if (index != -1) {
					Order order = billOrderDLM.get(index);
					String billString = order.generateBillInfo();
					textArea_Bill.setText(billString);
				}
			}
		});
		list_Bill.setBounds(10, 35, 71, 251);
		panel_Bill.add(list_Bill);

		JLabel lblFinished = new JLabel("Finished");
		lblFinished.setBounds(10, 10, 54, 15);
		panel_Bill.add(lblFinished);

		textArea_Bill = new JTextArea();
		textArea_Bill.setBounds(95, 10, 754, 451);
		textArea_Bill.setEditable(false);
		panel_Bill.add(textArea_Bill);

		JPanel panel_Kitchen = new JPanel();
		if (permission.canUseKitchenPanel()) {
			tabbedPane.addTab("Kitchen", null, panel_Kitchen, null);
		}
		panel_Kitchen.setLayout(null);

		kitchenOrderDLM = new DefaultListModel<>();
		list_kitchen = new JList(kitchenOrderDLM);
		list_kitchen.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = list_kitchen.getSelectedIndex();
				if (index != -1) {
					Order order = kitchenOrderDLM.get(index);
					panel_OrderInKitchen.enterDisplayMode(order, false);
				}
			}
		});
		list_kitchen.setBounds(10, 35, 71, 161);
		panel_Kitchen.add(list_kitchen);

		JLabel label_1 = new JLabel("Waiting");
		label_1.setBounds(10, 10, 54, 15);
		panel_Kitchen.add(label_1);

		panel_OrderInKitchen = new FoodPanel();
		panel_OrderInKitchen.setBounds(101, 10, 758, 342);
		panel_Kitchen.add(panel_OrderInKitchen);

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_kitchen.getSelectedIndex();
				if (index == 0) {
					Order order = kitchenOrderDLM.get(index);
					if (MainSystem.getInstance().isEnough(order.getNeedsMap())) {
						MainSystem.getInstance().prepareOrder(order);
						kitchenOrderDLM.remove(0);
						JOptionPane.showMessageDialog(null,
								"Order is prepared!", "Tips",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Ingredient is not enough!", "Tips",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"You can only start from first order", "Tips",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFinish.setBounds(101, 385, 123, 23);
		panel_Kitchen.add(btnFinish);

		JPanel panel_Employee = new JPanel();
		if (permission.canUseEmployeePanel()) {
			tabbedPane.addTab("Employee", null, panel_Employee, null);
		}
		panel_Employee.setLayout(null);

		employeeDlm = getEmployeeListModel();
		list_Employee = new JList<Employee>(employeeDlm);
		JScrollPane jsp = new JScrollPane(list_Employee);
		jsp.setBounds(0, 0, 344, 461);
		panel_Employee.add(jsp);

		list_Employee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnDel = new JButton("Fire");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_Employee.getSelectedIndex();
				if (index == -1) {
					JOptionPane.showMessageDialog(null,
							"Please choose an employee.", "Tips",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					Employee employee = employeeDlm.remove(index);
					MainSystem.getInstance().fireEmployee(employee);
				}
			}
		});
		btnDel.setBounds(354, 87, 99, 23);
		panel_Employee.add(btnDel);

		JButton btnHireChef = new JButton("Hire Chef");
		btnHireChef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = getNewEmployeeName();
				if (name != null && !name.equals("")) {
					Chef newChef = EmployeeFactory.getInstance().createChef(
							name);
					MainSystem.getInstance().addNewChef(newChef);
					employeeDlm.addElement(newChef);
					JOptionPane.showMessageDialog(null, "New chef is added",
							"Tips", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnHireChef.setBounds(354, 10, 99, 23);
		panel_Employee.add(btnHireChef);

		JButton btnHireServer = new JButton("Hire Server");
		btnHireServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = getNewEmployeeName();
				if (name != null && !name.equals("")) {
					Server newServer = EmployeeFactory.getInstance()
							.createServer(name);
					MainSystem.getInstance().addNewServer(newServer);
					employeeDlm.addElement(newServer);
					JOptionPane.showMessageDialog(null, "New server is added",
							"Tips", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnHireServer.setBounds(354, 49, 99, 23);
		panel_Employee.add(btnHireServer);

		init();
		showAlarm();
		updateOrderPanel();
		updateKitchenPanel();
		updateBillPanel();
	}

	private String getNewEmployeeName() {
		return JOptionPane.showInputDialog(null, "Enter name of new employee",
				"Tips", JOptionPane.INFORMATION_MESSAGE);
	}

	private DefaultListModel<Employee> getEmployeeListModel() {
		DefaultListModel<Employee> dlm = new DefaultListModel<Employee>();
		Map<Integer, Chef> chefMap = MainSystem.getInstance().getChefMap();
		Map<Integer, Server> serverMap = MainSystem.getInstance()
				.getServerMap();
		for (Map.Entry<Integer, Chef> entry : chefMap.entrySet()) {
			dlm.addElement(entry.getValue());
		}
		for (Map.Entry<Integer, Server> entry : serverMap.entrySet()) {
			dlm.addElement(entry.getValue());
		}

		return dlm;
	}

	private DefaultTableModel getIngredientTableModel() {
		DefaultTableModel dtm = new DefaultTableModel(createTableModelData(),
				createColumnNames());

		dtm.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int rowIdx = e.getLastRow();
					String title = (String) table_Ingredient.getValueAt(rowIdx,
							0);
					Integer integer = Integer.parseInt(table_Ingredient
							.getValueAt(rowIdx, 1).toString());
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put(title, integer);
					MainSystem.getInstance().update(map);
					showAlarm();
				}
			}
		});

		return dtm;
	}

	private Vector createTableModelData() {
		Vector data = new Vector();
		Map<String, Integer> ingredientMap = MainSystem.getInstance()
				.getIngredientMap();
		for (Map.Entry<String, Integer> entry : ingredientMap.entrySet()) {
			String title = entry.getKey();
			Integer integer = entry.getValue();
			Vector rowData = new Vector();
			rowData.add(title);
			rowData.add(integer);
			data.add(rowData);
		}

		return data;
	}

	private Vector createColumnNames() {
		Vector columnNames = new Vector();
		columnNames.add("Ingredient");
		columnNames.add("Count");

		return columnNames;
	}

	private void showAlarm() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
				Integer integer = Integer.parseInt(table_Ingredient.getValueAt(
						row, 1).toString());
				int alarm = MainSystem.getInstance().getAlarmNum();
				if (integer <= alarm) {
					setBackground(Color.red);
				} else {
					setBackground(Color.white);
				}
				return c;
			}
		};

		TableColumn tc = table_Ingredient.getColumn("Count");
		tc.setCellRenderer(dtcr);
		table_Ingredient.repaint();
	}

	private void tryMakeNewOrder() {
		String string = JOptionPane.showInputDialog(null, "How many persons?",
				"Tips", JOptionPane.INFORMATION_MESSAGE);
		int persons = Integer.parseInt(string.trim());
		Order order = panel_FoodOrder.tryToMakeNewOrder(persons);
		if (order != null) {
			waitingOrderDLM.addElement(order);
			MainSystem.getInstance().addNewOrder(order);
			JOptionPane.showMessageDialog(null, "New order is maded", "Tips",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Not enough ingredients!",
					"Tips", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
