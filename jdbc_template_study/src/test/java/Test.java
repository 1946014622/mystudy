import com.atguigu.controller.UserController;
import com.atguigu.domin.User;
import com.mysql.cj.jdbc.Driver;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-config.xml")
public class Test {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @org.junit.Test
    public void test(){
        try {
            Driver driver = new Driver();
            Properties properties = new Properties();
            properties.setProperty("user","root");
            properties.setProperty("password","123456");
            Connection connect = driver.connect("jdbc:mysql://localhost:3306/ssm?serverTimezone=UTC", properties);
            PreparedStatement preparedStatement = connect.prepareStatement("select name from t_user where ID = ?");
            preparedStatement.setInt(1,8);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString(1));
            System.out.println(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql1 = "select * from t_user where ID = ?";
    User user = jdbcTemplate.queryForObject(sql1, new BeanPropertyRowMapper<>(User.class), 1);
    System.out.println(user);
    }
    @org.junit.Test
    public void test2(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("application-config.xml");
        UserController userController = ioc.getBean(UserController.class);
        userController.insert();
    }

}
