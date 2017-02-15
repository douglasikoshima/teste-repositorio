package br.com.vivo.fo.ctrls.VOLTAV.fatura;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.ServicoEmailBeanDocument.ServicoEmailBean;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.fatura.db.dbClasses.TipoComunicacao;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

@Stateless(name="FaturaFacade",mappedName="FaturaFacade")
@Local(FaturaFacade.class)
@Session(ejbName = "FaturaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FaturaFacadeImpl implements FaturaFacade {

    @EJB
    private br.com.vivo.fo.ctrls.VOLTAV.fatura.db.FaturaDB faturaDB;

    @EJB
    private br.com.vivo.fo.ctrls.VOLTAV.fatura.db.ContaDB contaDB;
    
    private final static transient Logger                  log              = new Logger("FaturaFacade");

    public boolean isHierarquia(String idUsuario, String cdConta) throws FacadeException {
        try {
            boolean isHierarquia = false;
            log.info("FaturaFacadeImpl:isClienteDadosZap(" + idUsuario + ") - [cdConta: " + cdConta + "]");

            if (ConstantesCRM.SVAZIO.equals(cdConta)) {
                throw new Exception("Conta inválida.");
            }
            isHierarquia = (faturaDB.inHierarquia(cdConta) == 1) ? true : false;
            log.info("FaturaFacadeImpl:faturaDB.isHierarquia(" + idUsuario + "): " + isHierarquia + "]");

            return isHierarquia;

        } catch (Exception ex) {
            log.error("FaturaFacadeImpl:isHierarquia(" + idUsuario + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public boolean isClienteDadosZap(String idUsuario, String cdDDD, String nrLinha) throws FacadeException {
        try {
            boolean isClienteDadosZap = false;
            log.info("FaturaFacadeImpl:isClienteDadosZap(" + idUsuario + ") - [nrTelefone: " + cdDDD + nrLinha + "]");

            if (ConstantesCRM.SVAZIO.equals(cdDDD) || cdDDD.length() != 2) {
                throw new Exception("Código de área inválido.");
            
            } else if (ConstantesCRM.SVAZIO.equals(nrLinha) || nrLinha.length() != 8 || nrLinha.length() != 9) {
                throw new Exception("Número de linha inválido.");
            }
            
            isClienteDadosZap = (faturaDB.inClienteDadosZap(cdDDD, nrLinha) == 1) ? true : false;
            log.info("FaturaFacadeImpl:faturaDB.isClienteDadosZap(" + idUsuario + "): " + isClienteDadosZap + "]");

            return isClienteDadosZap;

        } catch (Exception ex) {
            log.error("FaturaFacadeImpl:isClienteDadosZap(" + idUsuario + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public TipoComunicacao[] getListaTiposComunicacao(String idUsuario, String sgTipoComunicacao) throws FacadeException {
        try {
            log.info("FaturaFacadeImpl:getListaTiposComunicacao(" + idUsuario + ") - [sgTipoComunicacao: " + sgTipoComunicacao + "]");

            TipoComunicacao[] listaTiposComunicacao = faturaDB.getTipoComunicacaoBySgClassificacao(sgTipoComunicacao);
            return listaTiposComunicacao;

        } catch (Exception ex) {
            log.error("FaturaFacadeImpl:getListaTiposComunicacao(" + idUsuario + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ServicoEmailBean ativaEmail(String idUsuario, String nrTelefone, String dsEmail, String dataTarefa, Integer idUFOperadora, boolean isPrePago, boolean isPJ, String idContaSistemaOrigem) throws Exception {
        try {
            Integer idPlataforma = new Integer(isPrePago ? 1 : 2);

            ServicoEmailBean cadastro = ServicoEmailBean.Factory.newInstance();
            long time = System.currentTimeMillis();
            long timeMetodo = time;

            try {
                String numeroContrato = nrTelefone;
                // Número de contrato apenas para linhas pós-pagas
                if (!isPrePago) {
                    numeroContrato = idContaSistemaOrigem;
                }

                if (isPJ) {
                    contaDB.updateFaturaEmailPJ(numeroContrato, "I");
                    java.util.Date date = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    contaDB.insertTarefaClientePJ(numeroContrato, "A", dsEmail, sqlDate);

                } else {
                    NumberFormat nf = new DecimalFormat("0");
                    nf.setMinimumIntegerDigits(10);
                    long l = Long.valueOf(numeroContrato).longValue();
                    numeroContrato = nf.format(l);

                    contaDB.desativaEnvioEmail(nrTelefone, idPlataforma, "I");
                    contaDB.insertTarefaCliente(nrTelefone, idPlataforma, "A", dataTarefa, numeroContrato, dsEmail, idUFOperadora);

                    // Grava histórico
                    contaDB.insertHistoricoClienteFull(nrTelefone, idPlataforma, dsEmail, "Cadastro");
                    time = System.currentTimeMillis() - time;
                    timeMetodo = System.currentTimeMillis() - timeMetodo;
                }
                cadastro.setCadastroAtivo(true);

            } catch (Exception e) {
                log.error("FaturaFacadeImpl:ativaEmail(" + idUsuario + ") - [-|" + e.getMessage() + "]");
                cadastro.setCadastroAtivo(false);
            }
            return cadastro;

        } catch (Exception e) {
            log.error("FaturaFacadeImpl:ativaEmail(" + idUsuario + ") - [" + e.getMessage() + "]");
            throw new Exception("Erro ao invocar o servico de Ativação de Email.", e);
        }
    }

    /**
     * @common:operation
     */
    public void desativarFaturaEmailPJ(String idUsuario, String nrConta) throws Exception {
        try {
            contaDB.updateFaturaEmailPJ(nrConta, "I");

        } catch (Exception e) {
            log.error("FaturaFacadeImpl:desativarFaturaEmailPJ(" + idUsuario + ") - [" + e.getMessage() + "]");
            throw new Exception("Erro ao desativar Fatura Online para PJ.", e);
        }
    }

    /**
     * @common:operation
     */
    public ServicoEmailBean ativarSomenteAvisoDisponibilidadeEmail(String idUsuario, String nrTelefone, String dsEmail, String dataTarefa, Integer idUFOperadora, boolean isPrePago, boolean isPJ, String nrConta) throws Exception {

        try {
            Integer idPlataforma = new Integer(isPrePago ? 1 : 2);
            contaDB.desativaEnvioEmail(nrTelefone, idPlataforma, "I");

            ServicoEmailBean cadastro = ServicoEmailBean.Factory.newInstance();

            try {
                String numeroContrato = nrTelefone;
                // Número de contrato apenas para linhas pós-pagas
                if (!isPrePago) {
                    numeroContrato = nrConta;
                }
                if (isPJ) {
                    java.util.Date date = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    contaDB.updateFaturaEmailPJ(nrConta, "I");
                    contaDB.insertTarefaClientePJ(nrConta, "D", dsEmail, sqlDate);

                } else {
                    NumberFormat nf = new DecimalFormat("0");
                    nf.setMinimumIntegerDigits(10);
                    long l = Long.valueOf(numeroContrato).longValue();
                    numeroContrato = nf.format(l);
                    contaDB.insertTarefaCliente(nrTelefone, idPlataforma, "D", dataTarefa, numeroContrato, dsEmail, idUFOperadora);
                }
                cadastro.setCadastroAtivo(true);

            } catch (Exception e) {
                log.error("FaturaFacadeImpl:ativaEmail(" + idUsuario + ") - [-|" + e.getMessage() + "]");
                cadastro.setCadastroAtivo(false);
            }
            return cadastro;

        } catch (Exception e) {
            log.error("FaturaFacadeImpl:ativaEmail(" + idUsuario + ") - [" + e.getMessage() + "]");
            throw new Exception("Erro ao invocar o servico de Ativação de Email.", e);
        }
    }

    /**
     * @common:operation
     */
    public void desativarFaturaEmailPF(String idUsuario, String nrTelefone, boolean isPrePago) throws Exception {
        try {
            Integer idPlataforma = new Integer(isPrePago ? 1 : 2);
            contaDB.desativaEnvioEmail(nrTelefone, idPlataforma, "I");

        } catch (Exception e) {
            log.error("FaturaFacadeImpl:desativarFaturaEmailPF(" + idUsuario + ") - [" + e.getMessage() + "]");
            throw new Exception("Erro ao desativar Fatura Online para PF.", e);
        }
    }
}
