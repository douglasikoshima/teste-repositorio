package br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb.db;

import java.io.Serializable;
import java.sql.SQLException;

import javax.ejb.Local;

/**
 * Defines a new database control.
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface ManterAcessoTorpedoDB {

    static final long serialVersionUID = 1L;

    public static class ItemAcessoTorpedo implements Serializable {

        private String idAcessoTorpedo;
        private String primeiroNivel;
        private String segundoNivel;
        private String inAtivo;
        private String idMenu;
        private String idSubMenu;
        private String idUsuario;
        private String dataAlteracao;

        public String getIdAcessoTorpedo() {
            return this.idAcessoTorpedo;
        }

        public void setIdAcessoTorpedo(String string) {
            this.idAcessoTorpedo = string;
        }

        public String getPrimeiroNivel() {
            return this.primeiroNivel;
        }

        public void setPrimeiroNivel(String string) {
            this.primeiroNivel = string;
        }

        public String getSegundoNivel() {
            return this.segundoNivel;
        }

        public void setSegundoNivel(String string) {
            this.segundoNivel = string;
        }

        public String getInAtivo() {
            return this.inAtivo;
        }

        public String getDescStatus() {
            return "1".equals(this.inAtivo) ? "Disponível para visuzalização" : "Não disponível para visuzalização";
        }

        public void setInAtivo(String string) {
            this.inAtivo = string;
        }

        public String getIdMenu() {
            return this.idMenu;
        }

        public void setIdMenu(String string) {
            this.idMenu = string;
        }

        public String getIdSubMenu() {
            return this.idSubMenu;
        }

        public void setIdSubMenu(String string) {
            this.idSubMenu = string;
        }

        public String getIdUsuario() {
            return this.idUsuario;
        }

        public void setIdUsuario(String string) {
            this.idUsuario = string;
        }

        public String getDataAlteracao() {
            return this.dataAlteracao;
        }

        public void setDataAlteracao(String string) {
            this.dataAlteracao = string;
        }
    }

    /**
     * @jc:sql 
     *   statement::
     *   SELECT IDACESSOTORPEDOWEB idAcessoTorpedo,
     *          PRIMEIRONIVEL       primeiroNivel ,
     *          SEGUNDONIVEL        segundoNivel,
     *          INATIVO             inAtivo,
     *          IDITEMMENU          idMenu,
     *          IDSUBMENU           idSubMenu,
     *          IDUSUARIOALTERACAO  idUsuario,
     *          DTULTIMAALTERACAO   dataAlteracao
     *   FROM   VOL.ACESSOTORPEDOWEB
     *   ORDER BY PRIMEIRONIVEL DESC	   
     *   ::
     */
    ItemAcessoTorpedo[] consultarAcessoTorpedoWeb() throws SQLException;

    /**
     * @jc:sql 
     *   statement::
     *   UPDATE VOL.ACESSOTORPEDOWEB SET INATIVO = 1 WHERE IDACESSOTORPEDOWEB = {idAcessoTorpedo}
     *   ::
     */
    void ativarAcessoTorpedoWeb(String idAcessoTorpedo) throws SQLException;

    /**
     * @jc:sql 
     *   statement::
     *   UPDATE VOL.ACESSOTORPEDOWEB SET INATIVO = 0 WHERE IDACESSOTORPEDOWEB = {idAcessoTorpedo}
     *   ::
     */
    void desativarAcessoTorpedoWeb(String idAcessoTorpedo) throws SQLException;
}
