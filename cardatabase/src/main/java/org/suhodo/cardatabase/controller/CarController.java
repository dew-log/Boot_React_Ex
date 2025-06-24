package org.suhodo.cardatabase.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.suhodo.cardatabase.domain.Car;
import org.suhodo.cardatabase.repository.CarRepository;

import lombok.RequiredArgsConstructor;

/*
 * Client : (브라우저상의) React App: Axios/Fetch 라이브러리 사용
 * Server : (톰캣을 연동한) Spring Boot App: @RestController 설정 클래스
 * 통신 : application/json 헤더에 설정
 * 데이터 : json 문자열(js객체를 문자열로 변환)
 */
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @GetMapping("/cars")
    public List<Car> getCars(){
        return carRepository.findAll();
    }
}