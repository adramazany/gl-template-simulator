package dp.gl.gltemplatesimulator.repository;

import dp.gl.gltemplatesimulator.model.Event;
import dp.gl.gltemplatesimulator.model.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
}
