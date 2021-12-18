package dp.gl.gltemplatesimulator.repository;

import dp.gl.gltemplatesimulator.model.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends CrudRepository<Template,Integer> {

}
