package com.example;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

/**
 * Created by zhangtao21 on 2017/3/28.
 */

public class Helloworld2 {
    public static void main(String[] args){
//        test$N();
        genInterface();
    }

    private static void test$N(){
        MethodSpec hexDigit = MethodSpec.methodBuilder("hexDigit")
                .addParameter(int.class, "i")
                .returns(char.class)
                .addStatement("return (char) (i < 10 ? i + 10 : i - 10)")
                .build();

        MethodSpec byteToHex = MethodSpec.methodBuilder("byteToHex")
                .addParameter(int.class, "b")
                .returns(String.class)
                .addStatement("char[] result = new char[2]")
                .addStatement("result[0] = $N(b*10)", hexDigit)
                .addStatement("result[1] = $N(b+3)", hexDigit)
                .addStatement("return new String(result)")
                .build();


        TypeSpec typeSpec = TypeSpec.classBuilder("Hello")
                .addMethod(hexDigit)
                .addMethod(byteToHex)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .build();

        JavaFile javaFile = JavaFile.builder("com.zhangtao21.test",typeSpec)
                .build();

        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void genInterface(){
        TypeSpec helloWorld = TypeSpec.interfaceBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec.builder(String.class, "ONLY_THING_THAT_IS_CONSTANT")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$S", "change")
                        .build())
                .addMethod(MethodSpec.methodBuilder("beep")
                        .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                        .build())
                .build();

        JavaFile javaFile = JavaFile.builder("com.zhangtao21.test",helloWorld)
                .build();

        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
