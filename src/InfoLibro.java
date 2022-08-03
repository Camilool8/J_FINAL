
import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.awt.Color;

public class InfoLibro implements ActionListener {

        public JButton btnaceptar, btnvolver;
        public static JLabel imagen;
        public static ImageIcon imagen1, imagen2, imagentrue, imagentrue2;
        public JFrame frame;
        hilos hilos = new hilos();
        
        public InfoLibro() {
            initialize();
        }
        
        private void initialize() {
            
            frame = new JFrame();
            frame.setBounds(100, 100, 600, 600);
            frame.getContentPane().setLayout(null);
            frame.setVisible(true);
            frame.setResizable(false);

            hilos.start();

            JLabel lblNewLabel2 = new JLabel("INFORMACION DEL LIBRO");
            lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 34));
            lblNewLabel2.setBounds(59, 25, 480, 65);
            frame.getContentPane().add(lblNewLabel2);

            btnaceptar = new JButton("Aceptar");
            btnaceptar.setBackground(new Color(247, 148, 29));
            btnaceptar.setBounds(85, 481, 414, 23);
            frame.getContentPane().add(btnaceptar);
            btnaceptar.addActionListener(this);

            btnvolver = new JButton("Volver");
            btnvolver.setBackground(new Color(247, 148, 29));
            btnvolver.setBounds(85, 510, 414, 23);
            frame.getContentPane().add(btnvolver);
            
            JPanel panel = new JPanel();
            panel.setBackground(new Color(26, 118, 188));
            panel.setBounds(55, 100, 476, 290);
            frame.getContentPane().add(panel);
                        panel.setLayout(null);
            
                        imagen = new JLabel("");
                        imagen.setBounds(40, 31, 209, 226);
                        panel.add(imagen);
                        
                        JLabel lblNewLabel = new JLabel("Titulo: " + Catalogo.tabla.getValueAt(Catalogo.tabla.getSelectedRow(), 1));
                        lblNewLabel.setBounds(265, 81, 250, 14);
                        lblNewLabel.setForeground(new Color(255, 255, 255));
                        panel.add(lblNewLabel);
                        
                        JLabel lblNewLabel_1 = new JLabel("Autor: " + Catalogo.tabla.getValueAt(Catalogo.tabla.getSelectedRow(), 2));
                        lblNewLabel_1.setBounds(265, 105, 250, 14);
                        lblNewLabel_1.setForeground(new Color(255, 255, 255));
                        panel.add(lblNewLabel_1);
                        
                        JLabel lblNewLabel_2 = new JLabel("Genero: " + Catalogo.tabla.getValueAt(Catalogo.tabla.getSelectedRow(), 3));
                        lblNewLabel_2.setBounds(265, 126, 250, 14);
                        lblNewLabel_2.setForeground(new Color(255, 255, 255));
                        panel.add(lblNewLabel_2);
                        
                        JLabel lblNewLabel_3 = new JLabel("Precio: " + Catalogo.tabla.getValueAt(Catalogo.tabla.getSelectedRow(), 4));
                        lblNewLabel_3.setBounds(265, 150, 250, 14);
                        lblNewLabel_3.setForeground(new Color(255, 255, 255));
                        panel.add(lblNewLabel_3);
                        
                        JLabel lblNewLabel_4 = new JLabel("Cantidad: " + Catalogo.tabla.getValueAt(Catalogo.tabla.getSelectedRow(), 5));
                        lblNewLabel_4.setBounds(265, 174, 250, 14);
                        lblNewLabel_4.setForeground(new Color(255, 255, 255));
                        panel.add(lblNewLabel_4);
                        
                        JLabel lblNewLabel_5 = new JLabel("ID: " + Catalogo.tabla.getValueAt(Catalogo.tabla.getSelectedRow(), 0));
                        lblNewLabel_5.setBounds(265, 198, 250, 14);
                        lblNewLabel_5.setForeground(new Color(255, 255, 255));
                        panel.add(lblNewLabel_5);
            btnvolver.addActionListener(this);

            
            
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnaceptar){
                hilos.stopThread();
                new Factura();
                frame.dispose();
            }
            if(e.getSource() == btnvolver){
                hilos.stopThread();
                frame.dispose();
            }
        }
    }
