import org.ofbiz.service.ServiceUtil;  
import org.ofbiz.base.util.Debug;
/**
 * 新闻采集
 * 
 * @author zepeng xdaidai@163.com
 * @version 1.0
 * @since 2011-9-15 上午01:17:36
 * @param fromdate (String)  :2011-9-14 
 * @param newsSize (Integer) :20
 * http://news.sina.com.cn/china/2011-9-14/index.shtml
 */
 
        def String getHTML(url){
            def htmlSource =  new String(url.toURL().text.getBytes("UTF-8"),"UTF-8")
        }
        def List get() {   
            def htmlSource = getHTML(this.url)
            def list = getNewsList(htmlSource)
            Collections.reverse(list);  
            return list
        }
        def List getNewsList(def htmlSource){
            def list = new LinkedList()
            def i = 0  
            htmlSource.eachLine{   
                if(i>newsSize)return
                if (it ==~testNewsItem){  
                try{
                    def entry = getNewsItem(it) 
                    i++
                    entry += ["index": ""+i]
                    list << entry  
                    Debug.logInfo "${entry.date}\t ${entry.description}\t ${entry.url}",""//\t ${entry.content}
                   }catch(Exception e){}
                }   
            }   
            return list;
        }
        def Map getNewsItem(def item){
              def entry = new LinkedHashMap()   
              def matcher = item =~ regxUrl    
              def url = matcher[0][1].trim()  
                entry += ["url":url]    
                matcher = item =~ regxDesc  
                entry += ["description": matcher[0][1].trim()]
                matcher = item =~ regxDate   
                entry += ["date": matcher[0][1].trim()] 
                def htmlSource = getHTML(host+url)
                entry += ["content": getCon(htmlSource)]
                return entry
        }
        def String getCon(def htmlSource){
            def c = new StringBuffer()
                def isBegin = false
                def isEnd = false
            htmlSource.eachLine(){line ->
                    line = line.trim()
                    if(isBegin && !isEnd){
                        c.append(line).append("\n")
                    }  
                     if(line.indexOf(beginBlock)>-1){
                         isBegin = true
                     }      
                    // ends ends
                    if(isBegin && line.indexOf(endBlock)>-1){
                        isEnd = true
                        line = line.substring(0, line.indexOf(endBlock))
                        c.append(line).append("\n")
                        return c
                    }
                }
                return c
        } 
        
     host = "http://news.sina.com.cn"  
     newsSize = 10
     dt = new Date().format('yyyy-MM-dd').replaceAll("-0","-") 
     url = host + "/china/" + dt + "/index.shtml"
     encoding = 'utf-8'
     testNewsItem = /^.+<a href=\/c\/[^\s]+ .+/
     regxUrl = /(\/c\/[^\s]+) /
     regxDesc =  /.+>([^<]+)<\/a.+/
     regxDate =  /.+\(([^<]+)\)<\/FONT.+/
     beginBlock = "id=\"artibody\">"
     endBlock = "<div"
     newsList = new LinkedList()
        if(context.newsSize!=null){
            newsSize = context.newsSize
            if(context.fromdate){
            	dt = context.fromdate.replaceAll("-0","-") 
            }
            newsList = get()
        }
    result = ServiceUtil.returnSuccess() 
    result.newsList = newsList
	return result