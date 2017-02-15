package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.matrizoferta.MatrizOfertaBeanLocal;
import com.indracompany.catalogo.to.MatrizOfertaArquivoCriticaTO;

public class MatrizOfertaDelegate {
    private static Logger log = Logger.getLogger(MatrizOfertaDelegate.class);

    public String obterTodasCriticasPorIdMatrizOfertaArquivoItem(long idMatrizOfertaArquivoItem) {
        log.debug(String.format("idMatrizOfertaArquivoItem:%s", idMatrizOfertaArquivoItem));
        StringBuilder sb = new StringBuilder();
        try {
            List<MatrizOfertaArquivoCriticaTO> matrizOfertaArquivoCriticaTOList = ((MatrizOfertaBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaBeanLocal.JNDI_NAME)).obterMatrizOfertaArquivoCriticaTOPorIdMatrizOfertaArquivoItemList(idMatrizOfertaArquivoItem);
            for (MatrizOfertaArquivoCriticaTO matrizOfertaArquivoCriticaTO : matrizOfertaArquivoCriticaTOList) {
                sb.append(String.format("%s\n", matrizOfertaArquivoCriticaTO.getDscCriticaImportacao()));                
            }
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [obterTodasMatrizOfertaArquivoItemCritica]", e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        
        return sb.toString();
    }

    public String obterTodasCriticasPorIdMatrizOfertaArquivoItemExportacao(Long idMatrizOfertaArquivoItem) {
        log.debug(String.format("idMatrizOfertaArquivoItem:%s", idMatrizOfertaArquivoItem));
        String retorno = "";
        try {
            StringBuilder sb = new StringBuilder();
            List<MatrizOfertaArquivoCriticaTO> matrizOfertaArquivoCriticaTOList = ((MatrizOfertaBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaBeanLocal.JNDI_NAME)).obterMatrizOfertaArquivoCriticaTOPorIdMatrizOfertaArquivoItemList(idMatrizOfertaArquivoItem);
            for (MatrizOfertaArquivoCriticaTO matrizOfertaArquivoCriticaTO : matrizOfertaArquivoCriticaTOList) {
                sb.append(String.format(" %s |", matrizOfertaArquivoCriticaTO.getDscCriticaImportacao()));                
            }
            if(sb.lastIndexOf("|") > 0) {
                retorno = sb.toString().substring(0, sb.lastIndexOf("|"));
            } else {
                retorno = sb.toString();
            }
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [obterTodasMatrizOfertaArquivoItemCritica]", e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        
        return retorno;
    }
}
