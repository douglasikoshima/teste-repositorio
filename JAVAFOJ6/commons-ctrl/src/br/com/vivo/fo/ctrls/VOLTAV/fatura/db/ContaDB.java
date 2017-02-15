package br.com.vivo.fo.ctrls.VOLTAV.fatura.db;

import java.sql.SQLException;

import javax.ejb.Local;

/**
 * @jc:connection data-source-jndi-name="jdbc.OracleDSVOLE"
 */
@Local
public interface ContaDB {

    static final long serialVersionUID = 17473542901L;

    /**
     * @jc:sql
     *   statement="SELECT ID, 
     *                     NR_TELEFONE, 
     *                     FL_STATUS, 
     *                     DIA_ENVIO, 
     *                     NM_EMAIL, 
     *                     ID_OPERADORA, 
     *                     NR_CONTRATO 
     *                FROM TS_TAREFA_CLIENTE 
     *               WHERE ID_PLATAFORMA = {idPlataforma} 
     *                 AND NR_TELEFONE = {nrTelefone} 
     *                 AND FL_STATUS = {flStatus} 
     *                 AND ROWNUM = 1"
     */
    TarefaCliente consultaContaEmail(Integer idPlataforma, String nrTelefone, String flStatus) throws SQLException;

    /**
     * @jc:sql
     *   statement="INSERT INTO TS_TAREFA_CLIENTE 
     *              (ID,NR_TELEFONE,ID_PLATAFORMA,FL_STATUS,DIA_ENVIO,NR_CONTRATO,NM_EMAIL,ID_OPERADORA,DT_ULTIMAEXECUCAO) 
     *              VALUES 
     *              (SQ_TS_TAREFA_CLIENTE.NEXTVAL, {nrTelefone}, {idPlataforma}, {flStatus}, {diaEnvio}, {nrContrato}, {nmEmail}, {idOperadora}, SYSDATE)"
     */
    void insertTarefaCliente(String nrTelefone, Integer idPlataforma, String flStatus, String diaEnvio, String nrContrato, String nmEmail, Integer idOperadora) throws SQLException;

    /**
     * @jc:sql
     *   statement::
     *   UPDATE TS_TAREFA_CLIENTE
     *      SET NR_TELEFONE = {nrTelefone},
     *          ID_PLATAFORMA = {idPlataforma},
     *          FL_STATUS = {flStatus},
     *          DIA_ENVIO = {diaEnvio},
     *          NR_CONTRATO = {nrContrato},
     *          NM_EMAIL = {nmEmail},
     *          ID_OPERADORA = {idOperadora},
     *          DT_ULTIMAEXECUCAO = NULL
     *    WHERE FL_STATUS IN ('A','D')
     *      AND NR_TELEFONE = {nrTelefone}
     *   ::
     */
    void updateTarefaCliente(String nrTelefone, Integer idPlataforma, String flStatus, String diaEnvio, String nrContrato, String nmEmail, Integer idOperadora) throws SQLException;

    /**
     * @jc:sql
     *   statement="UPDATE TS_TAREFA_CLIENTE 
     *                 SET FL_STATUS = {flStatus} 
     *               WHERE ID_PLATAFORMA = {idPlataforma} 
     *                 AND NR_TELEFONE = {nrTelefone} 
     *                 AND FL_STATUS IN ('A','D')"
     */
    void desativaEnvioEmail(String nrTelefone, Integer idPlataforma, String flStatus) throws SQLException;

    /**
     * @jc:sql command-type="update"
     *   statement="UPDATE TS_TAREFA_CLIENTE 
     *                 SET NM_EMAIL = {dsEmail} 
     *               WHERE ID_PLATAFORMA = {idPlataforma} 
     *                 AND NR_TELEFONE = {nrTelefone} 
     *                 AND FL_STATUS IN ('A','D')"
     */
    void atualizaEmail(String nrTelefone, Integer idPlataforma, String dsEmail) throws SQLException;

