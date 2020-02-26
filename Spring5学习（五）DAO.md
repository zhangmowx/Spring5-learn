## Spring DAO

> Spring对数据访问对象（DAO）的支持旨在使以一致的方式轻松使用数据访问技术（例如JDBC，Hibernate或JPA）。这使您可以轻松地在上述持久性技术之间进行切换，还使您无需担心捕获每种技术特有的异常即可进行编码。 

### 1. 使用JDBC访问数据库

使用`JdbcTemplate`模板类操作数据

**JdbcTemplate**主要提供以下五类方法：

- execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；

- update方法：update方法用于执行新增、修改、删除等语句；

- batchUpdate方法：batchUpdate方法用于执行批处理相关语句；

- query方法及queryForXXX方法：用于执行查询相关语句；

- call方法：用于执行存储过程、函数相关语句。

  

```java
@RunWith(SpringRunner.class)
@SpringJUnitConfig(locations = "file:src/main/resources/applicationContext.xml")
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1(){
        String sql = "INSERT INTO country(countryname,countrycode) values(?,?)";
        jdbcTemplate.update(sql,"安哥拉","AO");
    }

    @Test
    public void test2(){
        String sql = "UPDATE country set countrycode=?,countryname=? where id=?";
        jdbcTemplate.update(sql,"安哥拉1","AO1",11);
    }

    @Test
    public void test3(){
        String sql = "DELETE FROM country where id=?";
        jdbcTemplate.update(sql,10);
    }

    @Test
    public void test4(){
        String sql = "SELECT id,countryname,countrycode FROM country";
        List<Country> query = jdbcTemplate.query(sql, new RowMapper<Country>() {
            @Override
            public Country mapRow(ResultSet resultSet, int i) throws SQLException {
                Country country = new Country();
                country.setId(resultSet.getLong("id"));
                country.setCountryName(resultSet.getString("countryname"));
                country.setCountryCode(resultSet.getString("countrycode"));
                return country;
            }
        });
        //遍历
        for (Country country : query) {
            System.out.println(country);
        }
    }
    // JDK8 API
    @Test
    public void test5(){
        String sql = "SELECT id,countryname,countrycode FROM country";
        List<Country> query = jdbcTemplate.query(sql,
                (resultSet,i)->{
                Country country = new Country();
                country.setId(resultSet.getLong("id"));
                country.setCountryName(resultSet.getString("countryname"));
                country.setCountryCode(resultSet.getString("countrycode"));
                return country;

        });
        //遍历
        query.forEach(System.out::println);
    }

    // JDK8 API  查询id为1的country信息
    @Test
    public void test6(){
        String sql = "SELECT id,countryname,countrycode FROM country where id=?";
        List<Country> query = jdbcTemplate.query(sql,new Object[]{1},
                (resultSet,i)->{
                    Country country = new Country();
                    country.setId(resultSet.getLong("id"));
                    country.setCountryName(resultSet.getString("countryname"));
                    country.setCountryCode(resultSet.getString("countrycode"));
                    return country;

                });
        //遍历
        query.forEach(System.out::println);
    }
   
}
```

### 2. Spring 整合 Hibernate

### 3. Spring整合Mybatis