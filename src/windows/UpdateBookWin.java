package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import dao.BookDao;
import entity.Book;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class UpdateBookWin {

	public JFrame frame;
	private JTextField name;
	private JTextField publishing;
	private JTextField stock;
	private JTextField price;
	private BookDao dao = new BookDao();
	private Book book;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBookWin window = new UpdateBookWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateBookWin(Book book) {
		this.book = book;
		initialize();
	}
	public UpdateBookWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 386, 412);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[120.00][270.00,grow][123.00]", "[36.00][48.00][51.00][59.00][55.00][58.00][]"));
		
		JLabel label = new JLabel("\u4FEE\u6539\u56FE\u4E66");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		frame.getContentPane().add(label, "cell 0 0 3 1,alignx center,growy");
		
		JLabel label_1 = new JLabel("\u4E66\u540D");
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		frame.getContentPane().add(label_1, "cell 0 1,alignx trailing");
		
		name = new JTextField();
		name.setText(book.getName());
		frame.getContentPane().add(name, "cell 1 1,growx");
		name.setColumns(10);
		
		JLabel label_2 = new JLabel("\u51FA\u7248\u5546");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		frame.getContentPane().add(label_2, "cell 0 2,alignx trailing");
		
		publishing = new JTextField();
		publishing.setText(book.getPublishing());
		frame.getContentPane().add(publishing, "cell 1 2,growx");
		publishing.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5E93\u5B58");
		label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		frame.getContentPane().add(label_3, "cell 0 3,alignx trailing");
		
		stock = new JTextField();
		stock.setText(book.getStock().toString());
		frame.getContentPane().add(stock, "cell 1 3,growx");
		stock.setColumns(10);
		
		JLabel label_4 = new JLabel("\u5355\u4EF7");
		label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		frame.getContentPane().add(label_4, "cell 0 4,alignx trailing");
		
		price = new JTextField();
		price.setText(book.getPrice().toString());
		frame.getContentPane().add(price, "cell 1 4,growx");
		price.setColumns(10);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = JOptionPane.showConfirmDialog(null, "È·ÈÏÐÞ¸ÄÃ´?", "ÐÞ¸ÄÍ¼Êé",JOptionPane.YES_NO_OPTION);
				System.out.println(m);
				if(m==0) {
					try {
											
						book = new Book(book.getId(),name.getText(),publishing.getText()
								,Double.parseDouble(price.getText()),Integer.parseInt(stock.getText()));
						
						dao.updateBook(book);
						JOptionPane.showMessageDialog(null, "ÐÞ¸Ä³É¹¦");
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									BookWin window = new BookWin();
									window.frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
						frame.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 1 6,alignx right");
		
		JButton button = new JButton("\u53D6\u6D88");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							BookWin window = new BookWin();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.dispose();
			}
		});
		frame.getContentPane().add(button, "cell 2 6");
	}

}
