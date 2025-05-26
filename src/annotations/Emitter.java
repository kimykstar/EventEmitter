package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Single Value Annotation으로 활용(Element가 1개)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Emitter {
}
