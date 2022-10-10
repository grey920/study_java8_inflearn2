package me.whiteship.java8to11.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * container가 가진 리텐션과 타겟 정보는 감싸려는 애노테이션의 것보다 같거나 넓어야 한다
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE_USE )
public @interface ChickenContainer {

    /**
     * 자신이 감싸고 있을 애노테이션을 배열로 가지고 있는다
     * @return
     */
    Chicken[] value();
}
