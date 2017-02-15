package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.PromocaoTO;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

public class PromocaoFixaForm extends ValidatorActionForm implements Serializable{

    private static final long serialVersionUID = -9011490349069373787L;
    private static final String MENSAGEM_DATA_INICIO = "Data inicial inv&aacute;lida. Favor inserir uma data vigente!";
    private static final String MENSAGEM_DATA_INICIO_FIM = "Data final inv&aacute;lida. Favor inserir uma data superior a data inicial!";
    private static final String MENSAGEM_DATA_FIM_DATA_JOJE = "Data final inv&aacute;lida. Favor inserir uma data superior a data atual!";
    private static final String FORMATO_MENSAGEM_ERRO_PARSE_DATA = "Data Inv&aacute;lida!";
    private static final String MENSAGEM_PARAMETROS_OBRIGATORIOS = "Favor preencher ao menos um par&acirc;metro para pesquisa";
    private static final String NOVO_MENSAGEM_PARAMETROS_OBRIGATORIOS = "N&atilde;o foi poss&iacute;vel Criar a Oferta Banda Larga. Todos os campos obrigat&oacute;rios devem ser preenchidos";
    private static final String EXISTE_MENSAGEM_PARAMETROS_OBRIGATORIOS = "N&atilde;o foi poss&iacute;vel alterar a Oferta Banda Larga. Todos os campos obrigat&oacute;rios devem ser preenchidos.";
    private static final String MENSAGEM_NENHUM_PARAMETRO = "Favor preencher ao menos um par&acirc;metro da pesquisa";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private String idPromocao;
    private String codigo;
    private String nome;
    private String nomeServico;
    private String status;
    private String dataVigenteInicio;
    private String dataVigenteFim;
    private String reload;

    private String id;
    private String element;
    
    private String idServico;
    private String nmServico;
    private String nmServicoDesconto;
    private String idSolicitacao;
    private String nmSolicitacao;
    private String idComposicao;
    private String pzValidadePromocao;

    private String disabled;
    private String disponibilidadePersist;
    private String disponibilidadeRemove;
    private String pendenteConfiguracao;
    
    private String idEps;
    private String cdCanalVenda;

    private String canalVendaChecked;
    private String canalVendaUnchecked;
    private String operacao;
    
    private String  canalVendaCheckedAba;
    
    public ComposicaoPromocaoTO buildItemComposicaoTO() {
        ComposicaoPromocaoTO to = new ComposicaoPromocaoTO();
        
        to.setIdPromocao(!StringUtils.isBlank(this.idPromocao) ? Integer.valueOf(this.idPromocao) : null);
        to.setIdServico(!StringUtils.isBlank(this.idServico) ? Integer.valueOf(this.idServico) : null);
        to.setNomeServico(this.nmServico);
        to.setNomeDesconto(this.nmServicoDesconto);
        to.setIdSolicitacao(!StringUtils.isBlank(this.idSolicitacao) ? Integer.valueOf(this.idSolicitacao) : null);
        to.setNomeSolicitacao(this.nmSolicitacao);
        to.setIdComposicao(!StringUtils.isBlank(this.idComposicao) ? Integer.valueOf(this.idComposicao) : null);
        to.setValidadeDesconto(!StringUtils.isBlank(this.pzValidadePromocao) ? Integer.valueOf(this.pzValidadePromocao) : null);
        return to;
    }
    
    public CanalVendaTO buildSearchCanalVendaTO() throws CatalogoPRSException {
        if ((StringUtils.isBlank(this.idEps) || !StringUtils.isNumeric(this.idEps)) 
                && StringUtils.isBlank(this.cdCanalVenda)) {
            throw new CatalogoPRSException(MENSAGEM_NENHUM_PARAMETRO);
        }
        CanalVendaTO to = new CanalVendaTO();
        if (StringUtils.isNotBlank(this.idEps)) {
            Integer idEps = Integer.valueOf(this.idEps);
            to.setEpsTO(new EpsTO(idEps));
        }
        to.setCdCanalVenda(this.cdCanalVenda);
        return to;
    }
    
    public Set<Integer> obterIdCanalVendaListParaGravar() {
        Set<Integer> idCanalVendaList = new HashSet<Integer>();
        if (!StringUtils.isBlank(this.canalVendaChecked)) {
            String[] idCanalVendaArray = this.canalVendaChecked.split("\\|");
            for (String idCanalVenda : idCanalVendaArray) {
                idCanalVendaList.add(Integer.parseInt(idCanalVenda));
            }
        }
        return idCanalVendaList;
    }        

