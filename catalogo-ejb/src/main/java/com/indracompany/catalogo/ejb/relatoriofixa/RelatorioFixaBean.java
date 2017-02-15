package com.indracompany.catalogo.ejb.relatoriofixa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.RelatorioFixaDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoFixaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoComercialRelatorioFixaTO;
import com.indracompany.catalogo.to.RelacionamentoRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxENCxPFxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.ServicoRelatorioFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

@Stateless(name = "RelatorioFixaBean", mappedName = "RelatorioFixaBean")
@Session(ejbName = "RelatorioFixaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RelatorioFixaBean implements RelatorioFixaBeanLocal {

    @EJB
    private RelatorioFixaDAO relatorioFixaDAO;
    
    @EJB
    private ServicoFixaDAO servicoFixaDAO;
    
    public List<TipoSolicitacaoComercialTO> findAllTpSolicitacaoComercial() throws BusinessException {
        List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList;
        try {
            tipoSolicitacaoComercialTOList = this.relatorioFixaDAO.findAllTpSolicitacaoComercial();
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return tipoSolicitacaoComercialTOList;
    }
    
    public List<ServicoRelatorioFixaTO> pesquisarServico(ServicoRelatorioFixaTO servicoRelatorioFixaTO) throws BusinessException {
        List<ServicoRelatorioFixaTO> servicoRelatorioFixaTOList;
        try {
            servicoRelatorioFixaTOList = this.relatorioFixaDAO.pesquisarServico(servicoRelatorioFixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return servicoRelatorioFixaTOList;        
    }
    
    public List<RelacionamentoRelatorioFixaTO> pesquisarRelacionamentoServico(RelacionamentoRelatorioFixaTO relacionamentoRelatorioFixaTO) throws BusinessException {
        List<RelacionamentoRelatorioFixaTO> relacionamentoRelatorioFixaTOList;
        try {
            relacionamentoRelatorioFixaTOList = this.relatorioFixaDAO.pesquisarRelacionamentoServico(relacionamentoRelatorioFixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return relacionamentoRelatorioFixaTOList;        
    }

    public List<SolicitacaoComercialFixaTO> pesquisarSolicitacaoComercial(SolicitacaoComercialFixaTO solicitacaoComercialRelatorioFixaTO) throws BusinessException {
        List<SolicitacaoComercialFixaTO> solicitacaoComercialRelatorioFixaTOList;
        try {
            solicitacaoComercialRelatorioFixaTOList = this.relatorioFixaDAO.pesquisarSolicitacaoComercial(solicitacaoComercialRelatorioFixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return solicitacaoComercialRelatorioFixaTOList;
    }
    
    public List<TipoRelacionamentoTO> findAllTpRelacionamento() throws BusinessException{
        List<TipoRelacionamentoTO> tipoRelacionamentoTOList = new ArrayList<TipoRelacionamentoTO>();
        try {
            tipoRelacionamentoTOList = this.servicoFixaDAO.findAllTpRelacionamento();

        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return tipoRelacionamentoTOList;        
    }
    
    public List<GrupoComercialRelatorioFixaTO> pesquisarGrupoComercial(GrupoComercialRelatorioFixaTO grupoComercialRelatorioFixaTO) throws BusinessException {
        List<GrupoComercialRelatorioFixaTO> grupoComercialRelatorioFixaTOList;
        try {
            grupoComercialRelatorioFixaTOList = this.relatorioFixaDAO.pesquisarGrupoComercial(grupoComercialRelatorioFixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return grupoComercialRelatorioFixaTOList;
    }
    
    public List<SCxGCxPMxACRelatorioFixaTO> pesquisarSCxGCxPMxAC(SCxGCxPMxACRelatorioFixaTO sCxGCxPMxACRelatorioFixaTO) throws BusinessException {
        List<SCxGCxPMxACRelatorioFixaTO> sCxGCxPMxACRelatorioFixaTOList;
        try {
            sCxGCxPMxACRelatorioFixaTOList = this.relatorioFixaDAO.pesquisarSCxGCxPMxAC(sCxGCxPMxACRelatorioFixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return sCxGCxPMxACRelatorioFixaTOList;
    }

	public List<SCxENCxPFxGCxPMxACRelatorioFixaTO> pesquisarSCxENCxPFxGCxPMxAC(SCxENCxPFxGCxPMxACRelatorioFixaTO sCxENCxPFxGCxPMxACRelatorioFixaTO) throws BusinessException {
		List<SCxENCxPFxGCxPMxACRelatorioFixaTO> sCxENCxPFxGCxPMxACRelatorioFixaTOList;
		try {
			sCxENCxPFxGCxPMxACRelatorioFixaTOList = this.relatorioFixaDAO.pesquisarSCxENCxPFxGCxPMxAC(sCxENCxPFxGCxPMxACRelatorioFixaTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
		return sCxENCxPFxGCxPMxACRelatorioFixaTOList;
	}
    
}
