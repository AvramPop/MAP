package avram.pop.model.value;

import avram.pop.model.type.Type;

public interface Value {
    Type getType();

    Value copy();
}
