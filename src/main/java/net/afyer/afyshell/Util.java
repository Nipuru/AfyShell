package net.afyer.afyshell;

import java.util.function.Consumer;

/**
 * @author Nipuru
 * @since 2022/3/17 20:58
 */
public class Util {
    public static <T> T make(T t, Consumer<T> consumer) {
        consumer.accept(t);
        return t;
    }
}
