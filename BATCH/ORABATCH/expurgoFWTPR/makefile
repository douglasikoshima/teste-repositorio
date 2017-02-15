APPNAME=expurgoFWTPR

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
LOG_DIR=$(EXEC_DIR)/log
PROC_DIR=$(EXEC_DIR)/processado
PEND_DIR=$(EXEC_DIR)/pendente
ERRO_DIR=$(EXEC_DIR)/erro


clean:
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh

build:	inclusaoPR $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

expurgoFWTPR: install
	
install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ] || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ] || mkdir $(EXEC_DIR)
	[ -d $(LOG_DIR) ] || mkdir $(LOG_DIR)
	[ -d $(PEND_DIR) ] || mkdir $(PEND_DIR)
	[ -d $(PROC_DIR) ] || mkdir $(PROC_DIR)
	[ -d $(ERRO_DIR) ] || mkdir $(ERRO_DIR)
	# COPIA DE ARQUICOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/expurgoFWTPR.sh $(EXEC_DIR)/expurgoFWTPR.sh
	#cp -f $(COMPILE_DIR)/pendente/EXCLUSAO* $(PEND_DIR)/
	# PERMISSOES PARA EXECUCAO
	chmod 755 $(EXEC_DIR)/*.cfg
	chmod 755 $(EXEC_DIR)/*.sh
	