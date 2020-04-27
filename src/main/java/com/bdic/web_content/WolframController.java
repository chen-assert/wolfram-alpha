package com.bdic.web_content;

import com.bdic.QA;
import com.bdic.QARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

@Controller
public class WolframController {
    @Autowired
    private QARepository repository;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/wolfram";
    }

    @GetMapping(value = "/wolfram")
    public ModelAndView stackGet(HttpServletRequest servletRequest, Model model) {
        HttpSession session = servletRequest.getSession();
        ModelAndView mv = new ModelAndView("Wolfram_Alpha_ Computational Intelligence");

        return mv;
    }

    @GetMapping(value = "/wolfram/query", produces = "text/html")
    @ResponseBody
    public String query(HttpServletRequest servletRequest, @RequestParam("data") String data) {
        Iterator<QA> byQuestion = repository.findByQuestion(data).iterator();
        if (!byQuestion.hasNext()) {
            String answer = WolframAlpha2.query(data);
            QA qa = new QA();
            qa.setQuestion(data);
            qa.setAnswer(answer);
            repository.save(qa);
            return answer;
        } else {
            return byQuestion.next().getAnswer();
        }
    }
}
