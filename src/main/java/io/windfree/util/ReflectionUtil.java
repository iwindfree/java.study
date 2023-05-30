package io.windfree.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {
    public void solve(String path, String classname) throws ClassNotFoundException, MalformedURLException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class clazz = new URLClassLoader(
                new URL[]{new File(path).toURI().toURL()}
        ).loadClass(classname);
        Method[] methods = clazz.getDeclaredMethods();
        for(int i = 0; i < methods.length;i++) {
            System.out.println("method name:" + methods[i]);
            List<Integer> args = new ArrayList<>();
            args.add(1);
            args.add(2);
            if(methods[i].getName().equals("testMethod1")) {
                Object cls =  clazz.getConstructor().newInstance();
                methods[i].invoke(cls, args);
            }
            /*
            for(Class parameterType: parameterTypes){
                System.out.println(parameterType.getName());

            }

            Parameter[] parameters = methods[i].getParameters();
            for(Parameter param : parameters) {
                System.out.println(param.getType());
                System.out.println(param.getName());
            }
             */
        }


    }

    public static void main(String[] args) throws Exception {
        new ReflectionUtil().solve("/Users/windfree/workspace/","Test1");

    }
}
