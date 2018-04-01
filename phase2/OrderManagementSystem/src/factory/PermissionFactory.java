package factory;

import type.Permission;

public class PermissionFactory {
	private static PermissionFactory factory = null;

	public static PermissionFactory getInstance() {
		if (factory == null) {
			factory = new PermissionFactory();
		}

		return factory;
	} 

	public Permission makeManagerPermission() {
		return new Permission(true, true, false, true, false, true);
	}

	public Permission makeChefPermission() {
		return new Permission(false, false, false, false, true, false);
	}

	public Permission makeServerPermission() {
		return new Permission(false, true, true, true, false, false);
	}

}
