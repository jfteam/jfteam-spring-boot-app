package org.jfteam.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-08-16
 * Time: 下午5:57
 */
@Getter
@Setter
public class User implements Serializable {

    private Integer id;

    private String name;

    private int age;
}
