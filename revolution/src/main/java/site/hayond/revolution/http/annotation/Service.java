package site.hayond.revolution.http.annotation;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.DefaultScope;
import jakarta.inject.Singleton;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@Bean
@DefaultScope(Singleton.class)
public @interface Service {
}
