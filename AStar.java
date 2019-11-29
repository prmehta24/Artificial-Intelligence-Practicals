import java.util.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.lang.Math;
class Puzzle{
 String pstate;
 int depth;
 public Puzzle(){
	pstate="";
	depth=0;
}
public Puzzle(String state, int d){
	pstate=state;
	depth=d;
}
}
public class AStar
{
	static int maxdepth=0;
	static String initial = "12345678x";
	static String goal = "87654321x";
	static int countstates=0;
	static boolean reached=false;
	static Comparator<Puzzle> stringComparator = new Comparator<Puzzle>() {
            @Override
            public int compare(Puzzle obj1, Puzzle obj2) {
            	String s1 = obj1.pstate;
				String s2 = obj2.pstate;
				int g1=obj1.depth;
				int g2=obj2.depth;
				int h1=0;
            	int h2=0;
                for(int i=0;i<goal.length();i++)
                {
                	if(s1.charAt(i)!=goal.charAt(i))
                	{
                		h1++;
                	}
                	if(s2.charAt(i)!=goal.charAt(i))
                	{
                		h2++;
                	}

                }
				int f1=g1+h1;
				int f2=g2+h2;
				 if(f1==f2)
				{
					   if(h1==h2)
					{
						return g1-g2;
					}
					return h1-h2;
				} 
                return f1-f2;
        }
     };
	static PriorityQueue<Puzzle> q = new PriorityQueue<>(stringComparator);
	
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
	public static void printstate(Puzzle obj)
	{
		String state=obj.pstate;
		
		System.out.println("------");
		System.out.println("Depth: "+obj.depth);System.out.println(" ");
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
	public static void BFS(LinkedHashSet<Puzzle> visited, Puzzle state, Puzzle goal)
	{
		//System.out.println("Entering BFS");
		
			visited.add(state);
			q.add(state);
			while(q.size()!=0)
			{
				//System.out.println("In loop");
				countstates++;
				if(countstates>181295)
				{
					break;
				}
				
				//System.out.println("Queue: "+q.toString());
				Puzzle node=q.remove();
				//printstate(node);
				if(node.depth>maxdepth)
				{
					maxdepth=node.depth;
				}
				if((node.pstate).equals(goal.pstate))
				{
					System.out.println("Reached at Depth: "+node.depth);
					System.out.println("Max Depth Traversed: "+maxdepth);
					reached=true;
					break;
				}
				int moveoptions[]=moves(node.pstate);
				//System.out.println("Got moves");
				for(int i=0;i<moveoptions.length;i++)
				{
					//System.out.println("In for loop");
					Puzzle nextstate=new Puzzle(swap(node.pstate,moveoptions[i]),(node.depth+1));
					//System.out.println("Got nextstate");
					if(checks(visited,nextstate)==false)
					{
						//System.out.println("Adding to q");
						visited.add(nextstate);
						q.add(nextstate);
					}
				}

			}
			
		
		
 				
 		
	}
	public static boolean checks(LinkedHashSet<Puzzle> visited, Puzzle obj)
	{
		 Iterator<Puzzle> itr = visited.iterator();
        while(itr.hasNext()){
            if(((itr.next()).pstate).equals(obj.pstate))
			{
				//System.out.println("Returning true");
				return true;
			}
        }
		//System.out.println("Return false");
		return false;
		
	}
	public static void main(String args[])
	{
		double startTime = System.nanoTime();
		Puzzle initObj = new Puzzle(initial,0);
		Puzzle goalObj = new Puzzle(goal,0);
		LinkedHashSet<Puzzle> visited=new LinkedHashSet<Puzzle>(); 
		BFS(visited,initObj,goalObj);
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