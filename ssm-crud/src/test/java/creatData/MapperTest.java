package creatData;

import com.Benjamin.crud.dao.DepartmentMapper;
import com.Benjamin.crud.dao.EmployeeMapper;
import com.Benjamin.crud.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 使用Spring测试
 * 1、添加spring的test模块包
 * 2、增加@ContextConfiguration注解，并指定spring配置文件的位置
 * 3、@RunWith(SpringJUnit4ClassRunner.class)指定使用spring去测试
 * 4、在类中直接使用@Autowired获取spring容器中的对象
 */

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    @Transactional
    @Rollback(false)
    public void testCrud() {
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 1; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(
                    new Employee(
                            null,
                            uuid,
                            i % 2 == 0 ? "M" : "W",
                            uuid + "@Ben.com",
                            i % 3 == 0 ? 1 : 2
                    )
            );
        }
    }

}
