package gui.panel;

import gui.CoreJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import type.Permission;
import logic.MainSystem;

public class InventoryPanel extends JPanel implements TabPanelInterface{

	private final static String INGREDIENT_COLUMN = "Ingredient";
	private final static String STOCK_COLUMN = "Stock";
	private final static String IMPORT_COLUMN = "Import";

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460874470276803848L;
	private DefaultTableModel ingredientDtm;
	private JTextField textField_Alarm;
	private JTable table_Ingredient;
	private CoreJFrame jframe;

	public InventoryPanel(CoreJFrame coreJFrame) {
		jframe = coreJFrame;

		setLayout(new BorderLayout(0, 0));

		JPanel panel_Left = new JPanel();
		add(panel_Left, BorderLayout.CENTER);

		ingredientDtm = getIngredientTableModel();
		panel_Left.setLayout(new BorderLayout(0, 0));
		table_Ingredient = new JTable(ingredientDtm) {
			private static final long serialVersionUID = -300348443048090716L;

			public boolean isCellEditable(int row, int column) {
				if (column == 2) {
					return true;
				} else {
					return false;
				}
			}
		};
		JTableHeader tableHeader = table_Ingredient.getTableHeader();
		panel_Left.add(table_Ingredient, BorderLayout.CENTER);
		panel_Left.add(tableHeader, BorderLayout.NORTH);

		Permission permission = MainSystem.getInstance().getCurrentPermission();
		if (permission.canSetAlarm()) {
			JPanel panel_Right = new JPanel();
			add(panel_Right, BorderLayout.EAST);
			panel_Right.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			int alarmNum = MainSystem.getInstance().getAlarmNum();
			textField_Alarm = new JTextField();
			textField_Alarm.setText(alarmNum + "");
			textField_Alarm.setEditable(false);
			panel_Right.add(textField_Alarm);
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
			panel_Right.add(btn_Alarm);
		}

		showAlarm();
	}

	private DefaultTableModel getIngredientTableModel() {
		DefaultTableModel dtm = new DefaultTableModel(createTableModelData(),
				createColumnNames());

		dtm.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 2) {
					int rowIdx = e.getLastRow();
					Integer importValue = Integer.parseInt(table_Ingredient
							.getValueAt(rowIdx, 2).toString());
					if (importValue != 0) {
						String title = (String) table_Ingredient.getValueAt(
								rowIdx, 0);
						Integer cStock = Integer.parseInt(table_Ingredient
								.getValueAt(rowIdx, 1).toString());
						int total = cStock + importValue;
						table_Ingredient.setValueAt(total, rowIdx, 1);
						table_Ingredient.setValueAt(0, rowIdx, 2);

						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put(title, total);
						MainSystem.getInstance().update(map);
						showAlarm();
					}
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
			rowData.add(0);
			data.add(rowData);
		}

		return data;
	}

	private Vector createColumnNames() {
		Vector columnNames = new Vector();
		columnNames.add(INGREDIENT_COLUMN);
		columnNames.add(STOCK_COLUMN);
		columnNames.add(IMPORT_COLUMN);

		return columnNames;
	}

	public void showAlarm() {
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

		TableColumn tc = table_Ingredient.getColumn(STOCK_COLUMN);
		tc.setCellRenderer(dtcr);
		table_Ingredient.repaint();
	}

	@Override
	public void clearContent() {
		// TODO Auto-generated method stub
		
	}

}
