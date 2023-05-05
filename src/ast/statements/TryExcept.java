package src.ast.statements;

import java.util.List;
import src.ast.main.Type;

public class TryExcept extends Statement{
    public List<Statement> tryBody;
    public List<Statement> exceptBody;
    
    public TryExcept(List<Statement> tryBody, List<Statement> exceptBody, int line, int column, String fileLocation) {
        super(line, column);
        this.tryBody = tryBody;
        this.exceptBody = exceptBody;
    }

    public Type getType() {
        return Type.INT;
    }
}
