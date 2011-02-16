package br.com.caelum.vrpator.serialization;

import br.com.caelum.vraptor.serialization.gson.exclusion.CustomExclusionStrategy;
import br.com.caelum.vraptor.serialization.gson.exclusion.DontSerialize;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SerializationTest {
    @Test
    public void shouldSkipAnnotatedField() {

        Person p = new Person();
        p.age = 12;
        p.name = "joao";

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new CustomExclusionStrategy(Sets.newHashSet(""))).create();
        assertEquals("{\"age\":12}", gson.toJson(p));
    }

     @Test
    public void shouldSkipEspecifiedField() {

        Dog dog = new Dog();
        dog.age = 12;
        dog.name = "joao";

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new CustomExclusionStrategy(Sets.newHashSet("name"))).create();
        assertEquals("{\"age\":12}", gson.toJson(dog));
    }

    @Test
    public void shouldSkipClass() {

        Person p = new Person();
        p.age = 12;
        p.name = "joao";

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new CustomExclusionStrategy(Sets.newHashSet(""),Integer.class)).create();
        assertEquals("{}", gson.toJson(p));
    }

    public static class Person {
        Integer age;
        @DontSerialize
        String name;
    }

    public static class Dog {
        Integer age;
        String name;
    }
}
