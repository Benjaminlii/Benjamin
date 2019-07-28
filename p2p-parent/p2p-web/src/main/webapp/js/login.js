var referrer = "";//登录后返回页面
referrer = document.referrer;
if (!referrer) {
    try {
        if (window.opener) {
            // IE下如果跨域则抛出权限异常，Safari和Chrome下window.opener.location没有任何属性
            referrer = window.opener.location.href;
        }
    } catch (e) {
    }
}

//按键盘Enter键即可登录
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        login();
    }
});

//输入域失去焦点，将红色提示信息清除
$(function () {
    //获取平台基本信息
    loadStat();
});

//获取平台基本信息
function loadStat() {
    $.ajax({
        url: "loan/loadStat",
        type: "get",
        success: function (jsonObject) {
            $("#historyAverageRate").html(jsonObject.historyAverageRate);
            $("#allUserCount").html(jsonObject.allUserCount);
            $("#allBidMoney").html(jsonObject.allBidMoney);
        }
    });
}

//验证手机号
function checkPhone() {
    //获取用户输入的手机号
    var phone = $.trim($("#phone").val());
    if ("" == phone) {
        $("#showId").html("");
        $("#showId").html("请输入手机号");
        return false;
    } else if (!/^1[1-9]\d{9}$/.test(phone)) {
        $("#showId").html("");
        $("#showId").html("请输入正确的手机号");
        return false;
    } else {
        $("#showId").html("");
    }
    return true;
}

//验证登录密码
function checkLoginPassword() {
    //获取用户输入的登录密码
    var loginPassword = $.trim($("#loginPassword").val());
    if ("" == loginPassword) {
        $("#showId").html("");
        $("#showId").html("请输入登录密码");
        return false;
    } else {
        $("#showId").html("");
    }
    return true;
}

//验证图形验证码
function checkCaptcha() {
    var flag = true;
    //获取用户输入图形验证码
    var captcha = $.trim($("#captcha").val());
    if ("" == captcha) {
        $("#showId").html("");
        $("#showId").html("请输入图形验证码");
        return false;
    } else {
        $.ajax({
            url: "loan/checkCaptcha",
            type: "post",
            async: false,
            data: "captcha=" + captcha,
            success: function (jsonObject) {
                if (jsonObject.errorMessage == "OK") {
                    $("#showId").html("");
                    flag = true;
                } else {
                    $("#showId").html("");
                    $("#showId").html(jsonObject.errorMessage);
                    flag = false;
                }
            },
            error: function () {
                $("#showId").html("系统繁忙，请稍后重试...");
                flag = false;
            }
        });
    }

    if (!flag) {
        return false;
    }
    return true;
}

//用户登录
function login() {
    //获取用户输入表单内容
    var phone = $.trim($("#phone").val());
    var loginPassword = $.trim($("#loginPassword").val());

    if (checkPhone() && checkLoginPassword() && checkCaptcha()) {
        $("#loginPassword").val($.md5(loginPassword));

        $.ajax({
            url: "loan/login",
            type: "post",
            data: {
                "phone": phone,
                "loginPassword": $.trim($("#loginPassword").val())
            },
            success: function (jsonObject) {
                if (jsonObject.errorMessage == "OK") {
                    //登录成功
                    if ("" == referrer) {
                        window.location.href = "index";
                    } else {
                        window.location.href = referrer;
                    }
                } else {
                    //登录失败
                    $("#showId").html("");
                    $("#showId").html(jsonObject.errorMessage);
                }
            },
            error: function () {
                $("#showId").html("");
                $("#showId").html("系统繁忙，请稍后重试...");
            }
        });
    }
}

