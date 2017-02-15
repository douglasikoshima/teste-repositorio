package br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db;

import java.sql.SQLException;

import javax.ejb.Local;

@Local
public interface QuadroAvisoDB {

    static final long serialVersionUID = 1L;

    static public class Carteira implements java.io.Serializable {

        private static final long serialVersionUID = 9093028242120195368L;
        private String iduf;
        private String idsegmentacao;
        private String idconta;

        public String getIduf() {
            return iduf;
        }

        public void setIduf(String iduf) {
            this.iduf = iduf;
        }

        public String getIdsegmentacao() {
            return idsegmentacao;
        }

        public void setIdsegmentacao(String idsegmentacao) {
            this.idsegmentacao = idsegmentacao;
        }

        public String getIdconta() {
            return idconta;
        }

        public void setIdconta(String idconta) {
            this.idconta = idconta;
        }
    }

    static public class Associado implements java.io.Serializable {

        private static final long serialVersionUID = 3624138572963202371L;
        private String iduf;
        private String idsegmentacao;

        public String getIduf() {
            return iduf;
        }

        public void setIduf(String iduf) {
            this.iduf = iduf;
        }

        public String getIdsegmentacao() {
            return idsegmentacao;
        }

        public void setIdsegmentacao(String idsegmentacao) {
            this.idsegmentacao = idsegmentacao;
        }
    }

    static public class Segmento implements java.io.Serializable {

        private static final long serialVersionUID = 2478796282494995506L;
        private String idsegmentacao;
        private String dssegmentacao;

        public String getIdsegmentacao() {
            return idsegmentacao;
        }

        public void setIdsegmentacao(String idsegmentacao) {
            this.idsegmentacao = idsegmentacao;
        }

        public String getDssegmentacao() {
            return dssegmentacao;
        }

        public void setDssegmentacao(String dssegmentacao) {
            this.dssegmentacao = dssegmentacao;
        }
    }

    static public class UF implements java.io.Serializable {

        private static final long serialVersionUID = -4690679194925401413L;
        private String iduf;
        private String nmuf;

        public String getIduf() {
            return iduf;
        }

        public void setIduf(String iduf) {
            this.iduf = iduf;
        }

        public String getNmuf() {
            return nmuf;
        }

        public void setNmuf(String nmuf) {
            this.nmuf = nmuf;
        }
    }

    /**
     * @jc:sql
     *   statement="select idsegmentacao, dssegmentacao from apoio.segmentacao order by sgsegmentacao"
     */
    public Segmento[] getSegmentos() throws SQLException;

    /**
     * @jc:sql
     *   statement="select iduf, nmuf from apoio.uf where inpreenchelista = 1"
     */
    public UF[] getUFs() throws SQLException;

    /**
     * @jc:sql
     *   statement=" insert into customer.msglog (idmensagem, cdconta) values ({idMensagem}, {cdconta}) "
     */
    public void insertMensagemLog(String cdconta, String idMensagem) throws SQLException;

    /**
     * @jc:sql
     *   statement=" insert into contatoadm.msgsegmentacao (idmensagem, idsegmentacao, dtultimaalteracao, idusuario) values ({idMensagem}, {segmentacao}, SYSDATE, {idconsultor}) "
     */
    public void insertMensagemSegmentacao(String segmentacao, String idconsultor, String idMensagem) throws SQLException;

    /**
     * @jc:sql
     *   statement=" insert into contatoadm.msguf (idmensagem, iduf, dtultimaalteracao, idusuario) values ({idMensagem}, {uf}, SYSDATE, {idconsultor}) "
     */
    public void insertMensagemUF(String uf, String idconsultor, String idMensagem) throws SQLException;

    /**
     * @jc:sql
     *   statement="update contatoadm.mensagem set dtcancelamento = sysdate where idmensagem = {id}"
     */
    public void excluirMensagem(String id) throws SQLException;

