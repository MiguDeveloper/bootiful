package pe.tuna.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.tuna.models.ColaboradorBean;
import pe.tuna.repository.ColaboradorRepository;
import pe.tuna.service.IColaboradorService;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorServiceImpl implements IColaboradorService {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Override
    public List<ColaboradorBean> getAllColaborador() {
        List<ColaboradorBean> lstColaboradores = colaboradorRepository.findAll();
        if (lstColaboradores != null) {
            return lstColaboradores;
        } else {
            throw new ColaboradorNotFoundException();
        }

    }

    // Importante: recordar que cuando usamos optional es mejor ya que no evita de tener null silenciosos
    // recordar que optional tiene un mejor desempenio en el ambiente funcional y no en imperativo
    @Override
    public Optional<ColaboradorBean> getByIdColaborador(int id) {
        Optional<ColaboradorBean> colaborador = colaboradorRepository.findById(id);
        if (colaborador.isPresent()) {
            return colaborador;
        } else {
            throw new ColaboradorNotFoundException(id);
        }
    }

    @Override
    public ColaboradorBean createColaborador(ColaboradorBean colaboradorBean) {
        return colaboradorRepository.save(colaboradorBean);
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private class ColaboradorNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 787447934948938493L;

        public ColaboradorNotFoundException() {
            super("No existe ningun empleado");
        }

        public ColaboradorNotFoundException(int id) {
            super(String.format("No existe ningun colaborador con el ID = %d", id));
        }
    }

}
