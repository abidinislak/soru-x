package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.ResultQuestionAnswerRepo;
import com.example.demo.dao.ResultQuestionRepo;
import com.example.demo.dao.ResultRepo;
import com.example.demo.models.Answer;
import com.example.demo.models.Question;
import com.example.demo.models.Result;
import com.example.demo.models.ResultQuestion;
import com.example.demo.models.ResultQuestionAnswer;
import com.example.demo.models.Test;
import com.example.demo.services.AnswerService;
import com.example.demo.services.QuestionService;
import com.example.demo.services.TestService;
import com.example.demo.utils.Deneme;
import com.example.demo.utils.Sonuc;
import com.example.demo.utils.sorular;

@Controller
@RequestMapping("/deneme")
public class DenemeController {

	@Autowired
	private TestService tService;
	@Autowired
	private ResultRepo rService;
	@Autowired
	private ResultQuestionRepo rqService;

	@Autowired
	private ResultQuestionAnswerRepo    rqaService;
	
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
		int[] asd = new int[sirano];
		
		for(int i=0;i<sirano;i++) {
			
			asd[i]=i;
			
			
		}
		

		deneme.setSayi(asd);
		mav.addObject("sorusira", 0);

		Integer[] cern = {};
		mav.addObject("cern", cern);

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

	@RequestMapping(value = "/sinava", method = RequestMethod.POST)
	public ModelAndView sinava(@ModelAttribute("deneme") Deneme deneme,
			@RequestParam("cern") int[] cern,@RequestParam("www") int www){

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

		/// ***tekil soruları bulma
		List<Integer> testtekiTumSorular = new ArrayList<Integer>();

		for (int i : cern) {

			testtekiTumSorular.add(aService.findById(i).get().getQuestion().getqId());

		}
		HashSet hs = new HashSet();
		hs.addAll(testtekiTumSorular);
		testtekiTumSorular.clear();
		testtekiTumSorular.addAll(hs);

		//// --- testteki soru idleri

		List<List<Integer>> sorunumrasıCevaplar = new ArrayList<>();
		List<List<Integer>> testtekiSorularaVerielnKullaniciCevaplari = new ArrayList<>();

		List<Integer> cevaplar;
		sorunumrasıCevaplar.add(testtekiTumSorular);
		for (List<Integer> list : sorunumrasıCevaplar) {
			for (Integer list2 : list) {
				cevaplar = new ArrayList<Integer>();
				List<Answer> dGec = aService.findbyQuestion(qService.getQuestionByid(list2));
				for (Answer list3 : dGec) {
					for (Integer list4 : cern) {
						if (list3.getId() == list4) {
							cevaplar.add(list4);
							break;
						}
					}
				}
				if (cevaplar != null)
					testtekiSorularaVerielnKullaniciCevaplari.add(cevaplar);
			}
		}
		/// her bir sorunun cveaplarının idleri

		List<List<Integer>> TestekiSorularinTrueCevaplari = new ArrayList<>();

		for (List<Integer> list : sorunumrasıCevaplar) {
			for (Integer list2 : list) {

				List<Integer> dGec = aService.trueAnswers(list2);
				TestekiSorularinTrueCevaplari.add(dGec);

			}

		}
		/// her bir sorunun dogru veaplari

		/// *** sonucun tespiti

		List<Sonuc> dGecsonuc = new ArrayList<>();

		for (int i = 0; i < testtekiTumSorular.size(); i++) {

			Sonuc dGec = new Sonuc();

			dGec.soruid = testtekiTumSorular.get(i);
			dGec.trueMu = testtekiSorularaVerielnKullaniciCevaplari.get(i).equals(TestekiSorularinTrueCevaplari.get(i));
			dGecsonuc.add(dGec);
		}

		int truecount = 0;

		for (Sonuc sonuc : dGecsonuc) {
			if (sonuc.trueMu) {
				truecount++;
			}

		}
			
		
		
		
		
		
		Double score = (double) truecount / (double) dGecsonuc.size();
		
		score=score*100;
		ModelAndView mav = new ModelAndView("deneme/sonuc");
		mav.addObject("sonuc", dGecsonuc);
		mav.addObject("sorusayisi", dGecsonuc.size());
		mav.addObject("trueCount", truecount);
		mav.addObject("score", score.intValue());
		
		
		///+++ test sonucunu kaydetme
		
		Result dGecResult=new Result();
		dGecResult.setTest(tService.findById(www));
		dGecResult.setScore(score.intValue());
		
		dGecResult=	rService.save(dGecResult);
		
		
	      List<ResultQuestion > 	dgec=new ArrayList<ResultQuestion>();
		
		for (Sonuc item : dGecsonuc) {
			
			ResultQuestion 	dgecrq=new ResultQuestion();
			dgecrq.setResultId(dGecResult.getId());
			dgecrq.setQuestion(item.soruid);
			dgecrq.setQisTrue(item.trueMu);
			dgecrq=rqService.save(dgecrq);
			
			
			dgec.add(dgecrq);
			
				
			
		}
		
		
		for ( int i=0;i<testtekiSorularaVerielnKullaniciCevaplari.size();i++ )
		{
			
			
			
			
			
			for(  int a=0;a<testtekiSorularaVerielnKullaniciCevaplari.get(i).size();a++) {
				
				
				ResultQuestionAnswer dgecRQA=new ResultQuestionAnswer();
				dgecRQA.setResultQuestionId(dgec.get(i).getId());

				dgecRQA.setAnswerId(testtekiSorularaVerielnKullaniciCevaplari.get(i).get(a));
				
				
				dgecRQA.setAtrue(     aService.findById(testtekiSorularaVerielnKullaniciCevaplari.get(i).get(a)).get().isTrueMu());
				
				
				rqaService.save(dgecRQA);
				
				
				
			}
			
			
			
		}
		
		
		
		
		
		

		return mav;

	}

}
