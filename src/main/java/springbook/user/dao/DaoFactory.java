package springbook.user.dao;
// 객체 생성 방법 결정, 결정한 오브젝트 돌려주는 기능

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
// 애플리케이션 컨텍스트 or 빈 팩토리가 사용할 설정 정보라는 표시
public class DaoFactory {
        // 팩토리 메서드는 UserDao 타입의 오브젝트를 어떻게 만들고, 어떻게 준비시킬지 결정한다.
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();

//        ds.setDriverClass(com.mysql.cj.jdbc.Driver.class);
//        ds.setUrl("jdbc:mysql://localhost:3306/tobi");
//        ds.setUsername("root");
//        ds.setPassword("mariadb");

        return ds;
    }


    @Bean
    // 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
    public UserDao userDao() {
//        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao();
//        userDao.setConnectionMaker(connectionMaker());
        userDao.setDataSource(dataSource());
        return userDao;
//        return new UserDao(connectionMaker());
    }
    // 새로운 기능 AccountDao, MessageDao 추가

    public AccountDao accountDao(){
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao(){
        return new MessageDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        // 분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성 코드
        return new DConnectionMaker();
    }
}
