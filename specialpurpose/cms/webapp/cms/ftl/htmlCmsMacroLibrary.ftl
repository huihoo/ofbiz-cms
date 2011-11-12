


<#--
包含页面
	可以包含当前目录、方案。
eg：
包含当前方案文件：
    <@cms.Include name='head.html' solution='darkBlue'/>
-->
<#macro Include name solution="default" sysType="include">
		<#include "component://cms/webapp/cms/template/${solution}/${sysType}/${name}"/>
</#macro>

<#--
当前位置
homepage：首页显示方式。【1：站点简称；自定义】（默认：'首页'）。
title：内容页显示方式。【0：不显示；1：显示内容标题；自定义】（默认：'正文'）。
split：分割符号。（默认：>)。
target：是否原窗口打开。【0：原窗口打开；1：新窗口打开】（默认：0）。
-->
<#macro Position homepage='${uiLabelMap.FirstPage}' title='0' split='>' target='0' class='' style=''>


            <#if catalog.catalogPath??>
  	           <#assign url = "${(catalog.catalogPath)!}"/>
  	        <#else>
  	           <#assign url = "ViewCatalog?id=${catalog.catalogId}"/>
  	        </#if>
	
        <a href="/cms"<#t/><#if target=='1'> target="_blank"</#if>><#t/>
		<#if homepage=='1'>${(web.shortName)!}<#else>${homepage}</#if></a> ${split?html} <#t/>
		<a href="${(url)!}"<#t/>
		<#if target=='1'> target="_blank"</#if>><#t/>${catalog.catalogName}</a>${split?html} <#t/>

        <#if arti??>
	        <#if title!='0' >${arti.contentTitle}<#else>${uiLabelMap.MainText}</#if><#t/>
        </#if>
</#macro>
<#--
文章列表（自定义内容）
	chnlId：栏目ID。（默认：当前栏目ID）
	searchKey：搜索标题、tags和描述。用于搜索页。（默认空）
	topLevel：固定。【0：所有；1：不固定；2：固定】（默认0）
	orderBy：排序方式。【0：发布时间降序；1：发布时间升序；2：固顶降序；3：置顶降序；4：日点击降序；5：周点击降序；6：月点击降序；7：季点击降序；8：年点击降序；9：总点击降序】（默认0）
	recommend：是否推荐。【0：全部；1：推荐文章】（默认0）

	style：标签内部样式。如果指定sysContent或userContent，则该项无效。【1：普通列表；】（默认1）
-->


<#macro ArticleList catalogId='' typeId='default' viewIndex='0' viewSize='20' linkTarget='1'>

<div class="c1-body">


<#if catalogId==""> 


<#else>
 

        <#assign pviewIndex  =  Static["java.lang.Integer"].valueOf("${viewIndex}").intValue()/> 
        <#assign pviewSize  =  Static["java.lang.Integer"].valueOf("${viewSize}").intValue()/> 
  
               
                
    <#assign inputFields  = Static["org.ofbiz.base.util.UtilMisc"].toMap("catalogId",catalogId,"statusId","CAST_APPROVED")/>
    <#assign service_context = Static["org.ofbiz.base.util.UtilMisc"].toMap("entityName", "CmsArticle", "inputFields", inputFields,"viewSize", pviewSize,"viewIndex", pviewIndex)/>
    <#assign result =dispatcher.runSync("performFindList",service_context)/>
    <#assign articleList  = result.get("list")/>
    <#assign listSize  = result.get("listSize")/>
     

    <#if typeId=="focus"> 


    <#else>

    <#list articleList as item>
        <div class="c1-bline"><div class="fl"><img src="/cms/images/template/${solution}/app/img/textsuff.gif"  border="0"/> 
        <a href="/cms/control/ViewArticle?id=${item.articleId}" title="${item.title}" <#if linkTarget=="1"> target="_blank"</#if>><span style="">${item.title}</span></a></div>
        <div class="fr">${item.releaseDate}</div><div class="clear"></div></div>
    </#list>
    <#if listSize  gt 20><#include "pagination.ftl"/></#if>

    </#if> 



</#if>
</div>



