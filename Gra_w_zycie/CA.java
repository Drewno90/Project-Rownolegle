import java.util.Random;


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
	
	/**
	 * Generuje randomowe stany dla calej przestrzeni
	 */
	public void genRandomStates() {
		Random rand = new Random();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				tab[i][j] = rand.nextInt(2);
	}
	
	/**Czysci przestrzen */
	public void clear() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				tab[i][j] = 0;
	}
	
	public void genNext() {

		long startTime, stopTime;
		
		int tmp[][] = new int[size][size];
		thread = new MyThread[threadNumber];
		for (int i = 0; i < threadNumber; i++) {
			thread[i] = new MyThread(size, i, tmp,0,(i+1)*(size/threadNumber));
		}
	}


}
