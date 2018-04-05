package examples.helloboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/boards")
public class BoardController {

    @GetMapping
    public String list(){
        return "boards_list";
    }

    @GetMapping(value = "/{boardId}")
    public String read(){
        return "boards_read";
    }

    @GetMapping(value = "/writeform")
    public String writeform(){
        return "boards_writeform";
    }

    @PostMapping
    public String write(){
        return "redirect:boards_list";
    }

    @GetMapping(value = "/updateform")
    public String updateform(){
        return "boards_updateform";
    }

    @PutMapping
    public String update(){
        return "redirect:boards_read/"; //boardId 추가해야함
    }

    @GetMapping(value = "/deleteform")
    public String deleteform(){
        return "boards_deleteform";
    }

    @DeleteMapping
    public String delete(){
        return "redirect:boards_list";
    }
}
