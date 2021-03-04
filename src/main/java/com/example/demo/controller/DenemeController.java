package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;
import com.example.demo.models.Test;
import com.example.demo.services.AnswerService;
import com.example.demo.services.QuestionService;
import com.example.demo.services.TestService;
import com.example.demo.utils.Deneme;
import com.example.demo.utils.sorular;

@Controller
@RequestMapping("/deneme")
public class DenemeController {

	@Autowired
	private TestService tService;

	@Autowired
	private QuestionService qService;

	@Autowired
	private AnswerService aService;

	@RequestMapping("/deneme")
	public ModelAndView deneme(int Id) {
		ModelAndView mav = new ModelAndView("deneme/deneme");

		mav.addObject("test", tService.findById(Id));

		mav.addObject("message",
				"deneme sınavına gireceksiniz. sınavı bitir tuşuna basmadığınız veya son soturyu katydetm ediğiniz sürece sıvauını tammalanmış olmayabcaktır "
						+ tService.findById(Id).getTestAck());

		return mav;
	}

	@RequestMapping("/sinav")
	public ModelAndView sınava(int Id, @ModelAttribute("test") Test test) {

		System.err.println(test.getTestAck());

		ModelAndView mav = new ModelAndView("deneme/sinav");

		Deneme deneme = new Deneme();

		deneme.setTest(tService.findById(Id));

		List<sorular> sorular = new ArrayList<sorular>();
		int sirano = 0;
		for (Question sorular2 : qService.getByTest(Id)) {

			sorular soru = new sorular();

			soru.setSorusirano(sirano);
			soru.setSoru(sorular2);

			List<Answer> cevaplar = new ArrayList<Answer>();

			for (Answer sorular3 : aService.findbyQuestion(sorular2)) {

				cevaplar.add(sorular3);

			}
			soru.setCevaplar(cevaplar);

			sorular.add(soru);

			deneme.setSorular(sorular);
			sirano++;
		}

		mav.addObject("sorusira", 0);

		mav.addObject("deneme", deneme);

		return mav;
	}

}
