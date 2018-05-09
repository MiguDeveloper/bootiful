package pe.tuna.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpleadoBean {
    private int id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private List<CitaBean> citas;

    public EmpleadoBean() {
    }

    public EmpleadoBean(int id, String nombre, String apellidos, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.citas = new ArrayList<CitaBean>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<CitaBean> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaBean> citas) {
        this.citas = citas;
    }
}
