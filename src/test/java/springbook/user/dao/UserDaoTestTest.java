package springbook.user.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

/*
@Test : JUnit에게 테스트용 메서드임을 알려준다.
public : JUnit 테스트 메소드는 반드시 public 으로 선언
* */
class UserDaoTestTest {
    public static void main(String[] args) {
        JUnitCore.main("springbook.user.dao.UserDaoTestTest");
    }

    @Test
    public void addAndGet() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDao dao = (UserDao) context.getBean("userDao");

//        User user1 = new User("gyumee", "박상철", "springno1");
//        User user2 = new User("leegw700", "이길원", "springno2");
//
//        dao.deleteAll();
//        Assertions.assertThat(dao.getCount()).isEqualTo(0);
//
//        dao.add(user1);
//        dao.add(user2);
//        Assertions.assertThat(dao.getCount()).isEqualTo(2);
//
//        User userget1 = dao.get(user1.getId());
//        Assertions.assertThat(userget1.getId()).isEqualTo(user1.getId());
//        Assertions.assertThat(userget1.getPassword()).isEqualTo(user1.getPassword());
//
//        User userget2 = dao.get(user2.getId());
//        Assertions.assertThat(userget2.getId()).isEqualTo(user2.getId());
//        Assertions.assertThat(userget2.getPassword()).isEqualTo(user2.getPassword());

//        예외 테스트
        dao.deleteAll();
        Assertions.assertThat(dao.getCount()).isEqualTo(0);

        dao.get("unknown_id");

    }
}