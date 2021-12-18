package dp.gl.gltemplatesimulator.repository;

import dp.gl.gltemplatesimulator.model.Costcenter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostcenterRepository extends CrudRepository<Costcenter,Integer> {

    Costcenter findByCode(Integer code);
}
