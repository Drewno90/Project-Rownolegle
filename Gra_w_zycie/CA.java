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
/**Wyznacz stan komorki w nastepnym kroku*/
public static void calcNextCell(int tmp[][],int x, int y){
	int sum = calcSum(x, y);

	if (sum == 3 && tab[x][y] == 0)
		tmp[x][y] = 1;
	else if ((sum == 3 || sum == 2) && tab[x][y] == 1)
		tmp[x][y] = 1;
	else
		tmp[x][y] = 0;
}

/**
 * Wyznacza sume sasiadow zywych dla danej komorki
 * @param x x komorki
 * @param y y komorki
 * @return liczba zywych sasiadow
 */
private static int calcSum(int x, int y) {
	int sum = 0;
	int u[] = new int[3];
	int d[] = new int[3];
	int l;
	int p;

	// System.out.println(x + " " + y);

	// góra:-------------------
	if (y == 0) {
		if (x == 0)
			u[0] = tab[size - 1][size - 1];
		else
			u[0] = tab[x - 1][size - 1];
		u[1] = tab[x][size - 1];
		if (x == size - 1)
			u[2] = tab[0][size - 1];
		else
			u[2] = tab[x + 1][size - 1];
	} else {
		if (x == 0)
			u[0] = tab[size - 1][y - 1];
		else
			u[0] = tab[x - 1][y - 1];
		u[1] = tab[x][y - 1];
		if (x == size - 1)
			u[2] = tab[0][y - 1];
		else
			u[2] = tab[x + 1][y - 1];
	}

	// dó³:------------------
	if (y == size - 1) {
		if (x == 0)
			d[0] = tab[size - 1][0];
		else
			d[0] = tab[x - 1][0];
		d[1] = tab[x][0];
		if (x == size - 1)
			d[2] = tab[0][0];
		else
			d[2] = tab[x + 1][0];
	} else {
		if (x == 0)
			d[0] = tab[size - 1][y + 1];
		else
			d[0] = tab[x - 1][y + 1];
		d[1] = tab[x][y + 1];
		if (x == size - 1)
			d[2] = tab[0][y + 1];
		else
			d[2] = tab[x + 1][y + 1];
	}

	// lewo:-------------------
	if (x == 0) {
		l = tab[size - 1][y];
	} else {
		l = tab[x - 1][y];
	}

	// prawo:--------------------
	if (x == size - 1) {
		p = tab[0][y];
	} else {
		p = tab[x + 1][y];
	}

	// sumowanie
	sum = u[0] + u[1] + u[2] + l + 0 + p + d[0] + d[1] + d[2];

	return sum;
}

/**Rysuje przestrzen
 * @param g obiekt graficzny na ktorym rysujemy
 */
public void draw(Graphics g) {
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, 500, 500);
	g.setColor(new Color(255,110,180));
	for (int x = 0; x < size; x++) {
		for (int y = 0; y < size; y++) {
			if (tab[x][y] == 1)
				g.fillRect(x * GameWindow.WIDTH, y * GameWindow.WIDTH,
						GameWindow.WIDTH*GameWindow.thickness, GameWindow.WIDTH*GameWindow.thickness);

		}
	}
}
}