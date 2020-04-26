```shell
// 在本地master分支上拉取,获取最新的版本
git pull --rebase

// 在本地建立新的分支(分支要有命名规范)
// 创建分支使用git branch 这里的checkout是切换分支，加上-b则是创建并切换至分支
git checkout -b myBranchName

// 在远程新建一个分支跟踪刚刚建立的本地分支
git push --set-upstream origin myBranchName

// 开发中:
// 切换到自己的分支
git checkout myBranchName

// 修改代码===================================
// 如果没有在IDE中创建文件而是直接创建文件的话
// 需要在命令行中使用git add命令将创建的文件加入到本地仓库中
// 如果是IDE中创建的文件，一般会自动的进行这一步

// 通过git查看本地仓库的变化
git status

// 提交本地所有代码到本地仓库的缓冲中,text为描述性文字
git commit -am "text"
// 或者进行单个文件的提交
git commit filePath -m "text"

// 将本地分支提交到远程分支中(都是指自己的分支,而不是master分支)
git push

// 整个功能开发完成之后,进行分支的合并
// 在网页端进行
```

[Git教程](https://www.cnblogs.com/best/p/7474442.html)

