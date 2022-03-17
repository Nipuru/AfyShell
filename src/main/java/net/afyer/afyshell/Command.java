package net.afyer.afyshell;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.MapContext;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Nipuru
 * @since 2022/3/17 13:25
 */
public class Command implements TabExecutor {
    //存放全局属性的容器
    Map<String, Object> globalValues = new ConcurrentHashMap<>();
    //存放脚本属性的容器
    MapContext context = new MapContext();
    JexlBuilder builder = new JexlBuilder().namespaces(Util.make(new HashMap<>(), map -> map.put(null, Namespace.class)));
    List<String> sub = Arrays.asList("import", "clear", "init", "lg", "sc", "sxp");
    public Command() {
        globalValues.put("Bukkit", Bukkit.class);
        globalValues.put("Server", Bukkit.getServer());
    }
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args.length == 0) {
            Arrays.asList("/sh import [包名] 导入一个包",
                    "/sh sc [脚本] 执行一串代码，并显示结果",
                    "/sh exp [表达式] 执行一个表达式，并显示结果",
                    "/sh clear 清理脚本容器，里面的所有内容",
                    "/sh init 初始化一个脚本容器，里面全局容器的属性",
                    "/sh lg 展示全局容器，里面存放一些常用的变量")
                    .forEach(sender::sendMessage);
        } else {
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "import" -> {
                    if (args.length < 2) {
                        sender.sendMessage("请输入类");
                        return true;
                    }
                    String clazz = args[1];
                    try {
                        Class<?> c = Class.forName(clazz);
                        context.set(c.getSimpleName(), c);
                        sender.sendMessage(c.getSimpleName() + " 已添加");
                    } catch (ClassNotFoundException e) {
                        sender.sendMessage(clazz + " 不存在");
                    }

                }
                case "clear" -> {
                    context.clear();
                    sender.sendMessage("已清空");
                }
                case "init" -> {
                    context.clear();
                    globalValues.forEach(context::set);
                    sender.sendMessage("已初始化");
                }
                case "lg" -> {
                    sender.sendMessage("全局容器：别名/数据类型");
                    globalValues.forEach((k, v) -> sender.sendMessage(String.format("%s\t\t\t%s", k, v.getClass())));
                }
                case "sc" -> {
                    if (args.length != 2) {
                        sender.sendMessage("请输入正确的参数");
                    }
                    String sc = args[1];
                    sender.sendMessage("执行脚本：" + sc);
                    try {
                        Object execute = builder.create().createScript(sc).execute(context);
                        sender.sendMessage("返回结果：" + execute);
                    } catch (Exception e) {
                        sender.sendMessage("发生错误：" + e.getMessage());
                    }
                }
                case "sxp" -> {
                    if (args.length != 2) {
                        sender.sendMessage("请输入正确的参数");
                    }
                    String exp = args[1];
                    sender.sendMessage("执行表达式：" + exp);
                    try {
                        Object evaluate = builder.create().createExpression(exp).evaluate(context);
                        sender.sendMessage("返回结果：" + evaluate);
                    } catch (Exception e) {
                        sender.sendMessage("发生错误：" + e.getMessage());
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        List<String> options = new ArrayList<>();
        if (args.length == 0) {
            options.addAll(sub);
        } else if (args.length == 1) {
            options.addAll(sub.stream().filter(s -> s.startsWith(args[0].toLowerCase(Locale.ROOT))).collect(Collectors.toList()));
        }
        return options;
    }

//    private List<Class<?>> getClasses() {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        classLoader.getDefinedPackages()
//    }
}
