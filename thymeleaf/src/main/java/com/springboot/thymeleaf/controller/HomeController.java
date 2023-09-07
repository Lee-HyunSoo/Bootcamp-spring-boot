package com.springboot.thymeleaf.controller;

import com.springboot.thymeleaf.dto.Address;
import com.springboot.thymeleaf.dto.Card;
import com.springboot.thymeleaf.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class HomeController {

    /* http://localhost:8090 */
    @GetMapping("/")
    public String home(Model model) {
        log.info("== Home Controller ==");

        model.addAttribute("serverTime", LocalDateTime.now());
        model.addAttribute("msg", "안녕~~~");
        return "home";
    }

    /* http://localhost:8090/th-utext */
    @GetMapping("/th-utext")
    public String thUtext(Model model) {
        model.addAttribute("msg", "<b>Hello World!</b>");
        return "th-utext";
    }

    /* http://localhost:8090/message */
    @GetMapping("/message")
    public String message() {
        return "message";
    }

    /* http://localhost:8090/beans-property */
    @GetMapping("/beans-property")
    public String property(Model model) {
        Member member = setMember();

        model.addAttribute(member);
        return "beans-property";
    }

    /* http://localhost:8090/beans-properties */
    @GetMapping("/beans-properties")
    public String properties(Model model) {
        Member member = new Member();
        Address address = new Address();
        address.setPostCode("080908");
        address.setLocation("seoul");
        member.setAddress(address);

        model.addAttribute(member);
        return "beans-properties";
    }

    /* http://localhost:8090/th-object */
    @GetMapping("/th-object")
    public String thObject(Model model) {
        Member member = setMember();

        model.addAttribute(member);
        return "th-object";
    }

    /* http://localhost:8090/th-href */
    @GetMapping("/th-href")
    public String thHref(Model model) {
        return "th-href";
    }

    /* http://localhost:8090/sub/get-href */
    @GetMapping("/sub/get-href")
    public String getHref() {
        log.info("sub folder");
        return "/sub/get-href";
    }

    /* http://localhost:8090/th-block */
    @GetMapping("/th-block")
    public String block(Model model) {
        Member member = new Member();

        model.addAttribute(member);
        return "th-block";
    }

    /* http://localhost:8090/th-block2 */
    @GetMapping("/th-block2")
    public String block2(Model model) {
        Member member = new Member();
        member.setForeigner(true);

        model.addAttribute(member);
        return "th-block2";
    }

    /* http://localhost:8090/th-switch-case */
    @GetMapping("/th-switch-case")
    public String switchCase(Model model) {
        Member member = new Member();
        member.setGender("F");

        model.addAttribute(member);
        return "th-switch-case";
    }

    /* http://localhost:8090/th-each */
    @GetMapping("/th-each")
    public String thEach(Model model) {
        Member member = new Member();
        String[] hobbyArray = { "Music", "Movie" };
        member.setHobbyArray(hobbyArray);

        model.addAttribute(member);
        return "th-each";
    }

    /* http://localhost:8090/th-each2 */
    @GetMapping("/th-each2")
    public String thEach2(Model model) {
        Member member = new Member();
        List<Card> cards = new ArrayList<>();

        Card card1 = new Card("123456", YearMonth.of(2020, 9));
        Card card2 = new Card("456789", YearMonth.of(2022, 5));
        cards.add(card1);
        cards.add(card2);
        member.setCards(cards);

        model.addAttribute(member);
        return "th-each2";
    }

    /* http://localhost:8090/th-with */
    @GetMapping("/th-with")
    public String thWith(Model model) {
        Member member = setMember();

        model.addAttribute(member);
        return "/th-with";
    }

    /* http://localhost:8090/inline */
    @GetMapping("/inline")
    public String inLine(Model model) {
        model.addAttribute("username", "Sebastian");
        return "/inline";
    }

    /* http://localhost:8090/inline2 */
    @GetMapping("/inline2")
    public String inLine2(Model model) {
        model.addAttribute("username", "<b>Sebastian</b>");
        return "/inline2";
    }

    /* http://localhost:8090/comment */
    @GetMapping("/comment")
    public String comment(Model model) {
        model.addAttribute("msg", "comment test");
        return "/comment";
    }

    /* http://localhost:8090/jsinline */
    @GetMapping("/jsinline")
    public String jsinline(Model model) {
        model.addAttribute("username", "<b>Sebastian</b>");
        return "/jsinline";
    }

    private Member setMember() {
        Member member = new Member();
        member.setUserId("hongkd");
        member.setPassword("1234");
        member.setEmail("bbb@ccc.com");
        member.setUserName("홍길동");
        member.setDateOfBirth(LocalDate.of(1988, 10, 7));
        return member;
    }

}
