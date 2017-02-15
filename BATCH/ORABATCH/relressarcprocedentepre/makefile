############################################################
#  BATCH......: relRessarcProcedentePre
#  Criado por.: Eder Jani Martins (07/05/2015)
#  Funcional..: Erika Brum
############################################################
APPNAME=relressarcprocedentepre

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
PRC_DIR=$(EXEC_DIR)/prc
BAD_DIR=$(EXEC_DIR)/bad
SQL_DIR=$(EXEC_DIR)/sql



clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.lock
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par


build:	relressarcprocedentepre $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


relressarcprocedentepre: install


install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(PRC_DIR) ]       || mkdir $(PRC_DIR)
	[ -d $(BAD_DIR) ]       || mkdir $(BAD_DIR)
	[ -d $(SQL_DIR) ]       || mkdir $(SQL_DIR)
	# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg           $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/relRessarcProcedentePre.sh                 $(EXEC_DIR)/relRessarcProcedentePre.sh
	cp -f $(COMPILE_DIR)/relRessarcProcedentePre_db_functions.sh    $(EXEC_DIR)/relRessarcProcedentePre_db_functions.sh
	cp -f $(COMPILE_DIR)/relRessarcProcedentePre_files_functions.sh $(EXEC_DIR)/relRessarcProcedentePre_files_functions.sh
	cp -f $(COMPILE_DIR)/LEIAME.TXT                                 $(EXEC_DIR)/LEIAME.TXT
	# COPIA DE ARQUIVOS PL/SQL  E AFINS
	cp -f $(COMPILE_SQL)/RELRESSARCPROCEDENTEPRE.ctl              $(SQL_DIR)/RELRESSARCPROCEDENTEPRE.ctl
	cp -f $(COMPILE_SQL)/RELRESSARCPROCEDENTEPRE.par              $(SQL_DIR)/RELRESSARCPROCEDENTEPRE.par
	cp -f $(COMPILE_SQL)/RELRESSARCPROCEDENTEPRE.sql              $(SQL_DIR)/RELRESSARCPROCEDENTEPRE.sql
	
	# PERMISSOES PARA EXECUCAO
	chmod 777 $(EXEC_DIR)
	# Arquivo cfg
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	# Arquivos shell script
	chmod 755 $(EXEC_DIR)/relRessarcProcedentePre.sh
	chmod 755 $(EXEC_DIR)/relRessarcProcedentePre_db_functions.sh
	chmod 755 $(EXEC_DIR)/relRessarcProcedentePre_files_functions.sh
	# Arquivos SQL e afins
	chmod 644 $(SQL_DIR)/RELRESSARCPROCEDENTEPRE.ctl
	chmod 644 $(SQL_DIR)/RELRESSARCPROCEDENTEPRE.par
	chmod 644 $(SQL_DIR)/RELRESSARCPROCEDENTEPRE.sql
