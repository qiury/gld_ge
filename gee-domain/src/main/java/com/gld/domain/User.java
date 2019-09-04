package com.gld.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author qiuzx@gld.com
 * @Description: .java$
 * Created by qiuzx on 2019-09-03.
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
    private String nickName;
    private Integer age;
    private String email;
}
