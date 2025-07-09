package model.vo;

public class MascotaVO {
    private String nombre;
    private String raza;
    private String especie;
    private String sexo;
    private PersonaVO propietario;

    public MascotaVO(String nombre, String raza, String especie, String sexo, PersonaVO propietario) {
        this.nombre = nombre;
        this.raza = raza;
        this.especie = especie;
        this.sexo = sexo;
        this.propietario = propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public PersonaVO getPropietario() {
        return propietario;
    }

    public void setPropietario(PersonaVO propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Mascota: " + nombre + ", Raza: " + raza + ", Especie: " + especie + ", Sexo: " + sexo +
               ", Propietario: " + propietario.getNombre() + " (" + propietario.getDocumento() + ")";
    }
}
