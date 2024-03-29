/**
 * A very simple percent difference similarity check between the two.
 * 
 * **/

package org.jLOAF.sim.atomic;

import org.jLOAF.inputs.AtomicInput;
import org.jLOAF.inputs.Input;
import org.jLOAF.sim.AtomicSimilarityMetricStrategy;
import org.jLOAF.sim.SimilarityMetricStrategy;
/**
 * This class calculates the percent difference between two inputs
 * @author sachagunaratne
 *
 */
public class PercentDifference extends AtomicSimilarityMetricStrategy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Checks the percent difference between the two atomic values and returns that in the range 
	 * of 0 and 1. The higher the better
	 *  @see org.jLOAF.sim.SimilarityMetricStrategy
	 */
	@Override
	public double similarity(Input i1, Input i2) {
		if(!(i1 instanceof AtomicInput) || !(i2 instanceof AtomicInput)){
			throw new IllegalArgumentException("PercentDifference.similarity(...): One of the arguments was not an AtomicInput.");
		}

		double val1 = ((AtomicInput)i1).getFeature().getValue();
		double val2 = ((AtomicInput)i2).getFeature().getValue();
		
		double denom = val1+val2;
		double num = Math.abs(val1-val2);
		
		if(denom==0 && num!=0) return 0.0;
		if(denom==0 && num==0) return 1.0;
		
		return (1-num/denom);
	}

	
	
	

}
