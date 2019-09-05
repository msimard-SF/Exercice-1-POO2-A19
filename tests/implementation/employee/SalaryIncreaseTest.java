package implementation.employee;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import exceptions.employee.*;

public class SalaryIncreaseTest {

	private static final float SALARY_INCREASE_PERCENTAGE = 8f;
	private static final float SALARY_DELTA = 0.001f;
	
	private SalaryIncrease aSalaryIncrease = null;
	
	@Before
	public void setUpAnSalaryIncrease() {
		aSalaryIncrease = new SalaryIncrease(SALARY_INCREASE_PERCENTAGE);
	}
	
	
	@Test (expected = IllegalSalaryIncreasePercentageException.class)	
	public void createSalaryIncrease_whenSalaryIncreasePercentageIsSuperiorToMax_shouldRaiseIllegalSalaryIncreasePercentageException() {
		new SalaryIncrease(SalaryIncrease.MAX_SALARY_INCREASE_PERCENTAGE + 1);
	}
	
	@Test
	public void calculateSalaryIncreaseAmount_shouldApplyIncreasePercentage_ToTheSalary() {
		final float ANNUAL_SALARY = 42000f;
		
		float newSalary = aSalaryIncrease.getSalaryIncreaseAmount(ANNUAL_SALARY);
		
		final float EXPECTED_NEW_SALARY = ANNUAL_SALARY * SALARY_INCREASE_PERCENTAGE / 100;
		assertEquals(EXPECTED_NEW_SALARY, newSalary, SALARY_DELTA);	
	}


}
