package com.ooa1769.bs.web;

import com.ooa1769.bs.book.support.SearchHistoryService;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.MemberAlreadyExistException;
import com.ooa1769.bs.member.support.MemberService;
import com.ooa1769.bs.support.util.Mappings;
import com.ooa1769.bs.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(Mappings.MEMBER)
public class MemberController {

    private final MemberService memberService;
    private final SearchHistoryService searchHistoryService;

    @Autowired
    public MemberController(MemberService memberService, SearchHistoryService searchHistoryService) {
        this.memberService = memberService;
        this.searchHistoryService = searchHistoryService;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String registerPage(@ModelAttribute MemberDto memberDto) {
        return "member/registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid MemberDto memberDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("member/registerForm", "formErrors", result.getAllErrors());
        }

        Member member = null;
        try {
            member = memberService.register(memberDto);
        } catch (MemberAlreadyExistException e) {
            result.addError(new FieldError("memberDto", "email", e.getMessage()));
            return new ModelAndView("member/registerForm", "memberDto", memberDto);
        }

        return new ModelAndView("redirect:/member/congratulation", "name", member.getName());
    }

    @RequestMapping(value = "/congratulation", method = RequestMethod.GET)
    public String congratulation(String name, Model model) {
        model.addAttribute("name", name);
        return "member/congratulation";
    }
}
