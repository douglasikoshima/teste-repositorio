package br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite.db;

import java.io.Serializable;
import java.sql.SQLException;

import javax.ejb.Local;

/**
 * Defines a new database control.
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface ManterTermoAceiteDB {

    static final long serialVersionUID = 1L;

    public class ItemMenuTermoAceite implements Serializable {

        private static final long serialVersionUID = 1L;
        private String idItemMenu;
        private String statusTermo;
        private String txtTermo;
        private String idUsuarioAlteracao;
        private String nmItem;
        private String dataAlteracaotTermo;

        public String getIdItemMenu() {
            return this.idItemMenu;
        }

        public void setIdItemMenu(String string) {
            this.idItemMenu = string;
        }

        public String getStatusTermo() {
            return this.statusTermo;
        }

        public void setStatusTermo(String string) {
            this.statusTermo = string;
        }

        public String getTxtTermo() {
            return this.txtTermo;
        }

        public void setTxtTermo(String string) {
            this.txtTermo = string;
        }

        public String getDataAlteracaotTermo() {
            return this.dataAlteracaotTermo;
        }

        public void setDataAlteracaotTermo(String string) {
            this.dataAlteracaotTermo = string;
        }

        public String getIdUsuarioAlteracao() {
            return this.idUsuarioAlteracao;
        }

        public void setIdUsuarioAlteracao(String string) {
            this.idUsuarioAlteracao = string;
        }

        public String getNmItem() {
            return this.nmItem;
        }

        public void setNmItem(String string) {
            this.nmItem = string;
        }
    }

    /**
     * @jc:sql array-max-length="all" statement="{sql: query}"
     */
    ItemMenuTermoAceite[] getListaServico(String query)throws SQLException;

    /**
     * @jc:sql statement="{sql: query}"
     */
    ItemMenuTermoAceite getTermoAceiteServico(String query)throws SQLException;

    /**
     * @jc:sql statement="{sql: query}" command-type="update"
     */
    void setTextoTermo(String query)throws SQLException;
}
