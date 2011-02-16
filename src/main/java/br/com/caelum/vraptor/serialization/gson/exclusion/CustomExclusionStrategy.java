package br.com.caelum.vraptor.serialization.gson.exclusion;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomExclusionStrategy implements ExclusionStrategy {

    private final Set toExcludeClasses;
    private final Set<String> fieldsToExclude;

    public CustomExclusionStrategy(Set<String> fieldsToExclude, Class<?>... classes) {
        this.fieldsToExclude = fieldsToExclude;
        this.toExcludeClasses = new LinkedHashSet(Arrays.asList(classes));
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(DontSerialize.class) != null || fieldsToExclude.contains(f.getName());
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return toExcludeClasses.contains(clazz);
    }
}
