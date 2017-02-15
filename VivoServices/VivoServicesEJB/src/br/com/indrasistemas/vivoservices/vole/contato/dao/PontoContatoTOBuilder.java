package br.com.indrasistemas.vivoservices.vole.contato.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.indrasistemas.vivoservices.vole.contato.to.PontoContatoTO;

public class PontoContatoTOBuilder {

	public static List buildPontoContatoDDDTO(ResultSet resultSet)
			throws Exception {
		List result = new ArrayList();

		while (resultSet.next()) {
			//PontoContatoTO to = new PontoContatoTO();
			//to.setNrDDD(resultSet.getString("CDAREAREGISTRO"));
			//result.add(to);
			
			String cdAreaRegistro = new String();
			cdAreaRegistro = resultSet.getString("CDAREAREGISTRO");
			result.add(cdAreaRegistro);

		}

		return result;
	}

	public static List buildPontoContatoUFTO(ResultSet resultSet)
			throws Exception {
		List result = new ArrayList();

		while (resultSet.next()) {
			//PontoContatoTO to = new PontoContatoTO();
			//to.setsgUF(resultSet.getString("SGUF"));
			//result.add(to);

			String sgUF = new String();
			sgUF = resultSet.getString("SGUF").toString();
			result.add(sgUF);
		}

		return result;
	}

	public static List buildPontoContatoTO(ResultSet resultSet)
			throws Exception {
		List result = new ArrayList();

		while (resultSet.next()) {
			PontoContatoTO to = new PontoContatoTO();

			to.setIdTipoEmpresa(resultSet.getLong("IDTIPOEMPRESA"));

			result.add(to);

		}

		return result;
	}

	public static List buildPontoContatoGCNTO(ResultSet resultSet)
			throws Exception {
		List result = new ArrayList();

		while (resultSet.next()) {
			PontoContatoTO to = new PontoContatoTO();

			to.setIdTipoEmpresa(resultSet.getLong("IDTIPOEMPRESA"));
			to.setDsConsultorRelacionamento(resultSet
					.getString("DSCONSULTORRELACIONAMENTO"));
			to.setNrTelefoneConsultor(resultSet.getLong("NRTELEFONECONSULTOR"));
			to.setDsEmailConsultor(resultSet.getString("DSEMAILCONSULTOR"));
			to.setNmTecnicoResidente(resultSet.getString("NMTECNICORESIDENTE"));
			to.setNrTelefoneTecnico(resultSet.getLong("NRTELEFONETECNICO"));
			to.setDsEmailTecnico(resultSet.getString("DSEMAILTECNICO"));
			to.setNmGerenteContas(resultSet.getString("NMGERENTECONTAS"));
			to.setNrTelefoneGerContas(resultSet.getLong("NRTELEFONEGERCONTAS"));
			to.setDsEmailGercontas(resultSet.getString("DSEMAILGERCONTAS"));
			to.setDsGAM(resultSet.getString("DSGAM"));
			to.setNrTelefoneGAM(resultSet.getLong("NRTELEFONEGAM"));
			to.setDsEmailGAM(resultSet.getString("DSEMAILGAM"));
			to.setDsGerenteSecao(resultSet.getString("DSGERENTESECAO"));
			to.setNrTelefoneGerSecao(resultSet.getLong("NRTELEFONEGERSECAO"));
			to.setDsEmailGerSecao(resultSet.getString("DSEMAILGERSECAO"));
			to.setDsGerenteDivisao(resultSet.getString("DSGERENTEDIVISAO"));
			to.setNrTelefoneGerDivisao(resultSet
					.getLong("NRTELEFONEGERDIVISAO"));
			to.setDsEmailGerDivisao(resultSet.getString("DSEMAILGERDIVISAO"));
			to.setsgUF(resultSet.getString("SGUF"));

			result.add(to);

		}

		return result;
	}

	public static List buildPontoContatoPMETO(ResultSet resultSet)
			throws Exception {
		List result = new ArrayList();

		while (resultSet.next()) {
			PontoContatoTO to = new PontoContatoTO();

			to.setIdTipoEmpresa(resultSet.getLong("IDTIPOEMPRESA"));
			to.setDsRazaoSocialDealer(resultSet
					.getString("DSRAZAOSOCIALDEALER"));
			to.setNrTelef1(resultSet.getLong("NRTELEF1"));
			to.setNrTelef2(resultSet.getLong("NRTELEF2"));
			to.setDsEmailDealer(resultSet.getString("DSEMAILDEALER"));
			to.setDsCidadeDealer(resultSet.getString("DSCIDADEDEALER"));
			to.setNmGerenteContas(resultSet.getString("NMGERENTECONTAS"));
			to.setNrTelefoneGerContas(resultSet.getLong("NRTELEFONEGERCONTAS"));
			to.setDsEmailGercontas(resultSet.getString("DSEMAILGERCONTAS"));
			to.setsgUF(resultSet.getString("SGUF"));

			result.add(to);
		}

		return result;
	}
}
