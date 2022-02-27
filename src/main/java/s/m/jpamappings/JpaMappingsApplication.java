package s.m.jpamappings;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import s.m.jpamappings.entity.CustomerOrder;
import s.m.jpamappings.entity.Item;
import s.m.jpamappings.repo.ItemRepo;
import s.m.jpamappings.repo.OrderRepo;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class JpaMappingsApplication {

	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private ItemRepo itemRepo;

	public static void main(String[] args) {
		SpringApplication.run(JpaMappingsApplication.class, args);
	}

	@Scheduled(fixedRate = 30_000)
	@Transactional
	public void addOrder(){
		log.info("adding a new order..");
		CustomerOrder order = new CustomerOrder();
		order.setOrderedAt(LocalDateTime.now());
		orderRepo.save(order);
		Item tv = new Item("Philips 32 inch smart TV");
		Item iPhone = new Item("iPhone X");
		tv.setCustomerOrder(order);
		iPhone.setCustomerOrder(order);
		order.getItems().add(tv);
		order.getItems().add(iPhone);
	}

	@Scheduled(fixedRate = 30_000, initialDelay = 10000)
	@Transactional
	public void removeItemFromOrder(){
		log.info("removing an item order..");
		CustomerOrder order = orderRepo.findById(1L).orElse(null);
		order.getItems().remove(0);
	}

}
