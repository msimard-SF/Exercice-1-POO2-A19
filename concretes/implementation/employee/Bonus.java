package implementation.employee;

import abstracts.employee.IBonus;
import exceptions.employee.IllegalBonusPercentageException;

public class Bonus implements IBonus {

	public static final float MIN_BONUS_PERCENTAGE = 0f;
	public static final float MAX_BONUS_PERCENTAGE = 20f;
	
	private float bonusPercentage;  
	
	public Bonus(float bonusPercentage) {
		validateBonusPourcentage(bonusPercentage);
		this.bonusPercentage = bonusPercentage;
	}
	
	public float getBonusAmount(float annualSalary) {
		return annualSalary * this.bonusPercentage;
	}

	private void validateBonusPourcentage(float bonusPercentage) {
		if( bonusPercentage < MIN_BONUS_PERCENTAGE ) throw new IllegalBonusPercentageException();
		if( bonusPercentage > MAX_BONUS_PERCENTAGE ) throw new IllegalBonusPercentageException();	
	}
}
