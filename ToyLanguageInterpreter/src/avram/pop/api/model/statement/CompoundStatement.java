package avram.pop.api.model.statement;

import avram.pop.api.model.type.Type;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.utils.MyException;
import avram.pop.api.utils.StackInterface;
import avram.pop.api.model.control.ProgramState;

public class CompoundStatement implements Statement {
    private Statement firstStatement;
    private Statement secondStatement;

    public CompoundStatement(Statement firstStatement, Statement secondStatement){
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    public String toString(){
        return "(" + firstStatement.toString() + "; " + secondStatement.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException{
        StackInterface<Statement> executionStack = state.getExecutionStack();
        executionStack.push(secondStatement);
        executionStack.push(firstStatement);
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        return secondStatement.typecheck(firstStatement.typecheck(typeEnvironment));
    }
}
