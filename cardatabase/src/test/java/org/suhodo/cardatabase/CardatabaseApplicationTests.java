package org.suhodo.cardatabase;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhodo.cardatabase.domain.Car;
import org.suhodo.cardatabase.domain.Owner;
import org.suhodo.cardatabase.repository.CarRepository;
import org.suhodo.cardatabase.repository.OwnerRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class CardatabaseApplicationTests {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Test
	public void TestCarRepository() {
		Car fordCar = Car.builder()
				.brand("Ford")
				.model("Mustang")
				.color("Red")
				.registrationNumber("T-111")
				.modelYear(2025)
				.price(5400)
				.build();
		carRepository.save(fordCar);

		Car kiaCar = Car.builder()
				.brand("Kia")
				.model("K-5")
				.color("Silver")
				.registrationNumber("K-111")
				.modelYear(2025)
				.price(6400)
				.build();
		carRepository.save(kiaCar);

		Car hyndaiCar = Car.builder()
				.brand("Hyundai")
				.model("Genesis")
				.color("Black")
				.registrationNumber("H-111")
				.modelYear(2025)
				.price(7800)
				.build();
		carRepository.save(hyndaiCar);
	}

	@Test
	public void TestFindAll() {
		List<Car> carList = carRepository.findAll();

		carList.stream().forEach(car -> log.info(car));
	}

	@Test
	public void TestDelteAllCar() {
		carRepository.deleteAll();
	}

	@Test
	public void TestOwnerCar() {
		// 부모 엔티티
		Owner owner1 = Owner.builder()
				.firstname("John")
				.lastname("Johnson")
				.build();

		Owner owner2 = Owner.builder()
				.firstname("Mary")
				.lastname("Robinson")
				.build();

		// 부모 엔티티를 먼저 저장해야 한다.
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		/*자식 엔티티에 부모 엔티티를 연결시켜야한다. */

		// 자식 엔티티
		Car fordCar = Car.builder()
				.brand("Ford")
				.model("Mustang")
				.color("Red")
				.registrationNumber("T-111")
				.modelYear(2025)
				.price(5400)
				.owner(owner1)
				.build();
		carRepository.save(fordCar);

		Car kiaCar = Car.builder()
				.brand("Kia")
				.model("K-5")
				.color("Silver")
				.registrationNumber("K-111")
				.modelYear(2025)
				.price(6400)
				.owner(owner2)
				.build();
		carRepository.save(kiaCar);

		Car hyndaiCar = Car.builder()
				.brand("Hyundai")
				.model("Genesis")
				.color("Black")
				.registrationNumber("H-111")
				.modelYear(2025)
				.price(7800)
				.owner(owner2)
				.build();
		carRepository.save(hyndaiCar);
	}

	@Transactional
	@Test
	public void TestSelectOwner(){
		List<Owner> ownerList = ownerRepository.findAll();
		ownerList.stream().forEach(owner -> log.info(owner));
	}

	@Test
	public void TestSelectCar(){
		List<Car> carList = carRepository.findAll();
		carList.stream().forEach(car -> log.info(car));
	}
}