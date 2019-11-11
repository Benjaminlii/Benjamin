## 1. workbench 设计表阶段各个字段含义

### (1). PK - Belongs to primary key 

​		作为主键

### (2). NN - Not Null 

​		非空

### (3). UQ - Unique index 

​		唯一索引

### (4). BIN - Is binary column 

​		存放二进制数据的列

### (5). UN - Unsigned data type 

​		无符号数据类型（例如-500 to 500替换成0 - 1000,需要整数形数据）

### (6). ZF - Fill up values for that column with 0’s if it is numeric 

​		填充0位（例如指定3位小数，整数18就会变成18.000）

### (7). AI - Auto Incremental 

​		自增长

### (8). G - Generated column 

​		基于其他列的公式生成值的列