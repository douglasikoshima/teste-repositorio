package com.indracompany.catalogo.ejb.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO;
import com.indracompany.catalogo.dao.interfaces.ClassificacaoMultimidiaDAO;
import com.indracompany.catalogo.dao.interfaces.CorDAO;
import com.indracompany.catalogo.dao.interfaces.FabricanteDAO;
import com.indracompany.catalogo.dao.interfaces.GrupoProdutoCaracteristicaDAO;
import com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO;
import com.indracompany.catalogo.dao.interfaces.GrupoProdutoTecnFreqVlDAO;
import com.indracompany.catalogo.dao.interfaces.GrupoProdutoTecnologiaDAO;
import com.indracompany.catalogo.dao.interfaces.MultimidiaDAO;
import com.indracompany.catalogo.dao.interfaces.TecnologiaDAO;
import com.indracompany.catalogo.dao.interfaces.TecnologiaTpFrequenciaVlDAO;
import com.indracompany.catalogo.dao.interfaces.TipoProdutoDAO;
import com.indracompany.catalogo.dao.interfaces.ValorCaracteristicaDAO;
import com.indracompany.catalogo.datalayer.Caracteristica;
import com.indracompany.catalogo.datalayer.ClassificacaoMultimidia;
import com.indracompany.catalogo.datalayer.Cor;
import com.indracompany.catalogo.datalayer.Fabricante;
import com.indracompany.catalogo.datalayer.GrupoProduto;
import com.indracompany.catalogo.datalayer.GrupoProdutoCaracteristica;
import com.indracompany.catalogo.datalayer.GrupoProdutoTecnFreqVl;
import com.indracompany.catalogo.datalayer.GrupoProdutoTecnologia;
import com.indracompany.catalogo.datalayer.Multimidia;
import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.datalayer.TecnologiaTpFrequencia;
import com.indracompany.catalogo.datalayer.TecnologiaTpFrequenciaVl;
import com.indracompany.catalogo.datalayer.TipoMultimidia;
import com.indracompany.catalogo.datalayer.TipoProduto;
import com.indracompany.catalogo.datalayer.ValorCaracteristica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;
import com.indracompany.catalogo.to.CorTO;
import com.indracompany.catalogo.to.FabricanteTO;
import com.indracompany.catalogo.to.GrupoProdutoCaracteristicaTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.GrupoProdutoTecnFreqVlTO;
import com.indracompany.catalogo.to.GrupoProdutoTecnologiaTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.PesquisaGrupoProdutoTO;
import com.indracompany.catalogo.to.TecnologiaTO;
import com.indracompany.catalogo.to.TecnologiaTpFrequenciaVlTO;
import com.indracompany.catalogo.to.TipoFrequenciaTO;
import com.indracompany.catalogo.to.TipoMultimidiaTO;
import com.indracompany.catalogo.to.TipoProdutoTO;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;
import com.indracompany.catalogo.to.VlFrequenciaTO;

