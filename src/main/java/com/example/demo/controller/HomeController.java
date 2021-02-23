package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Answer;
import com.example.demo.models.Category;
import com.example.demo.models.Question;
import com.example.demo.models.Test;
import com.example.demo.services.AnswerService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.QuestionService;
import com.example.demo.services.TestService;

@Controller
public class HomeController {

	@Autowired
	private QuestionService qservice;

	@Autowired
	private TestService tService;

	@Autowired
	private AnswerService aService;

	@Autowired
	private CategoryService cService;

	@RequestMapping("/")
	public ModelAndView home(ModelAndView model, @RequestParam(defaultValue = "") String qtext) {

		ModelAndView mav = new ModelAndView("index");

		Question question = new Question();

//		mav.addObject("categories", cServices.findAll());

		if (qtext.isEmpty() || qtext.isBlank() || qtext == null) {

			mav.addObject("questions", qservice.findAll());

		}

		else {

			mav.addObject("questions", qservice.findByQtext(qtext));
		}
		mav.addObject("question", question);

		mav.addObject("message", "wellcome to soru-x world");

		return mav;
	}

	@RequestMapping("/chome")

	public ModelAndView categoryHome()

	{

		ModelAndView mav = new ModelAndView("chome");

		mav.addObject("categories", cService.findAll());

		return mav;

	}

	@RequestMapping("/thome")

	public ModelAndView testHome() {

		ModelAndView mav = new ModelAndView("thome");

		mav.addObject("tests", tService.findAll());

		return mav;
	}

//	@RequestMapping("/sorular")
//	public ModelAndView sorular() {
//		ModelAndView mav = new ModelAndView("sorular");
//
//		List<Question> questions = qservice.findAll();
//
//		mav.addObject("questions", questions);
//
//		return mav;
//	}

//	@RequestMapping("/new")
//	public ModelAndView newAndupdate() {
//		ModelAndView mav = new ModelAndView("addquestion");
//
//		mav.addObject("question", new Question());
//		mav.addObject("answer", new Answer());
//
//		return mav;
//	}

	@RequestMapping("/newcategory/{id}")

	public ModelAndView newCategory(@PathVariable(value = "id") int id) {

		ModelAndView mav = new ModelAndView("addcategory");

		if (id > 0) {
			Optional<Category> categoryEdit = cService.findById(id);

			Category category = categoryEdit.get();

			mav.addObject("category", category);

		}

		else {

			mav.addObject("category", new Category());

		}

		mav.addObject("categroies", cService.findAll());

		return mav;

	}

	@RequestMapping("/new/{id}")

	public ModelAndView update(@PathVariable(value = "id") int id) {

		ModelAndView mav = new ModelAndView("addquestion");

		mav.addObject("categories", cService.findAll());

		if (id > 0) {

			Question question = qservice.getQuestionByid(id);

			mav.addObject("question", question);

			mav.addObject("answer", new Answer());
			mav.addObject("answers", aService.findbyQuestion(question));

		}

		else {

			mav.addObject("question", new Question());

			mav.addObject("answer", new Answer());

		}

//		mav.addObject("categories", cService.findAll());

		return mav;
	}

	@RequestMapping("/newtest/{id}")
	public ModelAndView newUpTest(@PathVariable(value = "id") int id) {

		ModelAndView mav = new ModelAndView("newUpTest");

		Test test;

		if (id == 0 || id < 1) {

			test = new Test();

		}

		else {
			test = tService.findById(id);

		}

		mav.addObject("categories", cService.findAll());
		mav.addObject("questionsByTest", qservice.getByTest(id));

		mav.addObject("cat", new Category());

		mav.addObject("test", test);
		return mav;
	}

	@RequestMapping(value = "getcategoryquestions", method = RequestMethod.POST)

	public ModelAndView getCatQuestions(@ModelAttribute("cat") Category category,
			@ModelAttribute(value = "test") Test test) {

		ModelAndView mav = new ModelAndView("newUpTest");
		mav.addObject("categories", cService.findAll());

		test = tService.findById(test.gettId());
		mav.addObject("test", test);

		mav.addObject("guestions", qservice.questionsListByCategoryWithTest(test.gettId(), category.getcId()));
		mav.addObject("questionsByTest", qservice.getByTest(test.gettId()));

		for (Question question : qservice.findByCategory(category)) {

			System.err.println(question.getQtext());
		}

		return mav;
	}

	@RequestMapping(value = "/addtest", method = RequestMethod.POST)

	public ModelAndView addTest(@ModelAttribute("cat") Category category, @ModelAttribute("test") Test test) {

		System.err.println(test.getTestTitleText());
		System.err.println(test.gettId());

		if (test.gettId() > 0) {

			Test dGec = tService.findById(test.gettId());

			dGec.setTestTitleText(test.getTestTitleText());

			tService.save(dGec);

		}

		else {

			tService.save(test);
		}

		ModelAndView mav = new ModelAndView("newUpTest");

		mav.addObject("categories", cService.findAll());
		mav.addObject("test", test);
		mav.addObject("tests", tService.findAll());

		return mav;

	}

