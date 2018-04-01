package gui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import factory.EmployeeFactory;
import gui.CoreJFrame;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import logic.MainSystem;
import type.employee.Chef;
import type.employee.Employee;
import type.employee.Server;

public class EmployeePanel extends JPanel implements TabPanelInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -436940881601398397L;
	
	private DefaultListModel<Employee> employeeDlm;
	private JList<Employee> list_Employee;
	
	private CoreJFrame jframe;
	
	public EmployeePanel(CoreJFrame coreJframe){
		jframe = coreJframe;
		setLayout(null);

		employeeDlm = getEmployeeListModel();
		list_Employee = new JList<Employee>(employeeDlm);
		JScrollPane jsp = new JScrollPane(list_Employee);
		jsp.setBounds(0, 0, 344, 461);
		add(jsp);

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
		add(btnDel);

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
		add(btnHireChef);

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
		add(btnHireServer);
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



	@Override
	public void clearContent() {
		// TODO Auto-generated method stub
		
	}
}
