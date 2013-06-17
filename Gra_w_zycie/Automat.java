
public class Automat {

	public void genRandomStates() {
		Random rand = new Random();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				tab[i][j] = rand.nextInt(2);
	}

	public void clear() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				tab[i][j] = 0;
	}

	//CZAS START
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



//CZAS STOP
stopTime = System.nanoTime();
tab = tmp;
System.out.println((stopTime - startTime));
}

	
public static void calcNextCell(int tmp[][],int x, int y){
	int sum = calcSum(x, y);

	if (sum == 3 && tab[x][y] == 0)
		tmp[x][y] = 1;
	else if ((sum == 3 || sum == 2) && tab[x][y] == 1)
		tmp[x][y] = 1;
	else
		tmp[x][y] = 0;
}
	



}
