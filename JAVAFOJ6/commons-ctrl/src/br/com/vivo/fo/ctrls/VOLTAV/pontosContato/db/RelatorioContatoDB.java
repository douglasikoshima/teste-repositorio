package br.com.vivo.fo.ctrls.VOLTAV.pontosContato.db;

import java.sql.SQLException;

import javax.ejb.Local;

import br.com.vivo.fo.constantes.ConstantesCRM;

/** 
 * Defines a new database control. 
 * 
 * The @jc:connection tag indicates which WebLogic data source will be used by 
 * this database control. Please change this to suit your needs. You can see a 
 * list of available data sources by going to the WebLogic console in a browser 
 * (typically http://localhost:7001/console) and clicking Services, JDBC, 
 * Data Sources. 
 * 
 * @jc:connection data-source-jndi-name="jdbc.OracleDS" 
 */
@Local
public interface RelatorioContatoDB {

    static final long serialVersionUID = 1L;

    static public class PontoContato implements java.io.Serializable {

        private static final long serialVersionUID = 1L;
        private String idTipoEmpresa;
        private String dsConsultorRelacionamento;
        private String nrTelefoneConsultor;
        private String dsEmailConsultor;
        private String nmTecnicoResidente; 
        private String nrTelefoneTecnico;
        private String dsEmailTecnico;
        private String nmGerenteContas; 
        private String nrTelefoneGerContas;
        private String dsEmailGerContas;
        private String dsGAM;
        private String nrTelefoneGAM;
        private String dsEmailGAM;
        private String dsGerenteSecao;
        private String nrTelefoneGerSecao;
        private String dsEmailGerSecao;
        private String dsGerenteDivisao;
        private String nrTelenoneGerDivisao;
        private String sgUF;
        private String dsEmailGerDivisao;
        
        private String dsRazaoSocialDealer;
        private String nmDealer;
        private String nrTelef1;
        private String nrTelef2; 
        private String dsEmailDealer;
        private String dsCidadeDealer;
        private String cdCnpjEmpresa;
        
        private String nmLoginUsuario;           
        private String inAtivaSolicitacaoWEB;
        private String solicitaLinha;
               
        private String nmConsultor1;
        private String nrTelConsultor1;
        private String dsEmailConsultor1;
        
        private String nmConsultor2;
        private String nrTelConsultor2;
        private String dsEmailConsultor2;     
        

         public String getNmConsultor1() {
             return nmConsultor1;
         }
         public void setNmConsultor1(String nmConsultor1) {
             this.nmConsultor1 = nmConsultor1;
         }
         public String getNrTelConsultor1() {
             return nrTelConsultor1;
         }
         public void setNrTelConsultor1(String nrTelConsultor1) {
             this.nrTelConsultor1 = nrTelConsultor1;
         }
         public String getDsEmailConsultor1() {
             return dsEmailConsultor1;
         }
         public void setDsEmailConsultor1(String dsEmailConsultor1) {
             this.dsEmailConsultor1 = dsEmailConsultor1;
         }
         public String getNmConsultor2() {
             return nmConsultor2;
         }
         public void setNmConsultor2(String nmConsultor2) {
             this.nmConsultor2 = nmConsultor2;
         }
         public String getNrTelConsultor2() {
             return nrTelConsultor2;
         }
         public void setNrTelConsultor2(String nrTelConsultor2) {
             this.nrTelConsultor2 = nrTelConsultor2;
         }
         public String getDsEmailConsultor2() {
             return dsEmailConsultor2;
         }
         public void setDsEmailConsultor2(String dsEmailConsultor2) {
             this.dsEmailConsultor2 = dsEmailConsultor2;
         }       
          
        
        public String getIdTipoEmpresa() {
             return this.idTipoEmpresa;
        }
        public void setIdTipoEmpresa(String idTipoEmpresa) {
             this.idTipoEmpresa = idTipoEmpresa;
        }
        
        public void setDsConsultorRelacionamento(String dsConsultorRelacionamento) {
             this.dsConsultorRelacionamento = dsConsultorRelacionamento;
        }
        public String getDsConsultorRelacionamento() {
             return this.dsConsultorRelacionamento;
        }

        public void setNrTelefoneConsultor(String nrTelefoneConsultor) {
             this.nrTelefoneConsultor = nrTelefoneConsultor;
        }
        
        public String getNrTelefoneConsultor() {
             return  (nrTelefoneConsultor != null) ? this.nrTelefoneConsultor : "";
        }
        
