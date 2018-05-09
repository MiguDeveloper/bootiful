package pe.tuna.service;

import org.springframework.http.ResponseEntity;
import pe.tuna.models.ColaboradorBean;

import java.util.List;
import java.util.Optional;

public interface IColaboradorService {
    public List<ColaboradorBean> getAllColaborador();
    public Optional<ColaboradorBean> getByIdColaborador(int id);
    public ColaboradorBean createColaborador(ColaboradorBean colaboradorBean);
}
