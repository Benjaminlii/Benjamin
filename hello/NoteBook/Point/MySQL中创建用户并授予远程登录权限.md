# MySQL中创建用户并授予远程登录权限

## 1. 创建用户

```sql
create user 'username'@'host' identified by 'password';
```

## 2. 授予远程登录权限

​		下面的SQL需要在localhost下使用root用户登录执行,当然也可以进行权限的设置,有点麻烦.

​		grant是收益权限的命令,后面紧跟着权限选项(可以用all代替全部权限),databaseName是操作的数据库名,后面是'被授予权限的用户名'@'该用户登录的主机ip'(主机ip可以使用%代替所有).

```sql
grant select,insert,update,delete,create,drop,alter on databaseName.* to 'username'@'host' ;
```

## 3. 中端中远端登录

​		在登录MySQL的命令中添加-h参数紧跟数据库服务器的ip可以连接该服务器上的MySQL数据库.

```shell
mysql -h xx.xxx.xxx.xxx -u username -p password
```

