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
                <div class="cell">
                    <strong>个人论坛项目</strong>
                    <div class="sep5"></div>
                    <span class="fade">项目展示</span>
                </div>
                <div class="inner">
                    <div class="sep5"></div>

                    <div align="center"><a href="/personal/cao" class="super normal button">个人主页</a>
                        <div class="sep5"></div>
                        <div class="sep10"></div>
                        <h3>cao</h3>
                    </div>


                </div>
            </div>
            <div class="sep20"></div>
            <div class="box">
                <div class="inner" align="center">
                    <a href="/" target="_blank"><img src="/doge.gif" border="0" width="250" height="250" alt="Dell" style="vertical-align: bottom;"></a>
                </div>
                <div class="sidebar_compliance flex-one-row"><div><a href="/" target="_blank">Dell</a></div><a href="/advertise" target="_blank">广告</a></div>
            </div>
            <div class="sep20"></div>
            <div class="sep20"></div>
        </div>
        <div id="Main">
            <div class="sep20"></div>
            <div class="box" style="border-bottom: 0px;">
                <form action="/topic/add" method="POST">
                <div class="header"><div class="fr"><a href="/personal/${currentUser.username}"><img src="/person1.png" class="avatar" border="0" align="default" alt="redf"></a></div>
                    <a href="/">个人论坛</a> <span class="chevron">&nbsp;›&nbsp;</span> <a href="/?tab=${tab}">${tab}</a> <span class="chevron">&nbsp;›&nbsp;</span> <a href="/personal/${currentUser.username}">你的主页</a>
                    <div class="sep10"></div>
                    <h4>帖子标题</h4>
                    <input type="text" name="title" placeholder="请输入题目">
                    <br/>
                </div>
                <div class="outdated">帖子正文</div>
                <div class="cell">
                    <div class="topic_content"><div class="markdown_body"><p><a target="_blank" rel="nofollow" ></a></p>
                            <input type="text" name="content" placeholder="请输入正文">
                            <input type="hidden" name="tab" value="${tab}">
                        </div>
                        <button type="submit">添加</button>
                    </div>
                </div>

                </form>
            </div>
            <div class="sep20"></div>
            <div class="box">
                <div class="cell"><div class="fr" style="margin: -3px -5px 0px 0px;"><a href="/tag/标题" class="tag"><li class="fa fa-tag"></li> 标题</a><a href="/tag/输入" class="tag"><li class="fa fa-tag"></li> 输入</a><a href="/tag/主题" class="tag"><li class="fa fa-tag"></li> 主题</a></div><span class="gray">11 条回复 &nbsp;<strong class="snow">•</strong> &nbsp;2015-01-12 14:55:03 +08:00</span>
                </div>
                <div id="r_1694421" class="cell">
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tbody><tr>
                            <td width="48" valign="top" align="center"><img src="https://cdn.v2ex.com/avatar/c157/a8d6/84128_normal.png?m=1517710746" class="avatar" border="0" align="default" alt="xinghuan"></td>
                            <td width="10" valign="top"></td>
                            <td width="auto" valign="top" align="left"><div class="fr"> &nbsp; &nbsp; <span class="no">1</span></div>
                                <div class="sep3"></div>
                                <strong><a href="/member/xinghuan" class="dark">xinghuan</a></strong>&nbsp; &nbsp;<span class="ago" title="2015-01-12 00:00:14 +08:00">2015-01-12 00:00:14 +08:00</span>
                                <div class="sep5"></div>
                                <div class="reply_content">回复框没什么。。主题标题我是习惯性的在后面的空白的地方点了几下，然后发现是在下一行输入。。。</div>
                            </td>
                        </tr>
                        </tbody></table>
                </div>
            </div>
            <div class="sep20"></div>
        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>
</div>

<form action="/topic/add" method="POST">
    <input type="text" name="title" placeholder="请输入题目">
    <br>
    <input type="text" name="content" placeholder="请输入正文">
    <br>

    <button type="submit">添加</button>
</form>

<div>

    <#list topics as t>
    <h3>
        <a href="/topic/detail/${t.id}">${t.title}</a>
        <br/>
<#--        ${t.id} : ${t.title}-->
    </h3>
    <a href="/topic/edit?id=${t.id}">编辑</a>
    <a href="/topic/delete?id=${t.id}">删除</a>
</#list>
</div>
</body>
</html>