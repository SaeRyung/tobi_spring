//package springbook.user.dao;

// 클라이언트 역할 UserDaoTest
//public class UserDaoTest {
//    public static void main(String[] args) throws Exception {
//        // UserDao가 사용할 ConnectionMaker 구현 클래스를 결정하고, 오브젝트를 만든다.
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//
//        /* 1. UserDao 생성
//        * 2. 사용할 ConnectionMaker 타입의 오브젝트 제공
//        * => 결국 두 오브젝트 사이의 의존관계 설정 효과 */
//        UserDao userDao = new UserDao(connectionMaker);
        // DaoFactory 에서 UserDao 오브젝트를 받아서, 자신의 관심사인 테스트를 위해 활용
//        UserDao userDao = new DaoFactory().userDao();

//        DaoFactory daoFactory = new DaoFactory();
//        UserDao d1 = daoFactory.userDao();
//        UserDao d2 = daoFactory.userDao();
//
//        System.out.println(d1);
//        System.out.println(d2);

//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//        UserDao userDao1 = context.getBean("userDao", UserDao.class);
//        UserDao userDao2 = context.getBean("userDao", UserDao.class);
//
//        System.out.println(userDao1);
//        System.out.println(userDao2);
//        System.out.println(userDao1 == userDao2); //true

//        UserDao dao = context.getBean("userDao", UserDao.class);
//        User user1 = new User();
//
//        user1.setId("user");
//        user1.setName("백기선2");
//        user1.setPassword("123456");
//
//        dao.add(user1);
//
//        System.out.println(user1.getId() + " 등록 성공");
//
//        User user2 = dao.get("user");
//
//        if(!user1.getId().equals(user2.getId())) {
//            System.out.println("테스트 실패 (id)");
//        } else if (!user1.getPassword().equals(user2.getPassword())) {
//            System.out.println("테스트 실패 (password)");
//        } else {
//            System.out.println("테스트 성공");
//        }
//    }
//}
