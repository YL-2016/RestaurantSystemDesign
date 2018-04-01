package entry;

import gui.LoginJFrame;
import logic.MainSystem;

public class MainEntry {
	public static void printTip() {
		System.out.println("Please check your params.");
		System.out.println("A running exaplme:");
		System.out.println("First run: java -jar xxx.jar -n");
		System.out.println("Normal run: java -jar xxx.jar");
	}

	public static void main(String[] args) {
		if (args.length == 1) {
			if (args[0].equals("-n")) {
				MainSystem.getInstance().reset();
			}
		}
		MainSystem.getInstance().initialize();
		LoginJFrame.getInstance().setVisible(true);
	}
}
