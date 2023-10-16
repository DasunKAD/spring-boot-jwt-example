package com.demojwt.core.util;

import com.demojwt.core.model.Group;
import com.demojwt.core.model.Role;
import com.demojwt.core.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class SystemUtil {

    public static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    /**
     * Not null and Not Empty Check
     *
     * @param s
     * @return
     */
    public static boolean isNotValidString(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotValidString(String... s) {
        return Arrays.stream(s)
                .anyMatch(s1 -> s1 == null || s1.isEmpty());
    }

    public static boolean isValidString(String s) {
        return s != null && !s.isEmpty();
    }

    public static List<String> userRoles(User user) {
        return user.getGroups().stream()
                .map(Group::getRoles)
                .flatMap(List::stream)
                .map(Role::getRole)
                .collect(toList());
    }

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = EMAIL_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static String pinGenerator() {
        return String.valueOf((int) (Math.random() * 89999) + 10000);
    }

//    public static String passwordGenerator() {
//        final byte[] buffer = new byte[16];
//        ThreadLocalRandom.current().nextBytes(buffer);
//        return BaseEncoding.base64Url().omitPadding().encode(buffer);
//    }
//
//    public static Date calculateTokenExpiryDateTime(TokenExpiryConfig expiryConfig){
//        LocalDateTime expiry = LocalDateTime.now().plus(expiryConfig.calculateValidityDuration());
//        return Date.from(expiry.atZone(ZoneId.systemDefault()).toInstant());
//    }
}
