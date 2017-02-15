APPNAME=expurgoAutomaticoMovel

BATCH_DIR=$(HOME)/BATCH/SINCRONISMO

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/SINCRONISMO

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
LOG_DIR=$(EXEC_DIR)/log


clean:
	rm -r -f $(EXEC_DIR)

build:	expurgoAutomaticoMovel $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

expurgoAutomaticoMovel: install
	
install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ] || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ] || mkdir $(EXEC_DIR)
	[ -d $(LOG_DIR) ] || mkdir $(LOG_DIR)
	# COPIA DE ARQUICOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/createPartitionMovel.sh $(EXEC_DIR)/createPartitionMovel.sh
	cp -f $(COMPILE_DIR)/dropPartitionMovel.sh $(EXEC_DIR)/dropPartitionMovel.sh	
	# PERMISSOES PARA EXECUCAO
	chmod -R 777 $(EXEC_DIR)