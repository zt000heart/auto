package com.example;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Date;

import javax.lang.model.element.Modifier;
import javax.xml.crypto.Data;


/**
 * Created by zhangtao21 on 2017/3/24.
 */

public final class Helloworld {

    public static void main(String[] args) {
        System.out.println("Hello, JavaPoet!");
        MethodSpec main = getMethodSpec$S();

        ClassName hover = ClassName.get("com.example.zhangtao21.auto","Bean");
        FieldSpec fieldSpec = FieldSpec.builder(hover,"zhangtao", Modifier.FINAL, Modifier.PUBLIC).build();

        TypeSpec helloWorld = TypeSpec.classBuilder("Hello")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.zhangtao21.test", helloWorld)
                .build();

        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static MethodSpec getMethodMain() {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();
        return main;
    }

    private static MethodSpec getMethodSpecMain() {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addCode(""
                        + "int total = 0;\n"
                        + "for (int i = 0; i < 10; i++) {\n"
                        + "  total += i;\n"
                        + "}\n")
                .build();
        return main;
    }

    private static MethodSpec getMethodSpec$L(){
        int from = 0;
        int to = 10;
        String op = "+";
        return MethodSpec.methodBuilder("test$L")
                .returns(int.class)
                .addStatement("int result = 0")
                .beginControlFlow("for(int i = $L ; i < $L; i++)", from, to)
                .addStatement("result = result $L i", op)
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    private static MethodSpec getMethodSpec$S(){
        String name = "zhangtao";
        return MethodSpec.methodBuilder("test$S")
                .addParameter(int.class, "count")
                .returns(String.class)
                .addStatement("return $S", name)
                .build();
    }

    private static MethodSpec getMethodSpec$T(){
        return MethodSpec.methodBuilder("test$T")
                .returns(Date.class)
                .addStatement("return new $T()",Date.class)
                .build();
    }

    private static MethodSpec getMethodSpec$T2(){
        ClassName hover = ClassName.get("com.example.zhangtao21.auto","Bean");
        return MethodSpec.methodBuilder("test$T2")
                .addModifiers(Modifier.PUBLIC)
                .returns(hover)
                .addStatement("$T bean = new $T($S,$S)", hover, hover, "name", "address")
                .addStatement("return bean")
                .build();
    }

}
