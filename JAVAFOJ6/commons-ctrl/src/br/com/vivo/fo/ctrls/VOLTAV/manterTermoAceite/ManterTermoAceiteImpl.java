package br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite.db.ManterTermoAceiteDB;
import br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite.db.ManterTermoAceiteDB.ItemMenuTermoAceite;

@Stateless(name = "ManterTermoAceite", mappedName = "ManterTermoAceite")
@Local(ManterTermoAceite.class)
@Session(ejbName = "ManterTermoAceite", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterTermoAceiteImpl implements ManterTermoAceite {

    @EJB
    private ManterTermoAceiteDB manterTermoAceite;

    /**
     * @common:operation
     */
    public ItemMenuTermoAceite[] consultarListaServicos() throws Exception {
        return manterTermoAceite.getListaServico(montarConsultaServicoSQL(null));
    }

    /**
     * @common:operation
     */
    public ItemMenuTermoAceite buscarTermoServico(Short idItemMenu) throws Exception {
        return manterTermoAceite.getTermoAceiteServico(montarConsultaServicoSQL(idItemMenu));
    }

    /**
     * @common:operation
     */
    public void atualizarTermoServico(Short idItemMenu, String statusTermo, String textoTermo, String idUsuario) throws Exception {
        manterTermoAceite.setTextoTermo(montarInsertTermoSQL(idItemMenu, statusTermo, textoTermo, idUsuario));
    }

    private String montarConsultaServicoSQL(Short idItemMenu) {

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT    A.IDITEMMENU,                             ");
        sb.append("           A.INATIVO statusTermo,                    ");
        sb.append("           A.DSTERMOACEITE txtTermo,                 ");
        sb.append("           A.IDUSUARIOALTERACAO,                     ");
        sb.append("           B.NMITEM,                                 ");
        sb.append("           A.DTULTIMAALTERACAO dataAlteracaotTermo ");
        sb.append(" FROM      VOL.MENUTERMOACEITE A,                    ");
        sb.append("           ACESSO.ITEMMENU B                         ");
        sb.append("           WHERE  A.IDITEMMENU = B.IDITEMMENU        ");
        if (idItemMenu != null) {
            sb.append("              AND B.IDITEMMENU = ").append(idItemMenu);
        }

        return sb.toString();
    }

    private String montarInsertTermoSQL(Short idItemMenu, String statusTermo, String textoTermo, String idUsuario) {

        StringBuffer sb = new StringBuffer();

        sb.append(" UPDATE VOL.MENUTERMOACEITE");
        sb.append("        SET  INATIVO = ").append(statusTermo).append(",");
        sb.append("             DSTERMOACEITE = '").append(textoTermo).append("',");
        sb.append("             DTULTIMAALTERACAO = SYSDATE,");
        sb.append("             IDUSUARIOALTERACAO = ").append(String.valueOf(new Integer(idUsuario)));
        sb.append(" WHERE       IDITEMMENU = ").append(idItemMenu);

        return sb.toString();
    }
}
