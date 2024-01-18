package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.PhotoDto;
import ru.job4j.cars.dto.PostCreateDto;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class PostController {

    private final EngineTypeService engineTypeService;
    private final TransmissionService transmissionService;
    private final WheelDriveService wheelDriveService;
    private final SimpleCarBodyService carBodyService;
    private final ColorService colorService;
    private final PostService postService;

    @GetMapping({"/", "/posts"})
    public String getAll(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    @GetMapping("/post/create")
    public String getCreatePost(Model model) {
        model.addAttribute("engineTypes", engineTypeService.findAll());
        model.addAttribute("transmissions", transmissionService.findAll());
        model.addAttribute("wheelDrives", wheelDriveService.findAll());
        model.addAttribute("carBodies", carBodyService.findAll());
        model.addAttribute("allColours", colorService.findAll());
        return "post/create";
    }

    @PostMapping("/post/create")
    public String addPost(Model model,
                          @ModelAttribute PostCreateDto postCreateDto,
                          @RequestParam MultipartFile file,
                          HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            postService.add(postCreateDto, new PhotoDto(file.getOriginalFilename(), file.getBytes()), user);
            return "redirect:/";
        } catch (IOException e) {
            model.addAttribute("message", e.getMessage());
            return "errors/500";
        }
    }

    /*
    View one post in detail
     */
    @GetMapping("/posts/{id}")
    public String getById(Model model, @PathVariable int id) {
        Optional<Post> postOptional = postService.findById(id);
        if (postOptional.isEmpty()) {
            model.addAttribute("message", "Сообщение с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("post", postOptional.get());
        return "posts/one";
    }

    @PostMapping("/posts/{id}")
    public String setSoldCar(Model model, @PathVariable int id) {
        Optional<Post> postOptional = postService.findById(id);
        if (postOptional.isEmpty()) {
            model.addAttribute("message", "Сообщение с указанным идентификатором не найдено");
            return "errors/404";
        }
        Post post = postOptional.get();
        post.setSales(true);
        model.addAttribute("post", postService.update(post).get());
        return "posts/one";
    }

}