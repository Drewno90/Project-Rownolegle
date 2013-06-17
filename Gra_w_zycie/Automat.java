
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

	
	
}
