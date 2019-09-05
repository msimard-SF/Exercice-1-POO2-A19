package implementation.employee;

import org.junit.Test;

import exceptions.employee.*;

public class BonusTest {

	@Test
	public void createBonus_shouldInitializeBonusPercent(){		
	}

	
	@Test (expected = IllegalBonusPercentageException.class)	
	public void createBonus_whenBonusPercentageIsSuperiorToMax_shouldRaiseIllegalBonusPercentageException() {		
		//Act
		Bonus abonus = new Bonus(Bonus.MAX_BONUS_PERCENTAGE + 1);
	}
	
	@Test
	public void calculateBonusAmount_shouldApplyBonusPercentage_ToTheSalary() {	
	}
}
