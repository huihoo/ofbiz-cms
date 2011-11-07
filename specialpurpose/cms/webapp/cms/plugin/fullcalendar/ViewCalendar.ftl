<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>calendar</title>

<style type='text/css'>
	#calendar {
		width: 100%; 
		
		}
</style>
<script type="text/javascript" src="/cms/images/fullcalendar/jquery.min.js" ></script>
<link rel='stylesheet' type='text/css' href='/manager/images/fullcalendar/fullcalendar.css' />
<script type='text/javascript' src='/cms/images/fullcalendar/ui.core.js'></script>
<script type='text/javascript' src='/cms/images/fullcalendar/ui.draggable.js'></script>
<script type='text/javascript' src='/cms/images/fullcalendar/fullcalendar.js'></script>
<script type='text/javascript'>

	$(document).ready(function() {
	
		$('#calendar').fullCalendar({
			draggable: true,
			events: 
			[
			<#if eventsList?has_content><#list eventsList as event>
			
					{"id":1,"title":"<#if event.eventNumber=="1">*</#if><#if event.eventNumber=="2">**</#if><#if event.eventNumber=="3">***</#if><#if event.eventNumber=="4">****</#if><#if event.eventNumber=="5">*****</#if>","start":"${event.eventTime?if_exists}","url":"\/control\/channel.event?eventYear=${event.eventYear?if_exists}&eventMonth=${event.eventMonth?if_exists}&eventDay=${event.eventDay?if_exists}"},	
			</#list></#if>

		
			{"id":9999,"title":"z","start":"2011-09-01","url":"url"}
			
			 
			 ] 

			,
			eventDrop: function(event, delta) {
				alert(event.title + ' was moved ' + delta + ' days\n' +
					'(should probably update your database)');
			},
			loading: function(bool) {
				if (bool) $('#loading').show();
				else $('#loading').hide();
			}
		});
		
	});

</script>


</head>
<body>

<div id='calendar'></div>

</body>
</html>
