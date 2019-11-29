import java.util.*;
import java.lang.Math;
public class DFS
{
	static boolean reached=false;
	
	public static int[] moves(String state)
	{
		//LURD - Order of moves
		int xindex=state.indexOf('x');
		if(xindex==0)
		{
			int moves[]={1,3};
			return moves;
		}
		else if(xindex==1)
		{
			int moves[] = {0,2,4};
			return moves; 
		}
		else if(xindex==2)
		{
			int moves[] = {1,5}; 
			return moves;
		}
		else if(xindex==3)
		{
			int moves[] = {0,4,6};
			return moves;
		}
		else if(xindex==4)
		{
			int moves[] = {3,1,5,7}; 
			return moves;
		}
		else if(xindex==5)
		{
			int moves[] = {4,2,8}; 
			return moves;
		}
		else if(xindex==6)
		{
			int moves[] = {3,7}; 
			return moves;
		}
		else if(xindex==7)
		{
			int moves[] = {6,4,8};
			return moves; 
		}
		else
		{
			int moves[] = {7,5}; 
			return moves;
		}


       
	}
	public static String swap(String state, int index2)
	{
		int index1=state.indexOf('x');
		char[] charstate = state.toCharArray();
        charstate[index1] = state.charAt(index2);
        charstate[index2] = state.charAt(index1);
        String newstate = String.valueOf(charstate);
        return newstate;
	}
	public static void printstate(String state)
	{
		System.out.println("------");
		for(int i=0;i<state.length();i++)
		{
			if(i==2||i==5)
			{
				System.out.println(" "+state.charAt(i)+" ");
			}
			else
			{
				System.out.print(" "+state.charAt(i)+" ");
			}
		}
		System.out.println();
		System.out.println("------");
	}
	public static LinkedHashSet<String> DFS(LinkedHashSet<String> visited, int depth, String state, String goal)
	{
		LinkedHashSet<String> visitedcopy=new LinkedHashSet<String>(visited);
		//System.out.println("Set: "+visited);
		//System.out.println("Depth: "+depth);
		//printstate(state);
 		if(visited.contains(state)||depth<0)
 		{
 			//System.out.println("State already Visited or Depth Exceeded.");
 		}
 		else
 		{

 				visited.add(state);
 				if(state.equals(goal))
 				{
 					reached=true;
 					//System.out.println("Goal State Found. Returning Now!");
 					return visited;
 					
 				}
 				
 				int moveoptions[]=moves(state);
 				for(int i=0;i<moveoptions.length;i++)
 				{
 					String nextstate=swap(state,moveoptions[i]);
 					visited=DFS(visited,(depth-1),nextstate,goal);
 					if(reached)
 					{
 						//System.out.println("Goal State Found. Breaking Now!");
 						break;

 					}
 				}

 			
 			
 		}
 		if(reached)
 		{
 			//System.out.println("Return by Reference");
 			return visited;
 		}
 		//System.out.println("Normal Return");
 		return visitedcopy;
 		
	}
	public static void main(String args[])
	{
		
		String initial = "12345678x";
		String goal = "87654321x";
		//printstate(initial);
		//printstate(goal);
		LinkedHashSet<String> visited=new LinkedHashSet<String>();
		double startTime = System.nanoTime();
		LinkedHashSet<String> path=DFS(visited,50,initial,goal);
		double endTime   = System.nanoTime();
		double totalTime = endTime - startTime;
		System.out.println("Time Taken: "+(totalTime/1000000000));
		if(reached)
		{
			System.out.println("Path to Goal State Found: "+path.size()+" states");
			for(String state : path)
			{
         		printstate(state);
        	}
    	}
    	else
    	{
    		System.out.println("Goal State unreachable");
    	}
		
		
					
	}
}