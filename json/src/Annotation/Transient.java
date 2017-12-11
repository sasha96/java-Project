package Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Transient {

}

//    Class<?> cla = ChapterDao.class;
//    Method[] m = cla.getDeclaredMethods();
//        for(Method me : m){
//                if(me.isAnnotationPresent(Transient.class)){
//        Transient ma = me.getAnnotation(Transient.class);
//
//        }
//        }



