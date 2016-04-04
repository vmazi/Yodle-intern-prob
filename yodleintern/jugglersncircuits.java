package yodleintern;

public class jugglersncircuits {

	//this method goes through the file of circuits and jugglers and stores them
	//if you modify the input, look in here to modify certain values!!
	public static void parseFile(final circuit_table root) {
		int i = 0;
		int l = 0;
		String s;
		String name;
		final StringBuilder str = new StringBuilder(); 
		String[] f; 
		juggler j;
		int handeye;
		int endurance;
		int pizazz;
		while (true) {

			s = IO.readLine();
			if (s == null) {
				return;

			}
			if (s.length() == 0) {
				continue;
			}
			if (s.charAt(0) == 'J') {  //if reading a juggler
				i = i + 2;
				while ((Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i))) && i < s.length()) {
					str.append(s.charAt(i));
					i++;
				}

				name = str.toString();
				while (!Character.isDigit(s.charAt(i)) && i < s.length()) {
					i++;
				}
				str.setLength(0);
				while (Character.isDigit(s.charAt(i)) && i < s.length()) {
					str.append(s.charAt(i));
					i++;
				}
				handeye = Integer.parseInt(str.toString());
				while (!Character.isDigit(s.charAt(i)) && i < s.length()) {
					i++;
				}
				str.setLength(0);
				while (Character.isDigit(s.charAt(i)) && i < s.length()) {
					str.append(s.charAt(i));
					i++;
				}
				endurance = Integer.parseInt(str.toString());
				while (!Character.isDigit(s.charAt(i)) && i < s.length()) {
					i++;
				}
				str.setLength(0);
				while (Character.isDigit(s.charAt(i)) && i < s.length()) {
					str.append(s.charAt(i));
					i++;
				}
				pizazz = Integer.parseInt(str.toString());

				str.setLength(0);
				f = new String[10];        //IMPORTANT this assumes that jugglers all have 10 faves
											//change as u desire
				i++;
				for (int k = 0; k < 10 && i < s.length(); k++) { //k<10 because 10 faves, change as u wish
					while (i < s.length() && (Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
						str.append(s.charAt(i));
						i++;
					}
					i++;
					f[k] = str.toString();
					str.setLength(0);
				}
				j = new juggler(name, handeye, endurance, pizazz, f, root);
				juggler.addjuggler(j, root); 			//add to circuit roster
				i = 0;
			} else if (s.charAt(0) == 'C') {			//reading a circuit
				i = i + 2;
				while ((Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i))) && i < s.length()) {
					str.append(s.charAt(i));
					i++;
				}

				name = str.toString();
				while (!Character.isDigit(s.charAt(i)) && i < s.length()) {
					i++;
				}
				str.setLength(0);
				while (Character.isDigit(s.charAt(i)) && i < s.length()) {
					str.append(s.charAt(i));
					i++;
				}
				handeye = Integer.parseInt(str.toString());
				while (!Character.isDigit(s.charAt(i)) && i < s.length()) {
					i++;
				}
				str.setLength(0);
				while (Character.isDigit(s.charAt(i)) && i < s.length()) {
					str.append(s.charAt(i));
					i++;
				}
				endurance = Integer.parseInt(str.toString());
				while (!Character.isDigit(s.charAt(i)) && i < s.length()) {
					i++;
				}
				str.setLength(0);
				while (i < s.length() && Character.isDigit(s.charAt(i))) {
					str.append(s.charAt(i));
					i++;
				}
				pizazz = Integer.parseInt(str.toString());

				str.setLength(0);
				
				//2nd param is #jugglers per this circuit, modify as u wish.
				root.list[l] = new circuit(name, 6, handeye, endurance, pizazz);
				l++;
				i = 0;
			}
		}
	}

	//this method generates the favorites of each jugglers with their score for each one
	public static String parsefaves(final String[] f, final int[] score) {
		final StringBuilder str = new StringBuilder();
		for (int i = 0; i < f.length; i++) {
			str.append(f[i]);
			str.append(':');
			str.append(score[i]);
			str.append(' ');

		}
		return str.toString();
	}

	//this method generates the final data for each circuit after all the jugglers are placed
	public static String Generate_circ(final circuit circ) {
		final StringBuilder str = new StringBuilder();
		str.append(circ.name);
		str.append(' ');
		for (int i = 0; i < circ.lineup.length; i++) {
			str.append(circ.lineup[i].name);
			str.append(' ');
			str.append(parsefaves(circ.lineup[i].fave, circ.lineup[i].favescore));
			str.append(',');

		}
		str.append(System.getProperty("line.separator"));
		return str.toString();

	}

	//this is where everything comes together, look here to change certain values if changing input.
	public static void main(final String args[]) {
		
		final circuit_table root = new circuit_table(2000);    //cuz 2000 circuits, change as desired

		
		IO.openFile("test2.txt"); //this is the input file name, change as desired
		IO.newFile("test3.txt"); //output file name
		parseFile(root); //read input file
 
		for (int i = 0; i < root.list.length; i++) {

			IO.writeLine(Generate_circ(root.list[i]));

		}

		return;
	}
}
