package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.type.BoolType;
import avram.pop.model.type.Type;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.Value;
import avram.pop.utils.CloneFactory;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.MyException;

public class WhileStatement implements Statement{
    private Expression condition;
    private Statement statement;

    public WhileStatement(Expression condition, Statement statement){
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public String toString(){
        return "WhileStatement{" +
                "condition=" + condition +
                ", statement=" + statement +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        Value expressionEvaluation = condition.evaluate(state.getSymbolTable(), state.getHeap());
        if(expressionEvaluation.getType().equals(new BoolType())){
            BoolValue expressionValue = (BoolValue) expressionEvaluation;
            if(expressionValue.getValue()){
                state.getExecutionStack().push(this);
                statement.execute(state);
            }
        } else {
            throw new MyException("expression is not a bool");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        Type conditionType = condition.typecheck(typeEnvironment);
        if (conditionType.equals(new BoolType())) {
            statement.typecheck(CloneFactory.cloneTypeEnvironment(typeEnvironment));
            return typeEnvironment;
        }
        else
            throw new MyException("The condition of WHILE has not the type bool");
    }
}
