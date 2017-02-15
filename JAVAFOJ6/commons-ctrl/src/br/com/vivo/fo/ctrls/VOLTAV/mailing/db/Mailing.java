package br.com.vivo.fo.ctrls.VOLTAV.mailing.db;

import java.sql.SQLException;
import javax.ejb.Local;

@Local
public interface Mailing {

    static public class MailingBanner implements java.io.Serializable {

        private static final long serialVersionUID   = 8023264102356454111L;

        public String             idMailingBanner    = null;
        public String             nmMailingBanner    = null;
        public String             idAreaBanner       = null;
        public String             dtVigenciaInicio   = null;
        public String             dtVigenciaFim      = null;
        public String             dtUltimaAlteracao  = null;
        public String             idUsuarioAlteracao = null;

        public void setIdUsuarioAlteracao(String idUsuarioAlteracao) {
            this.idUsuarioAlteracao = idUsuarioAlteracao;
        }

        public String getIdUsuarioAlteracao() {
            return this.idUsuarioAlteracao;
        }

        public void setDtUltimaAlteracao(String dtUltimaAlteracao) {
            this.dtUltimaAlteracao = dtUltimaAlteracao;
        }

        public String getDtUltimaAlteracao() {
            return this.dtUltimaAlteracao;
        }

        public void setDtVigenciaFim(String dtVigenciaFim) {
            this.dtVigenciaFim = dtVigenciaFim;
        }

        public String getDtVigenciaFim() {
            return this.dtVigenciaFim;
        }

        public void setDtVigenciaInicio(String dtVigenciaInicio) {
            this.dtVigenciaInicio = dtVigenciaInicio;
        }

        public String getDtVigenciaInicio() {
            return this.dtVigenciaInicio;
        }

        public void setIdAreaBanner(String idAreaBanner) {
            this.idAreaBanner = idAreaBanner;
        }

        public String getIdAreaBanner() {
            return this.idAreaBanner;
        }

        public void setNmMailingBanner(String nmMailingBanner) {
            this.nmMailingBanner = nmMailingBanner;
        }

        public String getNmMailingBanner() {
            return this.nmMailingBanner;
        }

        public void setIdMailingBanner(String idMailingBanner) {
            this.idMailingBanner = idMailingBanner;
        }

        public String getIdMailingBanner() {
            return this.idMailingBanner;
        }
    }

    static public class MailingLinha implements java.io.Serializable {

        private static final long serialVersionUID = 1L;

        public String             idMailingBanner  = null;
        public String             ddd              = null;
        public String             linha            = null;
        public String             totalRegistros   = null;

        public void setLinha(String linha) {
            this.linha = linha;
        }

        public String getLinha() {
            return this.linha;
        }

        public void setDdd(String ddd) {
            this.ddd = ddd;
        }

        public String getDdd() {
            return this.ddd;
        }

        public void setIdMailingBanner(String idMailingBanner) {
            this.idMailingBanner = idMailingBanner;
        }

        public String getIdMailingBanner() {
            return this.idMailingBanner;
        }

        public void setTotalRegistros(String totalRegistrosParam) {
            this.totalRegistros = totalRegistrosParam;
        }

        public String getTotalRegistros() {
            return this.totalRegistros;
        }
    }

    static public class AreaBanner implements java.io.Serializable {

        public String idAreaBanner = null;
        public String dsAreaBanner = null;

        public void setIdAreaBanner(String idAreaBanner) {
            this.idAreaBanner = idAreaBanner;
        }

        public String getIdAreaBanner() {
            return this.idAreaBanner;
        }

        public void setDsAreaBanner(String dsAreaBanner) {
            this.dsAreaBanner = dsAreaBanner;
        }

        public String getDsAreaBanner() {
            return this.dsAreaBanner;
        }
    }

    static final long serialVersionUID = 1L;

    /**
     * @jc:sql
     *         statement=
     *         "select idareabanner idAreaBanner, dsareabanner, dsAreaBanner from apoio.areabanner where idareabanner >= 10 and idareabanner <= 20"
     */
    AreaBanner[] listarAreaBanner() throws SQLException;

    /**
     * @jc:sql
     *         statement=
     *         "select idmailingbanner,nmmailingbanner,idareabanner,TO_CHAR(dtvigenciainicio,'dd/mm/yyyy') dtvigenciainicio,TO_CHAR(dtvigenciafim,'dd/mm/yyyy') dtvigenciafim from vol.mailingbanner order by nmmailingbanner asc"
     */
    MailingBanner[] listarMailing() throws SQLException;

