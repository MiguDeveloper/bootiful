package pe.tuna.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "colaborador")
public class ColaboradorBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Aqui podemos setear el nombre de la columna
    @Column(name = "nombre")
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private Date fechaNacimiento;

    public ColaboradorBean() {
    }

    public ColaboradorBean(String nombre, String apellidos, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
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
}
