
package model;

import org.apache.commons.lang3.StringUtils;

public class Customer {

    private int id;
    private String name;
    private String phone;

    public Customer(int id, String name, String phone) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException(
                "имя клиента не может быть пустым"
            );
        }

        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
