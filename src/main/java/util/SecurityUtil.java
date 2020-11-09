package util;

import model.Security;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SecurityUtil {

    public static Map<String, Integer> getDropdownList(List<Security> securities) {
        Map<String, Integer> dropdownList = new TreeMap<>();
        securities.forEach((security) -> dropdownList.put(security.getName(), security.getId()));
        return dropdownList;
    }
}
