package com.example.demo.reflect;

import java.lang.reflect.*;

public class ProxyExample {
    public static void main(String[] args) {
        // 创建一个实现 Runnable 接口的代理对象，并委托给 CustomInvocationHandler 处理方法调用
        Runnable proxy = (Runnable) Proxy.newProxyInstance(
                ProxyExample.class.getClassLoader(),
                new Class[] { Runnable.class },
                new CustomInvocationHandler(new TargetObject())
        );

        // 调用代理对象的 run() 方法，实际上会委托给 CustomInvocationHandler 处理
        proxy.run();
    }
}

class CustomInvocationHandler implements InvocationHandler {
    private final Object target;

    public CustomInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("After invoking " + method.getName());
        return result;
    }
}

class TargetObject implements Runnable {
    @Override
    public void run() {
        System.out.println("Running target object's run() method");
    }
}
