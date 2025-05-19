import java.util.ArrayList;

public class Node {
	
	private double value; // Value stored in this Node
	private String action; // Which operation was used to get the Value
	private int g; // Depth of Node
	private Node parent;
	private Node child_fact;
	private Node child_floor;
	private Node child_root;
	
	/* Constructor */
	public Node () {
		
	}
	public Node (double v) {
		
		this.value = v;
	}
	public Node (long v, String action, int g, Node parent)
	{
		this.value = v;
		this.action = action;
		this.g = g;
		this.parent = parent;
	}
	
	/* Other Methods */
	
	public Double factor (Double f)
	{
		Double sol = 1.0;
		
		for (int i=1; i<=f; i++)
		{
			sol = sol * i;
		}
		
		return sol;
	}
	
	public ArrayList<Node> expandNode()
	{		
		ArrayList<Node> al = new ArrayList<>();		// ArrayList which we will be returning
		Node bad = new Node(-1);					// Negative Node
		
		
		for (int i=0; i<=2; i++)
        {
			
			/* Factorial */
			if (i==0)
			{
				if (this.value > 171.0 || this.value != Math.floor(this.value)) // 171 is the Maximum Value that we can Factor
				{
					al.add(bad);
				}
				else
				{
					Node good = new Node();
					Double a = good.factor(this.value);
						
					good.setValue(a);
					good.setAction("Factorial");
					good.setG(this.g + 1);
					
					al.add(good);
				}
			}
			
			/* Floor */
			if (i==1)
			{
				if (this.value == Math.floor(this.value))
				{
					al.add(bad);
				}
				else
				{
					Node good = new Node();
					Double a = Math.floor(this.value);
					
					good.setValue(a);
					good.setAction("Floor");
					good.setG(this.g + 1);

					
					al.add(good);
				}
			}
			
			/* Root */
			if (i==2)
			{
				
				Node good = new Node();
				Double a = Math.sqrt(this.value);
					
				good.setValue(a);
				good.setAction("Root");
				good.setG(this.g + 1);

					
				al.add(good);

			}
        }		
		return al;
	}
	
	/* Getters - Setters */
	
	public double getValue() {
		return value;
	}
	public void setValue(double a) {
		this.value = a;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getChild_fact() {
		return child_fact;
	}
	public void setChild_fact(Node child_fact) {
		this.child_fact = child_fact;
	}
	public Node getChild_root() {
		return child_root;
	}
	public void setChild_root(Node child_root) {
		this.child_root = child_root;
	}
	public Node getChild_floor() {
		return child_floor;
	}
	public void setChild_floor(Node child_floor) {
		this.child_floor = child_floor;
	}	
}
