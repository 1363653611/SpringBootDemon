package com.zbcn.web.controller;

import com.zbcn.web.bean.Author;
import com.zbcn.web.bean.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ControllerAdvice 验证
 */
@Controller
public class ControllerAdviceController {

	/**
	 * 结合 @InitBinder
	 * 两个实体类都有一个 name 属性，从前端传递时 ，无法区分。此时，通过 @ControllerAdvice 的全局数据预处理可以解决这个问题
	 * @param book
	 * @param author
	 */
	@PostMapping("/book")
	public void addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
		System.out.println(book);
		System.out.println(author);
	}
}
