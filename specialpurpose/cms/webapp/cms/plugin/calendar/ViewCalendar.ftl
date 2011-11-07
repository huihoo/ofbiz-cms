<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head xmlns="">
<meta content="text/html; charset=gb2312" http-equiv="Content-Type" />
	<script type="text/javascript" src="/cms/images/calendar/mootools-1.2.3.js"></script>
	<script type="text/javascript" src="/cms/images/calendar/mootools.more-1.2.3.1.js"></script>
	<script type="text/javascript" src="/cms/images/calendar/mooECal-CN.js"></script>
    <link rel="stylesheet" type="text/css" id="calStyle" href="/cms/images/calendar/mooECal.css">
	
	<script type="text/javascript">
	   window.addEvent('domready',function(){
		
		new Calendar({
			calContainer:'calBody',
			cEvents: [ 
            <#list calendarList as calendar>
            
        <#if calendar.eventNumber=="1"> <#assign eventNumber = "<FONT color=red>★</FONT>"></#if>
	  	<#if calendar.eventNumber=="2"> <#assign eventNumber="<FONT color=red>★</FONT><FONT color=blue>★</FONT>"></#if>
	  	<#if calendar.eventNumber=="3"> <#assign eventNumber="<FONT color=red>★</FONT><FONT color=blue>★</FONT><FONT color=yellow>★</FONT>"></#if>
	  	<#if calendar.eventNumber=="4"> <#assign eventNumber="<FONT color=red>★</FONT><FONT color=blue>★</FONT><FONT color=yellow>★</FONT><FONT color=red>★</FONT>"></#if>
	  	<#if calendar.eventNumber=="5"> <#assign eventNumber="<FONT color=red>★</FONT><FONT color=blue>★</FONT><FONT color=yellow>★</FONT><FONT color=red>★</FONT><FONT color=red>★</FONT>"></#if>	  
        
            
 				{ 
					title:'<a href="ViewCalendarEventsDetail?startTime=${calendar.startTime}">${(eventNumber)!}</a>', 
					start:'${calendar.startTime}', 
					end:'${calendar.startTime}', 
					location:'${(StringUtil.wrapString(calendar.content))!}' 
				},
             </#list>
 
				

				{
					title:'Bedtime',
					start:'2025-12-01',
					end:'2025-12-01',
					location:'systemover'
				}
			]	
		});
	})
	</script>
	<div id="calBody"></div>


</html>