        public String getNrTelefoneConsultorFormatado() {
             return  ConstantesCRM.formatPhoneNumber(nrTelefoneConsultor);
        }
        
        public void setDsEmailConsultor(String dsEmailConsultor) {
             this.dsEmailConsultor = dsEmailConsultor;
        }
        public String getDsEmailConsultor() {
             return this.dsEmailConsultor;
        }
        
        public void setNmTecnicoResidente(String nmTecnicoResidente) {
             this.nmTecnicoResidente = nmTecnicoResidente;
        }
        public String getNmTecnicoResidente() {
             return this.nmTecnicoResidente;
        }
        
        public void setNrTelefoneTecnico(String nrTelefoneTecnico) {
             this.nrTelefoneTecnico = nrTelefoneTecnico;
        }
        
        public String getNrTelefoneTecnico() {
             return  (nrTelefoneTecnico != null) ? this.nrTelefoneTecnico : "";
        }
        
        public String getNrTelefoneTecnicoFormatado() {
             return  ConstantesCRM.formatPhoneNumber(nrTelefoneTecnico);
        }
        
        public void setDsEmailTecnico(String dsEmailTecnico) {
             this.dsEmailTecnico = dsEmailTecnico;
        }
        public String getDsEmailTecnico() {
             return this.dsEmailTecnico;
        }
        
        public void setNmGerenteContas(String nmGerenteContas) {
             this.nmGerenteContas = nmGerenteContas;
        }
        public String getNmGerenteContas() {
             return this.nmGerenteContas;
        }

        public void setNrTelefoneGerContas(String nrTelefoneGerContas) {
             this.nrTelefoneGerContas = nrTelefoneGerContas;
        }
        
        public String getNrTelefoneGerContas() {
             return  (nrTelefoneGerContas != null) ? this.nrTelefoneGerContas : "";
        }
        
        public String getNrTelefoneGerContasFormatado() {
             return  ConstantesCRM.formatPhoneNumber(getNrTelefoneGerContas());
        } 

        public void setDsEmailGerContas(String dsEmailGerContas) {
             this.dsEmailGerContas = dsEmailGerContas;
        }

        public String getDsEmailGerContas() {
             return this.dsEmailGerContas;
        }
        
        public void setDsGAM(String dsGAM) {
             this.dsGAM = dsGAM;
        }
        public String getDsGAM() {
             return this.dsGAM;
        }

        public void setNrTelefoneGAM(String nrTelefoneGAM) {
             this.nrTelefoneGAM = nrTelefoneGAM;
        }

        public String getNrTelefoneGAM() {
             return  (nrTelefoneGAM != null) ? this.nrTelefoneGAM : "";
        }
        
        public String getNrTelefoneGAMFormatado() {
             return  ConstantesCRM.formatPhoneNumber(nrTelefoneGAM);
        } 

        public void setDsEmailGAM(String dsEmailGAM) {
             this.dsEmailGAM = dsEmailGAM;
        }

        public String getDsEmailGAM() {
             return this.dsEmailGAM;
        }

        public void setDsGerenteSecao(String dsGerenteSecao) {
             this.dsGerenteSecao = dsGerenteSecao;
        }
        
        public String getDsGerenteSecao() {
             return this.dsGerenteSecao;
        }
        
        public void setNrTelefoneGerSecao(String nrTelefoneGerSecao) {
             this.nrTelefoneGerSecao = nrTelefoneGerSecao;
        }
        
        public String getNrTelefoneGerSecao() {
             return  (nrTelefoneGerSecao != null) ? this.nrTelefoneGerSecao : "";
        }
        
        public String getNrTelefoneGerSecaoFormatado() {
             return  ConstantesCRM.formatPhoneNumber(nrTelefoneGerSecao);
        } 
        
        public void setDsEmailGerSecao(String dsEmailGerSecao) {
             this.dsEmailGerSecao = dsEmailGerSecao;
        }

        public String getDsEmailGerSecao() {
             return this.dsEmailGerSecao;
        }
        
        public void setDsGerenteDivisao(String dsGerenteDivisao) {
             this.dsGerenteDivisao = dsGerenteDivisao;
        }
        public String getDsGerenteDivisao() {
             return this.dsGerenteDivisao;
        }
        
        public void setNrTelenoneGerDivisao(String nrTelenoneGerDivisao) {
             this.nrTelenoneGerDivisao = nrTelenoneGerDivisao;
        }
        
        public String getNrTelenoneGerDivisao() {
             return  (nrTelenoneGerDivisao != null) ? this.nrTelenoneGerDivisao : "";
        } 
        
