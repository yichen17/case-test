package com.yichen.casetest.test.basetype;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author Qiuxinchao
 * @date 2022/12/7 16:55
 * @describe 序列化测试
 */
@Slf4j
public class SerializeTest {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setAge(20);
        person.setName("Joe");

        FileOutputStream fileOutputStream
                = new FileOutputStream("E:\\yourfile.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream("E:\\yourfile.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        Person p2 = (Person) objectInputStream.readObject();
        objectInputStream.close();

        log.info("{}", p2.getAge() == person.getAge());
        log.info("{}", p2.getName().equals(person.getName()));
    }

}

@Data
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    static String country = "ITALY";
    private int age;
    private String name;
    transient int height;
}
