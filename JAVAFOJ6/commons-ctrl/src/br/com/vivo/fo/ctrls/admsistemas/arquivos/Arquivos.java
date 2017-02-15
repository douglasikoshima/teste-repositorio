package br.com.vivo.fo.ctrls.admsistemas.arquivos;

import javax.ejb.Local;

@Local
public interface Arquivos {

    public void incluiProcessamentoArquivo(java.lang.String idUser, java.lang.String sgFunc, java.lang.String nmArq) throws java.lang.Exception;

    public int getQuantidadeArquivos(java.lang.String user, java.lang.String sgFuncionalidade);

    public br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO getDadosArquivosProcessados(java.lang.String user, java.lang.String sgFuncionalidade);

    public br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO getDadosArquivosProcessadosAnatel(java.lang.String dtInicio, java.lang.String dtFim);

    public void incluiProcessamentoArquivoAnatel(java.lang.String idUser, java.lang.String nmArq, java.lang.String nmArquivoOriginal) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses.RelatorioUpload getRelatorioUpload(java.lang.String idAtendimentoAnatelArquivo) throws java.lang.Exception;
}
