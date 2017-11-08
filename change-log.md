# 2017-11-6
+ 设置权限接口
- JWT 和 Session 同时开启..
+ 增加登陆(/admin/login.html) 和 退出(/admin/logout)

## 问题1:
- 如果单独使用 JWT 如何解决直接请求页面的问题。
    1. 使用携带参数的方式。
    2. 将 Token 放入 Session 中处理。
  > 因此, 我暂时取消了去除 Session 的设定, 但是这样没法防止 csrf(头疼, 求解)...
  > 考虑使用 Spring Security 自带的防止 csrf 但 APP 怎么获取CSRF的值呢？

--------------
# 2017-11-8
- 完善很多页面问题, 配置以下解决方案。
- 优化单位的一些校验 (数据校验未完全完成, 前端+后端) __设想: 前端+后端都需判断数据的完整性(正则/是否必填)__
- 删除单位会影响到
    1. // 删除此单位 及 子类单位
    2. // 删除用户 和 此单位及子单位之间的关联(这些单位的用户所属单位字段设置为null)
    3. // 删除角色 和 此单位及子单位之间的关联(直接删除掉这些角色)
- 配置了 rememberMe 功能。

## 解决方案[#问题1]()
- /api/** 使用 JWT **(未配置)**
- Web 站点, 使用 Spring Security 来防止 csrf。
因此, 具体有以下方案
1. 单纯的<a>标签使用
```html
<!--1. 使用表单方式进行跳转-->
<form th:action="@{/admin/logout}" method="POST">
    <input type="submit" class="btn btn-default btn-flat" value="退出"/>
</form>
<!--2. 或使用 AJAX 进行跳转-->

```
2. 全局的 AJAX Header 设置
```html
<meta name="_csrf" th:content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" th:content="${_csrf.headerName}" />
<!--...-->
    <script>
        $(function () {
            // 添加 CSRF 头
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajaxSetup({
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                }
            });
        });
    </script>
```

#
- 更新`Department`/`Permission`/`Variable`