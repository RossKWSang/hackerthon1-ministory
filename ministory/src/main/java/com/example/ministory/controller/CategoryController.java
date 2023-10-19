package com.example.ministory.controller;

import com.example.ministory.dto.UserDto;
import com.example.ministory.entity.User;
import com.example.ministory.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.ministory.dto.CategoryDto;
import com.example.ministory.entity.Category;
import com.example.ministory.service.CategoryService;
import com.example.ministory.service.UserService;

import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;
    private UserService userService;

    // TODO: 유저의 카테고리를 전부 받아서 리스팅하는 함수
    @GetMapping("")
    public String getCategoryList(Model model, Long userId) {
        List<Category> categories = categoryService.findUserCategory(1L);
        model.addAttribute("categories", categories);
        return "category/categoryList";
    }

    // TODO: 카테고리를 입력하는 함수
    @PostMapping("")
    public String postCategory(CategoryDto categoryDto, Long userId) {
        categoryDto.setTitle("test");
        // TODO: 1번 유저가 생성한 카테고리로 우선 분류
        categoryService.saveCategoryOnUser(categoryDto, 1L);
        return "redirect:category";
    }

    /*
    // TODO: 유저의 카테고리 이름을 수정하는 함수
    @PatchMapping("")
    public String patchCategory(CategoryDto categoryDto, String ) {
        // TODO: 1번 유저가 생성한 카테고리로 우선 분류
        categoryService.saveCategoryOnUser(categoryDto, 1L);
        return "category/categoryList";
    }
    */

    @PostMapping("/delete")
    public String deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/api/category";
    }
}