    /**
     * @jc:sql statement::
     * insert into contatoadm.mensagem (idmensagem,
     *                                  dtinicio,
     *                                  dtfim,
     *                                  cdconta,
     *                                  idconsultor,
     *                                  dtultimaalteracao,
     *                                  dsmensagem)
     *                          values ({idMensagem},
     *                                  {dtinicio},
     *                                  {dtfim},
     *                                  {cdconta},
     *                                  {idconsultor},
     *                                  SYSDATE,
     *                                  {dsmensagem})
     */
    public void insertMensagem(java.sql.Date dtinicio, java.sql.Date dtfim, String cdconta, String idconsultor, String dsmensagem, String idMensagem)
            throws SQLException;

    /**
     * @jc:sql statement::
     *   select distinct pe.iduf                   as iduf,
     *          nvl(psh.idsegmentacao,11) as idsegmentacao
     *   from customer.conta                      c,
     *        customer.pessoaconta                pc,
     *        customer.contaendereco              ce,
     *        customer.pessoaendereco             pe,
     *        customer.pessoadepara               pdp,
     *        customer.pessoasegmentacao          ps,
     *        customer.pessoasegmentacaohistorico psh
     *   where c.cdconta               = '{conta}'
     *     and pc.idconta              = c.idconta
     *     and ce.idconta              = c.idconta
     *     and ce.idpessoaendereco     = pe.idpessoaendereco
     *     and pdp.idpessoa            = pe.idpessoa
     *     and ps.idpessoadepara(+)       = pdp.idpessoadepara
     *     and psh.idpessoasegmentacao(+) = ps.idpessoasegmentacao
     */
    public Associado associadoUfSegmentacao(String conta) throws SQLException;

