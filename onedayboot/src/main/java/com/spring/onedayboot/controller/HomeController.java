package com.spring.onedayboot.controller;

import com.spring.onedayboot.controller.dto.BookResponseDto;
import com.spring.onedayboot.domain.entity.Book;
import com.spring.onedayboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final BookService bookService;

    @GetMapping("/")
    public String home(Model model) {
        List<BookResponseDto> books = bookService.findAll();
        model.addAttribute("books", books);
        return "/book/list";
    }
}
