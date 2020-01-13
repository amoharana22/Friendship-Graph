package friends;

import java.util.*;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {

		boolean[] visits = new boolean[g.members.length];
		
		Queue<Person> q = new Queue<>();
		
		ArrayList<String> shortestChain=new ArrayList<>();
		
		if(!p1.equals(p2)) {
			q.enqueue(g.members[g.map.get(p1)]);
			
			Queue<ArrayList<String>> paths = new Queue<>();
		
			ArrayList<String> correspath = new ArrayList<>();
			correspath.add(g.members[g.map.get(p1)].name);
			paths.enqueue(correspath);
			
			while(!q.isEmpty()) {
				Person man = q.dequeue();
			
				visits[g.map.get(man.name)] = true; 
				
				ArrayList<String> list = paths.dequeue();

				for(Friend ptr=g.members[g.map.get(man.name)].first; ptr!=null; ptr=ptr.next) {
					if(!visits[ptr.fnum]) {
			
						ArrayList<String> temp = new ArrayList<>(list);
						
						temp.add(g.members[ptr.fnum].name);
						
						
						if(g.members[ptr.fnum].name.equals(p2)) {
							
							for( int i=0; i<temp.size(); i++) {
								shortestChain.add(temp.get(i));
							}
						while(!q.isEmpty()) {
							q.dequeue();
						}
							break;
							
						}
						
						visits[ptr.fnum] = true;
						q.enqueue(g.members[ptr.fnum]);
						paths.enqueue(temp);
					}

					
				}
			}
			
		}
		
			return shortestChain;
		
		


	}


	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
	
		//Runs Bfs and adds the neighbors if they are in the clique 
		
		ArrayList<ArrayList<String>> cliques=new ArrayList<>();
		
		
		
		boolean[] visited=new boolean[g.members.length];
		
		
		//Modified BFS code 
		for(int j=0; j<g.members.length; j++) {
			
		
			if(g.members[j].student && !visited[g.map.get(g.members[j].name)] && g.members[j].school.equals(school) ) {
				
				
				
				ArrayList<String> Clique=new ArrayList<>();

				Clique.add(g.members[j].name);
				
				visited[g.map.get(g.members[j].name)]=true;
				
				Stack<Person> neighbor=new Stack<>();
				 neighbor.push(g.members[j]);
				 
				 while(!neighbor.isEmpty()) {
					 
					 for(Friend ptr=neighbor.pop().first; ptr!=null; ptr=ptr.next) {
						 
						 if(!visited[ptr.fnum] && g.members[ptr.fnum].student && g.members[ptr.fnum].school.equals(school)) {
							 Clique.add(g.members[ptr.fnum].name);
							 visited[ptr.fnum]=true;
							 neighbor.push(g.members[ptr.fnum]);
							 
							 }						 								 
					 }
					 
					 
				 }
				 
				 
				

				if(!Clique.isEmpty()) {
				cliques.add(Clique);
				}
			}
			}

	return cliques;
		
	}


	
}