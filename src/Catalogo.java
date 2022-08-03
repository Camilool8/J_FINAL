
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.Connection;


public class Catalogo implements ActionListener {

	private JFrame frame;
	public DefaultTableModel modelo;
	public static JTable tabla;
	public JScrollPane scroll;
	public DefaultTableCellRenderer centerRenderer;

	/**
	 * Create the application.
	 */
	public Catalogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 755, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Catalogo de Libros");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(255, 7, 238, 40);
		frame.getContentPane().add(lblNewLabel);
		
		modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Titulo", "Autor", "Genero", "Precio", "Cantidad"
				}
			);
		tabla = new JTable(modelo);
		scroll = new JScrollPane(tabla);
		scroll.setBounds(10, 60, 720, 485);
		frame.getContentPane().add(scroll);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(25);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(180);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(180);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(57);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabla.setRowHeight(30);
		for (int i = 0; i < tabla.getColumnCount(); i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					setHorizontalAlignment(SwingConstants.CENTER);
					return this;
				}
			});
		}
		tabla.setDefaultEditor(Object.class, null);
		tabla.setDefaultRenderer(Object.class, null);
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			//mouse clicked
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int row = tabla.getSelectedRow();
					if (row >= 0) {
						String id = tabla.getValueAt(row, 0).toString();
						String titulo = tabla.getValueAt(row, 1).toString();
						String autor = tabla.getValueAt(row, 2).toString();
						String genero = tabla.getValueAt(row, 3).toString();
						String precio = tabla.getValueAt(row, 4).toString();
						String cantidad = tabla.getValueAt(row, 5).toString();
						System.out.println(id + " " + titulo + " " + autor + " " + genero + " " + precio + " " + cantidad);
						new InfoLibro();
					}
				}
			}
		});

		try{
		
		Conexion con = new Conexion();
		Conexion con1 = (Conexion) con.clone();
		Connection con2 = con1.getConnection();

		String sql = "SELECT * FROM libro";
		
		java.sql.Statement st = con2.createStatement();
		
		java.sql.ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			modelo.addRow(new Object[]{
					rs.getInt("id"),
					rs.getString("titulo"),
					rs.getString("autor"),
					rs.getString("genero"),
					rs.getInt("precio"),
					rs.getInt("cantidad")
			});
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
}
