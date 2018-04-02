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

public class BillPanel extends JPanel implements TabPanelInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4396578369461641485L;
	private DefaultListModel<Order> billOrderDLM; // Bill
	private JList list_Bill;
	private JTextArea textArea_Bill;
	private CoreJFrame jframe;

	private Order currentOrder;

	public BillPanel(CoreJFrame coreJframe) {
		jframe = coreJframe;
		setLayout(null);

		billOrderDLM = new DefaultListModel<>();
		list_Bill = new JList(billOrderDLM);
		list_Bill.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		list_Bill.setBounds(10, 35, 71, 251);
		add(list_Bill);

		JLabel lblFinished = new JLabel("Finished");
		lblFinished.setBounds(10, 10, 54, 15);
		add(lblFinished);

		textArea_Bill = new JTextArea();
		textArea_Bill.setEditable(false);
		textArea_Bill.setFont(new Font("宋体", Font.PLAIN, 12));
		JScrollPane jsp = new JScrollPane(textArea_Bill);
		jsp.setBounds(91, 6, 758, 399);
		add(jsp);

		JButton btnFullReceipt = new JButton("Full Receipt");
		btnFullReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_Bill.getSelectedIndex();
				if (index != -1) {
					currentOrder = billOrderDLM.get(index);
					String billString = currentOrder.generateBillInfo(false);
					textArea_Bill.setText(billString);
				} else {
					JOptionPane.showMessageDialog(null,
							"Please choose an order", "Tips",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFullReceipt.setBounds(274, 421, 120, 23);
		add(btnFullReceipt);

		JButton btnAverageReceipt = new JButton("Average Receipt");
		btnAverageReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_Bill.getSelectedIndex();
				if (index != -1) {
					currentOrder = billOrderDLM.get(index);
					String billString = currentOrder.generateBillInfo(true);
					textArea_Bill.setText(billString);
				} else {
					JOptionPane.showMessageDialog(null,
							"Please choose an order", "Tips",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAverageReceipt.setBounds(404, 421, 128, 23);
		add(btnAverageReceipt);

		JButton btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int orderID = currentOrder.getOrderID();
				String receiptContent = textArea_Bill.getText();
				IOUtils.getInstance().printReceipt(orderID, receiptContent);
				JOptionPane.showMessageDialog(null, "Order is printed!",
						"Tips", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnPrintReceipt.setBounds(541, 421, 128, 23);
		add(btnPrintReceipt);

		Permission permission = MainSystem.getInstance().getCurrentPermission();
		if (permission.canOutputOneDayInfo()) {
			JButton btnPrintAllInfo = new JButton("Print Receipts");
			btnPrintAllInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IOUtils.getInstance().printAllReceiptInfo();
					JOptionPane.showMessageDialog(null,
							"All order info has been printed out to file!",
							"Tips", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			btnPrintAllInfo.setBounds(679, 421, 128, 23);
			add(btnPrintAllInfo);
		}

		updateBillPanel();
	}

	public void updateBillPanel() {
		List<Order> list = MainSystem.getInstance()
				.getCurrentFinishedOrderList();
		billOrderDLM.clear();
		for (Order order : list) {
			billOrderDLM.addElement(order);
		}
	}

	@Override
	public void clearContent() {
		textArea_Bill.setText("");
	}
}
