package pe.tuna.util;

public class ErrorRest {
    private String mensaje;

    public ErrorRest() {
    }

    public ErrorRest(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
