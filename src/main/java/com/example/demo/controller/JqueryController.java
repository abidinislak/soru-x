package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Dto.Test_Dto;
import com.example.demo.Mapper.MapperTest;
import com.example.demo.models.Test;
import com.example.demo.services.TestService;

@RestController
public class JqueryController {

	@Autowired
	private TestService tService;

	@RequestMapping("jqueryhome")
	public ModelAndView home() {

		ModelAndView mav = new ModelAndView("jquery/jqueryhome");

		Test dGec = tService.findById(180);

		Test_Dto dGecTestDto = MapperTest.INSTANCE.testToTest_Dto(dGec);

		dGecTestDto.setTestAdi("efw");
		dGecTestDto.setTestnumber(1111);
		dGecTestDto.setTestTitleText("qwqwe");
		dGecTestDto.settId(22222);

		mav.addObject("testdto", dGecTestDto);

		return mav;

	}

	@RequestMapping(value = "/gettodos")
	public @ResponseBody List<Test> getTodos(Model model) {

		return tService.findAll();
	}

}
