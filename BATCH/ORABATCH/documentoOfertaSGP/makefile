############################################################
#  BATCH......: documentoOfertaSGP
#  Criado por.: Lucas Gomes (29/09/2015)
#  Funcional..: Dener Neves dos Santos
############################################################
APPNAME=documentoOfertaSGP

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
DATA_PRC_DIR=$(EXEC_DIR)/prc
SQL_DIR=$(EXEC_DIR)/sql

clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par

build: documentoOferta $(APPNAME)

uninstall: rm -r -f $(EXEC_DIR)

meuVivoDW: install

install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(DATA_PRC_DIR) ]  || mkdir $(DATA_PRC_DIR)
	[ -d $(SQL_DIR) ]       || mkdir $(SQL_DIR)
	# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg  $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/documentoOfertaSGP.sh                $(EXEC_DIR)/documentoOfertaSGP.sh
	cp -f $(COMPILE_DIR)/documentoOfertaSGP_Apoio.sh          $(EXEC_DIR)/documentoOfertaSGP_Apoio.sh
	cp -f $(COMPILE_DIR)/documentoOfertaSGP_Carga_Arquivos.sh $(EXEC_DIR)/documentoOfertaSGP_Carga_Arquivos.sh
	cp -f $(COMPILE_DIR)/LEIAME.TXT                        $(EXEC_DIR)/LEIAME.TXT
	# COPIA DE ARQUIVOS PL/SQL  E AFINS
	cp -f $(COMPILE_SQL)/DOCUMENTO_OFERTA_SGP_TRUNCATABELA.sql $(SQL_DIR)/DOCUMENTO_OFERTA_SGP_TRUNCATABELA.sql
	cp -f $(COMPILE_SQL)/DOCUMENTO_OFERTA_SGP_CARGA.ctl        $(SQL_DIR)/DOCUMENTO_OFERTA_SGP_CARGA.ctl
	cp -f $(COMPILE_SQL)/DOCUMENTO_OFERTA_SGP_CARGA.par        $(SQL_DIR)/DOCUMENTO_OFERTA_SGP_CARGA.par
	
	# PERMISSOES PARA EXECUCAO
	chmod 777 $(EXEC_DIR)
	# Arquivo cfg
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	# Arquivos shell script
	chmod 755 $(EXEC_DIR)/documentoOfertaSGP.sh
	chmod 755 $(EXEC_DIR)/documentoOfertaSGP_Apoio.sh
	chmod 755 $(EXEC_DIR)/documentoOfertaSGP_Carga_Arquivos.sh
	# Arquivos SQL e afins
	chmod 644 $(SQL_DIR)/DOCUMENTO_OFERTA_SGP_CARGA.ctl
	chmod 644 $(SQL_DIR)/DOCUMENTO_OFERTA_SGP_CARGA.par
	chmod 644 $(SQL_DIR)/DOCUMENTO_OFERTA_SGP_TRUNCATABELA.sql