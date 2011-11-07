
<style>
.PageNum {
	MARGIN: 5px 0px;
	TEXT-ALIGN: left
}

.PageNum B {
	BORDER-RIGHT: #c9d7f1 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #c9d7f1 1px solid;
	PADDING-LEFT: 5px;
	BACKGROUND: url(../images/link_bg2.gif) repeat-x;
	PADDING-BOTTOM: 2px;
	MARGIN: 10px 0px 10px 2px;
	BORDER-LEFT: #c9d7f1 1px solid;
	COLOR: #c9d7f1;
	LINE-HEIGHT: 20px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #c9d7f1 1px solid
}

.PageNum A {
	BORDER-RIGHT: #4272db 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #4272db 1px solid;
	PADDING-LEFT: 5px;
	BACKGROUND: url(../images/link_bg1.gif) repeat-x;
	PADDING-BOTTOM: 2px;
	MARGIN: 10px 0px 10px 2px;
	BORDER-LEFT: #4272db 1px solid;
	LINE-HEIGHT: 20px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #4272db 1px solid
}

.PageNum A:link {
	COLOR: #4272db
}

.PageNum A:visited {
	COLOR: #4272db
}

.PageNum A:hover {
	BACKGROUND: url(../images/link_bg2.gif) repeat-x;
	COLOR: #c9d7f1
}


</style>
<#if requestAttributes.uiLabelMap?exists><#assign uiLabelMap = requestAttributes.uiLabelMap></#if>
<#macro pagination totalCount pageSize>   
       
    <#assign pageCount=getpageCount(totalCount,pageSize)>   

    <#if requestParameters.pager_offset!?length == 0>
		<#assign pageIndex=1> 
	<#else>
		<#assign pageIndex=requestParameters.pager_offset>  
	</#if>

    <#if !pageIndex?number?is_number>
		&#21830;&#19994;&#26234;&#33021;${requestParameters.pager_offset+requestParameters.pager_offset?is_number?string}
		<#return>
	<#else>
		<#assign pageIndex=pageIndex?number> 
	</#if>

    <#assign spl="&">  
	<#if (request.queryString!"")?index_of("pager_offset") == 0>  
		<#assign spl="">  
	</#if>

	<#assign querys=(request.queryString!"")?replace(spl+"pager_offset="+requestParameters.pager_offset!"", "")+spl+"pager_offset=">
    <#assign new_uri=request.requestURI+"?"+querys>  

    <#if pageIndex gt pageCount>   
        <#assign pageIndex=pageCount>   
    </#if>   
    <#assign uri=request.requestURI+"?"+request.queryString> 
    <#if pageIndex gt 1>   
        <a href="${new_uri+1}" title="&#39318;&#39029;">&lt;&lt;</a>   
    </#if>   
       
    <#if pageIndex gt 5>   
        <#assign prevPages=pageIndex-9>   
        <#if prevPages lt 1>   
            <#assign prevPages=1>   
        </#if>   
        <#assign start=pageIndex-4>   
        <a href="${new_uri+prevPages}" title="&#19978;5&#39029;">...</a>   
    <#else>   
        <#assign start=1>   
    </#if>   
       
    <#assign end=pageIndex+4>   
    <#if end gt pageCount>   
        <#assign end=pageCount>   
    </#if>   
    <#list start..end as index>   
        <#if pageIndex==index>   
            <b>${index}</b>   
        <#else>   
            <a href="${new_uri+index}">${index}</a>   
        </#if>   
    </#list>   
       
    <#if end lt pageCount>   
        <#assign endend=end+5>   
        <#if end gt pageCount>   
            <#assign end=pageCount>   
        </#if>   
        <a href="${new_uri+end}" title="&#19979;5&#39029;">...</a>   
    </#if>   
       
    <#if pageIndex lt pageCount>   
        <a href="${new_uri+pageCount}" title="&#23614;&#39029;">&gt;&gt;</a>   
    </#if>   
</#macro>  
<#function getpageCount totalCount pageSize>
  <#return totalCount / pageSize + eCount(totalCount,pageSize)>
</#function>
<#function eCount totalCount pageSize>
    <#if (totalCount % pageSize == 0)>  
		<#return 0>
	<#else>   
		<#return 1>
	</#if>   
</#function>
<DIV class=PageNum>
			
			<@pagination 1000 50/>
		</div>

