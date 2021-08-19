package Reflection;

import Parse.Members;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        Class c = Members.class;

        // Members 의 메서드
        Method[] m = c.getMethods();

        // Members 의 필드
        Field[] f = c.getFields();

        // Members 의 생성자
        Constructor[] cs = c.getConstructors();

        // Members 의 인터페이스
        Class[] inter = c.getInterfaces();

        // Members 의 수퍼클래스
        Class superClass = c.getSuperclass();
    }
}
