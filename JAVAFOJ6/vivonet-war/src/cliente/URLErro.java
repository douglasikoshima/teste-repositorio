package cliente; 

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import javax.servlet.http.HttpSession;

public class URLErro { 

    public static String getURLErro(HttpSession session) {
        /*
        String url = new String("/FrontOfficeWeb/cliente/TelaInicial/TelaInicial.do?");
        try {
            if (session.getAttribute("idProspect")==null && session.getAttribute("naoCliente")==null) {
                // Tem linha (cliente ou usuário).
                url += "nrLinha=" + ((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
            } else {
                if(session.getAttribute("naoCliente")!=null){
                    // Não cliente
                    url += "naoCliente=TRUE";
                } else {
                    // Prospect
                    url += "idProspect=" + session.getAttribute("idProspect");
                }
            }
        } catch (Exception e) {
            url += "inicioTela=TRUE";
        }
        return url;
        */
        return ("/FrontOfficeWeb/cliente/TelaInicial/begin.do?nrLinha=" + ((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha());
    }
} 
