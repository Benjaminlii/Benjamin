```shell
// 在本地master分支上拉取,获取最新的版本
git pull --rebase

// 在本地建立新的分支(分支要有命名规范)
git checkout -b feature/${Author}_${功能名称}_${yyMMdd}

// 在远程新建一个分支跟踪刚刚建立的本地分支
git push --set-upstream origin feature/${Author}_${功能名称}_${yyMMdd}

// 开发中:
// 切换到自己的分支
git checkout myBranch

// 修改代码===================================

// 提交本地所有代码到本地仓库的缓冲中,text为描述性文字
git commit -am "text"

// 将本地分支提交到远程分支中(都是指自己的分支,而不是master分支)
git push

// 整个功能开发完成之后,进行分支的合并
// 在网页端进行
```

[Git教程](https://www.cnblogs.com/best/p/7474442.html)

