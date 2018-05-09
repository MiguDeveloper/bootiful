package pe.tuna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.models.CitaBean;
import pe.tuna.models.EmpleadoBean;

import javax.annotation.PostConstruct;
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
            response.setStatus(404);
            return null;
        }
    }

    @GetMapping("/empleado/{id}/cita")
    public List<CitaBean> getCitasEmpleado(@PathVariable int id) {
        if (id > 0 && id <= repo.size()) {
            return repo.get(id - 1).getCitas();
        }

        return null;
    }

    @GetMapping("/empleado/{idE}/cita/{idC}")
    public CitaBean getCitaEmpleado(@PathVariable int idE, @PathVariable int idC) {
        if (idE > 0 && idE <= repo.size()) {
            List<CitaBean> citas = repo.get(idE - 1).getCitas();
            if (citas != null) {
                for (CitaBean c : citas) {
                    if (c.getId() == idC) {
                        return c;
                    }
                }
            }
        }

        return null;
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
