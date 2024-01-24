package com.alibou.security.user;

import java.util.List;

public interface searchrepository {

    List<User> findByText(String text);

}
