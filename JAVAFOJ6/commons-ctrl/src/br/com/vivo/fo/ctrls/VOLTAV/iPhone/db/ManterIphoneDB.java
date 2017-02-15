package br.com.vivo.fo.ctrls.VOLTAV.iPhone.db;

import java.sql.SQLException;

import javax.ejb.Local;

/** 
 * @jc:connection data-source-jndi-name="jdbc.OracleDS" 
 */
@Local
public interface ManterIphoneDB {

    static final long serialVersionUID = 1L;

    static public class LinhaRelatorioIphone implements java.io.Serializable {

		private static final long serialVersionUID = 5896660390308279107L;
		public String cdAreaRegistro;
        public String nrLinha;
        public String dtCadastro;
        public String nmPessoa;
        public String nrLinhaFixo;
        public String dsEmail;
        public String cpf;
        public String inAtivadoEnvioMail;
        public String dsEndereco;
        public String idTipoRelacionamento;

        public String getCdAreaRegistro() {
            return cdAreaRegistro;
        }

        public void setCdAreaRegistro(String cdAreaRegistro) {
            this.cdAreaRegistro = cdAreaRegistro;
        }

        public String getNrLinha() {
            return nrLinha;
        }

        public void setNrLinha(String nrLinha) {
            this.nrLinha = nrLinha;
        }

        public String getDtCadastro() {
            return dtCadastro;
        }

        public void setDtCadastro(String dtCadastro) {
            this.dtCadastro = dtCadastro;
        }

        public String getNmPessoa() {
            return nmPessoa;
        }

        public void setNmPessoa(String nmPessoa) {
            this.nmPessoa = nmPessoa;
        }

        public String getNrLinhaFixo() {
            return nrLinhaFixo;
        }

        public void setNrLinhaFixo(String nrLinhaFixo) {
            this.nrLinhaFixo = nrLinhaFixo;
        }

        public String getDsEmail() {
            return dsEmail;
        }

        public void setDsEmail(String dsEmail) {
            this.dsEmail = dsEmail;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getInAtivadoEnvioMail() {
            return inAtivadoEnvioMail;
        }

        public void setInAtivadoEnvioMail(String inAtivadoEnvioMail) {
            this.inAtivadoEnvioMail = inAtivadoEnvioMail;
        }

        public String getDsEndereco() {
            return dsEndereco;
        }

        public void setDsEndereco(String dsEndereco) {
            this.dsEndereco = dsEndereco;
        }

        public String getIdTipoRelacionamento() {
            return idTipoRelacionamento;
        }

        public void setIdTipoRelacionamento(String idTipoRelacionamento) {
            this.idTipoRelacionamento = idTipoRelacionamento;
        }
    }

    static public class LinhaIphone implements java.io.Serializable {

