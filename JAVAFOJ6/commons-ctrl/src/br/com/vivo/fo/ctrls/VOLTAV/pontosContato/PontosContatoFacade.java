package br.com.vivo.fo.ctrls.VOLTAV.pontosContato;

import javax.ejb.Local;

@Local
public interface PontosContatoFacade {

    public br.com.vivo.fo.voltav.vo.PontosContatoVODocument.PontosContatoVO consultarProcessamento(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.pontosContato.db.RelatorioContatoDB.PontoContato[] consultarPontosContatoPorCnpj(java.lang.String cdCnpjEmpresa) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.VOLTAV.pontosContato.db.RelatorioContatoDB.PontoContato[] consultarPontosContato(java.lang.String cdCnpjEmpresa) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.VOLTAV.pontosContato.db.RelatorioContatoDB.PontoContato[] consultarPontosContatoCompleto(int pageInicial, int pageFinal) throws java.lang.Exception;
}
