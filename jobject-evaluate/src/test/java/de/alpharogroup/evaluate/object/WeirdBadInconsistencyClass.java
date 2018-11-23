package de.alpharogroup.evaluate.object;

import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeirdBadInconsistencyClass
{
    String name;

    @Override
    public int hashCode() {
        return EnhancedRandom.random(Integer.class);
    }

    @Override
    public boolean equals(Object o) {
        boolean randomBoolean = EnhancedRandom.random(boolean.class);
        return randomBoolean;
    }

    @Override
    public String toString() {
        return EnhancedRandom.random(String.class);
    }
}