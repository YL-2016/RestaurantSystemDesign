package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.MainSystem;
import type.EnumInfo.EmployeeTypeEnum;

public class LoginJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6838214974790475172L;
	private JPanel contentPane;
	private JTextField textField_EmployeeID;
	private JComboBox<String> comboBox_EmployeeType;
	
	private static LoginJFrame loginJFrame;
	
	public static LoginJFrame getInstance(){ 
		if(loginJFrame == null){
			loginJFrame = new LoginJFrame();
		}
		return loginJFrame;
	}

	/**
	 * Create the frame.
	 */
	private LoginJFrame() {
		loginJFrame = this;
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox_EmployeeType = new JComboBox<String>();
		for (EmployeeTypeEnum type : EmployeeTypeEnum.values()) {
			comboBox_EmployeeType.addItem(type.getValue());
		}
		comboBox_EmployeeType.setBounds(202, 63, 149, 21);
		contentPane.add(comboBox_EmployeeType);

		JLabel lblNewLabel = new JLabel("Employee Type");
		lblNewLabel.setBounds(102, 66, 90, 15);
		contentPane.add(lblNewLabel);

		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(102, 120, 90, 15);
		contentPane.add(lblEmployeeId);

		textField_EmployeeID = new JTextField();
		textField_EmployeeID.setBounds(202, 117, 149, 21);
		contentPane.add(textField_EmployeeID);
		textField_EmployeeID.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = comboBox_EmployeeType.getSelectedIndex();
				int id = Integer.parseInt(textField_EmployeeID.getText());
				if(MainSystem.getInstance().login(EmployeeTypeEnum.values()[idx], id)){
					CoreJFrame coreJFrame = new CoreJFrame();
					coreJFrame.setVisible(true);
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "Wrong info", "Tips", JOptionPane.ERROR_MESSAGE);
				}
			} 
		});
		btnLogin.setBounds(99, 183, 93, 23);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(258, 183, 93, 23);
		contentPane.add(btnExit);
	}
}
