# AfyShell
字符串代码执行工具

- 设计的目的是为了能在游戏内使用指令执行一串代码 并显示返回结果 方便各种调试  
- 所有的CommandSender公用同一个脚本容器，也就是说不同CommandSender之间会相互干预脚本容器内存放的属性
- 脚本容器内存放的属性在服务器关闭时即丢失

### 命令列表
```
/sh import [包名]              #导入一个包 
/sh sc [脚本]                  #执行一串代码，并显示结果
/sh exp [表达式]               #执行一个表达式，并显示结果
/sh clear                     #清理脚本容器，里面的所有内容
/sh init                      #初始化一个脚本容器，里面全局容器的属性
/sh lg                        #展示全局容器，里面存放一些常用的变量
```

### 快速入门
```
/sh import org.bukkit.Bukkit                           #导入Bukkit类
/sh sc p=Bukkit.getPlayer("Royce_Nipuru")              #获取一个玩家Player对象并存入变量p
/sh sc p.setFlySpeed(1.0f)                             #设置玩家的飞行速度

/sh import org.bukkit.entity.Sheep                     #导入Sheep类
/sh sc s=p.getWorld().spawn(p.getLocation(),Sheep)     #刷出一个羊实体并将其对象存入s
/sc sc s.addPassenger(p)                               #让玩家p骑在羊s头上

/sc clear                                              #清空脚本容器，会将脚本中产生的变量清除，以及导入的包清除，例如上述代码中产生的p、s变量，Bukkit、Sheep包
```
### 常用函数
```
player("name")                #返回名为name的玩家Player对象  
world("name")                 #返回名为name的世界World对象
clazz("packageName")          #获取指定包名的Class对象(eg. clazz("org.bukkit.entity.Sheep"))
```