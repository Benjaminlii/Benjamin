# Linux下设置开机自启动

## 1.编写.service文件(或者使用提供好的)

```shell
# vi /etc/systemd/system/myapp.service
----------------------------------------------------
#如果/etc/systemd/system/myapp.service有过修改，需要进行更新，执行下面这个命令
#systemctl daemon-reload
#查看服务是否被正确识别
#systemctl list-unit-files|grep myapp
[Unit]
#服务描述，写有意义的内容，便于识别
Description=myapp service

[Service]
Type=simple
#设置应用的工作目录
WorkingDirectory=/myapp_path
ExecStart=/myapp_path/startMyApp.sh
ExecStop=/bin/kill -s TERM $MAINPID

[Install]
WantedBy=multi-user.target
```

## 2. 查看文件是否识别

```shell
systemctl list-unit-files | grep myapp
```

## 3. 将.service文件复制到/etc/systemd/system目录下

## 4. 将服务设置为开机自启动

```shell
systemctl enable myapp.service
```

## 5. 立即启动系统服务

```shell
systemctl start myapp.service
```

## 5. 查看系统服务是否成功启动

```shell
systemctl status myapp.service -l
```

