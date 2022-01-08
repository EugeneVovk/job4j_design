package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User first = new User("John Doe", 2, new GregorianCalendar(2000, Calendar.FEBRUARY, 31));
        User second = new User("John Doe", 2, new GregorianCalendar(2000, Calendar.FEBRUARY, 31));
        User third = new User("John Gold", 0, new GregorianCalendar(1910, Calendar.OCTOBER, 3));

        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        map.put(third, new Object());

        for (User key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }
}
