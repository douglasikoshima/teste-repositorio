package cliente.PrePago.sincronismo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cliente.simulador.urin.XmlPrettyFormatter;
import com.indracompany.actions.AbstractAction;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class SincronismoController extends AbstractAction {

    private static final long serialVersionUID = -6256434137906139047L;

    private SincronismoForm   form;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("sincronismo".equals(mapping.getParameter())) {
            return sincronismo(mapping, form, request, response);
        } else if ("resetarTentativas".equals(mapping.getParameter())) {
            return resetarTentativas(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        form = (SincronismoForm) formParam;
        form.setXml(ConstantesCRM.SVAZIO);
        request.setAttribute(ConstantesCRM.SERROR, ConstantesCRM.SVAZIO);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    protected ActionForward sincronismo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        form = (SincronismoForm) formParam;
        request.setAttribute(ConstantesCRM.SERROR, ConstantesCRM.SVAZIO);
        String nrLinha = form.getNrLinha();
        String ddd = nrLinha.substring(1, 3);
        String tel=null;
        if (nrLinha.substring(4).length()==9)
        	tel = nrLinha.substring(4, 8).concat(nrLinha.substring(9, 13));
        else 
        	tel = nrLinha.substring(4, 9).concat(nrLinha.substring(10, 14));
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT idfilasetclientinfo, cderro, dserro, xml1 ");
            query.append(" FROM apoio.arearegistro ar, linha.linhabase lb, linha.linhatelefonica lt, infra.filasetclientinfo fc ");
            query.append(" WHERE ar.idarearegistro = lb.idarearegistro ");
            query.append("   AND lb.idlinhabase = lt.idlinhabase ");
            query.append("   AND fc.idlinhatelefonica = lt.idlinhatelefonica ");
            query.append("   AND ar.cdarearegistro = ").append(ddd);
            query.append("   AND lb.nrlinha = ").append(tel);
            
            Pesquisar pesquisar = new Pesquisar();
            Resultset rs = pesquisar.executar(query.toString());
            
            if (rs != null && rs.getLinhas() != null) {
                Resultset.Linhas.Linha linhas[] = rs.getLinhas().getLinhaArray();
                
                if (linhas.length == 0) {
                    request.setAttribute(ConstantesCRM.SERROR, "Dados não disponíveis no Banco, por favor, digite um número de linha válido!");
                    return mapping.findForward(ConstantesCRM.SUCCESS);
                
                } else {
                    Resultset.Linhas.Linha linha = linhas[0];
                    String id = linha.getValorArray(0);
                    String cdErro = linha.getValorArray(1);
                    String dsErro = linha.getValorArray(2);
                    String xml = linha.getValorArray(3);
                    xml = XmlPrettyFormatter.xmlPrettyFormat(xml, 5);
                    
                    form.setId(id);
                    form.setNrLinha(nrLinha);
                    form.setCdErro(cdErro);
                    form.setDsErro(dsErro);
                    form.setXml(xml);
                    
                    return mapping.findForward(ConstantesCRM.SUCCESS);
                }
            }
        
        } catch (Exception e) {
            request.setAttribute(ConstantesCRM.SERROR, "Dados não disponíveis no Banco, por favor, digite um número de linha válido!");
        }
        
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    protected ActionForward resetarTentativas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        form = (SincronismoForm) formParam;
        request.setAttribute(ConstantesCRM.SERROR, "");
        String id = form.getId();
        try {
            StringBuffer query = new StringBuffer();
            query.append("UPDATE infra.filasetclientinfo ");
            query.append("   SET qtretry = 0, inerro = 0 ");
            query.append(" WHERE idfilasetclientinfo = ").append(id);
            query.append("   AND inerro = 1 ");
            query.append("   AND qtretry = 10 ");
            
            Pesquisar pesquisar = new Pesquisar();
            Resultset rs = pesquisar.executar(query.toString());
            
            if (rs.getMsg() == null) {
                request.setAttribute(ConstantesCRM.SERROR, "Requisição retornou à Fila com Sucesso!");
            } else {
                request.setAttribute(ConstantesCRM.SERROR, rs.getMsg());
            }
            
            return mapping.findForward(ConstantesCRM.SUCCESS);
        
        } catch (Exception e) {
            request.setAttribute(ConstantesCRM.SERROR, "Erro de conecxão ao Banco!");
        }
        
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public static class SincronismoForm extends ActionForm {

        private static final long serialVersionUID = -5875704767025058926L;

        private String            id               = ConstantesCRM.SVAZIO;
        private String            nrLinha          = ConstantesCRM.SVAZIO;
        private String            cdErro           = ConstantesCRM.SVAZIO;
        private String            dsErro           = ConstantesCRM.SVAZIO;
        private String            xml              = ConstantesCRM.SVAZIO;

        public void setId(String id) {
            if (id == null) {
                this.id = ConstantesCRM.SVAZIO;
            }
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setNrLinha(String nrLinha) {
            if (nrLinha == null) {
                this.nrLinha = ConstantesCRM.SVAZIO;
            }
            this.nrLinha = nrLinha;
        }

        public String getNrLinha() {
            return this.nrLinha;
        }

        public void setCdErro(String cdErro) {
            if (cdErro == null) {
                this.cdErro = ConstantesCRM.SVAZIO;
            }
            this.cdErro = cdErro;
        }

        public String getCdErro() {
            return this.cdErro;
        }

        public void setDsErro(String dsErro) {
            if (dsErro == null) {
                this.dsErro = ConstantesCRM.SVAZIO;
            }
            this.dsErro = dsErro;
        }

        public String getDsErro() {
            return this.dsErro;
        }

        public void setXml(String xml) {
            if (xml == null) {
                this.xml = ConstantesCRM.SVAZIO;
            }
            this.xml = xml;
        }

        public String getXml() {
            return this.xml;
        }
    }
}
