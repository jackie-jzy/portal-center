layui.define(['jquery', 'layer'], function (exports) {
    var $ = layui.$,
        layer = layui.layer;

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    // 配置全局ajax
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', common.getToken())
            xhr.setRequestHeader('Content-Type', 'application/json')
        },
        complete: function (xhr, textStatus) {
            var res = xhr.responseText;
            var result = JSON.parse(res);
            if (result.code == 401) {
                common.error(result.message);
                setTimeout(function () {
                    window.location = "/route/login";
                }, 2000);
                return;
            }
            if (result.code != 200) {
                common.error(result.message);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            switch (jqXHR.status) {
                case(500):
                    common.error("服务器系统内部错误");
                    break;
                case(401):
                    window.location = options.login;
                    break;
                case(403):
                    common.error("无权限执行此操作");
                    break;
                case(404):
                    common.error("请求路径不存在");
                    break;
                case(408):
                    common.error("请求超时");
                    break;
                default:
                    console.log(jqXHR.status);
                    common.error("未知错误");
            }
        }
    });

    var common = {
            render: function (options) {
            },

            /**
             * 获取token
             */
            getToken: function () {
                return layui.data('auth').token;
            },
            /**
             * 获取username
             */
            getUsername: function () {
                return layui.data('auth').username;
            },
            /**
             * 获取nickname
             */
            getNickname: function () {
                return layui.data('auth').nickname;
            },
            /**
             * 保存token
             * @param {Object} token 存入的token值
             */
            setToken: function (token) {
                if (token != null) {
                    layui.data('auth', {
                        key: 'token'
                        , value: token
                    });
                }
            },
            /**
             * 保存username
             * @param {Object} username 存入的username值
             */
            setUsername: function (username) {
                if (username != null) {
                    layui.data('auth', {
                        key: 'username'
                        , value: username
                    });
                }
            },
            /**
             * 保存nickname
             * @param {Object} username 存入的nickname值
             */
            setNickname: function (nickname) {
                if (nickname != null) {
                    layui.data('auth', {
                        key: 'nickname'
                        , value: nickname
                    });
                }
            },
            /**
             * 删除token
             */
            delToken: function () {
                layui.data('auth', {
                    key: 'token'
                    , remove: true
                });
            },
            /**
             * 清空缓存(清空auth的所有data数据)
             */
            delData: function () {
                layui.data('auth', null);
            },
            parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                if (res.code == 200) {
                    return {
                        "code": res.code, //返回状态码
                        "msg": res.message, //解析提示信息
                        "count": res.data.total, //后台返回的数据总条数，用于自定义分页使用
                        "data": res.data.records //解析数据列表
                    };
                }
            },
            getRecentDay: function (day) {
                var today = new Date();
                var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
                today.setTime(targetday_milliseconds);
                var tYear = today.getFullYear();
                var tMonth = today.getMonth();
                var tDate = today.getDate();
                tMonth = common.doHandleMonth(tMonth + 1);
                tDate = common.doHandleMonth(tDate);
                return tYear + "-" + tMonth + "-" + tDate;
            },
            doHandleMonth: function (month) {
                var m = month;
                if (month.toString().length == 1) {
                    m = "0" + month;
                }
                return m;
            },
            /**
             * 失败
             * @param title
             * @returns {*}
             */
            error: function (title) {
                return layer.msg(title, {icon: 2, shade: this.shade, scrollbar: false, time: 3000, shadeClose: true});
            }
            ,
        }
    ;
    exports('common', common);
});