package br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.DadosEnderecoType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.DocumentoType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.MsgBodyType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.ObjectFactory;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.TelefoneType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.MsgBodyType.PortabilidadeTOType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.MsgBodyType.PortabilidadeTOType.PFType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.MsgBodyType.PortabilidadeTOType.PJType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.DadosTO;

public class PortClienteInJAXBTOBuilder {

	static public MsgBodyType buildPORTCLIENTExmlIn(DadosTO to)
			throws JAXBException {

		ObjectFactory factory = new ObjectFactory();
		MsgBodyType corpoInB = factory.createMsgBodyType();

		PortabilidadeTOType corpoIn = factory
				.createMsgBodyTypePortabilidadeTOType();

		corpoIn.setTpOperacao(to.getTpOperacao());
		corpoIn.setNrLinha(to.getNrLinha());
		corpoIn.setSgTipoPessoa(to.getSgTipoPessoa());
		corpoIn.setSgTipoLinha(to.getSgTipoLinha());
		corpoIn.setIdPessoa(to.getIdPessoa());

		if (to.getDadosPF() != null) {

			PFType dPF = factory.createMsgBodyTypePortabilidadeTOTypePFType();
			dPF.setNmPessoa(to.getDadosPF().getNmPessoa());
			dPF.setSgSexo(to.getDadosPF().getSgSexo());
			dPF.setDtNascimento(to.getDadosPF().getDtNascimento());
			dPF.setSgEstadoCivil(to.getDadosPF().getSgEstadoCivil());
			dPF.setNrCPR(to.getDadosPF().getNrCPR());

			TelefoneType tPF = factory.createTelefoneType();
			tPF.setDsTipoTelefone(to.getDadosPF().getTelefone()
					.getDsTipoTelefone());
			tPF.setNrTelefone(to.getDadosPF().getTelefone().getNrTelefone());
			tPF.setNmContato(to.getDadosPF().getTelefone().getNmContato());
			dPF.setTelefone(tPF);

			DocumentoType docPF = factory.createDocumentoType();
			docPF.setDsTipoDocumento(to.getDadosPF().getDocumento()
					.getDsTipoDocumento());
			docPF.setNrDocumento(to.getDadosPF().getDocumento()
					.getNrDocumento());
			docPF.setDtExpedicao(to.getDadosPF().getDocumento()
					.getDtExpedicao());
			docPF.setDsOrgaoEmissor(to.getDadosPF().getDocumento()
					.getDsOrgaoEmissor());
			docPF.setSgUFDocumento(to.getDadosPF().getDocumento()
					.getSgUFDocumento());
			dPF.setDocumento(docPF);

			if (to.getDadosPF().getEndereco() != null) {
				DadosEnderecoType endPF = factory.createDadosEnderecoType();
				endPF.setSgTipoEndereco(to.getDadosPF().getEndereco()
						.getSgTipoEndereco());
				endPF.setNmTipoLogradouro(to.getDadosPF().getEndereco()
						.getNmTipoLogradouro());
				endPF.setNmTitLogradouro(to.getDadosPF().getEndereco()
						.getNmTitLogradouro());
				endPF.setNrCEP(to.getDadosPF().getEndereco().getNrCEP());
				endPF.setNmLogradouro(to.getDadosPF().getEndereco()
						.getNmLogradouro());
				endPF.setNrLogradouro(to.getDadosPF().getEndereco()
						.getNrLogradouro());
				endPF.setNmComplemento(to.getDadosPF().getEndereco()
						.getNmComplemento());
				endPF.setNmBairro(to.getDadosPF().getEndereco().getNmBairro());
				endPF.setNmMunicipio(to.getDadosPF().getEndereco()
						.getNmMunicipio());
				endPF.setSgUF(to.getDadosPF().getEndereco().getSgUF());
				dPF.setDadosEndereco(endPF);
			}

			corpoIn.setPF(dPF);

		} else if (to.getDadosPJ() != null) {

			PJType dPJ = factory.createMsgBodyTypePortabilidadeTOTypePJType();
			dPJ.setNmRazaoSocial(to.getDadosPJ().getNmRazaoSocial());
			dPJ.setNmFantasia(to.getDadosPJ().getNmFantasia());
			dPJ.setDtFundacao(to.getDadosPJ().getDtFundacao());
			dPJ.setSgClassTributaria(to.getDadosPJ().getSgClassTributaria());
			dPJ.setSgClassEmpresa(to.getDadosPJ().getSgClassEmpresa());
			dPJ.setNrCNPJ(to.getDadosPJ().getNrCNPJ());
			dPJ.setNrCNAE(to.getDadosPJ().getNrCNAE());
			dPJ.setNrCCM(to.getDadosPJ().getNrCCM());
			dPJ.setNrInscricao(to.getDadosPJ().getNrInscricao());
			dPJ.setSgTipoInscricao(to.getDadosPJ().getSgTipoInscricao());

			// br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.MsgBodyType.PortabilidadeTOType.PJType.TelefoneType
			// telPJ = factory
			// .createMsgBodyTypePortabilidadeTOTypePJTypeTelefoneType();

			br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.TelefoneType telPJ = factory
					.createTelefoneType();

			telPJ.setDsTipoTelefone(to.getDadosPJ().getTelefone()
					.getDsTipoTelefone());
			telPJ.setNrTelefone(to.getDadosPJ().getTelefone().getNrTelefone());
			telPJ.setNmContato(to.getDadosPJ().getTelefone().getNmContato());
			dPJ.setTelefone(telPJ);

			if (to.getDadosPJ().getEndereco() != null) {
				br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.entrada.DadosEnderecoType endPJ = factory
						.createDadosEnderecoType();
				endPJ.setSgTipoEndereco(to.getDadosPJ().getEndereco()
						.getSgTipoEndereco());
				endPJ.setNmTipoLogradouro(to.getDadosPJ().getEndereco()
						.getNmTipoLogradouro());
				endPJ.setNmTitLogradouro(to.getDadosPJ().getEndereco()
						.getNmTitLogradouro());
				endPJ.setNrCEP(to.getDadosPJ().getEndereco().getNrCEP());
				endPJ.setNmLogradouro(to.getDadosPJ().getEndereco()
						.getNmLogradouro());
				endPJ.setNrLogradouro(to.getDadosPJ().getEndereco()
						.getNrLogradouro());
				endPJ.setNmComplemento(to.getDadosPJ().getEndereco()
						.getNmComplemento());
				endPJ.setNmBairro(to.getDadosPJ().getEndereco().getNmBairro());
				endPJ.setNmMunicipio(to.getDadosPJ().getEndereco()
						.getNmMunicipio());
				endPJ.setSgUF(to.getDadosPJ().getEndereco().getSgUF());
				dPJ.setDadosEndereco(endPJ);
			}
			corpoIn.setPJ(dPJ);
		}

		corpoInB.setPortabilidadeTO(corpoIn);

		return corpoInB;
	}
}