package com.spring.onedayboot.controller;

import com.spring.onedayboot.controller.dto.BookRecordDto;
import com.spring.onedayboot.controller.dto.BookRequestDto;
import com.spring.onedayboot.controller.dto.BookResponseDto;
import com.spring.onedayboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/create")
    public String create() {
        return "/book/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute BookRequestDto bookRequestDto) {
        Long bookId = bookService.insert(bookRequestDto);

        return "redirect:/book/read/" + bookId;
    }

    @GetMapping("/read/{id}")
    public String read(@PathVariable Long id, Model model) {
        BookResponseDto book = bookService.findById(id);

        model.addAttribute("book", book);
        return "/book/read";
    }

    @PostMapping("/read/{id}")
    public String record(@PathVariable Long id,
                         @ModelAttribute BookRecordDto bookRecordDto)  {
        bookService.record(id, bookRecordDto);

        return "redirect:/book/read/" + id;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        BookResponseDto book = bookService.findById(id);

        model.addAttribute("book", book);
        return "/book/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute BookRequestDto bookRequestDto) {
        bookService.update(id, bookRequestDto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);

        return "redirect:/";
    }

}
