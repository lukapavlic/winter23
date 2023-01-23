package com.winter23.demo.service;

import com.winter23.demo.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface IEmployeeService
{
    void create(Employee e);
     
    Mono<Employee> findById(Integer id);
 
    Flux<Employee> findByName(String name);
 
    Flux<Employee> findAll();
 
    Mono<Employee> update(Employee e);
 
    Mono<Void> delete(Integer id);
}