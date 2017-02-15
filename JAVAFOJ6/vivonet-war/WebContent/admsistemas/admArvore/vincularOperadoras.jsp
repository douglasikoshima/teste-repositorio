<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_vo_verpagina">
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script>
        
        document.body.style.backgroundColor = '#ededed';    
        
        function initPagina() { }
        
        var oneSelect = 0;
        var linha ="";
        function rowOver(obj){
            cell = obj.childNodes;
            for(i=0;i<obj.childNodes.length;i++)
                cell[i].className='tblDinamica_linhaOver';
        }
        
        function rowClick(obj,tipo){
            cell = obj.childNodes;
            if(oneSelected==0){
                for(i=0;i<obj.childNodes.length;i++){
                    cell[i].className='tblDinamica_linhaSelected';
                }
                oneSelected = 1;
            }else{
                oneSelected = 0;
                if(tipo==0){
                    rowOutEven(obj);
                }else{
                    rowOutOdd(obj);
                }
            }
        }
        
        function rowOutEven(obj){
            cell = obj.childNodes;
            for(i=0;i<obj.childNodes.length;i++)
                cell[i].className='tblDinamica_linhapar';
        }
        
        function rowOutOdd(obj){
            cell = obj.childNodes;
            for(i=0;i<obj.childNodes.length;i++)
                cell[i].className='tblDinamica_linhaimpar';
        }
        
        function mostraVigencia(opcao){
            
            calendario = document.getElementById('calVigencia');
            calendInput = document.getElementById('operAte');
            
            if (opcao == 'Calendário') {
                calendario.style.visibility = 'visible';                
            } else {
                calendInput.value = '';
                calendario.style.visibility = 'hidden';
            }
        }

    </script>

        <table width="740">
            <tr>
                <td width="395">
                    <vivo:quadro width="395" height="90" label="Regionais Existentes" id="regExist">
                        <table width="385" height="84">
                            <tr>
                                <td colspan="2" height="19" width="127" valign="top">
                                    <b>Data de Vigência</b>
                                </td>                                
                                <td width="166" rowspan="3">
                                    <netui:select dataSource="{}" multiple="true" style="width:152px" styleClass="SELECT" size="4">
                                        <netui:selectOption value="BA"/>
                                        <netui:selectOption value="PR"/>
                                        <netui:selectOption value="RS"/>
                                    </netui:select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" height="22" valign="top">
                                    De <input type="text" id="operDe" name="operDe" style='width:70px' maxlength="10"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('operDe', '%d/%m/%Y');">
                                </td>
                            </tr>
                            <tr>
                                <td height="23">
                                    At&eacute;  <netui:select dataSource="{}" multiple="false" style="width:93px;" onChange="mostraVigencia(this.value)">
                                                    <netui:selectOption value="Indeterminado" onClick="alert('oi');"/>
                                                    <netui:selectOption value="Calend&aacute;rio"/>
                                                </netui:select>
                                </td>
                                <td width="92">                                
                                    <div id="calVigencia" style="visibility:hidden;width:84px;"><input type="text" id="operAte" name="operDe" style='width:60px' maxlength="10"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('operAte', '%d/%m/%Y');"></div>
                                </td>
                            </tr>
                         
                            
                        </table>
                    </vivo:quadro>
                </td>
                <td width="62" align="right">
                    <img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"/><br><br>
                    <img id="btLeftSimp" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"/>
                </td>
                <td width="283">
                    <vivo:quadro label="Regionais Associadas" width="283" height="90" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="4">
                                        <netui:selectOption value="RS/ES"/>
                                        <netui:selectOption value="SP"/>
                                    </netui:select>
                                </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        
        <table width="740">
            <tr>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="adm_vo_salvar">
                    <img id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"/>
                </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>