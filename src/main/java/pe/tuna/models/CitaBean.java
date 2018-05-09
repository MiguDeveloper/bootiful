package pe.tuna.models;

import java.util.Date;

public class CitaBean {
    private int id;
    private String texto;
    private Date fecha;

    public CitaBean() {
    }

    public CitaBean(int id, String texto, Date fecha) {
        this.id = id;
        this.texto = texto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
