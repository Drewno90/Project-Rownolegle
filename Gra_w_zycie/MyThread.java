
public class MyThread extends Thread implements Runnable {

	/** szerokoœæ */
	int size;
	/** numer rzêdu */
	int x;
	/** tablica do której zapisujemy wyniki */
	int tmp[][];
	/**rzad od ktorego zaczynamy obliczenia*/
	int rowStart;
	/**rzad na ktorym konczym obliczenia*/
	int rowEnd;

	public MyThread(int size, int x, int tmp[][], int start, int end) {
		this.x = x;
		this.size = size;
		this.tmp = tmp;
		this.rowStart = start;
		this.rowEnd = end;
	}

	@Override
	public void run() {

			}
	}
}
