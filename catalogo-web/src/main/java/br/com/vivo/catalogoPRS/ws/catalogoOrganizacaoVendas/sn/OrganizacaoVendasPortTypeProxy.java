package br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn;

public class OrganizacaoVendasPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.OrganizacaoVendasPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.OrganizacaoVendasPortType organizacaoVendasPortType = null;
  
  public OrganizacaoVendasPortTypeProxy() {
    _initOrganizacaoVendasPortTypeProxy();
  }
  
  public OrganizacaoVendasPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initOrganizacaoVendasPortTypeProxy();
  }
  
  private void _initOrganizacaoVendasPortTypeProxy() {
    try {
      organizacaoVendasPortType = (new br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.OrganizacaoVendasServiceLocator()).getOrganizacaoVendasSoapPort();
      if (organizacaoVendasPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)organizacaoVendasPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)organizacaoVendasPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (organizacaoVendasPortType != null)
      ((javax.xml.rpc.Stub)organizacaoVendasPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.OrganizacaoVendasPortType getOrganizacaoVendasPortType() {
    if (organizacaoVendasPortType == null)
      _initOrganizacaoVendasPortTypeProxy();
    return organizacaoVendasPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.BuscarListaOrgVendasComDDDResponse buscarListaOrgVendasComDDD(br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.BuscarListaOrgVendasComDDDRequest buscarListaOrgVendasComDDDRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.mc.geral.ErroInfo{
    if (organizacaoVendasPortType == null)
      _initOrganizacaoVendasPortTypeProxy();
    return organizacaoVendasPortType.buscarListaOrgVendasComDDD(buscarListaOrgVendasComDDDRequest, username, password);
  }
  
  
}