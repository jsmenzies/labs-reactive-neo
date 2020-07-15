package io.menzies.labs.rx.neo;

import lombok.Builder;
import lombok.Data;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Property;
import org.neo4j.springframework.data.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node
@Builder
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Builder.Default
    @Relationship(type = "LINKED")
    private List<Customer> linked = new ArrayList<>();
}
