package com.indracompany.catalogo.ejb.servicofixa;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoServicoTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoServicoTO;

@Local
public interface ServicoFixaBeanLocal {

    public static final String JNDI_NAME = "java:comp/env/ServicoFixaBean";
    
    public List<ServicoFixaTO> search(ServicoFixaTO servicoFixaTO) throws BusinessException;

    public String changeStatus(Integer id) throws BusinessException;

    public List<RelacionamentoServicoFixaTO> searchRelationship(RelacionamentoServicoFixaTO relacionamentoServicoFixaTO) throws BusinessException;

    public List<TipoRelacionamentoTO> findTipoRelacionamentoInsertFixa() throws BusinessException;

    public List<TipoServicoTO> findAllTpServico() throws BusinessException;

    public String changeStatusRelationship(Integer idRelacionamento) throws BusinessException;

    public void removeRelationship(Integer idRelacionamento) throws BusinessException;

    public void gravarRelacionamento(List<ServicoServicoTO> servicoServicoTOList);
    
    public Integer findIdSistemaByIdServico(Integer idServico) throws BusinessException;
}
