package br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn;

public class SegmentoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.SegmentoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.SegmentoPortType segmentoPortType = null;
  
  public SegmentoPortTypeProxy() {
    _initSegmentoPortTypeProxy();
  }
  
  public SegmentoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSegmentoPortTypeProxy();
  }
  
  private void _initSegmentoPortTypeProxy() {
    try {
      segmentoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.SegmentoServiceLocator()).getSegmentoSoapPort();
      if (segmentoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)segmentoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)segmentoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (segmentoPortType != null)
      ((javax.xml.rpc.Stub)segmentoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.SegmentoPortType getSegmentoPortType() {
    if (segmentoPortType == null)
      _initSegmentoPortTypeProxy();
    return segmentoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.BuscarListaSegmentacaoResponse buscarListaSegmentacao(br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.BuscarListaSegmentacaoRequest buscarListaSegmentacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoSegmento.mc.geral.ErroInfo{
    if (segmentoPortType == null)
      _initSegmentoPortTypeProxy();
    return segmentoPortType.buscarListaSegmentacao(buscarListaSegmentacaoRequest, username, password);
  }
  
  
}