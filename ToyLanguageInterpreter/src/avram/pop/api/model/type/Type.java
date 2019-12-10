package avram.pop.api.model.type;

import avram.pop.api.model.value.Value;

public interface Type {
    Value defaultValue();

    Type copy();
}
