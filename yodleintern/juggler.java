package yodleintern;

public class juggler {
	String name;
	int[] skills;
	String[] fave;
	int[] favescore;
	int kickscore;
	int currfave;

	public juggler(final String name, final int handeye, final int endurance, final int pizzazz, final String[] fave,
			final circuit_table root) {
		this.name = name;
		this.skills = new int[3];
		this.skills[0] = handeye;
		this.skills[1] = endurance;
		this.skills[2] = pizzazz;
		this.fave = fave;
		this.kickscore = 0;
		this.favescore = new int[fave.length];
		for (int i = 0; i < favescore.length; i++) {
			favescore[i] = get_currfave(this.skills, this.fave[i], root);
		}
	}

	//gets the score of the juggler for the circuit they are looking at
	private static int get_currfave(final int[] skills, final String circname, final circuit_table root) {
		final int index = get_index(circname);
		int currfave;
		currfave = dotproduct(skills, root.list[index].qualify);
		return currfave;
	}

//gets the index of the circuit based on its name
	private static int get_index(final String name) {
		final String str = name.substring(1, name.length());
		final int index = Integer.parseInt(str);
		return index;

	}

//computes score
	private static int dotproduct(final int[] a, final int[] b) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + (a[i] * b[i]);
		}
		return sum;
	}

	//if a juggler is kicked, they go here
	//and update their kick score.
	private static void kickproc(final juggler person, final circuit_table root) {
		person.kickscore++;
		addjuggler(person, root);
	}

	//checks if the circuit the juggler is looking at is one of their faves
	private static boolean check_if_fave(final String name, final String[] fave) {

		for (int i = 0; i < fave.length; i++) {
			if (name.equals(fave[i])) {
				return true;
			}

		}
		return false;

	}

	
	//So, first the program tries to add the juggler to its favorite. If the juggler's favorite is full,
	//the juggler sees if they are better than the worst on that circuit
	// if so, kick the worst and add the juggler.
	//if not kick the juggler(tell them to look at next fave)
	//if kicked, depending on your kick score, you choose your next circuit. Everytime a juggler
	// gets kicked, their kick score goes up.
	// kick score is used to pick which favorite the juggler is looking at
	// i.e if kickscore is 0, they are on their 1st fave, if kickscore is 9, they are on their 10th fave
	//
	// if juggler has been kicked from all their favorites, the juggler must find either the first
	// empty circuit, and if no empties, the first circuit they can kick someone out off.
	//remember, kicks only happen if the juggler is better than the worst on that circuit.
	public static void addjuggler(final juggler person, final circuit_table root) {
		circuit current;
		juggler temp;

		if (person.kickscore < person.fave.length) {  				//if not kicked from all faves
			person.currfave = get_currfave(person.skills, person.fave[person.kickscore], root);
			current = root.list[get_index(person.fave[person.kickscore])];
			for (int i = 0; i < current.lineup.length; i++) {
				if (current.lineup[i] == null) {  					//see if current fave is empty
					current.lineup[i] = person;
					return;
				}
			}
			quickSort(current.lineup, 0, current.lineup.length - 1);
																	//if current fave is not empty, see if u can kick worst
			if (person.currfave > current.lineup[0].currfave) {
				temp = current.lineup[0];
				current.lineup[0] = person;

				quickSort(current.lineup, 0, current.lineup.length - 1);
				kickproc(temp, root);

				return;
			} else {												//if cant kick worst, kick self 
				kickproc(person, root);
				return;
			}

		} else {												//if you've checked all ur faves
			for (int i = 0; i < root.list.length; i++) {
				current = root.list[i];
				if (!check_if_fave(current.name, person.fave)) {
					for (int j = 0; j < current.lineup.length; j++) { //find an empty circuit
						if (current.lineup[j] == null) {
							person.currfave = get_currfave(person.skills, current.name, root);
							current.lineup[j] = person;
							return;
						}
					}
				}
			}
			for (int i = 0; i < root.list.length; i++) {	//if here, then no empty circuits
				current = root.list[i];						//so, find the first one you can kick someone from
				person.currfave = get_currfave(person.skills, current.name, root);
				if (!check_if_fave(current.name, person.fave)) { //make sure ur not rechecking faves
					quickSort(current.lineup, 0, current.lineup.length - 1); //order current circ 
					if (person.currfave > current.lineup[0].currfave) { //see if better than worst
						temp = current.lineup[0];
						current.lineup[0] = person;

						quickSort(current.lineup, 0, current.lineup.length - 1);
						kickproc(temp, root);

						return;
					} else {
						kickproc(person, root);			//if not better than worst, kick self
						return;
					}
				}
			}
		}
	}

	//this method sorts the circuit from worst to best, used to determine kicked juggler
	private static void quickSort(final juggler[] a, final int start, final int end) {
		// index for the "left-to-right scan"
		int i = start;
		// index for the "right-to-left scan"
		int j = end;

		// only examine arrays of 2 or more elements.
		if (j - i >= 1) {
			// The pivot point of the sort method is arbitrarily set to the first element int the array.
			final int pivot = a[i].currfave;
			// only scan between the two indexes, until they meet.
			while (j > i) {
				// from the left, if the current element is lexicographically less than the (original)
				// first element in the String array, move on. Stop advancing the counter when we reach
				// the right or an element that is lexicographically greater than the pivot String.
				while (a[i].currfave <= pivot && i <= end && j > i) {
					i++;
				}
				// from the right, if the current element is lexicographically greater than the (original)
				// first element in the String array, move on. Stop advancing the counter when we reach
				// the left or an element that is lexicographically less than the pivot String.
				while (a[j].currfave >= pivot && j >= start && j >= i) {
					j--;
				}
				// check the two elements in the center, the last comparison before the scans cross.
				if (j > i)
					swap(a, i, j);
			}
			// At this point, the two scans have crossed each other in the center of the array and stop.
			// The left partition and right partition contain the right groups of numbers but are not
			// sorted themselves. The following recursive code sorts the left and right partitions.

			// Swap the pivot point with the last element of the left partition.
			swap(a, start, j);
			// sort left partition
			quickSort(a, start, j - 1);
			// sort right partition
			quickSort(a, j + 1, end);
		}
	}

	/**
	 * This method facilitates the quickSort method's need to swap two elements, Towers of Hanoi style.
	 */

	private static void swap(final juggler[] a, final int i, final int j) {
		final juggler temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
