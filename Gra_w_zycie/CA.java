
public class CA {

	/**
	 * Konstruktor
	 * @param size rozmiar
	 */
	public CA(int size) {
		this.size = size;
		tab = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				tab[i][j] = 0;

	}


}
