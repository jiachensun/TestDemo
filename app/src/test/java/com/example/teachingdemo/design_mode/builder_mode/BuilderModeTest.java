package com.example.teachingdemo.design_mode.builder_mode;

import org.junit.Test;

/**
 * @Author sjc
 * @Date 2020/7/25
 * Description：创建者模式
 * 需要创建不同的对象，不同对象有不同的功能，创建流程方便。
 */
public class BuilderModeTest {
    @Test
    public void main() {
        PeopleMode zhangsan = new Builder().setAge(28).setName("张三").setSex("男").build();
        System.out.println(zhangsan.getName());
    }

    public static final class Builder {
        private final PeopleMode people;

        Builder() {
            people = new PeopleMode();
        }

        Builder setAge(int age) {
            people.setAge(age);
            return this;
        }

        Builder setName(String name) {
            people.setName(name);
            return this;
        }

        Builder setSex(String sex) {
            people.setSex(sex);
            return this;
        }

        Builder setClassRoom(String classRoom) {
            people.setClassRoom(classRoom);
            return this;
        }

        Builder setSchoolMate(String schoolMate) {
            people.setSchoolMate(schoolMate);
            return this;
        }

        Builder setSalary(String salary) {
            people.setSalary(salary);
            return this;
        }

        PeopleMode build() {
            return people;
        }
    }
}