    public Set<Integer> obterIdCanalVendaListUnchecked() {
        Set<Integer> idCanalVendaList = new HashSet<Integer>();
        if (!StringUtils.isBlank(this.canalVendaUnchecked)) {
            String[] idCanalVendaArray = this.canalVendaUnchecked.split("\\|");
            for (String idCanalVenda : idCanalVendaArray) {
                idCanalVendaList.add(Integer.parseInt(idCanalVenda));
            }
        }
        return idCanalVendaList;
    }  
    
    public PromocaoTO buildRecordableTO(PromocaoTO promocaoTO) throws CatalogoPRSException {
        if (StringUtils.isBlank(this.codigo) 
                || StringUtils.isBlank(this.nome)
                || StringUtils.isBlank(this.dataVigenteInicio)) {
            throw new CatalogoPRSException(StringUtils.isBlank(this.idPromocao) ? NOVO_MENSAGEM_PARAMETROS_OBRIGATORIOS : EXISTE_MENSAGEM_PARAMETROS_OBRIGATORIOS);
        }
        if (promocaoTO.getIdPromocao() == null) {
            promocaoTO.setCdPromocao(this.codigo);
        }
        Date hoje = DateUtils.truncate(new Date(), Calendar.DATE);
        if (StringUtils.isBlank(promocaoTO.getStatus()) || promocaoTO.getStatus().equals(PromocaoTO.NAO_INICIADO)) {
            promocaoTO.setNmPromocao(this.nome);
            promocaoTO.setDtInicio(this.stringToDate(this.dataVigenteInicio));
            if (promocaoTO.getDtInicio().compareTo(hoje) < 0) {
                throw new CatalogoPRSException(MENSAGEM_DATA_INICIO);
            }                 
        }
        if (StringUtils.isBlank(promocaoTO.getStatus()) || !promocaoTO.getStatus().equals(PromocaoTO.FINALIZADO)) {
            if (!StringUtils.isBlank(this.dataVigenteFim)) {
                promocaoTO.setDtFim(this.stringToDate(this.dataVigenteFim));
                if (promocaoTO.getDtFim().compareTo(hoje) < 0) { 
                    throw new CatalogoPRSException(MENSAGEM_DATA_FIM_DATA_JOJE);
                }
                if (promocaoTO.getDtFim().compareTo(promocaoTO.getDtInicio()) < 0) {
                    throw new CatalogoPRSException(MENSAGEM_DATA_INICIO_FIM);
                }
                if (promocaoTO.getDtFim().compareTo(hoje) < 0) { 
                    throw new CatalogoPRSException(MENSAGEM_DATA_FIM_DATA_JOJE);
                }
            } else {
                promocaoTO.setDtFim(null);
            }
        }
        return promocaoTO;
    }

    public PromocaoTO buildTO() throws CatalogoPRSException{
        if (StringUtils.isBlank(this.codigo) &&
                StringUtils.isBlank(this.dataVigenteFim) &&
                StringUtils.isBlank(this.dataVigenteInicio) &&
                StringUtils.isBlank(this.status) &&
                StringUtils.isBlank(this.nome) &&
                StringUtils.isBlank(this.nomeServico) && 
                StringUtils.isBlank(this.pendenteConfiguracao)) {
            throw new CatalogoPRSException(MENSAGEM_PARAMETROS_OBRIGATORIOS);
        }
        PromocaoTO to = new PromocaoTO();
        to.setCdPromocao(this.codigo);
        to.setNmPromocao(this.nome);
        to.setNmServico(this.nomeServico);
        to.setStatus(this.status);
        if (!StringUtils.isBlank(this.dataVigenteInicio)) {
            to.setDtInicio(this.stringToDate(this.dataVigenteInicio));
        }
        if (!StringUtils.isBlank(this.dataVigenteFim)) {
            to.setDtFim(this.stringToDate(dataVigenteFim));
        }
        if (!StringUtils.isBlank(this.pendenteConfiguracao)) {
            to.setInStatusConfiguracao(this.pendenteConfiguracao.contains("sim") ? "D" : this.pendenteConfiguracao.contains("nao") ? "C" : null);
        }
        return to;
    }
    
    private Date stringToDate(String date) throws CatalogoPRSException {
        try {
            return StringUtils.isBlank(date) ? null : sdf.parse(date);
        } catch (Exception e) {
            throw new CatalogoPRSException(FORMATO_MENSAGEM_ERRO_PARSE_DATA, e);
        }
    }
    
