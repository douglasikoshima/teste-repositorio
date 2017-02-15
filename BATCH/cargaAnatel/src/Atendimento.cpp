#include "../include/Atendimento.hpp"

Atendimento::Atendimento() {
	log.setNivel(2);
	memset(idAtendimentoAnatel,0,sizeof(idAtendimentoAnatel));
	memset(nrSolicitacao,0,sizeof(nrSolicitacao));
	memset(dsTipoAtendimentoAnatel,0,sizeof(dsTipoAtendimentoAnatel));
	memset(dsServico,0,sizeof(dsServico));
	memset(dsModalidade,0,sizeof(dsModalidade));
	memset(dsMotivo,0,sizeof(dsMotivo));	
	memset(dsSubMotivo,0,sizeof(dsSubMotivo));
	memset(dsCategoria,0,sizeof(dsCategoria));
	memset(dsSubCategoria,0,sizeof(dsSubCategoria));
	memset(nrTelefoneProblema,0,sizeof(nrTelefoneProblema));
	memset(idAtendimentoAnatelArquivo,0,sizeof(idAtendimentoAnatelArquivo));	
}

Atendimento::~Atendimento() {

}

char* Atendimento::getNrCpfCnpjAssinante() {
	return this->nrCpfCnpjAssinante;
}

char* Atendimento::getNmAssinante() {
	return this->nmAssinante;
}
		
void Atendimento::setNrCpfCnpjAssinante(char *nrCpfCnpjAssinante) {
	if (nrCpfCnpjAssinante != NULL) {
		strcpy(this->nrCpfCnpjAssinante, nrCpfCnpjAssinante);
	}
}

void Atendimento::setNmAssinante(char *nmAssinante) {
	if (nmAssinante != NULL) {
		strcpy(this->nmAssinante, nmAssinante);
	}
}
		
char* Atendimento::getIdAtendimentoAnatel() {
	return this->idAtendimentoAnatel;
}

char* Atendimento::getNrSolicitacao() {
	return this->nrSolicitacao;
}

char* Atendimento::getDsTipoAtendimentoAnatel() {
	return this->dsTipoAtendimentoAnatel;
}

char* Atendimento::getDsServico() {
	return this->dsServico;
}

char* Atendimento::getDsModalidade() {
	return this->dsModalidade;
}

char* Atendimento::getDsMotivo() {
	return this->dsMotivo;
}

char* Atendimento::getDsSubMotivo() {
	return this->dsSubMotivo;
}

char* Atendimento::getDsCategoria() {
	return this->dsCategoria;
}

char* Atendimento::getDsSubCategoria() {
	return this->dsSubCategoria;
}

char* Atendimento::getIdAtendimentoAnatelArquivo() {
	return this->idAtendimentoAnatelArquivo;
}

void Atendimento::setIdAtendimentoAnatel(char *idAtendimentoAnatel) {
	if (idAtendimentoAnatel != NULL) {
		strcpy(this->idAtendimentoAnatel, idAtendimentoAnatel);
	}
}

void Atendimento::setNrSolicitacao(char *nrSolicitacao) {
	if (nrSolicitacao != NULL) {
		strcpy(this->nrSolicitacao, nrSolicitacao);
	}
}

void Atendimento::setDsTipoAtendimentoAnatel(char *dsTipoAtendimentoAnatel) {
	if (dsTipoAtendimentoAnatel != NULL) {
		strcpy(this->dsTipoAtendimentoAnatel, dsTipoAtendimentoAnatel);
	}
}

void Atendimento::setDsServico(char *dsServico) {
	if (dsServico != NULL) {
		strcpy(this->dsServico, dsServico);
	}
}

void Atendimento::setDsModalidade(char *dsModalidade) {
	if (dsModalidade != NULL) {
		strcpy(this->dsModalidade, dsModalidade);
	}
}

void Atendimento::setDsMotivo(char *dsMotivo) {
	if (dsMotivo != NULL) {
		strcpy(this->dsMotivo, dsMotivo);
	}
}

void Atendimento::setDsSubMotivo(char *dsSubMotivo) {
	if (dsSubMotivo != NULL) {
		strcpy(this->dsSubMotivo, dsSubMotivo);
	}
}

void Atendimento::setDsCategoria(char *dsCategoria) {
	if (dsCategoria != NULL) {
		strcpy(this->dsCategoria, dsCategoria);
	}
}

void Atendimento::setDsSubCategoria(char *dsSubCategoria) {
	if (dsSubCategoria != NULL) {
		strcpy(this->dsSubCategoria, dsSubCategoria);
	}
}

char* Atendimento::getNrTelefoneProblema() {
	return this->nrTelefoneProblema;
}

void Atendimento::setNrTelefoneProblema(char *nrTelefoneProblema) {
	if (nrTelefoneProblema != NULL) {
		strcpy(this->nrTelefoneProblema, nrTelefoneProblema);
	}
}

void Atendimento::setIdAtendimentoAnatelArquivo(char *idAtendimentoAnatelArquivo) {
	if (idAtendimentoAnatelArquivo != NULL) {
		strcpy(this->idAtendimentoAnatelArquivo, idAtendimentoAnatelArquivo);
	}
}