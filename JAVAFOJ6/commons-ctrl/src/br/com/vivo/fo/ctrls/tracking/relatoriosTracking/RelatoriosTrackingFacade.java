package br.com.vivo.fo.ctrls.tracking.relatoriosTracking;

import javax.ejb.Local;

import br.com.vivo.fo.cliente.vo.TrackingRelatorioConsolidadoVODocument.TrackingRelatorioConsolidadoVO;
import br.com.vivo.fo.cliente.vo.TrackingRelatorioDetalhadoVODocument.TrackingRelatorioDetalhadoVO;
import br.com.vivo.fo.cliente.vo.TrackingRelatoriosFiltrosVODocument.TrackingRelatoriosFiltrosVO;

@Local
public interface RelatoriosTrackingFacade {

    public TrackingRelatorioConsolidadoVO getRelatorioConsolidado(TrackingRelatoriosFiltrosVO filtro) throws Exception;

    public TrackingRelatorioDetalhadoVO getRelatorioDetalhado(TrackingRelatoriosFiltrosVO filtro) throws Exception;
}
