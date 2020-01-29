import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class Genealogical {
    
	class person implements Comparable<Object>{
		String name;
		String birthDate;
		String deathDate;
		person mother;
		person father; 
		ArrayList<person> children;
		
		public person(String name) {
			this.name = name;
			children = new ArrayList<person>();
		}
		public boolean compare(person p) {
			return (this.name.equals(p.name));
		}
		@Override
		public int compareTo(Object o) {
			return this.name.compareTo(((person) o).name);
		}
		
	}
	
	public static void main(String[] args) {
		Genealogical run = new Genealogical();
		run.run();
	}
	
	public void run() {
		Scanner scan = new Scanner(System.in);
		Hashtable<String, person> people = new Hashtable<String, person>();
		int count = 0;
		
		while (true) {
			String s = scan.next();
			if (s.equals("QUIT")) {
				scan.close();
				return;
			}
			String[] words = scan.nextLine().split(":");
			
			if (s.equals("BIRTH")) {
				String child = words[0].trim();
				String date = words[1].trim();
				String mother = words[2].trim();
				String father = words[3].trim();
				person c = new person("");
				person m;
				person f;

				if (people.containsKey(child)){
					c = people.get(child);
					c.birthDate = date;
				} else {
					c = new person(child);
					c.birthDate = date;
					people.put(child, c);
				}
				if (people.containsKey(mother)){
					m = people.get(mother);
					m.children.add(c);
				} else {
					m = new person(mother);
					m.children.add(c);
					people.put(mother, m);
				}
				if (people.containsKey(father)){
					f = people.get(father);
					f.children.add(c);
				} else {
					f = new person(father);
					f.children.add(c);
					people.put(father, f);
				}
				c.mother = m;
				c.father = f;
			}
			else if (s.equals("DEATH")) {
				people.get(words[0].trim()).deathDate = words[1].trim();
			}
			
			if (s.equals("ANCESTORS")) {
				count++; 
				if(count > 1) {
					System.out.println();
				}
				String person = words[0].trim();
				person p = people.get(person);
				System.out.println("ANCESTORS of " + person);
				printAncestors(p, 1);
			}
			if (s.equals("DESCENDANTS")) {
				count++; 
				if(count > 1) {
					System.out.println();
				}
				String person = words[0].trim();
				person p = people.get(person);
				System.out.println("DESCENDANTS of " + person);
				printDescendants(p, 1);
			}
		}
	}

	public void printAncestors(person p, int gen) {
		if (p.mother == null) {
			return;
		}
		for (int i = 0; i < gen; i++) {
			System.out.print("  ");
		}
		person first; 
		person last;
		if (p.mother.compareTo(p.father) < 0) {
			first = p.mother;
			last = p.father;
		} else {
			first = p.father;
			last = p.mother;
		}
		System.out.print(first.name);
		if (first.birthDate != null) {
			System.out.print(" " + first.birthDate + " -");
			if (first.deathDate != null) {
				System.out.print(" " + first.deathDate);
			}
		}
		System.out.println();
		printAncestors(first, gen+1);
		
		for (int i = 0; i < gen; i++) {
			System.out.print("  ");
		}
		System.out.print(last.name);
		if (last.birthDate != null) {
			System.out.print(" " + last.birthDate + " -");
			if (last.deathDate != null) {
				System.out.print(" " + last.deathDate);
			}
		}
		System.out.println();
		printAncestors(last, gen+1);
	}
	
	public void printDescendants(person p, int gen) {
        
		Collections.sort(p.children);
		for (int i = 0; i < p.children.size(); i++) {
			for (int j = 0; j < gen; j++) {
				System.out.print("  ");
			}
			person p2 = p.children.get(i);
			System.out.print(p2.name);
			if (p2.birthDate != null) {
				System.out.print(" " + p2.birthDate + " -");
				if (p2.deathDate != null) {
					System.out.print(" " + p2.deathDate);
				}
			}
			System.out.println();
			printDescendants(p2, gen+1);
		}
	}
}