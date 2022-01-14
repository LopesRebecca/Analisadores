package Exeptions;

public class ExceptionConverter extends RuntimeException {
    public ExceptionConverter(String mensagemErro) {
        super(mensagemErro);
    }
}