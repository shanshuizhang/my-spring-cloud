package com.zss.springcloud.common.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/27 15:03
 */
public class CollectionUtil {

    public static String join(List<Long> list, String joinner){
        List<String> stringList = list.stream()
                .map(p -> String.valueOf(p))
                .collect(Collectors.toList());
        return String.join(joinner,stringList);
    }
}
