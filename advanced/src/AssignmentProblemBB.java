import java.util.ArrayList;

class CycleNode
{
    public int val; // the node variable 0 or 1;
    public double currentNodeProfit;

    public CycleNode(int val,double currentNodeProfit)
    {
      this.val=val;
      this.currentNodeProfit=currentNodeProfit;
    }
    public String toString()
    {
        return "val: "+val+ " Current Cost: "+currentNodeProfit;
    }
}

public class AssignmentProblemBB extends BranchAndBound<CycleNode> {
	 public double [][] G; 
	
	 
	    public static void main(String[] args) 
	    {
	    	double C [][] = new double[][] { { 0, 6, 1, 3, 6}, { 6, 0, 3, 4, 2 }, { 0, 3, 0, 1, 6 },
				{ 3, 4, 1,0, 3 } ,{6,2,6,3,0}};

		double D [][] = new double[][] { { 0, 3, 4, 7, 6 }, { 3, 0, 3, 1, 2 }, { 5, 3, 0, 1, 6 }, { 7, 4, 1, 0, 5 },
				{ 9, 2, 6, 2, 0 } };

	  double T [][] = new double[][] { { 0, 9, 10, 30 , 16 }, { 6, 0, 3, 14, 2 }, { 0, 13, 0, 1, 16 }, { 13, 14, 1, 0, 13 },
					{ 6, 2, 6, 3, 0 } };
	        
	        double G [][] = new double[][] {{0,6,0,3,6},{6,0,3,4,2},
	            {0,3,0,1,6},{3,4,1,0,3},{6,2,6,3,0}};
	            
	            AssignmentProblemBB assignment =new AssignmentProblemBB(G);
	            assignment.solve();
	            AssignmentProblemBB assignment2 =new AssignmentProblemBB(T);
	            assignment2.solve();
	            AssignmentProblemBB assignment3 =new AssignmentProblemBB(D);
	            assignment3.solve();
	            AssignmentProblemBB assignment4 =new AssignmentProblemBB(C);
	            assignment4.solve();
	    }
	    
	    
	 public AssignmentProblemBB(double [][] G)
	    {
	        this.G=G;
	    }
	
	
	@Override
	public CycleNode[] nodeValues(Node<CycleNode> parent) {
		 ArrayList<CycleNode>nextValues=new ArrayList<CycleNode>();
	        ArrayList<Node<CycleNode>> currentPath=branchPath(parent);
	        for(int val=1;val<G.length;val++)
	        {   
	            if (G[parent.x.val][val]!=0)
	            {
	              
	                boolean found=false;
	                for(Node<CycleNode> node:currentPath)
	                {
	                    if (node.x.val==val)
	                    {
	                        found=true;
	                        break; 
	                    }
	                }
	                if(!found) 
	                {
	                   if (parent.depth<G.length-2) 
	                       
	                   {
	                       nextValues.add(new CycleNode(val,parent.x.currentNodeProfit+G[parent.x.val][val]));
	                   }
	                   else if ((parent.depth==G.length-2) && (G[val][0]!=0)) 
	                   {
	                       nextValues.add(new CycleNode(val,parent.x.currentNodeProfit+G[parent.x.val][val]));
	                   }
	                }           
	            }
	        }
	        
	        return (CycleNode []) nextValues.toArray(new CycleNode[nextValues.size()]);
	}

	@Override
	public boolean isSolution(Node<CycleNode> node) {
		 if (node.depth==G.length-1)
	            return true;
	        return false;
	}

	@Override
	public double calcCX(int depth, CycleNode t) {
		return t.currentNodeProfit;  
	}

	@Override
	public double calcUX(int depth, CycleNode t) {
		 if (depth==0)
	            return 1000000000;
	        else
	            return answerVal; 
	}
	
	
	public double cost(ArrayList<Node<CycleNode>> branch) 
    {
       return branch.get(0).x.currentNodeProfit;
    }
	
	 public void report(ArrayList<Node<CycleNode>> answer)
	    {
	        System.out.print("Min Cost: ");
	        for ( int i=answer.size()-1;i>=0;i--)
	        {
	            System.out.print(answer.get(i).x.val + ",");
	            
	        }
	        System.out.println(" Total Cost = "+ answerVal);
	        System.out.println( "Number of nodes generated: "+numberOfNodes);
	    }
	    public void solve()
	    {
	        CycleNode root=new CycleNode(0, 0);
	        //ArrayList<Node<CycleNode>> answer =solve(Strategy.FIFO,root);
	        ArrayList<Node<CycleNode>> answer =solve(Strategy.LIFO,root);
	        //ArrayList<Node<CycleNode>> answer =solve(Strategy.LC,root);
	        report(answer);
	    }

}
