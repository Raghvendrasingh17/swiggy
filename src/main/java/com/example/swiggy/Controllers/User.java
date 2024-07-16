package com.example.swiggy.Controllers;

import com.example.swiggy.Entety.Users;
import com.example.swiggy.Services.HotelServices;
import com.example.swiggy.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Controller
public class User {
    @Autowired
    HotelServices hotelServices;
    @Autowired
    UserServices userServices;

@RequestMapping("/userPage")
    public String user(Model model)
    {
        model.addAttribute("hotelInfo",hotelServices.findAll());
        return "userPage.html";
    }
    @RequestMapping("/cart")
    public String cart()
    {
        return "cart.html";
    }

@RequestMapping("/userlog")
    public String userlog()
    {
        return "userLogin.html";
    }
    @PostMapping("userlog")
    public  String save(Users users)
    {
    userServices.save(users);
    return "redirect:/userLogin";
    }
@RequestMapping({"/userLogin","/"})
    public String UserLogin()
    {
        return "login.html";
    }
    @PostMapping("/userLogin")
    public  String home(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/swiggy", "root", "1234");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from users where email='"+email+"' and password='"+password+"'");

            if(rs.next())
            {
                Users users = new Users();
                users.setUid(rs.getInt("uid"));
                users.setPassword(rs.getString("password"));
                users.setEmail(rs.getString("email"));
                session.setAttribute("users",users);
                return "redirect:/userPage";
            }
            else {
                return "redirect:/userLogin";
            }

        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "none user....!";
    }



}