	@RequestMapping(value = "/addquestion", method = RequestMethod.POST)
	public ModelAndView addQuestion(@ModelAttribute("question") Question question) {

		if (question.getqId() > 0) {
			question.setqId(question.getqId());

			qservice.save(question);

		} else {
			qservice.save(question);

		}
		ModelAndView mav = new ModelAndView("addquestion");
		mav.addObject("categories", cService.findAll());

		Answer answer = new Answer();

		mav.addObject("answer", answer);

		mav.addObject("question", question);

		mav.addObject("answers", aService.findbyQuestion(question));

		return mav;
	}

	@RequestMapping(value = "/addcategory", method = RequestMethod.POST)
	public ModelAndView addCategory(@Valid @ModelAttribute("category") Category category, BindingResult br) {

		ModelAndView mav = new ModelAndView("addcategory");

		if (br.hasErrors()) {
			mav.addObject("categories", cService.findAll());
			return mav;
		} else {

			if (category.getcId() > 0) {
				category.setcId(category.getcId());
				cService.Save(category);
			} else {

				cService.Save(category);

			}
			mav.addObject("categories", cService.findAll());

			return mav;

		}

	}

	@GetMapping("/delete/{id}")

	public String delete(@PathVariable(value = "id") int id) {

		qservice.deleteById(id);

		return "redirect:/";
	}

	@GetMapping("/deletetest/{id}")

	public ModelAndView deleteTest(@PathVariable(value = "id") int id) {

		tService.deleteById(id);
		ModelAndView mav = new ModelAndView("thome");

		mav.addObject("tests", tService.findAll());

		return mav;
	}

	@GetMapping("/deletecategory/{id}")

	public ModelAndView deleteCategory(@PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView("chome");

		mav.addObject("categories", cService.findAll());

		try {
			cService.deleteById(id);
		} catch (Exception e) {

			mav.addObject("message",
					new String("Oups.... sanırım bu kategoride ekli sorular var. Silmen hiçö uygun oplmaz"));

			return mav;

		}

		return mav;
	}

	@GetMapping("/addquestiontotest/{id}/{test}/{cat}")
	public ModelAndView addQuestionToAnswer(@PathVariable(value = "id") int id,
			@PathVariable(value = "test") int testId, @PathVariable(value = "cat") int categoryId) {

		Question question = qservice.getQuestionByid(id);

		Test test = tService.findById(testId);

		test.setQuestion(question);

//		test.getQuestions().add(question);

		tService.save(test);

		List<Question> questionsListByCategoryWithTest = qservice.getByTest(testId);

		ModelAndView mav = new ModelAndView("newUpTest");

		mav.addObject("questionsByTest", qservice.getByTest(testId));

		mav.addObject("guestions", qservice.questionsListByCategoryWithTest(testId, categoryId));

		mav.addObject("categories", cService.findAll());
		mav.addObject("test", test);
		mav.addObject("tests", tService.findAll());
		mav.addObject("cat", cService.findById(categoryId).get());

		return mav;
	}

	@GetMapping("/deleteanswer/{id}")

	public ModelAndView deleteanswer(@PathVariable(value = "id") int id) {

		ModelAndView mav = new ModelAndView("addquestion");

		int dGec = (aService.findById(id)).get().getQuestion().getqId();
		aService.deleteById(id);
		System.err.println(qservice.findById(dGec).get().getqId());
		mav.addObject("answers", aService.findbyQuestion(qservice.findById(dGec).get()));

		mav.addObject("question", qservice.getQuestionByid(dGec));

		mav.addObject("answer", new Answer());

		mav.addObject("categories", cService.findAll());

		return mav;
	}

	@RequestMapping(value = "/answerModify", method = RequestMethod.POST)
	public ModelAndView answerModify(@RequestParam("paramName") Integer id, @RequestParam("paramName2") String id2) {

//		System.err.println(answer.getAtext());
//		System.err.println(answer.getId());
		System.err.println(id);
		System.err.println(id2);

		Optional<Answer> modifyAnswerOptional = aService.findById(id);

		Answer modifyAnswer = modifyAnswerOptional.get();

		System.err.println(modifyAnswer.getAtext());

		modifyAnswer.setAtext(id2);
		System.err.println(modifyAnswer.getAtext());

		aService.save(modifyAnswer);

		ModelAndView mav = new ModelAndView("index");

		mav.addObject("answer", new Answer());

		return mav;
	}

	@RequestMapping(value = "/addanswer", method = RequestMethod.POST)
	public ModelAndView addAnswer(@ModelAttribute("question") Question question,
			@ModelAttribute("answer") Answer answer) {

		System.err.println(question.getqId());

		answer.setQuestion(question);

		aService.save(answer);

		ModelAndView mav = new ModelAndView("addquestion");

		mav.addObject("answers", aService.findbyQuestion(question));

		question = qservice.getQuestionByid(question.getqId());

		mav.addObject("question", question);

		mav.addObject("answer", new Answer());
		mav.addObject("categories", cService.findAll());

		return mav;
	}

}
