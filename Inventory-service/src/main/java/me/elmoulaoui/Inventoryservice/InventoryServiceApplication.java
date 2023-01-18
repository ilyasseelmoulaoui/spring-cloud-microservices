package me.elmoulaoui.Inventoryservice;

import me.elmoulaoui.Inventoryservice.entities.Product;
import me.elmoulaoui.Inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	// 25:00
	@Bean
	CommandLineRunner start(ProductRepository pr)
	{
		return args -> {
			Stream.of("Iphone", "XIAOMI", "Samsung", "Huawei", "LG", "Sony").forEach(p -> {
				Product product = new Product();
				product.setId(UUID.randomUUID().toString());
				product.setPrice(Math.random() * 749);
				product.setName(p);
				product.setQuantity((int) (Math.random() * 9.4));
				pr.save(product);
			});
		};
	}
}
