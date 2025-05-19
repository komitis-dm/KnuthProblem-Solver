import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {	

	public static void main(String[] args) {
		
		LinkedList<Node> frontier = new LinkedList<>();      //FRONTIER
		ArrayList<Double> checked = new ArrayList<>(); 		 //CHECKED
		Stack<Node> orderSol = new Stack<>();				 // STACK 
		
		Node S = new Node();
		double num = 0; // Selected Number to be found
		int algor = 0;  // Selected Algorithm to be used
	    Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Knuth's Problem");
    	System.out.println("\nChoose Algorithm (Breadth [1] or Iterative [2]): ");
    	algor = sc.nextInt();
		System.out.println("\nChoose Number: ");
	    num = sc.nextInt();
        
	    
	    initializeFrontier(frontier);
	    long timerStart = System.currentTimeMillis(); // Starts the Timer
	    long timeEx;

	    /* Breadth Search is chosen */
		if (algor == 1) 
		{
		    initializeFrontier(frontier);
			S = breadthSearch(frontier, checked, num); // Algorithm Runs //
			timeEx = (System.currentTimeMillis() - timerStart);
		}
		
		/* Iterative Deepening Search is chosen */
		else
		{
		    initializeFrontier(frontier);
			S = iterativeSearch(frontier, checked, num); // Algorithm Runs //
			timeEx = (System.currentTimeMillis() - timerStart);
		}
		
		if (S != null)
		{
			while (true)
			{
				orderSol.push(S);
				S = S.getParent();
				if (S == null || S.getParent() == null)
					break;
			}
			
			while (orderSol.isEmpty() == false)
			{
				Node view = orderSol.pop();
				System.out.println(view.getAction());
			}
			System.out.println("The Algorithm ran for: "+ timeEx+" miliseconds");
		}
		else
			System.out.println("Can't show Solution because we could not find one");		
	}
	
	public static void initializeFrontier(LinkedList<Node> f)
	{
		Node n = new Node(4,"Starting Node (Root)",0,null);
		f.addFirst(n);		
	}
	
	public static Node iterativeSearch(LinkedList<Node> frontier, ArrayList<Double> checked, Double sol)
	{
		Node solution = new Node();
		solution = null;
		int l=6; // Starting with this Value of Depth
		
		while (true)
		{
			//System.out.println("Search up to a Depth of: "+l);
			solution = depthLimitedSearch(frontier, checked, sol, l);
			if (solution != null)
				return solution;
			if (l > 50) // This clause should not exist (look at Code PDF for explanation)
			{
				System.out.println("No Solution");
				return null;
			}
			else
			{
				l++;
				initializeFrontier(frontier); // We need to Re-Initialize both the Frontier and the Checked List,
										  	  // it's like we are using the depthLimitited Search algorithm for the first time...
				checked.clear();
			}
		}
	}
	
	public static Node depthLimitedSearch(LinkedList<Node> frontier, ArrayList<Double> checked, Double sol, int l)
	{
		Node currNode = new Node();
		Node solution = new Node();
		solution = null;
		
		if (frontier.peek().getValue() == sol)
		{
			System.out.println("Solution Found");
			currNode = frontier.pop();
			return currNode;
		}
		while (frontier.isEmpty() == false)
		{
			currNode = frontier.pop();
			//System.out.println("Current Depth is: "+currNode.getG()+" Current Value is: "+currNode.getValue());
			if (currNode.getValue() == sol)
			{
				System.out.println("Solution Found");
				//System.out.println("Solution Depth is "+currNode.getG() );
				return currNode;
			}
			
			if (currNode.getG() > l)
			{
				// Do nothing, we are "cutting" this side of the Tree
			}
			else if (checked.contains(currNode.getValue()) == false && currNode.getG()<l)
			{
				checked.add(currNode.getValue());
				for(Node n : currNode.expandNode())
				{
					if (n.getValue() != -1)
					{
						// Floor Child //
						if (n.getAction().equals("Floor"))
						{
							frontier.push(n);
							currNode.setChild_floor(n);
							n.setParent(currNode);
						}
						
						// Root Child // 
						else if (n.getAction().equals("Root"))
						{
							frontier.push(n);
							currNode.setChild_root(n);
							n.setParent(currNode);
						}
						
						// Factorial Child
						else if (n.getAction().equals("Factorial"))
						{
							frontier.push(n);
							currNode.setChild_fact(n);
							n.setParent(currNode);
						}
					}
				}
			}
		}
		//System.out.println("No Solution");
		return solution;
	}

	public static Node breadthSearch(LinkedList<Node> frontier, ArrayList<Double> checked, Double sol)
	{
		Node currNode = new Node();
		Node solution = new Node();
		
		if (frontier.getLast().getValue() == sol)
		{
			System.out.println("Solution Found");
			solution = frontier.getLast();
			return solution;
		}
		
		while (frontier.isEmpty() == false)
		{
			currNode = frontier.getLast();
			frontier.removeLast();
						
			for(Node n : currNode.expandNode())
			{
				if (n.getValue() != -1)
				{
					if (n.getValue() == sol) // Checks if Node is a Solution
					{
						System.out.println("Solution Found");
						n.setParent(currNode);
						solution = n;
						
						return solution;
					}
					
					if (checked.contains(n.getValue()))
					{
						// Do Nothing 
					}
					
					else
					{
						// Floor Child //
						if (n.getAction().equals("Floor"))
						{
							frontier.addFirst(n);
							checked.add(n.getValue());
							currNode.setChild_floor(n);
							n.setParent(currNode);
						}
						
						// Root Child // 
						else if (n.getAction().equals("Root"))
						{
							frontier.addFirst(n);
							checked.add(n.getValue());
							currNode.setChild_root(n);
							n.setParent(currNode);
						}
						
						// Factorial Child
						else if (n.getAction().equals("Factorial"))
						{
							frontier.addFirst(n);
							checked.add(n.getValue());
							currNode.setChild_fact(n);
							n.setParent(currNode);
						}
					}
				}
			}
		}
		System.out.println("NOT FOUND");
		return solution;
	}
	
}