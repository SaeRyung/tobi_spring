package springbook.user.dao;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import springbook.user.domain.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/*
@Test : JUnit에게 테스트용 메서드임을 알려준다.
public : JUnit 테스트 메소드는 반드시 public 으로 선언
* */
@RunWith(SpringJUnit4ClassRunner.class) // springtest context framework junit 확장 기능 지정
@ContextConfiguration(locations = "/test-applicationContext.xml") // testContext가 자동으로 만들어줄 applicationContext 위치 지정
@DirtiesContext //테스트 메소드에서 applicationContext 구성, 상태 변경을 textContextFramework에 알려줌
public class UserDaoTest {
    //    public static void main(String[] args) {
//        JUnitCore.main("springbook.user.dao.UserDaoTest");
//    }
    @Autowired
    // Autowired로, 테스트 오브젝트 만들어지만 스프링테스트 컨텍스트에 의해 자동으로 값 주입
    private ApplicationContext context;
//    setUp()메소드에서 만드는 오브젝트를 테스트 메소드에서 사용할 수 있도록 인스턴스 변수 선언
    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost:3306/testdb", "root", "mariadb", true);
        //DataSource-> 테스트에서 UserDao가 사용할 DB 오브젝트 직접 생성

        this.userDao = (UserDao) context.getBean("userDao");
        this.user1 = new User("gyumee", "박상철", "springno1");
        this.user2 = new User("leegw700", "이길원", "springno2");
        System.out.println(this.context);
        System.out.println(this);

        userDao.setDataSource(dataSource); //코드 인한 수동 DI
    }

    @Test
    public void Count() throws Exception {
        userDao.deleteAll();
        Assertions.assertThat(userDao.getCount()).isEqualTo(0);

        userDao.add(user1);
        Assertions.assertThat(userDao.getCount()).isEqualTo(1);

        userDao.add(user2);
        Assertions.assertThat(userDao.getCount()).isEqualTo(2);
    }

    @Test
    public void addAndGet() throws Exception {
        userDao.deleteAll();
        Assertions.assertThat(userDao.getCount()).isEqualTo(0);

        userDao.add(user1);
        userDao.add(user2);
        Assertions.assertThat(userDao.getCount()).isEqualTo(2);

        User userget1 = userDao.get(user1.getId());
        Assertions.assertThat(userget1.getId()).isEqualTo(user1.getId());
        Assertions.assertThat(userget1.getPassword()).isEqualTo(user1.getPassword());

        User userget2 = userDao.get(user2.getId());
        Assertions.assertThat(userget2.getId()).isEqualTo(user2.getId());
        Assertions.assertThat(userget2.getPassword()).isEqualTo(user2.getPassword());

    }

    //        예외 테스트
    @Test(expected = EmptyResultDataAccessException.class)
    public void delete() throws Exception {
        userDao.deleteAll();
        Assertions.assertThat(userDao.getCount()).isEqualTo(0);

        userDao.get("unknown_id");
    }
}