package io.menzies.labs.rx.neo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Property;
import org.neo4j.springframework.data.core.schema.Relationship;

import java.time.LocalDate;
import java.util.List;

@Data
@Node
@Builder
public class Customer {

    @Id
    @JsonIgnore
    @GeneratedValue
    private Long id;

    @Property
    private String email;

    @Property
    private LocalDate joinedDate;

    @Property
    private List<String> countries;

    @JsonIgnore
    @Builder.Default
    @Relationship(type = "LINKED")
    private List<Customer> linked;
}