    /**
     * @jc:sql
     *         statement=
     *         "select idmailingbanner idMailingBanner,cdarearegistro ddd,nrlinha linha from vol.mailinglinha where idmailingbanner = {idMailing}"
     */
    MailingLinha[] listarLinhasPorMailing(java.lang.Integer idMailing) throws SQLException;

    /**
     * @jc:sql
     *         statement=
     *         "select idmailingbanner idMailingBanner,cdarearegistro ddd,nrlinha linha from vol.mailinglinha where idmailingbanner = {idMailing} and nrlinha = {linha}"
     */
    MailingLinha[] pesquisarPorLinha(java.lang.Integer idMailing, java.lang.Integer linha) throws SQLException;

    /**
     * @jc:sql
     *         statement=
     *         "select idmailingbanner idMailingBanner,cdarearegistro ddd,nrlinha linha from vol.mailinglinha where idmailingbanner = {idMailing} and cdarearegistro = {ddd}"
     */
    MailingLinha[] pesquisarPorDDD(java.lang.Integer idMailing, java.lang.Integer ddd) throws SQLException;

    /**
     * @jc:sql
     *         statement=
     *         "insert into vol.mailinglinha (idmailingbanner,cdarearegistro,nrlinha) values ({idMailing},{ddd},{linha})"
     */
    void incluirLinha(java.lang.Integer idMailing, java.lang.Integer ddd, java.lang.Integer linha) throws SQLException;

    /**
     * @jc:sql
     *         statement=
     *         "delete from vol.mailinglinha where nrLinha = {linha} and cdarearegistro = {ddd} and idMailingBanner = {idMailing}"
     */
    void excluirLinha(java.lang.Integer idMailing, java.lang.Integer ddd, java.lang.Integer linha) throws SQLException;

    /**
     * @jc:sql statement=
     *         "insert into vol.mailingbanner (idmailingbanner,nmmailingbanner,idareabanner,dtvigenciainicio,dtvigenciafim,dtultimaalteracao,idusuarioalteracao) values ({idMailingBanner},{nome},{idAreaBanner},TO_DATE({dataInicio},'DD/MM/YYYY'),TO_DATE({dataFim},'DD/MM/YYYY'),sysdate,{idUser})"
     */
    void incluirMailing(java.lang.String nome, java.lang.String dataInicio, java.lang.String dataFim, java.lang.Integer idAreaBanner, java.lang.Integer idMailingBanner, java.lang.Integer idUser)
            throws SQLException;

    /**
     * @jc:sql statement=
     *         "update vol.mailingbanner set dtvigenciainicio = TO_DATE({dataInicio},'DD/MM/YYYY'), dtvigenciafim = TO_DATE({dataFim},'DD/MM/YYYY') where idmailingbanner = {idMailingBanner}"
     */
    void alterarMailing(java.lang.String dataInicio, java.lang.String dataFim, java.lang.Integer idMailingBanner) throws SQLException;

    /**
     * @jc:sql
     *         statement="select vol.mailingbannersq.nextval idMailingBanner from dual"
     */
    MailingBanner getChavePrimariaMailing() throws SQLException;

    /**
     * @jc:sql
     *         statement="delete from vol.mailingbanner where idmailingbanner = {idMailingBanner}"
     */
    void excluirMailing(java.lang.Integer idMailingBanner) throws SQLException;

    /**
     * @jc:sql
     *         statement="delete from vol.mailinglinha where idmailingbanner = {idMailingBanner}"
     */
    void excluirLinhasMailing(java.lang.Integer idMailingBanner) throws SQLException;

    /**
     * @jc:sql
     *         statement=
     *         "select count(*) totalRegistros from linha.linhabase lb, apoio.arearegistro ag where lb.nrlinha = {linha} and ag.idarearegistro = lb.idarearegistro and ag.cdarearegistro = {ddd}"
     */
    MailingLinha getLinhaExiste(java.lang.Integer ddd, java.lang.Integer linha) throws SQLException;

    /**
     * @jc:sql statement=
     *         "insert into apoio.parametro (idparametro,cdparametro,dsparametro,dsvalorparametro,idusuarioalteracao,dtultimaalteracao) values (apoio.parametrosq.nextval,{cdParametro},{cdParametro},{idMailingBanner},{idUser},sysdate)"
     */
    void incluirArquivo(java.lang.String cdParametro, java.lang.String idMailingBanner, java.lang.Integer idUser) throws SQLException;
}
