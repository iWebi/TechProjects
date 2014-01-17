package com.webi


import javax.persistence.*

/**
 * Created by IntelliJ IDEA.
 * User: suman
 * Date: 1/9/14.
 * Time: 10:38 AM
 */
@Entity
class ExpenseDetails {
    @Id
    Date date = new Date();
    double boa
    double costCo
    double target
    double usaa
    double macys

    double total() {
        boa + costCo + target + usaa + macys
    }

    boolean isValid() {
        boa && costCo && target && usaa && macys
    }

	@Override
	public String toString() {
		"date=$date \nboa=$boa \ncostCo=$costCo \ntarget=$target \nusaa=$usaa \nmacys=$macys" as String
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(boa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(costCo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		temp = Double.doubleToLongBits(macys);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(target);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(usaa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object objInp) {
		ExpenseDetails obj = (ExpenseDetails)objInp
		boa == obj.boa && 
				costCo == obj.costCo && 
				target == obj.target &&
				usaa == obj.usaa &&
				macys == obj.macys
	}
	
}