package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class HighToLowFoodConsumedComparator implements Comparator<Chef> {

	public int compare(Chef a, Chef b) {
		return (int)(Math.round(b.getFoodConsumed() * 100.0D) - 
				Math.round(a.getFoodConsumed() * 100.0D));
	}
}
