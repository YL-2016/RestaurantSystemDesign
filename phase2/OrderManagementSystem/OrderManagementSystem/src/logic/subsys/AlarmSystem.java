package logic.subsys;

import utils.IOUtils;
import utils.LogUtils;

public class AlarmSystem {
	private int alarmNum;

	public AlarmSystem() {
		initialze();
	}

	private void initialze() {
		alarmNum = IOUtils.getInstance().loadAlarmNum();
	}

	public int getAlarmNum() {
		return alarmNum;
	}

	public void setAlarmNum(int value) {
		LogUtils.getInstance().log("Alarm value is set to " + value);
		alarmNum = value;
	}

}
