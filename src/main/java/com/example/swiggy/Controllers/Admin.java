package com.example.swiggy.Controllers;

import com.example.swiggy.Entety.Hotel;
import com.example.swiggy.Entety.HotelData;
import com.example.swiggy.Services.HotelServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class Admin {

    @Autowired
    HotelServices hotelServices;




    @RequestMapping("/login")
    public String login()
    {
        return "loginAdmin.html";
    }
    @PostMapping("/login")
    // @ResponseBody
    public String home(@RequestParam("username") String username,
                       @RequestParam("password") String password, HttpSession session) {
        if (username.equals("admin") && password.equals("admin123")) {

            session.setAttribute("user", "Admin");
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }
    @RequestMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "adminHome.html";
        }
        else
        {
            return "redirect:/login";
        }
    }
    @RequestMapping("/add")
    public String add()
    {
        return "AddDish.html";
    }
@PostMapping("/add")
public String save(HotelData hotelData)
{
    try {
        Hotel hotel = new Hotel(hotelData);
        hotelServices.save(hotel);
        byte by[] = hotelData.getPhoto().getBytes();
        Path path = Paths.get("src/main/resources/static/Image/" + hotelData.getPhoto().getOriginalFilename());
        Files.write(path, by);
        return "redirect:/display";
    } catch (Exception e) {
        return e.getMessage();

    }
}



@RequestMapping("/display")
public String disp(Model model)
{
    List<Hotel> data1=(List<Hotel>) hotelServices.findAll();
    model.addAttribute("data1",data1);
//    return data1.toString();
    return "/display.html";
}
@GetMapping("/delete/{id}")
public String remove(@PathVariable int id)
{
    hotelServices.deleteById(id);
    return "redirect:/display";
}
@GetMapping("/update/{id}")
public String edit(@PathVariable int id,Model model)
{
    Optional<Hotel>hotel=hotelServices.findById(id);
    model.addAttribute("item",hotel.get());
    return "update.html";
}
@PostMapping("/updateProduct")
public String update(Hotel hotel)
{
    hotelServices.save(hotel);
    return "redirect:/display";
}



    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/login";
    }


}
