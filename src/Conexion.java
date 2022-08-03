import java.sql.*;

public class Conexion implements IConexion {
        
    @Override
    public IConexion clone() throws CloneNotSupportedException {
        return (IConexion) super.clone();
    }
        public Connection getConnection(){
            Connection con = null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net/sql5508994", "sql5508994", "7H23S1ERTm");
            }catch(Exception e){
                e.printStackTrace();
            }
            return con;
        }
}
