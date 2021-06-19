package java8;

import org.junit.Test;

import java.lang.reflect.Method;

public class TestAnnotation {

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");

        MyAnnotation[] myAnnotations = m1.getAnnotationsByType(MyAnnotation.class);

        for(MyAnnotation myAnnotation : myAnnotations){
            System.out.println(myAnnotation.value());
        }
    }



    @MyAnnotation("hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation("abc") String string){

    }
}
