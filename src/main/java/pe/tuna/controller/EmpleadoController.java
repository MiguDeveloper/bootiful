package pe.tuna.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import pe.tuna.models.CitaBean;
import pe.tuna.models.EmpleadoBean;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpleadoController {

    List<EmpleadoBean> repo;

    @GetMapping("/empleados")
    public List<EmpleadoBean> list() {
        return repo;
    }

    // La primero forma de manejar las excepciones al momento de algun error es usando el response
    // con su codigo de respuesta la usuario, esta manera es facil de implementar pero quiza no sea la mas conveniente
    @GetMapping("/empleado/{id}")
    public EmpleadoBean getEmpleado(@PathVariable int id, HttpServletResponse response) {
        if (id > 0 && id <= repo.size()) {
            return repo.get(id - 1);
        } else {
            // response.setStatus(404);
            // return null;

            // Una tercera forma es la definicion de nuestros propios métodos de excepcion
            throw new EmpleadoNotFoundException(id);
        }
    }

    @GetMapping("/empleado/{id}/cita")
    public List<CitaBean> getCitasEmpleado(@PathVariable int id) {
        if (id > 0 && id <= repo.size()) {
            return repo.get(id - 1).getCitas();
        }

        return null;
    }

    // La segunda manera es la asociacion con las excepciones
    @GetMapping("/empleado/{idE}/cita/{idC}")
    public CitaBean getCitaEmpleado(@PathVariable int idE, @PathVariable int idC, HttpServletRequest request) throws NoHandlerFoundException {

        CitaBean result = null;

        if (idE > 0 && idE <= repo.size()) {
            List<CitaBean> citas = repo.get(idE - 1).getCitas();
            if (citas != null) {
                for (CitaBean c : citas) {
                    if (c.getId() == idC) {
                        result = c;
                    }
                }
            }
        }

        if (result != null) {
            return result;
        } else {
            throw new NoHandlerFoundException("GET", request.getRequestURI().toString(), null);
        }

    }


    // Este metodo es el encargado de manejar el error causado por una cita inexistente con excepciones
    // la descripcion del mensaje de error es generica y por ello la cambiamos a traves de las anotaciones
    // @ResponseStatus, @ExceptionHandler devolviendo un determinado código de respuesta y modificando el mensaje a nuestro
    // antojo, y el metodo no tendria nada de codigo que implementar
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "la cita no existe") // seteamos alguno campos de la rpta
    @ExceptionHandler(NoHandlerFoundException.class) // La excepcion NoHandlerFoundException corresponde con el 404
    public void citaInexistente() {
        //vacio
    }

    // Tercer metodo implementando nuestros propios metodos de excpeciones
    // Usamos esta anotacion si cambiamos el mensaje
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    // usamos esta anotacion si no queremos cambiar el mensaje y lanzar un mensaje generico
    // @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Empleado no encontrado!!")
    private class EmpleadoNotFoundException extends RuntimeException{
        private static final long serialVersionUID = -547477474747474959L;

        // sobre escribimos el constructor de runtimeException
        public EmpleadoNotFoundException(int id){
            super(String.format("El empleado %d no existe", id));
        }
    }


    @PostConstruct
    private void init() {
        repo = new ArrayList<EmpleadoBean>();
        repo.add(new EmpleadoBean(1, "Miguel", "Chinchay", new Date()));
        repo.get(0).getCitas().add(new CitaBean(1, "Reunion de trabajo", new Date()));
        repo.get(0).getCitas().add(new CitaBean(2, "Visita a un cliente", new Date()));

        repo.add(new EmpleadoBean(2, "Jorge", "Chinchay", new Date()));
        repo.get(1).getCitas().add(new CitaBean(1, "Visita a un proveedor", new Date()));

        repo.add(new EmpleadoBean(3, "Mortadelo", "gomez", new Date()));
        repo.add(new EmpleadoBean(4, "Filemon", "Guzman", new Date()));
        repo.add(new EmpleadoBean(5, "Americo", "Lopez", new Date()));
    }

}
