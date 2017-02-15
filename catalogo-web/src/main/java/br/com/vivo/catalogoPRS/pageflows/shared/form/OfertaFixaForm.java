package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.OfertafixaComplementarTO;
import com.indracompany.catalogo.to.OfertafixaTO;
import com.indracompany.catalogo.to.ServicoOfertafixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.UfTO;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

public class OfertaFixaForm  extends ValidatorActionForm  implements java.io.Serializable {

    private static final long serialVersionUID = 2094401727725592368L;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static final String VISUALIZAR = "visualizar";
    private static final String MENSAGEM_ERRO_DATA = "Data inv&aacute;lida!";
    private static final String MENSAGEM_NENHUM_PARAMETRO = "Favor preencher ao menos um par&acirc;metro da pesquisa";
    private static final String MENSAGEM_PARAMETROS_OBRIGATORIOS = "Favor preencher os par&acirc;metros obrigat&oacute;rios";


    private String idOfertafixa;
    private String cdOferta;
    private String cdOfertafixa;
    private String nmOferta;
    private String nmOfertafixa;
    private String idServicoLinha;
    private String idSolicitacaoComercialNormal;
    private String idSolicitacaoComercialPreCadastro;
    private String idServicoPlano;
    private String idSolicitacaoComercialPlano;
    private String status;
    private String dataVigenteInicio;
    private String dataVigenteFim;
    private String tipoOfertaChecked;
    private List<String> tipoOfertaList;
    private String pendenteConfiguracao;
    private String disabled; 
    
    private String psServivoLinha;
    private String opSolicitacaoComercialPreCadastro;
    private String opSolicitacaoComercialNormal;
    private String opSolicitacaoComercialPlano;
    private String psServicoPlano;

    private String id;
    private String element;
    private String neo;
    private String reload;

    private String idTipoServico;
    private String psServico;
    private String idServicoOfertaComplementar;

    private String solicitacaoComercialChecked;

    private String idOfertafixaComplementar;

    private String canalVendaChecked; 
    private String areaRegistroChecked;
    private String localidadeChecked;

    private String idUf;
    private String idAreaRegistro;
    private String idCidade;

    private String idAbaSelecionada;
    private String idAbaAtual;
    private String operacao;
    private String idSolicitacaoComercial;        
    
    private String carregar;
    private String idEps;
    private String cdCanalVenda;
    private String cdAreaRegistro;
    
    private String tipoOfertaCheckedAba;
    private String canalVendaCheckedAba;
    private String areaRegistroCheckedAba;
    private String cdOfertaNewAba;
    private String nmOfertaNewAba;
    private String idServicoLinhaNewAba;
    private String idSolicitacaoComercialNormalNewAba;
    private String idSolicitacaoComercialPreCadastroNewAba;
    private String idServicoPlanoNewAba;
    private String idSolicitacaoComercialPlanoNewAba;
    private String dataVigenteInicioNewAba;
    private String dataVigenteFimNewAba;
    private String disponibilidadeAlterada;
    private String complementarAlterada;
    
    private String tipoOfertaLinhacobre;
    private String tipoOfertaLinhafibra;
    private String tipoOfertaSolocobre;
    private String tipoOfertaSolofibra;
    private String tipoOfertaPortabilidade;
    private String tipoOfertaFwt;
    private String tipoOfertaBasePontual;
    private String tipoOfertaInadimplente;
    private String tipoOfertaFatb;
    
    private String psServicoNew;
    
    private String force;
    
    public String getStatus() {
        return status;
    }

