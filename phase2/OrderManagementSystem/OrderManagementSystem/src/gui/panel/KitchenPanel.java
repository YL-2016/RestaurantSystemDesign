package gui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import gui.CoreJFrame;

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
import type.employee.Chef;

public class KitchenPanel extends JPanel implements TabPanelInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2320751809906852668L;

	private DefaultListModel<Order> kitchenOrderDLM; // Kitchen
	private JList list_Kitchen;
	private FoodPanel panel_OrderInKitchen;
	private CoreJFrame jframe;
	
	public KitchenPanel(CoreJFrame coreJFrame){
		jframe = coreJFrame;
		
		setLayout(null);

		kitchenOrderDLM = new DefaultListModel<>();
		list_Kitchen = new JList(kitchenOrderDLM);
		list_Kitchen.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = list_Kitchen.getSelectedIndex();
				if (index != -1) {
					Order order = kitchenOrderDLM.get(index);
					panel_OrderInKitchen.enterDisplayMode(order, false);
				}
			}
		});
		list_Kitchen.setBounds(10, 35, 71, 161);
		add(list_Kitchen);

		JLabel label_1 = new JLabel("Waiting");
		label_1.setBounds(10, 10, 54, 15);
		add(label_1);

		panel_OrderInKitchen = new FoodPanel();
		panel_OrderInKitchen.setBounds(91, 0, 758, 399);
		add(panel_OrderInKitchen);

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_Kitchen.getSelectedIndex();
				if (index == 0) {
					Order order = kitchenOrderDLM.get(index);
					if (MainSystem.getInstance().isEnough(order.getNeedsMap())) {
						MainSystem.getInstance().prepareOrder(order);
						kitchenOrderDLM.remove(0);
						JOptionPane.showMessageDialog(null,
								"Order is prepared!", "Tips",
								JOptionPane.INFORMATION_MESSAGE);
						panel_OrderInKitchen.enterNewOrderMode();
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
		btnFinish.setBounds(101, 409, 123, 23);
		add(btnFinish);
		
		updateKitchenPanel();
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

	@Override
	public void clearContent() {
		
	}
}
