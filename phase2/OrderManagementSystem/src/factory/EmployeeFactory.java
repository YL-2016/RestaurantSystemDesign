package factory;

import type.employee.Chef;
import type.employee.Manager;
import type.employee.Server;

public class EmployeeFactory {
	private static EmployeeFactory factory = null;

	public static EmployeeFactory getInstance() {
		if (factory == null) {
			factory = new EmployeeFactory();
		}

		return factory;
	}

	public Manager createManager(String name) {
		return new Manager(name, PermissionFactory.getInstance()
				.makeManagerPermission());
	}

	public Chef createChef(String name) {
		return new Chef(name, PermissionFactory.getInstance()
				.makeChefPermission());
	}

	public Server createServer(String name) {
		return new Server(name, PermissionFactory.getInstance()
				.makeServerPermission());
	}
}
