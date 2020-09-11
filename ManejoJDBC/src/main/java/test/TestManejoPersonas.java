package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestManejoPersonas {

    public static void main(String[] args) {

        Connection conexion = null;
            
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaDAO personaDao = new PersonaDAO(conexion);
            Persona cambioPersona = new Persona();
            cambioPersona.setIdPersona(2);
            cambioPersona.setNombre("Lorenita");
            cambioPersona.setApellido("Perez");
            cambioPersona.setEmail("lorenita123@gmail.com");
            cambioPersona.setTelefono("123123123");
            personaDao.actualizar(cambioPersona);
            
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Rafael");
//            nuevaPersona.setApellido("RodriguezRodriguezRodriguezRodriguezRodriguezzzz");
            nuevaPersona.setApellido("Rodriguez");
            
            personaDao.insertar(nuevaPersona);
            
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");
            
        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                System.out.println("Entramos al rollback");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
    }
}
