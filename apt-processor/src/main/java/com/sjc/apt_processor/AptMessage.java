package com.sjc.apt_processor;

import javax.lang.model.type.TypeMirror;

/**
 * @Author sjc
 * @Date 2020/7/20
 * Description：
 */
public class AptMessage {
    // 注解标记的元素名字
    public String simpleName;
    // 注解标记的元素的类型
    public TypeMirror type;
    // 注解所在的类名
    public String fromClass;
    // 注解所在的类全称 包名+类名
    public String fromClassAll;
    // 注解所在的包名全称
    public String packageName;

    public AptMessage(String simpleName, TypeMirror type, String fromClass, String fromClassAll, String packageName) {
        this.simpleName = simpleName;
        this.type = type;
        this.fromClass = fromClass;
        this.fromClassAll = fromClassAll;
        this.packageName = packageName;
    }
}
