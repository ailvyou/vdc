package com.vdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CenterController extends BaseController {

	@RequestMapping("/center")
	public ModelAndView center(){
		ModelAndView mv=new ModelAndView("/center");
		
		return mv;
	}
}
