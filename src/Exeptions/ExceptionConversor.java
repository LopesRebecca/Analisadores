package Exeptions;

public class ExceptionConversor extends RuntimeException {
    public ExceptionConversor(String mensagemErro) {
        super(mensagemErro);
    }
}