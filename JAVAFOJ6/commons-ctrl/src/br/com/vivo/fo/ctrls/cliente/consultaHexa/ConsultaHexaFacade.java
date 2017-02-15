package br.com.vivo.fo.ctrls.cliente.consultaHexa;

import javax.ejb.Local;

@Local
public interface ConsultaHexaFacade {

    public br.com.vivo.fo.cliente.vo.MonitorarHexaVODocument.MonitorarHexaVO getMonitoramentoHexa(br.com.vivo.fo.cliente.vo.MonitorarHexaVODocument.MonitorarHexaVO hexaVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ConsultaHexaVODocument.ConsultaHexaVO getConsultaHexa(br.com.vivo.fo.cliente.vo.ConsultaHexaVODocument.ConsultaHexaVO hexaVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
