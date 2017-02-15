package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoValidarSenhaTO;

public class ValidarSenhaOutJAXBTOBuilder {

	
	static public ResultadoValidarSenhaTO buildResultadoValidarSenhaTO(Object body) throws JAXBException {

		br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.MsgBodyType out = (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.MsgBodyType) body;

		ResultadoValidarSenhaTO r = new ResultadoValidarSenhaTO();
			
		r.setDtNasc(out.getValidaSenha().getDtNasc());
		r.setCdCodigoRetorno(out.getValidaSenha().getCdCodigoRetorno());
		r.setE_mail(out.getValidaSenha().getEMail());
		r.setNome(out.getValidaSenha().getNome());
		r.setPrimeiroNome(out.getValidaSenha().getPrimeiroNome());
		r.setReason(out.getValidaSenha().getReason());
		r.setSexo(out.getValidaSenha().getSexo());
		//r.setStatCom(Integer.valueOf(out.getValidaSenha().getStatCom()));
		r.setStatus(out.getValidaSenha().getStatus());
		//r.setTipoLinha(Integer.valueOf(out.getValidaSenha().getTipoLinha()));

		return r;

	}	
	
}
