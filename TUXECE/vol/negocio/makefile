SUBDIRS=Defines Util Conta Senha Endereco Relacionamento Processo Pessoa Servico Ponto Linha Banco AcessoRapido TipoRecarga Menu Parametro Assunto Conta Email Documento Campanha VOLELinha VOLEEmpresa admatdCmm

all:
	make install_h; \
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make all; \
		cd ..; \
	done

clean:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

build:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		make install; \
		cd ..; \
	done

install_h:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install_h; \
		cd ..; \
	done
