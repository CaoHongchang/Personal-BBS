<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>personal</title>
    <link rel="stylesheet" href="/css">
    <link rel="stylesheet" href="/basic.css">
    <link rel="s9tylesheet" href="/combo.css">
    <link rel="stylesheet" href="/desktop.css">
    <link rel="stylesheet" href="/tomorrow.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/basic.css?v=3.9.8.5" />
    <link rel="stylesheet" type="text/css" media="screen" href="/combo.css?v=73463dff4f3cea61f9e00ee1abc4c24a" />
    <link rel="stylesheet" type="text/css" media="screen" href="/desktop.css?v=3.9.8.5" />
</head>
<body>
<div id="Top">
    <div class="content">
        <div class="site-nav">
            <a href="/" name="top" title="way to explore"><div id="Logo"></div></a>
            <div id="search-container">
                <input id="search" type="text" maxlength="128" autocomplete="off" tabindex="1">
                <div id="search-result" class="box"></div>
            </div>
            <div class="tools">
                <a href="/" class="top">首页</a>
                <a href="/register" class="top">注册</a>
                <a href="/login" class="top">登录</a>
            </div>
        </div>
    </div>
</div>

<div id="Wrapper">
    <div class="content">
        <div id="Leftbar"></div>
        <div id="Rightbar">
            <div class="sep20"></div>
            <div class="box">
                <div class="cell"><img src="/static/img/neue_comment.png" width="18" align="absmiddle"> &nbsp;${currentUser.username} 最近的时间轴更新</div>
                <div id="statuses">
                </div>
            </div>
            <div class="sep20"></div>
        </div>
        <div id="Main">
            <div class="sep20"></div>
            <div class="box">
                <div class="cell">
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tbody><tr>
                            <td width="73" valign="top" align="center"><img src="https://cdn.v2ex.com/avatar/6b8a/1cb4/180793_large.png?m=1490326738" class="avatar" border="0" align="default" alt="nutting"><div class="sep10"></div></td>
                            <td width="10"></td>
                            <td width="auto" valign="top" align="left">
                                <div class="fr">
                                </div>
                                <h1 style="margin-bottom: 5px;">${currentUser.username}</h1>
<#--                                <h1 style="margin-bottom: 5px;">nutting</h1>-->
                                <div class="sep10"></div>
                                <span class="gray">个人论坛 第 ${currentUser.id} 号会员<div class="sep5"></div>今日活跃度排名 <a href="/top/dau">12652</a>
</span>
                            </td>
                        </tr>
                        </tbody></table>
                    <div class="sep5"></div>
                </div>
            </div>
            <div class="sep20"></div>
            <div class="box">
                <div class="cell_tabs"><div class="fl"><img src="https://cdn.v2ex.com/avatar/6b8a/1cb4/180793_normal.png?m=1490326738" width="24" style="border-radius: 24px; margin-top: -2px;" border="0"></div><a href="/member/nutting" class="cell_tab_current">${currentUser.username} 创建的所有主题</a><a href="/member/nutting/qna" class="cell_tab">xxxx</a><a href="/member/nutting/tech" class="cell_tab">xxxx</a><a href="/member/nutting/play" class="cell_tab">xxxx</a><a href="/member/nutting/jobs" class="cell_tab">xxxx</a><a href="/member/nutting/deals" class="cell_tab">xxxx</a><a href="/member/nutting/city" class="cell_tab">xxxx</a></div>
                <#list topicModels as t>
                    <div class="cell item" style="">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tbody><tr>
                                <td width="auto" valign="middle"><span class="item_title"><a href="/t/734347#reply13" class="topic-link">${t.title}</a></span>
                                    <div class="sep5"></div>
                                    <span class="topic_info"><div class="votes"></div><a class="node" href="/go/linux">Linux</a> &nbsp;•&nbsp; <strong><a href="/personal/${currentUser.username}">${currentUser.username}</a></strong> &nbsp;•&nbsp; <span title="${t.updatedTime?number_to_datetime?string("HH:mm:ss")}">回复时间:${t.updatedTime?number_to_datetime?string("yyyy-MM-dd")}</span> &nbsp;•&nbsp; 最后回复来自 <strong><a href="/personal/${t.user.username}">${t.user.username}</a></strong></span>
                                </td>
                                <td width="70" align="right" valign="middle">

                                    <a href="/topic/detail/${t.id}" class="count_livid">详情</a>
                                </td>
                            </tr>
                            </tbody></table>
                    </div>

                </#list>

                <div class="inner"><span class="chevron">»</span> <a href="/member/nutting/topics">nutting 创建的更多主题</a></div>
            </div>
            <div class="sep20"></div>
            <div class="box">
                <div class="cell"><span class="gray">${currentUser.username} 最近回复了</span></div>
                <#list topicCommentModels as t>
                    <#list t.topicModels as tt>
                        <div class="dock_area">
                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                <tbody><tr>
                                    <td style="padding: 10px 15px 8px 15px; font-size: 12px; text-align: left;"><div class="fr"><span class="fade" title="${t.updatedTime?number_to_datetime?string("HH:mm:ss")}">回复时间:${t.updatedTime?number_to_datetime?string("yyyy-MM-dd")}</span> </div><span class="gray">回复了 <a href="/personal/${tt.user.username}">${tt.user.username}</a> 创建的主题 <span class="chevron">›</span> <a href="/go/invest">xxxx</a> <span class="chevron">›</span> <a href="/topic/detail/${tt.id}">${tt.title}</a></span></td>
                                </tr>
                                </tbody></table>
                        </div>
                        <div class="inner">
                            <div class="reply_content">${t.content}</div>
                        </div>
                    </#list>
                </#list>
         <div class="inner"><span class="chevron">»</span> <a href="/member/nutting/replies">${currentUser.username} 创建的更多回复</a></div>
            </div>
        </div>
    </div>
    <div class="c"></div>
    <div class="sep20"></div>
</div>
</body>
</html>