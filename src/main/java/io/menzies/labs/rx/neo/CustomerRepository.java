package io.menzies.labs.rx.neo;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveNeo4jRepository<Customer, Long> {

    @Override
    Flux<Customer> findAll();

    @Override
    Mono<Customer> findById(Long ids);

}