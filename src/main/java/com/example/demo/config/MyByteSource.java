package com.example.demo.config;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 自己定义一个ByteSource并实现序列化接口。
 * 注意：这里我们虽然实现了序列化接口，但只能进行序列化，而不能进行反序列化。这是为什么呢？
 * 我们看看异常  java.io.InvalidClassException: com.example.demo.config.MyByteSource; no valid constructor
 * 实际上就是该类中没有 无参构造函数（因为父类SimpleByteSource中是没有无参构造的，所以导致子类也无法定义无参构造），所以导致反序列化失败。
 * 那么要如何解决呢，实际上就是，将SimpleByteSource的源码全部复制过来，形成一个自己的类，然后在里面加上无参构造
 * */
public class MyByteSource extends CustomizeSimpleByteSource implements Serializable {
    public MyByteSource(byte[] bytes) {
        super(bytes);
    }

    public MyByteSource(char[] chars) {
        super(chars);
    }

    public MyByteSource(String string) {
        super(string);
    }

    public MyByteSource(ByteSource source) {
        super(source);
    }

    public MyByteSource(File file) {
        super(file);
    }

    public MyByteSource(InputStream stream) {
        super(stream);
    }

    public MyByteSource(){
        super();
    }

}
