package com.elkabani.db_demo.controllers;
import com.elkabani.db_demo.dtos.RegisterUserRequest;
import com.elkabani.db_demo.dtos.UpdateUserRequest;
import com.elkabani.db_demo.exceptions.InvalidUserStateException;
import com.elkabani.db_demo.exceptions.UserNotFoundException;
import com.elkabani.db_demo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import com.elkabani.db_demo.mappers.UserMapper;
import com.elkabani.db_demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/ui/users")
public class UserUIController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    // Helper method to check if user is logged in
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }

    @GetMapping
    public String listUsers(Model model, @RequestParam(defaultValue = "name") String sort, HttpSession session,
                            RedirectAttributes redirectAttributes){

        // session guard
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }

        var users = userRepository.findAll(Sort.by(sort)).stream().map(userMapper::toDto).toList();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model, HttpSession session, RedirectAttributes redirectAttributes)
    {
        // session guard
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }

        model.addAttribute("user", new RegisterUserRequest());
        model.addAttribute("isEdit", false);
        return "users/form";
    }

    @PostMapping
    public String createUser(@ModelAttribute RegisterUserRequest request, HttpSession session, RedirectAttributes redirectAttributes)
    {
        // session guard
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }

        var user = userMapper.toEntity(request);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");
        return "redirect:/ui/users";
    }

    @GetMapping("/{id}")
    public String viewUser (@PathVariable Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes)
    {
        // session guard
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }

        var user = userRepository.findById(id).orElse(null);
        if(user==null)
        {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
            return "redirect:/ui/users";
        }
        model.addAttribute("user", userMapper.toDto(user));
        return "users/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model,  HttpSession session, RedirectAttributes redirectAttributes)
    {
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }

        var user = userRepository.findById(id).orElse(null);
        if(user==null) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
            return "redirect:/ui/users";
        }
        var updateRequest = new UpdateUserRequest();
        updateRequest.setName(user.getName());
        updateRequest.setEmail(user.getEmail());
        model.addAttribute("user", updateRequest);
        model.addAttribute("userId", id);
        model.addAttribute("isEdit", true);
        return "users/form";
    }

    @PutMapping("/{id}")
    public String updateUSer(@PathVariable Long id, @ModelAttribute UpdateUserRequest request,
                             HttpSession session,
                             RedirectAttributes redirectAttributes)
    {
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }

        var user = userRepository.findById(id).orElse(null);
        if(user==null)
        {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
            return "redirect:/ui/users";
        }
        userMapper.update(request, user);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        return "redirect:/ui/users/"+id;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes)
    {
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }

        var user = userRepository.findById(id).orElse(null);
        if(user==null) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
            return "redirect:/ui/users";
        }
        userRepository.delete(user);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        return "redirect:/ui/users";
    }

    @PostMapping("/{id}/activate")
    public String activateUser(@PathVariable Long id, HttpSession session,
                               RedirectAttributes redirectAttributes) {

        // session guard
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }

        try {
            userService.activateUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "User activated!");
        } catch (UserNotFoundException | InvalidUserStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/ui/users";
    }

    @PostMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable Long id, HttpSession session,
                               RedirectAttributes redirectAttributes) {

        // session guard
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to continue");
            return "redirect:/login";
        }


        try {
            userService.deactivateUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deactivated!");
        } catch (UserNotFoundException | InvalidUserStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/ui/users";
    }


}
