import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameWindow extends JPanel {

	/** automat na ktorym pracujemy */
	CA automat;
	/** szerokosc komorki na ekranie w pikselach */
	public static final int WIDTH = 1;
	/** pauza miedzy cyklami zycia */
	public static int delay = 10;
	/** grubosc w pikselach */
	public static int thickness = 1;

	public GameWindow() {

		setBorder(BorderFactory.createLineBorder(Color.PINK));

		automat = new CA(500 / WIDTH);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (MyWindow.comboBox.getSelectedIndex()) {
				case 0:
					automat.setState(e.getX() / WIDTH, e.getY() / WIDTH);
					break;
				case 1:
					automat.setGlider(e.getX() / WIDTH, e.getY() / WIDTH);
					break;
				case 2:
					automat.setDieHard(e.getX() / WIDTH, e.getY() / WIDTH);
					break;
				case 3:
					automat.setAnts(e.getX() / WIDTH, e.getY() / WIDTH);
					break;
				case 4:
					automat.setBlinker(e.getX() / WIDTH, e.getY() / WIDTH);
					break;
				default:
					break;
				}
				repaint();
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				automat.setState(e.getX() / WIDTH, e.getY() / WIDTH);
				repaint();
			}
		});
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	/**nastepny cykl zycia*/
	public void iterate() {
		while (true) {
			automat.genNext();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}

	/**czysci ekran*/
	public void clear() {
		automat.clear();
		repaint();
	}

	/**
	 * rysuje przestrzen*/
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		automat.draw(g);
	}

	/**generuje losowe stany w przestrzeni*/
	public void genRandomStates() {
		automat.genRandomStates();
		repaint();
	}

}