package springbook.user.dao;
// 새로운 의존관계를 컨테이너가 사용할 설정 정보
// CountingConnectionMaker 의존관계가 추가된 DI 설정용 클래스

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao();
//        return new UserDao(ConnectionMaker());
        // 모든 DAO는 여전히 connectionMaker()에서 만들어지는 오브젝트를 DI 받는다.
    }
    @Bean
    public ConnectionMaker ConnectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }
    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }
}
