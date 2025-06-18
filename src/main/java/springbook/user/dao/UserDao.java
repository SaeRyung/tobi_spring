package springbook.user.dao;
// 사용자 정보를 넣고 관리할 수 있는 DAO class
// JDBC를 이용한 등록과 조회 기능이 있는 UserDao 클래스
import springbook.user.domain.User;

import java.sql.*;

public class UserDao {
    // 인터페이스 통해 오브젝트에 접근하므로 구체적인 클래스 정보 알 필요 없다.
    private ConnectionMaker connectionMaker;

    public UserDao() {
    }

    // UserDao 자신의 관심사이자 책임인 사용자 데이터 액세스 작업을 위해 SQL 생성, 실행하는 데만 집중 한다.
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    // 수정자 메소드 DI - 메소드를 이용한 의존관계 주입
    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        // 인터페이스 정의된 메소드를 사용하므로 클래스가 바뀌어도 메소드 이름 변경될 걱정 없다.
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;

    }
}
