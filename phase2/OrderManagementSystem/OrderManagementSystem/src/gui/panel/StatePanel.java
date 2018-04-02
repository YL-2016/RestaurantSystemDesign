package gui.panel;

import gui.CoreJFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.MainSystem;
import type.Order;
import type.Permission;
import utils.IOUtils;

public class StatePanel extends JPanel implements TabPanelInterface {

	/**
	 *
	 */

	private DefaultListModel<Order> billOrderDLM; // Bill
	private JList list_Bill;
	private DefaultListModel<Order> preparedOrderDLM;
	private CoreJFrame jframe;
	private JList list_Prepared;
	private JTextArea textArea_Info;
	private FoodPanel panel_FoodOrder;

	public StatePanel(CoreJFrame coreJframe) {
		jframe = coreJframe;
		setLayout(null);

		preparedOrderDLM = new DefaultListModel<>();
		list_Prepared = new JList(preparedOrderDLM);

		panel_FoodOrder = new FoodPanel();
		panel_FoodOrder.setBounds(91, 0, 758, 399);
		add(panel_FoodOrder);

		billOrderDLM = new DefaultListModel<>();
		list_Bill = new JList(billOrderDLM);
		list_Bill.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = list_Bill.getSelectedIndex();
				if (index != -1) {
					Order order = billOrderDLM.get(index);
					panel_FoodOrder.enterDisplayMode(order, false);
				}

			}
		});

		list_Prepared.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = list_Prepared.getSelectedIndex();
				if (index != -1) {
					Order order = preparedOrderDLM.get(index);
					panel_FoodOrder.enterDisplayMode(order, false);
				}

			}
		});
		list_Bill.setBounds(10, 237, 71, 155);
		add(list_Bill);
		JLabel lblFinished = new JLabel("Prepared");
		lblFinished.setBounds(10, 212, 54, 15);
		add(lblFinished);

		list_Prepared.setBounds(10, 41, 71, 161);
		add(list_Prepared);
		JLabel lblWaiting = new JLabel("Waiting");
		lblWaiting.setBounds(10, 16, 54, 15);



		add(lblWaiting);
		updateWaitingPanel();
		updatePreparedPanel();

	}

	public void updateWaitingPanel() {
		List<Order> list = MainSystem.getInstance()
				.getCurrentPreparedOrderList();
		billOrderDLM.clear();
		for (Order order : list) {
			billOrderDLM.addElement(order);
		}
	}

	public void updatePreparedPanel(){
		List<Order> list = MainSystem.getInstance()
				.getCurrentWaitingOrderList();
		preparedOrderDLM.clear();
		for (Order order : list) {
			preparedOrderDLM.addElement(order);
		}
	}


	@Override
	public void clearContent() {
	}
}
