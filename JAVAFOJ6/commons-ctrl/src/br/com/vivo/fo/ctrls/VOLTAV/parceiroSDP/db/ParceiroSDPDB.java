package br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db;

import java.sql.SQLException;

import javax.ejb.Local;

/**
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface ParceiroSDPDB {

    static final long serialVersionUID = 1L;

    static public class FolhaContato implements java.io.Serializable {

        private static final long serialVersionUID = 1L;
        public String idcontato;
        public String nmcontato;

        public String getIdcontato() {
            return idcontato;
        }

        public void setIdcontato(String idcontato) {
            this.idcontato = idcontato;
        }

        public String getNmcontato() {
            return nmcontato;
        }

        public void setNmcontato(String nmcontato) {
            this.nmcontato = nmcontato;
        }
    }

    static public class Parceiro implements java.io.Serializable {

        private static final long serialVersionUID = 1L;
        public String iditemmenu;
        public String nmparceiro;
        public String idcontato;
        public String dsipparceiro;
        public String dsurlparceiro;
        public String iditemmenupai;

        public String getIditemmenupai() {
            return iditemmenupai;
        }

        public void setIditemmenupai(String iditemmenupai) {
            this.iditemmenupai = iditemmenupai;
        }

        public String getIditemmenu() {
            return iditemmenu;
        }

        public void setIditemmenu(String iditemmenu) {
            this.iditemmenu = iditemmenu;
        }

        public String getNmparceiro() {
            return nmparceiro;
        }

        public void setNmparceiro(String nmparceiro) {
            this.nmparceiro = nmparceiro;
        }

        public String getIdcontato() {
            return idcontato;
        }

        public void setIdcontato(String idcontato) {
            this.idcontato = idcontato;
        }

        public String getDsipparceiro() {
            return dsipparceiro;
        }

        public void setDsipparceiro(String dsipparceiro) {
            this.dsipparceiro = dsipparceiro;
        }

        public String getDsurlparceiro() {
            return dsurlparceiro;
        }

        public void setDsurlparceiro(String dsurlparceiro) {
            this.dsurlparceiro = dsurlparceiro;
        }
    }

    /**
     * @jc:sql
     *   statement="SELECT P.IDITEMMENU, P.NMPARCEIRO, I.IDITEMMENUPAI FROM VOL.PARCEIROSDP P, ACESSO.ITEMMENUHIERARQUIA I WHERE P.IDITEMMENU = I.IDITEMMENU AND UPPER(P.NMPARCEIRO) = {nome}"
     */
    Parceiro[] getParceiros(String nome) throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT P.IDITEMMENU,P.NMPARCEIRO,P.IDCONTATO,P.DSIPPARCEIRO,P.DSURLPARCEIRO, I.IDITEMMENUPAI FROM VOL.PARCEIROSDP P, ACESSO.ITEMMENUHIERARQUIA I WHERE P.IDITEMMENU = I.IDITEMMENU AND P.IDITEMMENU = {id}"
     */
    Parceiro carregaParceiro(String id) throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT P.IDITEMMENU, P.NMPARCEIRO, I.IDITEMMENUPAI FROM VOL.PARCEIROSDP P, ACESSO.ITEMMENUHIERARQUIA I WHERE P.IDITEMMENU = I.IDITEMMENU"
     */
    Parceiro[] getTodosParceiros() throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT COUNT(1) FROM VOL.PARCEIROSDP WHERE UPPER(NMPARCEIRO) = {nome}"
     */
    int duplicidade(java.lang.String nome) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     * select c.idcontato, n.nmcontato
     * from contatoadm.contatohierarquia   h,
     *        contatoadm.contato           c,
     *        contatoadm.nomecontato       n
     * where h.idcontato = c.idcontato
     *    and n.idnomecontato = c.idnomecontato
     *    and h.idcontatopai  =  ( select idcontato from contatoadm.contato 
     *                              where NMPATH LIKE '%SDP-PARCEIROS%' 
     *                                AND IDCONTATO NOT IN (SELECT IDCONTATO FROM CONTATOADM.CONTATOFOLHA))
     *    and c.idcontato not in (select idcontato from vol.PARCEIROSDP)
     * order by n.nmcontato
     * ::
     */
    FolhaContato[] getFolhasDisponiveis() throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     * select c.idcontato, n.nmcontato
     * from contatoadm.contatohierarquia   h,
     *        contatoadm.contato           c,
     *        contatoadm.nomecontato       n
     * where h.idcontato = c.idcontato
     *    and n.idnomecontato = c.idnomecontato
     *    and h.idcontatopai  =  ( select idcontato from contatoadm.contato 
     *                              where NMPATH LIKE '%SDP-PARCEIROS%' 
     *                                AND IDCONTATO NOT IN (SELECT IDCONTATO FROM CONTATOADM.CONTATOFOLHA))
     *    and c.idcontato not in (select idcontato from vol.PARCEIROSDP where idcontato <> {id})
     * order by n.nmcontato
     * ::
     */
    FolhaContato[] getFolhasDisponiveisAlteracao(String id) throws SQLException;
}
