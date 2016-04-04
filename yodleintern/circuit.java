package yodleintern;

class circuit_table {

	circuit[] list;

	public circuit_table(final int size) {
		this.list = new circuit[size];
		for (int i = 0; i < list.length; i++) {
			list[i] = null;
		}

		return;
	}

}

public class circuit {
	String name;
	int[] qualify;
	juggler[] lineup;

	public circuit(final String name, final int size, final int handeye, final int endurance, final int pizzazz) {
		this.name = name;
		this.qualify = new int[3];
		this.qualify[0] = handeye;
		this.qualify[1] = endurance;
		this.qualify[2] = pizzazz;
		this.lineup = new juggler[size];

	}
}
