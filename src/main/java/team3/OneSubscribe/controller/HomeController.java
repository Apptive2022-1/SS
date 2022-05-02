package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.domain.WritingContent;
import team3.OneSubscribe.repository.WritingRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    WritingRepository writingRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/contents")
    public String contents(Model model){
        List<Writing> writings = writingRepository.findAll();
        model.addAttribute("writings", writings);
        return "posts";
    }

    @GetMapping("/contents/writing")
    public String writingPage(Model model){
        model.addAttribute("form", new WritingForm());
        return "write";
    }

    @PostMapping("/contents/writing/new")
    public String contentsFromWriting(WritingForm form){
        Writing writing = new Writing();
        writing.setTitle(form.getTitle());
        writing.setContext(form.getContext());

        writingRepository.save(writing);
        return "redirect:/contents";
    }

    @GetMapping("/contents/{writingId}")
    public String writingContents(@PathVariable("writingId") Long writingId, Model model){
        Writing writing = (Writing) writingRepository.findOneById(writingId);

        model.addAttribute("writing", writing);
        return "write"; // 일단은 html 없어서 write으로 가게 함. // 여기 특정글 화면 나타내는 페이지 만들어야 함. // 그리고 거기서 타임리프 써서 특정글 보여주는 형식으로 해야함.
    }



}
