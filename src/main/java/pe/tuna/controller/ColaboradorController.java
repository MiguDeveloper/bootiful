package pe.tuna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.tuna.models.ColaboradorBean;
import pe.tuna.models.EmpleadoBean;
import pe.tuna.repository.ColaboradorRepository;
import pe.tuna.serviceImpl.ColaboradorServiceImpl;
import pe.tuna.util.ErrorRest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api2")
public class ColaboradorController {

    @Autowired
    ColaboradorServiceImpl colaboradorService;

    @Autowired
    ColaboradorRepository repo;

    @GetMapping("/colaboradores")
    public List<ColaboradorBean> getColaboradores() {
        return colaboradorService.getAllColaborador();
    }

    @GetMapping("/colaborador/{id}")
    public Optional<ColaboradorBean> getByIdColaborador(@PathVariable int id) {
        return colaboradorService.getByIdColaborador(id);
    }

    // Primera forma de devolver el response code bastante sencilla de hacer un post
    @PostMapping("/add")
    public ColaboradorBean createColaborador(@RequestBody ColaboradorBean colaboradorBean, HttpServletResponse response) {
        ColaboradorBean colaborador = new ColaboradorBean(colaboradorBean.getNombre(), colaboradorBean.getApellidos(), colaboradorBean.getFechaNacimiento());
        response.setStatus(201);
        return colaboradorService.createColaborador(colaborador);
    }

    // Segunda forma de devolver el response code de post pero mas completa
    @PostMapping("/add2")
    public ResponseEntity<?> createColaborador(RequestEntity<ColaboradorBean> reqColaborador) {
        if (reqColaborador.getBody() == null) {
            return new ResponseEntity<ErrorRest>(new ErrorRest("El formato de peticion incorrecto. Debe enviar datos"), HttpStatus.BAD_REQUEST);
        }

        ColaboradorBean colaboradorBean = reqColaborador.getBody();

        Optional<ColaboradorBean> colaboradorEncontrado = repo.findById(colaboradorBean.getId());

        if (colaboradorEncontrado.isPresent()) {
            return new ResponseEntity<ErrorRest>(new ErrorRest("El empleado con id: " + colaboradorBean.getId() +
                    " ya existe"), HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<ColaboradorBean>(colaboradorService.createColaborador(colaboradorBean), HttpStatus.CREATED);
        }
    }

    @PutMapping("/colaborador/{id}")
    public ResponseEntity<?> updateColaborador(@PathVariable int id, RequestEntity<ColaboradorBean> reqColaborador) {
        if (reqColaborador.getBody() == null) {
            return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de peticion incorrecta. Debe enviar datos"), HttpStatus.BAD_REQUEST);
        }

        Optional<ColaboradorBean> colaboradorEncontrado = repo.findById(id);

        if (colaboradorEncontrado.isPresent()) {
            ColaboradorBean colaboradorBean = reqColaborador.getBody();
            ColaboradorBean colaboradorA_actualizar = new ColaboradorBean(id, colaboradorBean.getNombre(),
                    colaboradorBean.getApellidos(), colaboradorBean.getFechaNacimiento());
            return new ResponseEntity<ColaboradorBean>(repo.save(colaboradorA_actualizar), HttpStatus.OK);
        } else {
            return new ResponseEntity<ErrorRest>(new ErrorRest("El empleado a modificar no existe"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/colaborador/{id}")
    public ResponseEntity<?> deleteColaborador(@PathVariable int id) {
        Optional<ColaboradorBean> colaboradorEncontrado = repo.findById(id);
        if (colaboradorEncontrado.isPresent()) {
            repo.deleteById(id);
            return new ResponseEntity<ColaboradorBean>(colaboradorEncontrado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<ErrorRest>(new ErrorRest("El empleado a borrar no existe"), HttpStatus.BAD_REQUEST);
        }
    }


}
