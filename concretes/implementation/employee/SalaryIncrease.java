package implementation.employee;

import abstracts.employee.ISalaryIncrease;
import exceptions.employee.IllegalSalaryIncreasePercentageException;

public class SalaryIncrease implements ISalaryIncrease {

	public static final float MIN_SALARY_INCREASE_PERCENTAGE = 0f;
	public static final float MAX_SALARY_INCREASE_PERCENTAGE = 15f;
	
	private float salaryIncreasePercentage;  
	
	public SalaryIncrease(float salaryIncreasePercentage) {
		validateSalaryIncreasePourcentage(salaryIncreasePercentage);
		this.salaryIncreasePercentage = salaryIncreasePercentage;
	}
	
	public float getSalaryIncreaseAmount(float annualSalary) {
		return annualSalary * this.salaryIncreasePercentage /100;
	}

	private void validateSalaryIncreasePourcentage(float wageIncreasePercentage) {
		if( wageIncreasePercentage < MIN_SALARY_INCREASE_PERCENTAGE ) throw new IllegalSalaryIncreasePercentageException();
		if( wageIncreasePercentage > MAX_SALARY_INCREASE_PERCENTAGE ) throw new IllegalSalaryIncreasePercentageException();	
	}
	
}
