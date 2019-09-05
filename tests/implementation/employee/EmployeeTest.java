package implementation.employee;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.employee.IllegalInitialAnnualSalaryException;
import exceptions.employee.IllegalSalaryIncreasePercentageException;
import implementation.employee.Employee;
import implementation.employee.SalaryIncrease;

public class EmployeeTest {
	
	public static final String ANY_NAME = "Rita Matte";
	
	public static final float INITIAL_ANNUAL_SALARY = Employee.MAX_INITIAL_ANNUAL_SALARY;
	public static final int ANY_RATE_SALARY_INCREASE = 10;

	private static final float EPSILON = 0.00f;

	private Employee newEmployee;
	
	@Before
	public void setUpNewEmployee() {
		newEmployee = new Employee(ANY_NAME, INITIAL_ANNUAL_SALARY);
	}
	
	@Test
	public void createEmployee_shouldInitializeName() {
		assertEquals(ANY_NAME, newEmployee.getName());
	}
	
	@Test
	public void createEmployee_shouldInitializeInitialAnnualSalary() {
		assertEquals(INITIAL_ANNUAL_SALARY, newEmployee.getAnnualSalary(), EPSILON);
	}
	
	@Test
	public void createEmployee_shouldInitializeNumber_withIncrement() {
		final int lastEmployeeNumber = newEmployee.getNumber();

		Employee anOtherEmployee = new Employee(ANY_NAME, INITIAL_ANNUAL_SALARY);
		
		assertEquals(lastEmployeeNumber + Employee.EMPLOYEE_NUMBER_INCREMENT, anOtherEmployee.getNumber());
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createEmployee_whenInitialAnnualSalaryIsInferiorToMin_shouldRaiseIllegalInitialAnnualSalaryExceptionWithRightMessage() {
		thrown.expect(IllegalInitialAnnualSalaryException.class);
		
		new Employee(ANY_NAME, Employee.MIN_INITIAL_ANNUAL_SALARY - 1);	
	}
	
	@Test
	public void createEmployee_whenInitialAnnualSalaryIsSuperiorToMax_shouldRaiseIllegalInitialAnnualSalaryExceptionWithRightMessage() {
		thrown.expect(IllegalInitialAnnualSalaryException.class);
	
		new Employee(ANY_NAME, Employee.MAX_INITIAL_ANNUAL_SALARY + 1);	
	}
	
	@Test
	public void applySalaryIncrease_shouldUpdateAnnualSalary() {
		newEmployee.applySalaryIncrease(new SalaryIncrease(SalaryIncrease.MAX_SALARY_INCREASE_PERCENTAGE));
		
		float salary = INITIAL_ANNUAL_SALARY;
		salary += salary * SalaryIncrease.MAX_SALARY_INCREASE_PERCENTAGE / 100;
		
		final float EXPECTED_ANNUAL_SALARY = salary;
		assertEquals(EXPECTED_ANNUAL_SALARY , newEmployee.getAnnualSalary(), EPSILON);
	}
	
	@Test
	public void applyTwoSalaryIncrease_shouldUpdateAnnualSalary() {
		newEmployee.applySalaryIncrease(new SalaryIncrease(SalaryIncrease.MAX_SALARY_INCREASE_PERCENTAGE));
		newEmployee.applySalaryIncrease(new SalaryIncrease(SalaryIncrease.MAX_SALARY_INCREASE_PERCENTAGE));
		
		float salary = INITIAL_ANNUAL_SALARY;
		salary += salary * SalaryIncrease.MAX_SALARY_INCREASE_PERCENTAGE / 100;
		salary += salary * SalaryIncrease.MAX_SALARY_INCREASE_PERCENTAGE /100;
		
		final float EXPECTED_ANNUAL_SALARY = salary;
		assertEquals(EXPECTED_ANNUAL_SALARY , newEmployee.getAnnualSalary(), EPSILON);
	}
	
	@Test
	public void applySalaryIncrease_whenWageIncreasePercentageIsInferiorToMin_shouldRaiseIllegalWageIncreasePercentageExceptionWithRightMessage() {
		thrown.expect(IllegalSalaryIncreasePercentageException.class);
		
		newEmployee.applySalaryIncrease(new SalaryIncrease(SalaryIncrease.MIN_SALARY_INCREASE_PERCENTAGE - 0.001f));	
	}
	
	@Test
	public void applySalaryIncrease_whenWageIncreasePercentageIsSuperiorToMax_shouldRaiseIllegalWageIncreasePercentageExceptionWithRightMessage() {
		thrown.expect(IllegalSalaryIncreasePercentageException.class);
		
		newEmployee.applySalaryIncrease(new SalaryIncrease(SalaryIncrease.MAX_SALARY_INCREASE_PERCENTAGE + 0.001f));	
	}
	
	//Tests java.util.Collections.sort
	@Test
	public void Collection_whenSort_shouldSortEmployeeByNumberInAscendingForm() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee anOtherEmployee = new Employee(ANY_NAME, INITIAL_ANNUAL_SALARY);
		employees.add(anOtherEmployee);	//Employee with bigger number firts
		employees.add(newEmployee);
		
		java.util.Collections.sort(employees); //Méthode statique...
		
		assertSame(newEmployee, employees.get(0));
		assertSame(anOtherEmployee, employees.get(1));
	}
}
