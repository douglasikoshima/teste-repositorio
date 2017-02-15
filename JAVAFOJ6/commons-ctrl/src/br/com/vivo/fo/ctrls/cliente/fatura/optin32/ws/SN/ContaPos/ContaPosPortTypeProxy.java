package br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos;

public class ContaPosPortTypeProxy implements br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ContaPosPortType {
    private String           _endpoint        = null;
    private ContaPosPortType contaPosPortType = null;
    private String           userName;
    private String           password;

    public ContaPosPortTypeProxy() {
        _initContaPosPortTypeProxy();
    }

    public ContaPosPortTypeProxy(String endpoint) {
        _endpoint = endpoint;
        _initContaPosPortTypeProxy();
    }

    private void _initContaPosPortTypeProxy() {
        try {
            contaPosPortType = (new br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ContaPosLocator()).getContaPosPort();
            if (contaPosPortType != null) {
                if (_endpoint != null) {
                    ((javax.xml.rpc.Stub) contaPosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
                } else {
                    _endpoint = (String) ((javax.xml.rpc.Stub) contaPosPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
                }
            }

        } catch (javax.xml.rpc.ServiceException serviceException) {
        }
    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (contaPosPortType != null) {
            ((javax.xml.rpc.Stub) contaPosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        }

    }

    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ContaPosPortType getContaPosPortType() {
        if (contaPosPortType == null) {
            _initContaPosPortTypeProxy();
        }
        return contaPosPortType;
    }

    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta consultarContasEmAberto(
            br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ConsultarContasEmAbertoRequest consultarContasEmAbertoRequest) throws java.rmi.RemoteException,
            br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo {
        if (contaPosPortType == null) {
            _initContaPosPortTypeProxy();
        }
        return contaPosPortType.consultarContasEmAberto(consultarContasEmAbertoRequest);
    }

    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta consultarConta(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ConsultarContaRequest consultarContaRequest)
            throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo {
        if (contaPosPortType == null) {
            _initContaPosPortTypeProxy();
        }
        return contaPosPortType.consultarConta(consultarContaRequest);
    }

    public java.lang.Object solicitarSegundaViaConta(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ParametrosSolicitarSegundaViaConta solicitarSegundaViaContaRequest)
            throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo {
        if (contaPosPortType == null) {
            _initContaPosPortTypeProxy();
        }
        return contaPosPortType.solicitarSegundaViaConta(solicitarSegundaViaContaRequest);
    }

    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta consultarFaturaOnLine(
            br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ParametrosConsultarFaturaOnLine consultarFaturaOnLineRequest) throws java.rmi.RemoteException,
            br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo {
        if (contaPosPortType == null) {
            _initContaPosPortTypeProxy();
        }
        return contaPosPortType.consultarFaturaOnLine(consultarFaturaOnLineRequest);
    }

    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta manterFaturaOnLine(
            br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ParametrosManterFaturaOnLine manterFaturaOnLineRequest) throws java.rmi.RemoteException,
            br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo {
        if (contaPosPortType == null) {
            _initContaPosPortTypeProxy();
        }
        return contaPosPortType.manterFaturaOnLine(manterFaturaOnLineRequest);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}