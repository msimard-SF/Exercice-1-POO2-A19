package implementation.employee;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.employee.IllegalBonusPercentageException;
import implementation.employee.Bonus;
import implementation.employee.Manager;

public class ManagerTest {
	
	
	private Manager newManager;
	
	public static final float ANY_BONUS_PERCENTAGE = Bonus.MIN_BONUS_PERCENTAGE;
	public static final float BONUS_PERCENTAGE = Bonus.MAX_BONUS_PERCENTAGE;
	
	private static final float EPSILON = 0.00f;
	
	@Before
	public void setUpNewManager() {
		newManager = new Manager(EmployeeTest.ANY_NAME, EmployeeTest.INITIAL_ANNUAL_SALARY);
	}
	
	@Test
	public void createManager_shouldInitializeName() {
		assertEquals(EmployeeTest.ANY_NAME, newManager.getName());
	}
	
	@Test
	public void createManager_shouldInitializeInitialAnnualSalary() {
		assertEquals(EmployeeTest.INITIAL_ANNUAL_SALARY, newManager.getAnnualSalary(), EPSILON);
	}
	
	@Test
	public void awardingBonus_shouldUpdateAnnualSalaryWithBonus() {
		newManager.awardBonus(new Bonus(Bonus.MAX_BONUS_PERCENTAGE));
		
		final float EXPECTED_ANNUAL_SALARY = EmployeeTest.INITIAL_ANNUAL_SALARY + (EmployeeTest.INITIAL_ANNUAL_SALARY * Bonus.MAX_BONUS_PERCENTAGE);
		assertEquals(EXPECTED_ANNUAL_SALARY , newManager.getAnnualSalary(), EPSILON);
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void awardingBonus_whenBonusPercentageIsInferiorToMin_shouldRaiseIllegalBonusPercentageExceptionWithRightMessage() {
		thrown.expect(IllegalBonusPercentageException.class);
	
		newManager.awardBonus(new Bonus(Bonus.MIN_BONUS_PERCENTAGE - 0.001f));	
	}
	
	@Test
	public void awardingBonus_whenBonusPercentageIsSuperiorToMax_shouldRaiseIllegalBonusPercentageExceptionWithRightMessage() {
		thrown.expect(IllegalBonusPercentageException.class);
		
		newManager.awardBonus(new Bonus(Bonus.MAX_BONUS_PERCENTAGE + 0.001f));	
	}
}
