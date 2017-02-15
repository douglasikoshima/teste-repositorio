package workflow.AtendimentoFila.Portabilidade.commons; 

public class CommonsPortabilidade { 

    public static final String formatarCep(String cep) {
        if(cep!= null && cep.indexOf("-") == -1 && cep.length() >= 5){
            cep = cep.substring(0,5) + "-" + cep.substring(5,cep.length());
            cep = cep.length()>9 ? cep.substring(0,9) : cep;
        }
        return cep;
    }

}
