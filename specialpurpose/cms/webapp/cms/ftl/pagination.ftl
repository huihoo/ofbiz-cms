

<#assign highIndex=(pviewIndex+1)*pviewSize>
<#if listSize gt pviewSize>
<div class="page">
<#assign x=Static["java.lang.Math"].floor(listSize/pviewSize)>
<#if listSize gt (x*pviewSize)><#assign x=x+1></#if>
	<span class="total">${uiLabelMap.total}${x}${uiLabelMap.paginate}</span>
<#if pviewIndex gt 0><a href="/cms/control/ViewCatalog?id=${(chnl.catalogId)!}&pviewIndex=0&pviewSize=${pviewSize}">${uiLabelMap.paginateFirstLabel}</a><#else><span class="disabled">${uiLabelMap.paginateFirstLabel}</span></#if>
<#if pviewIndex gt 0><a href="/cms/control/ViewCatalog?id=${(chnl.catalogId)!}&pviewIndex=${pviewIndex-1}&pviewSize=${pviewSize}">${uiLabelMap.paginatePreviousLabel}</a><#else><span class="disabled">${uiLabelMap.paginatePreviousLabel}</span></#if>

<#if pviewIndex-10 gt 0>
	<#if x gt pviewIndex+4>
		<#list pviewIndex-5..pviewIndex+4 as i>
			<#if i = pviewIndex>
				<span class="current">${i+1}</span>
			<#else>
				<a href="/cms/control/ViewCatalog?id=${(chnl.catalogId)!}&pviewIndex=${i}&pviewSize=${pviewSize}">${i+1}</a>
			</#if>
		</#list>
	<#else>
		<#list x-9..x as i>
			<#if i = pviewIndex>
				<span class="current">${i+1}</span>
			<#else>
				<a href="/cms/control/ViewCatalog?id=${(chnl.catalogId)!}&pviewIndex=${i}&pviewSize=${pviewSize}">${i+1}</a>
			</#if>
		</#list>
	</#if>
<#else>
	<#if x gt 10>
		<#list 0..10 as i>
			<#if i = pviewIndex>
				<span class="current">${i+1}</span>
			<#else>
				<a href="/cms/control/ViewCatalog?id=${(chnl.catalogId)!}&pviewIndex=${i}&pviewSize=${pviewSize}">${i+1}</a>
			</#if>
		</#list>
	<#else>
		<#list 0..(x-1) as i>
			<#if i == pviewIndex>
				<span class="current">${i+1}</span>
			<#else>
				<a href="/cms/control/ViewCatalog?id=${(chnl.catalogId)!}&pviewIndex=${i}&pviewSize=${pviewSize}">${i+1}</a>
			</#if>
		</#list>
	</#if>
</#if>

<#if highIndex lt listSize><a href="/cms/control/ViewCatalog?id=${(chnl.catalogId)!}&pviewIndex=${pviewIndex+1}&pviewSize=${pviewSize}">${uiLabelMap.paginateNextLabel}</a><#else><span class="disabled">${uiLabelMap.paginateNextLabel}</span></#if>
<#if highIndex lt listSize><a href="/cms/control/ViewCatalog?id=${(chnl.catalogId)!}&pviewIndex=${x-1}&pviewSize=${pviewSize}">${uiLabelMap.paginateLastLabel}</a><#else><span class="disabled">${uiLabelMap.paginateLastLabel}</span></#if>
</div>
</#if>




