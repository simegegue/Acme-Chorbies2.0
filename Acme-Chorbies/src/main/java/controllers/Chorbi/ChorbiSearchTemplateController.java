
package controllers.Chorbi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CacheTimeService;
import services.ChorbiService;
import services.GenreService;
import services.KindRelationshipService;
import services.SearchTemplateService;
import controllers.AbstractController;
import domain.CacheTime;
import domain.Chorbi;
import domain.Genre;
import domain.KindRelationship;
import domain.SearchTemplate;
import forms.SearchTemplateForm;

@Controller
@RequestMapping("/chorbi/searchTemplate")
public class ChorbiSearchTemplateController extends AbstractController {

	//Services-------------------------

	@Autowired
	private SearchTemplateService	searchTemplateService;

	@Autowired
	private ChorbiService			chorbiService;

	@Autowired
	private CacheTimeService		cacheTimeService;

	@Autowired
	private GenreService			genreService;

	@Autowired
	private KindRelationshipService	kindRelationshipService;


	//Constructor----------------------

	public ChorbiSearchTemplateController() {
		super();
	}

	//Display--------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() throws ParseException {

		ModelAndView result;
		SearchTemplate searchTemplate;
		final Boolean b = this.chorbiService.principalCheckCreditCard();

		final Collection<CacheTime> cacheTimes = this.cacheTimeService.findAll();
		CacheTime cacheTime = new CacheTime();
		for (final CacheTime c : cacheTimes) {
			cacheTime = c;
			break;
		}

		Date cacheTimeDate;
		final SimpleDateFormat fecha = new SimpleDateFormat("HH:mm:ss");

		cacheTimeDate = fecha.parse(cacheTime.getTime());

		final Long cacheTimeHours = cacheTimeDate.getTime();

		searchTemplate = this.searchTemplateService.findByPrincipal();
		final Date d = new Date(System.currentTimeMillis());
		final Long aux = d.getTime() - searchTemplate.getLastTimeSearched().getTime();
		if (aux >= cacheTimeHours) {
			final Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
			searchTemplate.setChorbies(chorbies);
		}
		result = new ModelAndView("searchTemplate/display");
		result.addObject("searchTemplate", searchTemplate);
		result.addObject("validatorCreditCard", b);
		result.addObject("chorbies", searchTemplate.getChorbies());
		result.addObject("requestURI", "chorbi/searchTemplate/display.do");

		return result;
	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int searchTemplateId) {

		ModelAndView result;
		SearchTemplate searchTemplate;

		searchTemplate = this.searchTemplateService.findOne(searchTemplateId);
		final SearchTemplateForm searchTemplateForm = this.searchTemplateService.transform(searchTemplate);
		final Collection<Genre> genres = this.genreService.findAll();
		final Collection<KindRelationship> kindRelationships = this.kindRelationshipService.findAll();
		Assert.notNull(searchTemplateForm);
		result = this.createEditModelAndView(searchTemplateForm);
		result.addObject("genres", genres);
		result.addObject("kindRelationships", kindRelationships);
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SearchTemplateForm searchTemplateForm, final BindingResult binding) {

		ModelAndView result;
		SearchTemplate searchTemplate;
		if (binding.hasErrors())
			result = this.createEditModelAndView(searchTemplateForm);
		else
			try {
				
				if(searchTemplateService.compareSearch(searchTemplateForm)==true){
					searchTemplate = this.searchTemplateService.reconstruct(searchTemplateForm, binding);
					searchTemplateService.save(searchTemplate);
					result = this.display();
					result.addObject("advertisement","Loading cache data");
					result.addObject("chorbies", searchTemplate.getChorbies());
				}else{
					searchTemplate = this.searchTemplateService.reconstruct(searchTemplateForm, binding);
					searchTemplate.setChorbies(this.chorbiService.findBySearchTemplate(searchTemplate));
					searchTemplateService.save(searchTemplate);

					result = this.display();
					result.addObject("chorbies", searchTemplate.getChorbies());
				}
				
				
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(searchTemplateForm, "master.page.commit.error");
			}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(final SearchTemplateForm searchTemplateForm) {

		ModelAndView result;

		result = this.createEditModelAndView(searchTemplateForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final SearchTemplateForm searchTemplateForm, final String message) {
		ModelAndView result;
		final Collection<Genre> genres = this.genreService.findAll();
		final Collection<KindRelationship> kindRelationships = this.kindRelationshipService.findAll();
	
		result = new ModelAndView("searchTemplate/edit");
		result.addObject("searchTemplate", searchTemplateForm);
		result.addObject("genres", genres);
		result.addObject("kindRelationships", kindRelationships);
		result.addObject("message", message);

		return result;

	}

}