</#macro>


<#macro SearchArticleList >


    <#list searchArticleList as item>
        <div class="c1-bline"><div class="fl"><img src="/cms/images/template/${solution}/app/img/textsuff.gif"  border="0"/> 
        <a href="/cms/control/ViewArticle?id=${item.articleId}" title="${item.title}"><span style="">${item.title}</span></a></div>
        <div class="fr">${item.releaseDate}</div><div class="clear"></div></div>
    </#list>

    <#assign listSize  = searchArticleList.size()/>
    <#if listSize  gt 3><#include "pagination.ftl"/></#if>
    




</#macro>
<#macro AdvertiseList typeId='default' >
    <#assign inputFields  = Static["org.ofbiz.base.util.UtilMisc"].toMap("statusId","ADST_APPROVED")/>
    <#assign service_context = Static["org.ofbiz.base.util.UtilMisc"].toMap("entityName", "CmsAdvertise", "inputFields", inputFields)/>
    <#assign result =dispatcher.runSync("performFindList",service_context)/>
    <#assign advertiseList  = result.get("list")/>
    <#assign listSize  = result.get("listSize")/>
     
    <#list advertiseList as item>
       <a href="/cms/control/${item.url}" title="${item.title}" target='_blank'>
       <#assign format  = item.filePath.substring(item.filePath.indexOf('.'),item.filePath.length)/>
       <#if format == '.swf'>
           <embed src='/cms/images/upload/ad/${item.filePath}' quality='high' width='300' height='260' >
       <#else>
           <img style='border:0' src='/cms/images/upload/ad/${item.filePath}' >
       </#if>
      
      </a>	
    </#list>
    <#if advertiseList?? && advertiseList.size() gt 0>
      <script type="text/javascript">$("#closeDiv").css('display','block'); $("#middlePoint").css('display','block'); </script>
    </#if>



</#macro>



<#macro MsgList ctgId="" recommend="0" orderBy="0"
	isPage="0" count="20" firstResult="0" pageNo="0"
	style="1" inner="0" isLoop="1" sysTpl="1" cssClass="" cssStyle="" custom=[]
	sysContent="0" userContent="" sysPage="0" userPage="">
MsgList
</#macro>


<#macro VoteTopic id="-1"
	style="1" inner="0" sysTpl="1" cssClass="" cssStyle="" custom=[]>
	VoteTopic
</#macro>

<#--
子栏目列表
	id：父栏目ID。（默认：当前栏目ID）
	orderBy：排序方式。【0：优先级升序；1：优先级降序；2：点击次数升序；3：点击次数降序】（默认0）

	

-->
<#macro CatalogList id='' catalogType='article' orderBy='0' linkTarget='0' >
    <#if orderBy=='0'>
       <#assign orderByList = Static["org.ofbiz.base.util.UtilMisc"].toList("catalogId")/>
    <#else>
       <#assign orderByList = Static["org.ofbiz.base.util.UtilMisc"].toList("-catalogId")/>
    </#if> 
    
    
    <#if id==''>
    
<#--
    <#assign inputFields  = Static["org.ofbiz.base.util.UtilMisc"].toMap("websiteId",'${web.websiteId}')/>
    <#assign service_context = Static["org.ofbiz.base.util.UtilMisc"].toMap("entityName", "CmsCatalog", "inputFields", inputFields)/>
    <#assign result =dispatcher.runSync("performFindList",service_context)/>
    <#assign catalogList  = result.get("list")/>
-->   


    
    <#assign inputFields  = Static["org.ofbiz.base.util.UtilMisc"].toMap("websiteId",'${web.websiteId}')/>
    <#assign catalogList = delegator.findByAndCache("CmsCatalog", inputFields, orderByList)>
    
    
  	    <#list catalogList as c>
  	    
  	        <#if c.catalogPath??>
  	           <#assign url = "${(c.catalogPath)!}"/>
  	        <#else>
  	           <#assign url = "ViewCatalog?id=${c.catalogId}"/>
  	        </#if>
            <#nested c,url>
            
        </#list>
	<#else>
		
	</#if>
 
</#macro>
