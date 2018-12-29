package lk.ijse.edu.elite.client.common;

public class Validation {
    public static boolean nameValidate(String name) {
        if (name.matches("[a-zA-Z ].[^!@#$%^&*()+=<>?0-9]+")) {
            return true;
        }
        return false;
    }
    public static boolean bike_number(String name) {
        if (name.matches("[a-z]{2}[-][A-Z]+[0-9]{4}")) {
            return true;
        }
        return false;
    }
    public static boolean passwordValidation(String password) {
        if (password.matches("[a-zA-Z0-9]+")) {
            return true;
        }
        return false;
    }

    public static boolean addressValidate(String address) {
        if (address.matches("[^*&%$#@!?()+=]+")) {
            return true;
        }
        return false;
    }

    public static boolean nicValidate(String nic) {
        if ((nic.matches("[0-9]{9}[vVxX]{1}+")) || (nic.matches("[0-9]{12}+"))) {
            return true;
        }
        return false;
    }

    public static boolean telephoneValidate(String tel) {
        if (tel.matches("[0-9]{3}[-][0-9]{7}")) {
            return true;
        }
        return false;
    }

    public static boolean dobValidation(String dob) {
        if (dob.matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")) {
            return true;
        }
        return false;
    }

    public static boolean orderQty(String qty) {
        if (qty.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public static boolean idValidation(String id, String prifix) {
        if (id.matches("[" + prifix + "][0-9]{3}")) {
            return true;
        }
        return false;
    }

    public static boolean cashValidation(String cash) {
        if (cash.matches("[0-9.]+")) {
            return true;
        }
        return false;
    }

    public static boolean dateValidation(String date) {
        if (date.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*")) {
            return true;
        }
        return false;
    }

    public static boolean periodValidation(String period) {
        if (period.matches("[0-9]{2}")) {
            return true;
        }
        return false;
    }

    public static boolean qtyOne(String qty) {
        if (qty.matches("(1)")) {
            return true;
        }
        return false;
    }
}
