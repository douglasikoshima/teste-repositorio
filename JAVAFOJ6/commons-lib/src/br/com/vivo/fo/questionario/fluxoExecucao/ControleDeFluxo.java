package br.com.vivo.fo.questionario.fluxoExecucao; 

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.questionario.vo.PerguntaRespondidaVODocument.PerguntaRespondidaVO;
import java.util.ArrayList;

public class ControleDeFluxo 
{ 
    private ArrayList perguntasRespondidas = new ArrayList();
    
    
    public void addResposta(PerguntaRespondidaVO perguntaResp){
        
        perguntasRespondidas.add(perguntaResp);
        
    }
    
    //remove uma seguencia de perguntas a partir de um indice
    public void removeSeqRespostas(int indice)throws IndexOutOfBoundsException {
        
        for(int i = indice + 1; i < perguntasRespondidas.size(); i++ ){
            perguntasRespondidas.remove(i);
        }
        
    }
    
    // retorna a segurncia de respostas respondidas
    public PerguntaRespondidaVO[] getSeqRespostas(){
        
        PerguntaRespondidaVO[] seqRespostas = new PerguntaRespondidaVO[0];
       
        return (PerguntaRespondidaVO[])this.perguntasRespondidas.toArray(seqRespostas);        
    }
    
    //VERIFICA SE A RESPOSTA ESCOLHIDA DIRECIONA O FLUXO
    /*public boolean verificaProximaPergunta(int indice)throws IndexOutOfBoundsException{
        
        boolean result = false;
        
        PerguntaRespondidaVO pergunta = (PerguntaRespondidaVO)this.perguntasRespondidas.get(indice);
        
        if(pergunta.getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray(pergunta.getIndiceRespostaArray(0)).getIdProximaPergunta().equals("0")){
            result = true;
        }
        
        return result;
    }*/
    
    //RETORNA O XML PARA SER ENVIADO AO SERVIÇO TUXEDO
    public String montaXml(){
        
        String xml = ConstantesCRM.SVAZIO;
        
        for(int i = 0; i< perguntasRespondidas.size() ; i++){
            
            PerguntaRespondidaVO perguntaRespondidaVO = (PerguntaRespondidaVO)perguntasRespondidas.get(i);
            
            xml += perguntaRespondidaVO.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
        }
        
        return xml;
    }
    
    /*
     * VERIFICA SE A RESPOSTA SELECIONADA ALTERA O FLUXO DO QUESTIONARIO, SE ALTERAR,
     * TODAS AS RESPOSTAS SEGUINTES SERÃO APAGADAS.
    */
    /*public void alteraResposta(int indice,PerguntaRespondidaVO resposta){
        if(verificaProximaPergunta(indice)== false){           
            
            perguntasRespondidas.set(indice,resposta);
            
        }else{
            
            removeSeqRespostas(indice);
            perguntasRespondidas.add(resposta);
            
        }        
    }*/
    
    public PerguntaRespondidaVO getRestosta(int indice)throws IndexOutOfBoundsException{
        
        PerguntaRespondidaVO retorno = (PerguntaRespondidaVO)perguntasRespondidas.get(indice);
        
        return retorno;
    }
    
    public void limpaLista(){
        
        perguntasRespondidas.clear();
        
    }
    
} 