    public AreaRegistroTO buildSearchAreaRegistroTO() throws CatalogoPRSException {
        if ((StringUtils.isBlank(this.idUf) || !StringUtils.isNumeric(this.idUf)) 
                && StringUtils.isBlank(this.cdAreaRegistro)) {
            throw new CatalogoPRSException(MENSAGEM_NENHUM_PARAMETRO);
        }
        AreaRegistroTO to = new AreaRegistroTO();
        if (StringUtils.isNotBlank(this.idUf)) {
            Long idUf = Long.valueOf(this.idUf);
            to.setUfTO(new UfTO(idUf));
        }
        to.setCodigoArea(this.cdAreaRegistro);
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

    public List<Long> obterIdLocalidadeListParaGravar() {
        List<Long> idLocalidadeList = new ArrayList<Long>();
        if (!StringUtils.isBlank(this.localidadeChecked)) {
            String[] idLocalidadeArray = this.localidadeChecked.split("\\|");
            for (String idLocalidade : idLocalidadeArray) {
                idLocalidadeList.add(Long.valueOf(idLocalidade));
            }
        }
        return idLocalidadeList;
    }

    public List<Integer> obterIdAreaRegistroListParaGravar() {
        List<Integer> idAreaRegistroList = new ArrayList<Integer>();
        if (!StringUtils.isBlank(this.areaRegistroChecked)) {
            String[] idAreaRegistroArray = this.areaRegistroChecked.split("\\|");
            for (String idAreaRegistro : idAreaRegistroArray) {
                idAreaRegistroList.add(Integer.valueOf(idAreaRegistro));
            }
        }
        return idAreaRegistroList;
    }

    public List<Long> obterIdCanalVendaListParaGravar() {
        List<Long> idCanalVendaList = new ArrayList<Long>();
        if (!StringUtils.isBlank(this.canalVendaChecked)) {
            String[] idCanalVendaArray = this.canalVendaChecked.split("\\|");
            for (String idCanalVenda : idCanalVendaArray) {
                idCanalVendaList.add(Long.valueOf(idCanalVenda));
            }
        }
        return idCanalVendaList;
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

    public List<String> getTipoOfertaList() {
        return tipoOfertaList;
    }

    public void setTipoOfertaList(List<String> tipoOfertaList) {
        this.tipoOfertaList = tipoOfertaList;
    }

    public String getTipoOfertaChecked() {
        return tipoOfertaChecked;
    }

    public void setTipoOfertaChecked(String tipoOfertaChecked) {
        this.tipoOfertaChecked = tipoOfertaChecked;
        this.tipoOfertaList = new ArrayList<String>(Arrays.asList(this.tipoOfertaChecked.split("\\|")));
    }

    public String getCdOferta() {
        return cdOferta;
    }

    public void setCdOferta(String cdOferta) {
        this.cdOferta = cdOferta;
    }

    public String getNmOferta() {
        return nmOferta;
    }

    public void setNmOferta(String nmOferta) {
        this.nmOferta = nmOferta;
    }

    public String getIdServicoLinha() {
        return idServicoLinha;
    }

    public void setIdServicoLinha(String idServicoLinha) {
        this.idServicoLinha = idServicoLinha;
    }

    public String getIdSolicitacaoComercialNormal() {
        return idSolicitacaoComercialNormal;
    }

    public void setIdSolicitacaoComercialNormal(String idSolicitacaoComercialNormal) {
        this.idSolicitacaoComercialNormal = idSolicitacaoComercialNormal;
    }

    public String getIdSolicitacaoComercialPreCadastro() {
        return idSolicitacaoComercialPreCadastro;
    }

    public void setIdSolicitacaoComercialPreCadastro(String idSolicitacaoComercialPreCadastro) {
        this.idSolicitacaoComercialPreCadastro = idSolicitacaoComercialPreCadastro;
    }

    public String getIdServicoPlano() {
        return idServicoPlano;
    }

    public void setIdServicoPlano(String idServicoPlano) {
        this.idServicoPlano = idServicoPlano;
    }

    public String getIdSolicitacaoComercialPlano() {
        return idSolicitacaoComercialPlano;
    }

    public void setIdSolicitacaoComercialPlano(String idSolicitacaoComercialPlano) {
        this.idSolicitacaoComercialPlano = idSolicitacaoComercialPlano;
    }

    public OfertafixaTO buildRecordableTO(OfertafixaTO ofertafixaTO) throws CatalogoPRSException {         
        if (!StringUtils.equals(this.operacao, VISUALIZAR) && 
                (StringUtils.isBlank(this.cdOferta)
                || StringUtils.isBlank(this.nmOferta)
                || StringUtils.isBlank(this.idServicoLinha)
                || StringUtils.isBlank(this.idSolicitacaoComercialNormal)
                || StringUtils.isBlank(this.idServicoPlano)
                || StringUtils.isBlank(this.idSolicitacaoComercialPlano)
                || StringUtils.isBlank(this.dataVigenteInicio)
                || StringUtils.isBlank(this.psServivoLinha)
                || StringUtils.isBlank(this.opSolicitacaoComercialNormal)
                || StringUtils.isBlank(this.opSolicitacaoComercialPlano)
                || StringUtils.isBlank(this.psServicoPlano))) {
            throw new CatalogoPRSException(MENSAGEM_PARAMETROS_OBRIGATORIOS);
        }
        
        if (StringUtils.isBlank(ofertafixaTO.getStatus()) || ofertafixaTO.getStatus().equals(OfertafixaTO.NAO_INICIADO) || ofertafixaTO.isPendenteConfiguracao()) {
            Date hoje = DateUtils.truncate(new Date(), Calendar.DATE);
            
            if (StringUtils.isBlank(ofertafixaTO.getDtInicialDisabled())) {
                ofertafixaTO.setDtInicial(this.stringToDate(this.dataVigenteInicio));
                if (ofertafixaTO.getDtInicial().compareTo(hoje) < 0) {
                    throw new CatalogoPRSException(MENSAGEM_ERRO_DATA);
                }
            }
        	if (StringUtils.isNotBlank(this.idServicoLinha) && StringUtils.isNumeric(this.idServicoLinha)) {
        		Integer idServicoLinha = Integer.valueOf(this.idServicoLinha);
        		if (ofertafixaTO.getServicoOfertaFixaTOLinha() == null 
        				|| !idServicoLinha.equals(ofertafixaTO.getServicoOfertaFixaTOLinha().getId())
        				|| !(this.tipoOfertaList.contains("fwt") ? "S" : "N").equals(ofertafixaTO.getInFWT())) {
        			ofertafixaTO.setOfertafixaComplementarTOList(new ArrayList<OfertafixaComplementarTO>());
        		}
				ofertafixaTO.setServicoOfertaFixaTOLinha(new ServicoOfertafixaTO(idServicoLinha));
        	}
        	if (StringUtils.isNotBlank(this.idServicoPlano) && StringUtils.isNumeric(this.idServicoPlano)) {
        		ofertafixaTO.setServicoOfertaFixaTOPlano(new ServicoOfertafixaTO(Integer.valueOf(this.idServicoPlano)));
        	}
            if (StringUtils.isNotBlank(this.idSolicitacaoComercialNormal) && StringUtils.isNumeric(this.idSolicitacaoComercialNormal)) {
                ofertafixaTO.setSolicitacaoComercialFixaTOLinha(new SolicitacaoComercialFixaTO(Long.valueOf(this.idSolicitacaoComercialNormal)));
            }
            if (StringUtils.isNotBlank(this.idSolicitacaoComercialPlano) && StringUtils.isNumeric(this.idSolicitacaoComercialPlano)) {
                ofertafixaTO.setSolicitacaoComercialFixaTOPlano(new SolicitacaoComercialFixaTO(Long.valueOf(this.idSolicitacaoComercialPlano)));
            }
            if (StringUtils.isNotBlank(this.idSolicitacaoComercialPreCadastro) && StringUtils.isNumeric(this.idSolicitacaoComercialPreCadastro)) {
                ofertafixaTO.setSolicitacaoComercialFixaTOPreCad(new SolicitacaoComercialFixaTO(Long.valueOf(this.idSolicitacaoComercialPreCadastro)));
            }
            if (StringUtils.isNotBlank(this.psServivoLinha) && StringUtils.isNumeric(this.psServivoLinha)) {                	
            	ofertafixaTO.setPsServivoLinha(this.psServivoLinha);                	
            }
            if (StringUtils.isNotBlank(this.opSolicitacaoComercialNormal) && StringUtils.isNumeric(this.opSolicitacaoComercialNormal) ) {                
            	ofertafixaTO.setOpSolicitacaoComercialNormal(this.opSolicitacaoComercialNormal);                	                
            }
            if (StringUtils.isNotBlank(this.opSolicitacaoComercialPlano) && StringUtils.isNumeric(this.opSolicitacaoComercialPlano)) {
            	ofertafixaTO.setOpSolicitacaoComercialPlano(this.opSolicitacaoComercialPlano);
            }
            if (StringUtils.isNotBlank(this.psServicoPlano) && StringUtils.isNumeric(this.psServicoPlano)) {
            	ofertafixaTO.setPsServicoPlano(this.psServicoPlano);
            }
            if (StringUtils.isNotBlank(this.opSolicitacaoComercialPreCadastro) && StringUtils.isNumeric(this.opSolicitacaoComercialPreCadastro)) {
            	ofertafixaTO.setOpSolicitacaoComercialPreCadastro(this.opSolicitacaoComercialPreCadastro);
            }
            
            ofertafixaTO.setInConvergenteCobre(this.tipoOfertaList.contains("linhacobre") ? "S" : "N");
            ofertafixaTO.setInConvergenteFibra(this.tipoOfertaList.contains("linhafibra") ? "S" : "N");
            ofertafixaTO.setInFWT(this.tipoOfertaList.contains("fwt") ? "S" : "N");
            ofertafixaTO.setInPortab(this.tipoOfertaList.contains("portabilidade") ? "S" : "N");
            ofertafixaTO.setInSpeedySoloCobre(this.tipoOfertaList.contains("solocobre") ? "S" : "N");
            ofertafixaTO.setInSpeedySoloFibra(this.tipoOfertaList.contains("solofibra") ? "S" : "N");
            ofertafixaTO.setInBasePontual(this.tipoOfertaList.contains("basepontual") ? "S" : "N");
            ofertafixaTO.setInOfertaClienteInadimplente(this.tipoOfertaList.contains("inadimplente") ? "S" : "N");
            ofertafixaTO.setInFATB(this.tipoOfertaList.contains("fatb") ? "S" : "N");
            if (StringUtils.isBlank(ofertafixaTO.getDsOfertafixaDisabled())) {
            	ofertafixaTO.setCdOfertafixa(this.cdOferta);
                ofertafixaTO.setDsOfertafixa(this.nmOferta);
            }
        }
       
        if (StringUtils.isBlank(ofertafixaTO.getStatus()) || !ofertafixaTO.getStatus().equals(OfertafixaTO.FINALIZADO)) {
            if (StringUtils.isNotBlank(this.dataVigenteFim)) {
                ofertafixaTO.setDtFinal(this.stringToDate(this.dataVigenteFim));
                this.validarPeriodo(ofertafixaTO.getDtInicial(), ofertafixaTO.getDtFinal());
            } else {
                ofertafixaTO.setDtFinal(null);
            }
        }
        return ofertafixaTO;
    }
    
    public OfertafixaTO buildTOSearch() throws CatalogoPRSException {
        if (StringUtils.isBlank(this.cdOferta) 
                && StringUtils.isBlank(this.nmOferta)
                && StringUtils.isBlank(this.psServivoLinha) 
                && StringUtils.isBlank(this.idServicoLinha)
                && StringUtils.isBlank(this.opSolicitacaoComercialNormal) 
                && StringUtils.isBlank(this.idSolicitacaoComercialNormal) 
                && StringUtils.isBlank(this.opSolicitacaoComercialPreCadastro) 
                && StringUtils.isBlank(this.idSolicitacaoComercialPreCadastro) 
                && StringUtils.isBlank(this.psServicoPlano) 
                && StringUtils.isBlank(this.idServicoPlano)
                && StringUtils.isBlank(this.opSolicitacaoComercialPlano) 
                && StringUtils.isBlank(this.idSolicitacaoComercialPlano) 
                && StringUtils.isBlank(this.status) 
                && StringUtils.isBlank(this.dataVigenteInicio) 
                && StringUtils.isBlank(this.dataVigenteFim) 
                && StringUtils.isBlank(this.tipoOfertaChecked)
                && StringUtils.isBlank(this.pendenteConfiguracao)) {
            throw new CatalogoPRSException(MENSAGEM_NENHUM_PARAMETRO);
        }

        OfertafixaTO to = new OfertafixaTO();
        to.setCdOfertafixa(this.cdOferta);
        to.setDsOfertafixa(this.nmOferta);
        to.setDtInicial(this.stringToDate(this.dataVigenteInicio));
        to.setDtFinal(this.stringToDate(this.dataVigenteFim));
        if (to.getDtInicial() != null && to.getDtFinal() != null) {
            this.validarPeriodo(to.getDtInicial(), to.getDtFinal());
        }
        to.setStatus(this.status);
        to.setInConvergenteCobre(this.tipoOfertaList.contains("linhacobre") ? "S" : null);
        to.setInConvergenteFibra(this.tipoOfertaList.contains("linhafibra") ? "S" : null);
        to.setInFWT(this.tipoOfertaList.contains("fwt") ? "S" : null);
        to.setInPortab(this.tipoOfertaList.contains("portabilidade") ? "S" : null);
        to.setInSpeedySoloCobre(this.tipoOfertaList.contains("solocobre") ? "S" : null);
        to.setInSpeedySoloFibra(this.tipoOfertaList.contains("solofibra") ? "S" : null);
        to.setInBasePontual(this.tipoOfertaList.contains("basepontual") ? "S" : null);
        to.setInOfertaClienteInadimplente(this.tipoOfertaList.contains("inadimplente") ? "S" : null);
        to.setInFATB(this.tipoOfertaList.contains("fatb") ? "S" : null);
        to.setInStatusConfiguracao(this.pendenteConfiguracao.contains("sim") ? "D" : this.pendenteConfiguracao.contains("nao") ? "C" : null);
        if (!StringUtils.isBlank(this.idServicoLinha) && StringUtils.isNumeric(this.idServicoLinha)) {
            to.setServicoOfertaFixaTOLinha(new ServicoOfertafixaTO(Integer.valueOf(this.idServicoLinha)));
        }
        if (!StringUtils.isBlank(this.idServicoPlano) && StringUtils.isNumeric(this.idServicoPlano)) {
            to.setServicoOfertaFixaTOPlano(new ServicoOfertafixaTO(Integer.valueOf(this.idServicoPlano)));
        }
        if (!StringUtils.isBlank(this.idSolicitacaoComercialPlano) && StringUtils.isNumeric(this.idSolicitacaoComercialPlano)) {
            to.setSolicitacaoComercialFixaTOPlano(new SolicitacaoComercialFixaTO(Long.valueOf(this.idSolicitacaoComercialPlano)));
        }
        if (!StringUtils.isBlank(this.idSolicitacaoComercialNormal) && StringUtils.isNumeric(this.idSolicitacaoComercialNormal)) {
            to.setSolicitacaoComercialFixaTOLinha(new SolicitacaoComercialFixaTO(Long.valueOf(this.idSolicitacaoComercialNormal)));
        }
        if (!StringUtils.isBlank(this.idSolicitacaoComercialPreCadastro) && StringUtils.isNumeric(this.idSolicitacaoComercialPreCadastro)) {
            to.setSolicitacaoComercialFixaTOLinha(new SolicitacaoComercialFixaTO(Long.valueOf(this.idSolicitacaoComercialPreCadastro)));
        }
        if (!StringUtils.isBlank(this.psServivoLinha)) {
            to.setPsServivoLinha(this.psServivoLinha);
        }
        if (!StringUtils.isBlank(this.psServicoPlano)) {
            to.setPsServicoPlano(this.psServicoPlano);
        }
        if (!StringUtils.isBlank(this.opSolicitacaoComercialPlano)) {
            to.setOpSolicitacaoComercialPlano(this.opSolicitacaoComercialPlano);
        }
        if (!StringUtils.isBlank(this.opSolicitacaoComercialNormal)) {
            to.setOpSolicitacaoComercialNormal(this.opSolicitacaoComercialNormal);
        }
        if (!StringUtils.isBlank(this.opSolicitacaoComercialPreCadastro)) {
            to.setOpSolicitacaoComercialPreCadastro(this.opSolicitacaoComercialPreCadastro);
        }            

        return to;
    }

    private void validarPeriodo(Date dtInicial, Date dtFinal) throws CatalogoPRSException {
        if (dtInicial.after(dtFinal)) {
            throw new CatalogoPRSException(MENSAGEM_ERRO_DATA);
        }
    }

    private Date stringToDate(String date) throws CatalogoPRSException {
        try {
            return StringUtils.isBlank(date) ? null : sdf.parse(date);
        } catch (Exception e) {
            throw new CatalogoPRSException(MENSAGEM_ERRO_DATA, e);
        }
    }

    @Override
    public String toString() {
        return String.format("cdOferta=%s nmOferta=%s idServicoLinha=%s idSolicitacaoComercialNormal=%s idSolicitacaoComercialPreCadastro=%s idServicoPlano=%s idSolicitacaoComercialPlano=%s status=%s dataVigenteInicio=%s dataVigenteFim=%s tipoOfertaChecked=%s id=%s element", cdOferta, nmOferta, idServicoLinha, idSolicitacaoComercialNormal, idSolicitacaoComercialPreCadastro, idServicoPlano, idSolicitacaoComercialPlano, status, dataVigenteInicio, dataVigenteFim, tipoOfertaChecked, id, element);
    }

    public String getNeo() {
        return neo;
    }

    public void setNeo(String neo) {
        this.neo = neo;
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

    public String getIdServicoOfertaComplementar() {
        return idServicoOfertaComplementar;
    }
            
    public String getPsServico() {
		return psServico;
	}

	public void setPsServico(String psServico) {
		this.psServico = psServico;
	}

	public String getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdServicoOfertaComplementar(String idServicoOfertaComplementar) {
        this.idServicoOfertaComplementar = idServicoOfertaComplementar;
    }

    public void setIdTipoServico(String idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    public String getSolicitacaoComercialChecked() {
        return solicitacaoComercialChecked;
    }

    public void setSolicitacaoComercialChecked(String solicitacaoComercialChecked) {
        this.solicitacaoComercialChecked = solicitacaoComercialChecked;
    }

    public String getIdOfertafixaComplementar() {
        return idOfertafixaComplementar;
    }

    public void setIdOfertafixaComplementar(String idOfertafixaComplementar) {
        this.idOfertafixaComplementar = idOfertafixaComplementar;
    }

    public String getCanalVendaChecked() {
        return canalVendaChecked;
    }

    public void setCanalVendaChecked(String canalVendaChecked) {
        this.canalVendaChecked = canalVendaChecked;
    }

    public String getAreaRegistroChecked() {
        return areaRegistroChecked;
    }

    public void setAreaRegistroChecked(String areaRegistroChecked) {
        this.areaRegistroChecked = areaRegistroChecked;
    }

    public String getIdUf() {
        return idUf;
    }

    public void setIdUf(String idUf) {
        this.idUf = idUf;
    }

    public String getIdAreaRegistro() {
        return idAreaRegistro;
    }

    public void setIdAreaRegistro(String idAreaRegistro) {
        this.idAreaRegistro = idAreaRegistro;
    }

    public String getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(String idCidade) {
        this.idCidade = idCidade;
    }

    public String getLocalidadeChecked() {
        return localidadeChecked;
    }

    public void setLocalidadeChecked(String localidadeChecked) {
        this.localidadeChecked = localidadeChecked;
    }

    public String getPendenteConfiguracao() {
        return pendenteConfiguracao;
    }

    public void setPendenteConfiguracao(String pendenteConfiguracao) {
        this.pendenteConfiguracao = pendenteConfiguracao;
    }

	public String getIdOfertafixa() {
		return idOfertafixa;
	}

	public void setIdOfertafixa(String idOfertafixa) {
		this.idOfertafixa = idOfertafixa;
	}

	public String getIdAbaSelecionada() {
		return idAbaSelecionada;
	}

	public void setIdAbaSelecionada(String idAbaSelecionada) {
		this.idAbaSelecionada = idAbaSelecionada;
	}
			
	public String getIdAbaAtual() {
		return idAbaAtual;
	}

	public void setIdAbaAtual(String idAbaAtual) {
		this.idAbaAtual = idAbaAtual;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getNmOfertafixa() {
		return nmOfertafixa;
	}

	public void setNmOfertafixa(String nmOfertafixa) {
		this.nmOfertafixa = nmOfertafixa;
	}

	public String getCdOfertafixa() {
		return cdOfertafixa;
	}

	public void setCdOfertafixa(String cdOfertafixa) {
		this.cdOfertafixa = cdOfertafixa;
	}

	public String getIdSolicitacaoComercial() {
		return idSolicitacaoComercial;
	}

	public void setIdSolicitacaoComercial(String idSolicitacaoComercial) {
		this.idSolicitacaoComercial = idSolicitacaoComercial;
	}

    public String getCarregar() {
        return carregar;
    }

    public void setCarregar(String carregar) {
        this.carregar = carregar;
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

    public String getCdAreaRegistro() {
        return cdAreaRegistro;
    }

    public void setCdAreaRegistro(String cdAreaRegistro) {
        this.cdAreaRegistro = cdAreaRegistro;
    }

	public String getForce() {
		return force;
	}

	public void setForce(String force) {
		this.force = force;
	}

	public String getOpSolicitacaoComercialNormal() {
		return opSolicitacaoComercialNormal;
	}

	public void setOpSolicitacaoComercialNormal(String opSolicitacaoComercialNormal) {
		this.opSolicitacaoComercialNormal = opSolicitacaoComercialNormal;
	}

	public String getOpSolicitacaoComercialPlano() {
		return opSolicitacaoComercialPlano;
	}

	public void setOpSolicitacaoComercialPlano(String opSolicitacaoComercialPlano) {
		this.opSolicitacaoComercialPlano = opSolicitacaoComercialPlano;
	}

	public String getOpSolicitacaoComercialPreCadastro() {
		return opSolicitacaoComercialPreCadastro;
	}

	public void setOpSolicitacaoComercialPreCadastro(
			String opSolicitacaoComercialPreCadastro) {
		this.opSolicitacaoComercialPreCadastro = opSolicitacaoComercialPreCadastro;
	}

	public String getPsServicoPlano() {
		return psServicoPlano;
	}

	public void setPsServicoPlano(String psServicoPlano) {
		this.psServicoPlano = psServicoPlano;
	}

	public String getPsServivoLinha() {
		return psServivoLinha;
	}

	public void setPsServivoLinha(String psServivoLinha) {
		this.psServivoLinha = psServivoLinha;
	}

	public String getTipoOfertaCheckedAba() {
		return tipoOfertaCheckedAba;
	}

	public void setTipoOfertaCheckedAba(String tipoOfertaCheckedAba) {
		this.tipoOfertaCheckedAba = tipoOfertaCheckedAba;
	}

	public String getCanalVendaCheckedAba() {
		return canalVendaCheckedAba;
	}

	public void setCanalVendaCheckedAba(String canalVendaCheckedAba) {
		this.canalVendaCheckedAba = canalVendaCheckedAba;
	}

	public String getAreaRegistroCheckedAba() {
		return areaRegistroCheckedAba;
	}

	public void setAreaRegistroCheckedAba(String areaRegistroCheckedAba) {
		this.areaRegistroCheckedAba = areaRegistroCheckedAba;
	}

	public String getCdOfertaNewAba() {
		return cdOfertaNewAba;
	}

	public void setCdOfertaNewAba(String cdOfertaNewAba) {
		this.cdOfertaNewAba = cdOfertaNewAba;
	}

	public String getNmOfertaNewAba() {
		return nmOfertaNewAba;
	}

	public void setNmOfertaNewAba(String nmOfertaNewAba) {
		this.nmOfertaNewAba = nmOfertaNewAba;
	}

	public String getIdServicoLinhaNewAba() {
		return idServicoLinhaNewAba;
	}

	public void setIdServicoLinhaNewAba(String idServicoLinhaNewAba) {
		this.idServicoLinhaNewAba = idServicoLinhaNewAba;
	}

	public String getIdSolicitacaoComercialNormalNewAba() {
		return idSolicitacaoComercialNormalNewAba;
	}

	public void setIdSolicitacaoComercialNormalNewAba(String idSolicitacaoComercialNormalNewAba) {
		this.idSolicitacaoComercialNormalNewAba = idSolicitacaoComercialNormalNewAba;
	}

	public String getIdSolicitacaoComercialPreCadastroNewAba() {
		return idSolicitacaoComercialPreCadastroNewAba;
	}

	public void setIdSolicitacaoComercialPreCadastroNewAba(String idSolicitacaoComercialPreCadastroNewAba) {
		this.idSolicitacaoComercialPreCadastroNewAba = idSolicitacaoComercialPreCadastroNewAba;
	}

	public String getIdServicoPlanoNewAba() {
		return idServicoPlanoNewAba;
	}

	public void setIdServicoPlanoNewAba(String idServicoPlanoNewAba) {
		this.idServicoPlanoNewAba = idServicoPlanoNewAba;
	}

	public String getIdSolicitacaoComercialPlanoNewAba() {
		return idSolicitacaoComercialPlanoNewAba;
	}

	public void setIdSolicitacaoComercialPlanoNewAba(String idSolicitacaoComercialPlanoNewAba) {
		this.idSolicitacaoComercialPlanoNewAba = idSolicitacaoComercialPlanoNewAba;
	}

	public String getDataVigenteInicioNewAba() {
		return dataVigenteInicioNewAba;
	}

	public void setDataVigenteInicioNewAba(String dataVigenteInicioNewAba) {
		this.dataVigenteInicioNewAba = dataVigenteInicioNewAba;
	}

	public String getDataVigenteFimNewAba() {
		return dataVigenteFimNewAba;
	}

	public void setDataVigenteFimNewAba(String dataVigenteFimNewAba) {
		this.dataVigenteFimNewAba = dataVigenteFimNewAba;
	}

	public String getDisponibilidadeAlterada() {
		return disponibilidadeAlterada;
	}

	public void setDisponibilidadeAlterada(String disponibilidadeAlterada) {
		this.disponibilidadeAlterada = disponibilidadeAlterada;
	}

	public String getComplementarAlterada() {
		return complementarAlterada;
	}

	public void setComplementarAlterada(String complementarAlterada) {
		this.complementarAlterada = complementarAlterada;
	}

	public String getTipoOfertaLinhacobre() {
		return tipoOfertaLinhacobre;
	}

	public void setTipoOfertaLinhacobre(String tipoOfertaLinhacobre) {
		this.tipoOfertaLinhacobre = tipoOfertaLinhacobre;
	}

	public String getTipoOfertaLinhafibra() {
		return tipoOfertaLinhafibra;
	}

	public void setTipoOfertaLinhafibra(String tipoOfertaLinhafibra) {
		this.tipoOfertaLinhafibra = tipoOfertaLinhafibra;
	}

	public String getTipoOfertaSolocobre() {
		return tipoOfertaSolocobre;
	}

	public void setTipoOfertaSolocobre(String tipoOfertaSolocobre) {
		this.tipoOfertaSolocobre = tipoOfertaSolocobre;
	}

	public String getTipoOfertaSolofibra() {
		return tipoOfertaSolofibra;
	}

	public void setTipoOfertaSolofibra(String tipoOfertaSolofibra) {
		this.tipoOfertaSolofibra = tipoOfertaSolofibra;
	}

	public String getTipoOfertaPortabilidade() {
		return tipoOfertaPortabilidade;
	}

	public void setTipoOfertaPortabilidade(String tipoOfertaPortabilidade) {
		this.tipoOfertaPortabilidade = tipoOfertaPortabilidade;
	}

	public String getTipoOfertaFwt() {
		return tipoOfertaFwt;
	}

	public void setTipoOfertaFwt(String tipoOfertaFwt) {
		this.tipoOfertaFwt = tipoOfertaFwt;
	}

	public String getTipoOfertaBasePontual() {
		return tipoOfertaBasePontual;
	}

	public void setTipoOfertaBasePontual(String tipoOfertaBasePontual) {
		this.tipoOfertaBasePontual = tipoOfertaBasePontual;
	}

	public String getTipoOfertaInadimplente() {
		return tipoOfertaInadimplente;
	}

	public void setTipoOfertaInadimplente(String tipoOfertaInadimplente) {
		this.tipoOfertaInadimplente = tipoOfertaInadimplente;
	}

	public String getTipoOfertaFatb() {
		return tipoOfertaFatb;
	}

	public void setTipoOfertaFatb(String tipoOfertaFatb) {
		this.tipoOfertaFatb = tipoOfertaFatb;
	}

	public String getPsServicoNew() {
		return psServicoNew;
	}

	public void setPsServicoNew(String psServicoNew) {
		this.psServicoNew = psServicoNew;
	}	

}
