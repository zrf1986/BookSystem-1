package windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import dao.BookDao;
import entity.Book;
import net.miginfocom.swing.MigLayout;

public class BookWin {

	public JFrame frame;
	private JTextField textField;
	private JTable table;

	JButton deleteButton = new JButton("删除");
	JButton updateButton = new JButton("修改");

	private BookDao dao = new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public BookWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[95.00][124.00,grow][180,grow][180][277.00][180]",
				"[60.00][][136.00,grow][128.00][82.00][]"));
		table = new JTable();
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540E\u53F0\u5E93\u5B58\u7BA1\u7406");
		lblNewLabel.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 40));
		frame.getContentPane().add(lblNewLabel, "cell 1 0 4 1,alignx center,aligny center");

		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// 添加图书
		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0\u56FE\u4E66");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddBookWin window = new AddBookWin();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton_1, "cell 5 0,alignx center,aligny bottom");

		textField = new JTextField();
		frame.getContentPane().add(textField, "cell 2 1 2 1,growx");
		textField.setColumns(10);
		JScrollPane scrollPane = new JScrollPane();
		// 搜索图书
		JButton btnNewButton_2 = new JButton("\u641C\u7D22");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				try {
					if (name.equals("") || name == null) {
						table.setModel(
								new DefaultTableModel(addBooks(), new String[] { "书号", "书名", "出版社", "单价", "库存" }));
						scrollPane.setViewportView(table);
						frame.getContentPane().add(scrollPane, "cell 1 2 4 3,grow");
						return;
					} else {
						Book book = dao.findByName(name);
						System.out.println(book);
						if (book == null) {
							JOptionPane.showMessageDialog(null, "查无此书");
						} else {
							table.setModel(new DefaultTableModel(
									new String[][] { { book.getId() + "", book.getName(), book.getPublishing(),
											book.getPrice() + "", book.getStock() + "" } },
									new String[] { "书号", "书名", "出版社", "单价", "库存" }));
							scrollPane.setViewportView(table);
							frame.getContentPane().add(scrollPane, "cell 1 2 4 3,grow");
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton_2, "cell 4 1,alignx left,aligny center");

		// 删除图书
		JButton deleteBookButton = new JButton("\u5220\u9664\u56FE\u4E66");

		deleteBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "请输入书名", "删除图书", JOptionPane.PLAIN_MESSAGE);

				if (name != null) {
					int m = JOptionPane.showConfirmDialog(null, "确定删除该图书么?", "删除图书", JOptionPane.YES_NO_OPTION);
					System.out.println(name);
					System.out.println(m);
					if (m == 0) {
						try {
							dao.deleteBook(name);
							table.setModel(
									new DefaultTableModel(addBooks(), new String[] { "书号", "书名", "出版社", "单价", "库存" }));
							scrollPane.setViewportView(table);
							frame.getContentPane().add(scrollPane, "cell 1 2 4 3,grow");

						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				}

			}
		});
		frame.getContentPane().add(deleteBookButton, "cell 5 2,alignx center,aligny top");
		frame.getContentPane().add(btnNewButton, "cell 5 5,alignx center,aligny center");
		// 修改图书
		JButton updateButton = new JButton("\u4FEE\u6539\u56FE\u4E66");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Book> books = dao.findAllBook();
					Object[] obj2 = new Object[books.size()];
					for (int i = 0; i < obj2.length; i++) {
						obj2[i] = books.get(i).getName();
					}
					String s = (String) JOptionPane.showInputDialog(null, "请选择修改的图书:\n", "图书",
							JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj2, "==请选择");
					System.out.println(s);

					Book book = dao.findByName(s);

					if (s != null) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									UpdateBookWin window = new UpdateBookWin(book);
									window.frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(updateButton, "cell 5 1,alignx center,aligny top");

		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(addBooks(), new String[] { "书号", "书名", "出版社", "单价", "库存" }));
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane, "cell 1 2 4 3,grow");

	}

	public Object[][] addBooks() {
		// 向表格中添加数据
		List<Book> books = null;
		Object[][] bookT = null;
		try {
			books = dao.findAllBook();
			bookT = new Object[books.size()][5];
			for (int i = 0; i < books.size(); i++) {
				Book book = books.get(i);
				bookT[i] = new Object[] { book.getId(), book.getName(), book.getPublishing(), book.getPrice(),
						book.getStock() };
			}
			return bookT;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
