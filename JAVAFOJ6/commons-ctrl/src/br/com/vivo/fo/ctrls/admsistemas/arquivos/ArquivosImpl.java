package br.com.vivo.fo.ctrls.admsistemas.arquivos;

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
import br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses.ArquivosRS;
import br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses.RelatorioUpload;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "Arquivos", mappedName = "Arquivos")
@Local(Arquivos.class)
@Session(ejbName = "Arquivos", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArquivosImpl implements Arquivos {

	private static Logger log = new Logger("admsistemas");

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.arquivos.ArquivosDB arquivosDB;

    
    /**
     * @common:operation
     */
    @Override
	public ArquivoProcessamentoVO getDadosArquivosProcessados(String user, String sgFuncionalidade) {

		ArquivoProcessamentoVO vo = ArquivoProcessamentoVO.Factory.newInstance();
		try {
			if (sgFuncionalidade != null && !ConstantesCRM.SVAZIO.equals(sgFuncionalidade)) {

				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("SELECT DSERROPROCESSAMENTO, DTINCLUSAO, DTPROCESSAMENTO, ");
				query.append("		NMARQUIVO, NMLOGINUSUARIO, QTREGISTROSDESCARTADOS, QTREGISTROSPROCESSADOS ");
				query.append(" FROM ( " );
				query.append(" SELECT U.NMLOGINUSUARIO,   ");
				query.append("       TO_CHAR (AF.DTINCLUSAO, 'DD/MM/YYYY HH24:MI') AS DTINCLUSAO,  ");
				query.append("       TO_CHAR (AF.DTPROCESSAMENTO, 'DD/MM/YYYY HH24:MI') AS DTPROCESSAMENTO,  ");
				query.append("       (CASE WHEN REPLACE(DSERROPROCESSAMENTO,' ','') IS NOT NULL THEN DSERROPROCESSAMENTO  ");
				query.append("           WHEN REPLACE(DSERROPROCESSAMENTO,' ','') IS NULL AND INCONTROLEPROCESSAMENTO=1 THEN 'DISPONIVEL' ");
				query.append("           WHEN REPLACE(DSERROPROCESSAMENTO,' ','') IS NULL AND INCONTROLEPROCESSAMENTO=2 THEN 'PROCESSANDO' ");
				query.append("           WHEN REPLACE(DSERROPROCESSAMENTO,' ','') IS NULL AND INCONTROLEPROCESSAMENTO=3 THEN 'SUCESSO' END) DSERROPROCESSAMENTO,  ");
				query.append("       AF.QTREGISTROSDESCARTADOS, AF.QTREGISTROSPROCESSADOS,  ");
				query.append("       AF.NMARQUIVO ");
				query.append(" FROM ACESSO.USUARIO U, INFRA.ARQUIVOFUNCIONALIDADE AF");
				query.append(" WHERE AF.IDUSUARIOINCLUSAO = U.IDPESSOAUSUARIO(+)  ");
				query.append(" AND AF.SGFUNCIONALIDADE = '").append(sgFuncionalidade).append("' ");
				query.append(" ORDER BY AF.DTINCLUSAO DESC)  ");
				query.append(" WHERE ROWNUM < 6 ");

				log.debug("[ArquivosImpl.getDadosArquivosProcessados] SQL " + query.toString());
				ArquivosRS[] dbResult = arquivosDB.executeQuery(query.toString());

				vo.setSgFuncionalidade(sgFuncionalidade);
				for (int i = 0; i < dbResult.length; i++) {
					ArquivoProcessamentoVO.Item item = vo.addNewItem();
					item.setDsStatusCarga(dbResult[i].getDsErroProcessamento());
					item.setDtEnvio(dbResult[i].getDtInclusao());
					item.setDtProcessamento(dbResult[i].getDtProcessamento());
					item.setNmFileBad(dbResult[i].getNmArquivo());
					item.setDsLoginUsuario(dbResult[i].getNmLoginUsuario());
                    if (sgFuncionalidade.equalsIgnoreCase("BLCRD") || sgFuncionalidade.equalsIgnoreCase("BLMSG") || sgFuncionalidade.equalsIgnoreCase("BLDDS") || sgFuncionalidade.equalsIgnoreCase("CCLTR")) {
						item.setHasFileBad(ConstantesCRM.SZERO); // Não habilitar o download do arquivo de erros para funcionalidades
					} else {
					item.setHasFileBad(dbResult[i].getQtRegistrosDescartados() > 0 ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
					}
					item.setQtRegRejeitados(String.valueOf(dbResult[i].getQtRegistrosDescartados()));
					item.setQtRegTotal(String.valueOf(dbResult[i].getQtRegistrosProcessados()));
				}
				query = null;
				dbResult = null;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			vo = null;
		}
		return vo;
	}

    /**
     * @common:operation
     */
    @Override
	public ArquivoProcessamentoVO getDadosArquivosProcessadosAnatel(String dtInicio, String dtFim) {

		log.info("[ArquivosImpl.getDadosArquivosProcessadosAnatel] Inicio " + dtInicio + " " + dtFim);
		ArquivoProcessamentoVO vo = ArquivoProcessamentoVO.Factory.newInstance();
		try {

			String sgFuncionalidade = "ANTFO";

			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("SELECT DSERROPROCESSAMENTO, DTINCLUSAO, DTPROCESSAMENTO, ");
			query.append("		IDATENDIMENTOANATELARQUIVO, NMARQUIVO, NMARQUIVOORIGINAL, ");
			query.append("		NMLOGINUSUARIO, QTREGISTROSDESCARTADOS, QTREGISTROSPROCESSADOS ");
			query.append(" FROM (  ");
			query.append(" SELECT U.NMLOGINUSUARIO,   ");
			query.append("       TO_CHAR(AF.DTINCLUSAO, 'DD/MM/YYYY HH24:MI') AS DTINCLUSAO,    ");
			query.append("       TO_CHAR(AF.DTPROCESSAMENTO, 'DD/MM/YYYY HH24:MI') AS DTPROCESSAMENTO,    ");
			query.append("       (CASE WHEN REPLACE(DSERROPROCESSAMENTO,' ','') IS NOT NULL THEN dserroprocessamento  ");
			query.append("           WHEN REPLACE(DSERROPROCESSAMENTO,' ','') IS NULL AND INCONTROLEPROCESSAMENTO=1 THEN 'DISPONIVEL' ");
			query.append("           WHEN REPLACE(DSERROPROCESSAMENTO,' ','') IS NULL AND INCONTROLEPROCESSAMENTO=2 THEN 'PROCESSANDO'  ");
			query.append("           WHEN REPLACE(DSERROPROCESSAMENTO,' ','') IS NULL AND INCONTROLEPROCESSAMENTO=3 THEN 'SUCESSO' END) DSERROPROCESSAMENTO,   ");
			query.append("       AA.QTREJEITADO QTREGISTROSDESCARTADOS,  ");
			query.append("       AA.QTTOTALREGISTROS QTREGISTROSPROCESSADOS,   ");
			query.append("       NVL(AF.NMARQUIVO, ' ') NMARQUIVO, ");
			query.append("       NVL(AF.NMARQUIVOORIGINAL, ' ') NMARQUIVOORIGINAL, ");
			query.append("       NVL(AF.IDATENDIMENTOANATELARQUIVO, '0') IDATENDIMENTOANATELARQUIVO ");
			query.append(" FROM   ACESSO.USUARIO U,  INFRA.ARQUIVOFUNCIONALIDADE AF,  ");
			query.append("       ATENDIMENTO.ATENDIMENTOANATELARQUIVO AA   ");
			query.append(" WHERE  AF.IDUSUARIOINCLUSAO = U.IDPESSOAUSUARIO   ");
			query.append("       AND AF.SGFUNCIONALIDADE = '").append(sgFuncionalidade).append("' ");
			query.append("       AND AF.DTINCLUSAO BETWEEN TO_DATE('").append(dtInicio).append(" 00:01', 'DD/MM/YYYY HH24:MI') AND TO_DATE('").append(dtFim).append(" 23:59', 'DD/MM/YYYY HH24:MI') ");
			query.append("       AND AA.IDATENDIMENTOANATELARQUIVO(+) = AF.IDATENDIMENTOANATELARQUIVO ");
			query.append(" ORDER  BY af.DTINCLUSAO DESC)  ");
			query.append(" WHERE  ROWNUM < 6 ");

			//log.debug("[ArquivosImpl.getDadosArquivosProcessadosAnatel] SQL " + query.toString());
			ArquivosRS[] dbResult = arquivosDB.executeQuery(query.toString());

			vo.setSgFuncionalidade(sgFuncionalidade);
			for (int i = 0; i < dbResult.length; i++) {
				ArquivoProcessamentoVO.Item item = vo.addNewItem();
				item.setDsLoginUsuario(dbResult[i].getNmLoginUsuario());
				item.setDsStatusCarga(dbResult[i].getDsErroProcessamento());
				item.setDtEnvio(dbResult[i].getDtInclusao());
				item.setDtProcessamento(dbResult[i].getDtProcessamento());
				item.setHasFileBad(dbResult[i].getQtRegistrosDescartados() > 0 ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
				item.setNmFileBad(dbResult[i].getNmArquivo());
				item.setQtRegRejeitados(String.valueOf(dbResult[i].getQtRegistrosDescartados()));
				item.setQtRegTotal(String.valueOf(dbResult[i].getQtRegistrosProcessados()));
				item.setNmFileOriginal(String.valueOf(dbResult[i].getNmArquivoOriginal()));
				item.setIdAtendimentoAnatelArquivo(String.valueOf(dbResult[i].getIdAtendimentoAnatelArquivo()));
			}

			query = null;
			dbResult = null;

		} catch (Exception e) {
			log.error("[ArquivosImpl.getDadosArquivosProcessadosAnatel] Erro " + e.getMessage(), e);
			e.printStackTrace(System.err);
			vo = null;
		}
		return vo;
	}

    /**
     * @common:operation
     */
    @Override
	public int getQuantidadeArquivos(String user, String sgFuncionalidade) {

		int qtdArquivos = 0;

        StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) ")
		.append("  FROM INFRA.ARQUIVOFUNCIONALIDADE ")
		.append(" WHERE SGFUNCIONALIDADE = '").append(sgFuncionalidade).append("' ");

		try {
			qtdArquivos = arquivosDB.getInt(query.toString());
		} catch (Exception e) {
			qtdArquivos = 0;
		}

		qtdArquivos = qtdArquivos + 1;

		return qtdArquivos;

	}

	/**
	 * @common:operation
	 */
    @Override
	public void incluiProcessamentoArquivo(String idUser, String sgFunc, String nmArq) throws Exception {
		log.info("[ArquivosImpl.incluiProcessamentoArquivo] Inicio " + nmArq);
		try {
			if (sgFunc != null && !ConstantesCRM.SVAZIO.equals(sgFunc)) {
				StringBuffer query = new StringBuffer("insert into infra.arquivofuncionalidade ");
				query.append(" (sgfuncionalidade, nmarquivo, idusuarioinclusao, dtinclusao, incontroleprocessamento) values (");
				query.append(" '").append(sgFunc).append("',");
				query.append(" '").append(nmArq).append("',").append(idUser).append(", sysdate, 1) ");

				arquivosDB.executeCommand(query.toString());

				query = null;
			}
		} catch (Exception e) {
			throw (new FacadeException("Erro na inclusão do Arquivo: ArquivosImpl:incluiProcessamentoArquivo", e));
		}
	}

	/**
	 * @common:operation
	 */
    @Override
	public void incluiProcessamentoArquivoAnatel(String idUser, String nmArq, String nmArquivoOriginal) throws Exception {

		log.info("[ArquivosImpl.incluiProcessamentoArquivoAnatel] Inicio " + nmArq);
		try {
			StringBuffer query = new StringBuffer("INSERT INTO INFRA.ARQUIVOFUNCIONALIDADE ");
			query.append(" (SGFUNCIONALIDADE, NMARQUIVO, IDUSUARIOINCLUSAO, DTINCLUSAO, INCONTROLEPROCESSAMENTO ,NMARQUIVOORIGINAL) VALUES (");
			query.append(" '").append("ANTFO").append("',");
			query.append(" '").append(nmArq).append("',").append(idUser).append(", sysdate, 1, '").append(nmArquivoOriginal).append("' ) ");

			arquivosDB.executeCommand(query.toString());

			query = null;
		} catch (Exception e) {
			throw (new FacadeException("Erro na inclusão do Arquivo: ArquivosImpl:incluiProcessamentoArquivo", e));
		}
	}

	/**
	 * @common:operation
	 */
    @Override
	public RelatorioUpload getRelatorioUpload(String idAtendimentoAnatelArquivo) throws Exception {

		log.info("[ArquivosImpl.getRelatorioUpload] Inicio " + idAtendimentoAnatelArquivo);
		try {
            StringBuilder query = new StringBuilder();
			query.append("SELECT  AF.NMARQUIVOORIGINAL, AA.NMARQUIVO, ");
			query.append("        TO_CHAR(AA.DTENVIO, 'DD/MM/YYYY HH24:MI') AS DTENVIO, ");
			query.append("        NVL(AA.DSARQUIVOLOG, ' ') DSARQUIVOLOG, ");
			query.append("        AA.IDATENDIMENTOANATELARQUIVO, AA.QTTOTALREGISTROS, AA.QTPROCESSADO, ");
			query.append("        AA.QTREJEITADO, AA.QTPROTOCOLOCLIENTE, AA.QTPROTOCOLONAOCLIENTE, ");
			query.append("        AA.QTCONTATONOVO, AA.QTCONTATOGENERICO ");
			query.append("FROM    INFRA.ARQUIVOFUNCIONALIDADE AF, ATENDIMENTO.ATENDIMENTOANATELARQUIVO AA ");
			query.append("WHERE   AA.IDATENDIMENTOANATELARQUIVO = AF.IDATENDIMENTOANATELARQUIVO ");
			query.append("        AND AA.IDATENDIMENTOANATELARQUIVO = ").append(idAtendimentoAnatelArquivo);

			RelatorioUpload relatorioUpload = arquivosDB.buscarRelatorioUpload(query.toString());
			log.info("[ArquivosImpl.getRelatorioUpload] relatorioUpload " + relatorioUpload);

			return relatorioUpload;

		} catch (Exception e) {
			log.error("[ArquivosImpl.getRelatorioUpload] Erro " + e.getMessage(), e);
			throw (new FacadeException("Erro na inclusão do Arquivo: ArquivosImpl:incluiProcessamentoArquivo", e));
		}
	}
}
