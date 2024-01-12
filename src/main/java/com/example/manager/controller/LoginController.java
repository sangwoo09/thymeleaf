package com.example.manager.controller;

import com.example.manager.dto.UserDto;
import com.example.manager.entity.User;
import com.example.manager.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;

    @GetMapping("/login")
    private String login(){
        return "index";
    }

    @PostMapping("/login")
    private String login(String id, String password, Model model, HttpServletRequest request){
        User user = userRepository.findByIdAndPassword(id, password);

        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("sessionUserId", user.getId());
            session.setAttribute("sessionUserName", user.getName());
            return "redirect:/main";
        }else {
            model.addAttribute("msg", "아이디 또는 비밀번호를 확인해 주세요.");
            return "index";
        }
    }

    @GetMapping("/signup")
    private String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    private String signup(UserDto userDto,Model model){
        if (!userDto.getPassword1().equals(userDto.getPassword2())){
            model.addAttribute("msg","비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        User user = User.builder()
                .id(userDto.getId())
                .password(userDto.getPassword1())
                .name(userDto.getName())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .birth(userDto.getBirth())
                .registration(userDto.getRegistration())
                .joindate(userDto.getJoindate())
                .build();

        userRepository.save(user);
        model.addAttribute("msg", "회원가입이 완료 되었습니다.");
        return "index";
    }

    @GetMapping("emailConfirm")
    private String moveEmailConfirm(){
        return "emailConfirm";
    }

    @PostMapping("emailConfirm")
    private String emailConfirm(UserDto userDto, Model model){
        if (userRepository.existsByEmail(userDto.getEmail())){
            model.addAttribute("msg", "이미 사용중인 이메일 입니다.");
        }else {
            model.addAttribute("msg", "사용가능한 이메일 입니다.");
        }

        return "emailConfirm";
    }
}
