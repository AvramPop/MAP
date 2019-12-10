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

public class IfStatement implements Statement {
    private Expression expression;
    private Statement thenBranchStatement;
    private Statement elseBranchStatement;

    public IfStatement(Expression expression, Statement thenBranchStatement, Statement elseBranchStatement){
        this.expression = expression;
        this.thenBranchStatement = thenBranchStatement;
        this.elseBranchStatement = elseBranchStatement;
    }

    public String toString(){
        return "(IF(" + expression.toString() + ") THEN(" + thenBranchStatement.toString()
                + ")ELSE(" + elseBranchStatement.toString() + "))";
    }

    public ProgramState execute(ProgramState state) throws MyException{
        Value condition = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if(condition.getType().equals(new BoolType())){
            BoolValue booleanCondition = (BoolValue) condition;
            if(booleanCondition.getValue()){
                state.getExecutionStack().push(thenBranchStatement);
            } else {
                state.getExecutionStack().push(elseBranchStatement);
            }
        } else {
            throw new MyException("conditional expr is not a boolean");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        Type expressionType = expression.typecheck(typeEnvironment);
        if (expressionType.equals(new BoolType())) {
            thenBranchStatement.typecheck(CloneFactory.cloneTypeEnvironment(typeEnvironment));
            elseBranchStatement.typecheck(CloneFactory.cloneTypeEnvironment(typeEnvironment));
            return typeEnvironment;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }
}
