package utils;

import java.io.FileWriter;
import java.io.IOException;

public class LogUtils {
	private static LogUtils logUtils = null;

	public static LogUtils getInstance() {
		if (logUtils == null) {
			logUtils = new LogUtils();
		}

		return logUtils;
	}

	public void log(String str) {
		try {
			FileWriter fw = new FileWriter(ConstUtils.LOG_FILE, true);
			fw.write(str + ConstUtils.LINE_SEPARATOR);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
