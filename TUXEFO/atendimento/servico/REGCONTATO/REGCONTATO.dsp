# Microsoft Developer Studio Project File - Name="REGCONTATO" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=REGCONTATO - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "REGCONTATO.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "REGCONTATO.mak" CFG="REGCONTATO - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "REGCONTATO - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "REGCONTATO - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "REGCONTATO - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x416 /d "NDEBUG"
# ADD RSC /l 0x416 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "REGCONTATO - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /FR /YX /FD /GZ /c
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "REGCONTATO - Win32 Release"
# Name "REGCONTATO - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=..\ENCCONTATO\src\cEncContato.cpp
# End Source File
# Begin Source File

SOURCE=..\ENCCONTATO\src\cEncContatoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\FCHIMECONTATO\src\cFchImediatoContato.cpp
# End Source File
# Begin Source File

SOURCE=..\FCHIMECONTATO\src\cFchImediatoContatoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\commons\Collection\src\Collection.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cRegContato.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cRegContatoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\AgrupamentoEstadoTpProc\src\cWFAgrupamentoEstadoTpProc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\AgrupamentoEstadoTpProc\src\cWFAgrupamentoEstadoTpProcPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Andamento\src\cWFAndamento.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoMotivo\src\cWFAndamentoMotivo.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoMotivo\src\cWFAndamentoMotivoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoObservacao\src\cWFAndamentoObservacao.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoObservacao\src\cWFAndamentoObservacaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Andamento\src\cWFAndamentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoProcessoFODPR\src\cWFAndamentoProcessoFODPR.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoProcessoFODPR\src\cWFAndamentoProcessoFODPRPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdEncaminhadoIncorreto\src\cWFAtdEncaminhadoIncorreto.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdEncaminhadoIncorreto\src\cWFAtdEncaminhadoIncorretoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimento.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoAndamentoAtual\src\cWFAtendimentoAndamentoAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoAndamentoAtual\src\cWFAtendimentoAndamentoAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaAtual\src\cWFAtendimentoBaixaAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaAtual\src\cWFAtendimentoBaixaAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaHistorico\src\cWFAtendimentoBaixaHistorico.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaHistorico\src\cWFAtendimentoBaixaHistoricoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoConta\src\cWFAtendimentoConta.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoConta\src\cWFAtendimentoContaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContato\src\cWFAtendimentoContato.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContatoComunic\src\cWFAtendimentoContatoComunic.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContatoComunic\src\cWFAtendimentoContatoComunicPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContato\src\cWFAtendimentoContatoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFechamento\src\cWFAtendimentoFechamento.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFechamento\src\cWFAtendimentoFechamentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaBKO.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaBKOPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaCRI.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaCRIPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaRC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaRCPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrm\src\cWFAtendimentoFrm.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrmCampo\src\cWFAtendimentoFrmCampo.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrmCampo\src\cWFAtendimentoFrmCampoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrm\src\cWFAtendimentoFrmPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGerarXMLDPR\src\cWFAtendimentoGerarXMLDPR.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGrupoAtual\src\cWFAtendimentoGrupoAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGrupoAtual\src\cWFAtendimentoGrupoAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoInBoxBKO.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoInBoxBKOPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoLinha\src\cWFAtendimentoLinha.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoLinha\src\cWFAtendimentoLinhaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoNivel\src\cWFAtendimentoNivel.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoNivel\src\cWFAtendimentoNivelPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoObservacao\src\cWFAtendimentoObservacao.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoObservacao\src\cWFAtendimentoObservacaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoOrigem\src\cWFAtendimentoOrigem.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoOrigem\src\cWFAtendimentoOrigemPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPesquisas.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPesquisasPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPessoa\src\cWFAtendimentoPessoa.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPessoa\src\cWFAtendimentoPessoaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoSkillPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoUsuarioAtual\src\cWFAtendimentoUsuarioAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoUsuarioAtual\src\cWFAtendimentoUsuarioAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\ChamadaAtendimento\src\cWFChamadaAtendimento.cpp
# End Source File
# Begin Source File

SOURCE=..\ChamadaAtendimento\src\cWFChamadaAtendimentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\PPRelatorios\src\cWFPPRelatorios.cpp
# End Source File
# Begin Source File

SOURCE=..\PPRelatorios\src\cWFPPRelatoriosPC.cpp
# End Source File
# Begin Source File

SOURCE=..\REGCONTATOER\src\cWFRegContatoErro.cpp
# End Source File
# Begin Source File

SOURCE=..\REGCONTATOER\src\cWFRegContatoErroPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Usuario\src\cWFUsuarioPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\svcRegContato.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\include\cApoio.h
# End Source File
# Begin Source File

SOURCE=.\include\cMsgErro.h
# End Source File
# Begin Source File

SOURCE=.\include\cRegContato.h
# End Source File
# Begin Source File

SOURCE=.\include\cRegContatoPC.h
# End Source File
# Begin Source File

SOURCE=.\include\stRegContato.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=.\makefile
# End Source File
# Begin Source File

SOURCE=.\xml\REGCONTATO.xml
# End Source File
# Begin Source File

SOURCE=.\xml\RegContato_1.xml
# End Source File
# Begin Source File

SOURCE=.\xml\RegContato_2.xml
# End Source File
# Begin Source File

SOURCE=.\xml\REGCONTATO_direto.xml
# End Source File
# Begin Source File

SOURCE=.\xml\teste.xml
# End Source File
# Begin Source File

SOURCE=.\xml\testecri.xml
# End Source File
# End Group
# Begin Group "ProC"

# PROP Default_Filter "*.pcpp"
# Begin Source File

SOURCE=.\src\cRegContatoPC.pcpp

!IF  "$(CFG)" == "REGCONTATO - Win32 Release"

!ELSEIF  "$(CFG)" == "REGCONTATO - Win32 Debug"

# Begin Custom Build
InputDir=.\src
InputPath=.\src\cRegContatoPC.pcpp
InputName=cRegContatoPC

"$(InputDir)/$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
