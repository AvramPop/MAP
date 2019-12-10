package avram.pop.api.model.value;

import avram.pop.api.model.type.Type;

public interface Value {
    Type getType();

    Value copy();
}
