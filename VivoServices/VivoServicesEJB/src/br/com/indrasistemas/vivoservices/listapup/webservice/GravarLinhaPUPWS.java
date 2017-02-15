package br.com.indrasistemas.vivoservices.listapup.webservice;

import java.util.List;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;

public interface GravarLinhaPUPWS {

	public ResultadoLinhaPUPTO gravarLinhaPUP(RequestInfoTO requestInfo,
			Integer cdCanal, Integer cdProcedencia, String sgCanal,
			String sgProcedencia, List nrMIN, Integer cdOperacao,
			Integer inSMSProtocolos, Integer inSMSPromocoes,
			Integer inSMSProdutos, Integer inSMSParceiros,
			Integer inIVRPromocoes, Integer inIVRProdutos,
			Integer inIVRParceiros, Integer inTelmktMsgVoz);

}
