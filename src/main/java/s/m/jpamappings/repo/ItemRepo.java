package s.m.jpamappings.repo;

import org.springframework.data.repository.CrudRepository;
import s.m.jpamappings.entity.Item;

public interface ItemRepo extends CrudRepository<Item, Long> {
}
