package me.whiteship.java8to11.annotation;

import javax.lang.model.element.NestingKind;
import java.lang.annotation.*;

@Retention( RetentionPolicy.RUNTIME ) // retention 전략: 이 애노테이션 정보를 언제까지 유지할 것인가
@Target( ElementType.TYPE_USE )
@Repeatable( ChickenContainer.class ) // 애노테이션을 중복으로 사용하고 싶은 경우, 여러개의 애노테이션을 감싸고 있을 컨테이너 타입을 여기에 선언해야 한다
public @interface Chicken {

    String value();
}
