package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

		// mav.addObject("sorusira", 0);

		mav.addObject("deneme", deneme);

		return mav;
	}

	@RequestMapping("/sinav")
	public ModelAndView sınav(int Id, @ModelAttribute("test") Test test) {

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
		int [] asd= {0,1,2};
		
deneme.setSayi(asd);
		mav.addObject("sorusira", 0);
		
		Integer [] cern= {};
		mav.addObject("cern",cern);

		mav.addObject("deneme", deneme);

		return mav;

	}

	@RequestMapping("/sinavx")
	public ModelAndView sınavx(@ModelAttribute("deneme") Deneme deneme) {

		ModelAndView mav = new ModelAndView("deneme/sinav");

//		 Deneme deneme = new Deneme();
//
//		 deneme.setTest(tService.findById(Id));
//
//		List<sorular> sorular = new ArrayList<sorular>();
//		int sirano = 0;
//		for (Question sorular2 : qService.getByTest(Id)) {
//
//			sorular soru = new sorular();
//
//			soru.setSorusirano(sirano);
//			soru.setSoru(sorular2);
//
//			List<Answer> cevaplar = new ArrayList<Answer>();
//
//			for (Answer sorular3 : aService.findbyQuestion(sorular2)) {
//
//				cevaplar.add(sorular3);
//
//			}
//			soru.setCevaplar(cevaplar);
//
//			sorular.add(soru);
//
//			deneme.setSorular(sorular);
//			sirano++;
//		}
//
//		mav.addObject("sorusira", 0);
//
//		mav.addObject("deneme", deneme);

		return mav;
	}

	@RequestMapping("/sinava")
	public ModelAndView sinava(@ModelAttribute("deneme") Deneme sinava,
			@RequestParam(value = "cern" , required = false) int[] cern ) {

//		for (int iterable_element : sinava.getSorular().get(0).getKullanıcıcevabıo()) {
//
//			System.err.println(iterable_element);
//
//		}
//		for (int iterable_element : sinava.getSorular().get(1).getKullanıcıcevabıo()) {
//
//			System.err.println(iterable_element);
//
//		}
		
		
		///***tekil soruları bulma
		List<Integer> sorular=new ArrayList<Integer>();
		
		
		for (int i : cern) {
			
			
			
		sorular.add(aService.findById(i).get().getQuestion().getqId());	
			
			
		}
		HashSet hs = new HashSet();
        hs.addAll(sorular);
        sorular.clear();
        sorular.addAll(hs);
		
		
		////---
        
        
        
		
	//	System.err.println(sorular);
		
		
		List<List<Integer>> sorunumrasıCevaplar=new ArrayList<>();
		List<List<Integer>> sorunumrasıCevaplar2=new ArrayList<>();
		
		List<Integer> cevaplar;
		sorunumrasıCevaplar.add(sorular);
		
		for (List<Integer> list : sorunumrasıCevaplar) {
			
		
			for (Integer list2 : list) {
				cevaplar=new ArrayList<Integer>();
				List<Answer> dGec=aService.findbyQuestion(qService.getQuestionByid(list2));
				
				
				
				for (Answer list3 : dGec) {
					
					System.err.println(list2+"..nci sarunun cavepalrı arasında  "+list3.getId()+"...yer alır");
					
					for (Integer list4 : cern ) {
						
						if(list3.getId()==list4) {
							
							
							
							cevaplar.add(list4);
							break;
							
						}
						
						
						
					}
					
					
					
					
				}

				if(cevaplar!=null)
					sorunumrasıCevaplar2.add(cevaplar);
			}
		}
		
		
		System.err.println(sorunumrasıCevaplar2);
		
		
		
//		for (int item : sorular) {
//			
//			List<Answer> dGec=aService.findbyQuestion(qService.getQuestionByid(item));
//			
//			for (Answer list : dGec) {
//				
//				
//				sorunumrasıCevaplar.add(item,list.getId());
//				
//				
//				
//			}
//			
//			sorunumrasıCevaplar.add();
//			
//			
//		}
//		
//		System.err.println(sorunumrasıCevaplar);
//		
//		
//		
//		
//		
//		for (List<Answer> item : sorunumrasıCevaplar) {
//			for (Answer item2 : item ) {
//				
//				
//				
//				
//			}
//			
//		}
//		
//		
		
		
		
//		
//		List<List<Integer>> sorularvecevaplar=new ArrayList<List<Integer>>(); 
//		
//		sorularvecevaplar.add(sorular);
//		
//		
//		
//		
//		for (List<Integer> list : sorularvecevaplar) {
//			
//		}
//		
//		
		
		
//		List<Answer> dgec=aService.findbyQuestion(qService.getQuestionByid(sorular.get(0)));
//		
//		
//		for (int i=0;i<sorularvecevaplar.size();i++)
//		{
//			
//			aService.fi
//			
//		}
//		
//		for (List<Integer> list : sorularvecevaplar) {
//			
//			
//			
//			
//		}
		
		
		
//System.err.println(sorularvecevaplar);		
		
		
		
		
		

		return new ModelAndView();

	}

}
