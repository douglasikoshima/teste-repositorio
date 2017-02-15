package br.com.vivo.fo.ctrls.VOLE.banner;

import java.sql.SQLException;

import javax.ejb.Local;

import br.com.vivo.fo.dbclasses.AcessoBannerVOLE;
import br.com.vivo.fo.dbclasses.ApoioAreaBannerVOLE;
import br.com.vivo.fo.dbclasses.ApoioTipoBannerVOLE;
import br.com.vivo.fo.dbclasses.RelacionamentoBannerVOLE;

/**
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface BannerDB {

    static final long serialVersionUID = 1515487415100L;

    /**
     * @jc:sql statement="SELECT * FROM APOIO.AREABANNERVOLE ORDER BY DSAREABANNERVOLE"
     */
    ApoioAreaBannerVOLE[] getListaAreasBanner() throws SQLException;

    /**
     * @jc:sql statement="SELECT * FROM APOIO.TIPOBANNERVOLE"
     */
    ApoioTipoBannerVOLE[] getListaTiposBanner() throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement="{sql: query}"
     */
    AcessoBannerVOLE[] getListaBanners(String query) throws Exception;

    /**
     * @jc:sql array-max-length="all" statement="{sql: query}"
     */
    RelacionamentoBannerVOLE[] getListaRelacionamentos(String query) throws Exception;

    /**
     * @jc:sql statement="{sql: query}"
     */
    int countListaBanner(String query) throws Exception;

    /**
     * @jc:sql statement::
     * SELECT IDBANNERVOLE,
     *        NMBANNERVOLE,
     *        URLBANNERVOLE,
     *        IDAREABANNERVOLE,
     *        IDTIPOBANNERVOLE,
     *        DSBANNERVOLE,
     *        IPBANNERVOLE,
     *        DTINICIAL,
     *        DTFINAL,
     *        INCONTADOR,
     *        NRCONTADOR
     *   FROM ACESSO.BANNERVOLE
     *  WHERE IDBANNERVOLE = {idBannerVOLE}
     * ::
     */
    AcessoBannerVOLE getBannerVOLE(long idBannerVOLE) throws SQLException;

    /**
     * @jc:sql statement::
     * INSERT INTO ACESSO.BANNERVOLE (
     *             IDBANNERVOLE,
     *             NMBANNERVOLE,
     *             URLBANNERVOLE,
     *             IDAREABANNERVOLE,
     *             IDTIPOBANNERVOLE,
     *             DSBANNERVOLE,
     *             IPBANNERVOLE,
     *             DTINICIAL,
     *             DTFINAL,
     *             INCONTADOR,
     *             NRCONTADOR
     *    ) VALUES (
     *             ACESSO.BANNERVOLESQ.NEXTVAL,
     *             {nmBannerVOLE},
     *             {urlBannerVOLE},
     *             {idAreaBannerVOLE},
     *             {idTipoBannerVOLE},
     *             {dsBannerVOLE},
     *             {IPBannerVOLE},
     *             TO_DATE({dtInicial}, 'DD/MM/YYYY'),
     *             TO_DATE({dtFinal}, 'DD/MM/YYYY'),
     *             {inContador},
     *             0)
     * ::
     */
    void insertBannerVOLE(String nmBannerVOLE,
                      String urlBannerVOLE,
                      long idAreaBannerVOLE,
                      long idTipoBannerVOLE,
                      String dsBannerVOLE,
                      String IPBannerVOLE,
                      String dtInicial,
                      String dtFinal,
                      int inContador) throws SQLException;

    /**
     * @jc:sql statement::
     * UPDATE ACESSO.BANNERVOLE
     *    SET NMBANNERVOLE     = {nmBannerVOLE},
     *        URLBANNERVOLE    = {urlBannerVOLE},
     *        IDAREABANNERVOLE = {idAreaBannerVOLE},
     *        IDTIPOBANNERVOLE = {idTipoBannerVOLE},
     *        DSBANNERVOLE     = {dsBannerVOLE},
     *        IPBANNERVOLE     = {IPBannerVOLE},
     *        DTINICIAL        = TO_DATE({dtInicial}, 'DD/MM/YYYY'),
     *        DTFINAL          = TO_DATE({dtFinal}, 'DD/MM/YYYY'),
     *        INCONTADOR       = {inContador}
     *  WHERE IDBANNERVOLE = {idBannerVOLE}
     * ::
     */
    void updateBannerVOLE(long idBannerVOLE,
                          String nmBannerVOLE,
                          String urlBannerVOLE,
                          long idAreaBannerVOLE,
                          long idTipoBannerVOLE,
                          String dsBannerVOLE,
                          String IPBannerVOLE,
                          String dtInicial,
                          String dtFinal,
                          int inContador) throws SQLException;

    /**
     * @jc:sql statement="{sql: query}"
     */
    void updateRelacionamentoBannerVOLE(String query) throws SQLException;

}