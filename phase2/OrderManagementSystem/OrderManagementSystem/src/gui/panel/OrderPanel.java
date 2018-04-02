package gui.panel;

import gui.CoreJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.MainSystem;
import type.Order;
import type.employee.Server;

public class OrderPanel extends JPanel implements TabPanelInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1748532439042705857L;

	private JList list_Waiting;
	private JList list_Prepared;
	private DefaultListModel<Order> waitingOrderDLM; // Order
	private DefaultListModel<Order> preparedOrderDLM; // Order
	private FoodPanel panel_FoodOrder;
	private CoreJFrame jframe;

	public OrderPanel(CoreJFrame coreJframe) {
		this.jframe = coreJframe;
		setLayout(null);

		waitingOrderDLM = new DefaultListModel<>();
		list_Waiting = new JList(waitingOrderDLM);
		list_Waiting.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Order order = waitingOrderDLM.get(list_Waiting
						.getSelectedIndex());
				panel_FoodOrder.enterDisplayMode(order, true);
			}
		});
		list_Waiting.setBounds(10, 41, 71, 161);
		add(list_Waiting);

		JLabel lblNewLabel_1 = new JLabel("Waiting");
		lblNewLabel_1.setBounds(10, 16, 54, 15);
		add(lblNewLabel_1);

		JLabel lblPrepared = new JLabel("Prepared");
		lblPrepared.setBounds(10, 212, 54, 15);
		add(lblPrepared);

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
		add(list_Prepared);

		panel_FoodOrder = new FoodPanel();
		panel_FoodOrder.setBounds(91, 0, 758, 399);
		add(panel_FoodOrder);

		JButton btn_CreateOrder = new JButton("Create Order");
		btn_CreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MainSystem.getInstance().canPlaceAnotherOrder()) {
					tryMakeNewOrder();
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"You have not yet delivered an order that is ready for delivery",
									"Tips", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_CreateOrder.setBounds(413, 409, 123, 23);
		add(btn_CreateOrder);

		JButton btn_MakeBill = new JButton("Make Bill");
		btn_MakeBill.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int index = list_Prepared.getSelectedIndex();
				if (index != -1) {
					Order order = preparedOrderDLM.get(index);
					panel_FoodOrder.checkOrder(order);
					MainSystem.getInstance().finishOrder(order);
					preparedOrderDLM.remove(index);
					jframe.updateBillPanel();
					JOptionPane.showMessageDialog(null, "Bill has generated",
							"Tips", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Please select a prepared order",
							"Tips", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_MakeBill.setBounds(560, 409, 123, 23);
		add(btn_MakeBill);

		JButton btn_NewOrder = new JButton("New Order");
		btn_NewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MainSystem.getInstance().canPlaceAnotherOrder()) {
					panel_FoodOrder.enterNewOrderMode();
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"You have not yet delivered an order that is ready for delivery",
									"Tips", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_NewOrder.setBounds(267, 409, 123, 23);
		add(btn_NewOrder);
		updateOrderPanel();
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

	private void tryMakeNewOrder() {
		Order order = panel_FoodOrder.tryToMakeNewOrder();
		if (order != null) {
			waitingOrderDLM.addElement(order);
			MainSystem.getInstance().addNewOrder(order);
			JOptionPane.showMessageDialog(null, "New order is maded", "Tips",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void clearContent() {
		panel_FoodOrder.enterNewOrderMode();
		
	}
}
