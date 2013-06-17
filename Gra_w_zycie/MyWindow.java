import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MyWindow extends JFrame {

	private JPanel contentPane;
	public static JComboBox comboBox;
	
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyWindow frame = new MyWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyWindow() {
		setTitle("GameOfLife");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		final GameWindow gw = new GameWindow();
		gw.setBounds(255, 43, 500, 500);
		contentPane.add(gw);

		final JButton btnStart = new JButton("START");
		
		btnStart.setBounds(85, 44, 89, 23);
		contentPane.add(btnStart);

		JButton btnGenerateRandomStates = new JButton("Generate random states");
		btnGenerateRandomStates.setBounds(24, 159, 209, 23);
		contentPane.add(btnGenerateRandomStates);
		
		//wybór opcji dodawania
		String options[] = {"Single", "Glider", "Die Hard", "Ants","Blinker"};
		comboBox = new JComboBox(options);
		comboBox.setBounds(111, 211, 89, 20);
		comboBox.setSelectedIndex(0);
		contentPane.add(comboBox);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(85, 104, 89, 23);
		contentPane.add(btnClear);
		
		JLabel lblAdd = new JLabel("Add:");
		lblAdd.setBounds(72, 214, 29, 14);
		contentPane.add(lblAdd);
		
		textField = new JTextField();
		textField.setText("1");
		textField.setBounds(111, 254, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblThickness = new JLabel("Thickness:");
		lblThickness.setBounds(36, 257, 65, 14);
		contentPane.add(lblThickness);
	}
}
