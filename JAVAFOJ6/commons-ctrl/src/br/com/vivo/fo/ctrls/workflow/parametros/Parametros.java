package br.com.vivo.fo.ctrls.workflow.parametros;

import javax.ejb.Local;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.FacadeException;

@Local
public interface Parametros {

    public ParametrosVO retornarParametros(String user, String idAtendimento) throws TuxedoException, FacadeException;
}
