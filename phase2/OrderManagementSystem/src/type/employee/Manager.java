package type.employee;

import type.Permission;
import type.EnumInfo.EmployeeTypeEnum;

public class Manager extends Employee {

	public Manager(String name, Permission permission) {
		super(name, permission);
	}

	public EmployeeTypeEnum getEmployeeType() {
		return EmployeeTypeEnum.MANAGER;
	}
}
