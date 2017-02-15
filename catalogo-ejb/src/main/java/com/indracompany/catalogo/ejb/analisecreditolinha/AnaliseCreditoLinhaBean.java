package com.indracompany.catalogo.ejb.analisecreditolinha;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.AnaliseCreditoLinhaDAO;
import com.indracompany.catalogo.datalayer.OfertafixaCreditoScore;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnaliseCreditoLinhaTO;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

@Stateless(name = "AnaliseCreditoLinhaBean", mappedName = "AnaliseCreditoLinhaBean")
@Session(ejbName = "AnaliseCreditoLinhaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AnaliseCreditoLinhaBean implements AnaliseCreditoLinhaBeanLocal {

    @EJB
    private AnaliseCreditoLinhaDAO analiseCreditoLinhaDAO;

    private static Logger log = Logger.getLogger(AnaliseCreditoLinhaBean.class);

    public List<AnaliseCreditoLinhaTO> search(AnaliseCreditoLinhaTO analiseCreditoLinhaTO, List<AnaliseCreditoTO> analiseCreditoTOListScore)
            throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.analiseCreditoLinhaDAO.search(analiseCreditoLinhaTO, analiseCreditoTOListScore);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<AnaliseCreditoLinhaTO> loadServicoLinha() throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.analiseCreditoLinhaDAO.loadServicoLinha();
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<AnaliseCreditoTO> loadScore() throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.analiseCreditoLinhaDAO.loadScore();
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public void gravar(AnaliseCreditoLinhaTO analiseCreditoLinhaTO) throws BusinessException {
        log.debug("Direcionando para DAO");
        log.debug("Preparando para a persistencia no banco de dados.");

        List<OfertafixaCreditoScore> analiseCreditoLinhaTODelete = new ArrayList<OfertafixaCreditoScore>();
        List<AnaliseCreditoLinhaTO> ofertafixaCreditoScorePersist = new ArrayList<AnaliseCreditoLinhaTO>();

        try {
            log.debug("separando objetos por split (;)");
            if (analiseCreditoLinhaTO.getScoresListRemover() != null && !analiseCreditoLinhaTO.getScoresListRemover().equals("")) {
                montaListaAnaliseCredito(analiseCreditoLinhaTO.getScoresListRemover(), analiseCreditoLinhaTODelete, false);
                for (OfertafixaCreditoScore acl : analiseCreditoLinhaTODelete) {
                    this.analiseCreditoLinhaDAO.excluir(acl);
                }
            }
            if (analiseCreditoLinhaTO.getScoresListAdicionar() != null && !analiseCreditoLinhaTO.getScoresListAdicionar().equals("")) {
                montaListaAnaliseCredito(analiseCreditoLinhaTO.getScoresListAdicionar(), ofertafixaCreditoScorePersist, true);
                for (AnaliseCreditoLinhaTO analiseCred : ofertafixaCreditoScorePersist) {
                    this.analiseCreditoLinhaDAO.gravar(analiseCred);
                }
            }
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void montaListaAnaliseCredito(String array, List tmpList, boolean adicionar) throws DAOException {
        String[] s, aux;
        s = array.split(";");
        log.debug("quantidade de objetos gerado: " + s.length);
        for (String analise : s) {
            aux = analise.split("\\|");
            log.debug("separando atributos do objeto por split (|) para a oferta:" + aux[0].toString() + " e analise(score): " + aux[1].toString());
            if (adicionar) {
                AnaliseCreditoTO act = new AnaliseCreditoTO();
                AnaliseCreditoLinhaTO acl = new AnaliseCreditoLinhaTO();
                act.setIdAnaliseCredito(Integer.parseInt(aux[1].toString()));
                acl.setAnaliseCreditoTO(act);
                acl.setIdOferta(Integer.parseInt(aux[0].toString()));
                tmpList.add(acl);
            } else {
                OfertafixaCreditoScore tmp = this.analiseCreditoLinhaDAO.find(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
                if (tmp != null) {
                    tmpList.add(tmp);
                }
            }
        }
    }

}
