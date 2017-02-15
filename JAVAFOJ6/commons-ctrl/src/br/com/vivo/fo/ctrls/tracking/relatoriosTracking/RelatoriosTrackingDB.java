package br.com.vivo.fo.ctrls.tracking.relatoriosTracking;

import br.com.vivo.fo.ctrls.tracking.relatoriosTracking.dbClasses.TrackingRelatorioConsolidado;
import br.com.vivo.fo.ctrls.tracking.relatoriosTracking.dbClasses.TrackingRelatorioDetalhado;

import java.sql.SQLException;

import javax.ejb.Local;

/** 
 * 
 * @jc:connection data-source-jndi-name="jdbc.OracleDS" 
 */
@Local
public interface RelatoriosTrackingDB {

    /**
     * @jc:sql
     *   statement="{sql: query}"
     */
    public TrackingRelatorioConsolidado[] getTrackingRelatorioConsolidado(String query) throws SQLException;

    /**
     * @jc:sql
     *   statement="{sql: query}"
     */
    public TrackingRelatorioDetalhado[] getTrackingRelatorioDetalhado(String query) throws SQLException;
    
    public int getTrackingRelatorioDetalhadoCount(String query) throws SQLException;
}
