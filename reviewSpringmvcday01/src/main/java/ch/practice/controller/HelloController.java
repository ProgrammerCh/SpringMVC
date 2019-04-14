package ch.practice.controller;

import ch.practice.pojo.User;
import ch.practice.pojo.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("hello")
public class HelloController {
    @RequestMapping(value = "show1")
    public ModelAndView test01(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        mav.addObject("msg","这是第一个springmvc程序");
        return mav;
    }
    @RequestMapping(value="show2?")
    public ModelAndView test02(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","ant风格的映射：？的使用");
        return modelAndView;
    }
    @RequestMapping(value = "show3*")
    public ModelAndView test03(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","ant风格的*的使用");
        return modelAndView;
    }
    @RequestMapping("show4/**")
    public ModelAndView test04(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","ant风格的/**:表示匹配任意多级路径");
        return modelAndView;
    }
    @RequestMapping("show5/{name}/{id}")
    public ModelAndView test05(@PathVariable("name") String name,@PathVariable("id") Long  id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","Rest风格占位符的使用:");
        return modelAndView;
    }
    @RequestMapping(value = "show6",method = RequestMethod.POST)
    public ModelAndView test06(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","限定请求方法的映射：post");
        return modelAndView;
    }
    @RequestMapping(value = "show7",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView test07(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","限定请求的方法：post/get");
        return modelAndView;
    }
    @RequestMapping(value = "show8",params = "userId")
    public ModelAndView test08(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","x限定请求必须携带参数");
        return modelAndView;
    }
    @RequestMapping(value = "show9",params = "!userId")
    public ModelAndView test09(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","限定请求必须不携带参数userId");
        return modelAndView;
    }
    @RequestMapping(value = "show10",params = "userId=101")
    public ModelAndView test10(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","限定请求参数中种的userId必须为101");
        return modelAndView;
    }
    @RequestMapping(value = "show11",params = "userId!=101")
    public ModelAndView test11(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","限定请求中的携带的参数必须不为101,或者没有这个参数也行");
        return modelAndView;
    }
    @RequestMapping(value = "show12",params = {"name=zhangsan","userId!=101"})
    public ModelAndView test12(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("msg","请求携带部分参数,必须携带name=张三，携带的userId必须不为101,或者不携带userId也可以");
        return modelAndView;
    }
    @GetMapping(value = "show13")
    public ModelAndView test13(){
        //在构造函数中添加视图名称
        ModelAndView modelAndView = new ModelAndView("hello");

        modelAndView.addObject("msg","组合注解GetMapping");
        return modelAndView;
    }
    @PostMapping(value = "show14")
    public ModelAndView test14(){

        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("msg","组合注解PostMapping");
        return modelAndView;
    }

    @DeleteMapping(value = "show15")
    public ModelAndView test15(){
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("msg","组合注解deleteMapping");
        return modelAndView;
    }
    @PutMapping(value = "show16")
    public ModelAndView test16(){
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("msg","组合注解putmapping");
        return modelAndView;
    }
    @RequestMapping(value = "show17")
    public String test17(Model model){
        model.addAttribute("msg","返回值是string类型的");
        //返回值是视图名称
        return "hello";

    }
    @RequestMapping(value = "show18")
    @ResponseStatus(HttpStatus.OK)
    public void test18(Model model){
        System.out.println("返回值是void");
    }
    @RequestMapping(value = "show19")
    public String test19(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        model.addAttribute("msg",request+"</br>"+response+"</br>"+session);
        return "hello";
    }
    //不要使用，show20字段，和上面的show2?字段有冲突，这里使用show30字段
    @RequestMapping(value = "show30")
    //一旦给请求参数设定了默认值，required=true就失效了
    public String test30(Model model ,@RequestParam(value = "name",defaultValue = "zhangsan") String name){
        model.addAttribute("msg","使用@RequestPara接收到的参数"+name);
        return "hello";

    }
    @RequestMapping(value = "show31")
    public String test31(Model model,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            model.addAttribute("msg","JESESSIONID="+cookie);
        }
        return "hello";
    }
    @RequestMapping(value = "show32")
    //当cookie中没有JSESSIONID这个属性时，可以将将其设置为为非必须
    public String test32(Model model,@CookieValue(value = "JSESSIONID",required = false)String Jsessionid){
        model.addAttribute("msg","使用@CookieValue注解获取cookie，获取的cookie的值为："+Jsessionid);
        return "hello";
    }
    @RequestMapping(value = "show33")
    public String test33(Model model,
                         @RequestParam("name")String name,
                         @RequestParam("age") Integer age,
                         @RequestParam("isMarry") Boolean isMarry,
                         @RequestParam("income") Float income,
                         @RequestParam("interests") String[] interests
    ){
        System.out.println(name);
        System.out.println(age);
        System.out.println(isMarry);
        System.out.println(income);
        System.out.println(Arrays.toString(interests));
        return "hello";

    }
    @RequestMapping(value = "show34")
    public String test34(Model model, User user,@RequestParam("name")String name){
        model.addAttribute("msg",user+"---->"+name);
        return "hello";
    }
    @RequestMapping(value = "show35")
    public String test35(Model model,@RequestParam("ids")List<Long> ids){
        model.addAttribute("msg",ids.toString());
        return "hello";

    }
    @RequestMapping(value = "show36")
    public String test36(Model model, UserVO userVO){
        model.addAttribute("msg",userVO);
        return "hello";

    }
    @RequestMapping(value = "show37")
    public String test37(Model model){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i+1L);
            user.setUsername("zhangsan"+i);
            user.setName("张三"+i);
            user.setAge(18);
            userList.add(user);

        }
        model.addAttribute("users",userList);
        return "userList";
    }
    @RequestMapping(value = "show38")
    @ResponseBody
    //jackson将数据转换成json格式时，需要消息转换器HttpMessageConverter的支持，默认没有开启
    //在springmvc的配置文件开启注解扫描即可
    public List<User> test38(){
        List userList = new ArrayList();
        for(int i = 0;i< 20;i++) {
            User user = new User();
            user.setId(i+1L);
            user.setUsername("zhangsan"+i);
            user.setName("张三"+i);
            user.setAge(18);
            userList.add(user);
        }
        return userList;

    }

    /**
     * 将提交的json数据转成对象，注意接收json字符串是，用到的注解@RequestBody
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "show40")
    public String test40(Model model,@RequestBody User user){
        model.addAttribute("msg",user);
        return "hello";
    }

    /**
     * 使用String字符串接收json
     * @param model
     * @param user
     * @return
     */
   @RequestMapping(value = "show41")
    public String test41(Model model,@RequestBody String user){
        model.addAttribute("msg",user);
        return "hello";

   }
   @RequestMapping(value = "show42")
    public String test42(Model model, @RequestParam("file")MultipartFile file) throws IOException {
       if (null != file) {
           file.transferTo(new File("e://upload//"+file.getOriginalFilename()));
       }
       model.addAttribute("msg","上传成功");
       return "hello";
   }
   @RequestMapping(value = "show43")
    public String test43(Model model,@RequestParam("files") MultipartFile [] files) throws IOException {
       for (MultipartFile file : files) {
           if (null != file){
               file.transferTo(new File("e://upload//"+file.getOriginalFilename()));
           }
       }
       model.addAttribute("msg","上传成功");
       return "hello";

   }

    /**
     * 执行转发
     * @param
     * @return
     */
   @RequestMapping(value = "show44")
    public String test44(){
       return "forward:/hello/show46.do?id=101&type=forward";
   }
    /**
     * 执行重定向
     */
    @RequestMapping(value = "show45")
    public String test45(){
        return "redirect:/hello/show46.do?id=102&type=redirect";

    }
    @RequestMapping(value = "show46")
    public String test46(Model model,@RequestParam("id")Long id,@RequestParam("type")String type){
        model.addAttribute("msg",id+"-->"+type);
        return "hello";
    }
    @RequestMapping(value = "show47")
    public String test47(){
        System.out.println("正在执行执行处理器");
        return "hello";
    }
    @RequestMapping(value = "show48")
    public String test48(@RequestParam("name")String name){
        System.out.println(name);
        return "hello";
    }
}
