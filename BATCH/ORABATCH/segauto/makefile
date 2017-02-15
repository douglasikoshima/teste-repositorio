APPNAME=segauto

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
DIARIO_DIR=$(EXEC_DIR)/diario


clean:
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.sql


build:	segauto $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

segauto: install
	
install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ] || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ] || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ] || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ] || mkdir $(DATA_DIR)
	[ -d $(DIARIO_DIR) ] || mkdir $(DIARIO_DIR)
	# COPIA DE ARQUICOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/segauto.sh $(EXEC_DIR)/segauto.sh
	cp -f $(COMPILE_DIR)/segauto_AtualizaParametros.sh $(EXEC_DIR)/segauto_AtualizaParametros.sh
	cp -f $(COMPILE_DIR)/segauto_VerificaBatchComErro.sh $(EXEC_DIR)/segauto_VerificaBatchComErro.sh
	cp -f $(COMPILE_DIR)/segauto_exclusaoPos.sql $(EXEC_DIR)/segauto_exclusaoPos.sql
	cp -f $(COMPILE_DIR)/segauto_exclusaoPre.sql $(EXEC_DIR)/segauto_exclusaoPre.sql
	cp -f $(COMPILE_DIR)/segauto_inclusaoPos.sql $(EXEC_DIR)/segauto_inclusaoPos.sql
	cp -f $(COMPILE_DIR)/segauto_inclusaoPre.sql $(EXEC_DIR)/segauto_inclusaoPre.sql
	
	# PERMISSOES PARA EXECUCAO
	chmod -R 755 $(EXEC_DIR)
	chmod 755 $(EXEC_DIR)/orabatch.cfg
	chmod 755 $(EXEC_DIR)/segauto.sh
	chmod 755 $(EXEC_DIR)/segauto_AtualizaParametros.sh
	chmod 755 $(EXEC_DIR)/segauto_VerificaBatchComErro.sh
	chmod 644 $(EXEC_DIR)/segauto_exclusaoPos.sql
	chmod 644 $(EXEC_DIR)/segauto_exclusaoPre.sql
	chmod 644 $(EXEC_DIR)/segauto_inclusaoPos.sql
	chmod 644 $(EXEC_DIR)/segauto_inclusaoPre.sql
