package br.com.vivo.fo.ctrls.admsistemas.crud.servicosdepara;

import java.sql.SQLException;

import javax.ejb.Local;

@Local
public interface ServicosDeParaControl {

	static final long serialVersionUID = 166598985004584L;

	/**
	 * @jc:sql statement="SELECT IDPLANO, CDPLANO, DSPLANO, CDPLANOATLYS, INATIVO FROM LINHA.SERVICOSDEPARA WHERE IDPLANO = {idPlano}"
	 */
	br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara getServicosDeParaByIdPlano(long idPlano) throws SQLException;

	/**
	 * @jc:sql statement="SELECT IDPLANO, CDPLANO, DSPLANO, CDPLANOATLYS, INATIVO FROM LINHA.SERVICOSDEPARA ORDER BY CDPLANO"
	 */
	br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara[] getServicosDePara() throws SQLException;

	/**
	 * @jc:sql statement="SELECT IDPLANO, CDPLANO, DSPLANO, CDPLANOATLYS, INATIVO FROM LINHA.SERVICOSDEPARA WHERE CDPLANO = {cdPlano}"
	 */
	br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara getServicosDeParaByCdPlano(String cdPlano) throws SQLException;

	/**
	 * @jc:sql statement::
	 * INSERT INTO LINHA.SERVICOSDEPARA (
	 *        IDPLANO,
	 *        CDPLANO,
	 *        DSPLANO,
	 *        CDPLANOATLYS,
	 *        INATIVO)
	 * VALUES (LINHA.SERVICOSDEPARASQ.NEXTVAL,
	 *         {cdPlano},
	 *         {dsPlano},
	 *         {cdPlanoAtlys},
	 *         {inAtivo})
	 * ::
	 */
	void addServicosDePara(String cdPlano, String dsPlano, String cdPlanoAtlys, int inAtivo) throws SQLException;

	/**
	 * @jc:sql statement::
	 * UPDATE LINHA.SERVICOSDEPARA
	 *    SET CDPLANO = {cdPlano},
	 *        DSPLANO = {dsPlano},
	 *        CDPLANOATLYS = {cdPlanoAtlys},
	 *        INATIVO = {inAtivo}
	 *  WHERE IDPLANO = {idPlano}
	 * ::
	 */
	void updateServicosDePara(long idPlano, String cdPlano, String dsPlano, String cdPlanoAtlys, int inAtivo) throws SQLException;

	/**
	 * @jc:sql statement="DELETE FROM LINHA.SERVICOSDEPARA WHERE IDPLANO = {idPlano}"
	 */
	void deleteServicosDePara(long idPlano) throws SQLException;
}
