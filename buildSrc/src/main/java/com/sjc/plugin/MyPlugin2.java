package com.sjc.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import groovyjarjarasm.asm.ClassReader;

/**
 * @Author sjc
 * @Date 2020/6/24
 * Description
 */
public class MyPlugin2 implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        System.out.println("i am plugin");
        MyExt ext = project.getExtensions().create("myPluginExt", MyExt.class);
        project.afterEvaluate(project1 -> {

            System.out.println(ext.getAaa());
            System.out.println(ext.getBbb());
        });


    }
}
