package controller;

import java.util.List;

import dao.MascotaDAO;
import dao.PersonaDAO;
import model.vo.MascotaVO;
import model.vo.PersonaVO;

public class Controlador {

    private PersonaDAO personaDAO;
    private MascotaDAO mascotaDAO;

    public Controlador() {
        personaDAO = new PersonaDAO();
        mascotaDAO = new MascotaDAO();
    }

    // Métodos para Persona
    public void registrarPersona(PersonaVO persona) {
        personaDAO.registrarPersona(persona);
    }

    public PersonaVO consultarPersona(String documento) {
        return personaDAO.consultarPersona(documento);
    }

    public void actualizarPersona(PersonaVO persona) {
        personaDAO.actualizarPersona(persona);
    }

    public void eliminarPersona(String documento) {
        personaDAO.eliminarPersona(documento);
    }

    public List<PersonaVO> consultarListaPersonas() {
        return personaDAO.consultarListaPersonas();
    }

    // Métodos para Mascota
    public void registrarMascota(MascotaVO mascota) {
        mascotaDAO.registrarMascota(mascota);
    }

    public MascotaVO consultarMascota(String nombre) {
        return mascotaDAO.consultarMascota(nombre);
    }

    public void actualizarMascota(MascotaVO mascota) {
        mascotaDAO.actualizarMascota(mascota);
    }

    public void eliminarMascota(String nombre) {
        mascotaDAO.eliminarMascota(nombre);
    }

    public List<MascotaVO> consultarListaMascotas() {
        return mascotaDAO.listarMascotas();
    }

    public int cantidadMascotasPorPersona(String documento) {
        return mascotaDAO.cantidadMascotasPorPersona(documento);
    }
}
