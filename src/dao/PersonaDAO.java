package dao;

import connection.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.vo.PersonaVO;

public class PersonaDAO {

    private Connection conn;

    public PersonaDAO() {
        conn = Conexion.getConnection();
    }

    public void registrarPersona(PersonaVO persona) {
        String sql = "INSERT INTO Persona (documento, nombre, apellido, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getDocumento());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellido());
            ps.setString(4, persona.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar persona: " + e.getMessage());
        }
    }

    public PersonaVO consultarPersona(String documento) {
        PersonaVO persona = null;
        String sql = "SELECT * FROM Persona WHERE documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                persona = new PersonaVO(
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono")
                );
                persona.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar persona: " + e.getMessage());
        }
        return persona;
    }

    public void actualizarPersona(PersonaVO persona) {
        String sql = "UPDATE Persona SET nombre = ?, apellido = ?, telefono = ? WHERE documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getTelefono());
            ps.setString(4, persona.getDocumento());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
    }

    public void eliminarPersona(String documento) {
        String sqlMascotas = "SELECT COUNT(*) FROM Mascota WHERE idDueño = ?";
        try (PreparedStatement psMascotas = conn.prepareStatement(sqlMascotas)) {
            psMascotas.setString(1, documento);
            ResultSet rs = psMascotas.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("No se puede eliminar la persona porque tiene mascotas asociadas.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar mascotas asociadas: " + e.getMessage());
            return;
        }

        String sql = "DELETE FROM Persona WHERE documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, documento);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
        }
    }

    public List<PersonaVO> consultarListaPersonas() {
        List<PersonaVO> listaPersonas = new ArrayList<>();
        String sql = "SELECT * FROM Persona";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PersonaVO persona = new PersonaVO(
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono")
                );
                persona.setId(rs.getInt("id"));
                listaPersonas.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar lista de personas: " + e.getMessage());
        }
        return listaPersonas;
    }
}
