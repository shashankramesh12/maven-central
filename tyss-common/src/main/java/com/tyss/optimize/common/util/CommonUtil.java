package com.tyss.optimize.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CommonUtil {

    public static String getCurrentTimestamp() {
        return Instant.now().toString();
    }

    public static String getFormattedDate(String date) {

        if (StringUtils.isNotEmpty(date)) {
            if (date.equalsIgnoreCase(CommonConstants.DEFAULT_ICON)) {
                return date;
            }
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            try {
                String formattedDate = outputFormat.format(inputFormat.parse(date));
                return formattedDate;
            } catch (DateTimeParseException dateTimeParseException) {
                log.error("getFormattedDate Exception Date = " + date + " " + dateTimeParseException.getMessage());
            }
        }
        return date;
    }

    public static String getMongoFormattedDate(String date) {

        if (StringUtils.isNotEmpty(date)) {
            if (date.equalsIgnoreCase(CommonConstants.DEFAULT_ICON)) {
                return date;
            }
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            try {
                String formattedDate = outputFormat.format(inputFormat.parse(date));
                return formattedDate;
            } catch (DateTimeParseException dateTimeParseException) {
                log.error("getMongoFormattedDate Exception Date = " + date + " " + dateTimeParseException.getMessage());
            }
            return date;
        }
        return date;
    }

    public static String generateOtp() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

    public static boolean isValidPassword(String password) {
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[!@#$%&*()+=^])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);

        return m.matches();
    }

    public static boolean isValidName(String name) {
        // Regex to check valid name.
        String regex = "^[a-zA-Z0-9 ]+$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);

        return m.matches();
    }

    public static boolean isContainNumbersWithSpace(String name) {
        // Regex to check valid name.
        String regex = "^[0-9 ]+$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);

        return m.matches();
    }

    public static boolean isContainTrimSpace(String name) {
        if (name == null) {
            return false;
        }
        String trimName = name.trim();

        return !name.equals(trimName);
    }

    public static String setExecutionDurationInHourMinSecFormat(Long executionDuration) {
        return String.format("%02d:%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(executionDuration),
                TimeUnit.MILLISECONDS.toMinutes(executionDuration) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(executionDuration)),
                TimeUnit.MILLISECONDS.toSeconds(executionDuration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(executionDuration)),
                TimeUnit.MILLISECONDS.toMillis(executionDuration) -
                        TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(executionDuration)));
    }

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String formatDate(String date) {

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = "";
        try {
            formattedDate = outputFormat.format(inputFormat.parse(date));
        } catch (DateTimeParseException dateTimeParseException) {
            log.error("Exception while formatting date = " + date + " " + dateTimeParseException.getMessage());
        }
        return formattedDate;

    }

    public static String formatDateForDb(String date) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = "";
        try {
            formattedDate = outputFormat.format(inputFormat.parse(date));
        } catch (DateTimeParseException dateTimeParseException) {
            log.error("Exception while formatting date = " + date + " " + dateTimeParseException.getMessage());
        }
        return formattedDate;
    }

    public static String formatTime(String date) {

        String formattedDate = "";
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
            Instant instant = offsetDateTime.toInstant();
            Date instantDate = Date.from(instant);
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            formattedDate = formatter.format(instantDate);
        } catch (Exception e) {
            log.error("Exception while formatting time = " + date + " " + e.getMessage());
        }
        return formattedDate;

    }

    public static boolean checkValidEmail(String emailId) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(emailId).matches();
    }

    public static String formatDateTime(String date) {

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = "";
        try {
            formattedDate = outputFormat.format(inputFormat.parse(date));
        } catch (DateTimeParseException dateTimeParseException) {
            log.error("getFormattedDate yyy Exception Date = " + date + " " + dateTimeParseException.getMessage());
        }
        return formattedDate;

    }


}
