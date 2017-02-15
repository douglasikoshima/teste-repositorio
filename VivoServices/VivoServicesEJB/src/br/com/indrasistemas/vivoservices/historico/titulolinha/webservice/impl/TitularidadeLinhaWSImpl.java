package br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.impl;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.delegate.TitularidadeLinhaBD;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.ParametrosTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.TitularidadeLinhaTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.TitularidadeLinhaWS;
import br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.to.ListaTitularidadeLinhaWSTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.to.TitularidadeLinhaParametrosWSTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.to.TitularidadeLinhaWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class TitularidadeLinhaWSImpl implements TitularidadeLinhaWS {

	public TitularidadeLinhaWSImpl() {
	}

	public TitularidadeLinhaWSTO pesquisarTitularidadeLinha(RequestInfoTO requestInfo, TitularidadeLinhaParametrosWSTO entrada) {

		TitularidadeLinhaWSTO result = new TitularidadeLinhaWSTO();

		try {

			String nrLinha = Long.toString(entrada.getCdDDD()) + Long.toString(entrada.getNrTelefone());
			String primeiroNome = entrada.getPrimeiroNome() == null || "".equals(entrada.getPrimeiroNome()) ? null : entrada.getPrimeiroNome();
			String ultimoNome = entrada.getUltimoNome() == null || "".equals(entrada.getUltimoNome()) ? null : entrada.getUltimoNome();

			entrada.setPrimeiroNome(primeiroNome);
			entrada.setUltimoNome(ultimoNome);
			
			String nrCPFCNPJ = entrada.getNrCPFCNPJ() == null || "".equals(entrada.getNrCPFCNPJ()) ? null : entrada.getNrCPFCNPJ().replaceAll("[^0-9]", "");

			if (!"00".equals(nrLinha) && (nrLinha.length() < 10 || nrLinha.length()> 11)) {
				result.setStatus(RespostaWSTO.NAO_OK);
				result.setCdError(new Integer(1));
				result.setDsError("Número de linha inválido.");
				
			} else if (entrada.getCdRetorno() == 1 && "00".equals(nrLinha) && nrCPFCNPJ == null) {
				result.setStatus(RespostaWSTO.NAO_OK);
				result.setCdError(new Integer(1));
				result.setDsError("Número de telefone ou número de CPF/CNPJ deve ser informado.");
				
			} else if (primeiroNome != null && ultimoNome == null) {
				result.setStatus(RespostaWSTO.NAO_OK);
				result.setCdError(new Integer(1));
				result.setDsError("O último nome do cliente deve ser informado.");
				
			} else if (ultimoNome != null && primeiroNome == null) {
				result.setStatus(RespostaWSTO.NAO_OK);
				result.setCdError(new Integer(1));
				result.setDsError("O primeiro nome do cliente deve ser informado.");
				
			} else if (entrada.getCdRetorno() == 1 && entrada.getNrCPFCNPJ() == null && !"00".equals(nrLinha) && (nrLinha.length() < 10 || nrLinha.length()> 11)) {
				result.setStatus(RespostaWSTO.NAO_OK);
				result.setCdError(new Integer(1));
				result.setDsError("CPF/CNPJ ou número de linha devem ser informados.");

			} else {
				ParametrosTO parametros = new ParametrosTO();
				parametros.setNrCPFCNPJ(nrCPFCNPJ);
				parametros.setBuscaCompleta(true);
				parametros.setEnderecoPreferencial(entrada.getInPreferencial() == 0 ? false : true);

				// Indicador do tipo de retorno da API:
				// 0 - Somente dados do cliente (valor padrão).
				// 1 - Dados do cliente e histórico de titularidade.
				if (entrada.getCdRetorno() == 0) {
					parametros.setNrRG(entrada.getNrRG() == null || "".equals(entrada.getNrRG()) ? null : entrada.getNrRG().replaceAll("[^0-9]", ""));
					parametros.setPrimeiroNome(entrada.getPrimeiroNome() == null ? null : entrada.getPrimeiroNome().trim());
					parametros.setUltimoNome(entrada.getUltimoNome() == null ? null : entrada.getUltimoNome().trim());
					parametros.setBuscaCompleta(false);
				}

				if (entrada.getCdDDD() != 0) {
					parametros.setCdDDD(entrada.getCdDDD());
					parametros.setNrTelefone(entrada.getNrTelefone());
				}

				TitularidadeLinhaBD bd = new TitularidadeLinhaBD();
				TitularidadeLinhaTO to = bd.pesquisarTitularidadeLinha( requestInfo, parametros);

				result.setCdArea(to.getCdArea());
				result.setNrTelefone(to.getNrTelefone());

				if (to.getLista() != null && to.getLista().length > 0) {
					result.setStatus(RespostaWSTO.OK);
					result.setCdError(new Integer(0));
					result.setDsError("");

					ListaTitularidadeLinhaWSTO[] lista = new ListaTitularidadeLinhaWSTO[to.getLista().length];

					for (int i = 0; i < to.getLista().length; i++) {
						lista[i] = new ListaTitularidadeLinhaWSTO();
						
						lista[i].setNrTelefone(to.getLista()[i].getNrTelefone());
						lista[i].setCdArea(to.getLista()[i].getCdArea());
						
						lista[i].setLinhaAtiva(to.getLista()[i].isLinhaAtiva());
						lista[i].setNrCPFCNPJ(to.getLista()[i].getNrCPFCNPJ());
						lista[i].setDsModalidade(to.getLista()[i].getDsModalidade());
						lista[i].setDsTecnologia(to.getLista()[i].getDsTecnologia());
						lista[i].setNrRG(to.getLista()[i].getNrRG());
						lista[i].setDsOrgaoEmissorRG(to.getLista()[i].getDsOrgaoEmissorRG());
						lista[i].setDtNascimento(to.getLista()[i].getDtNascimento());

						lista[i].setNome(to.getLista()[i].getNome());
						lista[i].setNomeMeio(to.getLista()[i].getNomeMeio());
						lista[i].setSobrenome(to.getLista()[i].getSobrenome());						
						lista[i].setRazaoSocial(to.getLista()[i].getRazaoSocial());

						lista[i].setDsEndereco(to.getLista()[i].getDsEndereco());
						lista[i].setNrCEP(to.getLista()[i].getNrCEP());
						lista[i].setNmMunicipio(to.getLista()[i].getNmMunicipio());
						lista[i].setSgUF(to.getLista()[i].getSgUF());

						lista[i].setDtAtivacao(to.getLista()[i].getDtAtivacao());
						lista[i].setDtDesativacao(to.getLista()[i].getDtDesativacao());
					}

					result.setLista(lista);

				} else {
				    if(entrada.getCdRetorno()==0){
	                    result.setStatus(RespostaWSTO.NAO_OK);
	                    result.setCdError(new Integer(2));
	                    result.setDsError("Linha informada não existe na base do Vivonet.");
	                    result.setReason("Registro não encontrado, linha inexistente.");
	                    
				    }else{
	                    result.setStatus(RespostaWSTO.NAO_OK);
	                    result.setCdError(new Integer(4));
	                    result.setDsError("Histórico de clientes não encontrado para a linha informada");
				    }
				}
			}

		} catch (BusinessDelegateException e) {
			result.setStatus(RespostaWSTO.NAO_OK);
			result.setCdError(new Integer(99));
			result.setDsError(e.getMessage());
			result.setReason(e.getMessage());

		} catch (BusinessException e) {
			result.setStatus(RespostaWSTO.NAO_OK);
			result.setCdError(new Integer(99));
			result.setDsError(e.getMessage());
			result.setReason(e.getMessage());

		} catch (Exception e) {
			result.setStatus(RespostaWSTO.NAO_OK);
			result.setCdError(new Integer(99));
			result.setDsError(e.getMessage());
			result.setReason(e.getMessage());
		}
		return result;
	}
}
