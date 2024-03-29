package org.jLOAF.sim.StateBased;

import org.jLOAF.inputs.Input;
import org.jLOAF.inputs.StateBasedInput;
import org.jLOAF.sim.StateBasedSimilarity;
/**
 * this class represents a similarity metric that compares the traces until it reaches the  kth element of the traces
 * where the order matters. if K was bigger than the trace with the minimum size, and the difference between k and that size is larger than 5,
 * and also the difference between the trace with the bigger size and the smaller size is more than five, the similarity is zero.
 */
public class KOrderedSimilarity extends StateBasedSimilarity  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//k is how many steps you want to go back in the trace
		private int k;
		int bla=0;
		public KOrderedSimilarity(int k){
			this.k=k;
		}

	@Override
	public double similarity(Input i1, Input i2) {
		//null check
				if(i1==null || i2 ==null) return 0.0;
				
				if(!(i1 instanceof StateBasedInput) || !(i2 instanceof StateBasedInput)){
					throw new IllegalArgumentException("this metric is only used for StateBased inputs");
				}
				
				StateBasedInput st1 = (StateBasedInput)i1;
				StateBasedInput st2 = (StateBasedInput)i2;
				int sizeSt1 = st1.getSize();
				int sizeSt2=st2.getSize();
				int penalty =0;
				bla++;
				penalty =Math.abs(sizeSt1-sizeSt2);
				if(k>Math.min(sizeSt2, sizeSt1)+5){
				 
				 if(penalty>5){
					 return 0;
				 }
				}else if (k>Math.min(sizeSt2, sizeSt1)){
					
					if(penalty>5){
						penalty = k-Math.min(sizeSt2, sizeSt1);
					}
				}else {
					penalty=0;
				}
				
				double similarity = 0;
				for(int i=0;i<k;i=i+2){
					if(i>sizeSt1 ||i>sizeSt2){
						break;
					}
					try{
					similarity+=st1.getInput(i).similarity(st2.getInput(i));
					}catch(NullPointerException e){
						System.out.print(e);
					}
					if(i+1 <Math.min(sizeSt1, sizeSt2) && i+1 <k){
						
						similarity+=st1.getAction(i+1).similarity(st2.getAction(i+1));}
				}
				
				
				similarity=(similarity-((similarity*penalty)/10));
					if(similarity<0){
						return 0;
					}
					
				return similarity/k;
	}

}
