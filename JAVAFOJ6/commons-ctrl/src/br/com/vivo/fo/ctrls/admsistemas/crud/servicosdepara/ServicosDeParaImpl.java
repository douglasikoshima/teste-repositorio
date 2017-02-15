package br.com.vivo.fo.ctrls.admsistemas.crud.servicosdepara;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.constantes.ConstantesCRM;

@Stateless(name = "ServicosDePara", mappedName = "ServicosDePara")
@Local(ServicosDePara.class)
@Session(ejbName = "ServicosDePara", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ServicosDeParaImpl implements ServicosDePara {

	static final long serialVersionUID = 155484545452291L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.crud.servicosdepara.ServicosDeParaControl servicosDeParaControl;

	/**
	 * @common:operation
	 */
	public br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara[] buscarServicos() throws Exception {
		return servicosDeParaControl.getServicosDePara();
	}

	/**
	 * @common:operation
	 */
	public br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara buscarServico(long idServico) throws Exception {
		return servicosDeParaControl.getServicosDeParaByIdPlano(idServico);
	}

	/**
	 * @common:operation
	 */
	public void salvarServico(br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara servicoDePara) throws Exception {

		if (servicoDePara == null || servicoDePara.getCdPlano() == null || ConstantesCRM.SVAZIO.equals(servicoDePara.getCdPlano()) || servicoDePara.getDsPlano() == null || ConstantesCRM.SVAZIO.equals(servicoDePara.getDsPlano())) {
			throw new Exception("Parâmetros de entrada inválidos");
		}

		if (servicoDePara.getIdPlano() == 0) {
			servicosDeParaControl.addServicosDePara(servicoDePara.getCdPlano(), servicoDePara.getDsPlano(), servicoDePara.getCdPlanoAtlys(), servicoDePara.getInAtivo());
		} else {
			servicosDeParaControl.updateServicosDePara(servicoDePara.getIdPlano(), servicoDePara.getCdPlano(), servicoDePara.getDsPlano(), servicoDePara.getCdPlanoAtlys(), servicoDePara.getInAtivo());
		}
		return;
	}

	/**
	 * @common:operation
	 */
	public void excluirServico(long idServico) throws Exception {
		servicosDeParaControl.deleteServicosDePara(idServico);
	}
}