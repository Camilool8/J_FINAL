
import java.util.Random;
import java.util.concurrent.locks.*;
import java.sql.*;
import java.util.Objects;

import javax.swing.ImageIcon;

public class hilos extends Thread{

    private Lock blo = new ReentrantLock();
    public boolean exit = true;

    


    @Override
    public void run(){
        
        try {

                Conexion con = new Conexion();
                Conexion con1 = (Conexion) con.clone();
                Connection con2 = con1.getConnection();
                String sql = "SELECT * FROM libro";
                java.sql.Statement st = con2.createStatement();
                String img="";
                String verify="";
                java.sql.ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                    img = rs.getString("imagen");
                    verify = Catalogo.tabla.getValueAt(Catalogo.tabla.getSelectedRow(), 1).toString();
                    if( Objects.equals(verify, img))
                    {
                        
                    InfoLibro.imagen1 = new ImageIcon(getClass().getResource("./Img/"+ rs.getString("imagen") + ".png"));
                    InfoLibro.imagen2 = new ImageIcon(getClass().getResource("./Img/"+ rs.getString("imagen") + "contra" + ".png"));
                    InfoLibro.imagentrue = new ImageIcon(InfoLibro.imagen1.getImage().getScaledInstance(200, 210, java.awt.Image.SCALE_SMOOTH));
                    InfoLibro.imagentrue2 = new ImageIcon(InfoLibro.imagen2.getImage().getScaledInstance(200, 210, java.awt.Image.SCALE_SMOOTH));
                }
            }
            con2.close();

        } catch (Exception e) {
            System.out.println("Error:" + e);
            }

        repetir();

    }

    public void repetir(){

        
            blo.lock();
            try {
                while(exit){
                    Random rand = new Random();
                    int num = rand.nextInt(3);              
                    if(num == 1){
                        InfoLibro.imagen.setIcon(InfoLibro.imagentrue);
                        
                    }
                    
                    if(num == 2){
                        InfoLibro.imagen.setIcon(InfoLibro.imagentrue2);
                    }
                Thread.sleep(1300);    
                }
                
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                
            blo.unlock();
    }

    public void stopThread(){

        exit = false;

    }
    
}
