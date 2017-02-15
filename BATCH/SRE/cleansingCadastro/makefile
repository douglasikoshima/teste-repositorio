APPNAME=cleansingCadastro

BATCH_DIR=$(HOME)/BATCH/SRE

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/SRE

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
LOG_DIR=$(EXEC_DIR)/log
PENDENTE_DIR=$(EXEC_DIR)/pendente
PROCESSADO_DIR=$(EXEC_DIR)/processado

clean:
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.sql
	rm -f $(EXEC_DIR)/*.ctl

build:	cleansingCadastro $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

cleansingCadastro: install
	
install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ] || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ] || mkdir $(EXEC_DIR)
	[ -d $(LOG_DIR) ] || mkdir $(LOG_DIR)
	[ -d $(PENDENTE_DIR) ] || mkdir $(PENDENTE_DIR)
	[ -d $(PROCESSADO_DIR) ] || mkdir $(PROCESSADO_DIR)
	# COPIA DE ARQUICOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/loader.ctl $(EXEC_DIR)/loader.ctl
	cp -f $(COMPILE_DIR)/rejeitados.sql $(EXEC_DIR)/rejeitados.sql
	cp -f $(COMPILE_DIR)/inseridos.sql $(EXEC_DIR)/inseridos.sql
	cp -f $(COMPILE_DIR)/rollback.sql $(EXEC_DIR)/rollback.sql
	cp -f $(COMPILE_DIR)/cleansingCadastro.sh $(EXEC_DIR)/cleansingCadastro.sh	
	cp -f $(COMPILE_DIR)/cleansingRollback.sh $(EXEC_DIR)/cleansingRollback.sh
	# PERMISSOES PARA EXECUCAO
	chmod -R 777 $(EXEC_DIR)