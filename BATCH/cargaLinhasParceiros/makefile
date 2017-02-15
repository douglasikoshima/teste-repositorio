############################################################
#  BATCH......: cargaLinhasParceiros
#  Criado por.: Eder Jani Martins (19/02/2015)
#  Funcional..: Marcia Santana Silva
############################################################
APPNAME=cargaLinhasParceiros

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
SQL_DIR=$(EXEC_DIR)/sql
PRC_DIR=$(EXEC_DIR)/prc
ERR_DIR=$(EXEC_DIR)/err



clean:
	rm -f $(EXEC_DIR)/*.[tT][xX][tT]
	rm -f $(EXEC_DIR)/*.lst
	rm -f $(EXEC_DIR)/*.out
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.err
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par


build:	cargaLinhasParceiros $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


cargaLinhasParceiros: install


install:
	umask 022
	# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(SQL_DIR) ]       || mkdir $(SQL_DIR)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(PRC_DIR) ]       || mkdir $(PRC_DIR)
	[ -d $(ERR_DIR) ]       || mkdir $(ERR_DIR)
	
	# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg        $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/cargaLinhasParceiros.sh                 $(EXEC_DIR)/cargaLinhasParceiros.sh
	cp -f $(COMPILE_DIR)/cargaLinhasParceiros_Arquivo.sh         $(EXEC_DIR)/cargaLinhasParceiros_Arquivo.sh
	cp -f $(COMPILE_DIR)/cargaLinhasParceiros_Carga_Arquivos.sh  $(EXEC_DIR)/cargaLinhasParceiros_Carga_Arquivos.sh
	cp -f $(COMPILE_DIR)/cargaLinhasParceiros_Queries.sh         $(EXEC_DIR)/cargaLinhasParceiros_Queries.sh
	cp -f $(COMPILE_DIR)/LEIAME.TXT                              $(EXEC_DIR)/LEIAME.TXT
	# COPIA DE ARQUIVOS PL/SQL  E AFINS
	cp -f $(COMPILE_SQL)/CARGALIPAR_CARGA_ARQUIVO.ctl $(SQL_DIR)/CARGALIPAR_CARGA_ARQUIVO.ctl
	cp -f $(COMPILE_SQL)/CARGALIPAR_CARGA_ARQUIVO.par $(SQL_DIR)/CARGALIPAR_CARGA_ARQUIVO.par
	cp -f $(COMPILE_SQL)/CARGALIPAR_EXCLUSAO.sql      $(SQL_DIR)/CARGALIPAR_EXCLUSAO.sql
	cp -f $(COMPILE_SQL)/CARGALIPAR_EXCLUSAO_PAR.sql  $(SQL_DIR)/CARGALIPAR_EXCLUSAO_PAR.sql
	cp -f $(COMPILE_SQL)/CARGALIPAR_INCLUSAO.sql      $(SQL_DIR)/CARGALIPAR_INCLUSAO.sql
	cp -f $(COMPILE_SQL)/CARGALIPAR_INCLUSAO_PAR.sql  $(SQL_DIR)/CARGALIPAR_INCLUSAO_PAR.sql
	cp -f $(COMPILE_SQL)/CARGALIPAR_TRUNCATABELA.sql  $(SQL_DIR)/CARGALIPAR_TRUNCATABELA.sql
	
	# PERMISSOES PARA EXECUCAO
	chmod 755 $(EXEC_DIR)
	chmod 755 $(EXEC_LOG) 
	chmod 755 $(ERR_DIR)
	chmod 755 $(SQL_DIR)
	chmod 755 $(PRC_DIR)
	chmod a+rwx $(DATA_DIR)
	# Arquivo cfg
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	# Arquivos shell script
	chmod 755 $(EXEC_DIR)/cargaLinhasParceiros.sh
	chmod 755 $(EXEC_DIR)/cargaLinhasParceiros_Arquivo.sh
	chmod 755 $(EXEC_DIR)/cargaLinhasParceiros_Carga_Arquivos.sh
	chmod 755 $(EXEC_DIR)/cargaLinhasParceiros_Queries.sh
	# Arquivos SQL e afins
	chmod 644 $(SQL_DIR)/CARGALIPAR_CARGA_ARQUIVO.ctl
	chmod 644 $(SQL_DIR)/CARGALIPAR_CARGA_ARQUIVO.par
	chmod 644 $(SQL_DIR)/CARGALIPAR_EXCLUSAO.sql
	chmod 644 $(SQL_DIR)/CARGALIPAR_EXCLUSAO_PAR.sql
	chmod 644 $(SQL_DIR)/CARGALIPAR_INCLUSAO.sql
	chmod 644 $(SQL_DIR)/CARGALIPAR_INCLUSAO_PAR.sql
	chmod 644 $(SQL_DIR)/CARGALIPAR_TRUNCATABELA.sql

