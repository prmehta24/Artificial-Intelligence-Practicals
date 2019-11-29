import java.util.*;
import java.lang.Math;
public class BestFS
{
	static String initial = "12345678x";
	static String goal = "87654321x";
	static int countstates=0;
	static boolean reached=false;
	static Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
            	int count1=0;
            	int count2=0;
                for(int i=0;i<goal.length();i++)
                {
                	if(s1.charAt(i)!=goal.charAt(i))
                	{
                		count1++;
                	}
                	if(s2.charAt(i)!=goal.charAt(i))
                	{
                		count2++;
                	}

                }
                return count1-count2;
        }
     };
	static PriorityQueue<String> q = new PriorityQueue<>(stringComparator);
	
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
	public static void BFS(LinkedHashSet<String> visited, String state, String goal)
	{
		
			visited.add(state);
			q.add(state);
			while(q.size()!=0)
			{
				countstates++;
				//System.out.println("Queue: "+q.toString());
				String node=q.remove();
				//printstate(node);
				if(node.equals(goal))
				{
					reached=true;
					break;
				}
				int moveoptions[]=moves(node);
				for(int i=0;i<moveoptions.length;i++)
				{
					String nextstate=swap(node,moveoptions[i]);
					if(visited.contains(nextstate)==false)
					{
						visited.add(nextstate);
						q.add(nextstate);
					}
				}

			}
			
		
		
 				
 		
	}
	public static void main(String args[])
	{
		double startTime = System.nanoTime();

		
		
		
		LinkedHashSet<String> visited=new LinkedHashSet<String>(); 
		BFS(visited,initial,goal);
		double endTime   = System.nanoTime();
		double totalTime = endTime - startTime;
		System.out.println("Time Taken: "+(totalTime)/1000000000);
		if(reached)
		{
			System.out.println("Solution Found");
			System.out.println("States Traversed: "+countstates);
		}
		else
		{
			System.out.println("Solution Not Found");
			System.out.println("States Traversed: "+countstates);
		}
		
		
		
					
	}
}