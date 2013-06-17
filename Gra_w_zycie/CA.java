import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class CA {

	/**rozmiar*/
	static int size;
	/**tablica z wartosciami komorek*/
	static int tab[][];
	
	/**watki liczace nastepne kroki*/
	MyThread[] thread;
	/**liczba watkow*/
	int threadNumber = 2;

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
	 * Zmienia stan podanej komorki na przeciwny
	 * @param x x w przestrzeni
	 * @param y y w przestrzeni
	 */
	
	public void setState(int x, int y) {
		if (tab[x][y] == 1)
			tab[x][y] = 0;
		else
			tab[x][y] = 1;
	}

	
	/**
	 * Funkcja tworzaca Glidera
	 * @param x x w przestrzeni
	 * @param y y w przestrzeni
	 */
	public void setGlider(int x, int y){
		setState(x, y); setState((x+1)%size, y%size); setState((x+2)%size, y%size);
													setState((x+2)%size, (y+1)%size);
						setState((x+1)%size, (y+2)%size);							
	}
	
	/**
	 * Funkcja tworzaca Die Hard
	 * @param x x w przestrzeni
	 * @param y y w przestrzeni
	 */
	public void setDieHard(int x, int y){
		setState((x+6)%size, y%size);
		setState((x)%size, (y+1)%size); setState((x+1)%size, (y+1)%size);
		setState((x+1)%size, (y+2)%size);	setState((x+5)%size, (y+2)%size);	setState((x+6)%size, (y+2)%size); setState((x+7)%size, (y+2)%size);					
	}
	
	/**
	 * Funkcja tworzaca Blinkera
	 * @param x x w przestrzeni
	 * @param y y w przestrzeni
	 */
	public void setBlinker(int x, int y){
		setState((x+0)%size, y%size); setState((x+1)%size, y%size); setState((x+2)%size, y%size);
	}
	
	/**
	 * Funkcja tworzaca Mrowki
	 * @param x x w przestrzeni
	 * @param y y w przestrzeni
	 */
	public void setAnts(int x, int y){
		for (int i = 0; i < 10; i++) {

			setState((x+5*i)%size, (y)%size); setState((x+1+5*i)%size, (y)%size);
																setState((x+2+5*i)%size, (y+1)%size); setState((x+3+5*i)%size, (y+1)%size);
																setState((x+2+5*i)%size, (y+2)%size); setState((x+3+5*i)%size, (y+2)%size);
			setState((x+5*i)%size, (y+3)%size); setState((x+1+5*i)%size, (y+3)%size);
			
		}}


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
		//Cstartuj licznik
		startTime = System.nanoTime();
for (int i = 0; i < threadNumber; i++) {
	thread[i].start();
}
for (int i = 0; i < threadNumber; i++) {
	try {
		thread[i].join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



//stopuj licznik
stopTime = System.nanoTime();
tab = tmp;
System.out.println((stopTime - startTime));
	}


}
