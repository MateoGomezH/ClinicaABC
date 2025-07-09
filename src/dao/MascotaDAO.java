package dao;

import model.vo.MascotaVO;
import model.vo.PersonaVO;
import connection.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private Connection conn;

    public MascotaDAO() {
        conn = Conexion.getConnection();
    }

    public void registrarMascota(MascotaVO mascota) {

        if (!dueñoExiste(mascota.getPropietario().getDocumento())) {
            System.out.println("El dueño con documento " + mascota.getPropietario().getDocumento() + " no existe.");
            return;
        }

        String sql = "INSERT INTO Mascota (nombre, raza, especie, sexo, idDueño) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getRaza());
            stmt.setString(3, mascota.getEspecie());
            stmt.setString(4, mascota.getSexo());
            stmt.setString(5, mascota.getPropietario().getDocumento());
            stmt.executeUpdate();
            System.out.println("Mascota registrada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean dueñoExiste(String documento) {
        String sql = "SELECT COUNT(*) FROM persona WHERE documento = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public MascotaVO consultarMascota(String nombre) {
        String sql = "SELECT * FROM mascota WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String documentoPropietario = rs.getString("idDueño");
                PersonaVO propietario = obtenerPropietarioPorDocumento(documentoPropietario);
                return new MascotaVO(
                    rs.getString("nombre"),
                    rs.getString("raza"),
                    rs.getString("especie"),
                    rs.getString("sexo"),
                    propietario
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PersonaVO obtenerPropietarioPorDocumento(String documento) {
        String sql = "SELECT * FROM persona WHERE documento = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Corrección: usa los campos nombre y apellido directamente
                PersonaVO propietario = new PersonaVO(
                    rs.getString("documento"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono")
                );
                propietario.setId(rs.getInt("id"));
                return propietario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarMascota(MascotaVO mascota) {
        String sql = "UPDATE mascota SET raza = ?, especie = ?, sexo = ?, idDueño = ? WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mascota.getRaza());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getSexo());
            stmt.setString(4, mascota.getPropietario().getDocumento());
            stmt.setString(5, mascota.getNombre());
            stmt.executeUpdate();
            System.out.println("Mascota actualizada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarMascota(String nombre) {
        String sql = "DELETE FROM mascota WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            System.out.println("Mascota eliminada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MascotaVO> listarMascotas() {
        String sql = "SELECT * FROM mascota";
        List<MascotaVO> mascotas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                String especie = rs.getString("especie");
                String sexo = rs.getString("sexo");
                String documentoPropietario = rs.getString("idDueño");
                PersonaVO propietario = obtenerPropietarioPorDocumento(documentoPropietario);
                mascotas.add(new MascotaVO(nombre, raza, especie, sexo, propietario));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }

    public int cantidadMascotasPorPersona(String documento) {
        String sql = "SELECT COUNT(*) FROM Mascota WHERE idDueño = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