        public String getNrTelenoneGerDivisaoFormatado() {
             return  ConstantesCRM.formatPhoneNumber(nrTelenoneGerDivisao);
        } 
        
        public void setSgUF(String sgUF) {
             this.sgUF = sgUF;
        }
        public String getSgUF() {
             return this.sgUF;
        }

        public void setDsEmailGerDivisao(String dsEmailGerDivisao) {
             this.dsEmailGerDivisao= dsEmailGerDivisao;
        }

        public String getDsEmailGerDivisao() {
             return this.dsEmailGerDivisao;
        }
        
        public void setDsRazaoSocialDealer(String dsRazaoSocialDealer) {
             this.dsRazaoSocialDealer = dsRazaoSocialDealer;
        }

        public String getDsRazaoSocialDealer() {
             return this.dsRazaoSocialDealer;
        }
        
        public void setNmDealer(String nmDealer) {
             this.nmDealer = nmDealer;
        }
        public String getNmDealer() {
             return this.nmDealer;
        }
        
        public void setNrTelef1(String nrTelef1) {
             this.nrTelef1 = nrTelef1;
        }

        public String getNrTelef1() {
             return this.nrTelef1;
        }

        public void setNrTelef2(String nrTelef2) {
             this.nrTelef2 = nrTelef2;
        }

        public String getNrTelef2() {
             return this.nrTelef2;
        }

        public void setDsEmailDealer(String dsEmailDealer) {
             this.dsEmailDealer = dsEmailDealer;
        }
        public String getDsEmailDealer() {
             return this.dsEmailDealer;
        }
        
        public void setDsCidadeDealer(String dsCidadeDealer) {
             this.dsCidadeDealer = dsCidadeDealer;
        }

        public String getDsCidadeDealer() {
             return this.dsCidadeDealer;
        }
        
        public void setCdCnpjEmpresa(String cdCnpjEmpresa) {
             this.cdCnpjEmpresa = cdCnpjEmpresa;
        }
        public String getCdCnpjEmpresa() {
             return this.cdCnpjEmpresa;
        }
        
        public void setNmLoginUsuario(String nmLoginUsuario) {
             this.nmLoginUsuario = nmLoginUsuario;
        }           
        public String getNmLoginUsuario() {
             return this.nmLoginUsuario;
        }
        
        public void setInAtivaSolicitacaoWEB(String inAtivaSolicitacaoWEB) {
             this.inAtivaSolicitacaoWEB = inAtivaSolicitacaoWEB;
        }

        public String getInAtivaSolicitacaoWEB() {
             return this.inAtivaSolicitacaoWEB;
        }
        
        public void setSolicitaLinha(String solicitaLinha) {
             this.solicitaLinha = solicitaLinha;
        }

        public String getSolicitaLinha() {
             return  (solicitaLinha != null) ? this.solicitaLinha : "";
        }
        
        public String getNrTelefoneDealer() {
             return (this.nrTelef1 != null && ! this.nrTelef1.equals("")) ? this.nrTelef1 : this.nrTelef2;
        }
        
        public String getNrTelefoneDealerFormatado() {
             return (this.nrTelef1 != null && ! this.nrTelef1.equals("")) ? ConstantesCRM.formatPhoneNumber(this.nrTelef1) : ConstantesCRM.formatPhoneNumber(this.nrTelef2);
        }

        public String mask(String value, String mask) {
         
             //Valida entradas
             if( value == null )                                      return "";
             else if( (mask == null) || (mask.trim().length() == 0) ) return value;
         
             //Monta elementos
             int valueIndex = 0, maskIndex = 0;
             char maskChar = ' ', resultChar = ' ';
             StringBuffer buffer = new StringBuffer();
             
             //Processa a mascara
             while ((valueIndex < value.length()) && (maskIndex < mask.length())) {
                 maskChar = mask.charAt(maskIndex++);
                 
                 if( maskChar == '#' ) resultChar = value.charAt(valueIndex++);
                 else                  resultChar = maskChar;
     
                 buffer.append(resultChar);
             }
             
             return buffer.toString();
         }
             
     }