    /**
     * @jc:sql statement::
     *            select count(1) iduf
     *              from customer.conta           c,
     *                   customer.pessoaconta     pc,
     *                   customer.pessoaconsultor pcns,
     *                   customer.pessoadepara    pdp,
     *                   customer.pessoadocumento pd,
     *                   customer.documento       d
     *             where c.cdconta      = '{conta}'
     *               and pc.idconta     = c.idconta
     *                and pcns.idpessoa = {consultor}
     *               and pdp.idpessoa   = pd.idpessoa
     *               and pd.iddocumento = d.iddocumento
     *               and d.nrdocumento  = pcns.NRDOCUMENTO
     */
    public Associado validaAssociacao(String conta, String consultor) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     *
     *    select c.idconta, psh.idsegmentacao, pe.iduf
     *      from customer.conta                      c,
     *           customer.pessoa                     p,
     *           customer.pessoaendereco             pe,
     *           customer.pessoaconsultor            pcns,
     *           customer.contaendereco              ce,
     *           customer.pessoadepara               pdp,
     *           customer.pessoaconta                pc,
     *           customer.pessoasegmentacao          ps,
     *           customer.pessoasegmentacaohistorico psh
     *     where p.idpessoa              = pdp.idpessoa
     *       and pc.idconta              = c.idconta
     *       and pe.idpessoa             = p.idpessoa
     *       and pcns.idpessoa(+)        = pdp.idpessoa
     *       and ce.idconta              = c.idconta
     *       and ce.idpessoaendereco     = pe.idpessoaendereco
     *       and ps.idpessoadepara       = pdp.idpessoadepara
     *       and psh.idpessoasegmentacao = ps.idpessoasegmentacao
     *       and pe.iduf                 = {iduf}
     *       and p.idpessoa              = {user}
     * ::
     */
    public Carteira[] carteirasPorRegional(String iduf, String user) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     *
     *    select c.idconta, psh.idsegmentacao, pe.iduf
     *      from customer.conta                      c,
     *           customer.pessoa                     p,
     *           customer.pessoaendereco             pe,
     *           customer.pessoaconsultor            pcns,
     *           customer.contaendereco              ce,
     *           customer.pessoadepara               pdp,
     *           customer.pessoaconta                pc,
     *           customer.pessoasegmentacao          ps,
     *           customer.pessoasegmentacaohistorico psh
     *     where p.idpessoa              = pdp.idpessoa
     *       and pc.idconta              = c.idconta
     *       and pe.idpessoa             = p.idpessoa
     *       and pcns.idpessoa(+)        = pdp.idpessoa
     *       and ce.idconta              = c.idconta
     *       and ce.idpessoaendereco     = pe.idpessoaendereco
     *       and ps.idpessoadepara       = pdp.idpessoadepara
     *       and psh.idpessoasegmentacao = ps.idpessoasegmentacao
     *       and psh.idsegmentacao       = {idsegmentacao}
     *       and p.idpessoa              = {user}
     * ::
     */
    public Carteira[] carteirasPorSegmentacao(String idsegmentacao, String user) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     *
     *    select c.idconta, psh.idsegmentacao, pe.iduf
     *      from customer.conta                      c,
     *           customer.pessoa                     p,
     *           customer.pessoaendereco             pe,
     *           customer.pessoaconsultor            pcns,
     *           customer.contaendereco              ce,
     *           customer.pessoadepara               pdp,
     *           customer.pessoaconta                pc,
     *           customer.pessoasegmentacao          ps,
     *           customer.pessoasegmentacaohistorico psh
     *     where p.idpessoa              = pdp.idpessoa
     *       and pc.idconta              = c.idconta
     *       and pe.idpessoa             = p.idpessoa
     *       and pcns.idpessoa(+)        = pdp.idpessoa
     *       and ce.idconta              = c.idconta
     *       and ce.idpessoaendereco     = pe.idpessoaendereco
     *       and ps.idpessoadepara       = pdp.idpessoadepara
     *       and psh.idpessoasegmentacao = ps.idpessoasegmentacao
     *       and psh.idsegmentacao       = {idsegmentacao}
     *       and pe.iduf                 = {iduf}
     *       and p.idpessoa              = {user}
     * ::
     */
    public Carteira[] carteirasPorRegionalSegmentacao(String iduf, String idsegmentacao, String user) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     *
     *    select c.idconta, psh.idsegmentacao, pe.iduf
     *      from customer.conta                      c,
     *           customer.pessoa                     p,
     *           customer.pessoaendereco             pe,
     *           customer.pessoaconsultor            pcns,
     *           customer.contaendereco              ce,
     *           customer.pessoadepara               pdp,
     *           customer.pessoaconta                pc,
     *           customer.pessoasegmentacao          ps,
     *           customer.pessoasegmentacaohistorico psh
     *     where p.idpessoa              = pdp.idpessoa
     *       and pc.idconta              = c.idconta
     *       and pe.idpessoa             = p.idpessoa
     *       and pcns.idpessoa(+)        = pdp.idpessoa
     *       and ce.idconta              = c.idconta
     *       and ce.idpessoaendereco     = pe.idpessoaendereco
     *       and ps.idpessoadepara       = pdp.idpessoadepara
     *       and psh.idpessoasegmentacao = ps.idpessoasegmentacao
     *       and p.idpessoa              = {user}
     * ::
     */
    public Carteira[] carteirasTodas(String user) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     *       select c.cdconta idconta
     *         from customer.conta           c,
     *              customer.pessoa          p,
     *              customer.pessoaconsultor pcns,
     *              customer.pessoadepara    pdp,
     *              customer.pessoaconta     pc,
     *              customer.documento       doc,
     *              customer.pessoadocumento pdoc
     *        where p.idpessoa              = pdp.idpessoa
     *          and pc.idconta              = c.idconta
     *          and pc.idpessoadepara       = pdp.idpessoadepara
     *          and pc.idtiporelacionamento = 2
     *          and doc.iddocumento         = pdoc.iddocumento
     *          and pdoc.idpessoa           = p.idpessoa
     *          and doc.nrdocumento         = pcns.nrdocumento
     *          and pcns.idpessoa           = {user}
     * ::
     */
    public Carteira[] carteirasGeral(String user) throws SQLException;
}
