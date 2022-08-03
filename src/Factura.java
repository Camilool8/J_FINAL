
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;

public class Factura implements ActionListener {

    protected JFrame frame;
    protected static JButton boton, boton1, boton2;
    protected static JTextField texto;
    public static JTable table;
    protected static JComboBox<String> comboBox;
    protected JScrollPane scrollPane;
    protected static DefaultTableModel model;

    	public Factura() {
		initialize();
	}

    private void initialize(){
        frame = new JFrame();
        frame.setBounds(100, 100, 725, 600);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
        JLabel lblNewLabel = new JLabel(Login.usuario.getText().toUpperCase() + " - " + "VENTAS\r\n");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(580, 5, 150, 20);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Cliente:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(10, 5, 60, 20);
        frame.getContentPane().add(lblNewLabel_1);

        texto = new JTextField();
        texto.setBounds(70, 6, 100, 20);
        frame.getContentPane().add(texto);
        texto.setColumns(10);
        texto.setFont(new Font("Tahoma", Font.BOLD, 14));


        comboBox = new JComboBox<>();
        comboBox.setBounds(10, 35, 695, 20);
        frame.getContentPane().add(comboBox);
        comboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        comboBox.addActionListener(this);

        try{
            Conexion con = new Conexion();
            Conexion con1 = (Conexion) con.clone();
            Connection con2 = con1.getConnection();
            
            String sql = "SELECT * FROM libro";
            java.sql.Statement st = con2.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                String titulos = rs.getString("titulo");
                comboBox.addItem(titulos);
            }
        }catch(Exception e){
            System.out.println(e);
        }



        model = new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Vendedor", "Cliente", "Libro","Genero", "Total"
                }
        );
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 695, 400);
        frame.getContentPane().add(scrollPane);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(62);
		table.setRowHeight(30);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					setHorizontalAlignment(SwingConstants.CENTER);
					return this;
				}
			});
		}
        table.setDefaultEditor(Object.class, null);
		table.setDefaultRenderer(Object.class, null);



        boton = new JButton("Agregar");
        boton.setBounds(10, 470, 695, 23);
        frame.getContentPane().add(boton);
        boton.addActionListener(this);
        
        boton1 = new JButton("Facturar");
        boton1.setBounds(10, 500, 695, 23);
        frame.getContentPane().add(boton1);
        boton1.addActionListener(this);

        boton2 = new JButton("Volver");
        boton2.setBounds(10, 530, 695, 23);
        frame.getContentPane().add(boton2);
        boton2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton){
            try {
                Conexion con = new Conexion();
                Conexion con1 = (Conexion) con.clone();
                Connection con2 = con1.getConnection();
                String sql = "SELECT * FROM libro";
                java.sql.Statement st = con2.createStatement();
                java.sql.ResultSet rs = st.executeQuery(sql);

                while(rs.next()) {
                    String titulo = rs.getString("titulo");
                    String genero = rs.getString("genero");
                    String precio = rs.getString("precio");
                    if(titulo.equals(comboBox.getSelectedItem())){
                        model.addRow(new Object[]{Login.usuario.getText().toUpperCase(), texto.getText().toUpperCase(), titulo, genero, precio});
                        texto.setEditable(false);
                    }
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }

    
        }if(e.getSource() == boton1){
            new Reporte();

            Singleton Factura = Singleton.getInstance();
            if(Factura != null){
            texto.setEnabled(false);
            comboBox.setEnabled(false);
            boton.setEnabled(false);
            boton1.setEnabled(false);
            boton2.setEnabled(false);
            }
            
            
         }
        if(e.getSource() == boton2){
            frame.dispose();

        }
    

    }
}
