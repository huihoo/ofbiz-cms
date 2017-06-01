<script src="/images/jquery/plugins/elrte-1.3/js/elrte.min.js" type="text/javascript"></script>
<script src="/images/jquery/plugins/elrte-1.3/js/i18n/elrte.zh_CN.js" type="text/javascript"></script>
<link rel="stylesheet" href="/images/jquery/plugins/elrte-1.3/css/elrte.full.css" type="text/css">
<script type="text/javascript">
 
        $().ready(function() {
          var opts = {
              lang         : 'zh_CN',   
              styleWithCSS : false,
              height       : 400,
              allowSource  : false,
              toolbar      : 'maxi'
          };
        // create editor
        $('#EditCmsArticle_content').elrte(opts);
          
      //elRTE.prototype.options.toolbars.jiasuduToolbar = [];
      //$().ready(function() {
      //    var opts = {
      //        lang         : 'zh_CN',   
      //        styleWithCSS : false,
      //        height       : 400,
      //        toolbar      : 'jiasuduToolbar'      
      //    }; 
          // toolbar      :web2pyToolbar
//tiny: only buttons to change text style (bold, italic, underline, strike, subscript, superscript)
//compact: the same as tiny + save, undo/redo, text alignment, list, link, fullscreen
//normal: compact + copy/paste, colors, paddings, block-elemet, images
//complete: normal + text size, style and font formating
//maxi: complete + tables
         
 
         // or this way
         // var editor = new elRTE(document.getElementById('EditCmsArticle_content'), opts);
     });



</script>