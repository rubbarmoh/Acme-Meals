package controllers.manager;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import services.SocialIdentityService;

import controllers.AbstractController;
import domain.Manager;

import domain.SocialIdentity;

import forms.SocialIdentityForm;

@Controller
@RequestMapping("/socialIdentity")
public class ManagerSocialIdentityController extends AbstractController{
	//Services-------------------------------------------

		@Autowired
		private SocialIdentityService	socialIdentityService;

		@Autowired
		private ManagerService	managerService;

		//Constructor----------------------------------------

		public ManagerSocialIdentityController() {
			super();
		}

		//List--------------------------

		@RequestMapping(value = "/browse", method = RequestMethod.GET)
		public ModelAndView browse() {

			ModelAndView result;
			Collection<SocialIdentity> socialIdentities;
			Manager m=managerService.findByPrincipal();
			socialIdentities = socialIdentityService.findByRestaurant(m);

			result = new ModelAndView("socialIdentity/browse");
			result.addObject("socialIdentities", socialIdentities);
			result.addObject("requestURI", "socialIdentity/browse.do");

			return result;
		}

		//Creation-------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int restaurantId) {

			ModelAndView result;
			SocialIdentityForm socialIdentityForm;

			socialIdentityForm = socialIdentityService.generateForm(restaurantId);
			result = new ModelAndView("socialIdentity/create");
			result.addObject("socialIdentityForm", socialIdentityForm);

			return result;

		}

		//Edition--------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int socialIdentityId) {

			ModelAndView result;
			SocialIdentity socialIdentity;

			socialIdentity = socialIdentityService.findOne(socialIdentityId);
			SocialIdentityForm socialIdentityForm=socialIdentityService.transform(socialIdentity);
			Assert.notNull(socialIdentity);
			result = new ModelAndView("socialIdentity/edit");
			result.addObject("socialIdentityForm", socialIdentityForm);

			return result;

		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid SocialIdentityForm socialIdentityForm, BindingResult binding) {

			ModelAndView result = new ModelAndView();
			SocialIdentity socialIdentity;
			if (binding.hasErrors()) {
				if (socialIdentityForm.getId() != 0) {
					result = createEditModelAndView(socialIdentityForm, null);
				} else {
					result = createEditModelAndView2(socialIdentityForm, null);
				}
			} else {
				try {
					socialIdentity = socialIdentityService.reconstruct(socialIdentityForm, binding);
					socialIdentityService.save(socialIdentity);
					result = browse();
				} catch (Throwable oops) {
					String msgCode = "socialIdentity.save.error";
					result = createEditModelAndView(socialIdentityForm, msgCode);
				}
			}
			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(SocialIdentityForm socialIdentityForm, BindingResult binding) {

			ModelAndView result;

			
			if (binding.hasErrors()) {
				result = createEditModelAndView(socialIdentityForm,null);
			} else {
				try {
					SocialIdentity socialIdentity=socialIdentityService.reconstruct(socialIdentityForm,binding);
					socialIdentityService.delete(socialIdentity);
					result = browse();
				} catch (Throwable oops) {
					result = createEditModelAndView(socialIdentityForm,null);
				}
			}
			return result;
		}

		//Ancillary Methods---------------------------

		protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity, String message) {
			ModelAndView result;

			result = new ModelAndView("socialIdentity/edit");
			result.addObject("socialIdentity", socialIdentity);
			result.addObject("message", message);
			return result;

		}

		

		protected ModelAndView createEditModelAndView(SocialIdentityForm socialIdentityForm,String message) {
			ModelAndView result;

			result = new ModelAndView("socialIdentity/edit");
			result.addObject("socialIdentity", socialIdentityForm);

			result.addObject("message", message);

			return result;

		}
		protected ModelAndView createEditModelAndView2(SocialIdentityForm socialIdentityForm, String message) {
			ModelAndView result;

			result = new ModelAndView("socialIdentity/create");
			result.addObject("socialIdentity", socialIdentityForm);
			result.addObject("message", message);

			return result;

		}

		protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity) {
			ModelAndView result;

			result = createEditModelAndView(socialIdentity, null);

			return result;

	
}
}
