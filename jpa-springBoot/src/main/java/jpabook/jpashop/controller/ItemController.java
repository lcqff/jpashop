package jpabook.jpashop.controller;

import java.util.List;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items",items);
        return "items/itemList";
    }
}
