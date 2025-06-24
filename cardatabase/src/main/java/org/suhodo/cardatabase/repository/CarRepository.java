package org.suhodo.cardatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhodo.cardatabase.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}