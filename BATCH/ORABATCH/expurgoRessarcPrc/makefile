############################################################
#  BATCH......: expurgoRessarcPrc
#  Criado por.: Eder Jani Martins (07/05/2015)
#  Alterado por.: Lucas Gomes Fenelon (27/08/2015)
#  Funcional..: Erika Brum
############################################################
APPNAME=expurgoRessarcPrc

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log



clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.lock


build:	expurgoRessarcPrc $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


expurgoRessarcPrc: install


install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg     $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/expurgoRessarcPrc.sh                 $(EXEC_DIR)/expurgoRessarcPrc.sh
	cp -f $(COMPILE_DIR)/expurgoRessarcPrc_files_functions.sh $(EXEC_DIR)/expurgoRessarcPrc_files_functions.sh
	cp -f $(COMPILE_DIR)/LEIAME.TXT                           $(EXEC_DIR)/LEIAME.TXT
	
	# PERMISSOES PARA EXECUCAO
	chmod 777 $(EXEC_DIR)
	# Arquivo cfg
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	# Arquivos shell script
	chmod 755 $(EXEC_DIR)/expurgoRessarcPrc.sh
	chmod 755 $(EXEC_DIR)/expurgoRessarcPrc_files_functions.sh

