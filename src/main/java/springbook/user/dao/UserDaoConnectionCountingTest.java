package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                CountingDaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        // DAO 사용 코드
        userDao.get("whiteship");

        // getBean : DL(의존관계 검색)을 사용하면 이름을 이용해 어떤 빈이든 가져올 수 있다.
        CountingConnectionMaker ccm = context.getBean(CountingConnectionMaker.class);
        System.out.println("Connection counter : "+ ccm.getCount());
    }
}
