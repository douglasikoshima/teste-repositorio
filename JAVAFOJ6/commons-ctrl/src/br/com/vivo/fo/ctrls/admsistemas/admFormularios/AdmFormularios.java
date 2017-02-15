package br.com.vivo.fo.ctrls.admsistemas.admFormularios;

import javax.ejb.Local;

@Local
public interface AdmFormularios {

    public br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO getClienteFormularioVO(java.lang.String user, br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO filtros) throws java.lang.Exception;

    public br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO setClienteFormularioVO(java.lang.String user, br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO clienteFormularioVO) throws java.lang.Exception;
}
