package WebServicesWSDL.exception;

public class URAException extends Exception {

    private static final long serialVersionUID = 1901819509320436853L;

    private String codRetorno;

    public URAException(String codRetorno) {
        this.codRetorno = codRetorno;
    }

    public String getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(String cod) {
        codRetorno = cod;
    }
}