package s.m.jpamappings.repo;

import org.springframework.data.repository.CrudRepository;
import s.m.jpamappings.entity.CustomerOrder;

public interface OrderRepo extends CrudRepository<CustomerOrder, Long> {
}
