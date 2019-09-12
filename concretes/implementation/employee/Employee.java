package implementation.employee;

import java.util.ArrayList;

import abstracts.employee.IEmployee;
import exceptions.employee.IllegalInitialAnnualSalaryException;

public class Employee implements IEmployee, Comparable<Employee> {
		
	public static final int EMPLOYEE_NUMBER_INCREMENT = 10;
	public static final float MIN_INITIAL_ANNUAL_SALARY = 0;
	public static final float MAX_INITIAL_ANNUAL_SALARY = 100000f;
	

	private static int nextEmployeeNumber = EMPLOYEE_NUMBER_INCREMENT;
	private int number;
	private String name;
	private float initialAnnualSalary;
	private ArrayList<SalaryIncrease> salaryIncreaseList = new ArrayList<SalaryIncrease>();
	private int abc;
	
	public Employee(String name, float initialAnnualSalary) {
		validateInitialAnnualSalary(initialAnnualSalary);
		this.name = name;
		this.initialAnnualSalary = initialAnnualSalary;
		this.number = nextEmployeeNumber;
		incrementEmployeeNumber();
	}
	
	private void validateInitialAnnualSalary(float initialAnnualSalary) { 
		if( initialAnnualSalary < MIN_INITIAL_ANNUAL_SALARY ) throw new IllegalInitialAnnualSalaryException();
		if( initialAnnualSalary > MAX_INITIAL_ANNUAL_SALARY ) throw new IllegalInitialAnnualSalaryException();
	}

	public String getName() {
		return this.name;
	}
	public int getNumber() {
		return this.number;
	}

	public float getAnnualSalary() {
			
		float annualSalary = this.initialAnnualSalary;
		for(SalaryIncrease salaryIncrease: this.salaryIncreaseList) {
			annualSalary += salaryIncrease.getSalaryIncreaseAmount(annualSalary);
		}
		return annualSalary;
		
	}
	
	public void applySalaryIncrease(SalaryIncrease salaryIncrease) {
		this.salaryIncreaseList.add(salaryIncrease);
	}

	private void incrementEmployeeNumber() {
		nextEmployeeNumber = nextEmployeeNumber + EMPLOYEE_NUMBER_INCREMENT;
	}


	//@Override
	public int compareTo(Employee emp) {
		
		if (this.getNumber() < emp.getNumber()) {
			return -1;
		}
		else if(this.getNumber() > emp.getNumber()) {
			return 1;
		}	
		else {
			return 0;
		}
	}
}
