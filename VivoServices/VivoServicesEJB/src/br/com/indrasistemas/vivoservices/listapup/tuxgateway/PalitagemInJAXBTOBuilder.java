package br.com.indrasistemas.vivoservices.listapup.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.listapup.to.LinhasAssociadasTO;
import br.com.indrasistemas.vivoservices.listapup.to.ParametrosLinhaTO;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.MsgBodyType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.ObjectFactory;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.ArvoreAtendimentoType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.CanalVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.ContasType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.LinhasAssociadasVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.PessoaVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.ProcedenciaVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.UsuarioLinhaVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.ArvoreAtendimentoType.CarterizacaoVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.ArvoreAtendimentoType.SegmentacaoVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.ContasType.ContaVOType;
import br.com.indrasistemas.vivoservices.listapup.tuxgateway.entrada.AtendimentoVOType.ContasType.ContaVOType.LinhaVOType;

public class PalitagemInJAXBTOBuilder {

	ObjectFactory factory = new ObjectFactory();

	static public MsgBodyType buildREGCONTATOxmlIn(ParametrosLinhaTO to)
			throws JAXBException {

		ObjectFactory factory = new ObjectFactory();
		MsgBodyType corpoInB = factory.createMsgBodyType();
		AtendimentoVOType corpoIn = factory
				.createAtendimentoVOType();

		corpoIn.setIdLinhaAtendimento(to.getIdLinhaTelefonica().intValue());
		corpoIn.setIdUfOperadora(to.getIdUFOperadora().intValue());
		corpoIn.setIdTipoLinha(to.getIdTipoLinha().intValue());
		corpoIn.setIdGrupoAbertura(1);
		corpoIn.setInResponsavelAbertura(1);
		corpoIn.setInTipoPessoa((to.getSgTipoPessoa() == null
				|| "".equals(to.getSgTipoPessoa()) ? "PF" : to
				.getSgTipoPessoa()));

		String cdDDD = to.getCdAreaRegistro().toString();
		String nrTelefone = cdDDD + to.getNrLinha().toString();
		corpoIn.setNrTelefone(Long.parseLong(nrTelefone));

		corpoIn.setTpOperacao(1); // 1 = fechar, 2 = encaminhar

		LinhaVOType linha = factory
				.createAtendimentoVOTypeContasTypeContaVOTypeLinhaVOType();
		linha.setIdPessoaLinhaHistorico(to.getIdLinhaTelefonica().intValue());
		ContaVOType conta = factory
				.createAtendimentoVOTypeContasTypeContaVOType();
		conta.setLinhaVO(linha);
		conta.setIdConta(Long.parseLong(to.getIdConta().toString()));
		ContasType contas = factory.createAtendimentoVOTypeContasType();
		contas.setContaVO(conta);
		corpoIn.setContas(contas);

		PessoaVOType pessoa = factory
				.createAtendimentoVOTypePessoaVOType();
		pessoa.setIdPessoa(to.getIdPessoaDePara().intValue());

		corpoIn.setPessoaVO(pessoa);

		UsuarioLinhaVOType usuarioLinha = factory
				.createAtendimentoVOTypeUsuarioLinhaVOType();
		usuarioLinha.setIdPessoa(to.getIdPessoaDePara().intValue());

		corpoIn.setUsuarioLinhaVO(usuarioLinha);
		
		ArvoreAtendimentoType arvoreAtendimento = factory
				.createAtendimentoVOTypeArvoreAtendimentoType();
		arvoreAtendimento.setNmPath(to.getNmPath());

		CarterizacaoVOType carteirizacao = factory
				.createAtendimentoVOTypeArvoreAtendimentoTypeCarterizacaoVOType();
		carteirizacao.setIdTipoCarteira(to.getIdTipoCarteira().intValue());

		SegmentacaoVOType segmentacao = factory
				.createAtendimentoVOTypeArvoreAtendimentoTypeSegmentacaoVOType();
		segmentacao.setIdSegmentacao(to.getIdSegmentacao().intValue());

		arvoreAtendimento.setSegmentacaoVO(segmentacao);
		arvoreAtendimento.setCarterizacaoVO(carteirizacao);

		corpoIn.setArvoreAtendimento(arvoreAtendimento);
		corpoIn.setObservacao("");

		ProcedenciaVOType procedencia = factory
				.createAtendimentoVOTypeProcedenciaVOType();
		procedencia.setIdProcedencia(to.getIdProcedencia().intValue());
		corpoIn.setProcedenciaVO(procedencia);

		corpoIn.setIdChamadaTelefonica(1);

		CanalVOType canal = factory.createAtendimentoVOTypeCanalVOType();
		canal.setIdCanal(to.getIdCanal().intValue());
		corpoIn.setCanalVO(canal);

		LinhasAssociadasTO linhaAssociada = null;

		if (to.getLinhasAssociadas() != null) {
			for (int i = 0; i < to.getLinhasAssociadas().size(); i++) {
				LinhasAssociadasVOType linhasAssociadas = factory
						.createAtendimentoVOTypeLinhasAssociadasVOType();
				linhaAssociada = new LinhasAssociadasTO();
				linhaAssociada = (LinhasAssociadasTO) to.getLinhasAssociadas()
						.get(i);
				linhasAssociadas.setCdConta(linhaAssociada.getCdConta());
				linhasAssociadas.setNrTelefone(linhaAssociada.getNrTelefone());
				corpoIn.getLinhasAssociadasVO().add(linhasAssociadas);
			}
		}

		corpoInB.setAtendimentoVO(corpoIn);

		return corpoInB;
	}

}