    /**
     * @jc:sql array-max-length="all" statement::
     * SELECT  IDTIPOEMPRESA                         idTipoEmpresa,
     *         DSCONSULTORRELACIONAMENTO DsConsultorRelacionamento,
     *         NRTELEFONECONSULTOR             nrTelefoneConsultor, 
     *         DSEMAILCONSULTOR                   dsEmailConsultor, 
     *         NMTECNICORESIDENTE               nmTecnicoResidente, 
     *         NRTELEFONETECNICO                 nrTelefoneTecnico,
     *         DSEMAILTECNICO                       dsEmailTecnico,
     *         NMGERENTECONTAS                     nmGerenteContas, 
     *         NRTELEFONEGERCONTAS             nrTelefoneGerContas,
     *         DSEMAILGERCONTAS                   dsEmailGerContas,
     *         DSGAM                                         dsGAM,
     *         NRTELEFONEGAM                         nrTelefoneGAM,
     *         DSEMAILGAM                               dsEmailGAM,
     *         DSGERENTESECAO                       dsGerenteSecao,
     *         NRTELEFONEGERSECAO               nrTelefoneGerSecao,
     *         DSEMAILGERSECAO                     dsEmailGerSecao,
     *         DSGERENTEDIVISAO                   dsGerenteDivisao,
     *         NRTELEFONEGERDIVISAO           nrTelenoneGerDivisao,
     *         SGUF                                           sgUF,
     *         DSEMAILGERDIVISAO                 dsEmailGerDivisao,
     *         NMDEALER                         		  nmDealer,          
     *         DSRAZAOSOCIALDEALER             dsRazaoSocialDealer,
     *         NRTELEF1                                   nrTelef1,
     *         NRTELEF2                                   nrTelef2, 
     *         DSEMAILDEALER                         dsEmailDealer,
     *         DSCIDADEDEALER                       dsCidadeDealer,
     *         CDCNPJEMPRESA                         cdCnpjEmpresa
     * FROM    VOL.PONTOCONTATO 
     * WHERE   CDCNPJEMPRESA = {cdCnpjEmpresa} 
     * ::
     */
    PontoContato[] getRelatorioContato(String cdCnpjEmpresa) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     * SELECT 	 IDTIPOEMPRESA                          idTipoEmpresa,            
     *           CDCNPJEMPRESA                          cdCnpjEmpresa,
     *           SGUF                                   sgUF,
     *           NVL(DSCONSULTORRELACIONAMENTO, ' ')    DsConsultorRelacionamento,
     *           NVL(NRTELEFONECONSULTOR, '')           nrTelefoneConsultor,
     *           NVL(DSEMAILCONSULTOR, ' ')             dsEmailConsultor,
     *           NVL(NMTECNICORESIDENTE, ' ')           nmTecnicoResidente,
     *           NVL(NRTELEFONETECNICO, '')             nrTelefoneTecnico,
     *           NVL(DSEMAILTECNICO, ' ')               dsEmailTecnico,
     *           NVL(NMGERENTECONTAS, ' ')              nmGerenteContas,
     *           NVL(NRTELEFONEGERCONTAS, '')           nrTelefoneGerContas,
     *           NVL(DSEMAILGERCONTAS, ' ')             dsEmailGerContas    ,
     *           NVL(DSGAM, ' ')                        dsGAM,
     *           NVL(NRTELEFONEGAM, '')                 nrTelefoneGAM,
     *           NVL(DSEMAILGAM, ' ')                   dsEmailGAM,
     *           NVL(DSGERENTESECAO, ' ')               dsGerenteSecao,
     *           NVL(NRTELEFONEGERSECAO, '')            nrTelefoneGerSecao,
     *           NVL(DSEMAILGERSECAO, ' ')              dsEmailGerSecao,
     *           NVL(DSGERENTEDIVISAO, ' ')             dsGerenteDivisao,
     *           NVL(NRTELEFONEGERDIVISAO, '')          nrTelenoneGerDivisao,
     *           NVL(DSEMAILGERDIVISAO, ' ')            dsEmailGerDivisao,
     *           NVL(NMDEALER, ' ')           		   	nmDealer,
     *           NVL(DSRAZAOSOCIALDEALER, ' ')          dsRazaoSocialDealer,
     *           NVL(NRTELEF1, ' ')                     nrTelef1,
     *           NVL(NRTELEF2, ' ')                     nrTelef2,
     *           NVL(DSEMAILDEALER, ' ')                dsEmailDealer,
     *           NVL(DSCIDADEDEALER, ' ')               dsCidadeDealer,
     *           NVL(NMLOGINUSUARIO, ' ')               nmLoginUsuario,
     *           NVL(INATIVASOLICITACAOWEB, 'N')        inAtivaSolicitacaoWEB,
     *           NVL(SOLICITALINHA, '')                 solicitaLinha
     * FROM      VOL.PONTOCONTATO  
     * WHERE     CDCNPJEMPRESA = {cdCnpjEmpresa} 
     * ::
     */
    PontoContato[] getRelatorioContatoPorCnpj(String cdCnpjEmpresa) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement:: 
     * SELECT 	 IDTIPOEMPRESA                          idTipoEmpresa,            
     *           CDCNPJEMPRESA                          cdCnpjEmpresa,
     *           SGUF                                   sgUF,
     *           NVL(DSCONSULTORRELACIONAMENTO, ' ')    DsConsultorRelacionamento,
     *           NVL(NRTELEFONECONSULTOR, '')           nrTelefoneConsultor,
     *           NVL(DSEMAILCONSULTOR, ' ')             dsEmailConsultor,
     *           NVL(NMTECNICORESIDENTE, ' ')           nmTecnicoResidente,
     *           NVL(NRTELEFONETECNICO, '')             nrTelefoneTecnico,
     *           NVL(DSEMAILTECNICO, ' ')               dsEmailTecnico,
     *           NVL(NMGERENTECONTAS, ' ')              nmGerenteContas,
     *           NVL(NRTELEFONEGERCONTAS, '')           nrTelefoneGerContas,
     *           NVL(DSEMAILGERCONTAS, ' ')             dsEmailGerContas    ,
     *           NVL(DSGAM, ' ')                        dsGAM,
     *           NVL(NRTELEFONEGAM, '')                 nrTelefoneGAM,
     *           NVL(DSEMAILGAM, ' ')                   dsEmailGAM,
     *           NVL(DSGERENTESECAO, ' ')               dsGerenteSecao,
     *           NVL(NRTELEFONEGERSECAO, '')            nrTelefoneGerSecao,
     *           NVL(DSEMAILGERSECAO, ' ')              dsEmailGerSecao,
     *           NVL(DSGERENTEDIVISAO, ' ')             dsGerenteDivisao,
     *           NVL(NRTELEFONEGERDIVISAO, '')          nrTelenoneGerDivisao,
     *           NVL(DSEMAILGERDIVISAO, ' ')            dsEmailGerDivisao,
     *           NVL(NMDEALER, ' ')           		   	nmDealer,
     *           NVL(DSRAZAOSOCIALDEALER, ' ')          dsRazaoSocialDealer,
     *           NVL(NRTELEF1, ' ')                     nrTelef1,
     *           NVL(NRTELEF2, ' ')                     nrTelef2,
     *           NVL(DSEMAILDEALER, ' ')                dsEmailDealer,
     *           NVL(DSCIDADEDEALER, ' ')               dsCidadeDealer,
     *           NVL(NMLOGINUSUARIO, ' ')               nmLoginUsuario,
     *           NVL(INATIVASOLICITACAOWEB, 'N')        inAtivaSolicitacaoWEB,
     *           NVL(SOLICITALINHA, '')                 solicitaLinha
     * FROM
     *          ( 
     *          SELECT 
     *               ROWNUM AS NLIN,  
     *               IDTIPOEMPRESA,            
     *               CDCNPJEMPRESA,            
     *               SGUF,                     
     *               DSCONSULTORRELACIONAMENTO,
     *               NRTELEFONECONSULTOR,      
     *               DSEMAILCONSULTOR,         
     *               NMTECNICORESIDENTE,       
     *               NRTELEFONETECNICO,        
     *               DSEMAILTECNICO,           
     *               NMGERENTECONTAS,          
     *               NRTELEFONEGERCONTAS,      
     *               DSEMAILGERCONTAS,         
     *               DSGAM,                      
     *               NRTELEFONEGAM,            
     *               DSEMAILGAM,               
     *               DSGERENTESECAO,           
     *               NRTELEFONEGERSECAO,       
     *               DSEMAILGERSECAO,          
     *               DSGERENTEDIVISAO,         
     *               NRTELEFONEGERDIVISAO,     
     *               DSEMAILGERDIVISAO,        
     *               NMDEALER, 
     *               DSRAZAOSOCIALDEALER,      
     *               NRTELEF1,                 
     *               NRTELEF2,                 
     *               DSEMAILDEALER,            
     *               DSCIDADEDEALER,           
     *               NMLOGINUSUARIO,           
     *               INATIVASOLICITACAOWEB,    
     *               SOLICITALINHA
     *          FROM VOL.PONTOCONTATO
     *          )
     * WHERE NLIN >= {pageInicial} AND NLIN <= {pageFinal}
     * ORDER BY  CDCNPJEMPRESA
     * ::
     */
    PontoContato[] getRelatorioContatoCompleto(int pageInicial, int pageFinal) throws SQLException;
}
