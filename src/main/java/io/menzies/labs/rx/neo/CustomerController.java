package io.menzies.labs.rx.neo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repository;

    @GetMapping(value = {"", "/"}, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Customer> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/insert", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Customer> insertBulk() {
        return repository.saveAll(generator());
    }

    Flux<Customer> generator() {
        return Flux.fromStream(
                Stream.generate(() -> Customer.builder()
                        .email(UUID.randomUUID().toString())
                        .countries(List.of(UUID.randomUUID().toString().substring(0,4)))
                        .joinedDate(LocalDate.now())
                        .build()))
                .limitRequest(1000)
                .log()
                .delayElements(Duration.ofMillis(5));
    }
}

