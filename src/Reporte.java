import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.sql.*;

public class Reporte implements ActionListener {

        protected JFrame frame;
        protected JButton boton;
        protected JTextArea receipt;
        public static Float total = 0.0f;

        public Reporte() {
                initialize();
        }
        
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 330, 600);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        receipt = new JTextArea();
        receipt.setBounds(10, 10, 297, 500);
        frame.getContentPane().add(receipt);
        receipt.setFont(new Font("Tahoma", Font.BOLD, 14));
        receipt.setLineWrap(true);
        receipt.setWrapStyleWord(true);
        receipt.setEditable(false);
        receipt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        receipt.setText("---------------------------------------------");
        receipt.append("\n");
        receipt.append("\n");
        receipt.append("\tFACTURA");
        receipt.append("\n");
        receipt.append("\n");
        receipt.append("---------------------------------------------");
        receipt.append("\n");
        receipt.append("\n");
        receipt.append("    Vendedor: " + Login.usuario.getText().toUpperCase());
        receipt.append("\n");
        receipt.append("\n");
        receipt.append("    Cliente: " + Factura.texto.getText().toUpperCase());
        receipt.append("\n");
        receipt.append("\n");
        for (int i = 0; i < Factura.model.getRowCount(); i++) {
            if(i == 0){
                receipt.append("    Productos: \n\n");
                receipt.append("    " + (i + 1) + ". " + Factura.model.getValueAt(i, 2) + " - $" + Factura.model.getValueAt(i, 4));
            }else{
                receipt.append("\n");
                receipt.append("    " + (i + 1) + ". " + Factura.model.getValueAt(i, 2) + " - $" + Factura.model.getValueAt(i, 4));
            }
        }
        receipt.append("\n");
        receipt.append("\n");
        receipt.append("---------------------------------------------");
        receipt.append("\n");
        receipt.append("\n");
        for (int i = 0; i < Factura.model.getRowCount(); i++) {
            
            total += Float.parseFloat(Factura.model.getValueAt(i, 4).toString());
        }
        receipt.append("    " + "Total: $" + total);
        receipt.append("\n");
        receipt.append("\n");
        receipt.append("---------------------------------------------");
        receipt.append("\n");
        receipt.append("\n");
        receipt.append("                    CAMILEUS-CORP©");




        boton = new JButton("Imprimir");
        boton.setBounds(10, 525, 297, 20);
        frame.getContentPane().add(boton);
        boton.addActionListener(this);


        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton) {

                try{

                    Conexion con = new Conexion();
                    Conexion con1 = (Conexion) con.clone();
                    Connection con2 = con1.getConnection();

                    String sql = "INSERT INTO factura (Vendedor, Cliente, Producto_Cantidad, Total) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = con2.prepareStatement(sql);
                    ps.setString(1, Login.usuario.getText().toUpperCase());
                    ps.setString(2, Factura.texto.getText().toUpperCase());
                    ps.setInt(3, Factura.model.getRowCount());
                    ps.setFloat(4, total);
                    ps.executeUpdate();
                    ps.close();
                    con2.close();
                    total = 0.0f;
                }catch(Exception e1){
                    System.out.println(e1);
                }

            JOptionPane.showMessageDialog(null, "Su factura ha sido impresa!", "Factura", JOptionPane.INFORMATION_MESSAGE);
            Singleton.getInstance();
            Factura.texto.setEnabled(true);
            Factura.texto.setEditable(true);
            Factura.comboBox.setEnabled(true);
            Factura.boton.setEnabled(true);
            Factura.boton1.setEnabled(true);
            Factura.boton2.setEnabled(true);
            Factura.model.setRowCount(0);
            Factura.texto.setText("");
            Factura.comboBox.setSelectedIndex(0);

            frame.dispose();


        }
        
        
    }
        

    
}


