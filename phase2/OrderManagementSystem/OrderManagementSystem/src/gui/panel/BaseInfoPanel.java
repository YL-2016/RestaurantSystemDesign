package gui.panel;

import gui.CoreJFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.MainSystem;

public class BaseInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -157659867293281064L;

	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_Type;

	private CoreJFrame jframe;

	public BaseInfoPanel(CoreJFrame coreJframe) {
		jframe = coreJframe;
		setBounds(400, 0, 474, 42);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 13, 18, 15);
		add(lblNewLabel);

		textField_ID = new JTextField();
		textField_ID.setBounds(38, 10, 82, 21);
		add(textField_ID);
		textField_ID.setEditable(false);
		textField_ID.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(210, 10, 88, 21);
		add(textField_Name);
		textField_Name.setEditable(false);
		textField_Name.setColumns(10);

		JLabel label = new JLabel("Name:");
		label.setBounds(155, 13, 45, 15);
		add(label);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(342, 13, 30, 15);
		add(lblType);

		textField_Type = new JTextField();
		textField_Type.setBounds(382, 10, 82, 21);
		add(textField_Type);
		textField_Type.setEditable(false);
		textField_Type.setColumns(10);

		init();
	}

	private void init() {
		textField_ID.setText(MainSystem.getInstance().getCurrentId() + "");
		textField_Name.setText(MainSystem.getInstance().getCurrentName() + "");
		textField_Type.setText(MainSystem.getInstance().getEmployeeType() + "");
	}

}
