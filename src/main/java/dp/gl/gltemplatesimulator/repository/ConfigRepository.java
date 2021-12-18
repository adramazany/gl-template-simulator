package dp.gl.gltemplatesimulator.repository;

import dp.gl.gltemplatesimulator.model.Account;
import dp.gl.gltemplatesimulator.model.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends CrudRepository<Config,Integer> {

    Config findByKey(String key);
}
