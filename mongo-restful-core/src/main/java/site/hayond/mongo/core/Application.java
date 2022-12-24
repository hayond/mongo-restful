package site.hayond.mongo.core;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
public class Application {

    public static void main(String[] args) {
        Micronaut.build(args).classes(Application.class).environments("base").start();
    }
}
