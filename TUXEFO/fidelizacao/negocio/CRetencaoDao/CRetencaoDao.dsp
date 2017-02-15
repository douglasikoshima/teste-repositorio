# Microsoft Developer Studio Project File - Name="CRetencaoDao" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=CRetencaoDao - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "CRetencaoDao.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "CRetencaoDao.mak" CFG="CRetencaoDao - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "CRetencaoDao - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "CRetencaoDao - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

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
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib  kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib  kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Ignore_Export_Lib 0
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ  /c
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ  /c
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib  kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 cpputil.obj orautil.obj fidutil.obj kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# SUBTRACT LINK32 /pdb:none

!ENDIF 

# Begin Target

# Name "CRetencaoDao - Win32 Release"
# Name "CRetencaoDao - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=.\src\CApoioRet.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CApoioRet.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CApoioRet.pcpp
InputName=CApoioRet

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CCaractOfAceita.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CCaractOfAceita.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CCaractOfAceita.pcpp
InputName=CCaractOfAceita

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\COfertaRealizada.cpp
# End Source File
# Begin Source File

SOURCE=.\src\COfertaRealizada.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\COfertaRealizada.pcpp
InputName=COfertaRealizada

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencao.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencao.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencao.pcpp
InputName=CRetencao

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoAnalise.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoAnalise.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoAnalise.pcpp
InputName=CRetencaoAnalise

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoAparelho.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoAparelho.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoAparelho.pcpp
InputName=CRetencaoAparelho

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoBonus.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoBonus.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoBonus.pcpp
InputName=CRetencaoBonus

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoConsolidada.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoConsolidada.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoConsolidada.pcpp
InputName=CRetencaoConsolidada

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoLinha.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoLinha.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoLinha.pcpp
InputName=CRetencaoLinha

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoMigracao.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoMigracao.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoMigracao.pcpp
InputName=CRetencaoMigracao

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoPlano.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoPlano.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoPlano.pcpp
InputName=CRetencaoPlano

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoPontos.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoPontos.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoPontos.pcpp
InputName=CRetencaoPontos

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoSuspensao.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CRetencaoSuspensao.pcpp

!IF  "$(CFG)" == "CRetencaoDao - Win32 Release"

!ELSEIF  "$(CFG)" == "CRetencaoDao - Win32 Debug"

# Begin Custom Build
InputPath=.\src\CRetencaoSuspensao.pcpp
InputName=CRetencaoSuspensao

"src\$(InputName).cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp code=cpp src\$(InputName).pcpp

# End Custom Build

!ENDIF 

# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\include\CApoioRet.h
# End Source File
# Begin Source File

SOURCE=.\include\CCaractOfAceita.h
# End Source File
# Begin Source File

SOURCE=.\include\CCoreRetencao.h
# End Source File
# Begin Source File

SOURCE=.\include\COfertaRealizada.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencao.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoAnalise.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoAparelho.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoBonus.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoConsolidada.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoLinha.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoMigracao.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoPlano.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoPontos.h
# End Source File
# Begin Source File

SOURCE=.\include\CRetencaoSuspensao.h
# End Source File
# Begin Source File

SOURCE=.\include\utilretencao.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# End Group
# End Target
# End Project
