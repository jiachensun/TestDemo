package com.sjc.apt_processor;

import com.google.auto.service.AutoService;
import com.sjc.apt_annotation.MyAnnotation;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    /**
     * 用于{@link Element}处理的工具类
     */
    private Elements mElementUtils;

    private List<AptMessage> aptMessageList = new ArrayList<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // 添加关注的注解
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(MyAnnotation.class.getCanonicalName());
        return annotationTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 遍历所有标记注解的element
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(MyAnnotation.class);
        if (elementsAnnotatedWith == null || elementsAnnotatedWith.size() <= 0) {
            return false;
        }
        aptMessageList.clear();
        // 加入到数组
        for (Element element : elementsAnnotatedWith) {
            // 得到包信息
            PackageElement packageElement = mElementUtils.getPackageOf(element);
            // 得到类信息
            TypeElement encloseElement = (TypeElement) element.getEnclosingElement();
            AptMessage message = new AptMessage(element.getSimpleName().toString(), element.asType(),
                    encloseElement.getSimpleName().toString(), encloseElement.getQualifiedName().toString(),
                    packageElement.getQualifiedName().toString());
            aptMessageList.add(message);
        }

        if (aptMessageList == null || aptMessageList.size() <= 0) {
            return false;
        }

        // 为每一个注解的类都新建一个java文件
        // 需要生成的类名 key:注解所在类的全称 value:需要生成的class
        Map<String, Object> simpleFromClass = new HashMap<>();
        for (AptMessage message : aptMessageList) {
            // 如果Map中不存在注解来自的类，则新建一个typespec，一个typespec对应一个java文件和一个成员变量和一个初始化方法
            if (!simpleFromClass.containsKey(message.packageName)) {
                // 新建public类
                TypeSpec.Builder classBuilder = TypeSpec.classBuilder("SJCObject_" + message.fromClass).addModifiers(Modifier.PUBLIC);

                // 创建成员变量
                FieldSpec fieldSpec = FieldSpec.builder(ClassName.get("", message.fromClass), "activity").addModifiers(Modifier.PRIVATE).build();
                classBuilder.addField(fieldSpec);

                // 创建初始化方法
                MethodSpec.Builder initMethodBuilder = MethodSpec.methodBuilder("init").addModifiers(Modifier.PUBLIC)
                        .addStatement("this.$N = $N", "activity", "activity")
                        .addParameter(ClassName.get(message.packageName, message.fromClass), "activity");

                Map<Object, Object> classAndMethodBuilder = new HashMap<>();
                classAndMethodBuilder.put(classBuilder, initMethodBuilder);

                // 添加到map中
                simpleFromClass.put(message.packageName, classAndMethodBuilder);
            }

            Map<Object, Object> classAndMethodMap = (Map<Object, Object>) simpleFromClass.get(message.packageName);
            if (classAndMethodMap != null) {
                // 得到初始化方法的builder，添加实例化方法
                Collection<Object> values = classAndMethodMap.values();
                MethodSpec.Builder initMethodBuilder = null;
                for (Object ob : values) {
                    initMethodBuilder = (MethodSpec.Builder) ob;
                }
                if (initMethodBuilder != null) {
                    initMethodBuilder.addCode("this.activity.$N = new $T();\n", message.simpleName, message.type);
                }
            }
        }
        // 遍历整个map，生成java文件
        for (String key : simpleFromClass.keySet()) {
            Map<Object, Object> classAndMethodMap = (Map<Object, Object>) simpleFromClass.get(key);
            Set<Object> keySet = classAndMethodMap.keySet();
            for (Object keySetKey : keySet) {
                TypeSpec.Builder classBuilder = (TypeSpec.Builder) keySetKey;
                MethodSpec.Builder initMethodBuilder = (MethodSpec.Builder) classAndMethodMap.get(classBuilder);
                classBuilder.addMethod(initMethodBuilder.build());

                // 需要生成的文件 所在的包
                JavaFile javaFile = JavaFile.builder(key, classBuilder.build()).build();
                // 写入文件
                try {
                    javaFile.writeTo(processingEnv.getFiler());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}