package br.com.vivo.fo.ctrls.usuario.permissaoMassiva;

import javax.ejb.Local;

@Local
public interface ArquivoManutencaoFacade {

	public java.lang.Integer initTableManut(java.lang.String fileName, java.lang.String login, java.lang.Integer funcionalidade);
	
	public br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO selectTableByFunc(java.lang.String funcionalidade);
	
	public java.lang.Boolean eraseTableManut(java.lang.Integer sq);
	
    public String buscarParametroPath(String cdParametro);
}
