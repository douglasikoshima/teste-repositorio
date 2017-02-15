package br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem;

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
import br.com.vivo.fo.admsistemas.vo.RelatorioBlindagemVODocument.RelatorioBlindagemVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem.dbClasses.ListaRelBlindagem;

@Stateless(name = "RelBlindagem", mappedName = "RelBlindagem")
@Local(RelBlindagem.class)
@Session(ejbName = "RelBlindagem", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelBlindagemImpl implements RelBlindagem {

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem.RelBlindagemDB relBlindagemDB;

	/** @common:operation */
	public RelatorioBlindagemVO getRelatorioBlindagem(String user, String dtInicial, String dtFinal) {
		ListaRelBlindagem[] dbResult = null;
		RelatorioBlindagemVO relatorio = RelatorioBlindagemVO.Factory.newInstance();
		try {
			if (dtInicial != null && !ConstantesCRM.SVAZIO.equals(dtInicial) && dtFinal != null && !ConstantesCRM.SVAZIO.equals(dtFinal)) {

				StringBuffer query = new StringBuffer("");
				query.append("SELECT  TO_CHAR(data,'DD/MM/YYYY') AS dtBlindagem,  ");
				query.append("		SUM(qtfidelizados) qtCliFid,  ");
				query.append("		SUM(qtbloqueados) qtCliInb  ");
				query.append("FROM( SELECT dtvalidade data,  ");
				query.append("       (CASE WHEN dtaceite IS NOT NULL THEN 1 ELSE  0 END ) qtfidelizados, ");
				query.append("       (CASE WHEN DTBLOQUEIOEXIBICAO<SYSDATE THEN 1 ELSE 0 END ) qtbloqueados ");
				query.append("FROM RETENCAO.CLIENTEBLINDAGEM a ");
				query.append("WHERE dtvalidade IS NOT NULL)  ");
				query.append("WHERE data<=TO_DATE('").append(dtFinal).append(" 23:59:59','dd/mm/yyyy hh24:mi:ss')  ");
				query.append("AND   data >=TO_DATE('").append(dtInicial).append(" 00:00:00','dd/mm/yyyy hh24:mi:ss') ");
				query.append("GROUP BY data ");

				dbResult = relBlindagemDB.executeQuery(query.toString());
				int qtTotalFid = 0;
				int qtTotalBlq = 0;
				for (int i = 0; i < dbResult.length; i++) {
					RelatorioBlindagemVO.Linhas linha = relatorio.addNewLinhas();
					linha.setDtReferencia(dbResult[i].getDtBlindagem());
					linha.setQtCliBlq(String.valueOf(dbResult[i].getQtCliInb()));
					linha.setQtCliFid(String.valueOf(dbResult[i].getQtCliFid()));
					qtTotalFid += dbResult[i].getQtCliFid();
					qtTotalBlq += dbResult[i].getQtCliInb();
				}
				relatorio.setQtTotalFid(String.valueOf(qtTotalFid));
				relatorio.setQtTotalBlq(String.valueOf(qtTotalBlq));
				query = null;
				dbResult = null;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			dbResult = null;
		}
		return relatorio;
	}

}
