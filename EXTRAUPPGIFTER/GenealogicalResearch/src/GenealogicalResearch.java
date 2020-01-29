import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class GenealogicalResearch {
	public class Individual implements Comparable<Object> {
		ArrayList<Individual> g_arrChildren;
		Individual g_indMother;
		Individual g_indFather;
		String g_strName;
		String g_strBirth;
		String g_strDeath;

		public Individual(String name) {
			g_arrChildren = new ArrayList<Individual>();
			this.g_strName = name;
		}
		
		@Override
		public int compareTo(Object o) {
			return this.g_strName.compareTo(((Individual) o).g_strName);
		}
	}

	public void ancestors(Individual ind, int level) {
		if(ind.g_indMother == null) {
			return;
		}
		Individual one;
		Individual two;

		if(ind.g_indMother.g_strName.compareTo(ind.g_indFather.g_strName) <= -1) {
			one = ind.g_indMother;
			two = ind.g_indFather;
		} else {
			one = ind.g_indFather;
			two = ind.g_indMother;
		}

		for(int i = 1; i <= level; i++) {
			System.out.print("  ");
		}

		System.out.print(one.g_strName);
		
		if(!(one.g_strBirth == null)) {
			System.out.printf(" %s -", one.g_strBirth);
			if(!(one.g_strDeath == null)) {
				System.out.printf(" %s", one.g_strDeath);
			}
		}
		
		System.out.println();
		ancestors(one, level+1);

		for(int i = 1; i <= level; i++) {
			System.out.print("  ");
		}

		System.out.print(two.g_strName);
		
		if(!(two.g_strBirth == null)) {
			System.out.printf(" %s -", two.g_strBirth);
			if(!(two.g_strDeath == null)) {
				System.out.printf(" %s", two.g_strDeath);
			}
		}
		
		System.out.println();
		ancestors(two, level+1);
	}

	public void descendants(Individual ind, int level) {
		Collections.sort(ind.g_arrChildren);
		
		for (int a = 0; a < ind.g_arrChildren.size(); a++) {
			for(int b = 0; b < level; b++) {
				System.out.print("  ");
			}
			
			Individual person = ind.g_arrChildren.get(a);

			System.out.print(person.g_strName);
			
			if(person.g_strBirth != null) {
				System.out.printf(" %s -", person.g_strBirth);
				if(person.g_strDeath != null) {
					System.out.printf(" %s", person.g_strDeath);
				}
			}
			
			System.out.println();
			descendants(person, level+1);
		}
	}
	
	public void loop() {
		Hashtable<String, Individual> people = new Hashtable<String, Individual>();
		Scanner io = new Scanner(System.in);
		String QUIT_COMMAND = "QUIT";
		String BIRTH_COMMAND = "BIRTH";
		String DEATH_COMMAND = "DEATH";
		String ANCESTORS_COMMAND = "ANCESTORS";
		String DESCENDANTS_COMMAND = "DESCENDANTS";
		Boolean isNewLinePending = false;
		
		while(true) {
			String input = io.next();
			if(input.equals(QUIT_COMMAND)) { 
				io.close();
//				System.out.println();
				return;
			}

			String[] inputValues = io.nextLine().split(":");
			
			if(input.equals(BIRTH_COMMAND)) {
				Individual indChild = null;
				Individual indMother = null;
				Individual indFather = null;
				String strChild = inputValues[0].trim();
				String strDate = inputValues[1].trim();
				String strMother = inputValues[2].trim();
				String strFather = inputValues[3].trim();
				
				if(people.containsKey(strChild)) {
					indChild = people.get(strChild);
					indChild.g_strBirth = strDate;
				} else {
					indChild = new Individual(strChild);
					indChild.g_strBirth = strDate;
					people.put(strChild, indChild);
				}
				
				if(people.containsKey(strMother)) {
					indMother = people.get(strMother);
					indMother.g_arrChildren.add(indChild);
				} else {
					indMother = new Individual(strMother);
					indMother.g_arrChildren.add(indChild);
					people.put(strMother, indMother);
				}

				if(people.containsKey(strFather)) {
					indFather = people.get(strFather);
					indFather.g_arrChildren.add(indChild);
				} else {
					indFather = new Individual(strFather);
					indFather.g_arrChildren.add(indChild);
					people.put(strFather, indFather);
				}

				indChild.g_indMother = indMother;
				indChild.g_indFather = indFather;
			}
			
			if(input.equals(DEATH_COMMAND) || input.equals(ANCESTORS_COMMAND) || input.equals(DESCENDANTS_COMMAND)) {
				int count = 0;
				String person = inputValues[0].trim();
				Individual p = people.get(person);
				if(input.equals(DEATH_COMMAND)) {
					p.g_strDeath = inputValues[1].trim();
				} else if(input.equals(ANCESTORS_COMMAND)) {
					count += 1;
					
					if(count >= 2) {
						System.out.println();
					}
					
					if(isNewLinePending == true) {
						System.out.println();
					} else {
						isNewLinePending = true;
					}
					System.out.println(ANCESTORS_COMMAND + " of " + person);
//					System.out.printf("%s of %s\n", ANCESTORS_COMMAND, person);
					ancestors(p, 1);
//					System.out.println();
				} else if(input.equals(DESCENDANTS_COMMAND)) {
					count += 1;
					
					if(count >= 2) {
						System.out.println();
					}
					
					if(isNewLinePending == true) {
						System.out.println();
					} else {
						isNewLinePending = true;
					}
					System.out.println(DESCENDANTS_COMMAND + " of " + person);
//					System.out.printf("%s of %s\n", DESCENDANTS_COMMAND, person);
					descendants(p, 1);
//					System.out.println();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		GenealogicalResearch gr = new GenealogicalResearch();
		gr.loop();
	}
}