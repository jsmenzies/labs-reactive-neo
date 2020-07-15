package io.menzies.labs.rx.neo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @PostConstruct
    void insert() {
        Customer jack = Customer.builder()
                .name("Jack")
                .build();

        Customer james = Customer.builder()
                .name("James")
                .linked(List.of(jack))
                .build();

        repository.save(james)
                .log()
                .subscribe();
    }
}
