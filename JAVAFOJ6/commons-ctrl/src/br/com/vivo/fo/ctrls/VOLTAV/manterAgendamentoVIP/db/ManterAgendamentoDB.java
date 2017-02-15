package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.db;

import java.sql.SQLException;

import javax.ejb.Local;

/**
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface ManterAgendamentoDB {

    static final long serialVersionUID = 1L;

    static public class LinhaPremmiun implements java.io.Serializable {

        public String ddd;
        public String linha;
        public String totalRegistros;
        public String idx;
        public String uf;

        public String getUf() {
            return this.uf;
        }

        public void setUf(String paramUf) {
            this.uf = paramUf;
        }

        public void setIdx(String paramIdx) {
            this.idx = paramIdx;
        }

        public String getIdx() {
            return this.idx;
        }

        public void setTotalRegistros(String totalRegistrosParam) {
            this.totalRegistros = totalRegistrosParam;
        }

        public String getTotalRegistros() {
            return this.totalRegistros;
        }

        public void setDdd(String dddParam) {
            this.ddd = dddParam;
        }

        public String getDdd() {
            return this.ddd;
        }

        public void setLinha(String linhaParam) {
            this.linha = linhaParam;
        }

        public String getLinha() {
            return this.linha;
        }
    }

    /**
     * @jc:sql
     *   statement="SELECT NRLINHA linha, CDAREAREGISTRO ddd, LINHA idx FROM( SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM vol.linhapremium ) WHERE LINHA BETWEEN {paginaInicial} AND {paginaFinal}"
     */
    LinhaPremmiun[] getLinhaPremmiunAll(java.lang.Integer paginaInicial, java.lang.Integer paginaFinal)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT NRLINHA linha, CDAREAREGISTRO ddd, LINHA idx FROM( SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM vol.linhapremium where cdarearegistro = {ddd} ) WHERE LINHA BETWEEN {paginaInicial} AND {paginaFinal}"
     *   max-rows="100"
     */
    LinhaPremmiun[] getLinhaPremmiunByDdd(java.lang.Integer paginaInicial, java.lang.Integer paginaFinal, java.lang.Integer ddd)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT NRLINHA linha, CDAREAREGISTRO ddd, LINHA idx FROM( SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM vol.linhapremium where nrlinha = {linha}) WHERE LINHA BETWEEN {paginaInicial} AND {paginaFinal}"
     */
    LinhaPremmiun[] getLinhaPremmiunByLinha(java.lang.Integer paginaInicial, java.lang.Integer paginaFinal, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="insert into vol.linhapremium (CDAREAREGISTRO, NRLINHA) values ({ddd}, {linha})"
     */
    void incluirLinha(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="delete from vol.linhapremium where CDAREAREGISTRO = {ddd} and NRLINHA = {linha}"
     */
    void excluirLinha(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="select count(*) totalRegistros from vol.linhapremium where (cdarearegistro = {ddd} or {ddd} = -1) and (nrlinha = {linha} or {linha} = -1)"
     */
    LinhaPremmiun getTotalRegistroLinhaPremiun(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="select count(*) totalRegistros from linha.linhabase lb, apoio.arearegistro ag where lb.nrlinha = {linha} and ag.idarearegistro = lb.idarearegistro and ag.cdarearegistro = {ddd}"
     */
    LinhaPremmiun getLinhaExiste(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="select count(*) totalRegistros from vol.linhapremium where cdarearegistro = {ddd} and nrlinha = {linha}"
     */
    LinhaPremmiun isPremium(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT UF.SGUF uf FROM APOIO.AREAREGISTRO AREAREGISTRO, APOIO.UF UF, CUSTOMER.UFOPERADORA UFOPERADORA WHERE 	AREAREGISTRO.CDAREAREGISTRO = {ddd} AND UFOPERADORA.IDUF = UF.IDUF AND AREAREGISTRO.IDUFOPERADORA = UFOPERADORA.IDUFOPERADORA"
     *
     */
    LinhaPremmiun getUfByIdPessoa(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;
}