		private static final long serialVersionUID = -747375971019924274L;
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
     *   statement="SELECT NRLINHA linha, CDAREAREGISTRO ddd, 1 idx FROM VOL.MAILINGIPHONE"
     *   
     */
    LinhaIphone[] getLinhaIphoneGeral()
            throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT NRLINHA linha, CDAREAREGISTRO ddd, LINHA idx FROM( SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM VOL.MAILINGIPHONE ) WHERE LINHA BETWEEN {paginaInicial} AND {paginaFinal}"
     */
    LinhaIphone[] getLinhaIphoneAll(java.lang.Integer paginaInicial, java.lang.Integer paginaFinal)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT NRLINHA linha, CDAREAREGISTRO ddd, LINHA idx FROM( SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM VOL.MAILINGIPHONE where cdarearegistro = {ddd} )"
     *   max-rows="100"
     */
    LinhaIphone[] getLinhaIphoneByDdd(java.lang.Integer ddd)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT NRLINHA linha, CDAREAREGISTRO ddd, LINHA idx FROM( SELECT nrlinha, cdarearegistro, ROWNUM AS linha FROM VOL.MAILINGIPHONE where nrlinha = {linha})"
     */
    LinhaIphone[] getLinhaIphoneByLinha(java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="insert into VOL.MAILINGIPHONE (CDAREAREGISTRO, NRLINHA) values ({ddd}, {linha})"
     */
    void incluirLinha(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="delete from vol.MAILINGIPHONE where CDAREAREGISTRO = {ddd} and NRLINHA = {linha}"
     */
    void excluirLinha(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="select count(*) totalRegistros from vol.MAILINGIPHONE where (cdarearegistro = {ddd} or {ddd} = -1) and (nrlinha = {linha} or {linha} = -1)"
     */
    LinhaIphone getTotalRegistroLinhaIphone(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="select count(*) totalRegistros from linha.linhabase lb, apoio.arearegistro ag where lb.nrlinha = {linha} and ag.idarearegistro = lb.idarearegistro and ag.cdarearegistro = {ddd}"
     */
    LinhaIphone getLinhaExiste(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="select count(*) totalRegistros from vol.MAILINGIPHONE where cdarearegistro = {ddd} and nrlinha = {linha}"
     */
    LinhaIphone isPremium(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT UF.SGUF uf FROM APOIO.AREAREGISTRO AREAREGISTRO, APOIO.UF UF, CUSTOMER.UFOPERADORA UFOPERADORA WHERE 	AREAREGISTRO.CDAREAREGISTRO = {ddd} AND UFOPERADORA.IDUF = UF.IDUF AND AREAREGISTRO.IDUFOPERADORA = UFOPERADORA.IDUFOPERADORA"
     *
     */
    LinhaIphone getUfByIdPessoa(java.lang.Integer ddd, java.lang.Integer linha)
            throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     *   SELECT cdAreaRegistro                                      cdAreaRegistro,
     *          linhabase.nrLinha                                   nrLinha,
     *          TO_CHAR(dtCadastro, 'dd/mm/yyyy')                   dtCadastro,
     *          NVL(nmPessoa, ' ')                                  nmPessoa,
     *          TO_CHAR(DECODE(nrLinhaFixo, NULL, 0, nrLinhaFixo))  nrLinhaFixo,
     *          NVL(dsEmail, ' ')                                   dsEmail,
     *          NVL(dsEndereco, ' ')                                dsEndereco,
     *          NVL(cpf, ' ')                                       cpf,
     *          DECODE(inativadoenviomail, 1, 'Sim', 2, 'Não')      inAtivadoEnvioMail,
     *          DECODE(idtiporelacionamento, 1, 'U', 2, 'C')        idTipoRelacionamento
     *     FROM VOL.CAMPANHAVIP      CAMPANHAVIP,
     *          LINHA.LINHABASE      LINHABASE,
     *          APOIO.AREAREGISTRO   AREAREGISTRO,
     *          CUSTOMER.UFOPERADORA UFOPERADORA,
     *          CUSTOMER.OPERADORA   OPERADORA
     *    WHERE CAMPANHAVIP.IDLINHABASE = LINHABASE.IDLINHABASE
     *      AND LINHABASE.IDAREAREGISTRO = AREAREGISTRO.IDAREAREGISTRO
     *      AND CAMPANHAVIP.DTCADASTRO BETWEEN
     *          TO_DATE(TO_CHAR({cDtRelacionamentoInicio}) || '00:00:00','dd/mm/yyyy hh24:mi:ss') AND
     *          TO_DATE(TO_CHAR({cDtRelacionamentoFinal}) || '23:59:59', 'dd/mm/yyyy hh24:mi:ss')
     *      AND AREAREGISTRO.IDUFOPERADORA          = UFOPERADORA.IDUFOPERADORA
     *      AND UFOPERADORA.IDPESSOADEPARAOPERADORA = OPERADORA.IDPESSOADEPARAOPERADORA
     *      AND (CAMPANHAVIP.INATIVADOENVIOMAIL = {inAtivadoEnvioMailSQL} OR {inAtivadoEnvioMailSQL} = 0)   
     *      AND (LINHABASE.NRLINHA              = {iNrLinhaSQL}           OR {iNrLinhaSQL}           = 0)
     *      AND (AREAREGISTRO.CDAREAREGISTRO    = {iCdAreaRegistroSQL}    OR {iCdAreaRegistroSQL}    = 0)
     *      AND (AREAREGISTRO.IDUFOPERADORA     = {iIdUFOperadoraSQL}     OR {iIdUFOperadoraSQL}     = 0)
     *      AND (OPERADORA.IDGRUPOOPERADORA     = {iIdGrupoOperadoraSQL}  OR {iIdGrupoOperadoraSQL}  = 0)
     * ORDER BY DTCADASTRO
     * ::
     */
    LinhaRelatorioIphone[] getRelatorioIphone(String cDtRelacionamentoInicio, String cDtRelacionamentoFinal, String inAtivadoEnvioMailSQL, String iNrLinhaSQL, String iCdAreaRegistroSQL, String iIdUFOperadoraSQL, String iIdGrupoOperadoraSQL)
            throws SQLException;
}
