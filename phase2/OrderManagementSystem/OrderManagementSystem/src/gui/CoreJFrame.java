package gui;

import gui.panel.BaseInfoPanel;
import gui.panel.BillPanel;
import gui.panel.EmployeePanel;
import gui.panel.InventoryPanel;
import gui.panel.KitchenPanel;
import gui.panel.OrderPanel;
import gui.panel.TabPanelInterface;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.MainSystem;
import type.Permission;
import utils.LogUtils;

public class CoreJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2082519786286679302L;
	private JPanel contentPane;

	private BaseInfoPanel panel_BaseInfo;

	private JTabbedPane tabbedPane;
	private OrderPanel panel_Order;
	private BillPanel panel_Bill;
	private InventoryPanel panel_Inventory;
	private KitchenPanel panel_Kitchen;
	private EmployeePanel panel_Employee;

	public void updateBillPanel() {
		panel_Bill.updateBillPanel();
	}

	public void updateKitchenPanel() {
		panel_Kitchen.updateKitchenPanel();
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

		panel_BaseInfo = new BaseInfoPanel(this);
		contentPane.add(panel_BaseInfo);

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

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Component component = tabbedPane.getSelectedComponent();
				TabPanelInterface tpInterface = (TabPanelInterface) component;
				tpInterface.clearContent();
			}
		});
		tabbedPane.setBounds(10, 52, 864, 500);
		contentPane.add(tabbedPane);

		Permission permission = MainSystem.getInstance().getCurrentPermission();
		panel_Inventory = new InventoryPanel(this);
		if (permission.canUseInventoryPanel()) {
			tabbedPane.addTab("Inventory", null, panel_Inventory, null);
		}

		panel_Order = new OrderPanel(this);
		if (permission.canUseOrderPanel()) {
			tabbedPane.addTab("Order", null, panel_Order, null);
		}

		panel_Bill = new BillPanel(this);
		if (permission.canUseBillPanel()) {
			tabbedPane.addTab("Bill", null, panel_Bill, null);
		}

		panel_Kitchen = new KitchenPanel(this);
		if (permission.canUseKitchenPanel()) {
			tabbedPane.addTab("Kitchen", null, panel_Kitchen, null);
		}

		panel_Employee = new EmployeePanel(this);
		if (permission.canUseEmployeePanel()) {
			tabbedPane.addTab("Employee", null, panel_Employee, null);
		}
	}

}
