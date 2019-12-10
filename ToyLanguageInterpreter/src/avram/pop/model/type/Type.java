package avram.pop.model.type;

import avram.pop.model.value.Value;

public interface Type {
    Value defaultValue();

    Type copy();
}
