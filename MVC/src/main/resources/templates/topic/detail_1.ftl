<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>Topic</title>
    <link rel="stylesheet" href="/css">
    <link rel="stylesheet" href="/basic.css">
    <link rel="s9tylesheet" href="/combo.css">
    <link rel="stylesheet" href="/desktop.css">
    <link rel="stylesheet" href="/tomorrow.css">
    <link rel="dns-prefetch" href="https://static.v2ex.com/" />
    <link rel="dns-prefetch" href="https://cdn.v2ex.com/" />
    <link rel="dns-prefetch" href="https://i.v2ex.co/" />
    <link rel="dns-prefetch" href="https://www.google-analytics.com/" />
    <link rel="stylesheet" type="text/css" media="screen" href="/basic.css?v=3.9.8.5" />
    <link rel="stylesheet" type="text/css" media="screen" href="/combo.css?v=73463dff4f3cea61f9e00ee1abc4c24a" />
    <link rel="stylesheet" type="text/css" media="screen" href="/desktop.css?v=3.9.8.5" />

</head>
<body>

<div id="Wrapper">
    <div class="content">
        <div id="Leftbar"></div>

        <div id="Rightbar">
            <div class="sep20"></div>
            <div class="box">
                <div class="cell"><img src="/static/img/neue_comment.png" width="18" align="absmiddle"> &nbsp;${user.username} 最近的时间轴更新</div>
                <div id="statuses">
                </div>
            </div>
            <div class="sep20"></div>
        </div>

        <div id="Main">
            <div class="sep20"></div>
            <div class="box" style="border-bottom: 0px;">
                <div class="header"><div class="fr"><a href="/member/ouxch"><img src="https://cdn.v2ex.com/avatar/59bf/ead1/494925_large.png?m=1592282273" class="avatar" border="0" align="default" alt="ouxch"></a></div>
                    <a href="/">个人论坛</a> <span class="chevron">&nbsp;›&nbsp;</span> <a href="/personal/${user.username}">${user.username}的主页</a>
                    <div class="sep10"></div>
                    <h1>${topicWithComments.title}</h1>
                    <div id="topic_736035_votes" class="votes">
                </div>
                <div class="cell">
                    <div class="topic_content"><div class="markdown_body">
                            <p>${topicWithComments.content}</p>

                        </div></div>
                </div>
            </div>
            <div class="sep20"></div>
            <div class="box">
                <div class="cell"><div class="fr" style="margin: -3px -5px 0px 0px;"><a href="/tag/mapper" class="tag"><li class="fa fa-tag"></li> mapper</a><a href="/tag/mybatis" class="tag"><li class="fa fa-tag"></li> mybatis</a><a href="/tag/SQL" class="tag"><li class="fa fa-tag"></li> SQL</a><a href="/tag/玩意儿" class="tag"><li class="fa fa-tag"></li> 玩意儿</a></div><span class="gray">${topicWithComments.commentList?size} 条回复 &nbsp;<strong class="snow">•</strong> &nbsp;帖子创建时间</span>
                </div>
                <#list topicWithComments.commentList as c>
                    <div id="r_9933533" class="cell">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tbody><tr>
                                <td width="48" valign="top" align="center"><img src="https://cdn.v2ex.com/gravatar/a82b94968d8eaaba9043d3b71705af56?s=48&amp;d=retro" class="avatar" border="0" align="default" alt="shinelogo"></td>
                                <td width="10" valign="top"></td>
                                <td width="auto" valign="top" align="left"><div class="fr"> &nbsp; &nbsp; </div>
                                    <div class="sep3"></div>
                                    <strong><a href="/member/shinelogo" class="dark">${c.user.username}</a></strong>&nbsp; &nbsp;<div class="fr"><span class="fade" title="${c.updatedTime?number_to_datetime?string("HH:mm:ss")}">回复时间:${c.updatedTime?number_to_datetime?string("yyyy-MM-dd")}</span> </div>
                                    <div class="sep5"></div>
                                    <div class="reply_content">${c.content}</div>
                                </td>
                            </tr>
                            </tbody></table>
                    </div>

                </#list>


            </div>
            <div class="sep20"></div>
        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>- 
</div>


<#--    <h1>${topicWithComments.title}</h1>-->
<#--    <p>-->
<#--        ${topicWithComments.content}-->
<#--    </p>-->
<#--    <form action="/topicComment/add" method="post">-->
<#--        <input type="text" name="topicId" value="${topicId}" hidden>-->
<#--        <input type="text" name="content" placeholder="请输入正文">-->
<#--        <br>-->
<#--        <button type="submit">评论</button>-->
<#--    </form>-->

<#--    <#list topicWithComments.commentList as c>-->
<#--        ${c.id}: ${c.user.username}:${c.content}<a href="/topicComment/edit?id=${c.id}&topicId=${topicId}">编辑</a>:<a href="/topicComment/delete?id=${c.id}&topicId=${topicId}">删除</a>-->
<#--        <br>-->

<#--    </#list>-->

</body>
</html>