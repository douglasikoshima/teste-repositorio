package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica;

public class NotaFiscalEletronicaPortTypeProxy implements br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortType notaFiscalEletronicaPortType = null;
  
  public NotaFiscalEletronicaPortTypeProxy() {
    _initNotaFiscalEletronicaPortTypeProxy();
  }
  
  public NotaFiscalEletronicaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initNotaFiscalEletronicaPortTypeProxy();
  }
  
  private void _initNotaFiscalEletronicaPortTypeProxy() {
    try {
      notaFiscalEletronicaPortType = (new br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaLocator()).getNotaFiscalEletronicaPort();
      if (notaFiscalEletronicaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)notaFiscalEletronicaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)notaFiscalEletronicaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (notaFiscalEletronicaPortType != null)
      ((javax.xml.rpc.Stub)notaFiscalEletronicaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortType getNotaFiscalEletronicaPortType() {
    if (notaFiscalEletronicaPortType == null)
      _initNotaFiscalEletronicaPortTypeProxy();
    return notaFiscalEletronicaPortType;
  }
  
  public java.lang.Object consultarInformacoesConfianca(br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfianca consultarInformacoesConfiancaRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.ErroInfo{
    if (notaFiscalEletronicaPortType == null)
      _initNotaFiscalEletronicaPortTypeProxy();
    return notaFiscalEletronicaPortType.consultarInformacoesConfianca(consultarInformacoesConfiancaRequest);
  }
  
  
}