    public boolean isClearSearch() {
        return StringUtils.isBlank(this.codigo) 
            && StringUtils.isBlank(this.nome) 
            && StringUtils.isBlank(this.nomeServico) 
            && StringUtils.isBlank(this.status) 
            && StringUtils.isBlank(this.dataVigenteInicio) 
            && StringUtils.isBlank(this.dataVigenteFim);
    }
    
    public void clean(){
        this.codigo = null;
        this.nome = null;
        this.nomeServico = null;
        this.status = null;
        this.dataVigenteInicio = null;
        this.dataVigenteFim = null;
        this.idPromocao = null;
        this.id = null;
        this.element = null;
        this.idServico = null;
        this.nmServico = null;
        this.nmServicoDesconto = null;
        this.idSolicitacao = null;
        this.nmSolicitacao = null;
        this.idComposicao = null;
        this.pzValidadePromocao = null;
    }

    public String getElement() {
        return element;
    }
    
    public void setElement(String element) {
        this.element = element;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNomeServico() {
        return nomeServico;
    }
    
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDataVigenteFim() {
        return dataVigenteFim;
    }
    
    public void setDataVigenteFim(String dataVigenteFim) {
        this.dataVigenteFim = dataVigenteFim;
    }
    
    public String getDataVigenteInicio() {
        return dataVigenteInicio;
    }
    
    public void setDataVigenteInicio(String dataVigenteInicio) {
        this.dataVigenteInicio = dataVigenteInicio;
    }
    
    public String getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(String idPromocao) {
        this.idPromocao = idPromocao;
    }

    public String getIdComposicao() {
        return idComposicao;
    }

    public void setIdComposicao(String idComposicao) {
        this.idComposicao = idComposicao;
    }

    public String getIdServico() {
        return idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }

    public String getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(String idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getNmServico() {
        return nmServico;
    }

    public void setNmServico(String nmServico) {
        this.nmServico = nmServico;
    }

    public String getNmServicoDesconto() {
        return nmServicoDesconto;
    }

    public void setNmServicoDesconto(String nmServicoDesconto) {
        this.nmServicoDesconto = nmServicoDesconto;
    }

    public String getNmSolicitacao() {
        return nmSolicitacao;
    }

    public void setNmSolicitacao(String nmSolicitacao) {
        this.nmSolicitacao = nmSolicitacao;
    }

    public String getPzValidadePromocao() {
        return pzValidadePromocao;
    }

    public void setPzValidadePromocao(String pzValidadePromocao) {
        this.pzValidadePromocao = pzValidadePromocao;
    }

    public String getDisponibilidadePersist() {
        return disponibilidadePersist;
    }

    public void setDisponibilidadePersist(String disponibilidadePersist) {
        this.disponibilidadePersist = disponibilidadePersist;
    }

    public String getDisponibilidadeRemove() {
        return disponibilidadeRemove;
    }

    public void setDisponibilidadeRemove(String disponibilidadeRemove) {
        this.disponibilidadeRemove = disponibilidadeRemove;
    }

    public String getPendenteConfiguracao() {
        return pendenteConfiguracao;
    }

    public void setPendenteConfiguracao(String pendenteConfiguracao) {
        this.pendenteConfiguracao = pendenteConfiguracao;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getReload() {
        return reload;
    }

    public void setReload(String reload) {
        this.reload = reload;
    }
    
    public String getIdEps() {
        return idEps;
    }

    public void setIdEps(String idEps) {
        this.idEps = idEps;
    }

    public String getCdCanalVenda() {
        return cdCanalVenda;
    }

    public void setCdCanalVenda(String cdCanalVenda) {
        this.cdCanalVenda = cdCanalVenda;
    }
    
    public String getCanalVendaChecked() {
        return canalVendaChecked;
    }

    public void setCanalVendaChecked(String canalVendaChecked) {
        this.canalVendaChecked = canalVendaChecked;
    }
    
	public String getCanalVendaUnchecked() {
		return canalVendaUnchecked;
	}

	public void setCanalVendaUnchecked(String canalVendaUnchecked) {
		this.canalVendaUnchecked = canalVendaUnchecked;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getCanalVendaCheckedAba() {
		return canalVendaCheckedAba;
	}

	public void setCanalVendaCheckedAba(String canalVendaCheckedAba) {
		this.canalVendaCheckedAba = canalVendaCheckedAba;
	} 
}
