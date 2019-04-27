package model;

public class Employee {
	private Long id;
	private String name;
	private String age;
	private String salary;

	public Employee() {
		super();
	}

	public Employee(String name, String age, String salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Employee(Long id, String name, String age, String salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}

}
