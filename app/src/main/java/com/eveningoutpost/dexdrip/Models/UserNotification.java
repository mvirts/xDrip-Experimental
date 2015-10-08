package com.eveningoutpost.dexdrip.Models;

import android.provider.BaseColumns;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;
import ollie.query.Select;

import java.util.Date;

/**
 * Created by stephenblack on 11/29/14.
 */

@Table("Notifications")
public class UserNotification extends Model {

    @Column("timestamp")
    public double timestamp;

    @Column("message")
    public String message;

    @Column("bg_alert")
    public boolean bg_alert;

    @Column("calibration_alert")
    public boolean calibration_alert;

    @Column("double_calibration_alert")
    public boolean double_calibration_alert;

    @Column("extra_calibration_alert")
    public boolean extra_calibration_alert;

    @Column("bg_unclear_readings_alert")
    public boolean bg_unclear_readings_alert;

    @Column("bg_missed_alerts")
    public boolean bg_missed_alerts;

    @Column("bg_rise_alert")
    public boolean bg_rise_alert;

    @Column("bg_fall_alert")
    public boolean bg_fall_alert;

    public static UserNotification lastBgAlert() {
        return Select
                .from(UserNotification.class)
                .where("bg_alert = ?", true)
                .orderBy("_ID desc")
                .fetchSingle();
    }
    public static UserNotification lastCalibrationAlert() {
        return Select
                .from(UserNotification.class)
                .where("calibration_alert = ?", true)
                .orderBy("_ID desc")
                .fetchSingle();
    }
    public static UserNotification lastDoubleCalibrationAlert() {
        return Select
                .from(UserNotification.class)
                .where("double_calibration_alert = ?", true)
                .orderBy("_ID desc")
                .fetchSingle();
    }
    public static UserNotification lastExtraCalibrationAlert() {
        return Select
                .from(UserNotification.class)
                .where("extra_calibration_alert = ?", true)
                .orderBy("_ID desc")
                .fetchSingle();
    }


    public static UserNotification GetNotificationByType(String type) {
        type = type + " = ?";
        return Select
        .from(UserNotification.class)
        .where(type, true)
        .orderBy("_ID desc")
        .fetchSingle();
    }

    public static void DeleteNotificationByType(String type) {
        UserNotification userNotification = UserNotification.GetNotificationByType(type);
        if (userNotification != null) {
            userNotification.delete();
        }
    }

    public static UserNotification create(String message, String type) {
        UserNotification userNotification = new UserNotification();
        userNotification.timestamp = new Date().getTime();
        userNotification.message = message;
        if (type == "bg_alert") {
            userNotification.bg_alert = true;
        } else if (type == "calibration_alert") {
            userNotification.calibration_alert = true;
        } else if (type == "double_calibration_alert") {
            userNotification.double_calibration_alert = true;
        } else if (type == "extra_calibration_alert") {
            userNotification.extra_calibration_alert = true;
        } else if (type == "bg_unclear_readings_alert") {
            userNotification.bg_unclear_readings_alert = true;
        } else if (type == "bg_missed_alerts") {
            userNotification.bg_missed_alerts = true;
        } else if (type == "bg_rise_alert") {
            userNotification.bg_rise_alert = true;
        } else if (type == "bg_fall_alert") {
            userNotification.bg_fall_alert = true;
        }
        userNotification.save();
        return userNotification;

    }
}