@Stateless(name = "ModeloBean", mappedName = "ModeloBean")
@Session(ejbName = "ModeloBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ModeloBean implements ModeloBeanLocal {

	@EJB private TecnologiaDAO tecnologiaDAO;
	@EJB private TipoProdutoDAO tipoProdutoDAO;
	@EJB private FabricanteDAO fabricanteDAO;
	@EJB private CorDAO corDAO;
	@EJB private GrupoProdutoDAO grupoProdutoDAO;
	@EJB private CaracteristicaDAO caracteristicaDAO;
	@EJB private ValorCaracteristicaDAO valorCaracteristicaDAO;
	@EJB private MultimidiaDAO multimidiaDAO;
	@EJB private ClassificacaoMultimidiaDAO classificacaoMultimidiaDAO;
	@EJB private GrupoProdutoCaracteristicaDAO grupoProdutoCaracteristicaDAO;
	@EJB private GrupoProdutoTecnologiaDAO grupoProdutoTecnologiaDAO;
	@EJB private TecnologiaTpFrequenciaVlDAO tecnologiaTpFrequenciaVlDAO;
	@EJB private GrupoProdutoTecnFreqVlDAO grupoProdutoTecnFreqVlDAO;
	
	@SuppressWarnings("rawtypes")
	public Map<String, List> loadStaticFormLists() throws BusinessException {
		Map<String, List> formLists = new HashMap<String, List>(); 
		
		try {
			formLists.put("tipoProdutoTOList", tipoProdutoDAO.findAll());
			formLists.put("tecnologiaTOList", tecnologiaDAO.findAll());
			formLists.put("caracteristicaTOList", caracteristicaDAO.findAll());
		} catch(DAOException e){
			throw new EJBException(e);
		}
		return formLists;
	}
	
	public List<GrupoProdutoTO> searchModelo(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		List<GrupoProdutoTO> grupoProdutoTOList = null;
		
		try {
			grupoProdutoTOList = grupoProdutoDAO.search(grupoProdutoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return grupoProdutoTOList;
	}
	
	public void searchModelo(PesquisaGrupoProdutoTO pesquisaTO) throws BusinessException {
		try {
			grupoProdutoDAO.search(pesquisaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	public void saveModelo(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		try {
			String nmGrupoProduto = grupoProdutoTO.getNmGrupoProduto();
			if (grupoProdutoDAO.findByNome(nmGrupoProduto) != null) {
				throw new BusinessException("Nome Comercial já existe!");
			}
			
			grupoProdutoDAO.save(grupoProdutoTO);
			
			List<CaracteristicaTO> caracteristicaTOList = grupoProdutoTO.getCaracteristicaTOList();
			if (caracteristicaTOList != null && !caracteristicaTOList.isEmpty()) {
				for (CaracteristicaTO caracteristicaTO : caracteristicaTOList) {
					GrupoProdutoCaracteristicaTO grupoProdutoCaracteristicaTO = new GrupoProdutoCaracteristicaTO();
					grupoProdutoCaracteristicaTO.setGrupoProdutoTO(grupoProdutoTO);
					grupoProdutoCaracteristicaTO.setCaracteristicaTO(caracteristicaTO);
					grupoProdutoCaracteristicaTO.setNmUsuarioCriacao(grupoProdutoTO.getNmUsuarioCriacao());
					grupoProdutoCaracteristicaTO.setDtCriacao(grupoProdutoTO.getDtCriacao());
					grupoProdutoCaracteristicaDAO.save(grupoProdutoCaracteristicaTO);
				}
			}
			
			List<ValorCaracteristicaTO> valorCaracteristicaTOList = grupoProdutoTO.getValorCaracteristicaTOList();
			if (valorCaracteristicaTOList != null && !valorCaracteristicaTOList.isEmpty()) {
				for (ValorCaracteristicaTO valorCaracteristicaTO : valorCaracteristicaTOList) {
					GrupoProdutoCaracteristicaTO grupoProdutoCaracteristicaTO = new GrupoProdutoCaracteristicaTO();
					grupoProdutoCaracteristicaTO.setGrupoProdutoTO(grupoProdutoTO);
					grupoProdutoCaracteristicaTO.setCaracteristicaTO(valorCaracteristicaTO.getCaracteristicaTO());
					grupoProdutoCaracteristicaTO.setValorCaracteristicaTO(valorCaracteristicaTO);
					grupoProdutoCaracteristicaTO.setNmUsuarioCriacao(grupoProdutoTO.getNmUsuarioCriacao());
					grupoProdutoCaracteristicaTO.setDtCriacao(grupoProdutoTO.getDtCriacao());
					grupoProdutoCaracteristicaDAO.save(grupoProdutoCaracteristicaTO);
				}
			}
			
			List<TecnologiaTO> tecnologiaTOList = grupoProdutoTO.getTecnologiaTOList();
			if (tecnologiaTOList != null && !tecnologiaTOList.isEmpty()) {
				for (TecnologiaTO tecnologiaTO : tecnologiaTOList) {
					GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO = new GrupoProdutoTecnologiaTO();
					grupoProdutoTecnologiaTO.setGrupoProdutoTO(grupoProdutoTO);
					grupoProdutoTecnologiaTO.setTecnologiaTO(tecnologiaTO);
					grupoProdutoTecnologiaDAO.save(grupoProdutoTecnologiaTO);
					
					List<TipoFrequenciaTO> tipoFrequenciaTOList = tecnologiaTO.getTipoFrequenciaTOList();
					if (tipoFrequenciaTOList != null && !tipoFrequenciaTOList.isEmpty()) {
						for (TipoFrequenciaTO tipoFrequenciaTO : tipoFrequenciaTOList) {
							List<VlFrequenciaTO> vlFrequenciaTOList = tipoFrequenciaTO.getVlFrequenciaTOList();
							if (vlFrequenciaTOList != null && !vlFrequenciaTOList.isEmpty()) {
								for (VlFrequenciaTO vlFrequenciaTO : vlFrequenciaTOList) {
									TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO = tecnologiaTpFrequenciaVlDAO.obterTecnologiaTpFrequenciaVlTO(
										tecnologiaTO.getIdTecnologia(), tipoFrequenciaTO.getIdTipoFrequencia(), vlFrequenciaTO.getIdVlFrequencia());
									GrupoProdutoTecnFreqVlTO grupoProdutoTecnFreqVlTO = new GrupoProdutoTecnFreqVlTO();
									grupoProdutoTecnFreqVlTO.setGrupoProdutoTecnologiaTO(grupoProdutoTecnologiaTO);
									grupoProdutoTecnFreqVlTO.setTecnologiaTpFrequenciaVlTO(tecnologiaTpFrequenciaVlTO);
									grupoProdutoTecnFreqVlDAO.save(grupoProdutoTecnFreqVlTO);
								}
							}
						}
					}
				}
			}
			
			String url = grupoProdutoTO.getUrl();
			if (url != null && (url = url.trim()).length() > 0) {
				MultimidiaTO multimidiaTO = new MultimidiaTO();
				multimidiaTO.setNomeMultimidia(url);
				multimidiaTO.setGrupoProdutoTO(grupoProdutoTO);
				multimidiaTO.setTipoMultimidiaTO(new TipoMultimidiaTO(TipoMultimidia.ID_LINK));
				multimidiaTO.setNomeUsuarioCriacao(grupoProdutoTO.getNmUsuarioCriacao());
				multimidiaTO.setDataCriacao(grupoProdutoTO.getDtCriacao());
				multimidiaDAO.save(multimidiaTO);
			}
			
			List<MultimidiaTO> multimidiaTOList = grupoProdutoTO.getMultimidiaTOList();
			if (multimidiaTOList != null && !multimidiaTOList.isEmpty()) {
				for (MultimidiaTO multimidiaTO : multimidiaTOList) {
					multimidiaTO.setGrupoProdutoTO(grupoProdutoTO);
					Integer idTipoMultimidia = (multimidiaTO.getNomeMultimidia().toLowerCase().endsWith(".swf"))
						? TipoMultimidia.ID_VIDEO : TipoMultimidia.ID_IMAGEM;
					multimidiaTO.setTipoMultimidiaTO(new TipoMultimidiaTO(idTipoMultimidia));
					multimidiaTO.setNomeUsuarioCriacao(grupoProdutoTO.getNmUsuarioCriacao());
					multimidiaTO.setDataCriacao(grupoProdutoTO.getDtCriacao());
					multimidiaDAO.save(multimidiaTO);
				}
			}
			
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}
	
	public void updateModelo(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		try {
			Integer idGrupoProduto = grupoProdutoTO.getIdGrupoProduto();
			GrupoProduto grupoProduto = grupoProdutoDAO.findById(idGrupoProduto);
			if (grupoProduto == null) {
				throw new BusinessException("Não foi encontrado o modelo com o id " + idGrupoProduto + ".");
			}
			grupoProduto.setNmUsuarioAlteracao(grupoProdutoTO.getNmUsuarioAlteracao());
			grupoProduto.setDtUltimaAlteracao(grupoProdutoTO.getDtUltimaAlteracao());
			
			// tipoProduto
			TipoProduto tipoProduto = grupoProduto.getTipoProduto();
			TipoProdutoTO tipoProdutoTO = grupoProdutoTO.getTipoProdutoTO();
			if (tipoProdutoTO == null) {
				throw new BusinessException("O tipo de produto é obrigatório.");
			}
			Integer idTipoProduto = tipoProdutoTO.getIdTipoProduto();
			if (!tipoProduto.getIdTipoProduto().equals(idTipoProduto)) {
				tipoProduto = tipoProdutoDAO.findById(idTipoProduto);
				if (tipoProduto == null) {
					throw new BusinessException("Não foi encontrado o tipo de produto com o id " + idTipoProduto + ".");
				}
				grupoProduto.setTipoProduto(tipoProduto);
			}
			
			// fabricante
			Fabricante fabricante = grupoProduto.getFabricante();
			FabricanteTO fabricanteTO = grupoProdutoTO.getFabricanteTO();
			if (fabricanteTO == null) {
				throw new BusinessException("O fabricante é obrigatório.");
			}
			Integer idFabricante = fabricanteTO.getIdFabricante();
			if (!fabricante.getIdFabricante().equals(idFabricante)) {
				fabricante = fabricanteDAO.findById(idFabricante);
				if (fabricante == null) {
					throw new BusinessException("Não foi encontrado o fabricante com o id " + idFabricante + ".");
				}
				grupoProduto.setFabricante(fabricante);
			}
			
			// cor
			Cor cor = grupoProduto.getCor();
			CorTO corTO = grupoProdutoTO.getCorTO();
			if (corTO == null) {
				throw new BusinessException("A cor é obrigatória.");
			}
			Integer idCor = corTO.getIdCor();
			if (cor == null || (cor != null && !cor.getIdCor().equals(idCor))) {
				cor = corDAO.findById(idCor);
				if (cor == null) {
					throw new BusinessException("Não foi encontrada a cor com o id " + idCor + ".");
				}
				grupoProduto.setCor(cor);
			}
			
			// nmGrupoProduto
			String nmGrupoProduto = grupoProdutoTO.getNmGrupoProduto();
			if (nmGrupoProduto == null || (nmGrupoProduto = nmGrupoProduto.trim()).length() == 0) {
				throw new BusinessException("O nome é obrigatório.");
			}
			if (!nmGrupoProduto.equals(grupoProduto.getNmGrupoProduto()) && grupoProdutoDAO.findByNome(nmGrupoProduto) != null) {
				throw new BusinessException("Nome Comercial já existe!");
			}
			grupoProduto.setNmGrupoProduto(nmGrupoProduto);
			
			// inDisponivel
			String inDisponivel = grupoProdutoTO.getInDisponivel();
			if (inDisponivel == null || (inDisponivel = inDisponivel.trim()).length() == 0) {
				throw new BusinessException("A indicação de disponibilidade é obrigatória.");
			}
			if (!inDisponivel.equals("S") && !inDisponivel.equals("N")) {
				throw new BusinessException("Indicação de disponibilidade inválida.");
			}
			grupoProduto.setInDisponivel(inDisponivel);
			
			// inFimVida
			String inFimVida = grupoProdutoTO.getInFimVida();
			if (inFimVida == null || (inFimVida = inFimVida.trim()).length() == 0) {
				throw new BusinessException("A indicação de fim de vida é obrigatória.");
			}
			if (!inFimVida.equals("S") && !inFimVida.equals("N")) {
				throw new BusinessException("Indicação de fim de vida inválida.");
			}
			grupoProduto.setInFimVida(inFimVida);
			
			// dsNota
			String dsNota = grupoProdutoTO.getDsNota();
			if (dsNota != null) {
				dsNota = dsNota.trim();
			}
			grupoProduto.setDsNota(dsNota);
			
			// caracteristicas
			List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaList = grupoProduto.getGrupoProdutoCaracteristicaList();
			List<CaracteristicaTO> caracteristicaTOList = grupoProdutoTO.getCaracteristicaTOList();
			List<ValorCaracteristicaTO> valorCaracteristicaTOList = grupoProdutoTO.getValorCaracteristicaTOList();
			
			List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaListToAdd = new ArrayList<GrupoProdutoCaracteristica>();
			List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaListToRemove = new ArrayList<GrupoProdutoCaracteristica>();
			
			if (caracteristicaTOList != null && !caracteristicaTOList.isEmpty()) {
				for (CaracteristicaTO caracteristicaTO : caracteristicaTOList) {
					final Integer idCaracteristica = caracteristicaTO.getIdCaracteristica();
					GrupoProdutoCaracteristica grupoProdutoCaracteristica = (GrupoProdutoCaracteristica) CollectionUtils.find(grupoProdutoCaracteristicaList, new Predicate() {
						public boolean evaluate(Object obj) {
							GrupoProdutoCaracteristica grupoProdutoCaracteristica = (GrupoProdutoCaracteristica) obj;
							return (grupoProdutoCaracteristica.getValorCaracteristica() == null &&
									grupoProdutoCaracteristica.getCaracteristica().getIdCaracteristica().equals(idCaracteristica));
						}
					});
					if (grupoProdutoCaracteristica == null) {
						Caracteristica caracteristica = caracteristicaDAO.findById(idCaracteristica);
						if (caracteristica == null) {
							throw new BusinessException("Não foi encontrada a característica com o id " + idCaracteristica + ".");
						}
						grupoProdutoCaracteristica = new GrupoProdutoCaracteristica();
						grupoProdutoCaracteristica.setGrupoProduto(grupoProduto);
						grupoProdutoCaracteristica.setCaracteristica(caracteristica);
						grupoProdutoCaracteristica.setNmUsuarioCriacao(grupoProdutoTO.getNmUsuarioAlteracao());
						grupoProdutoCaracteristica.setDtCriacao(grupoProdutoTO.getDtUltimaAlteracao());
						grupoProdutoCaracteristicaListToAdd.add(grupoProdutoCaracteristica);
					} else {
						grupoProdutoCaracteristica.setNmUsuarioAlteracao(grupoProdutoTO.getNmUsuarioAlteracao());
						grupoProdutoCaracteristica.setDtUltimaAlteracao(grupoProdutoTO.getDtUltimaAlteracao());
					}
				}
			}
			
			if (valorCaracteristicaTOList != null && !valorCaracteristicaTOList.isEmpty()) {
				for (ValorCaracteristicaTO valorCaracteristicaTO : valorCaracteristicaTOList) {
					final Integer idValorCaracteristica = valorCaracteristicaTO.getIdValorCaracteristica();
					final Integer idCaracteristica = valorCaracteristicaTO.getCaracteristicaTO().getIdCaracteristica();
					GrupoProdutoCaracteristica grupoProdutoCaracteristica = (GrupoProdutoCaracteristica) CollectionUtils.find(grupoProdutoCaracteristicaList, new Predicate() {
						public boolean evaluate(Object obj) {
							GrupoProdutoCaracteristica grupoProdutoCaracteristica = (GrupoProdutoCaracteristica) obj;
							ValorCaracteristica valorCaracteristica = grupoProdutoCaracteristica.getValorCaracteristica();
							return (valorCaracteristica != null && valorCaracteristica.getIdValorCaracteristica().equals(idValorCaracteristica) &&
									grupoProdutoCaracteristica.getCaracteristica().getIdCaracteristica().equals(idCaracteristica));
						}
					});
					if (grupoProdutoCaracteristica == null) {
						ValorCaracteristica valorCaracteristica = valorCaracteristicaDAO.findById(idValorCaracteristica);
						if (valorCaracteristica == null) {
							throw new BusinessException("Não foi encontrado o valor de característica com o id " + idValorCaracteristica + ".");
						}
						grupoProdutoCaracteristica = new GrupoProdutoCaracteristica();
						grupoProdutoCaracteristica.setGrupoProduto(grupoProduto);
						grupoProdutoCaracteristica.setValorCaracteristica(valorCaracteristica);
						grupoProdutoCaracteristica.setCaracteristica(valorCaracteristica.getCaracteristica());
						grupoProdutoCaracteristica.setNmUsuarioCriacao(grupoProdutoTO.getNmUsuarioAlteracao());
						grupoProdutoCaracteristica.setDtCriacao(grupoProdutoTO.getDtUltimaAlteracao());
						grupoProdutoCaracteristicaListToAdd.add(grupoProdutoCaracteristica);
					} else {
						grupoProdutoCaracteristica.setNmUsuarioAlteracao(grupoProdutoTO.getNmUsuarioAlteracao());
						grupoProdutoCaracteristica.setDtUltimaAlteracao(grupoProdutoTO.getDtUltimaAlteracao());
					}
				}
			}
			
			if (grupoProdutoCaracteristicaList != null && !grupoProdutoCaracteristicaList.isEmpty()) {
				for (GrupoProdutoCaracteristica grupoProdutoCaracteristica : grupoProdutoCaracteristicaList) {
					ValorCaracteristica valorCaracteristica = grupoProdutoCaracteristica.getValorCaracteristica();
					if (valorCaracteristica == null) {
						if (caracteristicaTOList != null && !caracteristicaTOList.isEmpty()) {
							final Integer idCaracteristica = grupoProdutoCaracteristica.getCaracteristica().getIdCaracteristica();
							CaracteristicaTO caracteristicaTO = (CaracteristicaTO) CollectionUtils.find(caracteristicaTOList, new Predicate() {
								public boolean evaluate(Object obj) {
									CaracteristicaTO caracteristicaTO = (CaracteristicaTO) obj;
									return caracteristicaTO.getIdCaracteristica().equals(idCaracteristica);
								}
							});
							if (caracteristicaTO == null) {
								grupoProdutoCaracteristicaListToRemove.add(grupoProdutoCaracteristica);
							}
						} else {
							grupoProdutoCaracteristicaListToRemove.add(grupoProdutoCaracteristica);
						}
					} else {
						if (valorCaracteristicaTOList != null && !valorCaracteristicaTOList.isEmpty()) {
							final Integer idValorCaracteristica = valorCaracteristica.getIdValorCaracteristica();
							final Integer idCaracteristica = valorCaracteristica.getCaracteristica().getIdCaracteristica();
							ValorCaracteristicaTO valorCaracteristicaTO = (ValorCaracteristicaTO) CollectionUtils.find(valorCaracteristicaTOList, new Predicate() {
								public boolean evaluate(Object obj) {
									ValorCaracteristicaTO valorCaracteristicaTO = (ValorCaracteristicaTO) obj;
									return (valorCaracteristicaTO.getIdValorCaracteristica().equals(idValorCaracteristica) &&
											valorCaracteristicaTO.getCaracteristicaTO().getIdCaracteristica().equals(idCaracteristica));
								}
							});
							if (valorCaracteristicaTO == null) {
								grupoProdutoCaracteristicaListToRemove.add(grupoProdutoCaracteristica);
							}
						} else {
							grupoProdutoCaracteristicaListToRemove.add(grupoProdutoCaracteristica);
						}
					}
				}
			}
			
			grupoProdutoCaracteristicaList.removeAll(grupoProdutoCaracteristicaListToRemove);
			grupoProdutoCaracteristicaList.addAll(grupoProdutoCaracteristicaListToAdd);
			grupoProduto.setGrupoProdutoCaracteristicaList(grupoProdutoCaracteristicaList);
			
			// como o jpa 1 nao remove pra mim, preciso remover por conta propria
			for (GrupoProdutoCaracteristica grupoProdutoCaracteristica : grupoProdutoCaracteristicaListToRemove) {
				grupoProdutoCaracteristicaDAO.remove(grupoProdutoCaracteristica);
			}
			
			// tecnologias/frequencias
			List<GrupoProdutoTecnologia> grupoProdutoTecnologiaList = grupoProduto.getGrupoProdutoTecnologiaList();
			List<TecnologiaTO> tecnologiaTOList = grupoProdutoTO.getTecnologiaTOList();
			
			List<GrupoProdutoTecnologia> grupoProdutoTecnologiaListToAdd = new ArrayList<GrupoProdutoTecnologia>();
			List<GrupoProdutoTecnologia> grupoProdutoTecnologiaListToRemove = new ArrayList<GrupoProdutoTecnologia>();
			
			if (tecnologiaTOList != null && !tecnologiaTOList.isEmpty()) {
				for (TecnologiaTO tecnologiaTO : tecnologiaTOList) {
					final Integer idTecnologia = tecnologiaTO.getIdTecnologia();
					GrupoProdutoTecnologia grupoProdutoTecnologia = (GrupoProdutoTecnologia) CollectionUtils.find(grupoProdutoTecnologiaList, new Predicate() {
						public boolean evaluate(Object obj) {
							GrupoProdutoTecnologia grupoProdutoTecnologia = (GrupoProdutoTecnologia) obj;
							return grupoProdutoTecnologia.getTecnologia().getIdTecnologia().equals(idTecnologia);
						}
					});
					if (grupoProdutoTecnologia == null) {
						Tecnologia tecnologia = tecnologiaDAO.findById(idTecnologia);
						if (tecnologia == null) {
							throw new BusinessException("Não foi encontrada a tecnologia com o id " + idTecnologia + ".");
						}
						grupoProdutoTecnologia = new GrupoProdutoTecnologia();
						grupoProdutoTecnologia.setGrupoProduto(grupoProduto);
						grupoProdutoTecnologia.setTecnologia(tecnologia);
						grupoProdutoTecnologia.setGrupoProdutoTecnFreqVlList(new ArrayList<GrupoProdutoTecnFreqVl>());
						grupoProdutoTecnologiaListToAdd.add(grupoProdutoTecnologia);
					}
					
					List<GrupoProdutoTecnFreqVl> grupoProdutoTecnFreqVlList = grupoProdutoTecnologia.getGrupoProdutoTecnFreqVlList();
					List<TipoFrequenciaTO> tipoFrequenciaTOList = tecnologiaTO.getTipoFrequenciaTOList();
					
					List<GrupoProdutoTecnFreqVl> grupoProdutoTecnFreqVlListToAdd = new ArrayList<GrupoProdutoTecnFreqVl>();
					List<GrupoProdutoTecnFreqVl> grupoProdutoTecnFreqVlListToRemove = new ArrayList<GrupoProdutoTecnFreqVl>();
					
					if (tipoFrequenciaTOList != null && !tipoFrequenciaTOList.isEmpty()) {
						for (TipoFrequenciaTO tipoFrequenciaTO : tipoFrequenciaTOList) {
							List<VlFrequenciaTO> vlFrequenciaTOList = tipoFrequenciaTO.getVlFrequenciaTOList();
							if (vlFrequenciaTOList != null && !vlFrequenciaTOList.isEmpty()) {
								for (VlFrequenciaTO vlFrequenciaTO : vlFrequenciaTOList) {
									final Integer idTipoFrequencia = tipoFrequenciaTO.getIdTipoFrequencia();
									final Integer idVlFrequencia = vlFrequenciaTO.getIdVlFrequencia();
									GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl = (GrupoProdutoTecnFreqVl) CollectionUtils.find(grupoProdutoTecnFreqVlList, new Predicate() {
										public boolean evaluate(Object obj) {
											GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl = (GrupoProdutoTecnFreqVl) obj;
											TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl = grupoProdutoTecnFreqVl.getTecnologiaTpFrequenciaVl();
											TecnologiaTpFrequencia tecnologiaTpFrequencia = tecnologiaTpFrequenciaVl.getTecnologiaTpFrequencia();
											return (tecnologiaTpFrequencia.getTecnologia().getIdTecnologia().equals(idTecnologia) &&
													tecnologiaTpFrequencia.getTipoFrequencia().getIdTipoFrequencia().equals(idTipoFrequencia) &&
													tecnologiaTpFrequenciaVl.getVlFrequencia().getIdVlFrequencia().equals(idVlFrequencia));
										}
									});
									if (grupoProdutoTecnFreqVl == null) {
										TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl = tecnologiaTpFrequenciaVlDAO.obterTecnologiaTpFrequenciaVl(idTecnologia, idTipoFrequencia, idVlFrequencia);
										if (tecnologiaTpFrequenciaVl == null) {
											throw new BusinessException("Não foi encontrada a associação entre a tecnologia com o id " + idTecnologia + ", a frequência com o id " + idTipoFrequencia + " e o valor com o id " + idVlFrequencia + ".");
										}
										grupoProdutoTecnFreqVl = new GrupoProdutoTecnFreqVl();
										grupoProdutoTecnFreqVl.setGrupoProdutoTecnologia(grupoProdutoTecnologia);
										grupoProdutoTecnFreqVl.setTecnologiaTpFrequenciaVl(tecnologiaTpFrequenciaVl);
										grupoProdutoTecnFreqVlListToAdd.add(grupoProdutoTecnFreqVl);
									}
								}
							}
						}
					}
					
					if (grupoProdutoTecnFreqVlList != null && !grupoProdutoTecnFreqVlList.isEmpty()) {
						for (GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl : grupoProdutoTecnFreqVlList) {
							TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl = grupoProdutoTecnFreqVl.getTecnologiaTpFrequenciaVl();
							TecnologiaTpFrequencia tecnologiaTpFrequencia = tecnologiaTpFrequenciaVl.getTecnologiaTpFrequencia();
							final Integer idTipoFrequencia = tecnologiaTpFrequencia.getTipoFrequencia().getIdTipoFrequencia();
							final Integer idVlFrequencia = tecnologiaTpFrequenciaVl.getVlFrequencia().getIdVlFrequencia();
							if (tipoFrequenciaTOList != null && !tipoFrequenciaTOList.isEmpty()) {
								TipoFrequenciaTO tipoFrequenciaTO = (TipoFrequenciaTO) CollectionUtils.find(tipoFrequenciaTOList, new Predicate() {
									public boolean evaluate(Object obj) {
										TipoFrequenciaTO tipoFrequenciaTO = (TipoFrequenciaTO) obj;
										if (tipoFrequenciaTO.getIdTipoFrequencia().equals(idTipoFrequencia)) {
											List<VlFrequenciaTO> vlFrequenciaTOList = tipoFrequenciaTO.getVlFrequenciaTOList();
											if (vlFrequenciaTOList != null && !vlFrequenciaTOList.isEmpty()) {
												VlFrequenciaTO vlFrequenciaTO = (VlFrequenciaTO) CollectionUtils.find(vlFrequenciaTOList, new Predicate() {
													public boolean evaluate(Object obj) {
														VlFrequenciaTO vlFrequenciaTO = (VlFrequenciaTO) obj;
														return vlFrequenciaTO.getIdVlFrequencia().equals(idVlFrequencia);
													}
												});
												return (vlFrequenciaTO != null);
											}
										}
										return false;
									}
								});
								if (tipoFrequenciaTO == null) {
									grupoProdutoTecnFreqVlListToRemove.add(grupoProdutoTecnFreqVl);
								}
							} else {
								grupoProdutoTecnFreqVlListToRemove.add(grupoProdutoTecnFreqVl);
							}
						}
					}
					
					grupoProdutoTecnFreqVlList.removeAll(grupoProdutoTecnFreqVlListToRemove);
					grupoProdutoTecnFreqVlList.addAll(grupoProdutoTecnFreqVlListToAdd);
					grupoProdutoTecnologia.setGrupoProdutoTecnFreqVlList(grupoProdutoTecnFreqVlList);
					
					// como o jpa 1 nao remove pra mim, preciso remover por conta propria
					for (GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl : grupoProdutoTecnFreqVlListToRemove) {
						grupoProdutoTecnFreqVlDAO.remove(grupoProdutoTecnFreqVl);
					}
				}
			}
			
			if (grupoProdutoTecnologiaList != null && !grupoProdutoTecnologiaList.isEmpty()) {
				for (GrupoProdutoTecnologia grupoProdutoTecnologia : grupoProdutoTecnologiaList) {
					final Integer idTecnologia = grupoProdutoTecnologia.getTecnologia().getIdTecnologia();
					TecnologiaTO tecnologiaTO = (TecnologiaTO) CollectionUtils.find(tecnologiaTOList, new Predicate() {
						public boolean evaluate(Object obj) {
							TecnologiaTO tecnologiaTO = (TecnologiaTO) obj;
							return tecnologiaTO.getIdTecnologia().equals(idTecnologia);
						}
					});
					if (tecnologiaTO == null) {
						grupoProdutoTecnologiaListToRemove.add(grupoProdutoTecnologia);
					}
				}
			}
			
			grupoProdutoTecnologiaList.removeAll(grupoProdutoTecnologiaListToRemove);
			grupoProdutoTecnologiaList.addAll(grupoProdutoTecnologiaListToAdd);
			grupoProduto.setGrupoProdutoTecnologiaList(grupoProdutoTecnologiaList);
			
			// como o jpa 1 nao remove pra mim, preciso remover por conta propria
			for (GrupoProdutoTecnologia grupoProdutoTecnologia : grupoProdutoTecnologiaListToRemove) {
				grupoProdutoTecnologiaDAO.remove(grupoProdutoTecnologia);
			}
			
			// multimidias
			List<Multimidia> multimidiaList = grupoProduto.getMultimidiaList();
			List<MultimidiaTO> multimidiaTOList = grupoProdutoTO.getMultimidiaTOList();
			
			String url = grupoProdutoTO.getUrl();
			if (url != null && (url = url.trim()).length() > 0) {
				MultimidiaTO multimidiaTO = new MultimidiaTO();
				multimidiaTO.setNomeMultimidia(url);
				multimidiaTO.setGrupoProdutoTO(grupoProdutoTO);
				multimidiaTO.setTipoMultimidiaTO(new TipoMultimidiaTO(TipoMultimidia.ID_LINK));
				multimidiaTOList.add(multimidiaTO);
			}
			
			List<Multimidia> multimidiaListToAdd = new ArrayList<Multimidia>();
			List<Multimidia> multimidiaListToRemove = new ArrayList<Multimidia>();
			
			if (multimidiaTOList != null && !multimidiaTOList.isEmpty()) {
				for (MultimidiaTO multimidiaTO : multimidiaTOList) {
					final String nomeMultimidia = multimidiaTO.getNomeMultimidia();
					Multimidia multimidia = (Multimidia) CollectionUtils.find(multimidiaList, new Predicate() {
						public boolean evaluate(Object obj) {
							Multimidia multimidia = (Multimidia) obj;
							return multimidia.getNomeMultimidia().equals(nomeMultimidia);
						}
					});
					if (multimidia == null) {
						multimidia = new Multimidia();
						multimidia.setGrupoProduto(grupoProduto);
						multimidia.setNomeMultimidia(nomeMultimidia);
						
						TipoMultimidiaTO tipoMultimidiaTO = multimidiaTO.getTipoMultimidiaTO();
						Integer idTipoMultimidia = (tipoMultimidiaTO != null) ? tipoMultimidiaTO.getIdTipoMultimidia() :
							((nomeMultimidia.toLowerCase().endsWith(".swf")) ? TipoMultimidia.ID_VIDEO : TipoMultimidia.ID_IMAGEM);
						multimidia.setTipoMultimidia(new TipoMultimidia(idTipoMultimidia));
						
						ClassificacaoMultimidiaTO classificacaoTO = multimidiaTO.getClassificacaoTO();
						if (classificacaoTO != null) {
							Integer idClassificacao = classificacaoTO.getIdClassificacao();
							ClassificacaoMultimidia classificacao = classificacaoMultimidiaDAO.findById(idClassificacao);
							if (classificacao == null) {
								throw new BusinessException("Não foi encontrada a classificação de multimídia com o id " + idClassificacao + ".");
							}
							multimidia.setClassificacao(classificacao);
						}
						
						corTO = multimidiaTO.getCorTO();
						if (corTO != null) {
							idCor = corTO.getIdCor();
							cor = corDAO.findById(idCor);
							if (cor == null) {
								throw new BusinessException("Não foi encontrada a cor com o id " + idCor + ".");
							}
							multimidia.setCor(cor);
						}
						
						multimidia.setNomeUsuarioCriacao(grupoProdutoTO.getNmUsuarioAlteracao());
						multimidia.setDataCriacao(grupoProdutoTO.getDtUltimaAlteracao());
						multimidiaListToAdd.add(multimidia);
						
					} else {
						ClassificacaoMultimidia classificacao = multimidia.getClassificacao();
						ClassificacaoMultimidiaTO classificacaoTO = multimidiaTO.getClassificacaoTO();
						if (classificacaoTO != null) {
							Integer idClassificacao = classificacaoTO.getIdClassificacao();
							if (classificacao == null || (classificacao != null && !classificacao.getIdClassificacao().equals(idClassificacao))) {
								classificacao = classificacaoMultimidiaDAO.findById(idClassificacao);
								if (classificacao == null) {
									throw new BusinessException("Não foi encontrada a classificação de multimídia com o id " + idClassificacao + ".");
								}
								multimidia.setClassificacao(classificacao);
							}
						} else if (classificacao != null) {
							multimidia.setClassificacao(null);
						}
						
						cor = multimidia.getCor();
						corTO = multimidiaTO.getCorTO();
						if (corTO != null) {
							idCor = corTO.getIdCor();
							if (cor == null || (cor != null && !cor.getIdCor().equals(idCor))) {
								cor = corDAO.findById(idCor);
								if (cor == null) {
									throw new BusinessException("Não foi encontrada a cor com o id " + idCor + ".");
								}
								multimidia.setCor(cor);
							}
						} else if (cor != null) {
							multimidia.setCor(null);
						}
						
						if (!multimidia.getTipoMultimidia().getIdTipoMultimidia().equals(TipoMultimidia.ID_LINK) &&
							classificacaoTO == null && corTO == null) {
							throw new BusinessException("Favor classificar as imagens existentes antes de gravar.");
						}
						
						multimidia.setNomeUsuarioUltimaAlteracao(grupoProdutoTO.getNmUsuarioAlteracao());
						multimidia.setDataUltimaAlteracao(grupoProdutoTO.getDtUltimaAlteracao());
					}
				}
			}
			
			if (multimidiaList != null && !multimidiaList.isEmpty()) {
				for (Multimidia multimidia : multimidiaList) {
					final String nomeMultimidia = multimidia.getNomeMultimidia();
					MultimidiaTO multimidiaTO = (MultimidiaTO) CollectionUtils.find(multimidiaTOList, new Predicate() {
						public boolean evaluate(Object obj) {
							MultimidiaTO multimidiaTO = (MultimidiaTO) obj;
							return multimidiaTO.getNomeMultimidia().equals(nomeMultimidia);
						}
					});
					if (multimidiaTO == null) {
						multimidiaListToRemove.add(multimidia);
					}
				}
			}
			
			multimidiaList.removeAll(multimidiaListToRemove);
			multimidiaList.addAll(multimidiaListToAdd);
			grupoProduto.setMultimidiaList(multimidiaList);
			
			// como o jpa 1 nao remove pra mim, preciso remover por conta propria
			for (Multimidia multimidia : multimidiaListToRemove) {
				multimidiaDAO.remove(multimidia);
			}
			
			grupoProdutoDAO.update(grupoProduto);
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public List<FabricanteTO> findByTipoProduto(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		List<FabricanteTO> result = new ArrayList<FabricanteTO>();
		
		try {
			result = tipoProdutoDAO.findByIdTipoProduto(grupoProdutoTO.getTipoProdutoTO().getIdTipoProduto());
		} catch(DAOException e){
			throw new EJBException(e);
		}
		
		return result;
	}

	public void removeModelo(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		try {
			grupoProdutoDAO.remove(grupoProdutoTO);
		} catch(DAOException e) {
			throw new EJBException(e);
		}
	}
}