    /**
     * @jc:sql
     *   statement::
     *     SELECT CLIENTE.ID_ROW ID,
     *            TO_CHAR (CLIENTE.dt_hist, 'DD/MM/YYYY HH24:MI:SS') DATA, 
     *            ST.SITUACAO,
     *            ST.DESCRICAO, 
     *            ST.ID STATUSID
     *       FROM (SELECT ROWNUM "ID_ROW", 
     *                    hc.*
     *               FROM TS_HISTORICO_CLIENTE HC, 
     *                    TS_TAREFA_CLIENTE TC
     *              WHERE TS_TAREFA_CLIENTE_ID = TC.ID
     *                AND TC.NR_TELEFONE = {nrTelefone}
     *                AND HC.TS_STATUS_ID NOT IN (10, 11, 12, 13)
     *            ) CLIENTE,
     *            TS_STATUS ST
     *      WHERE CLIENTE.ID_ROW BETWEEN 1 AND 10 
     *        AND TS_STATUS_ID = ST.ID
     *   ORDER BY CLIENTE.DT_HIST DESC
     *   ::
     */
    TarefaCliente[] consultaHistoricoEmail(String nrTelefone) throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT ID FROM TS_STATUS WHERE DESCRICAO = {descricao}"
     */
    TarefaCliente getStatusIDByDescricao(String descricao) throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT ID, 
     *                     NR_TELEFONE, 
     *                     FL_STATUS, 
     *                     DIA_ENVIO 
     *                FROM TS_TAREFA_CLIENTE 
     *               WHERE NM_EMAIL = {dsEmail} 
     *                 AND ID_PLATAFORMA = {idPlataforma} 
     *                 AND FL_STATUS = 'A'"
     */
    TarefaCliente getTarefaClienteByEmail(String dsEmail, Integer idPlataforma) throws SQLException;

    /**
     * @jc:sql
     *   statement="INSERT INTO TS_HISTORICO_CLIENTE VALUES (SQ_TS_HISTORICO_CLIENTE.NEXTVAL, {tarefaClienteId} , {statusId} , SYSDATE)"
     */
    void insertHistoricoCliente(Integer tarefaClienteId, Integer statusId) throws SQLException;

    /**
     * @jc:sql
     *   statement::
     *   INSERT INTO ts_historico_cliente
     *   VALUES (sq_ts_historico_cliente.NEXTVAL,
     *        (SELECT DECODE ((SELECT ROWNUM
     *                           FROM ts_tarefa_cliente
     *                          WHERE nm_email = {dsEmail}
     *                            AND id_plataforma = {idPlataforma}
     *                            AND fl_status = 'A' AND ROWNUM = 1),
     *                        1, (SELECT ID
     *                              FROM ts_tarefa_cliente
     *                             WHERE nm_email = {dsEmail}
     *                               AND id_plataforma = {idPlataforma}
     *                               AND fl_status = 'A' AND ROWNUM = 1),
     *                        (SELECT ID
     *                           FROM ts_tarefa_cliente
     *                          WHERE id_plataforma = {idPlataforma}
     *                            AND nr_telefone = {nrTelefone}
     *                            AND fl_status = 'A' AND ROWNUM = 1)
     *                       ) DEC
     *           FROM ts_tarefa_cliente
     *          WHERE ROWNUM = 1),
     *        (SELECT ID
     *           FROM ts_status
     *          WHERE descricao = {dsOperacao} ), SYSDATE)
     *   ::
     */
    void insertHistoricoClienteFull(String nrTelefone, Integer idPlataforma, String dsEmail, String dsOperacao) throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT SBSCRP_ID FROM TMP_CLIENTE WHERE ACCESS_NBR = {nrTelefone}"
     */
    TarefaCliente consultaNumeroContrato(String nrTelefone) throws SQLException;

    /**
     * @jc:sql
     *   statement::
     *   UPDATE TS_TAREFA_CLIENTE_PJ
     *      SET FL_STATUS = {flStatus}
     *    WHERE FL_STATUS IN ('A','D')
     *      AND NR_CONTRATO = {nrConta}
     *   ::
     */
    void updateFaturaEmailPJ(String nrConta, String flStatus) throws SQLException;

    /**
     * @jc:sql
     *   statement::
     *   INSERT INTO TS_TAREFA_CLIENTE_PJ
     *       (ID, NR_CONTRATO, FL_STATUS, NM_EMAIL, DT_CADASTRO )
     *   VALUES
     *      (SQ_TS_TAREFA_CLIENTE_PJ.NEXTVAL, {nrConta}, {flStatus}, {dsEmail}, {dtCadastro} )
     *   ::
     */
    void insertTarefaClientePJ(String nrConta, String flStatus, String dsEmail, java.sql.Date dtCadastro) throws SQLException;
}
