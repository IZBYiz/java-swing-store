package com.cy.store.controller;

import com.cy.store.entity.BaseEntity;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import com.cy.store.service.impl.UserServiceImpl;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Controller
@RequestMapping("/users")
@RestController //@Controller+@ResponseBody
@SuppressWarnings({"all"})
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/reg")
    //@ResponseBody //此方法的响应结果以json格式的数据的响应给到前端
    public JsonResult<Void> reg(User user) {
        //创建响应结果对象
        JsonResult<Void> result = new JsonResult<>();
       /* try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }
        return result;*/
        userService.reg(user);
        return result;
    }

    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password,
                                  HttpSession session) {
        User data = userService.login(username, password);
        //向session对象中完成数据的绑定(session全局的）
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        System.out.println("login success!!!");
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        Integer uid = getuidFromSession(session);
        String useranme = getUsernameFromSession(session);
        userService.changePassword(uid, useranme, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        //获取当前用户的id和用户名
        Integer uid = getuidFromSession(session);
        String useranme = getUsernameFromSession(session);
        // 调用业务对象执行修改用户资料
        userService.changeInfo(uid, useranme, user);
        // 响应成功
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/isDelete")
    public JsonResult<Void> updateIsDelete(HttpSession session) {
        Integer uid = getuidFromSession(session);
        userService.updateIsDelete(uid);
        return new JsonResult<Void>(OK);

    }

    /**
     * 头像文件大小的上限值(10MB)
     * final对变量的修饰的作用，是防止变量值的改变
     */
    public static final int AVATAR_MAX_SIZE = 10 * 10 * 1024;

    /**
     * 允许上传的头像的文件类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    /**
     * MultipartFile 接口是springMVC提供的一个接口，
     * 这个接口为我们包装了获取文件类型的数据(任何类型的file都可以接收)，
     * springboot它由于整合了springMVC，只需要在处理请求的方法参数列表上
     * 声明一个参数类型为MultipartFile的参数，然后springboot自动
     * 将传递给服务的文件数据赋值给这个参数
     *
     * @RequestParam 表示在请求中的参数，将请求中的参数注入请求处理
     * 方法的某个参数，如果名称不一致则可以使用@RequestParam注解进行标记和映射
     */
    @PostMapping("/change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file")
                                                   MultipartFile file,
                                           HttpSession session) {
        //获取当前用户的uid
        Integer uid = getuidFromSession(session);
        //获取当前用户的用户名
        String username = getUsernameFromSession(session);
        //判断文件上传是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }
        //getSize()：返回文件的大小，以字节为单位
        if (file.getSize() >= AVATAR_MAX_SIZE) {
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" +
                    (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // boolean contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重新尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        // 将头像写入到数据库中
        userService.changeAvatar(uid, username, avatar);

        // 返回成功头像路径
        return new JsonResult<String>(OK, avatar);
    }
}
