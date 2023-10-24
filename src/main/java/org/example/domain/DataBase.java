package org.example.domain;

import org.example.domain.tarea.Tarea;
import org.example.domain.tarea.TareaAdapter;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DataBase {
    //lo primero es vamos a agregar las conexiones
    private static Connection connection;
    private static Logger logger;
    static {
        logger = Logger.getLogger(DataBase.class.getName());
        String url="jdbc:mysql://localhost:3306/ad";
        String user="root";
        String password="";
        try {
            //usamos lo siguiente para conectar usando los parametros que hemos creado anteriormente
            connection = DriverManager.getConnection(url,user,password);
            logger.info("Successful connection to database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
    public static ArrayList<String[]> getAll(){
        var salida = new ArrayList<String[]>();
        try(Statement st = connection.createStatement()){
            ResultSet rs= st.executeQuery("SELECT * FROM tarea");
            while (rs.next()){
                String[] fila = convert(rs);
                salida.add(fila);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salida;
    }
    public static ArrayList<Tarea>getAllTarea(){
        var salida = new ArrayList<Tarea>();
        try(Statement st = connection.createStatement()){
            ResultSet rs= st.executeQuery("SELECT * FROM tarea");
            while (rs.next()){

                salida.add(new TareaAdapter().loadFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salida;
    }

    private static String[] convert(ResultSet rs) throws SQLException {
        String[] fila ={
                String.valueOf(rs.getInt("id")),
                rs.getString("titulo"),
                rs.getString("prioridad"),
                String.valueOf((rs.getInt("usuario_id"))),
                rs.getString("categoria"),
                rs.getString("descripcion")

        };
        return fila;
    }
}
