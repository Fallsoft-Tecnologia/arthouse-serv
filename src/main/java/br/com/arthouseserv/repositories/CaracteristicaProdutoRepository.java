package br.com.arthouseserv.repositories;

import br.com.arthouseserv.dto.CaracteristicasDTO;
import br.com.arthouseserv.models.CaracteristicaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicaProdutoRepository extends JpaRepository<CaracteristicaProduto, Integer> {

    @Query(" SELECT CaracteristicasDTO(cp.idCaracteristicasProduto," +
            "cp.nomeCaracterisiticasProduto,cp.imgCaracterisiticasProduto) " +
            "FROM CaracteristicaProduto cp")
    List<CaracteristicasDTO> buscarCaracteristicas();
}
