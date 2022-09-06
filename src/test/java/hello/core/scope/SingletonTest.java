package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind() {

        // 이 때 PostConstruct 수행
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(SingletoneBean.class);

        SingletoneBean singletoneBean1 = ac.getBean(SingletoneBean.class);
        SingletoneBean singletoneBean2 = ac.getBean(SingletoneBean.class);
        System.out.println("singletoneBean1 = " + singletoneBean1);
        System.out.println("singletoneBean2 = " + singletoneBean2);
        Assertions.assertThat(singletoneBean2).isSameAs(singletoneBean1);
        ac.close();


    }

    @Scope("singleton")
    static class SingletoneBean{
        @PostConstruct
        public void init() {
            System.out.println("SingletoneBean.init");
        }
        @PreDestroy
        public void close() {
            System.out.println("SingletoneBean.close");
        }

    }
}
