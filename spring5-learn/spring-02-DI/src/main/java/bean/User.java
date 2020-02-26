package bean;

import java.util.*;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/23 10:09
 * @Description:
 */
public class User {

    private Integer id;
    private String name;
    private Dept dept;

    private Set<String> set;
    private List<String> list;
    private String[] array;
    private Map<String,Object> map;
    private Properties properties;

    public User() {
    }

    public User(Integer id, String name, Dept dept, Set<String> set, List<String> list, String[] array, Map<String, Object> map, Properties properties) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.set = set;
        this.list = list;
        this.array = array;
        this.map = map;
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept=" + dept +
                ", set=" + set +
                ", list=" + list +
                ", array=" + Arrays.toString(array) +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }
}
