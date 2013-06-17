
public class MyThread extends Thread implements Runnable {

	/** szeroko�� */
	int size;
	/** numer rz�du */
	int x;
	/** tablica do kt�rej zapisujemy wyniki */
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
		for (int i = rowStart; i < rowEnd; i++)
			for (int y = 0; y < size; y++) {
				CA.calcNextCell(tmp, i, y);
			}
		super.run();
	}
}
