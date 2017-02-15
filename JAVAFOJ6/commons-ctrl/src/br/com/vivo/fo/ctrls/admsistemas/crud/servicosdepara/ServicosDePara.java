package br.com.vivo.fo.ctrls.admsistemas.crud.servicosdepara;

import javax.ejb.Local;

@Local
public interface ServicosDePara {

    public void excluirServico(long idServico) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara[] buscarServicos() throws java.lang.Exception;

    public void salvarServico(br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara servicoDePara) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara buscarServico(long idServico) throws java.lang.Exception;
}
