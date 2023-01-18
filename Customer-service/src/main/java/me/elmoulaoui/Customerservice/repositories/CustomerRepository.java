package me.elmoulaoui.Customerservice.repositories;

import me.elmoulaoui.Customerservice.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
