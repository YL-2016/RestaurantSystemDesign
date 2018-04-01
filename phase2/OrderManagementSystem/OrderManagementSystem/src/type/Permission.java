package type;

public class Permission {
	private boolean canUseEmployeePanel;
	private boolean canUseInventoryPanel;
	private boolean canUseOrderPanel;
	private boolean canUseBillPanel;
	private boolean canUseKitchenPanel;
	private boolean canUseInfoPanel;
	private boolean canSetAlarm;
	private boolean canOutputOneDayInfo;

	public Permission(boolean canUseEmployeePanel,
			boolean canUseInventoryPanel, boolean canUseOrderPanel,
			boolean canUseBillPanel, boolean canUseKitchenPanel,
			boolean canUseInfoPanel, boolean canSetAlram,
			boolean canOutputOneDayInfo) {
		super();
		this.canUseEmployeePanel = canUseEmployeePanel;
		this.canUseInventoryPanel = canUseInventoryPanel;
		this.canUseOrderPanel = canUseOrderPanel;
		this.canUseBillPanel = canUseBillPanel;
		this.canUseKitchenPanel = canUseKitchenPanel;
		this.canUseInfoPanel = canUseInfoPanel;
		this.canSetAlarm = canSetAlram;
		this.canOutputOneDayInfo = canOutputOneDayInfo;
	}

	public boolean canOutputOneDayInfo() {
		return canOutputOneDayInfo;
	}

	public boolean canSetAlarm() {
		return canSetAlarm;
	}

	public boolean canUseInfoPanel() {
		return canUseInfoPanel;
	}

	public void setCanUseInfoPanel(boolean canUseInfoPanel) {
		this.canUseInfoPanel = canUseInfoPanel;
	}

	public boolean canUseEmployeePanel() {
		return canUseEmployeePanel;
	}

	public void setCanUseEmployeePanel(boolean canUseEmployeePanel) {
		this.canUseEmployeePanel = canUseEmployeePanel;
	}

	public boolean canUseInventoryPanel() {
		return canUseInventoryPanel;
	}

	public void setCanUseInventoryPanel(boolean canUseInventoryPanel) {
		this.canUseInventoryPanel = canUseInventoryPanel;
	}

	public boolean canUseOrderPanel() {
		return canUseOrderPanel;
	}

	public void setCanUseOrderPanel(boolean canUseOrderPanel) {
		this.canUseOrderPanel = canUseOrderPanel;
	}

	public boolean canUseBillPanel() {
		return canUseBillPanel;
	}

	public void setCanUseBillPanel(boolean canUseBillPanel) {
		this.canUseBillPanel = canUseBillPanel;
	}

	public boolean canUseKitchenPanel() {
		return canUseKitchenPanel;
	}

	public void setCanUseKitchenPanel(boolean canUseKitchenPanel) {
		this.canUseKitchenPanel = canUseKitchenPanel;
	}
}
