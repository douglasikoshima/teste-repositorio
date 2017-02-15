package br.com.vivo.fo.ctrls.tracking.previsaoentrega;

import javax.ejb.Local;

@Local
public interface PrevisaoEntrega {

    public br.com.vivo.fo.tracking.vo.TrackingPrevisaoEntregaVODocument.TrackingPrevisaoEntregaVO getTRACK(java.lang.String user, br.com.vivo.fo.tracking.vo.TrackingPrevisaoEntregaVODocument.TrackingPrevisaoEntregaVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.tracking.vo.TrackingPrevisaoEntregaVODocument.TrackingPrevisaoEntregaVO setTRACK(java.lang.String user, br.com.vivo.fo.tracking.vo.TrackingPrevisaoEntregaVODocument.TrackingPrevisaoEntregaVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
