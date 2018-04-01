package type.employee;

import type.EnumInfo.EmployeeTypeEnum;
import type.Order;
import type.Permission;

public abstract class Employee {
	public static int EMPLOYEE_ID = 10000;

	protected String name;
	protected int id;
	protected Permission permission;

	public Employee(String name, Permission permission) {
		super();
		this.name = name;
		this.id = EMPLOYEE_ID++;
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Permission getPermission() {
		return permission;
	}

	public abstract EmployeeTypeEnum getEmployeeType();

	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", Type="
				+ getEmployeeType().getValue() + "]";
	}

	public void addNewOrder(Order order) {
		System.out.println("Ops");
	}

	public void toPreparedList(Order order) {
		System.out.println("Ops");
	}

	public void toFinishedList(Order order) {
		System.out.println("Ops");
	}

	public Order prepareOrder() {
		System.out.println("Ops");
		return null;
	}

}
