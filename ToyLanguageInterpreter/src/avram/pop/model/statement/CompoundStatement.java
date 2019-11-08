package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.utils.StackInterface;
import avram.pop.model.control.ProgramState;

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
        return state;
    }
}
