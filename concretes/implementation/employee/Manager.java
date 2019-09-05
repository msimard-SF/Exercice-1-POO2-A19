package implementation.employee;

import abstracts.employee.IBonus;
import abstracts.employee.IManager;

public class Manager extends Employee implements IManager{
	
	private IBonus bonus;
	
	public Manager(String managerName, float initialAnnualSalary) {
		super(managerName, initialAnnualSalary);
	}
	
	public void awardBonus(IBonus bonus) {
		this.bonus = bonus;
	}
	
	@Override
	public float getAnnualSalary() { 
		float annualSalary = super.getAnnualSalary();
		
		float bonusAmount = 0;
		if(this.bonus != null) {
			bonusAmount = this.bonus.getBonusAmount(annualSalary);
		}
		return annualSalary + bonusAmount;
	}

}
